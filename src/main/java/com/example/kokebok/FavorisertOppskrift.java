package com.example.kokebok;

import javax.persistence.*;

@Entity
@Table(name = "favoriserte_oppskrifter")
public class FavorisertOppskrift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "brukernavn")
    Bruker bruker;

    @ManyToOne
    @JoinColumn(name ="oppskriftid")
    Oppskrift oppskrift;


    public FavorisertOppskrift() {
    }

    public FavorisertOppskrift(Bruker bruker, Oppskrift oppskrift) {

        this.bruker = bruker;
        this.oppskrift = oppskrift;
    }

    public Bruker getBruker() {
        return bruker;
    }

    public void setBruker(Bruker bruker) {
        this.bruker = bruker;
    }

    public Oppskrift getOppskrift() {
        return oppskrift;
    }

    public void setOppskrift(Oppskrift oppskrift) {
        this.oppskrift = oppskrift;
    }
}
