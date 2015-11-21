/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

public class Application extends Controller {
	
	final static Form<Login> loginForm = form(Login.class).bindFromRequest();
	final static Form<User> userForm = Form.form(User.class);
	private static User user;

	public static Result index() {
		return ok(index.render(""));
	}

	public static Result login() {
		return ok(login.render(form(Login.class)));
	}

	public static class Login {

		public String email;
		public String password;
		
		public String validate() {
			ObjectNode jsonData = Json.newObject();

			jsonData.put("email", email);
			jsonData.put("password", password);

			JsonNode response =	UserService.verifyUserAuthentity(jsonData);

			if (response == null) {
				return "Invalid user or password.";
			} else {
				return null;
			}
		}	
	}

	public static Result authenticate() {
		/*
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		}


		session().clear();
		session("email", loginForm.get().email);
		session("password", loginForm.get().password);
		*/
		return redirect(
				routes.WorkFlow.workflow()
				);
	}

	public static Result workflowHome() {
		if(session().isEmpty()) {
			return ok(login.render(loginForm));
		}
	
		user = new User(session().get("email"), session().get("password"));
		return ok(workflow_home.render(user));
	}

	//sign up
	public static Result signup() {	
		return ok(signup.render(userForm));
	}
	
	
	public static Result createNewUser(){
    	Form<User> nu = userForm.bindFromRequest();

    	ObjectNode jsonData = Json.newObject();
    	String userName = null;
    	
    	try{
    		userName = nu.field("firstName").value()+" "+(nu.field("middleInitial")).value()
    				+" "+(nu.field("lastName")).value();
    		jsonData.put("userName", userName);
			jsonData.put("firstName", nu.get().getFirstName());
			jsonData.put("middleInitial", nu.get().getMiddleInitial());
			jsonData.put("lastName", nu.get().getLastName());
			jsonData.put("password", nu.get().getPassword());
			jsonData.put("affiliation", nu.get().getAffiliation());
			jsonData.put("title", nu.get().getTitle());
			jsonData.put("email", nu.get().getEmail());
			jsonData.put("mailingAddress", nu.get().getMailingAddress());
			jsonData.put("phoneNumber", nu.get().getPhoneNumber());
			jsonData.put("faxNumber", nu.get().getFaxNumber());
			jsonData.put("researchFields", nu.get().getResearchFields());
			jsonData.put("highestDegree", nu.get().getHighestDegree());
			
			JsonNode response = UserService.verifyUserSUAuthentity(jsonData);

			// flash the response message
			//Application.flashMsg(response);
			return redirect(routes.Application.createSuccess());
			
    		
    	}catch (IllegalStateException e) {
			e.printStackTrace();
			Application.flashMsg(APICall
					.createResponse(ResponseType.CONVERSIONERROR));
		} catch (Exception e) {
			e.printStackTrace();
			Application.flashMsg(APICall
					.createResponse(ResponseType.UNKNOWN));
		}
		return ok(signup.render(nu));  
    }
	
	public static Result createSuccess(){
		return ok(createSuccess.render());
	}
	
	public static void flashMsg(JsonNode jsonNode) {
		Iterator<Entry<String, JsonNode>> it = jsonNode.fields();
		while (it.hasNext()) {
			Entry<String, JsonNode> field = it.next();
			flash(field.getKey(), field.getValue().asText());
		}
	}
}
