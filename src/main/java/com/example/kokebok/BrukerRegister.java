package com.example.kokebok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bruker")
public class BrukerRegister {

    //Bruke hashmap til liste over alle brukere. String er brukernavn og unik key. Bruker er objektet bruker.
    Map<String, Bruker> brukere = new HashMap<>();
    @Autowired OppskriftRegister oppskriftRegister;




    //Sjekker om brukernavn allerede ligger i lista ved opprettelse av ny bruker
    private boolean eksistererBrukernavn(String brukernavn) {
        if (brukere.containsKey(brukernavn)) return true;
        else return false;
    }

    //Sender oss til registrering av ny bruker siden
    @GetMapping("/registrer")
    public String registrerBruker() {
        return "registrerBruker";
    }

    //Registrerer ny bruker.
    @PostMapping("/registrer")
    public String registrerBruker(HttpSession session, @RequestParam String brukernavn, @RequestParam String passord, @RequestParam String gjentaPassord) { //Her fungerte ikke PathVariable

        if (!BrukerService.sjekkBrukerData(brukernavn, passord, gjentaPassord)) {
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

    //Sender oss til til registrer ny bruker siden
    @PostMapping("/gåTilRegistrering")
    public String tilReg() {
        return "redirect:/bruker/registrer";
    }


    //Logger ut bruker
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
        //Burde sjekke om oppskriften allerede er liket
        bruker.getFavorittOppskrifter().add(oppskrift);

        return "redirect:/oppskrift?page="+page+"&oppskriftsnavn="+oppskriftTittel;
    }


    //Side med markerte favorittoppskrifter
    @GetMapping("/mineOppskrifter")
    public String mineOppskrifterGet (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page) {
        int pageSize = 10;
        Bruker bruker = (Bruker)session.getAttribute("innloggetBruker");
        session.setAttribute("oppskrifterOnPageMine", oppskriftRegister.getPage(Integer.parseInt(page), pageSize,bruker.getFavorittOppskrifter()));
        session.setAttribute("currentPageMine", Integer.parseInt(page));
        session.setAttribute("totalNumberOfPagesMine", oppskriftRegister.numberOfPages(pageSize, bruker.getFavorittOppskrifter()));
        return "mineOppskrifter";
    }




}


