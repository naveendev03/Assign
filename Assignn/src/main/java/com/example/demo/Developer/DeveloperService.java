package com.example.demo.Developer;

import com.example.demo.Ollama.EmbeddingService;
import com.example.demo.Vectors.VectorStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;
    private final EmbeddingService embeddingService;
    private final VectorStoreService vectorStoreService;

    public Developer createDeveloper(DeveloperCreateRequest request) {

        // 1. Save developer in Postgres
        Developer developer = Developer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .module(request.getModule())
                .moduleDescription(request.getModuleDescription())
                .build();

        Developer savedDeveloper = developerRepository.save(developer);

        // 2. Generate embedding from module description
        List<Double> embedding =
                embeddingService.generateEmbedding(request.getModuleDescription());

        // 3. Store vector in Python Vector Service (ChromaDB)
        vectorStoreService.storeEmbedding(
                savedDeveloper.getId().toString(),
                embedding,
                savedDeveloper.getModule(),
                savedDeveloper.getModuleDescription()   // document parameter
        );

        return savedDeveloper;
    }
}