package model;

/**
 *
 * @author alysson
 */
abstract class Person implements java.io.Serializable{
    
    private int entityId;
    
    private String firstname;
    
    private String lastname;
    
    private String email;
    
    private String password;

    public Person() {
        this.entityId = 0;
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.password = "";
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
