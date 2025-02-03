package com.digital.wallet.digital_wallet.entity.Users;

import com.digital.wallet.digital_wallet.entity.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static com.digital.wallet.digital_wallet.utls.StaticTextConfig.DB_SCHEMA;

@Entity
@Table(name = "user_info", schema = DB_SCHEMA)
public class UsersInfo extends AuditEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone_no")
    private String contactPhone;
}
