package model;

/**
 *
 * @author alysson
 */
abstract class Person {
    
    public static final String COLUMN_ENTITY_ID = "entity_id";
    
    public static final String COLUMN_FIRSTNAME = "firstname";
    
    public static final String COLUMN_LASTNAME = "lastname";
    
    public static final String COLUMN_EMAIL = "email";
    
    public static final String COLUMN_PASSWORD = "pass";
    
    protected int entityId;
    
    protected String firstname;
    
    protected String lastname;
    
    protected String email;
    
    protected String password;

    public Person() {
        this.entityId = 0;
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.password = "";
    }
    
    public Person(
            int entityId, 
            String firstname, 
            String lastname, 
            String email, 
            String password
    ) {
        this.entityId = entityId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
    
    public void setEntityId(int entityId){
           this.entityId = entityId;
    }

    public int getEntityId() {
        return entityId;
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
    
    
}
