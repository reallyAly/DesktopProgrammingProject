package controller;

import exception.LoanNotExistException;
import model.repository.LoanRepository;
import model.Loan;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import javax.naming.CannotProceedException;

public class ReturnBookController {

    private int librarianId;
    
    private LoanRepository loanRepository;

    public ReturnBookController(int librarianId){
        this.librarianId = librarianId;
        this.loanRepository = new LoanRepository();
    }

    public void returnBook(String loanId) throws CannotProceedException, Exception{
        
        if(this.loanRepository.findById(Integer.parseInt(loanId)) == null){
            throw new LoanNotExistException("The loan specified does not exist");
        }
        
        this.validateFields(loanId);
        
        Loan loan = this.loanRepository.findById(Integer.parseInt(loanId));
        
        if(this.validateLoanStatus(loan)){
            loan.setStatus("RETURNED");
            loan.setDevolutionDate(this.getCurrentDate());
        }else{
            throw new CannotProceedException("The book was returned previously");
        }

        this.loanRepository.save(loan);
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
