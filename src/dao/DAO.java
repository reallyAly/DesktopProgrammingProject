package dao;

import java.util.ArrayList;
import utils.JDBCUtil;
import java.sql.Connection;

/**
 *
 * @author alysson
 */
public abstract class DAO<E> {
    
    protected Connection dbConnection;
    
    public DAO() {
        this.dbConnection = JDBCUtil.getConnection();
    }
    
    public abstract E findById(int id) throws Exception;
    
    public abstract ArrayList<E> get() throws Exception;
    
    public abstract boolean save(E obj) throws Exception;
    
    
}
