package controller;

import model.Student;
import model.Librarian;
import dao.LibrarianDAO;
import dao.StudentDAO;

/**
 *
 * @author alysson
 */
public class CreateAccountController {

    private StudentDAO studentDAO;
    
    private LibrarianDAO librarianDAO;

    private Student student;

    private Librarian librarian;
    
    private ValidationsController validationController;

    public CreateAccountController() {
        this.studentDAO = new StudentDAO();
        this.librarianDAO = new LibrarianDAO();
        this.student = new Student();
        this.librarian = new Librarian();
        this.validationController = new ValidationsController();
    }

    public boolean createUser(
            String firstname, 
            String lastname, 
            String email, 
            String password, 
            String RA
    ) throws Exception {
        
        // Validations
        this.validationController.validateFields(firstname, lastname, email, password, RA);
        this.validationController.checkIfEmailIsAvailable(email);
        this.validationController.checkIfRAIsAvailable(RA);

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
        this.validationController.validateFields(firstname, lastname, email, password, "00000");
        this.validationController.checkIfEmailIsAvailable(email);
        
        this.librarian.setFirstname(firstname);
        this.librarian.setLastname(lastname);
        this.librarian.setEmail(email);
        this.librarian.setPassword(password);
        
        return this.librarianDAO.save(this.librarian);

    }
}
