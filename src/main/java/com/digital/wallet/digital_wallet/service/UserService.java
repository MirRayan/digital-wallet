package com.digital.wallet.digital_wallet.service;

import com.digital.wallet.digital_wallet.dtos.Users.UserRegistration;
import com.digital.wallet.digital_wallet.entity.Users.UserType;
import com.digital.wallet.digital_wallet.entity.Users.Users;
import com.digital.wallet.digital_wallet.repository.user.UsersRepository;
import com.digital.wallet.digital_wallet.repository.user.UsersTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final UsersTypeService usersTypeService;

    public Users addNewUser(UserRegistration userRegistration) {

        Users savedUser = usersRepository.save(convertToUsers(userRegistration));

        return savedUser;

    }

    public Optional<Users> findByUserEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public Users convertToUsers(UserRegistration userRegistration){

        Optional<UserType> userType = usersTypeService.findById(userRegistration.getUserTypeId());
        Users user = new Users();

        if (userType.isPresent()){
            user.setEmail(userRegistration.getEmail());
            user.setPassword(userRegistration.getPassword());
            user.setPhoneNumber(userRegistration.getPhoneNumber());
            user.setUserType(userType.get());
            user.setCreatedOn(ZonedDateTime.now());
            user.setUpdatedOn(ZonedDateTime.now());
        }

        return user;

    }

}


