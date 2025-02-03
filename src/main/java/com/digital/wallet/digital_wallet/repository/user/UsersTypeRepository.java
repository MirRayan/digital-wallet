package com.digital.wallet.digital_wallet.repository.user;

import com.digital.wallet.digital_wallet.entity.Users.UserType;
import com.digital.wallet.digital_wallet.entity.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersTypeRepository extends JpaRepository<UserType, String > {

    Optional<UserType> findByName(String name);

}
