package dao;

import java.util.ArrayList;
import model.Devolution;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alysson
 */
public class DevolutionDAO extends DAO<Devolution>{
    
    protected static final String FIND_BY_ID_QUERY = "SELECT * FROM "+Devolution.TABLE_NAME+" WHERE entity_id = ?";
    
    protected static final String GET_QUERY = "SELECT * FROM "+Devolution.TABLE_NAME;
    
    protected static final String DELETE_QUERY = "DELETE FROM "+Devolution.TABLE_NAME+" WHERE entity_id = ?";
    
    public DevolutionDAO() {
        super();
    }
    
    @Override
    public Devolution findById(int id) {
        
        try{
            
            this.preparedStatement = this.dbConnection.prepareStatement(FIND_BY_ID_QUERY, this.type, this.competition);
        
            this.preparedStatement.setInt(1, id);
        
            this.resultSet = this.preparedStatement.executeQuery();
            
            this.first(this.resultSet);
            
            return this.getObjectByRs(this.resultSet);
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return new Devolution();
        }

    }

    @Override
    public ArrayList<Devolution> get() {
        
        ArrayList<Devolution> devolutions = new ArrayList<>();
        
        try{
            this.preparedStatement = this.dbConnection.prepareStatement(GET_QUERY, this.type, this.competition);

            this.resultSet = this.preparedStatement.executeQuery();
            
            while(this.next(this.resultSet)){
                devolutions.add(this.getObjectByRs(this.resultSet));
            }
            
            return devolutions;
        }catch(SQLException ex) {
            System.out.println(ex);
            return devolutions;
        }
    }

    @Override
    public boolean save(Devolution devolution) throws Exception {
        
        if(devolution.getEntityId() != 0) {
            this.preparedStatement = this.dbConnection.prepareStatement(this.getUpdateQuery(), this.type, this.competition);
        }else{
            this.preparedStatement = this.dbConnection.prepareStatement(this.getInsertQuery(), this.type, this.competition);
        }
        
        this.preparedStatement.setInt(1, devolution.getLibrarianId());
        this.preparedStatement.setString(2, devolution.getDevolutionDate());

        int result = this.preparedStatement.executeUpdate();
        
        if(result == 1) {
            this.dbConnection.commit();
            return true;
        }
        
        this.dbConnection.rollback();
        
        return false;
    }
    
    @Override
    public boolean delete(Devolution devolution) throws Exception {
        this.preparedStatement = this.dbConnection.prepareStatement(DELETE_QUERY, this.type, this.competition);
        
        this.preparedStatement.setInt(1, devolution.getEntityId());
         
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
                +Devolution.TABLE_NAME
                +"( "
                +Devolution.COLUMN_LIBRARIAN_ID
                +", "
                +Devolution.COLUMN_CREATEAD_AT
                +") "
                +"VALUES (?, ?)";
    }

    @Override
    public String getUpdateQuery(){
        return "UPDATE "+Devolution.TABLE_NAME
                + " SET "
                + Devolution.COLUMN_LIBRARIAN_ID + " = ?"
                +", "
                + Devolution.COLUMN_CREATEAD_AT + " = ?";
    }
    
    private Devolution getObjectByRs(ResultSet rs) throws SQLException {
        
        Devolution devolution = new Devolution();

        devolution.setEntityId(rs.getInt(Devolution.COLUMN_ENTITY_ID));
        devolution.setLibrarianId(rs.getInt(Devolution.COLUMN_LIBRARIAN_ID));
        devolution.setDevolutionDate(rs.getString(Devolution.COLUMN_CREATEAD_AT));  
        
        return devolution;
    } 
     
}
