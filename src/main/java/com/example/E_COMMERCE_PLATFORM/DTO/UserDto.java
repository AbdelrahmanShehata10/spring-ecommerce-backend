package com.example.E_COMMERCE_PLATFORM.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserDto {
//    @JsonIgnore
    //@Jsoninclude
    private String username;
    private String age;


}
