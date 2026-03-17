package com.example.demo.Developer;

import lombok.Data;

@Data
public class DeveloperCreateRequest {

    private String name;

    private String email;

    private String module;

    private String moduleDescription;
}