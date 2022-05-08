package model.repository;


import controller.file.FileBinController;
import exception.LoanNotExistException;
import java.util.ArrayList;
import model.Loan;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alysson
 */
public class LoanRepository implements Repository{
        private FileBinController fileBinController;
    
    public LoanRepository(){
        this.fileBinController = new FileBinController();
    }

    /**
     * Get Loan by specific ID
     * 
     *
     * @param id
     * @return Loan
     */
    @Override
    public Loan findById(int id) {
        ArrayList<Loan> loans = this.get();
        
        for(Loan loan: loans){
            if(loan.getEntityId() == id){
                return loan;
            }
        }
        
        return null;
    }

    /**
     * Get all loans
     * 
     *
     * @return
     */
    @Override
    public ArrayList<Loan> get() {
        this.fileBinController.setFile(Loan.FILENAME);  
        boolean result = this.fileBinController.read();
        
        if(!result){
            return new ArrayList<>();
        }
  
        return (ArrayList<Loan>) this.fileBinController.getObject();
    }

    /**
     * Save a new loan
     *
     * @param b
     * @return Boolean
     * @throws Exception
     */
    @Override
    public boolean save(Object b) throws Exception {
        
        ArrayList<Loan> loans = this.get();
        
        Loan loan = (Loan) b;
        
        Boolean isUpdate = false;
        
        for(int i = 0; i < loans.size(); i++){
            if(loans.get(i).getEntityId() == loan.getEntityId()){
                loans.set(i, loan);
                isUpdate = true;
                break;
            }
        }
        
        if(isUpdate){
            this.fileBinController.setObject(loans);
        }else{
            this.fileBinController.addNewObject(loan);
        }

        return this.fileBinController.write(false);
    }
    
    /**
     * Remove a book
     *
     * @param id
     * @return Boolean
     * @throws LoanNotExistException
     */
    public boolean delete(int id) throws LoanNotExistException{
            
        this.validateLoan(id);
        
        ArrayList<Loan> loans = this.get();
        
        for(int i = 0; i < loans.size(); i++){
            if(loans.get(i).getEntityId()== id){
                loans.remove(i);
            }
        }
        
        this.fileBinController.setObject(loans);
        return this.fileBinController.write(false);
    }
        
    private void validateLoan(int loanId) throws LoanNotExistException{
        if(this.findById(loanId) == null){
            throw new LoanNotExistException("The book specified does not exist ");
        }
    }

}