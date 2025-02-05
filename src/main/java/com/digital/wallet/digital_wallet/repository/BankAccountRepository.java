package com.digital.wallet.digital_wallet.repository;

import com.digital.wallet.digital_wallet.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, String > {

    Optional<BankAccount> findByNameAndNumberAndBankName(String name, String number, String bankName);

}
