package com.digital.wallet.digital_wallet.service;

import com.digital.wallet.digital_wallet.entity.Wallet;
import com.digital.wallet.digital_wallet.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {

        Wallet savedWallet = walletRepository.save(wallet);

        return savedWallet;

    }

    public void deleteWallet(String id) {
        walletRepository.deleteById(id);
    }

    public Wallet updateWallet(Wallet wallet) {
        return createWallet(wallet);
    }

    public Optional<Wallet> findById(String id){
        return walletRepository.findById(id);
    }

    public Optional<Wallet> findByUserId(String userId){
        return walletRepository.findByUserId(userId);
    }

    public List<Wallet> findByCurrencyId(String currencyId){
        return walletRepository.findByCurrencyId(currencyId);
    }

    public List<Wallet> findByUserIdInNull(){
        return walletRepository.findByUserIdIsNull();
    }

    public List<Wallet> getAllWallet(){
        return walletRepository.findAll();
    }

}


