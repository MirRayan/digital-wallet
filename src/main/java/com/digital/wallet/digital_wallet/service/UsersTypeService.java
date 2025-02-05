package com.digital.wallet.digital_wallet.service;

import com.digital.wallet.digital_wallet.entity.UserType;
import com.digital.wallet.digital_wallet.repository.UsersTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    public UserType addNewUserType(UserType userType) {

        UserType savedUserType = usersTypeRepository.save(userType);

        return savedUserType;

    }

    public void deleteUserType(String id) {
        usersTypeRepository.deleteById(id);
    }

    public UserType updateUserType(UserType userType) {
        return addNewUserType(userType);
    }

    public Optional<UserType> findById(String id){
        return usersTypeRepository.findById(id);
    }

    public Optional<UserType> findByName(String name){
        return usersTypeRepository.findByName(name);
    }

    public List<UserType> getAllUserType(){
        return usersTypeRepository.findAll();
    }

}


