package com.example.kokebok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
@Controller
public class KokebokApplication {

    public static void main(String[] args) {
        SpringApplication.run(KokebokApplication.class, args);
    }


    @GetMapping("/")
    public String forsideGet() {
        return "forside";
    }

    @PostMapping("/")
    public String forsidePost() {
        return "forside";
    }



}
