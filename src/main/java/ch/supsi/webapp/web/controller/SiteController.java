package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.service.StatusService;
import ch.supsi.webapp.web.service.TicketService;
import ch.supsi.webapp.web.service.TypeService;
import ch.supsi.webapp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SiteController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private StatusService statusService;

    // return the list of tickets
    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("tickets", ticketService.getAll());
        model.addAttribute("numberTickets", ticketService.getAll().size());
        model.addAttribute("numberOpenTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("Open")).count());
        model.addAttribute("numberInProgressTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("In progress")).count());
        model.addAttribute("numberDoneTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("Done")).count());
        model.addAttribute("numberClosedTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("Closed")).count());
        return "index";
    }

    // return the ticket with the given id
    @GetMapping("/ticket/{id}")
    public String getTicket(@PathVariable int id, Model model) {
        model.addAttribute("ticket", ticketService.get(id));
        return "pages/ticketDetails";
    }

    // return the form to create a new ticket
    @GetMapping("/ticket/new")
    public String getForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("typeList", typeService.getAll());
        return "pages/createTicketForm";
    }

    // create a new ticket
    @PostMapping("/ticket/new")
    public String newTicket(Ticket ticket, Model model) {
        ticketService.create(ticket);
        model.addAttribute("ticket", ticketService.get(ticket.getId()));
        return "redirect:/";
    }

    // return the form to edit the ticket with the given id
    @GetMapping("/ticket/{id}/edit")
    public String getEditForm(@PathVariable int id, Model model) {
        model.addAttribute("ticket", ticketService.get(id));
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("typeList", typeService.getAll());
        model.addAttribute("statusList", statusService.getAll());
        return "pages/editTicketForm";
    }

    // edit the ticket with the given id
    @PostMapping("/ticket/{id}/edit")
    public String editTicket(@PathVariable int id, Ticket ticket, Model model) {
        Ticket t = ticketService.get(id);
        t.setTitle(ticket.getTitle());
        t.setUser(ticket.getUser());
        t.setType(ticket.getType());
        t.setStatus(ticket.getStatus());
        t.setDescription(ticket.getDescription());
        ticketService.update(t);
        model.addAttribute("ticket", ticketService.get(id));
        return "redirect:/";
    }

    // delete the ticket with the given id
    @GetMapping("/ticket/{id}/delete")
    public String deleteTicket(@PathVariable int id) {
        ticketService.delete(id);
        return "redirect:/";
    }
}
