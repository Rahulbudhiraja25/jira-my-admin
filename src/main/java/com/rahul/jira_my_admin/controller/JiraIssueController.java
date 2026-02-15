/**
 * JiraIssueController
 *
 * @Author: @RahulBudhiraja
 */

package com.rahul.jira_my_admin.controller;

import com.rahul.jira_my_admin.dto.JiraIssueRequest;
import com.rahul.jira_my_admin.dto.JiraIssueResponse;
import com.rahul.jira_my_admin.service.JiraIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jira/issues")
@RequiredArgsConstructor
public class JiraIssueController {

    private final JiraIssueService jiraIssueService;

    // 🎟 CREATE
    @PostMapping
    public Mono<ResponseEntity<JiraIssueResponse>> createIssue(
            @RequestBody JiraIssueRequest request) {

        return jiraIssueService.createIssue(request)
                .map(ResponseEntity::ok);
    }

    // ✏️ UPDATE
    @PutMapping("/{issueKey}")
    public Mono<ResponseEntity<Void>> updateIssue(
            @PathVariable String issueKey,
            @RequestBody JiraIssueRequest request) {

        return jiraIssueService.updateIssue(issueKey, request)
                .thenReturn(ResponseEntity.noContent().build());
    }

    // ❌ DELETE
    @DeleteMapping("/{issueKey}")
    public Mono<ResponseEntity<Void>> deleteIssue(
            @PathVariable String issueKey) {

        return jiraIssueService.deleteIssue(issueKey)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
