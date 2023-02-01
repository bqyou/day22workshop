package sg.nus.edu.iss.day22_workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.iss.day22_workshop.model.User;
import sg.nus.edu.iss.day22_workshop.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public int count() {
        return userRepo.count();
    }

    public Boolean save(User user) {
        return userRepo.save(user);
    }

    public List<User> listAll() {
        return userRepo.listAll();
    }

    public User findByName(String name) {
        return userRepo.findByName(name);
    }

    public Integer update(User user) {
        return userRepo.update(user);
    }

    public User findById(Integer id) {
        return userRepo.findById(id);
    }

}
