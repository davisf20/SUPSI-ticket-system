package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.User;
import ch.supsi.webapp.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(roleService.get(2));
        userRepository.save(user);
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean exists(int id) {
        return userRepository.existsById(id);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
