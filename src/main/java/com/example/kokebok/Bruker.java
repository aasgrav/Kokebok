package com.example.kokebok;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


public class Bruker {

    String brukernavn;  //Blir key i Hashmap.
    String passord;
    // String fornavn
    // String etternavn
    List<Oppskrift> favorittOppskrifter;
    // Siste parametere legges inn hvis tid


    public Bruker(String brukernavn, String passord) {
        this.brukernavn = brukernavn;
        this.passord = passord;
        this.favorittOppskrifter = new ArrayList<>();
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public List<Oppskrift> getFavorittOppskrifter() {
        return favorittOppskrifter;
    }
}
