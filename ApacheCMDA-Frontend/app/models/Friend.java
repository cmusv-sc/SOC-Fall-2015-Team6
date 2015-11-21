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
package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.JsonNode;

import models.metadata.ClimateService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.data.validation.Constraints;
import util.APICall;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

public class Friend {

	
	private String userName;	
	private String firstName;
	private String lastName;
	private String title;
	private String email;
	private String phoneNumber;

	
	
    public Friend(String username) {
      this.userName=username;
    }
    
	public String getEmail() {
		return email;
	}
    
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Friend() {
	}

	public Friend(String userName,String firstName,
			String lastName, 
			String title, String email, 
			String phoneNumber) {
	
		this.userName = userName;	
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}




	public String getTitle() {
		return title;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}


//
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}