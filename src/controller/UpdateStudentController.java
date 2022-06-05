/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.StudentDAO;
import model.Student;


/**
 *
 * @author alysson
 */
public class UpdateStudentController {
    
    private StudentDAO studentDAO;
    
    private ValidationsController validationsController;
    
    public UpdateStudentController() {
        this.studentDAO = new StudentDAO();
        this.validationsController = new ValidationsController();
    }
    
    public boolean updateStudent(
            int studentId,
            String firstname, 
            String lastname, 
            String email, 
            String password, 
            String RA
    ) throws Exception {
       
       Student student = this.studentDAO.findById(studentId);
       
       if(!student.getEmail().equals(email)) {
           this.validationsController.checkIfEmailIsAvailable(email);
       }
       
       if(!student.getRA().equals(RA)) {
           this.validationsController.checkIfRAIsAvailable(RA);
       }
       
       this.validationsController.validateFields(firstname, lastname, email, password, RA);
       
       student.setFirstname(firstname);
       student.setLastname(lastname);
       student.setEmail(email);
       student.setPassword(password);
       student.setRA(RA);

       boolean result = this.studentDAO.save(student);
       
       return result;
       
    }
    
}
