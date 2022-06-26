package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.sql.SQLException;
import utils.JDBCUtil;

/**
 *
 * @author alysson
 */
public class GenerateReportController {
    
    private FileController fileController;
    
    private Connection connection;
        
    public GenerateReportController() 
    {
        this.fileController = new FileController();
        this.connection = null;
    }
    
    public void generateReport(String templateFileName) throws SQLException, FileNotFoundException, JRException {
        
        this.connection = JDBCUtil.getConnection();
        this.fileController.setFile(templateFileName);
      
        JasperPrint impressao;
        
        FileInputStream file = new FileInputStream(this.fileController.getFile());

        JasperReport report = JasperCompileManager.compileReport(file);

        impressao = JasperFillManager.fillReport(
                report,
                null,
                this.connection);

        JasperViewer.viewReport(impressao);

        this.connection.close();       
    }
   
}
