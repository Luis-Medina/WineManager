/*
 * ExampleFileFilter.java
 *
 * Created on August 25, 2006, 12:38 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package winemanager;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import winemanager.*;

/**
 *
 * @author Luiso
 */
public class MyFileFilter extends FileFilter{
    
    /** Creates a new instance of MyFileFilter */

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.dat)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return ".dat files";
    }
    
}
