package com.example.kokebok;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bruker")

public class Bruker {

    //Har skal vi legge inn nødvendige parametere
    String brukernavn;  //Blir key i Hashmap.
    String passord;

    @GetMapping("/registrer")
    public String registrerBruker(){

        return "registrerBruker";
    }

    @PostMapping("/registrer")
    public String registrerBruker(@PathVariable (required = false) String brukernavn, @PathVariable (required = false) String passord, @PathVariable (required = false) String gjentaPassord){

        return "redirect:/forside";
    }

}
