package com.digital.wallet.digital_wallet.controller;

import com.digital.wallet.digital_wallet.dtos.Users.BankAccountDTOS;
import com.digital.wallet.digital_wallet.entity.BankAccount;
import com.digital.wallet.digital_wallet.entity.Users;
import com.digital.wallet.digital_wallet.response.BaseResponse;
import com.digital.wallet.digital_wallet.response.ErrorResponse;
import com.digital.wallet.digital_wallet.service.BankAccountService;
import com.digital.wallet.digital_wallet.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/bankAccount")
@AllArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createBankAccount(@Valid @RequestBody BankAccountDTOS bankAccountDTOS) {

        Optional<Users> optionalUser;

        optionalUser = userService.findById(bankAccountDTOS.getUserID());

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Need User information "), HttpStatus.BAD_REQUEST);
        } else {


            BankAccount bankAccount = new BankAccount();
            if (bankAccountDTOS.getId() != null){
                bankAccount.setId(bankAccountDTOS.getId());
            }
            bankAccount.setName(bankAccountDTOS.getName());
            bankAccount.setNumber(bankAccountDTOS.getNumber());
            bankAccount.setBankName(bankAccountDTOS.getBankName());
            bankAccount.setBranchName(bankAccountDTOS.getBranchName());
            bankAccount.setUser(optionalUser.get());

            BankAccount newBankAccount = bankAccountService.createBankAccount(bankAccount);
            return new ResponseEntity<>(new BaseResponse(newBankAccount, HttpServletResponse.SC_CREATED, null), HttpStatus.OK);

        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBankAccount(@RequestBody BankAccountDTOS bankAccountDTOS) {
        return createBankAccount(bankAccountDTOS);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBankAccount(@Valid @PathVariable String id) {

        Optional<BankAccount> optionalBankAccount = bankAccountService.findById(id);

        if (optionalBankAccount.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Currency not Available"), HttpStatus.BAD_REQUEST);
        } else {
            bankAccountService.deleteBankAccount(id);
            return new ResponseEntity<>(new BaseResponse(id, HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllBankAccount() {

        return new ResponseEntity<>(new BaseResponse(bankAccountService.getAllBankAccount(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBankAccountById(@PathVariable String id) {

        Optional<BankAccount> optionalCurrency = bankAccountService.findById(id);

        if (optionalCurrency.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Currency not Available"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new BaseResponse(optionalCurrency.get(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

}
