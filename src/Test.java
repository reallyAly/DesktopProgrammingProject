import dao.StudentDAO;
import java.sql.SQLException;
import model.Student;
import utils.JDBCUtil;
import controller.FileController;
import java.io.IOException;
import model.Filter;

/**
 *
 * @author alysson
 */
public class Test {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, Exception {
        
        FileController f = new FileController();
        
        f.setFile("mysql.properties.txt");
        
        JDBCUtil.init(f.getFile());
        
        Student student = new Student(
           0,   
           "Alysson",
           "Victor",
           "victor.alysson13@gmail.com",
           "12345",
           "2142341"
        );
        
        StudentDAO dao = new StudentDAO();
        
        // Test findById
        /* Student stud = dao.findById(1); */
        
        // Test Save //
//        System.out.println(dao.save(student));
        
        // Test update //
        
        /* Student stud = dao.findById(1);
        
        stud.setFirstname("Aly");
        System.out.println(dao.save(stud)); */
        
        // Test get // 
        Filter filter = new Filter("email", "victor.alysson13@gmail.com");
        System.out.println(dao.get(filter).get(0).getEntityId());

        // Test delete //
        // System.out.println(dao.delete(dao.findById(1)));

    }
    
}
