package com.grepp.board.app.model.repository;

import com.grepp.board.infra.entity.Home;
import com.grepp.board.infra.entity.SetConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetConfigRepository extends JpaRepository<SetConfig, Long> {
    SetConfig findTopByOrderByIdAsc();
}
