package com.example.kokebok;

import javax.persistence.*;

//@Entity
@Table(name="favoriserte_oppskrifter")
public class favorisertOppskrift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
            @JoinColumn
    String brukernavn;
    @ManyToOne
    @JoinColumn
    int oppskriftid;


    public favorisertOppskrift(){
    }

    private favorisertOppskrift (String brukernavn, int oppskriftid){
        this.brukernavn = brukernavn;
        this.oppskriftid = oppskriftid;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setOppskriftid(int oppskriftid) {
        this.oppskriftid = oppskriftid;
    }

    public int getOppskriftid() {
        return oppskriftid;
    }
}
