package com.digital.wallet.digital_wallet.controller;

import com.digital.wallet.digital_wallet.dtos.Users.LoginResponse;
import com.digital.wallet.digital_wallet.dtos.Users.LoginUserDto;
import com.digital.wallet.digital_wallet.dtos.Users.UserRegistration;
import com.digital.wallet.digital_wallet.entity.Currency;
import com.digital.wallet.digital_wallet.entity.Users;
import com.digital.wallet.digital_wallet.entity.Wallet;
import com.digital.wallet.digital_wallet.response.BaseResponse;
import com.digital.wallet.digital_wallet.response.ErrorResponse;
import com.digital.wallet.digital_wallet.service.CurrencyService;
import com.digital.wallet.digital_wallet.service.JwtService;
import com.digital.wallet.digital_wallet.service.UserService;
import com.digital.wallet.digital_wallet.service.WalletService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final WalletService walletService;
    private final CurrencyService currencyService;
    private final JwtService jwtService;

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody UserRegistration userRegistration) {

        Optional<Users> optionalUsers = userService.findByUserEmail(userRegistration.getEmail());

        if (optionalUsers.isPresent()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user exists "), HttpStatus.BAD_REQUEST);
        } else {

            Wallet tempWallet = new Wallet();
            tempWallet.setAmount(0);

            Optional<Currency> optionalCurrency = currencyService.findByAbbreviation("BDT");

            if (optionalCurrency.isPresent()) {
                tempWallet.setCurrency(optionalCurrency.get());
            } else {
                Currency newCurrency = new Currency();
                newCurrency.setAbbreviation("BDT");
                newCurrency.setName("Bangladesh Taka");

                tempWallet.setCurrency(currencyService.createCurrency(newCurrency));
            }

            Users convertToUser = userService.convertToUsers(userRegistration);
            convertToUser.setWallet(tempWallet);

            Users newUser = userService.addNewUser(convertToUser);

            return new ResponseEntity<>(new BaseResponse(newUser, HttpServletResponse.SC_CREATED, null), HttpStatus.OK);

        }

    }

    @PutMapping("/update")
    public Users updateUser(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> userDelete(@Valid @PathVariable String id) {

        Optional<Users> optionalUserType = userService.findById(id);

        if (optionalUserType.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user not Available"), HttpStatus.BAD_REQUEST);
        } else {
            //userService.deleteUser(id);
            //return new ResponseEntity<>(new BaseResponse(id, HttpServletResponse.SC_OK, null), HttpStatus.OK);
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user Cannot be deleted"), HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> userGetAll() {

        return new ResponseEntity<>(new BaseResponse(userService.getAllUser(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> userGet(@PathVariable String id) {

        Optional<Users> optionalUserType = userService.findById(id);

        if (optionalUserType.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user type not Available"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new BaseResponse(optionalUserType.get(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Users authenticatedUser = userService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return new ResponseEntity<>(new BaseResponse(loginResponse, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

}
