package com.issueflow.issue.service;

import com.issueflow.global.exception.NotFoundException;
import com.issueflow.issue.dto.IssueCreateRequest;
import com.issueflow.issue.dto.IssueResponse;
import com.issueflow.issue.entity.Issue;
import com.issueflow.issue.entity.IssueStatus;
import com.issueflow.issue.repository.IssueRepository;
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
public class IssueService {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public IssueResponse createIssue(Long projectId, Long userId, IssueCreateRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("프로젝트를 찾을 수 없습니다."));

        User author = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));

        User assignee = null;
        if (request.getAssigneeId() != null) {
            assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new NotFoundException("담당자를 찾을 수 없습니다."));
        }

        Issue issue = Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(IssueStatus.TODO)
                .priority(request.getPriority())
                .project(project)
                .author(author)
                .assignee(assignee)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Issue savedIssue = issueRepository.save(issue);

        return new IssueResponse(
                savedIssue.getId(),
                savedIssue.getTitle(),
                savedIssue.getDescription(),
                savedIssue.getStatus(),
                savedIssue.getPriority(),
                savedIssue.getProject().getId(),
                savedIssue.getAuthor().getId(),
                savedIssue.getAuthor().getName(),
                savedIssue.getAssignee() != null ? savedIssue.getAssignee().getId() : null,
                savedIssue.getAssignee() != null ? savedIssue.getAssignee().getName() : null
        );
    }

    @Transactional(readOnly = true)
    public List<IssueResponse> getIssuesByProject(Long projectId) {
        return issueRepository.findByProjectId(projectId).stream()
                .map(issue -> new IssueResponse(
                        issue.getId(),
                        issue.getTitle(),
                        issue.getDescription(),
                        issue.getStatus(),
                        issue.getPriority(),
                        issue.getProject().getId(),
                        issue.getAuthor().getId(),
                        issue.getAuthor().getName(),
                        issue.getAssignee() != null ? issue.getAssignee().getId() : null,
                        issue.getAssignee() != null ? issue.getAssignee().getName() : null
                ))
                .toList();
    }
}