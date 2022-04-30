package controller;

import controller.file.FileLoanController;
import java.util.ArrayList;
import model.Loan;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class ReturnBookController {

    private FileLoanController fileLoanController;
   
    private int librarianId;

    public ReturnBookController(int librarianId){
        this.librarianId = librarianId;
        this.fileLoanController = new FileLoanController();
    }

    public void returnBook(String loanId) throws Exception{
        
        if(this.fileLoanController.findLoanById(Integer.parseInt(loanId)) == null){
            throw new Exception("The loan specified does not exist");
        }
        
        this.validateFields(loanId);
        
        ArrayList<Loan> loans = this.fileLoanController.getLoans();
        
        for(int i = 0; i < loans.size(); i++){
            
            if(this.validateLoanToReturn(loans.get(i), loanId)){
                
                if(this.validateLoanStatus(loans.get(i))){
                    loans.get(i).setStatus("RETURNED");
                    loans.get(i).setDevolutionDate(this.getCurrentDate());
                }else{
                    throw new IllegalArgumentException("The loan specified cannot be returned");
                }
                
            }
  
        }
        
        this.fileLoanController.setLoans(loans);

    }
    
    private Boolean validateLoanToReturn(Loan loan, String loanId){
        return loan.getEntityId() == Integer.parseInt(loanId);
    }
    
    private Boolean validateLoanStatus(Loan loan){
        return loan.getStatus().equals("LOAN");
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
