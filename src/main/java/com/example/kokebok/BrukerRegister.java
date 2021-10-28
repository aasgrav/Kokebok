package com.example.kokebok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/bruker")
public class BrukerRegister {

    //Bruke hashmap til liste over alle brukere. String er brukernavn og unik key. Bruker er objektet bruker.

    //Map<String, Bruker> brukere = new HashMap<>();

    @Autowired
    OppskriftRegister oppskriftRegister;

    @Autowired OppskriftRepository oppskriftRepository;

    @Autowired OppskriftService oppskriftService;

    @Autowired
    BrukerRepository brukerRepository;

    @Autowired
    FavorisertOppskrift_Repository favorisertOppskriftRepository;

    //Sjekker om brukernavn allerede ligger i lista ved opprettelse av ny bruker
/*    private boolean eksistererBrukernavn(String brukernavn) {
        if (brukerRepository.findById(brukernavn).isPresent()) return true;
        else return false;
    }*/

    //Sender oss til registrering av ny bruker siden
    @GetMapping("/registrer")
    public String registrerBruker() {
        return "registrerBruker";
    }

    //Registrerer ny bruker.
    @PostMapping("/registrer")
    public String registrerBruker( HttpSession session, @Valid Bruker bruker, BindingResult bindingResult, @RequestParam String gjentaPassord) { //Her fungerte ikke PathVariable

        if (!BrukerService.sjekkBrukerData(bruker.getBrukernavn(), bruker.getPassord(), gjentaPassord) || bindingResult.hasErrors()) {
            return "registrerBruker";
        } else {
            session.setAttribute("innloggetBruker", brukerRepository.save(bruker));
            return "redirect:/forside";
        }
    }

    @GetMapping("/loggInn")
    public String loggInn() {
        return "loggInn";
    }

    @PostMapping("/loggInn")
    public String loggInn(@RequestParam String brukernavn, @RequestParam String passord, HttpSession session) {

        Optional<Bruker> bruker2= brukerRepository.findById(brukernavn);

        if (bruker2.isPresent() && bruker2.get().passord.equals(passord)) {
            session.setAttribute("innloggetBruker", bruker2.get());
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
        Oppskrift oppskrift = oppskriftRepository.findOppskriftByOppskriftstittel(oppskriftTittel);

        favorisertOppskriftRepository.save(new FavorisertOppskrift(bruker,oppskrift));

        return "redirect:/oppskrift?page="+page+"&oppskriftsnavn="+oppskriftTittel;
    }

    //Side med markerte favorittoppskrifter
/*    @GetMapping("/mineOppskrifter")
    public String mineOppskrifterGet (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page) {
        int pageSize = 10;
        Bruker bruker = (Bruker)session.getAttribute("innloggetBruker");
        session.setAttribute("oppskrifterOnPageMine", oppskriftService.getPage(Integer.parseInt(page), pageSize,bruker.getFavorittOppskrifter()));
        session.setAttribute("currentPageMine", Integer.parseInt(page));
        session.setAttribute("totalNumberOfPagesMine", oppskriftService.numberOfPages(pageSize, bruker.getFavorittOppskrifter()));
        return "mineOppskrifter";
    }*/


}


