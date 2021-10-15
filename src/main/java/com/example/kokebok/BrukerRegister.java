package com.example.kokebok;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bruker")
public class BrukerRegister {

    //Bruke hashmap til liste over alle brukere. String er brukernavn og unik key. Bruker er objektet bruker.
    Map<String, Bruker> brukere;
    OppskriftRegister oppskriftRegister;

//    BrukerService brukerService;

    public BrukerRegister(OppskriftRegister oppskriftRegister) {
//        this.brukerService = brukerService;
        this.brukere = new HashMap<>();
        this.oppskriftRegister = oppskriftRegister;
    }

    private boolean eksistererBrukernavn(String brukernavn) {
        //Sjekker om brukernavn allerede ligger i lista.
        if (brukere.containsKey(brukernavn)) return true;
        else return false;
    }

    @GetMapping("/registrer")
    public String registrerBruker() {
        System.out.println("test");
        return "registrerBruker";
    }

    @PostMapping("/registrer")
    public String registrerBruker(HttpSession session, @RequestParam String brukernavn, @RequestParam String passord, @RequestParam String gjentaPassord) { //Her fungerte ikke PathVariable
/* if (brukerService.save(new User()) {
        return "redirect:/forside"}
    else {
        return "registrerBruker";
    }
 */
        System.out.println("brukernavn:" + brukernavn + " passord: " + passord + " gjentaPasord: " + gjentaPassord);

        if (!brukerService.sjekkBrukerData(brukernavn, passord, gjentaPassord)) {
            return "registrerBruker";
        } else {
            brukere.put(brukernavn, new Bruker(brukernavn, passord));
            session.setAttribute("innloggetBruker", brukere.get(brukernavn));
            return "redirect:/forside";
        }
    }

    @GetMapping("/loggInn")
    public String loggInn() {
        return "loggInn";
    }

    @PostMapping("/loggInn")
    public String loggInn(@RequestParam String brukernavn, @RequestParam String passord, HttpSession session) {

        if (eksistererBrukernavn(brukernavn) && brukere.get(brukernavn).passord.equals(passord)) {
            session.setAttribute("innloggetBruker", brukere.get(brukernavn));
            return "redirect:/forside";
        } else {
            return "loggInn";
        }
    }

    @PostMapping("/gåTilRegistrering")
    public String tilReg() {
        return "redirect:/bruker/registrer";
    }

    @PostMapping("/loggUt")
    public String loggUt(HttpSession session){
        session.setAttribute("innloggetBruker", null);
        return "redirect:/forside";
    }

    @GetMapping ("/mineLister")
    public String tilMinListe(){
        return "mineOppskrifter";
    }

    @PostMapping("/favoriserOppskrift")
    public String favoriserOppskrift(@RequestParam String oppskriftTittel, @RequestParam String page, HttpSession session){

        Bruker bruker = (Bruker) session.getAttribute("innloggetBruker");
        Oppskrift oppskrift = oppskriftRegister.getOppskriftByName(oppskriftTittel);
        bruker.getFavorittOppskrifter().add(oppskrift);

        return "redirect:/oppskrifter?page=" + page;
    }


}


