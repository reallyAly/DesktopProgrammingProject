package controller;

import controller.file.FileBookController;
import model.Book;
import java.util.ArrayList;

public class ManagementBookController {

    private FileBookController fileBookController;

    private Book book;
    
    private int librarianId;

    public ManagementBookController(int librarianId){
        this.librarianId = librarianId;
        this.fileBookController = new FileBookController();
        this.book = new Book();
        
    }

    public boolean createBook(String name, String isbn, String author) throws Exception{
           
        this.validateFields(name, isbn, author);
        
        this.book.setEntityId(this.getBookNewUniqueId());
        this.book.setName(name);
        this.book.setIsbn(isbn);
        this.book.setAuthor(author);
        
        boolean result = this.fileBookController.storeBook(this.book);
        
        if(!result){
            throw new Exception("Error trying to save the new book");
        }
        
        return result;
    }
    
    public boolean updateBook(
            Book book, 
            String name, 
            String isbn, 
            String author
    ) throws Exception{
           
        this.validateFields(name, isbn, author);
        
        book.setName(name);
        book.setIsbn(isbn);
        book.setAuthor(author);
        
        ArrayList<Book> books = this.fileBookController.getBooks();
        
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getEntityId() == book.getEntityId()){
                books.set(i, book);
                break;
            }
        }
        
        boolean result = this.fileBookController.setBooks(books);

        if(!result){
            throw new Exception("Error trying to update the book");
        }
        
        return result;
    }
    
    public void validateFields(
            String name, 
            String isbn, 
            String author
    ) throws IllegalArgumentException {
        if(name.isBlank()){
            
            throw new IllegalArgumentException(
                "The name field cannot be empty"
            );
            
        }else if(isbn.isBlank()){
            
            throw new IllegalArgumentException(
                "The isbn field cannot be empty"
            );
            
        }else if(author.isBlank()){
            
            throw new IllegalArgumentException(
                "The author field cannot be empty"
            );
            
        }
    }

    private int getBookNewUniqueId(){

        this.fileBookController.readBook();
        ArrayList<Book> books = this.fileBookController.getBooks();

        if(books.isEmpty()){
            return 1;
        }

        int lastId = books.get(books.size()-1).getEntityId();
        lastId+=1;

        return lastId;
    }
}
