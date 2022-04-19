/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.file.FileBookController;
import java.util.ArrayList;
import model.Book;

/**
 *
 * @author alysson
 */
public class DeleteBookController {
    
    private int librarianId;
    
    private FileBookController fileBookController;
    
    public DeleteBookController(int librarianId){
        this.librarianId = librarianId;
        this.fileBookController = new FileBookController();
        this.fileBookController.readBook();
    }
    
    public void deleteBook(int bookId) throws Exception{
        
        this.validateBook(bookId);
        
        ArrayList<Book> books = this.fileBookController.getBooks();
        
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getEntityId()== bookId){
                books.remove(i);
            }
        }
        
        this.fileBookController.setBooks(books);
       
    }
    
    private void validateBook(int bookId) throws Exception{
        if(this.fileBookController.getBookById(bookId) == null){
            throw new Exception("The book specified does not exist ");
        }
    }
    
    
}
