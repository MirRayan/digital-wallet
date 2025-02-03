package com.digital.wallet.digital_wallet.entity.Users;

import com.digital.wallet.digital_wallet.entity.AuditEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static com.digital.wallet.digital_wallet.utls.StaticTextConfig.DB_SCHEMA;

@Entity
@Table(name = "currency", schema = DB_SCHEMA)
@Getter
@Setter
public class Currency extends AuditEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "abbreviation")
    @JsonProperty("abbreviation")
    private String abbreviation;

}
