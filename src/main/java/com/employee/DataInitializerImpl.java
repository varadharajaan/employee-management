package com.employee;


import com.employee.model.User;
import com.employee.repository.RoleRepository;
import com.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Varadharajan
 */
@Component
@Transactional
public class DataInitializerImpl {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void initData() {

        User user = new User();
        user.setEmail("varathu09@gmail.com");
        user.setFirstname("varadha");
        user.setLastname("rajan");
        user.setUsername("varadharajan");
        user.setPassword(passwordEncoder.encode("password"));

        User user1 = new User();
        user1.setEmail("mike09@gmail.com");
        user1.setFirstname("john");
        user1.setLastname("mike");
        user1.setUsername("john");
        user1.setPassword(passwordEncoder.encode("password"));

        //roleRepository.save(role);
       // userRepository.save(user);
        //userRepository.save(user1);

        List<User> users = userRepository.findAll();
        users.forEach(User::getId);


    }


}
