package com.example.kokebok;

import org.springframework.web.bind.annotation.RequestParam;

public class brukerService {

    public static boolean sjekkBrukerData(String brukernavn, String passord, String gjentaPassord){ //usikker på om vi skal beholde denne som static
        boolean reglerOverholdt =true;

        if(!passord.equals(gjentaPassord)){
            reglerOverholdt = false;
        }
        if(brukernavn.length()>15  && brukernavn.length()<6){
            reglerOverholdt = false;
        }
        if(!passord.matches(".*[a-zA-Z].*")){
            reglerOverholdt = false;
        }
        if(!passord.matches(".*\\d.*")){
            reglerOverholdt = false;
        }
        return reglerOverholdt;
    }
}
