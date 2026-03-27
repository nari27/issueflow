package com.issueflow.user.dto;

import lombok.Getter;

@Getter
public class SignupRequest {

    private String email;
    private String password;
    private String name;
}