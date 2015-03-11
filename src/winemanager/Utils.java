/*
 * Utils.java
 *
 * Created on August 25, 2006, 12:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package winemanager;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Luiso
 */
public class Utils {

    public final static String dat = "dat";
    public final static String newline = System.getProperty("line.separator");

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static Image createImageIcon(String path) {
        java.net.URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static synchronized void move(File src, File dest) throws FileNotFoundException, IOException {
        copy(src, dest);
        src.delete();
    }

    public static synchronized void copy(File src, File dest) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dest);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

     protected static Image getFrameIcon() {
        String path = "/winemanager/vinos/wine1.jpg";
        java.net.URL imgURL = WineMainFrame.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
     
    protected static ArrayList<String> defaultCountries(){
        ArrayList<String> countries = new ArrayList<String>();
        countries.add("Albania");
        countries.add("Algeria");
        countries.add("Argentina");
        countries.add("Armenia");
        countries.add("Australia");
        countries.add("Austria");
        countries.add("Azerbaijan");
        countries.add("Belarus");
        countries.add("Belgium");
        countries.add("Bolivia");
        countries.add("Bosnia and Herzegovina");
        countries.add("Brazil");
        countries.add("Bulgaria");
        countries.add("Canada");
        countries.add("Chile");
        countries.add("China");
        countries.add("Croatia");
        countries.add("Cuba");
        countries.add("Cyprus");
        countries.add("Czech Republic");
        countries.add("Egypt");
        countries.add("Ethiopia");
        countries.add("France");
        countries.add("Georgia");
        countries.add("Germany");
        countries.add("Greece");
        countries.add("Hungary");
        countries.add("India");
        countries.add("Israel");
        countries.add("Italy");
        countries.add("Japan");
        countries.add("Kazakhstan");
        countries.add("Kyrgyzstan");
        countries.add("Lebanon");
        countries.add("Liechtenstein");
        countries.add("Lithuania");
        countries.add("Luxembourg");
        countries.add("Macedonia");
        countries.add("Madagascar");
        countries.add("Malta");
        countries.add("Mexico");
        countries.add("Moldova");
        countries.add("Morocco");
        countries.add("New Zealand");
        countries.add("Panama");
        countries.add("Paraguay");
        countries.add("Peru");
        countries.add("Portugal");
        countries.add("Romania");
        countries.add("Russia");
        countries.add("Serbia and Montenegro");
        countries.add("Slovakia");
        countries.add("Slovenia");
        countries.add("South Africa");
        countries.add("Spain");
        countries.add("Switzerland");
        countries.add("Syria");
        countries.add("Tajikistan");
        countries.add("The Netherlands");
        countries.add("Tunisia");
        countries.add("Turkey");
        countries.add("Turkmenistan");
        countries.add("Ukraine");
        countries.add("United Kingdom");
        countries.add("United States");
        countries.add("Uruguay");
        countries.add("Uzbekistan");
        countries.add("Venezuela");
        countries.add("Zimbabwe");  
        return countries;
    }

}
