package com.example.kokebok;

public class Bruker {

    String brukernavn;  //Blir key i Hashmap.
    String passord;
    // String fornavn
    // String etternavn
    // List <Oppskrift> favorittOppskrifter
    // Siste parametere legges inn hvis tid


    public Bruker(String brukernavn, String passord) {
        this.brukernavn = brukernavn;
        this.passord = passord;
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
}
