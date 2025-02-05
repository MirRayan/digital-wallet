package com.digital.wallet.digital_wallet.controller;

import com.digital.wallet.digital_wallet.dtos.Users.UserRegistration;
import com.digital.wallet.digital_wallet.entity.Currency;
import com.digital.wallet.digital_wallet.entity.Users;
import com.digital.wallet.digital_wallet.entity.Wallet;
import com.digital.wallet.digital_wallet.response.BaseResponse;
import com.digital.wallet.digital_wallet.response.ErrorResponse;
import com.digital.wallet.digital_wallet.service.CurrencyService;
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

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody UserRegistration userRegistration) {

        Optional<Users> optionalUsers = userService.findByUserEmail(userRegistration.getEmail());

        if (optionalUsers.isPresent()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "user exists "), HttpStatus.BAD_REQUEST);
        } else {

            Wallet tempWallet = new Wallet();
            Optional<Currency> currency = currencyService.findById("3398dbd7-70d1-4c5c-a100-bfdd62c94c06");

            if (currency.isPresent()){
                tempWallet.setCurrency(currency.get());
                tempWallet.setAmount(0);

                Wallet wallet = walletService.createWallet(tempWallet);

                Users convertToUser = userService.convertToUsers(userRegistration);
                convertToUser.setWallet(wallet);

                Users newUser = userService.addNewUser(convertToUser);

                tempWallet.setUserId(newUser.getId());

                walletService.updateWallet(wallet);

                newUser = userService.updateUser(newUser);

                return new ResponseEntity<>(new BaseResponse(newUser, HttpServletResponse.SC_CREATED, null), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Currency not Available"), HttpStatus.BAD_REQUEST);
            }

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

}
