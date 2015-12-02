
package models;

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

