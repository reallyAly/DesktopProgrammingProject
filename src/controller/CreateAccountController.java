package controller;

import controller.file.FileLibrarianController;
import controller.file.FileStudentController;
import model.Student;
import model.Librarian;

import java.util.ArrayList;

/**
 *
 * @author alysson
 */
public class CreateAccountController {

    private FileStudentController fileStudentController;

    private FileLibrarianController fileLibrarianController;

    private Student student;

    private Librarian librarian;

    public CreateAccountController() {
        this.fileLibrarianController = new FileLibrarianController();
        this.fileStudentController = new FileStudentController();
        this.student = new Student();
        this.librarian = new Librarian();
        
        this.fileStudentController.readStudent();
        this.fileLibrarianController.readLibrarian();
    }

    public boolean createUser(
            String firstname, 
            String lastname, 
            String email, 
            String password, 
            String RA
    ) throws Exception {
        
        ArrayList<Student> students = this.fileStudentController.getStudents();
        ArrayList<Librarian> librarians = this.fileLibrarianController.getLibrarians();
        
        // Validations
        this.validateFields(firstname, lastname, email, password, RA);
        
        this.checkIfEmailIsAvailable(
                email, 
                students, 
                librarians
        );
        
        this.checkIfRAIsAvailable(RA, students);

        this.student.setEntityId(this.getStudentNewUniqueId(students));
        this.student.setFirstname(firstname);
        this.student.setLastname(lastname);
        this.student.setEmail(email);
        this.student.setPassword(password);
        this.student.setRA(RA);
          
        boolean result = this.fileStudentController.storeStudent(this.student);
        
        if(!result){
           throw new Exception("Error trying to save the new user");
        }

        return result;
    }

    public boolean createUser(
            String firstname, 
            String lastname, 
            String email, 
            String password
    ) {
        
        ArrayList<Student> students = this.fileStudentController.getStudents();
        ArrayList<Librarian> librarians = this.fileLibrarianController.getLibrarians();
        
        // Validations
        this.validateFields(firstname, lastname, email, password, "00000");
        this.checkIfEmailIsAvailable(
                email, 
                students, 
                librarians
        );
        
        this.librarian.setEntityId(this.getLibrarianNewUniqueId(librarians));
        this.librarian.setFirstname(firstname);
        this.librarian.setLastname(lastname);
        this.librarian.setEmail(email);
        this.librarian.setPassword(password);

        return this.fileLibrarianController.storeLibrarian(this.librarian);
    }

    private int getStudentNewUniqueId(ArrayList<Student> students) {

        if(students.isEmpty()){
            return 1;
        }

        int lastId = students.get(students.size()-1).getEntityId();
        lastId+=1;

        return lastId;
    }

    private int getLibrarianNewUniqueId(ArrayList<Librarian> librarians) {

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
    
    private void checkIfEmailIsAvailable(
            String email, 
            ArrayList<Student> students, 
            ArrayList<Librarian> librarians
    ) throws IllegalArgumentException{

        for(Student stud: students){
                if(stud.getEmail().equals(email)){
                    throw new IllegalArgumentException(
                        "The email was registered previously, try another"
                    );
                }
         }

        for(Librarian lib: librarians){
                if(lib.getEmail().equals(email)){
                    throw new IllegalArgumentException(
                            "The email was registered previously, try another "
                    );
                }
        }
    }
    
    private void checkIfRAIsAvailable(String RA, ArrayList<Student> students) throws IllegalArgumentException{
        
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
}
