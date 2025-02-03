package com.digital.wallet.digital_wallet.entity.Users;

import com.digital.wallet.digital_wallet.entity.AuditEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.digital.wallet.digital_wallet.utls.StaticTextConfig.DB_SCHEMA;

@Entity
@Table(name = "wallet", schema = DB_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet extends AuditEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "amount")
    @JsonProperty("amount")
    private int amount;

    @JsonProperty("user_id")
    private String userId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @JsonProperty("currency")
    private Currency currency;

}
