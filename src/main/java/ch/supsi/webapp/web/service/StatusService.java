package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.Status;
import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.repository.StatusRepository;
import ch.supsi.webapp.web.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public void save(Status status) {
        statusRepository.save(status);
    }

    public List<Status> getAll() {
        return (List<Status>) statusRepository.findAll();
    }

    public Status get(int id) {
        return statusRepository.findById(id).orElse(null);
    }

    public boolean exists(int id) {
        return statusRepository.existsById(id);
    }

    public void delete(int id) {
        statusRepository.deleteById(id);
    }
}
