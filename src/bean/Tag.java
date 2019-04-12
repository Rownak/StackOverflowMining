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
public class Tag{
        
        private String name ;
        private String numberOfQues ;
        private double quesPoint;

    public double getQuesPoint() {
        return quesPoint;
    }

    public void setQuesPoint(double quesPoint) {
        this.quesPoint = quesPoint;
    }
        
        public Tag(){
            
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumberOfQues() {
            return numberOfQues;
        }

        public void setNumberOfQues(String numberOfQues) {
            this.numberOfQues = numberOfQues;
        }
        
}