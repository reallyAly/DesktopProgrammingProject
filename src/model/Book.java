package model;

/**
 *
 * @author alysson
 */
public class Book {
   
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
