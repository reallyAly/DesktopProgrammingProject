package controller;


import exception.BookNotExistException;
import java.util.ArrayList;
import model.Loan;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import dao.BookDAO;
import dao.LoanDAO;
import model.Filter;

public class LoanBookController {

    private LoanDAO loanDAO;
    
    private BookDAO bookDAO;

    private Loan loan;
    
    private int studentId;

    public LoanBookController(int studentId) {
        this.studentId = studentId;
        this.loanDAO = new LoanDAO();
        this.bookDAO = new BookDAO();
        this.loan = new Loan();
    }

    public boolean loanBook(String bookId) throws Exception{
        
        if(this.checkIfBookisLoan(Integer.parseInt(bookId))){
            throw new IllegalArgumentException(
                "The book specified cannot be loan"
            );
        }
        
        if(this.bookDAO.findById(Integer.parseInt(bookId)).getEntityId() == 0){
            throw new BookNotExistException("The book specified does not exist");
        }
        
        this.validateFields(bookId);
        
        this.loan.setStudentId(this.studentId);
        this.loan.setBookId(Integer.parseInt(bookId));
        this.loan.setLoanDate(this.getCurrentDate());
       
        Boolean result = this.loanDAO.save(this.loan);
        
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
    
    private String getCurrentDate(){
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
         LocalDateTime now = LocalDateTime.now();  
         return dtf.format(now);
    }
    
    public boolean checkIfBookisLoan(int bookId){
        
        Filter filter = new Filter("book_id", String.valueOf(bookId));
        
        ArrayList<Loan> loans = this.loanDAO.get(filter);
        
        boolean isLoan = false;
        
        for(int i = 0; i < loans.size(); i++){
            
            Loan loan = loans.get(i);
            
            if((loan.getBookId() == bookId) || loan.getDevolutionId() != 0){
                isLoan = true;
                break;
            }
           
        }
        
        return isLoan;
    }
    
    
}
