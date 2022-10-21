package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.User;
import ch.supsi.webapp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    public ResponseEntity<User> post(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(value="/users")
    public List<User> get() {
        return userService.getAll();
    }

    @GetMapping(value="/users/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        if (userService.exists(id)) {
            return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/users/{id}")
    public ResponseEntity<User> put(@PathVariable int id, @RequestBody User newUser) {
        if (userService.exists(id)) {
            User user = userService.get(id);
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setTickets(newUser.getTickets());
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/users/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int id) {
        if (userService.exists(id)) {
            userService.delete(id);
            return new ResponseEntity<>(Map.of("success", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
