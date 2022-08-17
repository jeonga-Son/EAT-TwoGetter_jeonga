package com.ll.example.getTwoGetter.chat.repository;

import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ChatInfoRepository extends JpaRepository<ChatInfo , Long> {
    List<ChatInfo> findByUsername(String username);

    List<ChatInfo> findByPartner(String nickname);
}
