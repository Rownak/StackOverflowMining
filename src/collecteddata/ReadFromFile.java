/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collecteddata;

import Algorithm.UsingKnn;
import bean.ResultSet;
import bean.Tag;
import bean.User;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;





/**
 *
 * @author AFRownak
 */
public class ReadFromFile {
    ArrayList<User> userList = new ArrayList<User>();
    ArrayList<ResultSet> resultSet = new ArrayList<ResultSet>();

    public ArrayList<ResultSet> getResultSet() {
        return resultSet;
    }

    public void setResultSet(ArrayList<ResultSet> resultSet) {
        this.resultSet = resultSet;
    }
    
    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    
    public void readJsonData()  {
        
        
        
        JSONParser parser = new JSONParser();
        
 
        try {
 
            Object obj = parser.parse(new FileReader(
                    "NewStackData.txt"));
 
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray userArray = (JSONArray) jsonObject.get("users");
           // System.out.println("Users :"+ userArray.toString());
            
            for(int i=0; i<userArray.size(); i++){
                JSONObject userObj = (JSONObject) userArray.get(i);
                JSONObject userInfo = (JSONObject) userObj.get("info");
                JSONArray tags = (JSONArray) userObj.get("tags");
                
                
                userList.add(creatTheUserObj(userInfo,tags));
                
                for(int j=0; j<userList.get(i).getTagList().size(); j++){
                    System.out.println("    tag name :" + userList.get(i).getTagList().get(j).getName());
                    System.out.println("    tag QuesNum :" + userList.get(i).getTagList().get(j).getNumberOfQues());
                    System.out.println("    tag QuesPoint :" + userList.get(i).getTagList().get(j).getQuesPoint());
                    
                }
                
             //   System.out.println("user " +i +" : "+ user.toString());
                
            }
     
//            JSONArray tags = (JSONArray) userArray.get(1);
//            User user = new User();
//            
//            creatTheUserObj(user,userInfo,tags);
//            System.out.println("userName:" + user.getName() +"User 1st Tag : " + user.getTagList().get(0).getName());
//            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    public  User creatTheUserObj(JSONObject userInfo, JSONArray tags) {
        User user = new User();
     //   user.setId((Integer)userInfo.get("id"));
        user.setMemberSince((String) userInfo.get("membersince"));
        user.setName((String) userInfo.get("name"));
        user.setReputation(Integer.parseInt((String)userInfo.get("reputation")));
        user.setId(Integer.parseInt((String)userInfo.get("id")));
        
        ArrayList<Tag> tagList = new ArrayList<Tag>();
        double totalQues=0;
        for(int j=0; j<tags.size(); j++){
            JSONObject tagObj= (JSONObject) tags.get(j);
             Tag tag = new Tag();
                tag.setName((String) tagObj.get("name"));
                String numOfQues =(String) tagObj.get("numOfQues");
                tag.setNumberOfQues(numOfQues);
                totalQues += Integer.parseInt(numOfQues);
                
                //tag.setNumberOfQues((Integer) tagObj.get("numOfQues"));
                tagList.add(tag);
        }
        
        for(int j=0; j<tagList.size();j++){
            double numOfQues =Integer.parseInt(tagList.get(j).getNumberOfQues());
            System.out.println("numOfQues"+numOfQues);
            System.out.println("total Ques :"+totalQues);
            double quesRatio  = numOfQues/totalQues;
            System.out.println(quesRatio);
            tagList.get(j).setQuesPoint(quesRatio);
        }
        user.setTagList(tagList);
        
        System.out.println("User Name: " +user.getName());
        System.out.println("User ");
        return user;
    }
    
    public ArrayList<String> mainMethodToRun(String searchName) {
        ReadFromFile rff = new ReadFromFile();
        rff.readJsonData();
        UsingKnn usingKnn = new UsingKnn();
        ArrayList<User> trainUserList = rff.getUserList();
        usingKnn.setUserList(trainUserList);
        
//        String search = "blcArmadillo";
        String search = searchName;
        User inputUser = null;
        for(User u : trainUserList){
        if(u.getName() != null && u.getName().contains(search)){
            inputUser = new User();
            inputUser = u;
        }
           
    }
        
        ArrayList<String> linkList = new ArrayList<String>();
        String outputString = "http://stackoverflow.com/users/";
        String linkString = "";
        
        if(inputUser!=null){
            resultSet=usingKnn.calculateDistance(inputUser);
            
            boolean flag = true;

            for(int i=0; i<resultSet.size() && i<4; i++){
                flag = false;
                 linkString = outputString + resultSet.get(i).getConnectedUser().getId() +"/" + resultSet.get(i).getConnectedUser().getName();
                linkList.add(linkString);
            }
            if(flag){
                linkString = "No Data";
                linkList.add(linkString);
            }
        }
        else{
            System.out.println("No Data");
            linkString = "No Data";
            linkList.add(linkString);
        }

        
        return linkList;
    }
    
}
