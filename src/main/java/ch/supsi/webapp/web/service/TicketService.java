package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.Status;
import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public void save(Ticket ticket) {
        ticket.setStatus(Status.OPEN);
        ticket.setCreationDate(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    public List<Ticket> getAll() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    public Ticket get(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public boolean exists(int id) {
        return ticketRepository.existsById(id);
    }

    public void delete(int id) {
        ticketRepository.deleteById(id);
    }
}
