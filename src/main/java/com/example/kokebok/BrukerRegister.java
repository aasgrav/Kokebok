package com.example.kokebok;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bruker")
public class BrukerRegister {

    //Bruke hashmap til liste over alle brukere. String er brukernavn og unik key. Bruker er objektet bruker.
    Map<String, Bruker> brukere;

//    BrukerService brukerService;

    public BrukerRegister(/*BrukerService brukerService*/) {
//        this.brukerService = brukerService;
        this.brukere = new HashMap<>();
    }

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
    public String registrerBruker(@RequestParam String brukernavn, @RequestParam String passord, @RequestParam String gjentaPassord) { //Her fungerte ikke PathVariable
/* if (brukerService.save(new User()) {
        return "redirect:/forside"}
    else {
        return "registrerBruker";
    }
 */
        System.out.println("brukernavn:" + brukernavn + " passord: " + passord + " gjentaPasord: " + gjentaPassord);

        if (eksistererBrukernavn(brukernavn) || !(passord.equals(gjentaPassord))) {
            System.out.println("Godkjenner ikke brukerinformasjon");
//            return "redirect:/bruker/registrer";
            return "registrerBruker";
        } else {
            System.out.println("Brukerinformasjon godkjent");
            brukere.put(brukernavn, new Bruker(brukernavn, passord));
            return "redirect:/forside";
        }
    }

}
