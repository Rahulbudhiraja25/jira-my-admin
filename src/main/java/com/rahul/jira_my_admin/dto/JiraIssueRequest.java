package com.rahul.jira_my_admin.dto;

import lombok.Data;

@Data
public class JiraIssueRequest {

    // Project key like "SCRUM"
    private String projectKey;

    // Issue summary
    private String summary;

    // Plain text description (we convert to ADF internally)
    private String description;

    // Bug / Task / Story
    private String issueType;
}
