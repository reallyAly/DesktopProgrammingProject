package model;

/**
 *
 * @author alysson
 */
public class Student extends Person {
    
    public static final String COLUMN_ENTITY_ID = "entity_id";
    
    public static final String COLUMN_FIRSTNAME = "firstname";
    
    public static final String COLUMN_LASTNAME = "lastname";
    
    public static final String COLUMN_EMAIL = "email";
    
    public static final String COLUMN_PASSWORD = "pass";
    
    public static final String COLUMN_RA = "ra";
   
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
