package com.digital.wallet.digital_wallet.dtos.Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrationRequest {
    /*@JsonProperty("firstName")
    @NotBlank(message = "First Name is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last Name is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String lastName;*/

    @JsonProperty("phoneNumber")
    @NotBlank(message = "Contact Phone is required.")
    @Size(max = 13, message = "Field length should not greater than 13")
    private String phoneNumber;

    @JsonProperty("email")
    @NotBlank(message = "Email is required.")
    @Size(max = 100, message = "Field length should not greater than 100")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$", message = "Email format is not valid")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "Password is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!])(?=\\S+$).{8,}$",
            message = "Choose a password of at least seven characters, one special character, one uppercase letter and one number")
    @Size(max = 20, message = "Field length should not greater than 20")
    private String password;

    /*@JsonProperty("confirmPassword")
    @NotBlank(message = "Confirm Password is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!])(?=\\S+$).{8,}$",
            message = "Choose a password of at least seven characters, one special character, one uppercase letter and one number")
    @Size(max = 20, message = "Field length should not greater than 20")
    private String confirmPassword;*/

    /*public @NotBlank(message = "First Name is required.") @Size(max = 50, message = "Field length should not greater than 50") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First Name is required.") @Size(max = 50, message = "Field length should not greater than 50") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last Name is required.") @Size(max = 50, message = "Field length should not greater than 50") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last Name is required.") @Size(max = 50, message = "Field length should not greater than 50") String lastName) {
        this.lastName = lastName;
    }*/

    public @NotBlank(message = "Contact Phone is required.") @Size(max = 13, message = "Field length should not greater than 13") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Contact Phone is required.") @Size(max = 13, message = "Field length should not greater than 13") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotBlank(message = "Email is required.")
    @Size(max = 100, message = "Field length should not greater than 100")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$", message = "Email format is not valid")
    public String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required.") @Size(max = 100, message = "Field length should not greater than 100") @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$", message = "Email format is not valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!])(?=\\S+$).{8,}$",
            message = "Choose a password of at least seven characters, one special character, one uppercase letter and one number") @Size(max = 20, message = "Field length should not greater than 20") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!])(?=\\S+$).{8,}$",
            message = "Choose a password of at least seven characters, one special character, one uppercase letter and one number") @Size(max = 20, message = "Field length should not greater than 20") String password) {
        this.password = password;
    }

    /*public @NotBlank(message = "Confirm Password is required.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!])(?=\\S+$).{8,}$",
            message = "Choose a password of at least seven characters, one special character, one uppercase letter and one number") @Size(max = 20, message = "Field length should not greater than 20") String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotBlank(message = "Confirm Password is required.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!])(?=\\S+$).{8,}$",
            message = "Choose a password of at least seven characters, one special character, one uppercase letter and one number") @Size(max = 20, message = "Field length should not greater than 20") String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }*/
}
