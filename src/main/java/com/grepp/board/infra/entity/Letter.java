package com.grepp.board.infra.entity;

import com.grepp.board.infra.emuns.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import lombok.*;
import org.attoparser.dom.Text;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "letter")
@EntityListeners(AuditingEntityListener.class)
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔁 FK를 Long이 아니라 연관관계로!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id", nullable = false)
    private Home home;

    private String title;

    private String story;

    private Category category;

    @CreatedDate
    @NotNull
    @Column(name = "created_at" , updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updtaed_at")
    private LocalDateTime updatedAt;

    // qr관련
    // 딱히 어노테이션 설정 할 필요 없을 듯
    private String qrUrl;
    private Integer size;
    private String qrName;
}
