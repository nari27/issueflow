package com.issueflow.issue.dto;

import lombok.Getter;

@Getter
public class IssueUpdateRequest {

    private String title;
    private String description;
    private Long assigneeId;
}