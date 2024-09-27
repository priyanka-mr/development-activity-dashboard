package com.dashboard.development_activity_dashboard.controller;

import com.dashboard.development_activity_dashboard.model.Comment;
import com.dashboard.development_activity_dashboard.model.Commit;
import com.dashboard.development_activity_dashboard.model.PullRequest;
import com.dashboard.development_activity_dashboard.model.RepositoryInfo;
import com.dashboard.development_activity_dashboard.service.ElasticsearchService;
import com.dashboard.development_activity_dashboard.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GitHubController {

    @Autowired
    private  GitHubService gitHubService;

    @Autowired
    private ElasticsearchService elasticsearchService;

    @GetMapping("/repos/{owner}")
    public String getAllRepositories(@PathVariable String owner) throws Exception {
        List<RepositoryInfo> repos = null;
        try {
            repos = gitHubService.getAllGitHubRepositories(owner);
        } catch (Exception e) {
            return e.getMessage();
        }
        elasticsearchService.saveRepositories(repos);
        return "Repo Info saved to Elasticsearch";
    }

    @GetMapping("/repos/{owner}/{repo}/commits")
    public String getAllCommits(@PathVariable String owner, @PathVariable String repo) throws Exception {
        List<Commit> commits = null;
        try {
            commits = gitHubService.getCommits(owner, repo);
        } catch (Exception e) {
            return e.getMessage();
        }
        elasticsearchService.saveCommits(commits);
        return "Commits saved to Elasticsearch";
    }

    @GetMapping("repos/{owner}/{repo}/pulls")
    public String getAllPullRequests(@PathVariable String owner, @PathVariable String repo) throws Exception {
        List<PullRequest> pullRequests = null;
        try {
            pullRequests = gitHubService.getPullRequests(owner, repo);
        } catch (Exception e) {
            return e.getMessage();
        }
        elasticsearchService.savePullRequests(pullRequests);
        return "Pull Requests saved to Elasticsearch";
    }

    @GetMapping("repos/{owner}/{repo}/comments")
    public String getAllComments(@PathVariable String owner, @PathVariable String repo) throws Exception {
        List<Comment> commentList = null;
        try {
            commentList = gitHubService.getComments(owner, repo);
        } catch (Exception e) {
            return e.getMessage();
        }
        elasticsearchService.saveComments(commentList);
        return "Comments saved to Elasticsearch";
    }
}