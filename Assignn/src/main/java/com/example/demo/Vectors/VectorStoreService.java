package com.example.demo.Vectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VectorStoreService {

    private final RestTemplate restTemplate;

    public void storeEmbedding(String id, List<Double> embedding, String module, String document) {

        String url = "http://localhost:9000/vectors/add";

        Map<String, Object> request = Map.of(
                "id", id,
                "embedding", embedding,
                "module", module,
                "document", document
        );

        restTemplate.postForObject(url, request, String.class);
    }
    public String searchModule(List<Double> embedding) {

        String url = "http://localhost:9000/vectors/search";

        Map<String,Object> request = Map.of(
                "embedding", embedding,
                "top_k", 1
        );

        Map response = restTemplate.postForObject(url, request, Map.class);

        List metadataList = (List) response.get("metadatas");

        List firstResult = (List) metadataList.get(0);

        Map metadata = (Map) firstResult.get(0);

        return metadata.get("module").toString();
    }
}