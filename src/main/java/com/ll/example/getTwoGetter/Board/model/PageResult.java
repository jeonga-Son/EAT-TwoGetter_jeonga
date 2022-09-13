package com.ll.example.getTwoGetter.Board.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class PageResult<T> {
    private Long totalCount;
    private List<T> contents;
}

