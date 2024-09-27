package com.dashboard.development_activity_dashboard.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Setter
@Getter
@Data
@Document(indexName = "pull_requests")
public class PullRequest {

    @Id
    private Long id;
    private String title;
    private String user;
    private String createdAt;

}
