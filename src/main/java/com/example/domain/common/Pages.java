package com.example.domain.common;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Pages<T> {
    private List<T> contents;
    private int page;
    private int size;
    private Long total;

}
