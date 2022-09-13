//Entity는 데이터베이스 테이블과 매핑되는 객체입니다.

package com.ll.example.getTwoGetter.Board.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String storeType;

    @Column(length = 100, nullable = false)
    private String storeName;

    @Column(length = 100, nullable = false)
    private String orderDetail;
    @Column(length = 100, nullable = false)
    private String minimumOrderAmount;
    @Column(length = 100, nullable = false)
    private String deliveryCharge;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
    @Column(length = 100)
    private String username;

    @Column(length = 100)
    private String lat;

    @Column(length = 100)
    private String lng;
    @Column(length = 100)
    private Double distance;

    @Builder
    public Board(Long id, String title,  String storeType, String storeName, String orderDetail, String minimumOrderAmount,
                 String deliveryCharge, String content, String username, String lat, String lng, Double distance) {
        this.id = id;
        this.title = title;
        this.storeType = storeType;
        this.storeName = storeName;
        this.orderDetail = orderDetail;
        this.deliveryCharge = deliveryCharge;
        this.minimumOrderAmount = minimumOrderAmount;
        this.content = content;
        this.username = username;
        this.lat = lat;
        this.lng = lng;
        this.distance=distance;
    }

}