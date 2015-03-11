/*
 * WineMainFrame.java
 *
 * Created on June 11, 2007, 12:25 PM
 */
package winemanager;

import java.awt.Font;
import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 *
 * @author Luiso
 */
public class WineMainFrame extends javax.swing.JFrame {

    private static boolean infoChanged = false;
    private static ArrayList<Vino> vinos = new ArrayList<Vino>();
    private boolean saved = false;
    private String dirName = System.getProperty("user.dir");
    private JFrame frame = new JFrame();
    private JFrame listFrame;
    private static ListFrame lf;
    private static Vector<String> countries = new Vector<String>();
    private static Vector<Integer> years = new Vector<Integer>();
    private static Vector<String> regions = new Vector<String>();
    private static Vector<String> types = new Vector<String>();
    private static boolean configDataChanged = false;
    private static double version = 2.1;
    public static Locale currentLocale;
    public static ResourceBundle strings;
    public static ArrayList<String> availableLanguages = new ArrayList<String>();

    /**
     * Creates new form WineMainFrame
     */
    public WineMainFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        initComponents();
        java.net.URL imageURL = getClass().getClassLoader().getResource("winemanager/vinos/wine1.jpg");
        ImageIcon wine = new ImageIcon(imageURL);
        jLabel2.setIcon(wine);
        loadWines();
        loadConfigData();
        loadYears();
        this.setTitle("Wine Manager v" + version);
        this.setIconImage(Utils.getFrameIcon());
        setLocale();
        setUIText();
    }
    
    private void setUIText(){
        btnAddWine.setText(strings.getString("btnAddWine"));
        btnModifyQty.setText(strings.getString("btnModifyQty"));
        btnStatistics.setText(strings.getString("btnStatistics"));
        btnViewList.setText(strings.getString("btnViewList"));
        menuItemExit.setText(strings.getString("menuItemExit"));
        menuItemOpen.setText(strings.getString("menuItemOpen"));
        menuItemExportExcel.setText(strings.getString("menuItemExportExcel"));
        menuItemPrint.setText(strings.getString("btnPrint"));
        menuItemSave.setText(strings.getString("menuItemSave"));
        menuItemSaveAs.setText(strings.getString("menuItemSaveAs"));
        menuItemSettings.setText(strings.getString("menuItemSettings"));
    }

    private void setLocale() {
        String[] langs = Locale.getISOLanguages();
        for (String lang : langs) {
            //System.out.println(lang);
            URL rb = getClass().getClassLoader().getResource("winemanager/locales/StringsBundle_" + lang + "_US.properties");
            if (rb != null) {
                availableLanguages.add(Locale.forLanguageTag(lang).getDisplayLanguage() + " - " + lang);
            }
        }
        if(availableLanguages.isEmpty()){
            availableLanguages.add("English - en");
        }
        if (currentLocale == null) {
            currentLocale = new Locale("en", "US");
        }
        strings = ResourceBundle.getBundle("winemanager/locales/StringsBundle", currentLocale);
    }

    public static ListFrame getWineListFrame() {
        return lf;
    }

    public static void setConfigDataChanged(boolean value) {
        configDataChanged = value;
    }

    public static Vector<String> getTypes() {
        return types;
    }

    public static Vector<String> getRegions() {
        return regions;
    }

    public static Vector<Integer> getYears() {
        return years;
    }

    public static void setTypes(Vector<String> t) {
        types = t;
    }

    public static void setCountries(Vector<String> t) {
        countries = t;
    }

    public static void setRegions(Vector<String> t) {
        regions = t;
    }

    private void loadYears() {
        Calendar cal = Calendar.getInstance();
        for (int i = 1950; i <= cal.get(Calendar.YEAR); i++) {
            years.add(new Integer(i));
        }
    }

    private void loadWines() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(dirName + "\\vinos.dat"));
            try {
                vinos = (ArrayList<Vino>) in.readObject();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(WineMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadConfigData() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(dirName + "\\config.dat"));
            try {
                countries = (Vector<String>) in.readObject();
                regions = (Vector<String>) in.readObject();
                types = (Vector<String>) in.readObject();
                version = in.readDouble();
                currentLocale = (Locale) in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(WineMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WineMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (countries == null) {
                    countries.addAll(Utils.defaultCountries());
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(WineMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveConfigData() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(dirName + "\\config.dat"));
            out.writeObject(countries);
            out.writeObject(regions);
            out.writeObject(types);
            out.writeDouble(version);
            out.writeObject(currentLocale);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    public static Vector<String> getCountries() {
        return countries;
    }

    public static ArrayList<Vino> getVinos() {
        return vinos;
    }

    public static void setInfoChanged(boolean infoChanged) {
        WineMainFrame.infoChanged = infoChanged;
    }

    private boolean tableFrameExists() {
        try {
            return lf.isShowing();
        } catch (Exception e) {
            return false;
        }
    }

    private void defaultSaveFile() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(dirName + "\\vinos.dat"));
            out.writeObject(vinos);
            saved = true;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnViewList = new javax.swing.JButton();
        btnAddWine = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnStatistics = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnModifyQty = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemOpen = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        menuItemSave = new javax.swing.JMenuItem();
        menuItemSaveAs = new javax.swing.JMenuItem();
        menuItemExportExcel = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        menuItemPrint = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        menuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItemSettings = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setAutoscrolls(true);

        btnViewList.setText("Ver lista");
        btnViewList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewListActionPerformed(evt);
            }
        });

        btnAddWine.setText("Añadir vino");
        btnAddWine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWineActionPerformed(evt);
            }
        });

        btnStatistics.setText("Ver estadisticas");
        btnStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticsActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winemanager/vinos/wine1.jpg"))); // NOI18N

        btnModifyQty.setText("Modificar cantidad");
        btnModifyQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyQtyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnViewList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStatistics, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddWine, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModifyQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddWine, btnModifyQty, btnStatistics, btnViewList});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, 0, 346, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddWine, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModifyQty, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnViewList, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddWine, btnModifyQty, btnStatistics, btnViewList});

        jMenu1.setText("Menu");

        menuItemOpen.setText("Abrir archivo");
        menuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemOpen);
        jMenu1.add(jSeparator3);

        menuItemSave.setText("Salvar");
        menuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemSave);

        menuItemSaveAs.setText("Salvar en otro lugar");
        menuItemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveAsActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemSaveAs);

        menuItemExportExcel.setText("Exportar a Excel");
        menuItemExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExportExcelActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemExportExcel);
        jMenu1.add(jSeparator1);

        menuItemPrint.setText("Imprimir lista");
        menuItemPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPrintActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemPrint);
        jMenu1.add(jSeparator2);

        menuItemExit.setText("Salir");
        menuItemExit.setToolTipText("Exit");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        menuItemSettings.setText("Editar Opciones");
        menuItemSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSettingsActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemSettings);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-527)/2, (screenSize.height-427)/2, 527, 427);
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPrintActionPerformed
        if (!tableFrameExists()) {
            lf = new ListFrame();
        }
        MessageFormat header = new MessageFormat("Page {0,number,integer}");
        try {
            JTable table = lf.getTable();
            table.setFont(new Font("Arial", Font.PLAIN, 15));
            table.print(JTable.PrintMode.FIT_WIDTH, header, null);
            lf.dispose();
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(null, strings.getString("wineMainFrame_errorPrinting") + e.getMessage());
        }
    }//GEN-LAST:event_menuItemPrintActionPerformed

    private void btnStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticsActionPerformed
        StatisticsFrame sf = new StatisticsFrame();
        sf.setVisible(true);
    }//GEN-LAST:event_btnStatisticsActionPerformed

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        if (!saved) {
            int chooseInput = JOptionPane.showConfirmDialog(frame, strings.getString("wineMainFrame_confirmSave"));
            if (chooseInput == 0) {
                defaultSaveFile();
                System.exit(0);
            }
            if (chooseInput == 1) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_menuItemExitActionPerformed

    private void menuItemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveAsActionPerformed
        JFileChooser chooser = new JFileChooser();
        MyFileFilter filter = new MyFileFilter();
        chooser.setFileFilter(filter);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String filename = selectedFile.getAbsolutePath();
            if (selectedFile.exists()) {
                int input = JOptionPane.showConfirmDialog(frame, strings.getString("wineMainFrame_confirmOverwrite"));
                if (input == 0) {
                    ObjectOutputStream out = null;
                    try {
                        out = new ObjectOutputStream(new FileOutputStream(filename));
                        out.writeObject(vinos);
                        JOptionPane.showMessageDialog(frame, strings.getString("wineMainFrame_successSave"));
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    } finally {
                        try {
                            out.close();
                        } catch (IOException ex) {
                            Logger.getLogger(WineMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                ObjectOutputStream out = null;
                try {
                    out = new ObjectOutputStream(new FileOutputStream(filename + ".dat"));
                    out.writeObject(vinos);
                    JOptionPane.showMessageDialog(frame, strings.getString("wineMainFrame_successSave"));
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } finally {
                    try {
                        out.close();
                    } catch (IOException ex) {
                        Logger.getLogger(WineMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_menuItemSaveAsActionPerformed

    private void btnViewListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewListActionPerformed
        if (vinos.isEmpty()) {
            JOptionPane.showMessageDialog(frame, strings.getString("msg_EmptyWineList"));
        } else if (tableFrameExists()) {
            lf.toFront();
            lf.setState(Frame.NORMAL);
        } else {
            lf = new ListFrame();
            lf.setLocationRelativeTo(null);
            lf.setVisible(true);
        }
    }//GEN-LAST:event_btnViewListActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (configDataChanged) {
            saveConfigData();
        }
        if (infoChanged) {
            if (!saved) {
                int chooseInput = JOptionPane.showConfirmDialog(frame, strings.getString("wineMainFrame_confirmSave"));
                if (chooseInput == 0) {
                    defaultSaveFile();
                    System.exit(0);
                }
                if (chooseInput == 1) {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnAddWineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWineActionPerformed
        AddWineFrame awf = new AddWineFrame(listFrame);
        awf.setLocationRelativeTo(null);
        awf.setVisible(true);
    }//GEN-LAST:event_btnAddWineActionPerformed

    private void menuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveActionPerformed
        defaultSaveFile();
    }//GEN-LAST:event_menuItemSaveActionPerformed

    private void btnModifyQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyQtyActionPerformed
        QuantityChange qc = new QuantityChange();
        qc.setLocationRelativeTo(null);
        qc.setVisible(true);
    }//GEN-LAST:event_btnModifyQtyActionPerformed

    private void menuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenActionPerformed
        JFileChooser chooser = new JFileChooser();
        MyFileFilter filter = new MyFileFilter();
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            File current = new File(dirName + "\\vinos.dat");
            current.renameTo(new File(dirName + "\\vinosOld.dat"));
            try {
                Utils.copy(selectedFile, current);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(new FileInputStream(dirName + "\\vinos.dat"));
                try {
                    vinos = (ArrayList<Vino>) in.readObject();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(WineMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_menuItemOpenActionPerformed

    private void menuItemSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSettingsActionPerformed
        OptionsEditor oe = new OptionsEditor();
        oe.setLocationRelativeTo(null);
        oe.setVisible(true);
    }//GEN-LAST:event_menuItemSettingsActionPerformed

    private void menuItemExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExportExcelActionPerformed
        JFileChooser chooser = new JFileChooser();
        MyExcelFilter filter = new MyExcelFilter();
        chooser.setFileFilter(filter);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String filename = selectedFile.getAbsolutePath();
            boolean doWrite = true;
            if (selectedFile.exists()) {
                int input = JOptionPane.showConfirmDialog(frame, strings.getString("wineMainFrame_confirmOverwrite"));
                if (input != 0) {
                    doWrite = false;
                }
            }
            if (doWrite) {
                if (!filename.endsWith(".xls")) {
                    selectedFile = new File(filename.concat(".xls"));
                }
                boolean noErrors = true;
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(selectedFile));
                    BasicTableModel wineModel = new BasicTableModel(vinos);
                    for (String column : wineModel.getColumns()) {
                        writer.write(column);
                        writer.write("\t");
                    }
                    writer.write(Utils.newline);
                    int columnCount = wineModel.getColumnCount();
                    for (int i = 0; i < wineModel.getRowCount(); i++) {
                        for (int j = 0; j < columnCount; j++) {
                            Object currentValue = wineModel.getValueAt(i, j);
                            if (currentValue == null) {
                                writer.write("");
                            } else {
                                writer.write(currentValue.toString());
                            }
                            writer.write("\t");
                        }
                        writer.write(Utils.newline);
                    }
                } catch (FileNotFoundException ex) {
                    noErrors = false;
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (IOException ex) {
                    noErrors = false;
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } finally {
                    try {
                        writer.close();
                        if (noErrors) {
                            JOptionPane.showMessageDialog(frame, strings.getString("wineMainFrame_successSave"));
                        } else {
                            JOptionPane.showMessageDialog(frame, strings.getString("wineMainFrame_errorSaving"),"Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(WineMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_menuItemExportExcelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WineMainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddWine;
    private javax.swing.JButton btnModifyQty;
    private javax.swing.JButton btnStatistics;
    private javax.swing.JButton btnViewList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemExportExcel;
    private javax.swing.JMenuItem menuItemOpen;
    private javax.swing.JMenuItem menuItemPrint;
    private javax.swing.JMenuItem menuItemSave;
    private javax.swing.JMenuItem menuItemSaveAs;
    private javax.swing.JMenuItem menuItemSettings;
    // End of variables declaration//GEN-END:variables
}
