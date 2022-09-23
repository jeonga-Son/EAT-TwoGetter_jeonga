//글쓰기 Form에서 받은 데이터는 '글쓰기' 버튼을 누르면 /post로 Post 요청을 하게 됩니다.
//BoardController에 Post로 받은 데이터를 데이터베이스에 추가하는 것을 추가해 줍니다.

package com.ll.example.getTwoGetter.Board.controller;


import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.domain.repository.BoardRepository;
import com.ll.example.getTwoGetter.Board.dto.BoardDto;
import com.ll.example.getTwoGetter.Board.service.BoardService;
import com.ll.example.getTwoGetter.Board.model.PageResult;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.ll.example.getTwoGetter.exception.DataNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    UserService userService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/board") //view용 컨트롤러
    public String list(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user); // user라는 key값에 login된 사용자 user의 정보를 넘긴다.
        }
        return "board/list.html";
    }
    /**
     *
     * rest-api : boards
     * board list를 return
     * @param page - 호출할 페이지
     * @param latitude - 위도
     * @param longitude - 경도
     * @return
     */

    @GetMapping("/boards")
    public ResponseEntity<PageResult> getBoards(@RequestParam int page, @RequestParam String latitude,
                                                @RequestParam String longitude) {
        System.out.println("::::::::::::::" + latitude + ":::::::::" + longitude);
        List<Double> boardDistance = boardService.getDistanceAsc(latitude, longitude, page);

        PageResult pageResult = boardService.getBoardList(page, latitude, longitude, boardDistance);
        // rest-api controller 응답값으로는 ResponseEntity를 사용하는 것이 좋다고함


        return ResponseEntity.ok().body(pageResult);
    }


    @GetMapping("/getMarkerBoard/{id}")
    @ResponseBody
    public Board getMarkerBoard(@PathVariable long id){
        System.out.println(id);
        Board board = boardService.findById(id);
        return board;
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
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }

    @PostMapping("/board/modify")
    public String modifyBoard(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
        //홈으로 리다이렉트//git연습
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/board/delete/{id}")
    public String boardDelete(Principal principal, @PathVariable("id") Long id) throws DataNotFoundException {
        Board board = this.boardService.getBoard(id);
        this.boardService.delete(board);
        return "/";
    }

}