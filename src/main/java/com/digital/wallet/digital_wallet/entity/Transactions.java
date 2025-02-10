package com.digital.wallet.digital_wallet.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.digital.wallet.digital_wallet.utls.StaticTextConfig.DB_SCHEMA;

@Entity
@Table(name = "transaction", schema = DB_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transactions extends AuditEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "wallet_id")
    @JsonProperty("wallet_id")
    private Wallet wallet;

    @Column(name = "bank_account_id")
    @JsonProperty("bank_account_id")
    private BankAccount bankAccount;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Users user;

}
