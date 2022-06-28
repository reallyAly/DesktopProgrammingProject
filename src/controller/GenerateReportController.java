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
import java.util.HashMap;
import java.util.Map;
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
    
    public void generateReport(String templateFileName, Map parameters) throws SQLException, FileNotFoundException, JRException {
        
        this.connection = JDBCUtil.getConnection();
        this.fileController.setFile(templateFileName);
      
        JasperPrint impressao;
        
        FileInputStream file = new FileInputStream(this.fileController.getFile());

        JasperReport report = JasperCompileManager.compileReport(file);

        if(parameters.isEmpty()) {
            impressao = JasperFillManager.fillReport(
                report,
                null,
                this.connection);
        }else{
            impressao = JasperFillManager.fillReport(
                report,
                parameters,
                this.connection);
        }
       
        JasperViewer.viewReport(impressao, false);

        this.connection.close();       
    }
    
    public void generateLoanReportByStudent(String templateFileName, int studentId) throws SQLException, FileNotFoundException, JRException {
        
        Map params = new HashMap();
        
        params.put("student_id", studentId);
        
        this.generateReport(templateFileName, params);
    }
   
}
