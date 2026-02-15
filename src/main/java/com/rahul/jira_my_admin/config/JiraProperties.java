/**
 * JiraProperties - Auto-generated class
 *
 * @Author: @RahulBudhiraja
 * @Created: 2/1/2026
 */

package com.rahul.jira_my_admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jira")
@Data
public class JiraProperties {
    private String baseUrl;
    private String username;
    private String password;
}