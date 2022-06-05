package dao;

import java.util.ArrayList;
import model.Librarian;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alysson
 */
public class LibrarianDAO extends DAO<Librarian>{
    
    protected static final String FIND_BY_ID_QUERY = "SELECT * FROM "+Librarian.TABLE_NAME+" WHERE entity_id = ?";
    
    protected static final String GET_QUERY = "SELECT * FROM "+Librarian.TABLE_NAME;
    
    protected static final String DELETE_QUERY = "DELETE FROM "+Librarian.TABLE_NAME+" WHERE entity_id = ?";
    
    public LibrarianDAO() {
        super();
    }
    
    @Override
    public Librarian findById(int id) {
        
        try{
            
            this.preparedStatement = this.dbConnection.prepareStatement(FIND_BY_ID_QUERY, this.type, this.competition);
        
            this.preparedStatement.setInt(1, id);
        
            this.resultSet = this.preparedStatement.executeQuery();
            
            this.first(this.resultSet);
            
            return this.getObjectByRs(this.resultSet);
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return new Librarian();
        }

    }

    @Override
    public ArrayList<Librarian> get() {
        
        ArrayList<Librarian> librarians = new ArrayList<>();
        
        try{
            this.preparedStatement = this.dbConnection.prepareStatement(GET_QUERY, this.type, this.competition);

            this.resultSet = this.preparedStatement.executeQuery();
            
            while(this.next(this.resultSet)){
                librarians.add(this.getObjectByRs(this.resultSet));
            }
            
            return librarians;
        }catch(SQLException ex) {
            System.out.println(ex);
            return librarians;
        }
    }

    @Override
    public boolean save(Librarian librarian) throws Exception {
        
        if(librarian.getEntityId() != 0) {
            this.preparedStatement = this.dbConnection.prepareStatement(this.getUpdateQuery(), this.type, this.competition);
        }else{
            this.preparedStatement = this.dbConnection.prepareStatement(this.getInsertQuery(), this.type, this.competition);
        }
        
        this.preparedStatement.setString(1, librarian.getFirstname());
        this.preparedStatement.setString(2, librarian.getLastname());
        this.preparedStatement.setString(3, librarian.getEmail());
        this.preparedStatement.setString(4, librarian.getPassword());

        int result = this.preparedStatement.executeUpdate();
        
        if(result == 1) {
            this.dbConnection.commit();
            return true;
        }
        
        this.dbConnection.rollback();
        
        return false;
    }
    
    @Override
    public boolean delete(Librarian student) throws Exception {
        this.preparedStatement = this.dbConnection.prepareStatement(DELETE_QUERY, this.type, this.competition);
        
        this.preparedStatement.setInt(1, student.getEntityId());
         
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
        return "INSERT INTO "
                +Librarian.TABLE_NAME
                +"( "
                +Librarian.COLUMN_FIRSTNAME
                +", "
                +Librarian.COLUMN_LASTNAME
                +", "
                +Librarian.COLUMN_EMAIL
                +", "
                +Librarian.COLUMN_PASSWORD
                +") "
                +"VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery(){
        return "UPDATE "+Librarian.TABLE_NAME
                + " SET "
                + Librarian.COLUMN_FIRSTNAME + " = ?"
                +", "
                + Librarian.COLUMN_LASTNAME + " = ?"
                +", "
                + Librarian.COLUMN_EMAIL + " = ?"
                +", "
                + Librarian.COLUMN_PASSWORD + " = ?";
    }
    
    private Librarian getObjectByRs(ResultSet rs) throws SQLException {
        
        Librarian librarian = new Librarian();

        librarian.setEntityId(rs.getInt(Librarian.COLUMN_ENTITY_ID));
        librarian.setFirstname(rs.getString(Librarian.COLUMN_FIRSTNAME));
        librarian.setLastname(rs.getString(Librarian.COLUMN_LASTNAME));
        librarian.setEmail(rs.getString(Librarian.COLUMN_EMAIL));
        librarian.setPassword(rs.getString(Librarian.COLUMN_PASSWORD));
           
        return librarian;
    }
     
}
