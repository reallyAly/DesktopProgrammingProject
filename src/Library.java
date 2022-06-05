/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import utils.JDBCUtil;
import controller.FileController;
import java.io.IOException;
import java.sql.SQLException;
import view.LoginView;

/**
 *
 * @author alysson
 */
public class Library {
    
    private static final String DB_PROPERTIES_FILE_PATH = "mysql.properties.txt";
    
    public static void main(String[] args) {
        
        try{
            
            FileController fileController = new FileController();
            
            fileController.setFile(DB_PROPERTIES_FILE_PATH);

            JDBCUtil.init(fileController.getFile());
            
            new LoginView().setVisible(true);
            
        }catch(SQLException | IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        
    }
    
}
