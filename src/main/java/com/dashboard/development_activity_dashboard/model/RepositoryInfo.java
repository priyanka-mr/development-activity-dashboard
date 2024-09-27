package com.dashboard.development_activity_dashboard.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Getter
@Setter
@Document(indexName = "github_repositories")
public class RepositoryInfo {

    @Id
    private Long id;
    private String name;
    private String url;
}
