package model.repository;


import java.util.ArrayList;
import controller.file.FileBinController;
import exception.BookNotExistException;
import exception.LoanNotExistException;
import javax.naming.CannotProceedException;
import model.Book;
import model.Loan;

/**
 *
 * @author alysson
 */
public class BookRepository implements Repository {
    
    private FileBinController fileBinController;
    
    private LoanRepository loanRepository;
    
    public BookRepository(){
        this.fileBinController = new FileBinController();
        this.loanRepository = new LoanRepository();
        this.fileBinController.setFile(Book.FILENAME);  
    }

    /**
     * Get book by specific Id
     * 
     *
     * @param id
     * 
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

    /**
     * Get all books
     *
     * @return ArrayList
     */
    @Override
    public ArrayList<Book> get() {
        
        boolean result = this.fileBinController.read();
        
        if(!result){
            return new ArrayList<>();
        }
  
        return (ArrayList<Book>) this.fileBinController.getObject();
    }

    /**
     * Save a new book
     *
     * @param b
     * @return Boolean
     * @throws Exception
     */
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
    
    /**
     * Remove a book
     *
     * @param id
     * @return Boolean
     * @throws BookNotExistException
     * @throws javax.naming.CannotProceedException
     * @throws exception.LoanNotExistException
     */
    public boolean delete(int id) throws BookNotExistException, CannotProceedException, LoanNotExistException{
            
        this.validateBook(id);
        
        ArrayList<Book> books = this.get();
        
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getEntityId()== id){
                Boolean result = this.deleteLoans(books.get(i).getEntityId());
                
                if(!result){
                    throw new CannotProceedException("A loan of this book cannot be deleted");
                }
                books.remove(i);
            }
        }
        
        this.fileBinController.setObject(books);
        return this.fileBinController.write(false);
    }
        
    private void validateBook(int bookId) throws BookNotExistException{
        if(this.findById(bookId) == null){
            throw new BookNotExistException("The book specified does not exist ");
        }
    }
    
    private boolean deleteLoans(int id) throws LoanNotExistException{
        ArrayList<Loan> loans = this.loanRepository.get();
        
        for(int i = 0; i < loans.size(); i++){
            if(loans.get(i).getBookId() == id){
                return this.loanRepository.delete(loans.get(i).getEntityId());
            }
        }
        
        return false;
    }

}
