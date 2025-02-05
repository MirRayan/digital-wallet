package com.digital.wallet.digital_wallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AuditEntity {

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;

    @PrePersist
    protected void onCreate() {
        createdOn = ZonedDateTime.now();
        updatedOn = ZonedDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = ZonedDateTime.now();
    }

}
