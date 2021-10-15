package com.example.kokebok;

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

    private List<Oppskrift> oppskriftListe = new ArrayList<>();

    //Oppretting av oppskriftsregister
    public OppskriftRegister() {
//        Faker faker = new Faker();
        List<String> ingredienser = new ArrayList<String>();
        ingredienser.add("Smør");
        ingredienser.add("Egg");
        ingredienser.add("Sukker");
        ingredienser.add("Mel");

        List<String> allergier = new ArrayList<String>();
        allergier.add("Nøtter");
        allergier.add("Gluten");
        allergier.add("Laktose");
        allergier.add("");

        for (int i = 1; i <= 50; i++) {
            oppskriftListe.add(new Oppskrift("Oppskrift " + i, ingredienser, allergier.get(i % 4), "Gjør dette og dette."));
        }
    }

    public void leggTilOppskrift(Oppskrift oppskrift) {
        oppskriftListe.add(oppskrift);
    }

    public List<Oppskrift> getOppskriftListe() {
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
        for (Oppskrift oppskrift : oppskriftListe) {
            if (oppskrift.getOppskriftstittel().equals(navn)) {
                return oppskrift;
            }
        }
        return null;
    }

    //Hovedside med oversikt over alle oppskrifter
    @GetMapping("/oppskrifter")
    public String oppskrifterGet (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page) {
        if (session.isNew()) {
            session.setAttribute("allergi", "   ");
        }
        int pageSize = 10;

        String allergi = (String) session.getAttribute("allergi");

        List<Oppskrift> oppskriftregister = new ArrayList<>();
        if (allergi.equals("   ")) {
            oppskriftregister = getOppskriftListe();
        } else {
            for (Oppskrift oppskrift : getOppskriftListe()) {
                if (!oppskrift.getAllergier().equals(allergi)) {
                    oppskriftregister.add(oppskrift);
                }
            }
        }

        session.setAttribute("oppskrifterOnPage",  getPage(Integer.parseInt(page), pageSize, oppskriftregister));
        session.setAttribute("currentPage", Integer.parseInt(page));
        session.setAttribute("totalNumberOfPages", numberOfPages(pageSize, oppskriftregister));
        return "oppskrifter";
    }

    @PostMapping("/oppskrifter")
    public String oppskrifterPost (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = false, defaultValue = "   ", name = "allergi") String allergi) {
        int pageSize = 10;
        List<Oppskrift> oppskriftregister = new ArrayList<>();
        if (allergi.equals("   ")) {
            oppskriftregister = getOppskriftListe();
        } else {
            for (Oppskrift oppskrift : getOppskriftListe()) {
                if (!oppskrift.getAllergier().equals(allergi)) {
                    oppskriftregister.add(oppskrift);
                }
            }
            page = "1";
        }

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
