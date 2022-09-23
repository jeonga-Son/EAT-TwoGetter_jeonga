//글쓰기 Form에서 내용을 입력한 뒤, '글쓰기' 버튼을 누르면 Post 형식으로 요청이 오고,
//BoardService의 savePost()를 실행하게 됩니다.
package com.ll.example.getTwoGetter.Board.service;


import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.domain.repository.BoardRepository;
import com.ll.example.getTwoGetter.Board.dto.BoardDto;
import com.ll.example.getTwoGetter.exception.DataNotFoundException;
import com.ll.example.getTwoGetter.chat.service.ChatInfoService;
import com.ll.example.getTwoGetter.Board.model.PageResult;
import com.ll.example.getTwoGetter.common.constants.BoardConstants;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private ChatInfoService chatInfoService;

    public BoardService(BoardRepository boardRepository, ChatInfoService chatInfoService) {
        this.boardRepository = boardRepository;
        this.chatInfoService = chatInfoService;
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

//        Stream
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
        if(boards==null){
            return null;
        }
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

    public List<Board> findByUsername(String nickname) {
        List<Board> boards = boardRepository.findByUsername(nickname);
        if(boards==null){
            return null;
        }
        return boards;
    }

    public void delete(List<Board> boards) {
        for(int i=0; i<boards.size(); i++){
            boardRepository.delete(boards.get(i));
        }
    }

    public void modify(String beforeNickname, String afterNickname) {
        List<Board> boards = boardRepository.findAll();
        if(boards ==null){
            return;
        }
        for(int i=0; i<boards.size(); i++){
            Board board = boards.get(i);
            if(board.getUsername().equals(beforeNickname)){
                board.setUsername(afterNickname);
                boardRepository.save(board);
            }
        }
        chatInfoService.modify(beforeNickname, afterNickname);
    }

    public List<Double> getDistanceAsc(String lat, String lng) {
        List<Double> distances = boardRepository.getArticle2(lat, lng);
        return distances;
    }
}