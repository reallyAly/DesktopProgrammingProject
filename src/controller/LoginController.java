/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.file.FileLibrarianController;
import controller.file.FileStudentController;
import java.util.ArrayList;
import model.Librarian;
import model.Student;

/**
 *
 * @author alysson
 */
public class LoginController {
    
    private FileStudentController fileStudentController;

    private FileLibrarianController fileLibrarianController;
    
    public LoginController(){
        this.fileLibrarianController = new FileLibrarianController();
        this.fileStudentController = new FileStudentController();
    }
    
    public int login(String email, String password) throws IllegalAccessException{
        
        Student stud = this.fileStudentController.findStudentByEmail(email);
        
        if(stud != null){
            if (this.validatePassword(stud, password)) {
                return stud.getEntityId();
            }
        }
             
        Librarian lib = this.fileLibrarianController.findLibrarianByEmail(email);

        if(lib != null){
            if (this.validatePassword(lib, password)) {
                return lib.getEntityId();
            }
        }

        throw new IllegalAccessException("Email or Password may be wrong, please try again");

    }
    
    private boolean validatePassword(Student student, String password){
        return student.getPassword().equals(password);
    }
    
    private boolean validatePassword(Librarian librarian, String password){
        return librarian.getPassword().equals(password);
    }
   
}
