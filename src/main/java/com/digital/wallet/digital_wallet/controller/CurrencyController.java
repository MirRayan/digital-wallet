package com.digital.wallet.digital_wallet.controller;

import com.digital.wallet.digital_wallet.dtos.Users.UserRegistration;
import com.digital.wallet.digital_wallet.entity.Users.Currency;
import com.digital.wallet.digital_wallet.entity.Users.Users;
import com.digital.wallet.digital_wallet.response.BaseResponse;
import com.digital.wallet.digital_wallet.response.ErrorResponse;
import com.digital.wallet.digital_wallet.service.CurrencyService;
import com.digital.wallet.digital_wallet.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/currency")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping("/create")
    public ResponseEntity<?> createCurrency(@Valid @RequestBody Currency currency) {

        Optional<Currency> optionalCurrencyByName = currencyService.findByName(currency.getName());
        Optional<Currency> optionalCurrencyByAbbreviation = currencyService.findByAbbreviation(currency.getAbbreviation());

        if (optionalCurrencyByName.isPresent() || optionalCurrencyByAbbreviation.isPresent()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Currency exists "), HttpStatus.BAD_REQUEST);
        } else {
            Currency createdCurrency = currencyService.createCurrency(currency);
            return new ResponseEntity<>(new BaseResponse(createdCurrency, HttpServletResponse.SC_CREATED, null), HttpStatus.OK);

        }

    }

    @PutMapping("/update")
    public Currency updateCurrency(@RequestBody Currency currency) {
        return currencyService.updateCurrency(currency);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCurrency(@Valid @PathVariable String id) {

        Optional<Currency> optionalCurrency = currencyService.findById(id);

        if (optionalCurrency.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Currency not Available"), HttpStatus.BAD_REQUEST);
        } else {
            currencyService.deleteCurrency(id);
            return new ResponseEntity<>(new BaseResponse(id, HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCurrency() {

        return new ResponseEntity<>(new BaseResponse(currencyService.getAllCurrency(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCurrencyById(@PathVariable String id) {

        Optional<Currency> optionalCurrency = currencyService.findById(id);

        if (optionalCurrency.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Currency not Available"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new BaseResponse(optionalCurrency.get(), HttpServletResponse.SC_OK, null), HttpStatus.OK);

        }

    }

}
