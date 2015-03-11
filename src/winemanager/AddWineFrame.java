/*
 * AddWineFrame.java
 *
 * Created on June 11, 2007, 1:06 PM
 */
package winemanager;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author  Luiso
 */
public class AddWineFrame extends javax.swing.JFrame {

    /**
     * Creates new form AddWineFrame
     */
    private String siteURL = "http://www.bottlecount.com/UPCDB/";
    private URL site;
    private Vino wine = new Vino();

    public AddWineFrame() {
        initComponents();
        setUIText();
    }

    public AddWineFrame(JFrame fr) {
        initComponents();
        setUIText();
        chooserRegion.setSelectedIndex(-1);
        chooserYear.setSelectedIndex(-1);
        chooserCountry.setSelectedIndex(-1);
        chooserAging.setSelectedIndex(-1);
        ClassLoader cldr = this.getClass().getClassLoader();
        java.net.URL imageURL = cldr.getResource("winemanager/vinos/wine_btl.jpg");
        ImageIcon image = new ImageIcon(imageURL);
        lblWineImage.setIcon(image);
        try {
            site = new URL(siteURL);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddWineFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIconImage(Utils.getFrameIcon());
    }
    
    private void setUIText(){
        btnSaveAndContinue.setText(WineMainFrame.strings.getString("btnSaveAndContinue"));
        btnSaveAndExit.setText(WineMainFrame.strings.getString("btnSaveAndExit"));
        btnCancel.setText(WineMainFrame.strings.getString("btnCancel"));
        btnSearch.setText(WineMainFrame.strings.getString("btnSearch"));
        lblAging.setText(WineMainFrame.strings.getString("lblAging"));
        lblColor.setText(WineMainFrame.strings.getString("lblColor"));
        lblComment.setText(WineMainFrame.strings.getString("lblComment"));
        lblName.setText(WineMainFrame.strings.getString("lblName"));
        lblCountry.setText(WineMainFrame.strings.getString("lblCountry"));
        lblYear.setText(WineMainFrame.strings.getString("lblYear"));
        lblPrice.setText(WineMainFrame.strings.getString("lblPrice"));
        lblQuantity.setText(WineMainFrame.strings.getString("lblQuantity"));
        lblRegion.setText(WineMainFrame.strings.getString("lblRegion"));
        lblPoints.setText(WineMainFrame.strings.getString("lblPoints"));    
        this.setTitle(WineMainFrame.strings.getString("addWineFrame_title"));
    }

    public static JButton getSearchButton() {
        return btnSearch;
    }

    private void processPage(String line) {
        if (line.contains("Wine Name:")) {
            txtName.setText(line.substring(89, line.length() - 10));
        } else if (line.contains("Winery:")) {
            //String temp = line.substring(86, line.length() - 10);
        } else if (line.contains("Varietal:")) {
            //String temp = line.substring(88, line.length() - 10);
        } else if (line.contains("Region:")) {
            chooserRegion.setSelectedItem(line.substring(86, line.length() - 10));
        } else if (line.contains("Year:")) {
            try{
            chooserYear.setSelectedItem(Integer.parseInt(line.substring(84, line.length() - 10)));
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error setting " + ": Year");
            }
        } else if (line.contains("Price:")) {
            try{
            String temp = line.substring(85, line.length() - 10);
            String stripped = temp.replaceAll("\\D", "");
            double price = Double.parseDouble(stripped);
            txtPrice.setValue(price);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error setting " + ": Price");
            }
        } else if (line.contains("Rating:")) {
            try{
            String temp = line.substring(86, line.length() - 10);
            String stripped = temp.replaceAll("\\D", "");
            int rating = Integer.parseInt(stripped);
            spinnerPoints.setValue(rating);
            }
            catch(Exception e){
                 JOptionPane.showMessageDialog(null, "Error setting " + ": Rating");
            }
        }
    }

    private boolean readyToAdd() {
        if (txtName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor escribir un nombre para el vino.");
            return false;
        }
        /*
        if (jFormattedTextField2.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Por favor escribir un UPC.");
        return false;
        }*/
        wine.setName(txtName.getText());      
        String type = (String) chooserAging.getSelectedItem();
        wine.setRegion(type);
        if (!WineMainFrame.getTypes().contains(type)) {
            chooserAging.addItem(type);
            WineMainFrame.setConfigDataChanged(true);
        }
        wine.setColor(chooserColor.getSelectedItem().toString());
        wine.setComment(txtComment.getText());
        wine.setCountry((String) chooserCountry.getSelectedItem());
        try {
            wine.setPoints((Integer) (spinnerPoints.getValue()));
        } catch (Exception e) {
        }
        try {
            wine.setPrice(Double.parseDouble(txtPrice.getText()));
        } catch (Exception e) {
        }
        String region = (String) chooserRegion.getSelectedItem();
        wine.setRegion(region);
        if (!WineMainFrame.getRegions().contains(region)) {
            chooserRegion.addItem(region);
            WineMainFrame.setConfigDataChanged(true);
        }
        try {
            wine.setYear((Integer) chooserYear.getSelectedItem());
        } catch (Exception e) {
        }
        wine.setQuantity(((Integer) spinnerQty.getValue()).intValue());
        wine.setTotalAmount(wine.getPrice() * wine.getQuantity());
        wine.setUPC(txtUPC.getText());
        return true;
    }

    private void searchUPCInWeb() {
        String upc = txtUPC.getText();
        boolean proceed = false;
        if (upc.length() == 12 || upc.length() == 13) {
            try {
                Long.parseLong(upc);
                proceed = true;
            } catch (Exception e) {
            }
        }
        if (!proceed) {
            JOptionPane.showMessageDialog(null, "UPC incorrecto. Intente nuevamente.");
        } else {
            btnSearch.setEnabled(false);
            setCursor(Cursor.WAIT_CURSOR);
            try {
                site = new URL(siteURL + upc);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            boolean pageFound = false;
            try {
                in = new BufferedReader(new InputStreamReader(site.openStream()));
                pageFound = true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "La pagina no fue encontrada.");
            }
            if (pageFound) {
                String inputLine;
                try {
                    while ((inputLine = in.readLine()) != null) {
                        processPage(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final JFrame temp = new JFrame();
                class CloseTask extends TimerTask {

                    public void run() {
                        temp.dispose();
                    }
                }
                if (txtName.getText().equals("")) {
                    JDialog dialog = new JDialog(temp, "Mensaje");
                    dialog.setSize(275, 140);
                    dialog.add(new Notifier());
                    dialog.setLocationRelativeTo(this);
                    dialog.setVisible(true);
                    Timer timer = new Timer();
                    timer.schedule(new CloseTask(), 5000);
                    txtUPC.requestFocusInWindow();
                } else {
                    btnSaveAndContinue.requestFocusInWindow();
                }
            }
            setCursor(Cursor.DEFAULT_CURSOR);
            btnSearch.setEnabled(true);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblCountry = new javax.swing.JLabel();
        lblAging = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        lblPoints = new javax.swing.JLabel();
        lblComment = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        lblRegion = new javax.swing.JLabel();
        lblWineImage = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        chooserColor = new javax.swing.JComboBox();
        chooserAging = new javax.swing.JComboBox(WineMainFrame.getTypes());
        txtPrice = new javax.swing.JFormattedTextField();
        spinnerQty = new javax.swing.JSpinner();
        chooserCountry = new javax.swing.JComboBox(WineMainFrame.getCountries());
        spinnerPoints = new javax.swing.JSpinner();
        chooserYear = new javax.swing.JComboBox(WineMainFrame.getYears());
        chooserRegion = new javax.swing.JComboBox(WineMainFrame.getRegions());
        jPanel2 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSaveAndContinue = new javax.swing.JButton();
        btnSaveAndExit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblUPC = new javax.swing.JLabel();
        txtUPC = new javax.swing.JFormattedTextField();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Añadir vinos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblName.setText("Nombre:");

        lblCountry.setText("País:");

        lblAging.setText("Envejecimiento:");

        lblColor.setText("Color:");

        lblPrice.setText("Precio:               $");

        lblQuantity.setText("Cantidad:");

        lblYear.setText("Año:");

        lblPoints.setText("Puntuación:");

        lblComment.setText("Comentario:");

        txtComment.setColumns(20);
        txtComment.setLineWrap(true);
        txtComment.setRows(5);
        jScrollPane1.setViewportView(txtComment);

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        lblRegion.setText("Región:");

        lblWineImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWineImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winemanager/vinos/wine_btl.jpg"))); // NOI18N

        jLabel13.setText("UPC");

        chooserAging.setEditable(true);

        txtPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        spinnerQty.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));

        spinnerPoints.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        chooserRegion.setEditable(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName)
                    .addComponent(lblCountry)
                    .addComponent(lblColor)
                    .addComponent(lblYear)
                    .addComponent(lblQuantity)
                    .addComponent(lblAging)
                    .addComponent(lblRegion)
                    .addComponent(lblPoints)
                    .addComponent(lblComment)
                    .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooserAging, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chooserColor, javax.swing.GroupLayout.Alignment.LEADING, 0, 85, Short.MAX_VALUE))
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerQty, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooserCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooserYear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooserRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(lblWineImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCountry)
                            .addComponent(chooserCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chooserColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblColor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYear)
                            .addComponent(chooserYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrice)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQuantity)
                            .addComponent(spinnerQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAging)
                            .addComponent(chooserAging, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRegion)
                            .addComponent(chooserRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPoints)
                            .addComponent(spinnerPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(lblComment)))
                    .addComponent(lblWineImage, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSaveAndContinue.setText("Guardar y Continuar");
        btnSaveAndContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAndContinueActionPerformed(evt);
            }
        });

        btnSaveAndExit.setText("Guardar y Salir");
        btnSaveAndExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAndExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(btnSaveAndExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveAndContinue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSaveAndContinue)
                    .addComponent(btnSaveAndExit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));

        lblUPC.setText("UPC");

        txtUPC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUPCKeyPressed(evt);
            }
        });

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUPC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUPC, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUPC)
                    .addComponent(txtUPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("");

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-557)/2, (screenSize.height-568)/2, 557, 568);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtUPC.requestFocusInWindow();
    }//GEN-LAST:event_formWindowOpened

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchUPCInWeb();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSaveAndExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAndExitActionPerformed
        if (readyToAdd()) {
            WineMainFrame.getVinos().add(wine);
            try {
                ListFrame.getListModel().fireTableDataChanged();
            } catch (Exception e) {
            }
            WineMainFrame.setInfoChanged(true);
            this.setVisible(false);
            this.dispose();
        }

    }//GEN-LAST:event_btnSaveAndExitActionPerformed

    private void btnSaveAndContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAndContinueActionPerformed
        if (readyToAdd()) {
            WineMainFrame.getVinos().add(wine);
            try {
                ListFrame.getListModel().fireTableDataChanged();
            } catch (Exception e) {
            }
            WineMainFrame.setInfoChanged(true);
            wine = new Vino();
            txtName.setText("");
            chooserCountry.setSelectedIndex(0);
            txtUPC.setValue(null);
            txtUPC.setText("");
            txtPrice.setValue(null);
            spinnerQty.setValue(1);
            txtComment.setText("");
            txtUPC.requestFocusInWindow();
            chooserRegion.setSelectedIndex(-1);
            chooserYear.setSelectedIndex(-1);
            chooserCountry.setSelectedIndex(-1);
            chooserAging.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_btnSaveAndContinueActionPerformed

    private void txtUPCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUPCKeyPressed
        if (evt.getKeyCode() == 10) {
            searchUPCInWeb();
        }
    }//GEN-LAST:event_txtUPCKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AddWineFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSaveAndContinue;
    private javax.swing.JButton btnSaveAndExit;
    private static javax.swing.JButton btnSearch;
    private javax.swing.JComboBox chooserAging;
    private javax.swing.JComboBox chooserColor;
    private javax.swing.JComboBox chooserCountry;
    private javax.swing.JComboBox chooserRegion;
    private javax.swing.JComboBox chooserYear;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel lblAging;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblComment;
    private javax.swing.JLabel lblCountry;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPoints;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblRegion;
    private javax.swing.JLabel lblUPC;
    private javax.swing.JLabel lblWineImage;
    private javax.swing.JLabel lblYear;
    private javax.swing.JSpinner spinnerPoints;
    private javax.swing.JSpinner spinnerQty;
    private javax.swing.JTextArea txtComment;
    private javax.swing.JTextField txtName;
    private javax.swing.JFormattedTextField txtPrice;
    private javax.swing.JFormattedTextField txtUPC;
    // End of variables declaration//GEN-END:variables
}
