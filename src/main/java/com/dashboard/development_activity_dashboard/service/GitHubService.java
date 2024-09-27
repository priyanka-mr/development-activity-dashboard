package com.dashboard.development_activity_dashboard.service;

import com.dashboard.development_activity_dashboard.model.Comment;
import com.dashboard.development_activity_dashboard.model.Commit;
import com.dashboard.development_activity_dashboard.model.PullRequest;
import com.dashboard.development_activity_dashboard.model.RepositoryInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GitHubService {

    private final RestTemplate restTemplate;

    public GitHubService() {
        this.restTemplate = new RestTemplate();
    }

    @Value("${github.api.base.url}")
    private String githubApiBaseUrl;

    @Value("${github.api.token}")
    private String githubApiToken;

    public List<RepositoryInfo> getAllGitHubRepositories(String owner) throws Exception {
        String url = githubApiBaseUrl + "/users/" + owner + "/repos";

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(githubApiToken);

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );

            JSONArray reposArray = new JSONArray(responseEntity.getBody());
            List<RepositoryInfo> reposList = new ArrayList<>();

            for (int i = 0; i < reposArray.length(); i++) {
                JSONObject jsonObject = reposArray.getJSONObject(i);
                Long id = jsonObject.getLong("id");
                String name = jsonObject.getString("name");
                String repoUrl = jsonObject.getString("html_url");

                RepositoryInfo repositoryInfo = new RepositoryInfo();
                repositoryInfo.setId(id);
                repositoryInfo.setName(name);
                repositoryInfo.setUrl(repoUrl);

                reposList.add(repositoryInfo);
            }
            return reposList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Commit> getCommits(String owner, String repo) throws Exception {
        String url = githubApiBaseUrl + "/repos/" + owner + "/" + repo + "/commits";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(githubApiToken);

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );

            JSONArray commitsArray = new JSONArray(responseEntity.getBody());
            List<Commit> commitList = new ArrayList<>();


            for (int i = 0; i < commitsArray.length(); i++) {
                JSONObject jsonObject = commitsArray.getJSONObject(i);
                String sha = jsonObject.getString("sha");
                String author = jsonObject.getJSONObject("commit").getJSONObject("author").getString("name");
                String message = jsonObject.getJSONObject("commit").getString("message");
                String commitUrl = jsonObject.getString("html_url");
                String timestamp = jsonObject.getJSONObject("commit").getJSONObject("author").getString("date");

                Commit commit = new Commit();
                commit.setSha(sha);
                commit.setAuthor(author);
                commit.setMessage(message);
                commit.setUrl(commitUrl);
                commit.setTimestamp(timestamp);
                commit.setRepository(owner + "/" +repo);

                commitList.add(commit);
            }
            return commitList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<PullRequest> getPullRequests(String owner, String repo) throws Exception {
        String url = githubApiBaseUrl + "/repos/" + owner + "/" + repo + "/pulls";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(githubApiToken);

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );

            JSONArray pullsArray = new JSONArray(Objects.requireNonNull(responseEntity.getBody()));
            List<PullRequest> pullRequestList = new ArrayList<>();

            for (int i = 0; i < pullsArray.length(); i++) {
                JSONObject jsonObject = pullsArray.getJSONObject(i);

                Long id = jsonObject.getLong("id");
                String title = jsonObject.getString("title");
                String user = jsonObject.getJSONObject("user").getString("login");
                String createdAt = jsonObject.getString("created_at");

                PullRequest pullRequest = new PullRequest();
                pullRequest.setId(id);
                pullRequest.setTitle(title);
                pullRequest.setUser(user);
                pullRequest.setCreatedAt(createdAt);

                pullRequestList.add(pullRequest);
            }

            return pullRequestList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Comment> getComments(String owner, String repo) throws Exception {
        String url = githubApiBaseUrl + "/repos/" + owner + "/" + repo + "/comments";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(githubApiToken);

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );

            JSONArray commentsArray = new JSONArray(Objects.requireNonNull(responseEntity.getBody()));
            List<Comment> commentList = new ArrayList<>();

            for (int i = 0; i < commentsArray.length(); i++) {
                JSONObject jsonObject = commentsArray.getJSONObject(i);

                Long id = jsonObject.getLong("id");
                String user = jsonObject.getJSONObject("user").getString("login");
                String comment = jsonObject.getString("body");
                String createdAt = jsonObject.getString("created_at");

                Comment commentObj = new Comment();
                commentObj.setId(id);
                commentObj.setUser(user);
                commentObj.setComment(comment);
                commentObj.setCreatedAt(createdAt);

                commentList.add(commentObj);
            }

            return commentList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
