package com.digital.wallet.digital_wallet.dtos.Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

}
