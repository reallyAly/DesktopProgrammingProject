package model;

import model.Person;

/**
 *
 * @author alysson
 */
public class Student extends Person {
    
    public static final String FILENAME = "Students";

    private String RA;

    public Student() {
        this.RA = "";
    }

    public String getRA(){
        return this.RA;
    }

    public void setRA(String RA){
        this.RA = RA;
    }
}
