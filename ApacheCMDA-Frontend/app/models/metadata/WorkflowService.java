package models.metadata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import play.libs.Json;
import util.Constants;
import util.APICall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 12/2/15.
 */
public class WorkflowService {

    private String wfId;
    private String name;
    private String description;

    private byte[] image;
    private String url;

    private String tasks;
    private String input;
    private String output;
    private String contributors;
    private String tags;
    private String links;
    private String instructions;
    private List<String> datasets;
    private List<String> otherWorkflows;

    private String photo;


    private static final String ADD_WORKFLOW_CALL = Constants.NEW_BACKEND+"/workflow/addWorkflow";
    private static final String ADD_ATTRIBUTEWORKFLOW_CALL = Constants.NEW_BACKEND + "/workflow/addAttributeWorkflowToWorkflow";
    private static final String ADD_TASK_CALL = Constants.NEW_BACKEND + "/task/addTask";


    private static final String GET_WORKFLOWS_CALL = Constants.NEW_BACKEND+"/workflow/getAllWorkflows";

    public static JsonNode create(JsonNode jsonData) {

        //add workflow, get ID
        JsonNode addResponse = APICall.postAPI(ADD_WORKFLOW_CALL, jsonData);
        String workflowId = addResponse.get("workflowId").asText();

        //add attribute workflow, get all information of it
        JsonNode attWfResponse = APICall.postAPI(ADD_ATTRIBUTEWORKFLOW_CALL, jsonData);
        attWfResponse.path("attributeWorkflows");


        ObjectNode taskJson = Json.newObject();
        String[] taskDes = taskJson.path("tasks").asText().split(":");
        taskJson.put("name", taskDes[0]);
        taskJson.put("description", taskDes[1]);
        JsonNode addTaskResponse = APICall.postAPI(ADD_TASK_CALL, taskJson);
        String taskId = addTaskResponse.path("taskId").asText();








        return null;
    }


    public static List<WorkflowService> all() {
        List<WorkflowService> allWorkflows = new ArrayList<>();
        JsonNode node = APICall.callAPI(GET_WORKFLOWS_CALL);

        if (node == null || node.has("error") || !node.isArray()) {
            return allWorkflows;
        }

        for (int i = 0; i < node.size(); i++) {
            JsonNode json = node.path(i);
            WorkflowService wfs = new WorkflowService();

            wfs.setName(json.get("name").asText());
            wfs.setDescription(json.path("description").asText());
            wfs.setUrl(json.get("url").asText());
            wfs.setTasks(json.path("tasks").asText());

            wfs.setInput(json.path("input").asText());
            wfs.setOutput(json.path("output").asText());
            wfs.setContributors(json.path("contributors").asText());
            wfs.setTags(json.path("tags").asText());
            wfs.setLinks(json.path("links").asText());
            wfs.setInstructions(json.path("instructions").asText());

            allWorkflows.add(wfs);
        }

        return allWorkflows;
    }



    // getters and setters

    public String getWfId() {
        return wfId;
    }

    public void setWfId(String wfId) {
        this.wfId = wfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getContributors() {
        return contributors;
    }

    public void setContributors(String contributors) {
        this.contributors = contributors;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<String> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<String> datasets) {
        this.datasets = datasets;
    }

    public List<String> getOtherWorkflows() {
        return otherWorkflows;
    }

    public void setOtherWorkflows(List<String> otherWorkflows) {
        this.otherWorkflows = otherWorkflows;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        setPhoto();
    }




    public void setPhoto(){
        if(url.contains("threeDimVarVertical.html")){
            photo = "/assets/images/3DVerticalProfile.jpeg";
        }else if(url.contains("twoDimZonalMean.html")){
            photo = "http://einstein.sv.cmu.edu:9002/static/twoDimZonalMean/65778f88e3e057738423aa7183f5ee54/nasa_modis_clt_200401_200412_Annual.jpeg";
        }else if(url.contains("twoDimMap.html")){
            photo = "http://einstein.sv.cmu.edu:9002/static/twoDimMap/6879a2eedd1910f4c45e6213d342e066/nasa_modis_clt_200401_200412_Annual.jpeg";
        }else if(url.contains("twoDimSlice3D.html")){
            photo = "http://einstein.sv.cmu.edu:9002/static/twoDimSlice3D/ba6b08d54048d9c8349185d2606d3638/nasa_airs_ta_200401_200412_Annual.jpeg";
        }else if(url.contains("scatterPlot2Vars.html")){
            photo = "/assets/images/ScatterPlot.png";
        }else if(url.contains("conditionalSampling.html")){
            photo = "/assets/images/ConditionalSampling1Variable.jpeg";
        }else if(url.contains("twoDimTimeSeries.html")){
            photo = "/assets/images/TimeSeriesPlot.jpeg";
        }else if(url.contains("threeDimZonalMean.html")){
            photo = "http://einstein.sv.cmu.edu:9002/static/threeDimZonalMean/e4e120045d2bb589eed371e1ca08fd99/nasa_airs_ta_200401_200412_Annual.jpeg";
        }else if(url.contains("diffPlot2Vars.html")){
            photo = "/assets/images/DifferencePlot.png";
        }else if (url.contains("regridAndDownload.html")) {
            photo = "/assets/images/regrid.jpg";
        }else if (url.contains("correlationMap.html")) {
            photo = "/assets/images/correlationMap.png";
        }else if (url.contains("conditionalSampling2Var.html")) {
            photo = "/assets/images/conditionalSampling2Variables.jpeg";
        }
        else{
            photo = "http://www.kissyourworld.com/wp-content/uploads/2015/09/Happy-Day.jpg";
        }
    }
    public String getPhoto() {
        return photo;
    }
}
