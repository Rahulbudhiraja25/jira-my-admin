/**
 * JiraPingController - Auto-generated class
 *
 * @Author: @RahulBudhiraja
 * @Created: 2/1/2026
 */

package com.rahul.jira_my_admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import com.rahul.jira_my_admin.service.JiraConnectivityService;

@RestController
@RequestMapping("/jira")
@RequiredArgsConstructor
public class JiraPingController {

    private final JiraConnectivityService connectivityService;

    @GetMapping("/ping")
    public Mono<String> ping() {
        return connectivityService.pingJira();
    }
}
