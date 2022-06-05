/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import exception.LibrarianNotExistException;
import exception.StudentNotExistException;
import model.Librarian;
import model.Student;
import dao.LibrarianDAO;
import dao.StudentDAO;
import java.util.ArrayList;
import model.Filter;

/**
 *
 * @author alysson
 */
public class LoginController {
    
    private LibrarianDAO librarianDAO;
    
    private StudentDAO studentDAO;
    
    public LoginController(){
        this.librarianDAO = new LibrarianDAO();
        this.studentDAO = new StudentDAO();
    }
    
    public int login(String email, String password) 
            throws IllegalAccessException, 
            StudentNotExistException, 
            LibrarianNotExistException
    {
        Filter filter = new Filter("email", email);
        
        ArrayList<Student> students = this.studentDAO.get(filter);
        
        if(!students.isEmpty()){
            if (this.validatePassword(students.get(0), password)) {
                return students.get(0).getEntityId();
            }
        }
  
        ArrayList<Librarian> librarians = this.librarianDAO.get(filter);

        if(!librarians.isEmpty()){
            if (this.validatePassword(librarians.get(0), password)) {
                return librarians.get(0).getEntityId();
            }
        }
        
        if(librarians.isEmpty()){
           throw new LibrarianNotExistException("Cannot find a user with this E-mail: "+email); 
        }else if(students.isEmpty()){
           throw new StudentNotExistException("Cannot find a user with this E-mail: "+email);  
        }else{
            throw new IllegalAccessException(
                "Email or Password may be wrong, please try again"
            );
        }
    }
    
    private boolean validatePassword(Student student, String password){
        return student.getPassword().equals(password);
    }
    
    private boolean validatePassword(Librarian librarian, String password){
        return librarian.getPassword().equals(password);
    }
   
}
