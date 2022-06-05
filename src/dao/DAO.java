package dao;

import java.util.ArrayList;
import utils.JDBCUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author alysson
 */
public abstract class DAO<E> {
    
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
    
    public boolean next(ResultSet rsdados) {
        try {
            if (rsdados != null) {
                if (!rsdados.isLast()) {
                    rsdados.next();
                    return true;
                }               
            }
        } catch (SQLException erro) {
            System.out.println(erro);
            return false;
        }
        return false;
    }

    public boolean previous(ResultSet rsdados) {
        try {
            if (rsdados != null) {
                if (!rsdados.isFirst()) {
                    rsdados.previous();
                    return true;
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
            return false;
        }
        return false;
    }

    public boolean last(ResultSet rsdados) {
        try {
            if (rsdados != null) {
                if (!rsdados.isLast()) {
                    rsdados.last();
                    return true;
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
            return false;
        }
        return false;
    }

    public boolean first(ResultSet rsdados) {
        try {
            if (rsdados != null) {
                if (!rsdados.isFirst()) {
                    rsdados.first();
                    return true;
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
            return false;
        }
        return false;
    }
}
