package com.enigmabank.enigmabanking.service;

import com.enigmabank.enigmabanking.dto.response.UserResponse;
import com.enigmabank.enigmabanking.entity.AppUser;
import com.enigmabank.enigmabanking.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
    UserResponse getUserById(String id);
    User getUserByIdNoResponse(String id);
}
