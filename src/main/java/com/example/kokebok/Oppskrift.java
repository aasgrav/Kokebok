package com.example.kokebok;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oppskrifter")
public class Oppskrift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String oppskriftstittel;
    private String ingredienser;
    private String allergier;
    private Kategori kategori;
    private String oppskriftstekst;

    public Oppskrift() {

    }


    //Gettere og settere for alle variabler
    public String getOppskriftstittel() {
        return oppskriftstittel;
    }

    public void setOppskriftstittel(String oppskriftstittel) {
        this.oppskriftstittel = oppskriftstittel;
    }

    public String getIngredienser() {
        return ingredienser;
    }

    public void setIngredienser(String ingredienser) {
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
