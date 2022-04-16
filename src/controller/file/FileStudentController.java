package controller.file;

import java.util.StringTokenizer;
import model.Student;
import java.util.ArrayList;

public class FileStudentController extends FileTextController {

    protected ArrayList<Student> students = new ArrayList<>();
    
    protected Student student;
    
    public void readStudent() {
        
        this.setFile(Student.FILENAME);
        
        this.read();
        
        String aux = this.getText();
        String tk = String.valueOf(";")+String.valueOf("\n");
        StringTokenizer tokens = new StringTokenizer(aux, tk);
        
        while (tokens.hasMoreTokens()) {
            this.student = this.getNewStudent();
            this.student.setEntityId(Integer.parseInt(tokens.nextToken()));
            this.student.setFirstname(tokens.nextToken());
            this.student.setLastname(tokens.nextToken());
            this.student.setEmail(tokens.nextToken());
            this.student.setPassword(tokens.nextToken());
            
            this.addNewStudent(this.student);
        }
    }

    public boolean storeStudent(Student student) {

        String aux = student.getEntityId()+ ";" +
                student.getFirstname()+ ";" +
                student.getLastname()+ ";" +
                student.getEmail() + ";" +
                student.getPassword() + "\n";
        
        setText(aux);
        
        this.setFile(Student.FILENAME);
        
        return this.write(true);
                
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }
    
    public Student getNewStudent(){
        return new Student();
    }
    
    private void addNewStudent(Student student){
        this.students.add(student);
    }
}