package com.grepp.board.app.controller.web.payload;

import com.grepp.board.infra.emuns.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {
    private String email;
    private String password;
}
