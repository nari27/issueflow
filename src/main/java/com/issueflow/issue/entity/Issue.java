package com.issueflow.issue.entity;

import com.issueflow.project.entity.Project;
import com.issueflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status;

    @Column(nullable = false)
    private String priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public  void updateStatus(IssueStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String title, String description, User assignee) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.updatedAt = LocalDateTime.now();
    }
}