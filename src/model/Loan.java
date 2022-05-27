package model;

/**
 *
 * @author alysson
 */
public class Loan {
    
    private int entityId;
    
    private int studentId;
    
    private int bookId;
    
    private String loanDate;
    
    private String status;
    
    private String devolutionDate;
            

    public Loan() {
        this.entityId = 0;
        this.studentId = 0;
        this.bookId = 0;
        this.loanDate = "";
        this.status = "";
        this.devolutionDate = "";
    }

    public int getEntityId() {
        return entityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(String devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }
    
    
}
