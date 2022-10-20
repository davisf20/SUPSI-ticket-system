package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "/tickets")
    public ResponseEntity<Ticket> post(@RequestBody Ticket ticket) {
        ticketService.save(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @GetMapping(value="/tickets")
    public List<Ticket> get() {
        return ticketService.getAll();
    }

    @GetMapping(value="/tickets/{id}")
    public ResponseEntity<Ticket> get(@PathVariable int id) {
        if (ticketService.exists(id)) {
            return new ResponseEntity<>(ticketService.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/tickets/{id}")
    public ResponseEntity<Ticket> put(@PathVariable int id, @RequestBody Ticket newTicket) {
        if (ticketService.exists(id)) {
            Ticket ticket = ticketService.get(id);
            ticket.setTitle(newTicket.getTitle());
            ticket.setDescription(newTicket.getDescription());
            ticket.setUser(newTicket.getUser());
            ticket.setStatus(newTicket.getStatus());
            ticketService.save(ticket);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/tickets/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int id) {
        if (ticketService.exists(id)) {
            ticketService.delete(id);
            return new ResponseEntity<>(Map.of("success", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
