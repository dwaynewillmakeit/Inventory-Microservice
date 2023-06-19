package com.dwaynewillmakeit.identity_management.util;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PaginatedResult<T> {

    private final List<T> data;
    private final int pageSize;
    private final int totalItem;

    private int totalPage;

    public int getTotalPage() {
        return (int) Math.ceil((double) totalItem / pageSize);
    }
}
