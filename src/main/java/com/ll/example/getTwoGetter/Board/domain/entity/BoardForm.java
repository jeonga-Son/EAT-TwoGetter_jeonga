package com.ll.example.getTwoGetter.Board.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
public class BoardForm {

    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String storeType;

    @Column(length = 100, nullable = false)
    private String storeName;

    @Column(length = 100, nullable = false)
    private String orderDetail;
    @Column(length = 100, nullable = false)
    private int minimumOrderAmount;
    @Column(length = 100, nullable = false)
    private int deliveryCharge;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
    @Column(length = 100)
    private String username;

}
