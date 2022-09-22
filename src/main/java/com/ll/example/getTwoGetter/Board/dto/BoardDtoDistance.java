package com.ll.example.getTwoGetter.Board.dto;

import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
public class BoardDtoDistance {
    private Long id;
    private String title;
    private String storeType;
    private String storeName;
    private String orderDetail;
    private int minimumOrderAmount;
    private int deliveryCharge;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private String username;

    private String lng;

    private String lat;

    private double distance;


    public static BoardDtoDistance[] getBoardDtoDistances(List<Board> boardList, List<Double> distances) {

        BoardDtoDistance[] boardDtoDistances = new BoardDtoDistance[boardList.size()];

        for(int i=0; i<boardDtoDistances.length; i++){
            Board board = boardList.get(i);
            Double distance = distances.get(i);
            boardDtoDistances[i] = BoardDtoDistance.add(board, distance);
        }


        return boardDtoDistances;
    }

    private static BoardDtoDistance add(Board board, Double distance) {
        BoardDtoDistance boardDtoDistance = new BoardDtoDistance();

        boardDtoDistance.setId(board.getId());
        boardDtoDistance.setTitle(board.getTitle());
        boardDtoDistance.setStoreType(board.getStoreType());
        boardDtoDistance.setStoreName(board.getStoreName());
        boardDtoDistance.setOrderDetail(board.getOrderDetail());
        boardDtoDistance.setMinimumOrderAmount(board.getMinimumOrderAmount());
        boardDtoDistance.setDeliveryCharge(board.getDeliveryCharge());
        boardDtoDistance.setContent(board.getContent());
        boardDtoDistance.setCreatedDate(board.getCreatedDate());
        boardDtoDistance.setModifiedDate(board.getModifiedDate());
        boardDtoDistance.setUsername(board.getUsername());
        boardDtoDistance.setLat(board.getLat());
        boardDtoDistance.setLng(board.getLng());
        boardDtoDistance.setDistance(distance);
        return boardDtoDistance;
    }
}
