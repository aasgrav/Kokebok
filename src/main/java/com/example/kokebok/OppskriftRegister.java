package com.example.kokebok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OppskriftRegister {

    private List<Oppskrift> oppskriftListe = new ArrayList<>();

    public OppskriftRegister() {
        for (int i = 1; i <= 50; i++) {
            oppskriftListe.add(new Oppskrift("Oppskrift" + i));
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
