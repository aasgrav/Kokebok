package com.example.kokebok;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavorisertOppskrift_Repository extends CrudRepository<FavorisertOppskrift, Integer> {

    List<FavorisertOppskrift> findByBruker (Bruker bruker);

}
