package com.ll.example.getTwoGetter.login.Repository;

import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    String findByNickname(User user);

}
