package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.Message;
import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public void create(Message message) {
        if (!exists(message.getId())) {
            message.setCreationDate(LocalDateTime.now());
            save(message);
        }
    }

    public List<Message> getByTicketId(int ticketId) {
        return messageRepository.getMessagesByTicketId(ticketId);
    }

    public boolean exists(int id) {
        return messageRepository.existsById(id);
    }
}
