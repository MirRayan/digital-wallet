package com.digital.wallet.digital_wallet.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.digital.wallet.digital_wallet.utls.StaticTextConfig.DB_SCHEMA;

@Entity
@Table(name = "bank_account", schema = DB_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount extends AuditEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "number")
    @JsonProperty("number")
    private String number;

    @Column(name = "bank_name")
    @JsonProperty("bank_name")
    private String bankName;

    @Column(name = "branch_name")
    @JsonProperty("branch_name")
    private String branchName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty("user")
    private Users user;

}
