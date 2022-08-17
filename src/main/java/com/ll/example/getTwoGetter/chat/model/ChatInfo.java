package com.ll.example.getTwoGetter.chat.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ChatInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String chatTitle;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String partner;

    @LastModifiedDate
    private LocalDateTime createChatDate;

    @OneToMany(mappedBy = "chatInfo", cascade = {CascadeType.ALL})
    private List<ChatMessage> chatMessageList = new ArrayList<>();

}
