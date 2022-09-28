package com.ll.example.getTwoGetter.Board.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class PageResult<BoardDtoDistance> {
    private Long totalCount;
    private List<BoardDtoDistance> contents;
}

