package com.digital.wallet.digital_wallet.controller;

import com.digital.wallet.digital_wallet.entity.UserType;
import com.digital.wallet.digital_wallet.response.BaseResponse;
import com.digital.wallet.digital_wallet.response.ErrorResponse;
import com.digital.wallet.digital_wallet.service.UsersTypeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/usersType")
@AllArgsConstructor
public class UserTypeController {

    private final UsersTypeService usersTypeService;

    @PostMapping("/create")
    public ResponseEntity<?> userTypeCreate(@Valid @RequestBody UserType userType) {

        Optional<UserType> optionalUserType = usersTypeService.findByName(userType.getName());

        if (optionalUserType.isPresent()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user type exists "), HttpStatus.BAD_REQUEST);
        } else {

            userType.setCreatedOn(ZonedDateTime.now());
            userType.setUpdatedOn(ZonedDateTime.now());

            UserType newUserType = usersTypeService.addNewUserType(userType);
            return new ResponseEntity<>(new BaseResponse(newUserType, HttpServletResponse.SC_CREATED, null), HttpStatus.OK);

        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> userTypeUpdate(@Valid @RequestBody UserType userType) {

        Optional<UserType> optionalUserType = usersTypeService.findByName(userType.getName());

        if (optionalUserType.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user type not Available"), HttpStatus.BAD_REQUEST);
        } else {
            UserType newUserType = usersTypeService.updateUserType(userType);
            return new ResponseEntity<>(new BaseResponse(newUserType, HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> userTypeDelete(@Valid @PathVariable String id) {

        Optional<UserType> optionalUserType = usersTypeService.findById(id);

        if (optionalUserType.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user type not Available"), HttpStatus.BAD_REQUEST);
        } else {
            usersTypeService.deleteUserType(id);
            return new ResponseEntity<>(new BaseResponse(id, HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> userTypeGetAll() {

        return new ResponseEntity<>(new BaseResponse(usersTypeService.getAllUserType(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> userTypeGet(@PathVariable String id) {

        Optional<UserType> optionalUserType = usersTypeService.findById(id);

        if (optionalUserType.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user type not Available"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new BaseResponse(optionalUserType.get(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

}
