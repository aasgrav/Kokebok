package com.example.kokebok;

import java.util.List;

public class Oppskrift {

    private String oppskriftstittel;
    private List<String> ingredienser;
    private List<String> allergier;
    private String kategori;
    private String oppskriftstekst;


    //Constructors (override) - minimumsinfo er oppskriftstittel
    public Oppskrift(String oppskriftstittel, List<String> ingredienser, List<String> allergier, String kategori, String oppskriftstekst) {
        this.oppskriftstittel = oppskriftstittel;
        this.ingredienser = ingredienser;
        this.allergier = allergier;
        this.kategori = kategori;
        this.oppskriftstekst = oppskriftstekst;
    }

    public Oppskrift(String oppskriftstittel, List<String> ingredienser, List<String> allergier, String oppskriftstekst) {
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

    public List<String> getAllergier() {
        return allergier;
    }

    public void setAllergier(List<String> allergier) {
        this.allergier = allergier;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getOppskriftstekst() {
        return oppskriftstekst;
    }

    public void setOppskriftstekst(String oppskriftstekst) {
        this.oppskriftstekst = oppskriftstekst;
    }

}
