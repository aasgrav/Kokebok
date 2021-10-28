package com.example.kokebok;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {

    @Autowired
    private OppskriftRepository oppskriftRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void sjekkeAtFørsteOppskriftIDatabaseTabellOppskrifterErKålrabistappeMedAllergiLaktose() {
        //Oppskrift oppskrift = oppskriftRepository.findOppskriftByOppskriftstittel("Kålrabistappe");
        Oppskrift oppskrift = oppskriftRepository.findById(1).get();

        Assert.assertEquals("Oppskrift skal være kålrabistappe", "Kålrabistappe", oppskrift.getOppskriftstittel());
        Assert.assertEquals("Kålrabistappe skal ha allergi laktose", "Laktose", oppskrift.getAllergier());
    }

}
