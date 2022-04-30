package controller.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author fabricio@utfpr.edu.br
 */
public class FileTextController extends FileController {

    private String text = null;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;

    /**
     * @return the text
     */
    public String getText() {
        return this.text;
    }

    /**
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean read() {
        StringBuilder line = new StringBuilder();
        try {
            this.reader = new BufferedReader(new FileReader(this.file));
            while (this.reader.ready()) {
                line.append(this.reader.readLine()).append("\n");
            }
            this.reader.close();
            setText(line.toString());
            return true;
        } catch (FileNotFoundException erro) {
            System.err.println(erro.getMessage() + "Arquivo não encontrado.");
            return false;
        } catch (IOException erro) {
            System.err.println(erro.getMessage() + "Erro ao ler arquivo.");
            return false;
        }
    }

    /**
     * @param append
     */
    @Override
    public boolean write(boolean append) {
        if (this.file != null) {
            try {
                this.writer = new BufferedWriter(new FileWriter(this.file, append));
                this.writer.write(getText());
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