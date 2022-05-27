package model;

/**
 *
 * @author alysson
 */
public class Student extends Person {
   
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
