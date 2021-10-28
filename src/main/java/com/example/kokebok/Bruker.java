package com.example.kokebok;

import javax.persistence.*;
import javax.validation.constraints.Size;



@Entity
@Table(name="brukere")
public class Bruker {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 6, max = 15)
    String brukernavn;  //Blir key i Hashmap.
    String passord;
    // String fornavn
    // String etternavn
    //List<Oppskrift> favorittOppskrifter;
    // Siste parametere legges inn hvis tid



    public Bruker(){
    }

    public Bruker(String brukernavn, String passord) {
        this.brukernavn = brukernavn;
        this.passord = passord;
        //this.favorittOppskrifter = new ArrayList<>();
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

/*    public List<Oppskrift> getFavorittOppskrifter() {
        return favorittOppskrifter;
    }*/
}
