package com.digital.wallet.digital_wallet.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditEntity {

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;

}
