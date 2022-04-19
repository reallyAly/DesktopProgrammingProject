package controller;

import controller.file.FileLoanController;
import java.util.ArrayList;
import model.Loan;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class ReturnBookController {

    private FileLoanController fileLoanController;
   
    private Loan loan;
    
    private int librarianId;

    public ReturnBookController(int librarianId){
        this.librarianId = librarianId;
        this.fileLoanController = new FileLoanController();
        this.loan = new Loan();
    }

    public void returnBook(String loanId) throws Exception{
        
        if(this.fileLoanController.findLoanById(Integer.parseInt(loanId)) == null){
            throw new Exception("The loan specified does not exist");
        }
        
        this.validateFields(loanId);
        
        ArrayList<Loan> loans = this.fileLoanController.getLoans();
        
        for(int i = 0; i < loans.size(); i++){
            if(loans.get(i).getEntityId() == Integer.parseInt(loanId)){
                loans.get(i).setStatus("RETURNED");
            }
        }
        
        this.fileLoanController.setLoans(loans);

    }
    
    private void validateFields(String id) throws IllegalArgumentException {
        if(id.isBlank()){
            throw new IllegalArgumentException(
                "The id field cannot be empty"
            );
        }
    }

    private int getLoanNewUniqueId(){

        this.fileLoanController.readLoan();
        ArrayList<Loan> loans = this.fileLoanController.getLoans();

        if(loans.isEmpty()){
            return 1;
        }

        int lastId = loans.get(loans.size()-1).getEntityId();
        lastId+=1;

        return lastId;
    }
    
    private String getCurrentDate(){
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
         LocalDateTime now = LocalDateTime.now();  
         return dtf.format(now);
    }
    
}
