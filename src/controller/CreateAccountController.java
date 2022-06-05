package controller;

import model.Student;
import model.Librarian;
import dao.LibrarianDAO;
import dao.StudentDAO;
import java.util.ArrayList;
import model.Filter;

/**
 *
 * @author alysson
 */
public class CreateAccountController {

    private StudentDAO studentDAO;
    
    private LibrarianDAO librarianDAO;

    private Student student;

    private Librarian librarian;

    public CreateAccountController() {
        this.studentDAO = new StudentDAO();
        this.librarianDAO = new LibrarianDAO();
        this.student = new Student();
        this.librarian = new Librarian();
    }

    public boolean createUser(
            String firstname, 
            String lastname, 
            String email, 
            String password, 
            String RA
    ) throws Exception {
        // Validations
        this.validateFields(firstname, lastname, email, password, RA);
        this.checkIfEmailIsAvailable(email);
        this.checkIfRAIsAvailable(RA);

        this.student.setFirstname(firstname);
        this.student.setLastname(lastname);
        this.student.setEmail(email);
        this.student.setPassword(password);
        this.student.setRA(RA);
        
        return this.studentDAO.save(this.student);
    }

    public boolean createUser(
            String firstname, 
            String lastname, 
            String email, 
            String password
    ) throws Exception {
        
        // Validations
        this.validateFields(firstname, lastname, email, password, "00000");
        this.checkIfEmailIsAvailable(email);
        
        this.librarian.setFirstname(firstname);
        this.librarian.setLastname(lastname);
        this.librarian.setEmail(email);
        this.librarian.setPassword(password);
        
        return this.librarianDAO.save(this.librarian);

    }
    
    private void validateFields(
            String firstname, 
            String lastname, 
            String email, 
            String password, 
            String RA
    ) throws IllegalArgumentException{
        
        if(firstname.isBlank()){
            
            throw new IllegalArgumentException(
                    "The firstname field cannot be empty"
            );
            
        }else if(lastname.isBlank()){
            
            throw new IllegalArgumentException(
                    "The lastname field cannot be empty"
            );
            
        }else if(email.isBlank()){
            
            throw new IllegalArgumentException(
                    "The email field cannot be empty"
            );
            
        }else if(password.isBlank()){
            
            throw new IllegalArgumentException(
                    "The password field cannot be empty"
            );
            
        }else if(RA.isBlank()){
            
            throw new IllegalArgumentException(
                    "The RA field cannot be empty"
            );
            
        }
        
    }
    
    private void checkIfEmailIsAvailable(String email) throws IllegalArgumentException{
        
        Filter filter = new Filter("email", email);
        
        ArrayList<Student> students = this.studentDAO.get(filter);
        
        ArrayList<Librarian> librarians = this.librarianDAO.get(filter);
        
        if(!students.isEmpty()) {
            throw new IllegalArgumentException(
                "The email was registered previously, try another"
            );
        }
        
        if(!librarians.isEmpty()) {
            throw new IllegalArgumentException(
                "The email was registered previously, try another"
            );
        }
        
    }
    
    private void checkIfRAIsAvailable(String RA) throws IllegalArgumentException {
           
          Filter filter = new Filter("RA", RA);
          
          ArrayList<Student> students = this.studentDAO.get(filter);
          
          
          if(!students.isEmpty()) {
             throw new IllegalArgumentException(
                "The RA was registered previously, try another"
            ); 
          }
    }
}
