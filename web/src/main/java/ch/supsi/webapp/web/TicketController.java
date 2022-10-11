package ch.supsi.webapp.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class TicketController {
    private List<Ticket> tickets = new ArrayList<>();

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public ResponseEntity<Ticket> post(@RequestBody Ticket ticket) {
        tickets.add(ticket);
        return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
    }

    @RequestMapping(value="/tickets", method= RequestMethod.GET)
    public List<Ticket> get() {
        return tickets;
    }

    @RequestMapping(value="/tickets/*", method= RequestMethod.GET)
    public ResponseEntity<Ticket> get(int id) {
        Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (ticket != null) {
            return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/tickets/*", method= RequestMethod.PUT)
    public ResponseEntity<Ticket> put(int id, @RequestBody Ticket newTicket) {
        Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (ticket != null) {
            ticket.setTitle(newTicket.getTitle());
            ticket.setDescription(newTicket.getDescription());
            ticket.setAuthor(newTicket.getAuthor());
            return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/tickets/*", method= RequestMethod.DELETE)
    public ResponseEntity<Object> delete(int id) {
        Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (ticket != null) {
            tickets.remove(ticket);
            // Returns a JSON indicating whether the removal was successful and status code = 200
            return new ResponseEntity<Object>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }
}
