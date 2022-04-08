package model;

/**
 *
 * @author alysson
 */
public class Book {
    
    public static final String FILENAME = "Books";
    
    private String name;
    
    private String isbn;
    
    private String releaseDate;

    public Book(String name, String isbn, String releaseDate) {
        this.name = name;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
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
