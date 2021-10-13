package com.example.kokebok;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@Service
@RequestMapping("/bruker")
public class BrukerRegister {

    //Bruke hashmap til liste over alle brukere. String er brukernavn og unik key. Bruker er objektet bruker.
    Map<String, Bruker> brukerRegister = new HashMap<>();


    public boolean eksistererBrukernavn(String brukernavn) {
        //Sjekk om brukernavn allerede ligger i lista.
        if (brukerRegister.containsKey(brukernavn)) return true;
        else return false;
    }

    @GetMapping("/registrer")
    public String registrerBruker(){
        return "registrerBruker";
    }

    @PostMapping("/registrer")
    public String registrerBruker(@PathVariable(required = false) String brukernavn, @PathVariable (required = false) String passord, @PathVariable (required = false) String gjentaPassord){

        if(eksistererBrukernavn(brukernavn) || (passord != gjentaPassord)){
            return "registrerBruker";
        }
        else {
            brukerRegister.put(brukernavn, new Bruker(brukernavn, passord));
            return "redirect:/forside";
        }
    }


}
