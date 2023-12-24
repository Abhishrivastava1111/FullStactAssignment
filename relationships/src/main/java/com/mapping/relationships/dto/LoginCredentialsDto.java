package com.mapping.relationships.dto;


import java.util.Set;


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

    private Set<String> role;

    private String email;
    private String authToken;

    private String message;

}
