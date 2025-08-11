package com.grepp.board.app.model.repository;

import com.grepp.board.infra.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeLenderRepository extends JpaRepository<Home, Long> {

    Home findAllById(Long id);
}
