package com.example.demo.Developer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/developer")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping
    public Developer createDeveloper(@RequestBody DeveloperCreateRequest request) {
        return developerService.createDeveloper(request);
    }
}