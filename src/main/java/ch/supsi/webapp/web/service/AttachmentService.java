package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.Attachment;
import ch.supsi.webapp.web.model.Status;
import ch.supsi.webapp.web.repository.AttachmentRepository;
import ch.supsi.webapp.web.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    public void save(Attachment attachment) {
        attachmentRepository.save(attachment);
    }

    public Attachment get(int id) {
        return attachmentRepository.findById(id).orElse(null);
    }

    public boolean exists(int id) {
        return attachmentRepository.existsById(id);
    }

    public void delete(int id) {
        attachmentRepository.deleteById(id);
    }
}
