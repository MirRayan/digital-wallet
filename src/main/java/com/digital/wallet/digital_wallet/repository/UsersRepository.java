package com.digital.wallet.digital_wallet.repository;

import com.digital.wallet.digital_wallet.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String > {

    Optional<Users> findByEmail(String email);

}
