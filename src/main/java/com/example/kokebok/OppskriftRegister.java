package com.example.kokebok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OppskriftRegister {

    @Autowired
    private OppskriftRepository oppskriftRepository;

    @Autowired
    private OppskriftService oppskriftService;


    public void leggTilOppskrift(Oppskrift oppskrift) {
        oppskriftRepository.save(oppskrift);
    }

    public List<Oppskrift> getOppskriftListe() {
        List<Oppskrift> oppskriftListe = (List<Oppskrift>) oppskriftRepository.findAll();
        return oppskriftListe;
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
        int pageSize = 10;

        String allergi = (String) session.getAttribute("allergi");

        List<Oppskrift> oppskriftregisterPage = oppskriftService.findPageByAllergierIsNot(allergi, Integer.parseInt(page), pageSize);
        List<Oppskrift> oppskriftregisterAll = oppskriftRepository.findAllByAllergierIsNot(allergi);

        session.setAttribute("oppskrifterOnPage", oppskriftregisterPage);
        session.setAttribute("currentPage", Integer.parseInt(page));
        session.setAttribute("totalNumberOfPages", oppskriftService.numberOfPages(pageSize, oppskriftregisterAll));
        return "oppskrifter";
    }

    @PostMapping("/oppskrifter")
    public String oppskrifterPost (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = false, defaultValue = "", name = "allergi") String allergi) {
        int pageSize = 10;

        List<Oppskrift> oppskriftregisterPage = oppskriftService.findPageByAllergierIsNot(allergi, Integer.parseInt(page), pageSize);
        List<Oppskrift> oppskriftregisterAll = oppskriftRepository.findAllByAllergierIsNot(allergi);
        page = "1";

        session.setAttribute("oppskrifterOnPage", oppskriftregisterPage);
        session.setAttribute("currentPage", Integer.parseInt(page));
        session.setAttribute("totalNumberOfPages", oppskriftService.numberOfPages(pageSize, oppskriftregisterAll));
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
