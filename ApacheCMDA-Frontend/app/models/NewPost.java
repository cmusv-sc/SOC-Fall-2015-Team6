package models;

import com.fasterxml.jackson.databind.JsonNode;
import util.APICall;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoxi_000 on 11/19/2015.
 */
public class NewPost {

    private long id;

    private String domain;               // [workflow|service|group]
    private String domainName;           // [workflowName|serviceName|groupName]

    private String title;                // post title
    private String content;              // post content
    private String attachment;           // attachment link of image, pdf or video
    private boolean isQuestion;          // When a user intends to post, she has an option to
                                         // decide whether to mark it as a discussion or a
                                         // question (if it is a question, then the starter of
                                         // the thread will be able to select a later reply as an
                                         // answer (i.e., the “mark as answer” as in Stackoverflow style).
    private int questionableCount;

    private User newUser;

    private List<NewReply> newReplies = new ArrayList<NewReply>();

    public NewPost() {}
    
    public NewPost(long id,String title,String content){
    	 this.title = title;
         this.content = content;
         this.id=id;
    }

    public NewPost(String domain, String domainName, String title, String content, String attachment, boolean isQuestion, int questionableCount) {
        this.domain = domain;
        this.domainName = domainName;
        this.title = title;
        this.content = content;
        this.attachment = attachment;
        this.isQuestion = isQuestion;
        this.questionableCount = questionableCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public boolean isQuestion() {
        return isQuestion;
    }

    public void setQuestion(boolean question) {
        isQuestion = question;
    }

    public int getQuestionableCount() {
        return questionableCount;
    }

    public void setQuestionableCount(int questionableCount) {
        this.questionableCount = questionableCount;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

//    public Set<NewTag> getPostTags() {
//        return postTags;
//    }

//    public void setPostTags(Set<NewTag> postTags) {
//        this.postTags = postTags;
//    }

    public List<NewReply> getNewReplies() {
        return newReplies;
    }

    public void setNewReplies(List<NewReply> newReplies) {
        this.newReplies = newReplies;
    }

   
}