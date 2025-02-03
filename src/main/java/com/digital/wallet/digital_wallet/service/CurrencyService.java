package com.digital.wallet.digital_wallet.service;

import com.digital.wallet.digital_wallet.entity.Users.Currency;
import com.digital.wallet.digital_wallet.entity.Users.UserType;
import com.digital.wallet.digital_wallet.repository.user.CurrencyRepository;
import com.digital.wallet.digital_wallet.repository.user.UsersTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public Currency createCurrency(Currency currency) {

        Currency savedCurrency = currencyRepository.save(currency);

        return savedCurrency;

    }

    public void deleteCurrency(String id) {
        currencyRepository.deleteById(id);
    }

    public Currency updateCurrency(Currency currency) {
        return createCurrency(currency);
    }

    public Optional<Currency> findById(String id){
        return currencyRepository.findById(id);
    }

    public Optional<Currency> findByName(String name){
        return currencyRepository.findByName(name);
    }

    public Optional<Currency> findByAbbreviation(String abbreviation){
        return currencyRepository.findByAbbreviation(abbreviation);
    }

    public List<Currency> getAllCurrency(){
        return currencyRepository.findAll();
    }

}


