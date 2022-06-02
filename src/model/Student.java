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
    
    public Student(
            int entityId, 
            String firstname, 
            String lastname, 
            String email, 
            String password,
            String RA
    ) {
        super(entityId, firstname, lastname, email, password);
        this.RA = RA;
    }

    public String getRA(){
        return this.RA;
    }

    public void setRA(String RA){
        this.RA = RA;
    }
}
