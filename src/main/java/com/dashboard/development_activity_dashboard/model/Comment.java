package com.dashboard.development_activity_dashboard.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Getter
@Setter
@Document(indexName = "comments")
public class Comment {

    @Id
    private Long id;
    private String comment;
    private String user;
    private String createdAt;
}
