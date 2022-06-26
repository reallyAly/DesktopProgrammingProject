package model;

/**
 *
 * @author alysson
 */
public class Devolution {
    
    public static final String TABLE_NAME = "devolution";
    
    public static final String COLUMN_ENTITY_ID = "entity_id";
    
    public static final String COLUMN_LIBRARIAN_ID = "librarian_id";
    
    public static final String COLUMN_LOAN_ID = "loan_id";
    
    public static final String COLUMN_CREATEAD_AT = "created_at";
    
    private int entityId;
    
    private int librarianId;
    
    private int loanId;
    
    private String devolutionDate;

    public Devolution(
            int entityId, 
            int librarianId, 
            int loanId, 
            String devolutionDate
    ){
        this.entityId = entityId;
        this.librarianId = librarianId;
        this.loanId = loanId;
        this.devolutionDate = devolutionDate;
    }
    
    public Devolution() {
        this.entityId = 0;
        this.librarianId = 0;
        this.loanId = 0;
        this.devolutionDate = "";
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(String devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }
    
}
