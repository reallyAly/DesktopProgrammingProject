package dao;

import java.util.ArrayList;
import model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alysson
 */
public class StudentDAO extends DAO<Student>{
    
    public StudentDAO() {
        super();
    }
    
    @Override
    public Student findById(int id) throws SQLException {
        this.preparedStatement = this.dbConnection.prepareStatement(DAO.FIND_BY_ID_QUERY);
        
        this.preparedStatement.setString(1, Student.TABLE_NAME);
        this.preparedStatement.setInt(2, id);

        return this.getObjectByRs(this.preparedStatement.executeQuery());
    }

    @Override
    public ArrayList<Student> get() throws SQLException {
         
        this.preparedStatement = this.dbConnection.prepareStatement(DAO.GET_QUERY);
            
        this.preparedStatement.setString(1, Student.TABLE_NAME);
            
        ArrayList<Student> students = new ArrayList<>();
            
        this.resultSet = this.preparedStatement.executeQuery();

        int size = this.resultSet.getFetchSize();
            
        for(int i = 0; i < size; i++) {
            students.add(this.getObjectByRs(this.resultSet));
        }
            
        return students;
    }

    @Override
    public boolean save(Student student) throws Exception {
        
        if(student.getEntityId() != 0) {
            this.preparedStatement = this.dbConnection.prepareStatement(this.getUpdateQuery());
        }else{
            this.preparedStatement = this.dbConnection.prepareStatement(this.getInsertQuery());
        }
        
        this.preparedStatement.setString(1, student.getFirstname());
        this.preparedStatement.setString(2, student.getLastname());
        this.preparedStatement.setString(3, student.getEmail());
        this.preparedStatement.setString(4, student.getPassword());
        this.preparedStatement.setString(5, student.getRA());

        int result = this.preparedStatement.executeUpdate();
        
        if(result == 1) {
            this.dbConnection.commit();
            return true;
        }
        
        this.dbConnection.rollback();
        
        return false;
    }
    
    @Override
    public boolean delete(Student student) throws Exception {
        this.preparedStatement = this.dbConnection.prepareStatement(DAO.DELETE_QUERY);
        
        this.preparedStatement.setString(1, Student.TABLE_NAME);
        this.preparedStatement.setInt(2, student.getEntityId());
         
        int result = this.preparedStatement.executeUpdate();
        
        if(result == 1) {
            this.dbConnection.commit();
            return true;
        }
        
        this.dbConnection.rollback();
        
        return false;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO "+Student.TABLE_NAME+" VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery(){
        return "UPDATE "+Student.TABLE_NAME
                + " SET "
                + Student.COLUMN_FIRSTNAME + " = ?"
                + Student.COLUMN_LASTNAME + " = ?"
                + Student.COLUMN_EMAIL + " = ?"
                + Student.COLUMN_PASSWORD + " = ?"
                + Student.COLUMN_RA + " = ?";
    }
    
    private Student getObjectByRs(ResultSet rs) throws SQLException {
        
        Student student = new Student();

        student.setEntityId(rs.getInt(Student.COLUMN_ENTITY_ID));
        student.setFirstname(rs.getString(Student.COLUMN_FIRSTNAME));
        student.setLastname(rs.getString(Student.COLUMN_LASTNAME));
        student.setEmail(rs.getString(Student.COLUMN_EMAIL));
        student.setPassword(rs.getString(Student.COLUMN_PASSWORD));
        student.setRA(rs.getString(Student.COLUMN_RA));
           
        return student;
    }
     
}
