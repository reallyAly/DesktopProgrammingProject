package controller.file;

import java.io.File;

/**
 *
 * @author alysson
 */
public abstract class FileController {
    
    protected File file = null;
    
    public abstract boolean read();
    
    public abstract boolean write(boolean append);
    
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
        this.file = new File(initialDir+"/src/files/"+filename);
    }
}
