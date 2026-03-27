package com.issueflow.project.service;

import com.issueflow.global.exception.NotFoundException;
import com.issueflow.project.dto.ProjectCreateRequest;
import com.issueflow.project.dto.ProjectResponse;
import com.issueflow.project.entity.Project;
import com.issueflow.project.repository.ProjectRepository;
import com.issueflow.user.entity.User;
import com.issueflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectResponse createProject(Long userId, ProjectCreateRequest request) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));

        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .owner(owner)
                .createdAt(LocalDateTime.now())
                .build();

        Project savedProject = projectRepository.save(project);

        return new ProjectResponse(
                savedProject.getId(),
                savedProject.getName(),
                savedProject.getDescription(),
                savedProject.getOwner().getId(),
                savedProject.getOwner().getName()
        );
    }

    public List<ProjectResponse> getProjects() {
        return projectRepository.findAll().stream()
                .map(project -> new ProjectResponse(
                        project.getId(),
                        project.getName(),
                        project.getDescription(),
                        project.getOwner().getId(),
                        project.getOwner().getName()
                ))
                .toList();
    }

    @Transactional
    public ProjectResponse getProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("프로젝트를 찾을 수 없습니다."));

        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getOwner().getId(),
                project.getOwner().getName()
        );
    }
}