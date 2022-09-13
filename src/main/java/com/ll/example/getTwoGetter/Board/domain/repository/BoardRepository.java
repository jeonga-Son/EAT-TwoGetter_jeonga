//Repository는 데이터 조작을 담당하며, JpaRepository를 상속받습니다.
//JpaRepository의 값은 매핑할 Entity와 Id의 타입입니다.

package com.ll.example.getTwoGetter.Board.domain.repository;


import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    /**
     * TODO : 조건 확정되면 쿼리에 having 절 추가
     * having distance <= 0.5
     */
    @Query(value = "select b.id, b.title, b.order_detail, b.minimum_order_amount, b.delivery_charge, " +
            "b.store_type, b.store_name, b.content, b.username, b.created_date, b.lat, b.lng, b.modified_date, " +
            "(6371*ACOS(COS(RADIANS(?3))) * COS(RADIANS(b.lat)) * COS(RADIANS(b.lng)-RADIANS(?4) + SIN(RADIANS(?3)) * SIN(RADIANS(b.lat)))) " +
            "AS distance from Board b order by distance asc limit ?2, ?1", nativeQuery = true)
    // 수학적 식이 아닌 실제 두 지점간의 거리를 구하고자 삼각함수와 라디안을 사용했습니다.
    // HAVING 부분은 0.5km 이내 글만 뜨게끔 구현한 것
    List<Board> getArticle(int limit, int offset, String latitude, String longitude);
    List<Board> findByUsername(String nickname);
}
//    Example SQLSELECT count(*)FROM (SELECT ( 6371 * acos( cos( radians( 37.4097995 ) ) * cos( radians( lat) ) * cos( radians( lot ) - radians(127.128697) ) + sin( radians(37.4097995) ) * sin( radians(lat) ) ) )
//        AS distanceFROM cf_location) DATA
//        WHERE DATA.distance < 3
//