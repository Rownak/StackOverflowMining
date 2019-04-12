/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.util.ArrayList;

/**
 *
 * @author AFRownak
 */
public class User {
    
    private String name;
    private int reputation;
    private String memberSince;
    private ArrayList<Tag> tagList ;
    private int id;

    

  
    
    public User(){
        name=null;
        reputation = 0;
        memberSince = null;
        tagList = new ArrayList<Tag>();
    }
    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }
    public ArrayList<Tag> getTagList() {
        return tagList;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public String getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }
}