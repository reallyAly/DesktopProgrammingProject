package controller.file;

import java.util.StringTokenizer;
import model.Loan;
import java.util.ArrayList;
import model.Loan;

public class FileLoanController extends FileTextController {

    protected ArrayList<Loan> loans = new ArrayList<>();

    protected Loan loan;

    public void readLoan() {

        this.setFile(Loan.FILENAME);

        this.read();

        String aux = this.getText();
        String tk = String.valueOf(";")+String.valueOf("\n");
        StringTokenizer tokens = new StringTokenizer(aux, tk);

        while (tokens.hasMoreTokens()) {
            this.loan = this.getNewLoan();
            this.loan.setEntityId(Integer.parseInt(tokens.nextToken()));
            this.loan.setBookId(Integer.parseInt(tokens.nextToken()));
            this.loan.setStudentId(Integer.parseInt(tokens.nextToken()));
            this.loan.setLoanDate(tokens.nextToken());
            this.loan.setStatus(tokens.nextToken());
            this.loan.setDevolutionDate(tokens.nextToken());
            
            this.addNewLoan(this.loan);
        }
    }

    public boolean storeLoan(Loan loan) {

        String aux = loan.getEntityId()+ ";" +
                loan.getBookId()+ ";" +
                loan.getStudentId()+ ";" +
                loan.getLoanDate()+ ";" +
                loan.getStatus()+ ";" +
                loan.getDevolutionDate()+ ";" + "\n";
        
        setText(aux);

        this.setFile(Loan.FILENAME);

        return this.write(true);

    }
    
    public void setLoans(ArrayList<Loan> loans){
        
        this.getFile().delete();
        
        for(int i = 0; i < loans.size(); i++){
            this.storeLoan(loans.get(i));
        }

    }

    public ArrayList<Loan> getLoans() {
        return this.loans;
    }

    private Loan getNewLoan(){
        return new Loan();
    }

    private void addNewLoan(Loan librarian){
        this.loans.add(librarian);
    }

    public Loan findLoanById(int id){
        
        if(this.loans.isEmpty()){
            this.readLoan();
        }
        
        ArrayList<Loan> loans = this.getLoans();

        for(int i = 0; i < loans.size(); i++){
            if(loans.get(i).getEntityId() == id){
                return loans.get(i);
            }
        }
        
        return null;
        
    }
    
    public Loan findLoanByBookId(int bookId){
        
        if(this.loans.isEmpty()){
            this.readLoan();
        }
        
        ArrayList<Loan> loans = this.getLoans();

        for(int i = 0; i < loans.size(); i++){
            if(loans.get(i).getEntityId() == bookId){
                return loans.get(i);
            }
        }
        
        return null;
        
    }
    
    public boolean checkIfBookisLoan(int bookId){
        
        if(this.loans.isEmpty()){
            this.readLoan();
        }
        
        ArrayList<Loan> loans = this.getLoans();
        
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