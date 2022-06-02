package model;

/**
 *
 * @author alysson
 */
public class Librarian extends Person{
   
    public Librarian() {
        
    }
    
    public Librarian(
            int entityId, 
            String firstname, 
            String lastname, 
            String email, 
            String password
    ) {
        super(entityId, firstname, lastname, email, password);
    }
    
}
