package com.grepp.board.app.controller.web.payload;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QrCodeUrlRequest {

    @Column(length = 300)
    private String url;
}
