package ma.emsi.ticketing.web;

import ma.emsi.ticketing.entities.Ticket;
import ma.emsi.ticketing.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/tickets")
    public String showTickets(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "3") int size,
                              @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Ticket> pageTickets = ticketRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("listeTickets", pageTickets.getContent());
        model.addAttribute("pages", new int[pageTickets.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        return "tickets"; // Assuming you have a "tickets.html" template
    }

    @GetMapping("/deleteTicket")
    public String deleteTicket(@RequestParam int id,
                               @RequestParam int page,
                               @RequestParam String keyword) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/formTicket")
    public String showTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "formTicket"; // Assuming you have a "formTicket.html" template for ticket creation/editing
    }

    @PostMapping("/saveTicket")
    public String saveTicket(@ModelAttribute Ticket ticket,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "3") int size,
                             @RequestParam(defaultValue = "") String keyword) {
        ticketRepository.save(ticket);
        return "redirect:/tickets?page=" + page + "&size=" + size + "&keyword=" + keyword;
    }

    @GetMapping("/editTicket")
    public String showEditTicketForm(@RequestParam int id,
                                     @RequestParam int page,
                                     @RequestParam String keyword,
                                     Model model) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
        model.addAttribute("ticket", ticket);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editTicket"; // Assuming you have an "editTicket.html" template
    }
}
