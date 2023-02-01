package sg.nus.edu.iss.day22_workshop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.day22_workshop.model.User;

@Repository
public interface UserRepo {

    int count();

    Boolean save(User user);

    List<User> listAll();

    User findByName(String name);

    Integer update(User user);

    User findById(Integer id);

}
