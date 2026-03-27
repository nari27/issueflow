package com.issueflow.issue.dto;

import com.issueflow.issue.entity.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueResponse {

    private Long id;
    private String title;
    private String description;
    private IssueStatus status;
    private String priority;
    private Long projectId;
    private Long authorId;
    private String authorName;
    private Long assigneeId;
    private String assigneeName;
}