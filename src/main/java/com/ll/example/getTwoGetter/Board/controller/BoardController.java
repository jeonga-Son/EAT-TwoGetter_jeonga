//글쓰기 Form에서 받은 데이터는 '글쓰기' 버튼을 누르면 /post로 Post 요청을 하게 됩니다.
//BoardController에 Post로 받은 데이터를 데이터베이스에 추가하는 것을 추가해 줍니다.

package com.ll.example.getTwoGetter.Board.controller;


import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.dto.BoardDto;
import com.ll.example.getTwoGetter.Board.service.BoardService;
import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import com.ll.example.getTwoGetter.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Component
@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "index.html";
    }

    @GetMapping("/post")
    public String post() {
        return "index.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        //boardDto 객체를 post 이름으로 추가한다.
        model.addAttribute("post", boardDto);
        return "index.html";
    }

    @GetMapping("/getMarkerBoard/{id}")
    @ResponseBody
    public Board getMarkerBoard(@PathVariable long id) {
        System.out.println(id);
        Board board = boardService.findById(id);
        return board;
    }



    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "index.html";
    }

//    @PutMapping("/post/edit/{id}")
//    public String update(BoardDto boardDto) {
//        boardService.savePost(boardDto);
//        return "redirect:/";
//    }

//    @DeleteMapping("/deleteBoard/{id}")
//    public String deleteBoard(@PathVariable("id") long id) {
//        Board board = this.boardService.findById(id);
//        this.boardService.delete(board);
//        return "redirect:/";
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/board/delete/{id}")
    public String boardDelete(Principal principal, @PathVariable("id") Long id) throws DataNotFoundException {
        Board board = this.boardService.getBoard(id);
        this.boardService.delete(board);
        return "/";
    }


}