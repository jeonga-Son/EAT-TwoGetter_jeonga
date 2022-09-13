//글쓰기 Form에서 내용을 입력한 뒤, '글쓰기' 버튼을 누르면 Post 형식으로 요청이 오고,
//BoardService의 savePost()를 실행하게 됩니다.
package com.ll.example.getTwoGetter.Board.service;


import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.domain.repository.BoardRepository;
import com.ll.example.getTwoGetter.Board.dto.BoardDto;
import com.ll.example.getTwoGetter.Board.model.PageResult;
import com.ll.example.getTwoGetter.common.constants.BoardConstants;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * getBoardList
     *
     * @param page - 호출할 페이지
     * @param latitude - 위도
     * @param longitude - 경도
     * @return
     */

    @Transactional
    public PageResult<BoardDto> getBoardList(int page, String latitude, String longitude) {

        Long totalCount = boardRepository.count(); //화면상 페이지 버튼 처리를 위한 total count 계산


        // boardList 가져오기 (가까운 순으로) -> @Query() 어노테션 사용해서 구현 (마이타비스도 가능한듯)
        List<Board> boardList = boardRepository.getArticle(BoardConstants.PAGE_SIZE,
                (page-1) * BoardConstants.PAGE_SIZE, latitude,longitude);

        //Stream
        List<BoardDto> boardDtoList = boardList.stream().map(board ->
                BoardDto.builder()
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
                        .build()
        ).collect(Collectors.toList());


        // PageResult에 매핑해서 return
        return PageResult.<BoardDto>builder()
                .totalCount(totalCount)
                .contents(boardDtoList)
                .build();
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

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    public List<Board> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public Board findById(long id) {
        Board board = boardRepository.findById(id).orElse(null);
        return board;
    }
}