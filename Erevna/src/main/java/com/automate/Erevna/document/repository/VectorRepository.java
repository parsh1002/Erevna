package com.automate.Erevna.document.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public class VectorRepository{

    private EntityManager entityManager;

    public void saveEmbedding(UUID chunkId, float[] embedding){
        StringBuilder vector = new StringBuilder();

        vector.append("[");
        for(int i = 0; i<embedding.length; i++){
            vector.append(i);
            if(i<embedding.length - 1){
                vector.append(",");
            }
        }
        vector.append("]");

        entityManager.createNativeQuery("""
                        UPDATE document_chunks
                        SET embedding as CAST(:embedding as VECTOR)
                        WHERE id = :id
                        """)
                .setParameter("embedding", vector.toString())
                .setParameter("id", chunkId)
                .executeUpdate();

    }
}
