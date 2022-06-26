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
    
    public static final String REPORT_LOAN_BY_STUDENT_TEMPLATE_FILENAME = "loans_by_student.jrxml";
    
    public static final String REPORT_LOAN_TEMPLATE_FILENAME = "loans_report.jrxml";
    
    public static final String REPORT_LOAN_PER_BOOKS_TEMPLATE_FILENAME = "loans_per_books.jrxml";
    
    private int entityId;
    
    private int studentId;
    
    private int bookId;
    
    private String loanDate;
    
    private int devolutionId;

    public Loan(
            int entityId,
            int studentId, 
            int bookId, 
            int devolutionId, 
            String loanDate
    ) {
        this.entityId = entityId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.devolutionId = devolutionId;
        this.loanDate = loanDate;
    }
            
    public Loan() {
        this.entityId = 0;
        this.studentId = 0;
        this.bookId = 0;
        this.devolutionId = 0;
        this.loanDate = "";
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
