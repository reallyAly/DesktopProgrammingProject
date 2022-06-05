package dao;

import java.util.ArrayList;
import utils.JDBCUtil;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author alysson
 */
public abstract class DAO<E> {
    
    public Connection getConnection() throws SQLException {
        return JDBCUtil.getConnection();
    }
    
    public abstract E findById(int id) throws Exception;
    
    public abstract ArrayList<E> get() throws Exception;
    
    public abstract boolean save(E obj) throws Exception;
    
    
}
