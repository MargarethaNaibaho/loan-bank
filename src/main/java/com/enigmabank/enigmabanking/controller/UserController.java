package com.enigmabank.enigmabanking.controller;

import com.enigmabank.enigmabanking.dto.response.CommonResponse;
import com.enigmabank.enigmabanking.dto.response.UserResponse;
import com.enigmabank.enigmabanking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        UserResponse userResponse = userService.getUserById(id);
        CommonResponse<UserResponse> commonResponse = CommonResponse.<UserResponse>builder()
                .message("Successfully get user by id")
                .statusCode(HttpStatus.OK.value())
                .data(userResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
