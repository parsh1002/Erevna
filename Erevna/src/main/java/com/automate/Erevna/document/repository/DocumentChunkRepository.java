package com.automate.Erevna.document.repository;


import com.automate.Erevna.document.entity.DocumentChunk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentChunkRepository extends JpaRepository<DocumentChunk, UUID> {


}
