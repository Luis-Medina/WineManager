/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package winemanager;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Luis
 */
public class MyExcelFilter extends FileFilter{
    
    /** Creates a new instance of MyFileFilter */

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals("xls")) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return "Excel files";
    }
    
}
