/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm;

import bean.ResultSet;
import bean.Tag;
import bean.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author AFRownak
 */
public class UsingKnn {
    
    private User inputUser ;
    private ArrayList<User> trainUserList = null;

    public User getInputUser() {
        return inputUser;
    }

    public void setInputUser(User inputUser) {
        this.inputUser = inputUser;
    }

    public ArrayList<User> getUserList() {
        return trainUserList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.trainUserList = userList;
    }
    
    
    public ArrayList<ResultSet> calculateDistance(User inputUser){
        setInputUser(inputUser);
        ArrayList<ResultSet> resultSetList = new ArrayList<ResultSet>();
        for(int i=0; i< trainUserList.size(); i++){
            ArrayList<Tag> trainTagList = trainUserList.get(i).getTagList();
            ArrayList<Tag> inputTagList = inputUser.getTagList();
            double sumOfDiffSqrt = 0.0; 
            int count =0;
            for(int j=0; j<inputUser.getTagList().size(); j++){
                for(int k=0; k<trainTagList.size(); k++){
                    if(inputTagList.get(j).getName().equalsIgnoreCase(trainTagList.get(k).getName())){
                        double difference=inputTagList.get(j).getQuesPoint() - trainTagList.get(k).getQuesPoint();
                        sumOfDiffSqrt += Math.pow(difference, 2);
                        count++;
                    }
                }
            }
            if(count<4){
                continue;
            }
            else{
                double distance = sumOfDiffSqrt/count;
            ResultSet resultSet = new ResultSet();
            resultSet.setConnectedUser(trainUserList.get(i));
            distance = distance*10000;
            Double d = new Double(distance);
            resultSet.setDistance(d.intValue());
            resultSetList.add(resultSet);
            }
        }
        Collections.sort(resultSetList, new UserComparator());
        for(int i=0; i< resultSetList.size(); i++){
            System.out.println("User Name: " +resultSetList.get(i).getConnectedUser().getName());
            System.out.println("Distance: " +resultSetList.get(i).getDistance());
        }
        return resultSetList;
    }
    public class UserComparator implements Comparator<ResultSet> {
    @Override
    public int compare(ResultSet r1, ResultSet r2) {
        return r1.getDistance().compareTo(r2.getDistance());
    }
}
}


