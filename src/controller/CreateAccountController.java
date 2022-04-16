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
    }

    public boolean createUser(String firstname, String lastname, String email, String password, String RA) {

        this.student.setEntityId(this.getNewUniqueId(this.student));
        this.student.setFirstname(firstname);
        this.student.setLastname(lastname);
        this.student.setEmail(email);
        this.student.setPassword(password);
        this.student.setRA(RA);

        return this.fileStudentController.storeStudent(this.student);
    }

    public boolean createUser(String firstname, String lastname, String email, String password) {

        this.librarian.setEntityId(this.getNewUniqueId(this.librarian));
        this.librarian.setFirstname(firstname);
        this.librarian.setLastname(lastname);
        this.librarian.setEmail(email);
        this.librarian.setPassword(password);

        return this.fileLibrarianController.storeLibrarian(this.librarian);
    }

    private int getNewUniqueId(Student student) {

        this.fileStudentController.readStudent();
        ArrayList<Student> students = this.fileStudentController.getStudents();

        if(students.size() == 0){
            return 1;
        }

        int lastId = students.get(students.size()-1).getEntityId();
        lastId+=1;

        return lastId;
    }

    private int getNewUniqueId(Librarian librarian) {

        this.fileLibrarianController.readLibrarian();
        ArrayList<Librarian> librarians = this.fileLibrarianController.getLibrarians();

        if(librarians.size() == 0){
            return 1;
        }

        int lastId = librarians.get(librarians.size()-1).getEntityId();
        lastId+=1;

        return lastId;
    }
}
