package controller;

import controller.file.FileBinController;
import model.Student;
import model.Librarian;

import java.util.ArrayList;

/**
 *
 * @author alysson
 */
public class CreateAccountController {

    private FileBinController fileBinController;

    private Student student;

    private Librarian librarian;

    public CreateAccountController() {
        this.fileBinController = new FileBinController();
        this.student = new Student();
        this.librarian = new Librarian();
    }

    public boolean createUser(
            String firstname, 
            String lastname, 
            String email, 
            String password, 
            String RA
    ){
        // Validations
        this.validateFields(firstname, lastname, email, password, RA);
        this.checkIfEmailIsAvailable(email);
        this.checkIfRAIsAvailable(RA);

        this.student.setEntityId(this.getStudentNewUniqueId());
        this.student.setFirstname(firstname);
        this.student.setLastname(lastname);
        this.student.setEmail(email);
        this.student.setPassword(password);
        this.student.setRA(RA);
          
        this.fileBinController.addNewObject(this.student);
        return this.fileBinController.write(false);
    }

    public boolean createUser(
            String firstname, 
            String lastname, 
            String email, 
            String password
    ) {
        
        // Validations
        this.validateFields(firstname, lastname, email, password, "00000");
        this.checkIfEmailIsAvailable(email);
        
        this.librarian.setEntityId(this.getLibrarianNewUniqueId());
        this.librarian.setFirstname(firstname);
        this.librarian.setLastname(lastname);
        this.librarian.setEmail(email);
        this.librarian.setPassword(password);
        
        this.fileBinController.addNewObject(this.librarian);
        
        this.fileBinController.setFile(Librarian.FILENAME);  
        
        return this.fileBinController.write(false);
        
    }

    private int getStudentNewUniqueId() {
        
        ArrayList<Student> students = this.getStudents();
        
        if(students.isEmpty()){
            return 1;
        }

        int lastId = students.get(students.size()-1).getEntityId();
        lastId+=1;

        return lastId;
    }

    private int getLibrarianNewUniqueId() {
        
        ArrayList<Librarian> librarians = this.getLibrarians();
        
        if(librarians.isEmpty()){
            return 1;
        }

        int lastId = librarians.get(librarians.size()-1).getEntityId();
        lastId+=1;

        return lastId;
        
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
        
        ArrayList<Student> students = this.getStudents();
        
        ArrayList<Librarian> librarians = this.getLibrarians();
        
        if(!students.isEmpty()){
            for(Student stud: students){
                if(stud.getEmail().equals(email)){
                    throw new IllegalArgumentException(
                        "The email was registered previously, try another"
                    );
                }
            } 
        }

        if(!librarians.isEmpty()){
            for(Librarian lib: librarians){
                if(lib.getEmail().equals(email)){
                    throw new IllegalArgumentException(
                        "The email was registered previously, try another "
                    );
                }
            }
        }
        
    }
    
    private void checkIfRAIsAvailable(String RA) throws IllegalArgumentException{
        
          ArrayList<Student> students = this.getStudents();
          
          if(!students.isEmpty()){
               for(Student stud: students){
                   if(stud.getRA().equals(RA)){
                       throw new IllegalArgumentException(
                               "The RA was registered previously, try another"
                       );
                   }
               }
          }
          
    }

    private ArrayList<Student> getStudents(){
        this.fileBinController.setFile(Student.FILENAME);  
        boolean result = this.fileBinController.read();
        
        if(!result){
            return new ArrayList<>();
        }
  
        return (ArrayList<Student>) this.fileBinController.getObject();
    }
    
    private ArrayList<Librarian> getLibrarians(){
        this.fileBinController.setFile(Librarian.FILENAME);
        boolean result = this.fileBinController.read();
        
        if(!result){
            return new ArrayList<>();
        }
        
        return (ArrayList<Librarian>) this.fileBinController.getObject();
    }
}
