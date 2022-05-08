package controller;

import model.repository.BookRepository;
import model.Book;
import java.util.ArrayList;

public class CreateAndUpdateBookController {

    private BookRepository bookRepository;

    private Book book;
    
    private int librarianId;

    public CreateAndUpdateBookController(int librarianId){
        this.librarianId = librarianId;
        this.bookRepository = new BookRepository();
        this.book = new Book();
    }

    public boolean createBook(String name, String isbn, String author) throws Exception{
           
        this.validateFields(name, isbn, author);
        
        this.book.setEntityId(this.getBookNewUniqueId());
        this.book.setName(name);
        this.book.setIsbn(isbn);
        this.book.setAuthor(author);
        
        Boolean result = this.bookRepository.save(this.book);
        
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
        
        boolean result = this.bookRepository.save(book);

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

        ArrayList<Book> books = this.bookRepository.get();

        if(books.isEmpty()){
            return 1;
        }

        int lastId = books.get(books.size()-1).getEntityId();
        lastId+=1;

        return lastId;
    }
}
