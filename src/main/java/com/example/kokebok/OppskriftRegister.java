package com.example.kokebok;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OppskriftRegister {

    private List<Oppskrift> oppskriftListe = new ArrayList<>();

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

    public List<Oppskrift> getPage(int page, int pageSize) {
        int from = Math.max(0,(page-1)*pageSize);
        int to = Math.min(oppskriftListe.size(),(page)*pageSize);

        return oppskriftListe.subList(from, to);
    }

    public int numberOfPages(int pageSize) {
        return (int)Math.ceil((double) oppskriftListe.size() / pageSize);
    }

    public Oppskrift getOppskriftByName(String navn) {
        for (Oppskrift oppskrift : oppskriftListe) {
            if (oppskrift.getOppskriftstittel().equals(navn)) {
                return oppskrift;
            }
        }
        return null;
    }


}
