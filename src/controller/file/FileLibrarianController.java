package controller.file;

import java.util.StringTokenizer;
import model.Librarian;
import java.util.ArrayList;

public class FileLibrarianController extends FileTextController {

    protected ArrayList<Librarian> librarians = new ArrayList<>();

    protected Librarian librarian;

    public void readLibrarian() {

        this.setFile(Librarian.FILENAME);

        this.read();

        String aux = this.getText();
        String tk = String.valueOf(";")+String.valueOf("\n");
        StringTokenizer tokens = new StringTokenizer(aux, tk);

        while (tokens.hasMoreTokens()) {
            this.librarian = this.getNewLibrarian();
            this.librarian.setEntityId(Integer.parseInt(tokens.nextToken()));
            this.librarian.setFirstname(tokens.nextToken());
            this.librarian.setLastname(tokens.nextToken());
            this.librarian.setEmail(tokens.nextToken());
            this.librarian.setPassword(tokens.nextToken());

            this.addNewLibrarian(this.librarian);
        }
    }

    public boolean storeLibrarian(Librarian librarian) {

        String aux = librarian.getEntityId()+ ";" +
                librarian.getFirstname()+ ";" +
                librarian.getLastname()+ ";" +
                librarian.getEmail() + ";" +
                librarian.getPassword() + "\n";

        setText(aux);

        this.setFile(Librarian.FILENAME);

        return this.write(true);

    }

    public ArrayList<Librarian> getLibrarians() {
        return this.librarians;
    }

    public Librarian getNewLibrarian(){
        return new Librarian();
    }

    private void addNewLibrarian(Librarian librarian){
        this.librarians.add(librarian);
    }
}