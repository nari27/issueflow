package com.issueflow.issue.controller;

import com.issueflow.issue.dto.IssueCreateRequest;
import com.issueflow.issue.dto.IssueResponse;
import com.issueflow.issue.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @PostMapping("/projects/{projectId}/issues")
    @ResponseStatus(HttpStatus.CREATED)
    public IssueResponse createIssue(@PathVariable Long projectId,
                                     @RequestBody IssueCreateRequest request,
                                     Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return issueService.createIssue(projectId, userId, request);
    }

    @GetMapping("/projects/{projectId}/issues")
    public List<IssueResponse> getIssuesByProject(@PathVariable Long projectId) {
        return issueService.getIssuesByProject(projectId);
    }
}