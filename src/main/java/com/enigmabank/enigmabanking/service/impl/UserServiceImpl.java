package com.enigmabank.enigmabanking.service.impl;

import com.enigmabank.enigmabanking.dto.response.UserResponse;
import com.enigmabank.enigmabanking.entity.AppUser;
import com.enigmabank.enigmabanking.entity.User;
import com.enigmabank.enigmabanking.repository.UserRepository;
import com.enigmabank.enigmabanking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid credential!"));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().getRole())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));

        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().getRole())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User isnt't found"));
        UserResponse userResponse = UserResponse.builder()
                .email(user.getEmail())
                .role(user.getRole().getRole().toString())
                .build();
        return userResponse;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByIdNoResponse(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User isnt't found"));
    }
}
