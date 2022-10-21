package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.User;
import ch.supsi.webapp.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean exists(int id) {
        return userRepository.existsById(id);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
