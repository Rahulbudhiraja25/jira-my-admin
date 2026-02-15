package com.rahul.jira_my_admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class JiraClientConfig {

    private final JiraProperties jiraProperties;

    @Bean
    public WebClient jiraWebClient() {

        String auth = Base64.getEncoder()
                .encodeToString(
                        (jiraProperties.getUsername() + ":" +
                                jiraProperties.getPassword()).getBytes()
                );

        return WebClient.builder()
                .baseUrl("https://rahulbudhirajawork.atlassian.net/rest/api/3")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + auth)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
