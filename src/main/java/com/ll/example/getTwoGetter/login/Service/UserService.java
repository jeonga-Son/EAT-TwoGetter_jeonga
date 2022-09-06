package com.ll.example.getTwoGetter.login.Service;

import com.ll.example.getTwoGetter.login.Repository.UserRepository;
import com.ll.example.getTwoGetter.login.model.Role;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    public User save(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setEnabled(true);
        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    public User findByUserName(String username){
        User user = userRepository.findByUsername(username);
        return user;
    }
}