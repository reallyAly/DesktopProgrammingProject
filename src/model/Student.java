package model;

/**
 *
 * @author alysson
 */
public class Student extends Person {
    
    public static final String TABLE_NAME = "student";
    
    public static final String COLUMN_RA = "ra";
   
    private String RA;

    public Student() {
        super();
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
