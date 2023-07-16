package com.lofominhili.cinemahd.dto;

import com.lofominhili.cinemahd.models.user.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupRequest {

    private String email;
    private String username;
    private String password;
    private LocalDate date_of_birth;
    private LocalDate sub_deadline;

    public User fromWithoutRoles() {
        return new User(email, username, password, date_of_birth, sub_deadline);
    }
}
