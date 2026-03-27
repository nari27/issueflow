package com.issueflow.issue.dto;

import lombok.Getter;

@Getter
public class IssueCreateRequest {

    private String title;
    private String description;
    private String priority;
    private Long assigneeId;
}