package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.metadata.WorkflowService;
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
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shuai Wang on 11/15/15.
 */
public class WorkflowController extends Controller {

    final static Form<WorkflowService> workflowServiceForm = Form
            .form(WorkflowService.class);

    public static Result newWorkflow() {
        return ok(newWorkflow.render(workflowServiceForm));
    }

    public static Result createNewWorkflow() {

        Form<WorkflowService> dc = workflowServiceForm.bindFromRequest();
        ObjectNode jsonData = Json.newObject();

        try {

			String originalWorkflowServiceName = dc.field("Name").value();
			String newWorkflowServiceName = originalWorkflowServiceName.replace(' ', '-');

			if (newWorkflowServiceName != null && !newWorkflowServiceName.isEmpty()) {
				jsonData.put("name", newWorkflowServiceName);
			}

			jsonData.put("description", dc.field("Description").value());

            Http.MultipartFormData dataBody = request().body().asMultipartFormData();
            File imageFile = dataBody.getFile("Image_Preview").getFile();
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
            jsonData.put("image", new String(Base64.encodeBase64(imageBytes)));

            jsonData.put("previewImage", dc.field("Url").value());

            jsonData.put("tasks", dc.field("Tasks").value());

            jsonData.put("inputs", dc.field("Input").value());
            jsonData.put("outputs", dc.field("Output").value());
            jsonData.put("contributors", dc.field("Contributors").value());
            jsonData.put("tags", dc.field("Tags").value());
            jsonData.put("links", dc.field("Links").value());
            jsonData.put("instructions", dc.field("Instructions").value());
            jsonData.put("datasets", dc.field("Datasets").value());
            jsonData.put("attributeWorkflow", dc.field("AttributeWorkflow").value());


			JsonNode response = WorkflowService.create(jsonData);
			Application.flashMsg(response);

		} catch (IllegalStateException e) {
			e.printStackTrace();
			Application.flashMsg(APICall
					.createResponse(ResponseType.CONVERSIONERROR));
		} catch (Exception e) {
			e.printStackTrace();
			Application.flashMsg(APICall.createResponse(ResponseType.UNKNOWN));
		}

		return redirect("/workflowHome/newWorkflow");
    }

    public static Result viewAllWorkflows() {
//        List<WorkflowService> allWfServices = WorkflowService.all();
//        return ok(allWorkflows.render(allWfServices));

        List<WorkflowService> allWfServices = new ArrayList<>();

        WorkflowService wf1 = new WorkflowService();
        wf1.setName("first one");
        wf1.setDescription("This is the first one, good!");
        wf1.setUrl("http://www.kissyourworld.com/wp-content/uploads/2015/09/Happy-Day.jpg");
        wf1.setInput("first input");
        wf1.setOutput("first output");
        wf1.setContributors("many many");
        wf1.setTags("Start");
        wf1.setLinks("http://www.google.com");
        wf1.setInstructions("Try it");
        allWfServices.add(wf1);


        WorkflowService wf2 = new WorkflowService();
        wf2.setName("second one");
        wf2.setDescription("This is the second one, come on!");
        wf2.setUrl("threeDimVarVertical.html");
        wf2.setInput("second input");
        wf2.setOutput("second output");
        wf2.setContributors("very few");
        wf2.setTags("Continue");
        wf2.setLinks("http://www.cmu.edu");
        wf2.setInstructions("Keep going");
        allWfServices.add(wf2);


        return ok(allWorkflows.render(allWfServices));
    }


    public static Result downloadWorkflowService() {
        List<WorkflowService> user = WorkflowService.all();
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("user.json");

        try {
            mapper.writeValue(file, user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        response().setContentType("application/x-download");
        response().setHeader("Content-disposition",
                "attachment; filename=user.json");
        return ok(file);
    }

    public static Result oneWorkflowService(String url) {
        return ok(oneWorkflow.render("/assets/html/" + url));
    }


}
