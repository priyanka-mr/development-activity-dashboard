package com.dashboard.development_activity_dashboard.repository;

import com.dashboard.development_activity_dashboard.model.Comment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CommentRepository extends ElasticsearchRepository<Comment, Long> {
}
