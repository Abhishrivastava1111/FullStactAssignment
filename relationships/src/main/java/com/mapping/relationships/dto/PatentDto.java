package com.mapping.relationships.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatentDto {
   
    private String userType;

    private String name;

    private String email;

    private String password;    
}
