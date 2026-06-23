package com.demo.ai.controller;

import org.springframework.web.bind.annotation.*;

import com.demo.ai.service.AIService;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ask")
    public String generate(@RequestParam String prompt) {
        return aiService.generateText(prompt);
    }
}
