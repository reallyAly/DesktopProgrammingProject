package model;

/**
 *
 * @author alysson
 */
public class Book implements java.io.Serializable{
    
    public static final String FILENAME = "Books.bin";

    private int entityId;
    
    private String name;
    
    private String isbn;
    
    private String author;

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
