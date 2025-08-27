package com.grepp.board.app.model.service;

import com.grepp.board.app.controller.web.payload.SignupRequest;
import com.grepp.board.app.model.repository.UsersRepository;
import com.grepp.board.infra.emuns.Role;
import com.grepp.board.infra.entity.Users;
import com.grepp.board.infra.error.DuplicateEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    public void signup(SignupRequest signupRequest) {

        if(usersRepository.existsByEmail(signupRequest.getEmail())){
            throw new DuplicateEmailException("이미 사용중인 이메일 입니다.");

        }
        Users user = Users.builder()
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(Role.USER)  // 기본권한
                .build();
        usersRepository.save(user);

    }
}
