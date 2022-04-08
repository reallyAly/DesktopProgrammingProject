package model;

/**
 *
 * @author alysson
 */
abstract class Person {
    
    private String entityId;
    
    private String firstname;
    
    private String lastname;
    
    private String email;
    
    private String password;

    public Person(String entityId, String firstname, String lastname, String email, String password) {
        this.entityId = entityId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getEntityId() {
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

    public void setEntityId(String entityId) {
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
    
    
}
