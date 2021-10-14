package com.example.kokebok;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bruker")
public class BrukerRegister {

    //Bruke hashmap til liste over alle brukere. String er brukernavn og unik key. Bruker er objektet bruker.
    Map<String, Bruker> brukere;

/*    BrukerService brukerService;

    public BrukerRegister(BrukerService brukerService) {
        this.brukerService = brukerService;
        Map<String, Bruker> brukerRegister = new HashMap<>();
    }*/

    private boolean eksistererBrukernavn(String brukernavn) {
        //Sjekk om brukernavn allerede ligger i lista.
        if (brukere.containsKey(brukernavn)) return true;
        else return false;
    }

    @GetMapping("/registrer")
    public String registrerBruker() {
        System.out.println("test");
        return "registrerBruker";
    }

    @PostMapping("/registrer")
    public String registrerBruker(@PathVariable(required = false) String brukernavn, @PathVariable(required = false) String passord, @PathVariable(required = false) String gjentaPassord) {
/* if (brukerService.save(new User()) {
        return "redirect:/forside"}
    else {
        return "registrerBruker";
    }
 */
        if (eksistererBrukernavn(brukernavn) || (passord != gjentaPassord)) {
            return "registrerBruker";
        } else {
            brukere.put(brukernavn, new Bruker(brukernavn, passord));
            return "redirect:/forside";
        }
    }

}
