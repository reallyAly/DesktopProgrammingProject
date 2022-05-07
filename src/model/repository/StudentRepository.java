/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repository;

import java.util.ArrayList;
import model.Student;
import controller.file.FileBinController;
import exception.StudentNotExistException;

/**
 *
 * @author alysson
 */
public class StudentRepository implements Repository {
    
    private FileBinController fileBinController;
    
    public StudentRepository(){
        this.fileBinController = new FileBinController();
    }
    
    /**
     * Get student by specific id
     *
     * @param id
     * @return Student
     * @throws StudentNotExistException
     */
    @Override
    public Student findById(int id) throws StudentNotExistException{
        ArrayList<Student> students = this.get();
        
        for(Student stud: students){
            if(stud.getEntityId() == id){
                return stud;
            }
        }
        
        throw new StudentNotExistException("Cannot find a Student with the id:"+id);
    }
    
    /**
     * Get all students
     *
     * @return ArrayList
     */
    @Override
    public ArrayList<Student> get(){
        this.fileBinController.setFile(Student.FILENAME);  
        boolean result = this.fileBinController.read();
        
        if(!result){
            return new ArrayList<>();
        }
  
        return (ArrayList<Student>) this.fileBinController.getObject();
    }
    
    /**
     * Save a new student
     * 
     *
     * @param student
     * @return Boolean
     */
    @Override
    public boolean save(Object student) {
        this.fileBinController.addNewObject(student);
        return this.fileBinController.write(false);
    }
    
    /**
     * Get student by specific email
     * 
     *
     * @param email
     * @return
     * @throws StudentNotExistException
     */
    public Student findByEmail(String email) throws StudentNotExistException{
        ArrayList<Student> students = this.get();
        
        for(Student stud: students){
            if(stud.getEmail().equals(email)){
                return stud;
            }
        }
        
        throw new StudentNotExistException("Cannot find a Student with the e-mail:"+email);
    }
    
    
}
