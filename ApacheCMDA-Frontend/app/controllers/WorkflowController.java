package controllers;

import views.html.newWorkflow;

import controllers.Application;
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
 * Created by Shuai Wang on 11/15/15.
 */
public class WorkflowController extends Controller {

    public static Result createNewWorkFlow() {
        System.out.println(">>new workflow");

        User user = new User(session().get("email"), session().get("password"));
        System.out.println(">>>email: " + user.getEmail());

        return ok(newWorkflow.render("Come on"));
    }

    public static Result viewAllWorkflows() {
        return ok(allWorkflows.render("come on"));
    }
}
