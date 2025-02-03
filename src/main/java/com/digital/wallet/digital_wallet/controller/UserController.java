package com.digital.wallet.digital_wallet.controller;

import com.digital.wallet.digital_wallet.dtos.Users.RegistrationRequest;
import com.digital.wallet.digital_wallet.dtos.Users.UserInfoResponse;
import com.digital.wallet.digital_wallet.dtos.Users.UserRegistration;
import com.digital.wallet.digital_wallet.entity.Users.Users;
import com.digital.wallet.digital_wallet.exception.BadRequestException;
import com.digital.wallet.digital_wallet.response.BaseResponse;
import com.digital.wallet.digital_wallet.response.ErrorResponse;
import com.digital.wallet.digital_wallet.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody UserRegistration userRegistration) {

        Optional<Users> optionalUsers = userService.findByUserEmail(userRegistration.getEmail());

        if (optionalUsers.isPresent()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user exists "), HttpStatus.BAD_REQUEST);
        } else {
            Users newUser = userService.addNewUser(userRegistration);
            return new ResponseEntity<>(new BaseResponse(newUser, HttpServletResponse.SC_CREATED, null), HttpStatus.OK);

        }

    }

}
