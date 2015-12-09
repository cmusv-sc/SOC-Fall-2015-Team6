package controllers;

import models.Group;
import models.metadata.GroupService;
import models.metadata.WorkflowService;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.editUser;
import views.html.groups;
import views.html.newGroup;
import views.html.newWorkflow;


/**
 * Created by Shuai Wang on 11/20/15.
 */
public class GroupController extends Controller {
    final static Form<GroupService> groupServiceForm = Form
            .form(GroupService.class);

    public static Result showGroups() {
        return ok(groups.render(Group.all()));
    }

    public static Result newGroup() {
        return ok(newGroup.render(groupServiceForm));
    }

    public static Result createNewGroup() {

        Form<GroupService> dc = groupServiceForm.bindFromRequest();
        return redirect("/workflowHome/newWorkflow");
    }
}
