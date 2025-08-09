package com.grepp.board.infra.entity;

import com.grepp.board.infra.emuns.Category;
import com.grepp.board.infra.emuns.Comment;
import jakarta.persistence.*;

@Entity
public class HomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Comment today_comment;


    private Category category;

    @Column(nullable = false)
    private String introduce_story;

}
