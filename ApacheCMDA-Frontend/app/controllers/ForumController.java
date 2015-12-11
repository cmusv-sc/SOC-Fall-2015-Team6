package controllers;

import controllers.Application;
import play.mvc.*;
import util.APICall;
import util.APICall.ResponseType;
import views.html.*;
import models.metadata.WorkflowService;
import play.data.*;
import play.libs.Json;
import play.*;
import static play.data.Form.*;

import models.*;
import models.metadata.UserService;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import util.Constants;

/**
 * Created by Shuai Wang on 11/20/15.
 */
public class ForumController extends Controller {

    final static Form<PO> postForm = Form.form(PO.class);

    public static Result startDiscuss(String wfname) {
        ObjectNode jsonData = Json.newObject();
        List<NewPost> postList = new ArrayList<NewPost>();

        String GET_POST_CALL=Constants.NEW_BACKEND+"post/getPostsUnderDomainAndDomainName";
        try{
            jsonData.put("domain", "workflow");
            jsonData.put("domainName", wfname);
            JsonNode postResponse = APICall.postAPI(GET_POST_CALL, jsonData).get("posts");

            for(int i = 0; i < postResponse.size(); i++) {
                Long id = Long.parseLong(postResponse.path(i).get("postId").asText());
                String title=postResponse.path(i).get("title").asText();
                String content=postResponse.path(i).get("content").asText();
                NewPost np=new NewPost(id,title,content);
                postList.add(np);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return ok(forumDiscuss.render(postList));
    }

    public static Result toSingle2(long Id){

        //get rplies with post id
        ObjectNode jsonData = Json.newObject();
        List<NewReply> replyList = new ArrayList<NewReply>();
        String GET_REPLY_CALL = Constants.NEW_BACKEND + "post/getPost";
        try{
            jsonData.put("postId", Id);
            JsonNode replyResponse = APICall.postAPI(GET_REPLY_CALL, jsonData).get("replies");
            for(int i = 0; i < replyResponse.size(); i++) {
                String content = replyResponse.path(i).get("content").asText();
                replyList.add(new NewReply(content));
            }

            //return ok(forumsingle2.render(replyList));
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(">forum id: " + Id);
        return ok(forumsingle2.render(postForm, replyList, Id));

    }


    public static class PO {

        public String content;
        public PO(){}

        public PO(String content){
            this.content=content;
        }
        public String getContent(){
            return content;
        }

        public void setContent(String content){
            this.content=content;
        }
    }

    private static long iidd;

    public static Result createNewReplyProxy(long id) {
        iidd = id;

        return badRequest();
    }


    public static Result createNewReply(){
        Form<PO> nu = postForm.bindFromRequest();
        ObjectNode jsonData = Json.newObject();
        ObjectNode jsonData2 = Json.newObject();
        long postid=0;
        String ADD_REPLY=Constants.NEW_BACKEND+"reply/addReply";
        String ADD_REPLY_TO_POST=Constants.NEW_BACKEND+"reply/addReplyToPost";

        try{
            String content = nu.get().getContent();
            jsonData.put("content", content);

            JsonNode replyResponse = APICall.postAPI(ADD_REPLY, jsonData);
            long replyid=Long.parseLong(replyResponse.get("replyId").asText());
            //add reply to post
            jsonData2.put("replyId", replyid);
            jsonData2.put("postId", iidd);
            postid=iidd;
            JsonNode replyResponse2 = APICall.postAPI(ADD_REPLY_TO_POST, jsonData2);
            postid=Long.parseLong(replyResponse2.get("postId").asText());

        }catch(Exception e){
            e.printStackTrace();
        }
        return redirect(
                routes.ForumController.toSingle2(postid)
        );
    }
}
