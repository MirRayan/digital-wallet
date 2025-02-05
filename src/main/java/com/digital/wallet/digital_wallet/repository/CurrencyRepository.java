package com.digital.wallet.digital_wallet.repository;

import com.digital.wallet.digital_wallet.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, String > {

    Optional<Currency> findByName(String name);

    Optional<Currency> findByAbbreviation(String abbreviation);

}
