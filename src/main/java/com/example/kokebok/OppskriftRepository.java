package com.example.kokebok;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OppskriftRepository extends CrudRepository<Oppskrift, Integer> {

    public List<Oppskrift> findAllByAllergierIsNot(String allergi);

    public Oppskrift findOppskriftByOppskriftstittel(String oppskriftstittel);

}
