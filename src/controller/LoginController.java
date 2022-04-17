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
        
        Student stud = this.findStudentByEmail(email);
        
        if(!stud.getFirstname().isEmpty() && this.validatePassword(stud, password)){
           return stud.getEntityId();
        }
        
        Librarian lib = this.findLibrarianByEmail(email);
        
        if(!lib.getFirstname().isEmpty() && this.validatePassword(lib, password)){
           return lib.getEntityId();
        }

        throw new IllegalAccessException("Password may be wrong, please try again");

    }
    
    private Student findStudentByEmail(String email) throws IllegalAccessException{
        
        ArrayList<Student> studs = this.getStudents();
        
        for(int i = 0; i < studs.size(); i++){
            if(studs.get(i).getEmail().equals(email)){
                return studs.get(i);
            }
        }
        
        throw new IllegalAccessException("User not found");
        
    }
    
    private Librarian findLibrarianByEmail(String email) throws IllegalAccessException{
        
        ArrayList<Librarian> libs = this.getLibrarians();

        for(int i = 0; i < libs.size(); i++){
            if(libs.get(i).getEmail().equals(email)){
                return libs.get(i);
            }
        }
        
        throw new IllegalAccessException("User not found");
        
    }
    
    private boolean validatePassword(Student student, String password){
        return student.getPassword().equals(password);
    }
    
    private boolean validatePassword(Librarian librarian, String password){
        return librarian.getPassword().equals(password);
    }
   
    private ArrayList<Librarian> getLibrarians(){
        
        this.fileLibrarianController.readLibrarian();
        return this.fileLibrarianController.getLibrarians();
        
    }
    
    private ArrayList<Student> getStudents(){
        
        this.fileStudentController.readStudent();
        return this.fileStudentController.getStudents();
        
    }
   
   
}
