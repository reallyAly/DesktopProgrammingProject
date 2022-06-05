package controller;

import java.io.File;

/**
 *
 * @author alysson
 */
public class FileController {
    
    protected File file = null;
    
    /**
     * @return File
     */
    public File getFile() {
        return this.file;
    }

    /**
     * @param filename name of the file
     */
    public void setFile(String filename) {
        file = null;
        String initialDir = System.getProperty("user.dir");
        this.file = new File(initialDir+"/src/"+filename);
    }
}