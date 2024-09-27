package com.dashboard.development_activity_dashboard.service;

import com.dashboard.development_activity_dashboard.model.Comment;
import com.dashboard.development_activity_dashboard.model.Commit;
import com.dashboard.development_activity_dashboard.model.PullRequest;
import com.dashboard.development_activity_dashboard.model.RepositoryInfo;
import com.dashboard.development_activity_dashboard.repository.CommentRepository;
import com.dashboard.development_activity_dashboard.repository.CommitRepository;
import com.dashboard.development_activity_dashboard.repository.PullRequestRepository;
import com.dashboard.development_activity_dashboard.repository.RepoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticsearchService {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private PullRequestRepository pullRequestRepository;

    @Autowired
    private RepoInfoRepository repoInfoRepository;

    @Autowired
    private CommentRepository commentRepository;

    public void saveCommits(List<Commit> commits) {
        commitRepository.saveAll(commits);
    }

    public void savePullRequests(List<PullRequest> pullRequests) {
        pullRequestRepository.saveAll(pullRequests);
    }

    public void saveRepositories(List<RepositoryInfo> repositoryInfos) {
        repoInfoRepository.saveAll(repositoryInfos);
    }

    public void saveComments(List<Comment> commentList) {
        commentRepository.saveAll(commentList);
    }
}
