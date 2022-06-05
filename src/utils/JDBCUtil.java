package utils;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;
import java.sql.DriverManager;

/**
 * A simple data source for getting database connections.
 */
public class JDBCUtil {

    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    /**
     * Initializes the data source.
     *
     * @param fileName the name of the property file that contains the database
     * driver, URL, username, and password
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void init(File fileName) throws IOException, ClassNotFoundException, SQLException {

        Properties props = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);
        
        String driver = props.getProperty("mysql.driver");
        
        url = props.getProperty("mysql.url");
        
        username = props.getProperty("mysql.username");
        if (username == null) {
            username = "";
        }
        password = props.getProperty("mysql.password");
        if (password == null) {
            password = "";
        }
        if (driver != null) {
            Class.forName(driver);
        }
        
        conn = DriverManager.getConnection(url, username, password);
        
        conn.setAutoCommit(false);
    }

    /**
     * Get connection to the database.
     *
     * @return the database connection
     *
     */
    public static Connection getConnection(){
        return conn;
    }
}