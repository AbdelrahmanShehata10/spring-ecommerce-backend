package com.example.E_COMMERCE_PLATFORM.DTO;

import lombok.Data;

@Data
public class change_password_req {

    private String oldpassword;
    private String newpassword;
}
