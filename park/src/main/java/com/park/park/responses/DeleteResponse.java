package com.park.park.responses;

import lombok.Data;

@Data
public class DeleteResponse {
    private String message;
    private String code = "200";
    private long deleteId;

    public DeleteResponse(String message, long deleteId) {
        this.message = message;
        this.deleteId = deleteId;
    }
}
