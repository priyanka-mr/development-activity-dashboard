package com.dashboard.development_activity_dashboard.repository;

import com.dashboard.development_activity_dashboard.model.PullRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PullRequestRepository extends ElasticsearchRepository<PullRequest, Long> {
}
