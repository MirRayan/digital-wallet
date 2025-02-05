package com.digital.wallet.digital_wallet.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static com.digital.wallet.digital_wallet.utls.StaticTextConfig.DB_SCHEMA;

@Entity
@Table(name = "users_type", schema = DB_SCHEMA)
@Getter
@Setter
public class UserType extends AuditEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "transaction_limit")
    @JsonProperty("transactionLimit")
    private int transactionLimit;

    @Column(name = "transaction_charge")
    @JsonProperty("transactionCharge")
    private int transactionCharge;

}
