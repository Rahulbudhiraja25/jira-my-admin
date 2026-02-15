/**
 * JiraConnectivityService - Auto-generated class
 *
 * @Author: @RahulBudhiraja
 * @Created: 2/1/2026
 */

package com.rahul.jira_my_admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JiraConnectivityService {

    private final WebClient jiraWebClient;

    public Mono<String> pingJira() {
        return jiraWebClient.get()
                .uri("/project")
                .retrieve()
                .bodyToMono(String.class);
    }
}
