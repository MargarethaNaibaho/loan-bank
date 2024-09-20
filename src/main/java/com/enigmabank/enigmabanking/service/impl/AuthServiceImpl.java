package com.enigmabank.enigmabanking.service.impl;

import com.enigmabank.enigmabanking.constant.ERole;
import com.enigmabank.enigmabanking.dto.request.LoginRequest;
import com.enigmabank.enigmabanking.dto.request.RegisterAdminAndStaffRequest;
import com.enigmabank.enigmabanking.dto.request.RegisterCustomerRequest;
import com.enigmabank.enigmabanking.dto.response.AdminAndStaffResponse;
import com.enigmabank.enigmabanking.dto.response.LoginResponse;
import com.enigmabank.enigmabanking.dto.response.RegisterResponse;
import com.enigmabank.enigmabanking.entity.*;
import com.enigmabank.enigmabanking.repository.UserRepository;
import com.enigmabank.enigmabanking.security.JwtUtil;
import com.enigmabank.enigmabanking.service.AuthService;
import com.enigmabank.enigmabanking.service.CustomerService;
import com.enigmabank.enigmabanking.service.ProfilePictureService;
import com.enigmabank.enigmabanking.service.RoleService;
import com.enigmabank.enigmabanking.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final RoleService roleService;
    private final ProfilePictureService profilePictureService;
    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        try {
            validationUtil.validate(registerCustomerRequest);

            Role role = roleService.getOrSave(Role.builder()
                            .role(ERole.ROLE_CUSTOMER)
                            .build());

            User user = User.builder()
                    .email(registerCustomerRequest.getEmail().toLowerCase())
                    .password(passwordEncoder.encode(registerCustomerRequest.getPassword()))
                    .role(role)
                    .build();
            userRepository.saveAndFlush(user);

            ProfilePicture profilePicture = profilePictureService.createFile(registerCustomerRequest.getProfilePicture());

            Customer customer = Customer.builder()
                    .firstName(registerCustomerRequest.getFirstName())
                    .lastName(registerCustomerRequest.getLastName())
                    .dateOfBirth(registerCustomerRequest.getDateOfBirth())
                    .phone(registerCustomerRequest.getPhone())
                    .profilePicture(profilePicture)
                    .user(user)
                    .build();
            customerService.createNewCustomer(customer);

            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRole().getRole().toString())
                    .build();

        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerAdmin(RegisterAdminAndStaffRequest registerAdminAndStaffRequest) {
        try{
            validationUtil.validate(registerAdminAndStaffRequest);
            Role role = roleService.getOrSave(Role.builder()
                            .role(ERole.ROLE_ADMIN)
                            .build());

            User user = User.builder()
                    .email(registerAdminAndStaffRequest.getEmail().toLowerCase())
                    .password(passwordEncoder.encode(registerAdminAndStaffRequest.getPassword()))
                    .role(role)
                    .build();
            userRepository.saveAndFlush(user);

            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRole().getRole().toString())
                    .build();

        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists!");
        }
    }

    @Override
    public RegisterResponse registerStaff(RegisterAdminAndStaffRequest registerAdminAndStaffRequest) {
        try{
            validationUtil.validate(registerAdminAndStaffRequest);
            Role role = roleService.getOrSave(Role.builder()
                    .role(ERole.ROLE_STAFF)
                    .build());

            User user = User.builder()
                    .email(registerAdminAndStaffRequest.getEmail().toLowerCase())
                    .password(passwordEncoder.encode(registerAdminAndStaffRequest.getPassword()))
                    .role(role)
                    .build();
            userRepository.saveAndFlush(user);

            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRole().getRole().toString())
                    .build();

        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists!");
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validationUtil.validate(loginRequest);

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        AppUser appUser = (AppUser) authenticate.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
