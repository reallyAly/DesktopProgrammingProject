/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repository;

import controller.file.FileBinController;
import exception.LibrarianNotExistException;
import java.util.ArrayList;
import model.Librarian;

/**
 *
 * @author alysson
 */
public class LibrarianRepository implements Repository{
        
    private FileBinController fileBinController;
    
    public LibrarianRepository(){
        this.fileBinController = new FileBinController();
    }
    
    /**
     * Get student by specific id
     *
     * @param id
     * @return Librarian
     * @throws LibrarianNotExistException
     */
    @Override
    public Librarian findById(int id) throws LibrarianNotExistException{
        ArrayList<Librarian> students = this.get();
        
        for(Librarian stud: students){
            if(stud.getEntityId() == id){
                return stud;
            }
        }
        
        throw new LibrarianNotExistException("Cannot find a Librarian with the id:"+id);
    }
    
    /**
     * Get all librarians
     *
     * @return ArrayList
     */
    @Override
    public ArrayList<Librarian> get(){
        this.fileBinController.setFile(Librarian.FILENAME);  
        boolean result = this.fileBinController.read();
        
        if(!result){
            return new ArrayList<>();
        }
  
        return (ArrayList<Librarian>) this.fileBinController.getObject();
    }

    /**
     * Save a new librarian
     * 
     *
     * @param librarian
     * @return Boolean
     */
    @Override
    public boolean save(Object librarian) {
        this.fileBinController.addNewObject(librarian);
        return this.fileBinController.write(false);
    }
    
    /**
     * Get student by specific email
     * 
     *
     * @param email
     * @return
     * @throws LibrarianNotExistException
     */
    public Librarian findByEmail(String email) throws LibrarianNotExistException{
        ArrayList<Librarian> librarians = this.get();
        
        for(Librarian lib: librarians){
            if(lib.getEmail().equals(email)){
                return lib;
            }
        }
        
        throw new LibrarianNotExistException("Cannot find a Librarian with the e-mail:"+email);
    }
    
}
