package com.digital.wallet.digital_wallet.repository;

import com.digital.wallet.digital_wallet.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersTypeRepository extends JpaRepository<UserType, String > {

    Optional<UserType> findByName(String name);

}
