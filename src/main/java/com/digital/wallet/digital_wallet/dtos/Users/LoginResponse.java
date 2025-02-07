package com.digital.wallet.digital_wallet.dtos.Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("expiresIn")
    private long expiresIn;

}
