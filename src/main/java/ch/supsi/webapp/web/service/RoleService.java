package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.Role;
import ch.supsi.webapp.web.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void save(Role role) {
        roleRepository.save(role);
    }

    public List<Role> getAll() {
        return (List<Role>) roleRepository.findAll();
    }

    public Role get(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    public boolean exists(int id) {
        return roleRepository.existsById(id);
    }

    public void delete(int id) {
        roleRepository.deleteById(id);
    }
}
