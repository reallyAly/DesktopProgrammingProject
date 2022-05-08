/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import exception.LibrarianNotExistException;
import exception.StudentNotExistException;
import model.Librarian;
import model.Student;
import model.repository.LibrarianRepository;
import model.repository.StudentRepository;

/**
 *
 * @author alysson
 */
public class LoginController {
    
    private LibrarianRepository librarianRepository;
    
    private StudentRepository studentRepository;
    
    public LoginController(){
        this.librarianRepository = new LibrarianRepository();
        this.studentRepository = new StudentRepository();
    }
    
    public int login(String email, String password) 
            throws IllegalAccessException, 
            StudentNotExistException, 
            LibrarianNotExistException
    {
        Student stud = this.studentRepository.findByEmail(email);
        
        if(stud != null){
            if (this.validatePassword(stud, password)) {
                return stud.getEntityId();
            }
        }
             
        Librarian lib = this.librarianRepository.findByEmail(email);

        if(lib != null){
            if (this.validatePassword(lib, password)) {
                return lib.getEntityId();
            }
        }
        
        if(lib == null){
           throw new LibrarianNotExistException("Cannot find a user with this E-mail: "+email); 
        }else if(stud == null){
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
