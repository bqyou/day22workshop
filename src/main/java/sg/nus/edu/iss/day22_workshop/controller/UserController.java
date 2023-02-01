package sg.nus.edu.iss.day22_workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.edu.iss.day22_workshop.model.User;
import sg.nus.edu.iss.day22_workshop.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/rsvps/count")
    public ResponseEntity<Integer> getCount() {
        Integer count = userService.count();
        return new ResponseEntity<Integer>(count, HttpStatus.CREATED);
    }

    @GetMapping("/rsvps")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.listAll();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }
    }

    @PostMapping("/rsvp")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        Boolean result = userService.save(user);
        if (!result) {
            return new ResponseEntity<String>("user added", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rsvp")
    public ResponseEntity<User> retrieveUserByName(@RequestParam String q) {
        User user = userService.findByName(q);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/rsvp/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        User u = null;
        u = userService.findById(id);
        if (u == null) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        Integer update = userService.update(user);
        if (update == 0) {
            return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<String>("Updated", HttpStatus.ACCEPTED);
        }
    }

}
