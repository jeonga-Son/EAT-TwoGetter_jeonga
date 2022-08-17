package com.ll.example.getTwoGetter.chat.repository;

import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatInfoRepository extends JpaRepository<ChatInfo , Long> {
}
