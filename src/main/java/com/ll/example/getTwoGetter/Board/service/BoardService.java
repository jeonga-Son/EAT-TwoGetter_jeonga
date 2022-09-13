//글쓰기 Form에서 내용을 입력한 뒤, '글쓰기' 버튼을 누르면 Post 형식으로 요청이 오고,
//BoardService의 savePost()를 실행하게 됩니다.
package com.ll.example.getTwoGetter.Board.service;


import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.domain.repository.BoardRepository;
import com.ll.example.getTwoGetter.Board.dto.BoardDto;
import com.ll.example.getTwoGetter.chat.service.ChatInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    private ChatInfoService chatInfoService;

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

        for(Board board : boardList) {
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
}