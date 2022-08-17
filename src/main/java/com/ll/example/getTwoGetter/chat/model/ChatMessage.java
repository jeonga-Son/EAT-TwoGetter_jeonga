package com.ll.example.getTwoGetter.chat.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 200, nullable = false)
    private String content;

    @LastModifiedDate
    private LocalDateTime chatMessageTime;

    @ManyToOne
    private ChatInfo chatInfo;
}
