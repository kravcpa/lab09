package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String DEFAULT_FILE_PATH = System.getProperty("user.home") + File.separator + "output.txt";

    private File currentFile;

    /**
     * Constructor.
     */
    public Controller() {
        currentFile = new File(DEFAULT_FILE_PATH);
    }

    /**
     * setCurrentFile.
     * @param file
     */
    public void setCurrentFile(final File file) {
        this.currentFile = file; // new File(file.getAbsolutePath());
    }

    /**
     * getCurrentFile.
     * @return file
     */
    public File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * getCurrentFilePath.
     * @return filev
     */
    public String getCurrentFilePath() {
        return this.currentFile.getAbsolutePath();
    }

    /**
     * saveString.
     * @param string
     */
    public void saveString(final String string) throws IOException {
        try (PrintStream ps = new PrintStream(currentFile, StandardCharsets.UTF_8)) {
            ps.println(string);
        } catch (IOException e) {
            e.printStackTrace(); // NOPMD
            throw e;
        }
    }
}
