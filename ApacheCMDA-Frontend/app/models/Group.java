
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

    public ArrayList<Friend> friends;

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
        Friend wo = new Friend("haonanli", 11);
        Friend wo1 = new Friend("xiaozhu", 12);
        Friend wo2= new Friend("junqiang", 13);
        Friend wo3 = new Friend("shuaiwang", 14);
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

    public ArrayList<Friend> getFriends() {
        return friends;
    }
}

