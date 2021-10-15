package com.example.kokebok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@SpringBootApplication
@Controller
public class KokebokApplication {

    public static void main(String[] args) {
        SpringApplication.run(KokebokApplication.class, args);
    }

    @Autowired
    public OppskriftRegister oppskriftRegister;

    @GetMapping("/forside")
    public String forsideGet() {
        return "forside";
    }

    @PostMapping("/forside")
    public String forsidePost() {
        return "forside";
    }


    //Hovedside med oversikt over alle oppskrifter
    @GetMapping("/oppskrifter")
    public String oppskrifterGet (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page) {
        int pageSize = 10;
        model.addAttribute("oppskrifterOnPage",  oppskriftRegister.getPage(Integer.parseInt(page), pageSize));
        model.addAttribute("currentPage", Integer.parseInt(page));
        model.addAttribute("totalNumberOfPages", oppskriftRegister.numberOfPages(pageSize));
        return "oppskrifter";
    }


    //Detaljside for enkeltoppskrift
    @GetMapping("/oppskrift")
    public String getOppskrift(Model model, @RequestParam String page, @RequestParam String oppskriftsnavn) {
        model.addAttribute("currentOppskrift", oppskriftRegister.getOppskriftByName(oppskriftsnavn));
        model.addAttribute("currentPage", page);
        return "oppskriftDetaljside";
    }

    //Side med markerte favorittoppskrifter
    @GetMapping("/mineOppskrifter")
    public String mineOppskrifterGet (HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") String page) {
        int pageSize = 10;
        model.addAttribute("oppskrifterOnPage",  oppskriftRegister.getPage(Integer.parseInt(page), pageSize));
        model.addAttribute("currentPage", Integer.parseInt(page));
        model.addAttribute("totalNumberOfPages", oppskriftRegister.numberOfPages(pageSize));
        return "oppskrifter";
    }

}
