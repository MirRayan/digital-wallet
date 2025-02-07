package com.digital.wallet.digital_wallet.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.digital.wallet.digital_wallet.utls.StaticTextConfig.DB_SCHEMA;

@Entity
@Table(name = "users", schema = DB_SCHEMA)
@Getter
@Setter
public class Users extends AuditEntity implements UserDetails {

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

    @ManyToOne()
    @JoinColumn(name = "user_type_id", referencedColumnName = "id")
    private UserType userType;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
