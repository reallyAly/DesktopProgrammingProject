package dao;

import java.util.ArrayList;
import model.Loan;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alysson
 */
public class LoanDAO extends DAO<Loan>{
    
    protected static final String FIND_BY_ID_QUERY = "SELECT * FROM "+Loan.TABLE_NAME+" WHERE entity_id = ?";
    
    protected static final String GET_QUERY = "SELECT * FROM "+Loan.TABLE_NAME;
    
    protected static final String DELETE_QUERY = "DELETE FROM "+Loan.TABLE_NAME+" WHERE entity_id = ?";
    
    public LoanDAO() {
        super();
    }
    
    @Override
    public Loan findById(int id) {
        
        try{
            
            this.preparedStatement = this.dbConnection.prepareStatement(FIND_BY_ID_QUERY, this.type, this.competition);
        
            this.preparedStatement.setInt(1, id);
        
            this.resultSet = this.preparedStatement.executeQuery();
            
            this.first(this.resultSet);
            
            return this.getObjectByRs(this.resultSet);
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return new Loan();
        }

    }

    @Override
    public ArrayList<Loan> get() {
        
        ArrayList<Loan> loans = new ArrayList<>();
        
        try{
            this.preparedStatement = this.dbConnection.prepareStatement(GET_QUERY, this.type, this.competition);

            this.resultSet = this.preparedStatement.executeQuery();
            
            while(this.next(this.resultSet)){
                loans.add(this.getObjectByRs(this.resultSet));
            }
            
            return loans;
        }catch(SQLException ex) {
            System.out.println(ex);
            return loans;
        }
    }

    @Override
    public boolean save(Loan loan) throws Exception {
        
        if(loan.getEntityId() != 0) {
            this.preparedStatement = this.dbConnection.prepareStatement(this.getUpdateQuery(), this.type, this.competition);
        }else{
            this.preparedStatement = this.dbConnection.prepareStatement(this.getInsertQuery(), this.type, this.competition);
        }
        
        this.preparedStatement.setInt(1, loan.getStudentId());
        this.preparedStatement.setInt(2, loan.getBookId());
        this.preparedStatement.setInt(3, loan.getDevolutionId());
        this.preparedStatement.setString(4, loan.getLoanDate());

        int result = this.preparedStatement.executeUpdate();
        
        if(result == 1) {
            this.dbConnection.commit();
            return true;
        }
        
        this.dbConnection.rollback();
        
        return false;
    }
    
    @Override
    public boolean delete(Loan loan) throws Exception {
        this.preparedStatement = this.dbConnection.prepareStatement(DELETE_QUERY, this.type, this.competition);
        
        this.preparedStatement.setInt(1, loan.getEntityId());
         
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
                +Loan.TABLE_NAME
                +"( "
                +Loan.COLUMN_STUDENT_ID
                +", "
                +Loan.COLUMN_BOOK_ID
                +", "
                +Loan.COLUMN_DEVOLUTION_ID
                +", "
                +Loan.COLUMN_CREATED_AT
                +") "
                +"VALUES (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery(){
        return "UPDATE "+Loan.TABLE_NAME
                + " SET "
                + Loan.COLUMN_STUDENT_ID + " = ?"
                +", "
                + Loan.COLUMN_BOOK_ID + " = ?"
                +", "
                + Loan.COLUMN_DEVOLUTION_ID + " = ?"
                +", "
                + Loan.COLUMN_CREATED_AT + " = ?";
    }
    
    private Loan getObjectByRs(ResultSet rs) throws SQLException {
        
        Loan loan = new Loan();

        loan.setEntityId(rs.getInt(Loan.COLUMN_ENTITY_ID));
        loan.setStudentId(rs.getInt(Loan.COLUMN_STUDENT_ID));
        loan.setBookId(rs.getInt(Loan.COLUMN_BOOK_ID));
        loan.setDevolutionId(rs.getInt(Loan.COLUMN_DEVOLUTION_ID));       
        loan.setLoanDate(rs.getString(Loan.COLUMN_CREATED_AT)); 
        
        return loan;
    } 
     
}
