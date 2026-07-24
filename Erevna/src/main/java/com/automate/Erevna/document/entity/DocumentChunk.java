package com.automate.Erevna.document.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "document_chunks")
public class DocumentChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    private Integer chunkIndex;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Transient
    private float[] embedding;

    
}
