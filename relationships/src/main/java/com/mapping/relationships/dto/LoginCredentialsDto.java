package com.mapping.relationships.dto;


import com.mapping.relationships.Entities.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentialsDto {

    private UserType role;

    private String email;
    private String authToken;

    private String message;

}
