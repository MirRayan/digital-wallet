package com.digital.wallet.digital_wallet.dtos.Users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String contactEmail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
