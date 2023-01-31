package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.Status;
import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.repository.StatusRepository;
import ch.supsi.webapp.web.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private StatusRepository statusRepository;

    private void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void create(Ticket ticket) {
        if (!exists(ticket.getId()) && statusRepository.existsById(1)) {
            ticket.setStatus(statusRepository.findById(1).orElse(null));
            ticket.setCreationDate(LocalDateTime.now());
            save(ticket);
        }
    }

    public void update(Ticket ticket) {
        if (exists(ticket.getId())) {
            save(ticket);
        }
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

    public List<Ticket> search(String q) {
        List<Ticket> tickets;

        if (q != null && !q.isEmpty()) {
            tickets = ticketRepository.findByTitleContainingOrAuthorContainingOrDescriptionContaining(q);
        } else {
            tickets = getAll();
        }

        return tickets;
    }

    public List<Ticket> getByStatus(Status status) {
        return ticketRepository.findByStatus(status);
    }

    public List<Ticket> getNotClosed() {
        return ticketRepository.findByStatusNot(statusRepository.findById(4).orElse(null));
    }
}
