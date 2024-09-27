package com.dashboard.development_activity_dashboard.repository;

import com.dashboard.development_activity_dashboard.model.RepositoryInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RepoInfoRepository extends ElasticsearchRepository<RepositoryInfo, Long> {
}
