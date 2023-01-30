package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.Message;
import ch.supsi.webapp.web.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> getMessagesByTicketId(int ticketId);
}
