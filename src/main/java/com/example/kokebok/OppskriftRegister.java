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

    @Autowired OppskriftService oppskriftService;


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
        int pageSize = 2;

        String allergi = (String) session.getAttribute("allergi");

        List<Oppskrift> oppskriftregister = oppskriftService.findPageByAllergierIsNot(allergi, Integer.parseInt(page), pageSize);

        session.setAttribute("oppskrifterOnPage", oppskriftregister);
        session.setAttribute("currentPage", Integer.parseInt(page));
        session.setAttribute("totalNumberOfPages", oppskriftService.numberOfPages(pageSize, oppskriftregister));
        return "oppskrifter";
    }

    @PostMapping("/oppskrifter")
    public String oppskrifterPost (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = false, defaultValue = "", name = "allergi") String allergi) {
        int pageSize = 2;

        List<Oppskrift> oppskriftregister = oppskriftService.findPageByAllergierIsNot(allergi, Integer.parseInt(page), pageSize);
        page = "1";

        session.setAttribute("oppskrifterOnPage", oppskriftService.getPage(Integer.parseInt(page), pageSize, oppskriftregister));
        session.setAttribute("currentPage", Integer.parseInt(page));
        session.setAttribute("totalNumberOfPages", oppskriftService.numberOfPages(pageSize, oppskriftregister));
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
