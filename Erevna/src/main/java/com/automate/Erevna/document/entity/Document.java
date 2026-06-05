package com.automate.Erevna.document.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String extractedText;

    private String status;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreated(){
        createdAt = LocalDateTime.now();
    }

}
