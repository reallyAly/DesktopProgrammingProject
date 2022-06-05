package controller;

import dao.LoanDAO;
import dao.DevolutionDAO;
import exception.LoanNotExistException;
import model.Loan;
import model.Devolution;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import javax.naming.CannotProceedException;

public class ReturnBookController {

    private int librarianId;
    
    private LoanDAO loanDAO;
    
    private DevolutionDAO devolutionDAO; 

    public ReturnBookController(int librarianId){
        this.librarianId = librarianId;
        this.loanDAO = new LoanDAO();
        this.devolutionDAO = new DevolutionDAO();
    }

    public boolean returnBook(String loanId) throws CannotProceedException, Exception{
        
        Loan loan = this.loanDAO.findById(Integer.parseInt(loanId));
       
        if(loan.getEntityId() == 0){
            throw new LoanNotExistException("The loan specified does not exist");
        }
        
        if(loan.getDevolutionId() != 0){
            throw new CannotProceedException("The book was returned previously");
        }
        
        Devolution devolution = new Devolution();
         
        devolution.setLibrarianId(this.librarianId);
        devolution.setDevolutionDate(this.getCurrentDate());

        this.validateFields(loanId);
 
        boolean devolutionResult = this.devolutionDAO.save(devolution);   
       
        if(devolutionResult) {
            boolean loanResult = this.loanDAO.save(loan);
            
            return loanResult;
        }

        return devolutionResult;
    }

    private void validateFields(String id) throws IllegalArgumentException {
        if(id.isBlank()){
            throw new IllegalArgumentException(
                "The id field cannot be empty"
            );
        }
    }

    private String getCurrentDate(){
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
         LocalDateTime now = LocalDateTime.now();  
         return dtf.format(now);
    }
    
}
