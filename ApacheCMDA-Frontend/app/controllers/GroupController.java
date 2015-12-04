package controllers;

import models.Group;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.editUser;
import views.html.groups;


/**
 * Created by Shuai Wang on 11/20/15.
 */
public class GroupController extends Controller {
    public static Result showGroups() {
        return ok(groups.render(Group.all()));
    }
}
