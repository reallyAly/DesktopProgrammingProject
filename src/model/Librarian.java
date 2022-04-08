package model;

import model.Person;

/**
 *
 * @author alysson
 */
public class Librarian extends Person {
    
    public static final String FILENAME = "Librarians";

    public Librarian(String entityId, String firstname, String lastname, String email, String password) {
        super(entityId, firstname, lastname, email, password);
    }
    
}
