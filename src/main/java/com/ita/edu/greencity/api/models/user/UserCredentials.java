package com.ita.edu.greencity.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredentials {
    private String email;
    private String password;
}
