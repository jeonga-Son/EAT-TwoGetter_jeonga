package com.ll.example.getTwoGetter.login.Service;

import com.ll.example.getTwoGetter.login.Repository.UserRepository;
import com.ll.example.getTwoGetter.login.model.Role;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    //유저 정보를 저장
    public User save(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setEnabled(true);
        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    //모든 user의 정보를 리턴
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    //username에 해당하는 user를 찾아서 리턴
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    //user 정보를 삭제
    public void delete(User user) {
        userRepository.delete(user);
    }
}