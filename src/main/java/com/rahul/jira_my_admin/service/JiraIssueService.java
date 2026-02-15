/**
 * JiraIssueService - Auto-generated class
 *
 * @Author: @RahulBudhiraja
 * @Created: 2/2/2026
 */

package com.rahul.jira_my_admin.service;

import com.rahul.jira_my_admin.dto.JiraIssueRequest;
import com.rahul.jira_my_admin.dto.JiraIssueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JiraIssueService {

    private final WebClient jiraWebClient;

    // =========================================================
    // 🎟 CREATE ISSUE
    // =========================================================
    public Mono<JiraIssueResponse> createIssue(JiraIssueRequest request) {

        String projectKey = request.getProjectKey() != null
                ? request.getProjectKey()
                : "SCRUM";

        String issueType = request.getIssueType() != null
                ? request.getIssueType()
                : "Bug";

        String summary = request.getSummary() != null
                ? request.getSummary()
                : "Created via Chatbot";

        String description = request.getDescription() != null
                ? request.getDescription()
                : summary;

        Map<String, Object> jiraPayload = Map.of(
                "fields", Map.of(
                        "project", Map.of("key", projectKey),
                        "summary", summary,
                        "issuetype", Map.of("name", issueType),
                        "description", buildDescription(description)
                )
        );

        return jiraWebClient.post()
                .uri("/issue")
                .bodyValue(jiraPayload)
                .retrieve()
                .bodyToMono(JiraIssueResponse.class);
    }

    // =========================================================
    // ✏️ UPDATE ISSUE
    // =========================================================
    public Mono<Void> updateIssue(String issueKey, JiraIssueRequest request) {

        Map<String, Object> fields = new HashMap<>();

        if (request.getSummary() != null) {
            fields.put("summary", request.getSummary());
        }

        if (request.getDescription() != null) {
            fields.put(
                    "description",
                    buildDescription(request.getDescription())
            );
        }

        if (request.getIssueType() != null) {
            fields.put(
                    "issuetype",
                    Map.of("name", request.getIssueType())
            );
        }

        // ❌ Nothing to update
        if (fields.isEmpty()) {
            return Mono.error(
                    new IllegalArgumentException("No fields provided to update")
            );
        }

        Map<String, Object> jiraPayload = Map.of("fields", fields);

        return jiraWebClient.put()
                .uri("/issue/{issueKey}", issueKey)
                .bodyValue(jiraPayload)
                .retrieve()
                .bodyToMono(Void.class);
    }

    // =========================================================
    // ❌ DELETE ISSUE
    // =========================================================
    public Mono<Void> deleteIssue(String issueKey) {
        return jiraWebClient.delete()
                .uri("/issue/{issueKey}", issueKey)
                .retrieve()
                .bodyToMono(Void.class);
    }

    // =========================================================
    // 🧠 Jira Description Builder (ADF format)
    // =========================================================
    private Map<String, Object> buildDescription(String text) {
        return Map.of(
                "type", "doc",
                "version", 1,
                "content", List.of(
                        Map.of(
                                "type", "paragraph",
                                "content", List.of(
                                        Map.of(
                                                "type", "text",
                                                "text", text
                                        )
                                )
                        )
                )
        );
    }
}
