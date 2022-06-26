package dao;

import java.util.ArrayList;
import model.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Filter;

/**
 *
 * @author alysson
 */
public class BookDAO extends DAO<Book>{
    
    protected static final String FIND_BY_ID_QUERY = "SELECT * FROM "+Book.TABLE_NAME+" WHERE entity_id = ?";
    
    protected static final String GET_QUERY = "SELECT * FROM "+Book.TABLE_NAME;
    
    protected static final String DELETE_QUERY = "DELETE FROM "+Book.TABLE_NAME+" WHERE entity_id = ?";
    
    @Override
    public Book findById(int id) {
        
        try{
            
            this.startConnection();
            
            this.preparedStatement = this.dbConnection.prepareStatement(FIND_BY_ID_QUERY, this.type, this.competition);
        
            this.preparedStatement.setInt(1, id);
        
            this.resultSet = this.preparedStatement.executeQuery();
            
            this.first(this.resultSet);
            
            Book book = this.getObjectByRs(this.resultSet);
            
            this.closeConnection();
            
            return book;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return new Book();
        }

    }

    @Override
    public ArrayList<Book> get(Filter filter) {
        
        ArrayList<Book> books = new ArrayList<>();
        
        try{
            
            this.startConnection();
            
            if(filter != null) {
                String getWithFilterQuery = 
                        "SELECT * FROM "
                        +Book.TABLE_NAME
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
                books.add(this.getObjectByRs(this.resultSet));
            }
            
            this.closeConnection();
            
            return books;
        }catch(SQLException e) {
            System.out.println(e.getMessage());
            return books;
        }
    }

    @Override
    public boolean save(Book book) {
        try{
            
            this.startConnection();
            
            if(book.getEntityId() != 0) {
            this.preparedStatement = this.dbConnection.prepareStatement(this.getUpdateQuery(), this.type, this.competition);
            this.preparedStatement.setInt(4, book.getEntityId());
            }else{
                this.preparedStatement = this.dbConnection.prepareStatement(this.getInsertQuery(), this.type, this.competition);
            }

            this.preparedStatement.setString(1, book.getName());
            this.preparedStatement.setString(2, book.getIsbn());
            this.preparedStatement.setString(3, book.getAuthor());

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
    public boolean delete(Book book) throws Exception {
        try{
            
            this.startConnection();
            
            this.preparedStatement = this.dbConnection.prepareStatement(DELETE_QUERY, this.type, this.competition);
        
            this.preparedStatement.setInt(1, book.getEntityId());
         
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
                +Book.TABLE_NAME
                +"( "
                +Book.COLUMN_BOOK_NAME
                +", "
                +Book.COLUMN_ISBN
                +", "
                +Book.COLUMN_AUTHOR
                +") "
                +"VALUES (?, ?, ?)";
    }

    @Override
    public String getUpdateQuery(){
        return "UPDATE "+Book.TABLE_NAME
                + " SET "
                + Book.COLUMN_BOOK_NAME + " = ?"
                +", "
                + Book.COLUMN_ISBN + " = ?"
                +", "
                + Book.COLUMN_AUTHOR + " = ?"
                +" WHERE entity_id = ?";
    }
    
    private Book getObjectByRs(ResultSet rs) throws SQLException {
        
        Book book = new Book();

        book.setEntityId(rs.getInt(Book.COLUMN_ENTITY_ID));
        book.setName(rs.getString(Book.COLUMN_BOOK_NAME));
        book.setIsbn(rs.getString(Book.COLUMN_ISBN));
        book.setAuthor(rs.getString(Book.COLUMN_AUTHOR));       
        return book;
    } 
     
}
