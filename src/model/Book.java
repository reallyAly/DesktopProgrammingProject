package model;

/**
 *
 * @author alysson
 */
public class Book {
    
    public static final String FILENAME = "Books";

    private int entityId;
    
    private String name;
    
    private String isbn;
    
    private String releaseDate;

    public Book() {
        this.entityId = 0;
        this.name = "";
        this.isbn = "";
        this.releaseDate = "";
    }

    public int getEntityId(){
        return this.entityId;
    }

    public void setEntityId(int entityId){
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
