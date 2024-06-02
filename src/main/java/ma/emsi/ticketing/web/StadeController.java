package ma.emsi.ticketing.web;

import ma.emsi.ticketing.entities.Stade;
import ma.emsi.ticketing.repositories.StadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StadeController {
    @Autowired
    private StadeRepository stadeRepository;

    @GetMapping("/stadeHome")
    public String Home(){
        return "layout";
    }

    @GetMapping(path = "stades")
    public String AfficherStades(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "3") int size,
                                 @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Stade> pageStades = stadeRepository.findByNameContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listeStades", pageStades.getContent());
        model.addAttribute("pages", new int[pageStades.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        return "stades";
    }
    @GetMapping("/delete")
    public String Delete(int id, int page, String keyword){
        stadeRepository.deleteById(id);
        return "redirect:/stades?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formStade")
    public String formStade(Model model ){
        model.addAttribute("stade",new Stade());
        return "formStade";
    }
    @PostMapping(path = "/save")
    public String save( Stade stade, Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "3") int size,
                       @RequestParam(defaultValue = "") String keyword){
        stadeRepository.save(stade);
        return "redirect:/stades?page="+page+"&size="+size+"&keyword="+keyword;
    }
    @GetMapping("/editStade")
    public String editStade(int id, int page, String keyword,Model model){
        Stade stade =stadeRepository.findById(id).orElse(null);
        if(stade == null) throw new RuntimeException("Stade Inexistant");
        model.addAttribute("stade",stade);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editStade";
    }
}
