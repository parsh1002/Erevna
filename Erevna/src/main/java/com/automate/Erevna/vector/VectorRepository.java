package com.automate.Erevna.vector;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor

public class VectorRepository{

    private final EntityManager entityManager;

    public void saveEmbedding(UUID chunkId, float[] embedding){
        String vector = convertToPgvector(embedding);


        entityManager.createNativeQuery("""
                        UPDATE document_chunks
                        SET embedding as CAST(:embedding as VECTOR)
                        WHERE id = :id
                        """)
                .setParameter("embedding", vector.toString())
                .setParameter("id", chunkId)
                .executeUpdate();

    }

    @SuppressWarnings("unchecked")
    public List<String> findRelevantChunks(float[] vector, int limit){
        String queryEmbedding = convertToPgvector(vector);

        return entityManager.createNativeQuery("""
                        SELECT content
                        FROM document_chunks
                        ORDER BY embedding <=> CAST(:embedding as VECTOR)
                        LIMIT :limit
                        """)
                .setParameter("embedding", queryEmbedding)
                .setParameter("limit", limit)
                .getResultList();
    }

    public String convertToPgvector(float[] embedding){
        StringBuilder vector = new StringBuilder();

        vector.append("[");
        for(int i = 0; i<embedding.length; i++){
            vector.append(i);
            if(i<embedding.length - 1){
                vector.append(",");
            }
        }
        vector.append("]");
        return vector.toString();
    }
}
