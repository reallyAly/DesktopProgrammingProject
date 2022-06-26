/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LibrarianDAO;
import model.Librarian;


/**
 *
 * @author alysson
 */

public class UpdateLibrarianController {
    
    private LibrarianDAO librarianDAO;
    
    private ValidationsController validationsController;
    
    public UpdateLibrarianController() {
        this.librarianDAO = new LibrarianDAO();
        this.validationsController = new ValidationsController();
    }
    
    public boolean updateLibrarian(
            int studentId,
            String firstname, 
            String lastname, 
            String email, 
            String password
    ) throws Exception {
       
       Librarian librarian = this.librarianDAO.findById(studentId);
       
       if(!librarian.getEmail().equals(email)) {
           this.validationsController.checkIfEmailIsAvailable(email);
       }
       
       this.validationsController.validateFields(firstname, lastname, email, password, "00000");
       
       librarian.setFirstname(firstname);
       librarian.setLastname(lastname);
       librarian.setEmail(email);
       librarian.setPassword(password);

       boolean result = this.librarianDAO.save(librarian);
       
       return result;
       
    }
    
}
