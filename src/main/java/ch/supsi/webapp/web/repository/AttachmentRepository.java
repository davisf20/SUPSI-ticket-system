package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Integer> {
}
