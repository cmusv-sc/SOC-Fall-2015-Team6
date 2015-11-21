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

public class Group {
    public String Title;
    public String subtitle;

    public ArrayList friends;
    

    public Group(String title, String subt, ArrayList friends) {
        this.Title=title;
        this.subtitle=subt;
        this.friends = friends;
    }
    
    
    /**
     * TEST SET
     */
    static ArrayList<Friend> list1=new ArrayList<Friend>();
    static ArrayList<Friend> list2=new ArrayList<Friend>();
    static ArrayList<Friend> list3=new ArrayList<Friend>();
    
    

    
    
    public static List<Group> all() {
    	/**
         * TEST SET
         */
    	Friend wo = new Friend("haonanli");
        Friend wo1 = new Friend("xiaozhu");
        Friend wo2= new Friend("junqiang");
        Friend wo3 = new Friend("shuaiwang");
    	list1.add(wo);
    	list2.add(wo1);
    	list3.add(wo2);
    	list3.add(wo3);
        
        List<Group> groups = new ArrayList<Group>();
        groups.add(new Group("Eating", "indian shit,chinese shit", list1));
        groups.add(new Group("Playing", "Dota,LOL",list2));
        groups.add(new Group("Sleeping","wangshuai,junqiang", list3));
        return groups;
    }

    public String getTitle() {
        return Title;
    }

    public String getSubTitle() {
        return subtitle;
    }

    public ArrayList getFriends() {
        return friends;
    }
}

