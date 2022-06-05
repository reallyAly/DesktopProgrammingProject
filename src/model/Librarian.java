package model;

/**
 *
 * @author alysson
 */
public class Librarian extends Person{
    
    public static final String TABLE_NAME = "librarian";
   
    public Librarian() {
        super();
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
