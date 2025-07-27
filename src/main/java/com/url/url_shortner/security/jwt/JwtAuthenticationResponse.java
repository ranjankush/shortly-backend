package com.url.url_shortner.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String message;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
        this.message="User Login Successfully!";
    }

    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }
}
