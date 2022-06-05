/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.Filter;
import model.Librarian;
import model.Student;
import dao.StudentDAO;
import dao.LibrarianDAO;

/**
 *
 * @author alysson
 */
public class ValidationsController {
    
    private StudentDAO studentDAO;
    
    private LibrarianDAO librarianDAO;
    
    public ValidationsController() {
        this.studentDAO = new StudentDAO();
        this.librarianDAO = new LibrarianDAO();
    }
    
    public void checkIfEmailIsAvailable(String email) throws IllegalArgumentException{
        
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
    
    public void checkIfRAIsAvailable(String RA) throws IllegalArgumentException {
           
          Filter filter = new Filter("RA", RA);
          
          ArrayList<Student> students = this.studentDAO.get(filter);

          if(!students.isEmpty()) {
             throw new IllegalArgumentException(
                "The RA was registered previously, try another"
            ); 
          }
    }
    
    public void validateFields(
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
    
}
