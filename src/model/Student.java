package model;

/**
 *
 * @author alysson
 */
public class Student {
    
    public static final String TABLE_NAME = "student";

    public static final String COLUMN_ENTITY_ID = "entity_id";
    
    public static final String COLUMN_FIRSTNAME = "firstname";
    
    public static final String COLUMN_LASTNAME = "lastname";
    
    public static final String COLUMN_EMAIL = "email";
    
    public static final String COLUMN_PASSWORD = "pass";
    
    public static final String COLUMN_RA = "ra";
    
    protected int entityId;
    
    protected String firstname;
    
    protected String lastname;
    
    protected String email;
    
    protected String password;
   
    private String RA;

    public Student() {
        this.entityId = 0;
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.password = "";
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
        this.entityId = entityId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.RA = RA;
    }

    public String getRA(){
        return this.RA;
    }

    public void setRA(String RA){
        this.RA = RA;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
