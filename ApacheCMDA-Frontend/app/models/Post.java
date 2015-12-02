package models;

import com.fasterxml.jackson.databind.JsonNode;
import util.APICall;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoxi_000 on 11/19/2015.
 */
public class Post {
    public String image;
    public String textHead;
    public String text;
    public String[] tags;

    private static final String GET_POSTS_CALL = Constants.NEW_BACKEND+"climateService/getAllPosts/json";

    public Post(String image, String textHead, String text) {
        this.image = image;
        this.textHead = textHead;
        this.text = text;
    }

    public static List<Post> all() {
        List<Post> posts = new ArrayList<Post>();
        String[] testTags = new String[] {"nba", "18655", "data"};
        Post post1 = new Post("http://l2.yimg.com/bt/api/res/1.2/PUaS1RT2fFVeoogSwns8MA--/YXBwaWQ9eW5ld3NfbGVnbztpbD1wbGFuZTtxPTc1O3c9NjAw/http://media.zenfs.com/en/person/Ysports/james-harden-basketball-headshot-photo.jpg", "yes", "Although traditional social media offer a variety of opportunities for companies in a wide range of business sectors, Economic Sector mobile social media makes use of the location- and time-sensitivity aspects of it in order to engage into marketing research, communication, sales promotions/discounts, and relationship development/loyalty programs.");
        post1.setTags(testTags);
        posts.add(post1);

        posts.add(new Post("http://graphics.wsj.com/six-degrees-of-lebron-james/img/LeBron_head.jpg", "yes", "Some social media sites have greater virality - defined as a greater likelihood that users will reshare content posted (by another user) to their social network. Many social media sites provide specific functionality to help users reshare content - for example, Twitter's retweet button, Pinterest pin or Tumblr's reblog function. Businesses may have a particular interest in viral marketing; nonprofit organisations and activists may have similar interests in virality."));
        posts.add(new Post("http://a.espncdn.com/combiner/i?img=/i/teamlogos/leagues/500/nba.png?transparent=true","LOL","People obtain information, education, news, and other data from electronic and print media. Social media are distinct from industrial or traditional media such as newspapers, television, and film as they are comparatively inexpensive and accessible. They enable anyone (even private individuals) to publish or access information. Industrial media generally require significant resources to publish information as in most cases the articles go through many revisions before being published."));
        posts.add(new Post("http://a.espncdn.com/combiner/i?img=/i/teamlogos/leagues/500/nba.png?transparent=true","LOL","People obtain information, education, news, and other data from electronic and print media. Social media are distinct from industrial or traditional media such as newspapers, television, and film as they are comparatively inexpensive and accessible. They enable anyone (even private individuals) to publish or access information. Industrial media generally require significant resources to publish information as in most cases the articles go through many revisions before being published."));
        posts.add(new Post("http://a.espncdn.com/combiner/i?img=/i/teamlogos/leagues/500/nba.png?transparent=true","LOL","People obtain information, education, news, and other data from electronic and print media. Social media are distinct from industrial or traditional media such as newspapers, television, and film as they are comparatively inexpensive and accessible. They enable anyone (even private individuals) to publish or access information. Industrial media generally require significant resources to publish information as in most cases the articles go through many revisions before being published."));
        posts.add(new Post("http://a.espncdn.com/combiner/i?img=/i/teamlogos/leagues/500/nba.png?transparent=true","LOL","People obtain information, education, news, and other data from electronic and print media. Social media are distinct from industrial or traditional media such as newspapers, television, and film as they are comparatively inexpensive and accessible. They enable anyone (even private individuals) to publish or access information. Industrial media generally require significant resources to publish information as in most cases the articles go through many revisions before being published."));
        posts.add(new Post("http://a.espncdn.com/combiner/i?img=/i/teamlogos/leagues/500/nba.png?transparent=true","LOL","People obtain information, education, news, and other data from electronic and print media. Social media are distinct from industrial or traditional media such as newspapers, television, and film as they are comparatively inexpensive and accessible. They enable anyone (even private individuals) to publish or access information. Industrial media generally require significant resources to publish information as in most cases the articles go through many revisions before being published."));
        posts.add(new Post("http://a.espncdn.com/combiner/i?img=/i/teamlogos/leagues/500/nba.png?transparent=true","LOL","People obtain information, education, news, and other data from electronic and print media. Social media are distinct from industrial or traditional media such as newspapers, television, and film as they are comparatively inexpensive and accessible. They enable anyone (even private individuals) to publish or access information. Industrial media generally require significant resources to publish information as in most cases the articles go through many revisions before being published."));

        /*
        JsonNode postsNode = APICall
                .callAPI(GET_POSTS_CALL);

        if (postsNode == null || postsNode.has("error")
                || !postsNode.isArray()) {
            return posts;
        }

        for (int i = 0; i < postsNode.size(); i++) {
            JsonNode json = postsNode.path(i);
            Post post = new Post(json.path("image").asText(),json.path("text").asText(),json.path("title").asText());
            posts.add(post);
        }
        */
        return posts;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public String getTextHead() {
        return textHead;
    }

    public String getText() {
        return text;
    }
}
