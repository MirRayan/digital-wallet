package com.digital.wallet.digital_wallet.service;

import com.digital.wallet.digital_wallet.dtos.Users.LoginUserDto;
import com.digital.wallet.digital_wallet.dtos.Users.UserRegistration;
import com.digital.wallet.digital_wallet.entity.UserType;
import com.digital.wallet.digital_wallet.entity.Users;
import com.digital.wallet.digital_wallet.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final UsersTypeService usersTypeService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public Users addNewUser(Users users) {

        Users savedUser = usersRepository.save(users);

        return savedUser;

    }

    public Users updateUser(Users users) {

        Users updatedUser = addNewUser(users);

        return updatedUser;

    }

    public Optional<Users> findByUserEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public Users convertToUsers(UserRegistration userRegistration){

        Optional<UserType> userType = usersTypeService.findById(userRegistration.getUserTypeId());
        Users user = new Users();

        if (userType.isPresent()){
            user.setEmail(userRegistration.getEmail());
            user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
            user.setPhoneNumber(userRegistration.getPhoneNumber());
            user.setUserType(userType.get());
            user.setCreatedOn(ZonedDateTime.now());
            user.setUpdatedOn(ZonedDateTime.now());
        }

        return user;

    }

    public Optional<Users> findById(String id) {
        return usersRepository.findById(id);
    }

    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    public Users authenticate(LoginUserDto loginUserDto) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));

        return findByUserEmail(loginUserDto.getEmail()).orElseThrow();

    }
}


