package com.example.kokebok;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class Oppskrift {

    private String oppskriftstittel;
    private List<String> ingredienser;
    private String allergier;
    private Kategori kategori;
    private String oppskriftstekst;


    //Constructors (override) - minimumsinfo er oppskriftstittel
    public Oppskrift(String oppskriftstittel, List<String> ingredienser, String allergier, Kategori kategori, String oppskriftstekst) {
        this.oppskriftstittel = oppskriftstittel;
        this.ingredienser = ingredienser;
        this.allergier = allergier;
        this.kategori = kategori;
        this.oppskriftstekst = oppskriftstekst;
    }

    public Oppskrift(String oppskriftstittel, List<String> ingredienser, String allergier, String oppskriftstekst) {
        this.oppskriftstittel = oppskriftstittel;
        this.ingredienser = ingredienser;
        this.allergier = allergier;
        this.oppskriftstekst = oppskriftstekst;
    }

    public Oppskrift(String oppskriftstittel, List<String> ingredienser, String oppskriftstekst) {
        this.oppskriftstittel = oppskriftstittel;
        this.ingredienser = ingredienser;
        this.oppskriftstekst = oppskriftstekst;
    }

    public Oppskrift(String oppskriftstittel, List<String> ingredienser) {
        this.oppskriftstittel = oppskriftstittel;
        this.ingredienser = ingredienser;
    }

    public Oppskrift(String oppskriftstittel) {
        this.oppskriftstittel = oppskriftstittel;
    }


    //Gettere og settere for alle variabler
    public String getOppskriftstittel() {
        return oppskriftstittel;
    }

    public void setOppskriftstittel(String oppskriftstittel) {
        this.oppskriftstittel = oppskriftstittel;
    }

    public List<String> getIngredienser() {
        return ingredienser;
    }

    public void setIngredienser(List<String> ingredienser) {
        this.ingredienser = ingredienser;
    }

    public String getAllergier() {
        return allergier;
    }

    public void setAllergier(String allergier) {
        this.allergier = allergier;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getOppskriftstekst() {
        return oppskriftstekst;
    }

    public void setOppskriftstekst(String oppskriftstekst) {
        this.oppskriftstekst = oppskriftstekst;
    }

    public String toString() {
        return oppskriftstittel + ": " + allergier;
    }
}
