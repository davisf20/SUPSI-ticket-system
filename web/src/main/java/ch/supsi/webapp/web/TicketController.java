package ch.supsi.webapp.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TicketController {
    private final List<Ticket> tickets = new ArrayList<>();

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public ResponseEntity<Ticket> post(@RequestBody Ticket ticket) {
        tickets.add(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @RequestMapping(value="/tickets", method= RequestMethod.GET)
    public List<Ticket> get() {
        return tickets;
    }

    @RequestMapping(value="/tickets/{id}", method= RequestMethod.GET)
    public ResponseEntity<Ticket> get(@PathVariable int id) {
        Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/tickets/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Ticket> put(@PathVariable int id, @RequestBody Ticket newTicket) {
        Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (ticket != null) {
            ticket.setTitle(newTicket.getTitle());
            ticket.setDescription(newTicket.getDescription());
            ticket.setAuthor(newTicket.getAuthor());
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/tickets/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int id) {
        Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (ticket != null) {
            tickets.remove(ticket);
            return new ResponseEntity<>(Map.of("success", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
