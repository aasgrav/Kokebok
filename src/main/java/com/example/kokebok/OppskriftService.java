package com.example.kokebok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OppskriftService {

    @Autowired OppskriftRepository oppskriftRepository;

    //Hente ut en side med en viss mengde oppskrifter (f.eks. side 2 med oppskrift 10-19)
    public List<Oppskrift> getPage(int page, int pageSize, List<Oppskrift> oppskriftregister) {
        int from = Math.max(0,(page-1)*pageSize);
        int to = Math.min(oppskriftregister.size(),(page)*pageSize);

        return oppskriftregister.subList(from, to);
    }

    //Kalkulere antall sider som trengs for å vise alle oppskrifter (f.eks. 50 oppskrifter trenger 5 sider med 10 oppskrifter på hver)
    public int numberOfPages(int pageSize, List<Oppskrift> oppskriftregister) {
        return (int)Math.ceil((double) oppskriftregister.size() / pageSize);
    }

    public List<Oppskrift> findPageByAllergierIsNot(String allergi, int pageNumber, int pageSize) {
        List<Oppskrift> oppskriftliste = oppskriftRepository.findAllByAllergierIsNot(allergi);
        return getPage(pageNumber, pageSize, oppskriftliste);
    }


}
