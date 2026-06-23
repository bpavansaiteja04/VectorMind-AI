package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.*;
import org.springframework.ai.document.Document;

import java.util.List;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
    }

    @GetMapping
    public String ask(@RequestParam String q) {

        List<Document> docs = vectorStore.similaritySearch(
                SearchRequest.builder().query(q).topK(4).build()
        );

        String context = String.join("\n",
                docs.stream().map(Document::getText).toList());

        return chatClient.prompt()
                .system("Answer ONLY using context. If missing say I don't know.")
                .user("Context:\n" + context + "\n\nQuestion:\n" + q)
                .call()
                .content();
    }
}
