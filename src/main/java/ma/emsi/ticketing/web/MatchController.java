package ma.emsi.ticketing.web;

import ma.emsi.ticketing.entities.Match;
import ma.emsi.ticketing.entities.Stade;
import ma.emsi.ticketing.repositories.MatchRepository;
import ma.emsi.ticketing.repositories.StadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MatchController {
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping(path = "matchs")
    public String AfficherMatches(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "3") int size,
                                 @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Match> pageMatches = matchRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listeMatchs", pageMatches.getContent());
        model.addAttribute("pages", new int[pageMatches.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        return "matchs";
    }
    @GetMapping("/deleteMatch")
    public String Delete(int id, int page, String keyword){
        matchRepository.deleteById(id);
        return "redirect:/matchs?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formMatch")
    public String formMatch(Model model ){
        model.addAttribute("match",new Match());
        return "formMatch";
    }
    @PostMapping(path = "/saveMatch")
    public String save( Match match, Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "3") int size,
                        @RequestParam(defaultValue = "") String keyword){
        matchRepository.save(match);
        return "redirect:/matchs?page="+page+"&size="+size+"&keyword="+keyword;
    }
    @GetMapping("/editMatch")
    public String editMatch(int id, int page, String keyword,Model model){
        Match match =matchRepository.findById(id).orElse(null);
        if(match == null) throw new RuntimeException("Match Inexistant");
        model.addAttribute("match",match);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editMatch";
    }

}
