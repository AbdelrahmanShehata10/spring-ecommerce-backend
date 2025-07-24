package com.example.E_COMMERCE_PLATFORM.Controller;

import com.example.E_COMMERCE_PLATFORM.Entites.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class MessageController {

    @GetMapping
    public Message sayhello(){

        return new Message ("abdo");
    }
}
