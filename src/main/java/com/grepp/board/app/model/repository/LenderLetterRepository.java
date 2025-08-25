package com.grepp.board.app.model.repository;

import com.grepp.board.infra.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LenderLetterRepository extends JpaRepository<Letter, Long> {
}
