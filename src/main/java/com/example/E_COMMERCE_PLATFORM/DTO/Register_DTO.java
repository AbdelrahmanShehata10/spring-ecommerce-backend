package com.example.E_COMMERCE_PLATFORM.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Register_DTO {

    private String username;
    private String age;
    private String email;
    private String password;

}
