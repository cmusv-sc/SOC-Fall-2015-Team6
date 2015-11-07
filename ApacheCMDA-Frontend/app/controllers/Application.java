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
		
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		}
		
		session().clear();
		session("email", loginForm.get().email);
		session("password", loginForm.get().password);
		
		return redirect(
				routes.Application.workflowHome()
				);
	}

	public static Result workflowHome() {
		if(session().isEmpty()) {
			return ok(login.render(loginForm));
		}
	
		user = new User(session().get("email"), session().get("password"));
		//call backend, get user all information.
		
		return ok(workflow_home.render(user));
	}

	
	
	
	
	
	
//	public static void sessionMsg(JsonNode jsonNode) {
//		session().clear();
//		Iterator<Entry<String, JsonNode>> it = jsonNode.fields();
//		while (it.hasNext()) {
//			Entry<String, JsonNode> field = it.next();
//			session(field.getKey(), field.getValue().asText());
//		}
//	}
//	
	public static void flashMsg(JsonNode jsonNode) {
		Iterator<Entry<String, JsonNode>> it = jsonNode.fields();
		while (it.hasNext()) {
			Entry<String, JsonNode> field = it.next();
			flash(field.getKey(), field.getValue().asText());
		}
	}
}
