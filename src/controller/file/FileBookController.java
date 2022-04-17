package controller.file;

import model.Book;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileBookController extends FileTextController {

    protected ArrayList<Book> books = new ArrayList<>();

    protected Book book;

    public void readBook() {

        this.setFile(Book.FILENAME);

        this.read();

        String aux = this.getText();
        String tk = String.valueOf(";")+String.valueOf("\n");
        StringTokenizer tokens = new StringTokenizer(aux, tk);

        while (tokens.hasMoreTokens()) {
            this.book = this.getNewBook();
            this.book.setEntityId(Integer.parseInt(tokens.nextToken()));
            this.book.setName(tokens.nextToken());
            this.book.setIsbn(tokens.nextToken());
            this.book.setAuthor(tokens.nextToken());
            this.addNewBook(this.book);
        }
    }

    public boolean storeBook(Book book) {

        this.book = this.getNewBook();

        String aux = this.book.getEntityId()+ ";" +
                this.book.getName()+ ";" +
                this.book.getIsbn()+ ";" +
                this.book.getAuthor()+ "\n";

        setText(aux);

        this.setFile(Book.FILENAME);

        return this.write(true);

    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    private Book getNewBook(){
        return new Book();
    }

    private void addNewBook(Book book){
        this.books.add(book);
    }

}
