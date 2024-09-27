package com.dashboard.development_activity_dashboard.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Setter
@Getter
@Data
@Document(indexName = "commits")
public class Commit {

    @Id
    private String sha;
    private String author;
    private String message;
    private String url;
    private String timestamp;
    private String repository;

}
