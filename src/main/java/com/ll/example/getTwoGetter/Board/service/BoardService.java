//글쓰기 Form에서 내용을 입력한 뒤, '글쓰기' 버튼을 누르면 Post 형식으로 요청이 오고,
//BoardService의 savePost()를 실행하게 됩니다.
package com.ll.example.getTwoGetter.Board.service;


import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.domain.repository.BoardRepository;
import com.ll.example.getTwoGetter.Board.dto.BoardDto;
import com.ll.example.getTwoGetter.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .storeType(board.getStoreType())
                    .storeName(board.getStoreName())
                    .orderDetail(board.getOrderDetail())
                    .minimumOrderAmount(board.getMinimumOrderAmount())
                    .deliveryCharge(board.getDeliveryCharge())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .username(board.getUsername())
                    .lat(board.getLat())
                    .lng(board.getLng())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .storeType(board.getStoreType())
                .storeName(board.getStoreName())
                .orderDetail(board.getOrderDetail())
                .minimumOrderAmount(board.getMinimumOrderAmount())
                .deliveryCharge(board.getDeliveryCharge())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .username(board.getUsername())
                .lat(board.getLat())
                .lng(board.getLng())
                .build();
        return boardDto;
    }


    public List<Board> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public Board findById(long id) {
        Board board = boardRepository.findById(id).orElse(null);
        return board;
    }

    public void delete(Board board) {
        boardRepository.delete(board);
    }

    public Board getBoard(Long id) throws DataNotFoundException {
        Optional<Board> board = this.boardRepository.findById(id);
        if (board.isPresent()) {
            return board.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void modify(Board board, String title, String storeName, String storeType, String orderDetail, int minimumOrderAmount, int deliveryCharge, String content) {
        board.setTitle(title);
        board.setStoreType(storeType);
        board.setStoreName(storeName);
        board.setOrderDetail(orderDetail);
        board.setMinimumOrderAmount(minimumOrderAmount);
        board.setDeliveryCharge(deliveryCharge);
        board.setContent(content);

        this.boardRepository.save(board);
    }
}

//}    @Column(length = 100, nullable = false)
//private String title;
//    @Column(length = 100, nullable = false)
//    private String storeType;
//
//    @Column(length = 100, nullable = false)
//    private String storeName;
//
//    @Column(length = 100, nullable = false)
//    private String orderDetail;
//    @Column(length = 100, nullable = false)
//    private int minimumOrderAmount;
//    @Column(length = 100, nullable = false)
//    private int deliveryCharge;
