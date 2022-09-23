//Repository는 데이터 조작을 담당하며, JpaRepository를 상속받습니다.
//JpaRepository의 값은 매핑할 Entity와 Id의 타입입니다.

package com.ll.example.getTwoGetter.Board.domain.repository;


import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    /**
     * TODO : 조건 확정되면 쿼리에 having 절 추가
     * having distance <= 0.5
     */
    @Query(value = "select b.id, b.title, b.order_detail, b.minimum_order_amount, b.delivery_charge, b.store_type, b.store_name, b.content, b.username, b.created_date, b.lat, b.lng, b.modified_date, " +
            "(6371*ACOS(COS(RADIANS(?3))) * COS(RADIANS(b.lat)) * COS(RADIANS(b.lng)-RADIANS(?4) + SIN(RADIANS(?3)) * SIN(RADIANS(b.lat)))) " +
            "AS distance "+"from Board b order by distance desc limit ?2, ?1", nativeQuery = true)
    List<Board> getArticle(int limit, int offset, String latitude, String longitude);
//    @Query(value = "select "+"new"+" com.ll.example.getTwoGetter.Board.dto.BoardDto(b.id, b.title, b.order_detail, b.minimum_order_amount, b.delivery_charge, b.store_type, b.store_name, b.content, b.username, b.created_date, b.lat, b.lng, b.modified_date, " +
//            "(6371*ACOS(COS(RADIANS(?3))) * COS(RADIANS(b.lat)) * COS(RADIANS(b.lng)-RADIANS(?4) + SIN(RADIANS(?3)) * SIN(RADIANS(b.lat)))) " +
//            "AS distance) "+"from Board b order by distance asc")
    // 수학적 식이 아닌 실제 두 지점간의 거리를 구하고자 삼각함수와 라디안을 사용했습니다.
    // HAVING 부분은 0.5km 이내 글만 뜨게끔 구현한 것

    //하버사인 공식
    @Query(value = "select " +
            "(6371*ACOS(COS(RADIANS(:lat)) * COS(RADIANS(b.lat)) * COS(RADIANS(b.lng)-RADIANS(:lng)) + SIN(RADIANS(:lat)) * SIN(RADIANS(b.lat)))) " +
            "AS distance "+"from Board b order by distance asc ", nativeQuery = true)
    List<Double> getArticle2(@Param("lat")String lat, @Param("lng") String lng);


    List<Board> findByUsername(String nickname);
}
