package controller.file;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabricio@utfpr.edu.br
 */
public class FileBinController extends FileController {

    private Object object = null;
    private ObjectInputStream reader = null;
    private ObjectOutputStream writer = null;

    /**
     * @return the object
     */
    public Object getObject() {
        return this.object;
    }

    /**
     * @param object
     */
    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public boolean read() {
        try {
            this.reader = new ObjectInputStream(new FileInputStream(this.file));
            this.object = this.reader.readObject();
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