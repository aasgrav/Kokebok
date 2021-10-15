package com.example.kokebok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@SpringBootApplication
@Controller
public class KokebokApplication {

    public static void main(String[] args) {
        SpringApplication.run(KokebokApplication.class, args);
    }

    @GetMapping("/forside")
    public String forsideGet(HttpSession session) {
        if (session.isNew()) {
            session.setAttribute("allergi", "   ");
        }
        return "forside";
    }

    @PostMapping("/forside")
    public String forsidePost() {
        return "forside";
    }




}
