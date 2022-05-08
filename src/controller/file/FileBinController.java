package controller.file;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author alysson
 */
public class FileBinController extends FileController {

    private ArrayList<Object> object;
    private ObjectInputStream reader = null;
    private ObjectOutputStream writer = null;

    public FileBinController() {
        this.object = new ArrayList<>();
    }

    /**
     * @return the object
     */
    public ArrayList<?> getObject() {
        return this.object;
    }

    /**
     * @param object
     */
    public void setObject(ArrayList<?> object) {
        this.object = (ArrayList<Object>) object;
    }
    
    public void addNewObject(Object object){
        this.object.add(object);
    }

    @Override
    public boolean read() {
        try {
            this.reader = new ObjectInputStream(new FileInputStream(this.file));
            this.object = (ArrayList<Object>) this.reader.readObject();
            this.reader.close(); 
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "Erro ao ler arquivo.");
            return false;
        }
        return true;
    }

    /**
     * @param append
     * @return Boolean
     */
    @Override
    public boolean write(boolean append) {
        if (this.file != null) {
            try {
                this.writer = new ObjectOutputStream(new FileOutputStream(this.file, append));
                this.writer.writeObject(this.object);
                this.writer.close();
                return true;
            } catch (IOException erro) {
                System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
                return false;
            }
        } else {
            return false;
        }
    }
    
}