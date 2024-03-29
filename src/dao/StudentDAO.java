package dao;

import java.util.ArrayList;
import model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Filter;

/**
 *
 * @author alysson
 */
public class StudentDAO extends DAO<Student>{
    
    protected static final String FIND_BY_ID_QUERY = "SELECT * FROM "+Student.TABLE_NAME+" WHERE entity_id = ?";
    
    protected static final String GET_QUERY = "SELECT * FROM "+Student.TABLE_NAME;
    
    protected static final String DELETE_QUERY = "DELETE FROM "+Student.TABLE_NAME+" WHERE entity_id = ?";
    
    @Override
    public Student findById(int id) {
        
        try{
            
            this.startConnection();
            
            this.preparedStatement = this.dbConnection.prepareStatement(FIND_BY_ID_QUERY, this.type, this.competition);
        
            this.preparedStatement.setInt(1, id);
        
            this.resultSet = this.preparedStatement.executeQuery();
            
            this.first(this.resultSet);
            
            Student student = this.getObjectByRs(this.resultSet);
            
            this.closeConnection();
            
            return student;
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return new Student();
        }

    }

    @Override
    public ArrayList<Student> get(Filter filter) {
        
        ArrayList<Student> students = new ArrayList<>();
        
        try{
            
            this.startConnection();
            
            if(filter != null) {
                String getWithFilterQuery = 
                        "SELECT * FROM "
                        +Student.TABLE_NAME
                        +" WHERE "
                        +filter.getColumnName()
                        +" = ?";
               
                this.preparedStatement = this.dbConnection.prepareStatement(getWithFilterQuery, this.type, this.competition);
                
                this.preparedStatement.setString(1, filter.getColumnValue());
                
            }else{
                this.preparedStatement = this.dbConnection.prepareStatement(GET_QUERY, this.type, this.competition);
            }

            this.resultSet = this.preparedStatement.executeQuery();
            
            while(this.next(this.resultSet)){
                students.add(this.getObjectByRs(this.resultSet));
            }
            
            this.closeConnection();
            
            return students;
        }catch(SQLException ex) {
            System.out.println(ex);
            return students;
        }
    }

    @Override
    public boolean save(Student student) throws Exception {
        
        try{
            
            this.startConnection();
            
            if(student.getEntityId() != 0) {
                this.preparedStatement = this.dbConnection.prepareStatement(this.getUpdateQuery(), this.type, this.competition);
            }else{
                this.preparedStatement = this.dbConnection.prepareStatement(this.getInsertQuery(), this.type, this.competition);
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
            
            this.closeConnection();
            
            return false;
        }catch(SQLException e) {
            System.out.println(e);
            return false;
        }
        
    }
    
    @Override
    public boolean delete(Student student){
        try{
            
            this.startConnection();
            
            this.preparedStatement = this.dbConnection.prepareStatement(DELETE_QUERY, this.type, this.competition);
        
            this.preparedStatement.setInt(1, student.getEntityId());
         
            int result = this.preparedStatement.executeUpdate();
        
            if(result == 1) {
                this.dbConnection.commit();
                return true;
            }
        
            this.dbConnection.rollback();
            
            this.closeConnection();
            
            return false;
        }catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getInsertQuery() {
        return "INSERT INTO "
                +Student.TABLE_NAME
                +"( "
                +Student.COLUMN_FIRSTNAME
                +", "
                +Student.COLUMN_LASTNAME
                +", "
                +Student.COLUMN_EMAIL
                +", "
                +Student.COLUMN_PASSWORD
                +", "
                +Student.COLUMN_RA
                +") "
                +"VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery(){
        return "UPDATE "+Student.TABLE_NAME
                + " SET "
                + Student.COLUMN_FIRSTNAME + " = ?"
                +", "
                + Student.COLUMN_LASTNAME + " = ?"
                +", "
                + Student.COLUMN_EMAIL + " = ?"
                +", "
                + Student.COLUMN_PASSWORD + " = ?"
                +", "
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
