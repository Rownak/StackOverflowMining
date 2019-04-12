/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

/**
 *
 * @author AFRownak
 */
public class ResultSet {
    
    private Integer distance;
    private User connectedUser;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = new Integer(distance);
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }
    
    
    
    
    
}
