package com.example.kokebok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OppskriftRegister {

    @Autowired
    private OppskriftRepository oppskriftRepository;


    public void leggTilOppskrift(Oppskrift oppskrift) {
        // TODO: lagre oppskrift i databasen (save)
        oppskriftRepository.save(oppskrift);
        //oppskriftListe.add(oppskrift);
    }

    public List<Oppskrift> getOppskriftListe() {
        List<Oppskrift> oppskriftListe = (List<Oppskrift>) oppskriftRepository.findAll();
        return oppskriftListe;
    }

    //Hente ut en side med en viss mengde oppskrifter (f.eks. side 2 med oppskrift 10-19)
    public List<Oppskrift> getPage(int page, int pageSize, List<Oppskrift> oppskriftregister) {
        int from = Math.max(0,(page-1)*pageSize);
        int to = Math.min(oppskriftregister.size(),(page)*pageSize);

        return oppskriftregister.subList(from, to);
    }

    //Kalkulere antall sider som trengs for å vise alle oppskrifter (f.eks. 50 oppskrifter trenger 5 sider med 10 oppskrifter på hver)
    public int numberOfPages(int pageSize, List<Oppskrift> oppskriftregister) {
        return (int)Math.ceil((double) oppskriftregister.size() / pageSize);
    }

    //Hente en spesifikk oppskrift utifra navn
    public Oppskrift getOppskriftByName(String navn) {
        Oppskrift oppskrift = oppskriftRepository.findOppskriftByOppskriftstittel(navn);
        return oppskrift;
    }

    //Hovedside med oversikt over alle oppskrifter
    @GetMapping("/oppskrifter")
    public String oppskrifterGet (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page) {
        if (session.isNew()) {
            session.setAttribute("allergi", "");
        }
        int pageSize = 2;

        String allergi = (String) session.getAttribute("allergi");

        List<Oppskrift> oppskriftregister = oppskriftRepository.findAllByAllergierIsNot(allergi);

        session.setAttribute("oppskrifterOnPage",  getPage(Integer.parseInt(page), pageSize, oppskriftregister));
        session.setAttribute("currentPage", Integer.parseInt(page));
        session.setAttribute("totalNumberOfPages", numberOfPages(pageSize, oppskriftregister));
        return "oppskrifter";
    }

    @PostMapping("/oppskrifter")
    public String oppskrifterPost (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = false, defaultValue = "", name = "allergi") String allergi) {
        int pageSize = 2;

        List<Oppskrift> oppskriftregister = oppskriftRepository.findAllByAllergierIsNot(allergi);
        page = "1";

        session.setAttribute("oppskrifterOnPage",  getPage(Integer.parseInt(page), pageSize, oppskriftregister));
        session.setAttribute("currentPage", Integer.parseInt(page));
        session.setAttribute("totalNumberOfPages", numberOfPages(pageSize, oppskriftregister));
        session.setAttribute("allergi", allergi);

        return "oppskrifter";
    }


    //Detaljside for enkeltoppskrift
    @GetMapping("/oppskrift")
    public String getOppskrift(Model model, @RequestParam String page, @RequestParam String oppskriftsnavn) {
        model.addAttribute("currentOppskrift", getOppskriftByName(oppskriftsnavn));
        model.addAttribute("currentPage", page);
        return "oppskriftDetaljside";
    }

}
