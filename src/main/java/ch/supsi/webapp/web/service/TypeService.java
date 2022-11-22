package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.Type;
import ch.supsi.webapp.web.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public void save(Type type) {
        typeRepository.save(type);
    }

    public List<Type> getAll() {
        return (List<Type>) typeRepository.findAll();
    }

    public Type get(int id) {
        return typeRepository.findById(id).orElse(null);
    }

    public boolean exists(int id) {
        return typeRepository.existsById(id);
    }

    public void delete(int id) {
        typeRepository.deleteById(id);
    }
}
