package controller;

import controller.file.FileBookController;
import model.Book;

import java.util.ArrayList;

public class CreateBookController {

    private FileBookController fileBookController;

    private Book book;

    public CreateBookController(){
        
        this.fileBookController = new FileBookController();
        this.book = new Book();
        
    }

    public boolean createBook(String name, String isbn, String releaseDate){
           
        this.validateFields(name, isbn, releaseDate);
        
        this.book.setEntityId(this.getNewUniqueId());
        this.book.setName(name);
        this.book.setIsbn(isbn);
        this.book.setReleaseDate(releaseDate);

        return this.fileBookController.storeBook(this.book);

    }
    
    public void validateFields(String name, String isbn, String releaseDate) throws IllegalArgumentException {
        if(name.isBlank()){
            
            throw new IllegalArgumentException(
                "The name field cannot be empty"
            );
            
        }else if(isbn.isBlank()){
            
            throw new IllegalArgumentException(
                "The isbn field cannot be empty"
            );
            
        }else if(releaseDate.isBlank()){
            
            throw new IllegalArgumentException(
                "The realease Date field cannot be empty"
            );
            
        }
    }

    private int getNewUniqueId(){

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
