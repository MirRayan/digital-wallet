package com.digital.wallet.digital_wallet.controller;

import com.digital.wallet.digital_wallet.dtos.Users.UserRegistration;
import com.digital.wallet.digital_wallet.entity.Users.Users;
import com.digital.wallet.digital_wallet.entity.Users.Wallet;
import com.digital.wallet.digital_wallet.response.BaseResponse;
import com.digital.wallet.digital_wallet.response.ErrorResponse;
import com.digital.wallet.digital_wallet.service.UserService;
import com.digital.wallet.digital_wallet.service.WalletService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallet")
@AllArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<?> createWallet(@Valid @RequestBody Wallet wallet) {

        //Optional<Wallet> optionalWallet = walletService.findByUserId(wallet.getId());

        /*if (optionalWallet.isPresent()) {
            return new ResponseEntity<>(new BaseResponse(optionalWallet.get(), HttpServletResponse.SC_CREATED, null), HttpStatus.OK);
        } else {*/
            Wallet newWallet = walletService.createWallet(wallet);
            return new ResponseEntity<>(new BaseResponse(newWallet, HttpServletResponse.SC_CREATED, null), HttpStatus.OK);

        //}

    }

    @PutMapping("/update")
    public Wallet updateWallet(@RequestBody Wallet wallet) {
        return walletService.updateWallet(wallet);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWallet(@Valid @PathVariable String id) {

        Optional<Wallet> optionalUserType = walletService.findById(id);

        if (optionalUserType.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wallet not Available"), HttpStatus.BAD_REQUEST);
        } else {
            walletService.deleteWallet(id);
            return new ResponseEntity<>(new BaseResponse(id, HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @DeleteMapping("/deleteUserIdNull")
    public ResponseEntity<?> deleteWalletUserIdNull() {

        List<Wallet> listWallet = walletService.findByUserIdInNull();

        if (listWallet.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wallet not Available"), HttpStatus.BAD_REQUEST);
        } else {

            List<Wallet> deletedWallet = new ArrayList<>();

            for (Wallet wallet: listWallet){

                walletService.deleteWallet(wallet.getId());
                deletedWallet.add(wallet);

            }

            return new ResponseEntity<>(new BaseResponse(deletedWallet, HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllWallet() {

        return new ResponseEntity<>(new BaseResponse(walletService.getAllWallet(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getWallet(@PathVariable String id) {

        Optional<Wallet> optionalWallet = walletService.findById(id);

        if (optionalWallet.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wallet not Available"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new BaseResponse(optionalWallet.get(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getWalletByUserId(@PathVariable String userId) {

        Optional<Wallet> optionalWallet = walletService.findByUserId(userId);

        if (optionalWallet.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wallet not Available"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new BaseResponse(optionalWallet.get(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @GetMapping("/getByCurrencyId/{currencyId}")
    public ResponseEntity<?> getWalletByCurrencyId(@PathVariable String currencyId) {

        List<Wallet> listWallet = walletService.findByCurrencyId(currencyId);

        if (listWallet.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wallet not Available"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new BaseResponse(listWallet, HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @GetMapping("/getUserIdIsNull")
    public ResponseEntity<?> getWalletUserIdIsNull() {

        List<Wallet> listWallet = walletService.findByUserIdInNull();

        if (listWallet.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wallet not Available"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new BaseResponse(listWallet, HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

}
