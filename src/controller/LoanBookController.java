package controller;


import java.util.ArrayList;
import model.Loan;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import model.repository.BookRepository;
import model.repository.LoanRepository;

public class LoanBookController {

    private LoanRepository loanRepository;
    
    private BookRepository bookRepository;

    private Loan loan;
    
    private int studentId;

    public LoanBookController(int studentId){
        this.studentId = studentId;
        this.loanRepository = new LoanRepository();
        this.bookRepository = new BookRepository();
        this.loan = new Loan();
    }

    public boolean loanBook(String bookId) throws Exception{
        
        if(this.checkIfBookisLoan(Integer.parseInt(bookId))){
            throw new IllegalArgumentException(
                "The book specified cannot be loan"
            );
        }
        
        if(this.bookRepository.findById(Integer.parseInt(bookId)) == null){
            throw new Exception("The book specified does not exist");
        }
        
        this.validateFields(bookId);
        
        this.loan.setEntityId(this.getLoanNewUniqueId());
        this.loan.setStudentId(this.studentId);
        this.loan.setBookId(Integer.parseInt(bookId));
        this.loan.setStatus("LOAN");
        this.loan.setLoanDate(this.getCurrentDate());
        this.loan.setDevolutionDate("0000000");
        
        Boolean result = this.loanRepository.save(this.loan);
        
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
        ArrayList<Loan> loans = this.loanRepository.get();

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
    
    public boolean checkIfBookisLoan(int bookId){
        
        ArrayList<Loan> loans = this.loanRepository.get();
        
        boolean isLoan = false;
        
        for(int i = 0; i < loans.size(); i++){
            
            Loan loan = loans.get(i);
            
            if((loan.getBookId()== bookId) && loan.getStatus().equals("LOAN")){
                isLoan = true;
                break;
            }
           
        }
        
        return isLoan;
    }
    
    
}
