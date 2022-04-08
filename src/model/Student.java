package model;

import model.Person;

/**
 *
 * @author alysson
 */
public class Student extends Person {
    
    public static final String FILENAME = "Students";

    public Student(String entityId, String firstname, String lastname, String email, String password) {
        super(entityId, firstname, lastname, email, password);
    }
 
}
