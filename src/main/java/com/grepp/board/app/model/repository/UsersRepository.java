package com.grepp.board.app.model.repository;

import com.grepp.board.infra.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    //DB에서 사용자를 찾아주는 창구가 필요합니다. 나중에 서비스/시큐리티가 이것을 사용해 조회합니다.
    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);
}
