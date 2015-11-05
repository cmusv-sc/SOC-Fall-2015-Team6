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

import java.util.Iterator;
import java.util.Map.Entry;
import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.*;
import views.html.*;

import play.data.*;


import play.*;
import static play.data.Form.*;

import models.*;

public class Application extends Controller {
	
	private static User user;
	
	public static Result index() {
        return ok(index.render(""));
    }
 
    
    public static Result login() {
		return ok(
				login.render(form(Login.class))
				);
	}

    public static class Login {
        
        public String email;
        public String password;
        public String userName;
        
        
//        public String validate() {
//            if (User.authenticate(email, password) == null) {
//              return "Invalid user or password";
//            }
//            return null;
//        }
    }
    
    public static Result authenticate() {
    	Form<Login> loginForm = form(Login.class).bindFromRequest();
    	
//    	user.save();
    	user = new User(loginForm.get().email, loginForm.get().password, loginForm.get().userName);
    	
    	
//    	Login login = Form.form(Login.class).bindFromRequest().get();
//    	user = new User(login.email, login.password, login.userName);
    	
//		session().clear();
//		session("email", loginForm.get().email);
//		session("password", loginForm.get().password);
//		session("userName", loginForm.get().userName);
		
//		user = new User(session(result).get("email"), session(result).get("password"), session(result).get("userName"));
//		System.out.println(user.getEmail());
//    	System.out.println(user.getUserName());
//    	System.out.println(user.getPassword());
    	
//    	System.out.println("////////" + request().body().asJson());
    	
//    	if (loginForm.hasErrors()) {
//            return badRequest(login.render(loginForm));
//        } else {
//            session().clear();
//            session("email", loginForm.get().email);
//            return redirect(
//                routes.Application.workflowHome()
//            );
//        }
    	
    	return redirect(
    			routes.Application.workflowHome()
        );
    }
    
    public static Result workflowHome() {
    	return ok(workflow_home.render(user));
    }

    
  
    
    
    public static void flashMsg(JsonNode jsonNode){
		Iterator<Entry<String, JsonNode>> it = jsonNode.fields();
		while (it.hasNext()) {
			Entry<String, JsonNode> field = it.next();
			flash(field.getKey(),field.getValue().asText());	
		}
    }
}
