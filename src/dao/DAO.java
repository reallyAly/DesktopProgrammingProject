package dao;

import java.util.ArrayList;
import utils.JDBCUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author alysson
 */
public abstract class DAO<E> {
    
    protected static final String FIND_BY_ID_QUERY = "SELECT * FROM ? WHERE entity_id = ?";
    
    protected static final String GET_QUERY = "SELECT * FROM ? ";
    
    protected static final String DELETE_QUERY = "DELETE FROM ? WHERE entity_id = ?";
    
    protected Connection dbConnection;
    
    protected PreparedStatement preparedStatement;
    
    protected ResultSet resultSet;
    
    protected int type = ResultSet.TYPE_SCROLL_SENSITIVE;
    
    protected int competition = ResultSet.CONCUR_UPDATABLE;

    public DAO() {
        this.dbConnection = JDBCUtil.getConnection();
    }
    
    public abstract E findById(int id) throws Exception;
    
    public abstract ArrayList<E> get() throws Exception;
    
    public abstract boolean save(E obj) throws Exception;
    
    public abstract boolean delete(E obj) throws Exception;
    
    public abstract String getInsertQuery();
    
    public abstract String getUpdateQuery();
}
