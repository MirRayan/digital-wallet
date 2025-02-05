package com.digital.wallet.digital_wallet.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.digital.wallet.digital_wallet.utls.StaticTextConfig.DB_SCHEMA;

@Entity
@Table(name = "users", schema = DB_SCHEMA)
@Getter
@Setter
public class Users extends AuditEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "email")
    @JsonProperty("email")
    @NotBlank(message = "Email is required.")
    @Size(max = 100, message = "Field length should not greater than 100")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$", message = "Email format is not valid")
    private String email;

    @Column(name = "password")
    @JsonProperty("password")
    @NotBlank(message = "Password is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!])(?=\\S+$).{8,}$",
            message = "Choose a password of at least seven characters, one special character, one uppercase letter and one number")
    @Size(max = 20, message = "Field length should not greater than 20")
    private String password;

    @Column(name = "phoneNumber")
    @JsonProperty("phoneNumber")
    @NotBlank(message = "Contact Phone is required.")
    @Size(max = 13, message = "Field length should not greater than 13")
    private String phoneNumber;

    @Column(name = "firstName")
    @JsonProperty("firstName")
    //@NotBlank(message = "First Name is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String firstName;

    @Column(name = "lastName")
    @JsonProperty("lastName")
    //@NotBlank(message = "Last Name is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String lastName;

    @Column(name = "otp")
    private String otp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    @JsonProperty("wallet_id")
    private Wallet wallet;

    @Column(name = "is_enabled")
    private Boolean enabled = false;

    @Column(name = "is_not_expired")
    private Boolean accountNonExpired = true;

    @Column(name = "is_not_locked")
    private Boolean accountNonLocked = true;

    @Column(name = "is_cred_not_expired")
    private Boolean credentialsNonExpired = true;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_type_id", referencedColumnName = "id")
    private UserType userType;


}
