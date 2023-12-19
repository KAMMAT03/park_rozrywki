package com.park.park.responses;

import lombok.Data;

import java.util.List;

@Data
public class ModelResponse<T> {
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<T> content;
}
