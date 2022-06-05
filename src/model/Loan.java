package model;

/**
 *
 * @author alysson
 */
public class Loan {
    
    public static final String TABLE_NAME = "loan";
    
    public static final String COLUMN_ENTITY_ID = "entity_id";
    
    public static final String COLUMN_STUDENT_ID = "student_id";
    
    public static final String COLUMN_BOOK_ID = "book_id";
    
    public static final String COLUMN_DEVOLUTION_ID = "devolution_id";
    
    public static final String COLUMN_CREATED_AT = "created_at";
    
    private int entityId;
    
    private int studentId;
    
    private int bookId;
    
    private String loanDate;
    
    private int devolutionId;

    public Loan(int entityId, int studentId, int bookId, String loanDate, int devolutionId) {
        this.entityId = entityId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.devolutionId = devolutionId;
    }
            
    public Loan() {
        
    }
    public int getEntityId() {
        return entityId;
    }

    public int getDevolutionId() {
        return devolutionId;
    }

    public void setDevolutionId(int devolutionId) {
        this.devolutionId = devolutionId;
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
