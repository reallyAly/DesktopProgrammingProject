package model;

/**
 *
 * @author alysson
 */
public class Book {
    
    public static final String TABLE_NAME = "book";
    
    public static final String COLUMN_ENTITY_ID = "entity_id";
    
    public static final String COLUMN_BOOK_NAME = "book_name";
    
    public static final String COLUMN_ISBN = "isbn";
    
    public static final String COLUMN_AUTHOR = "author";
    
    public static final String REPORT_BOOK_TEMPLATE_FILENAME = "books_report.jrxml";
   
    private int entityId;
    
    private String name;
    
    private String isbn;
    
    private String author;

    public Book(int entityId, String name, String isbn, String author) {
        this.entityId = entityId;
        this.name = name;
        this.isbn = isbn;
        this.author = author;
    }
    
    public Book() {
        this.entityId = 0;
        this.name = "";
        this.isbn = "";
        this.author = "";
    }

    public int getEntityId(){
        return this.entityId;
    }

    public void setEntityId(int entityId){
        this.entityId = entityId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
