package com.issueflow.project.controller;

import com.issueflow.project.dto.ProjectCreateRequest;
import com.issueflow.project.dto.ProjectResponse;
import com.issueflow.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse createProject(@RequestBody ProjectCreateRequest request,
                                         Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return projectService.createProject(userId, request);
    }

    @GetMapping
    public List<ProjectResponse> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/{projectId}")
    public ProjectResponse getProject(@PathVariable Long projectId) {
        return projectService.getProject(projectId);
    }
}