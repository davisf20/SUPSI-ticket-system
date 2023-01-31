package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.Attachment;
import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.model.User;
import ch.supsi.webapp.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
    @Autowired
    private AttachmentService attachmentService;

    // return the list of tickets
    @GetMapping("/")
    public String getIndex(Model model, HttpSession session) {
        model.addAttribute("tickets", ticketService.getAll());
        model.addAttribute("numberTickets", ticketService.getAll().size());
        model.addAttribute("numberOpenTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("Open")).count());
        model.addAttribute("numberInProgressTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("In progress")).count());
        model.addAttribute("numberDoneTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("Done")).count());
        model.addAttribute("numberClosedTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("Closed")).count());

        SecurityContextImpl sc = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) sc.getAuthentication().getPrincipal();
        model.addAttribute("authUser", userService.getByUsername(user.getUsername()));

        return "index";
    }

    // return the ticket with the given id
    @GetMapping("/ticket/{id}")
    public String getTicket(@PathVariable int id, Model model) {
        model.addAttribute("ticket", ticketService.get(id));
        model.addAttribute("users", userService.getAll());

        return "ticketDetails";
    }

    // return the form to create a new ticket
    @GetMapping("/ticket/new")
    public String getForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("typeList", typeService.getAll());
        model.addAttribute("users", userService.getAll());

        return "createTicketForm";
    }

    // create a new ticket
    @PostMapping("/ticket/new")
    public String newTicket(Ticket ticket, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        SecurityContextImpl sc = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) sc.getAuthentication().getPrincipal();
        ticket.setUser(userService.getByUsername(user.getUsername()));

        if (!file.isEmpty()) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setFile(file.getBytes());
            ticket.setAttachment(attachment);
            attachmentService.save(attachment);
        }

        ticketService.create(ticket);

        return "redirect:/";
    }

    @GetMapping("/ticket/{id}/attachment")
    @ResponseBody
    public ResponseEntity<Resource> getAttachment(@PathVariable int id) {
        Ticket ticket = ticketService.get(id);
        Attachment attachment = ticket.getAttachment();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"")
                .body(new ByteArrayResource(attachment.getFile()));
    }

    // return the form to edit the ticket with the given id
    @GetMapping("/ticket/{id}/edit")
    public String getEditForm(@PathVariable int id, Model model) {
        model.addAttribute("ticket", ticketService.get(id));
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("typeList", typeService.getAll());
        model.addAttribute("statusList", statusService.getAll());
        model.addAttribute("users", userService.getAll());

        return "editTicketForm";
    }

    // edit the ticket with the given id
    @PostMapping("/ticket/{id}/edit")
    public String editTicket(@PathVariable int id, Ticket ticket, @RequestParam("file") MultipartFile file) throws IOException {
        Ticket t = ticketService.get(id);
        t.setTitle(ticket.getTitle());
        t.setType(ticket.getType());
        t.setStatus(ticket.getStatus());
        t.setDescription(ticket.getDescription());
        t.setAssignedTo(ticket.getAssignedTo());
        t.setEstimatedTime(ticket.getEstimatedTime());
        if (!file.isEmpty()) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setFile(file.getBytes());
            ticket.setAttachment(attachment);

            Attachment a = ticket.getAttachment();
            if (attachmentService.exists(a.getId())) {
                attachmentService.delete(a.getId());
            }
            attachmentService.save(attachment);
        }
        t.setAttachment(ticket.getAttachment());
        ticketService.update(t);

        return "redirect:/ticket/" + id;
    }

    // delete the ticket with the given id
    @GetMapping("/ticket/{id}/delete")
    public String deleteTicket(@PathVariable int id) {
        ticketService.delete(id);

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {

        if (userService.getByUsername(user.getUsername()) != null) {
            return "redirect:/register";
        }

        userService.save(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());

        return "login";
    }

    @GetMapping("/ticket/search")
    public String search(@RequestParam String q, Model model) {
        model.addAttribute("tickets", ticketService.search(q));

        return "index :: searchResult";
    }

    @GetMapping("/ticket/{id}/timeSpent")
    public String getTicketTimeSpent(@PathVariable int id, Model model) {
        model.addAttribute("ticket", ticketService.get(id));

        return "timeSpent";
    }

    @PostMapping("/ticket/{id}/timeSpent")
    public String setTicketTimeSpent(@PathVariable int id, Ticket ticket) {
        Ticket t = ticketService.get(id);
        t.setTimeSpent(ticket.getTimeSpent());
        ticketService.update(t);

        return "redirect:/ticket/" + id;
    }

    @GetMapping("/board")
    public String getBoard(Model model, HttpSession session) {
        model.addAttribute("openedTickets", ticketService.getByStatus(statusService.get(1)));
        model.addAttribute("inProgressTickets", ticketService.getByStatus(statusService.get(2)));
        model.addAttribute("doneTickets", ticketService.getByStatus(statusService.get(3)));
        model.addAttribute("closedTickets", ticketService.getByStatus(statusService.get(4)));
        model.addAttribute("notClosedTickets", ticketService.getNotClosed());

        model.addAttribute("ticketStatus", new Ticket());

        model.addAttribute("numberTickets", ticketService.getAll().size());
        model.addAttribute("numberOpenTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("Open")).count());
        model.addAttribute("numberInProgressTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("In progress")).count());
        model.addAttribute("numberDoneTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("Done")).count());
        model.addAttribute("numberClosedTickets", ticketService.getAll().stream().filter(ticket -> ticket.getStatus().getName().equals("Closed")).count());

        SecurityContextImpl sc = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) sc.getAuthentication().getPrincipal();
        model.addAttribute("authUser", userService.getByUsername(user.getUsername()));

        return "board";
    }

    @GetMapping("/board/{id}/status")
    public String ticketStatus(@PathVariable int id, Model model) {
        model.addAttribute("ticketStatus", ticketService.get(id));

        return "board :: statusResult";
    }

    @GetMapping("/watches")
    public String getWatches(Model model, HttpSession session) {
        SecurityContextImpl sc = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) sc.getAuthentication().getPrincipal();

        model.addAttribute("authUser", userService.getByUsername(user.getUsername()));
        model.addAttribute("watchesTicket", userService.getByUsername(user.getUsername()).getWatchedTickets());

        return "watches";
    }
}
