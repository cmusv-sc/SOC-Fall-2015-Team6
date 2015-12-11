package controllers;

import controllers.Application;
import models.metadata.GroupService;
import models.metadata.SearchService;
import models.metadata.WorkflowService;
import play.mvc.*;
import util.APICall;
import util.APICall.ResponseType;
import views.html.*;

import play.data.*;
import play.libs.Json;
import play.*;
import static play.data.Form.*;

import models.*;
import models.metadata.UserService;


/**
 * Created by Shuai Wang on 11/20/15.
 */
public class ForumController extends Controller {
    public static Result startDiscuss() {
        return ok(forumDiscuss.render(WorkflowService.all(), Post.all()));
    }

    public static Result search(String tag) {
        return ok(forumDiscuss.render(SearchService.searchGetWorkflows(tag), Post.all()));
    }
}
