package com.example.demo.ingestion;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Loader {

    private final VectorStore vectorStore;

    public Loader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void load() {
        vectorStore.add(List.of(
            new Document("SD-WAN improves enterprise networking performance."),
            new Document("Aruba EdgeConnect is a leading SD-WAN solution."),
            new Document("Weaviate is a vector database for AI search."),
            new Document("MPLS is a traditional WAN technology."),
            new Document("I am a funny guy who loves to make jokes.")
            
        ));
    }
}
