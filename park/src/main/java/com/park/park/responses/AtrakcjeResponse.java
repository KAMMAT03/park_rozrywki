package com.park.park.responses;

import com.park.park.dto.AtrakcjeDTO;
import lombok.Data;

import java.util.List;

@Data
public class AtrakcjeResponse {
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<AtrakcjeDTO> content;
}
