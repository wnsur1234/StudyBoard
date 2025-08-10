package com.grepp.board.infra.entity;

import com.grepp.board.infra.emuns.Category;
import com.grepp.board.infra.emuns.Comment;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "home")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Comment today_comment;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    @Column(nullable = false)
    private String introduce_story;

}
