package ai.inmotion.javatest.model;

import lombok.Data;

@Data
public class GithubUser {
    String login;
    String id;
    String node_id;
    String avatar_url;
    String gravatar_id;
    String url;
    String html_url;
    String followers_url;
    String following_url;
    String gists_url;
    String starred_url;
    String subscriptions_url;
    String organizations_url;
    String repos_url;
    String events_url;
    String received_events_url;
    String type;
    boolean site_admin;

        public GithubUser() {
        }

        public GithubUser(String login, String id, String node_id, String avatar_url, String gravatar_id, String url, String html_url, String followers_url, String following_url, String gists_url, String starred_url, String subscriptions_url, String organizations_url, String repos_url, String events_url, String received_events_url, String type, boolean site_admin) {
                this.login = login;
                this.id = id;
                this.node_id = node_id;
                this.avatar_url = avatar_url;
                this.gravatar_id = gravatar_id;
                this.url = url;
                this.html_url = html_url;
                this.followers_url = followers_url;
                this.following_url = following_url;
                this.gists_url = gists_url;
                this.starred_url = starred_url;
                this.subscriptions_url = subscriptions_url;
                this.organizations_url = organizations_url;
                this.repos_url = repos_url;
                this.events_url = events_url;
                this.received_events_url = received_events_url;
                this.type = type;
                this.site_admin = site_admin;
        }
}
