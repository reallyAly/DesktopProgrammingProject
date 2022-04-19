package controller;

import controller.file.FileLoanController;
import controller.file.FileBookController;
import java.util.ArrayList;
import model.Loan;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class LoanBookController {

    private FileLoanController fileLoanController;
    
    private FileBookController fileBookController;

    private Loan loan;
    
    private int studentId;

    public LoanBookController(int studentId){
        this.studentId = studentId;
        this.fileLoanController = new FileLoanController();
        this.fileBookController = new FileBookController();
        this.loan = new Loan();
    }

    public boolean loanBook(String bookId) throws Exception{
        
        if(this.fileLoanController.checkIfBookisLoan(Integer.parseInt(bookId))){
            throw new IllegalArgumentException(
                "The book specified cannot be loan"
            );
        }
        
        if(this.fileBookController.getBookById(Integer.parseInt(bookId)) == null){
            throw new Exception("The book specified does not exist");
        }
        
        this.validateFields(bookId);
        
        this.loan.setEntityId(this.getLoanNewUniqueId());
        this.loan.setStudentId(this.studentId);
        this.loan.setBookId(Integer.parseInt(bookId));
        this.loan.setStatus("LOAN");
        this.loan.setLoanDate(this.getCurrentDate());
        this.loan.setDevolutionDate("0000000");
        
        boolean result = this.fileLoanController.storeLoan(this.loan);
        
        if(!result){
            throw new Exception("Error trying to loan the book");
        }
        
        return result;
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
