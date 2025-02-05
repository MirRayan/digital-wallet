package com.digital.wallet.digital_wallet.service;

import com.digital.wallet.digital_wallet.entity.BankAccount;
import com.digital.wallet.digital_wallet.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserService userService;

    public BankAccount createBankAccount(BankAccount bankAccount) {

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return savedBankAccount;

    }

    public void deleteBankAccount(String id) {
        bankAccountRepository.deleteById(id);
    }

    public BankAccount updateBankAccount(BankAccount bankAccount) {
        return createBankAccount(bankAccount);
    }

    public Optional<BankAccount> findById(String id){
        return bankAccountRepository.findById(id);
    }

    public List<BankAccount> getAllBankAccount(){
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> findByNameAndNumberAndBankName(String name, String number, String bankName){
        return bankAccountRepository.findByNameAndNumberAndBankName(name, number, bankName);
    }

}


