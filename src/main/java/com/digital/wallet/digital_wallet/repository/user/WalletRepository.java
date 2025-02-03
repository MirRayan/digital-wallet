package com.digital.wallet.digital_wallet.repository.user;

import com.digital.wallet.digital_wallet.entity.Users.UserType;
import com.digital.wallet.digital_wallet.entity.Users.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, String > {

    Optional<Wallet> findByUserId(String userId);

    List<Wallet> findByUserIdIsNull();

    List<Wallet> findByCurrencyId(String currencyId);

}
