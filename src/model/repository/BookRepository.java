package model.repository;


import java.util.ArrayList;
import controller.file.FileBinController;
import model.Book;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alysson
 */
public class BookRepository implements Repository {
    
    private FileBinController fileBinController;
    
    public BookRepository(){
        this.fileBinController = new FileBinController();
    }

    /**
     *
     * @param id
     * @return Book
     */
    @Override
    public Book findById(int id) {
        ArrayList<Book> books = this.get();
        
        for(Book book: books){
            if(book.getEntityId() == id){
                return book;
            }
        }
        
        return null;
    }

    @Override
    public ArrayList<Book> get() {
        this.fileBinController.setFile(Book.FILENAME);  
        boolean result = this.fileBinController.read();
        
        if(!result){
            return new ArrayList<>();
        }
  
        return (ArrayList<Book>) this.fileBinController.getObject();
    }

    @Override
    public boolean save(Object b) throws Exception {
        
        ArrayList<Book> books = this.get();
        
        Book book = (Book) b;
        
        Boolean isUpdate = false;
        
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getEntityId() == book.getEntityId()){
                books.set(i, book);
                isUpdate = true;
                break;
            }
        }
        
        if(isUpdate){
            this.fileBinController.setObject(books);
        }else{
            this.fileBinController.addNewObject(book);
        }

        return this.fileBinController.write(false);
    }
    
}
