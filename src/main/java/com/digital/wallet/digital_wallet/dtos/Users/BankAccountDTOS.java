package com.digital.wallet.digital_wallet.dtos.Users;

import com.digital.wallet.digital_wallet.entity.AuditEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTOS extends AuditEntity {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("number")
    private String number;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("branch_name")
    private String branchName;

    @JsonProperty("userId")
    private String userID;

}
