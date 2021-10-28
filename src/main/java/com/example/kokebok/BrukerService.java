package com.example.kokebok;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class BrukerService {

    @Autowired Map<String, Bruker> brukere;


    public static boolean sjekkBrukerData(String brukernavn, String passord, String gjentaPassord) { //usikker på om vi skal beholde denne som static
        boolean reglerOverholdt = true;

        if (!passord.equals(gjentaPassord)) {
            reglerOverholdt = false;
        }
        if (brukernavn.length() > 15 || brukernavn.length() < 6) {
            reglerOverholdt = false;
        }
        if (!passord.matches(".*[a-zA-Z].*")) {
            reglerOverholdt = false;
        }
        if (!passord.matches(".*\\d.*")) {
            reglerOverholdt = false;
        }
        /*if (brukere.containsKey(brukernavn)) {
            reglerOverholdt = false;
        }*/
        return reglerOverholdt;
    }
}
