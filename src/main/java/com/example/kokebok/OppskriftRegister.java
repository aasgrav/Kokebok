package com.example.kokebok;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class OppskriftRegister {

    private List<Oppskrift> oppskriftListe = new ArrayList<>();

    public void leggTilOppskrift(Oppskrift oppskrift) {
        oppskriftListe.add(oppskrift);
    }

    public List<Oppskrift> getOppskriftListe() {
        return oppskriftListe;
    }

}
