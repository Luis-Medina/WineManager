/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OptionsEditor.java
 *
 * Created on Jun 20, 2009, 12:22:09 AM
 */
package winemanager;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class OptionsEditor extends javax.swing.JFrame {
    
    private String originalLanguage;

    /** Creates new form OptionsEditor */
    public OptionsEditor() {
        initComponents();
        createLists();
        this.setIconImage(Utils.getFrameIcon());
        for(String s : WineMainFrame.availableLanguages){
            chooserLanguage.addItem(s);
        }
        setSelectedLanguage();
        setUIText();
    }
    
    private void setSelectedLanguage(){
        if(chooserLanguage.getItemCount() > 0){
            for(int i=0; i<chooserLanguage.getItemCount(); i++){
                String chooserLanguageCode = chooserLanguage.getItemAt(i).toString().split("-")[1].trim();
                if(chooserLanguageCode.equals(WineMainFrame.currentLocale.getLanguage())){
                    chooserLanguage.setSelectedIndex(i);
                    originalLanguage = chooserLanguage.getSelectedItem().toString();
                    break;
                }
            }
        }
        
    }
    
    private void setUIText(){
        btnSave.setText(WineMainFrame.strings.getString("menuItemSave"));
        btnCancel.setText(WineMainFrame.strings.getString("btnCancel"));
        lblAgings.setText(WineMainFrame.strings.getString("lblAgings"));
        lblCountries.setText(WineMainFrame.strings.getString("lblCountries"));
        lblRegions.setText(WineMainFrame.strings.getString("lblRegions"));
        lblLanguage.setText(WineMainFrame.strings.getString("lblLanguage"));
        this.setTitle(WineMainFrame.strings.getString("optionsEditor_title"));
    }

    private void createLists() {
        DefaultListModel model = new DefaultListModel();
        for (String s : WineMainFrame.getCountries()) {
            model.addElement(s);
        }
        countryList.setModel(model);
        model = new DefaultListModel();
        for (String s : WineMainFrame.getTypes()) {
            model.addElement(s);
        }
        agingList.setModel(model);
        model = new DefaultListModel();
        for (String s : WineMainFrame.getRegions()) {
            model.addElement(s);
        }
        regionList.setModel(model);
    }

    private void removeSelected(JList list){
        DefaultListModel model = (DefaultListModel) list.getModel();
        int[] tmp = list.getSelectedIndices();
        int[] selectedIndices;
        for (int i = tmp.length - 1; i >= 0; i--) {
            selectedIndices = list.getSelectedIndices();
            model.removeElementAt(selectedIndices[i]);
        }
        if(tmp.length > 0) {
            WineMainFrame.setConfigDataChanged(true);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegionRemove = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        countryList = new javax.swing.JList();
        btnCountryRemove = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        regionList = new javax.swing.JList();
        btnCountryAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblRegions = new javax.swing.JLabel();
        btnRegionAdd = new javax.swing.JButton();
        btnAgingAdd = new javax.swing.JButton();
        lblAgings = new javax.swing.JLabel();
        lblCountries = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        agingList = new javax.swing.JList();
        btnAgingRemove = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblLanguage = new javax.swing.JLabel();
        chooserLanguage = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar configuracion");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnRegionRemove.setText("-");
        btnRegionRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegionRemoveActionPerformed(evt);
            }
        });

        jScrollPane7.setViewportView(countryList);

        btnCountryRemove.setText("-");
        btnCountryRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountryRemoveActionPerformed(evt);
            }
        });

        jScrollPane8.setViewportView(regionList);

        btnCountryAdd.setText("+");
        btnCountryAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountryAddActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblRegions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegions.setText("Regiones");

        btnRegionAdd.setText("+");
        btnRegionAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegionAddActionPerformed(evt);
            }
        });

        btnAgingAdd.setText("+");
        btnAgingAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgingAddActionPerformed(evt);
            }
        });

        lblAgings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgings.setText("Envejecimientos");

        lblCountries.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCountries.setText("Paises");

        jScrollPane9.setViewportView(agingList);

        btnAgingRemove.setText("-");
        btnAgingRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgingRemoveActionPerformed(evt);
            }
        });

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblLanguage.setText("Idioma:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8)
                            .addComponent(lblCountries)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCountryAdd)
                                    .addComponent(btnCountryRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnRegionRemove, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegionAdd, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAgings)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAgingAdd)
                                            .addComponent(btnAgingRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegions)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblLanguage)
                                .addGap(18, 18, 18)
                                .addComponent(chooserLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnSave});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLanguage)
                            .addComponent(chooserLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(lblCountries)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCountryAdd)
                                .addGap(3, 3, 3)
                                .addComponent(btnCountryRemove)))
                        .addGap(18, 18, 18)
                        .addComponent(lblRegions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegionAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegionRemove))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lblAgings)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgingAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgingRemove))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnSave});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegionRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegionRemoveActionPerformed
        removeSelected(regionList);
    }//GEN-LAST:event_btnRegionRemoveActionPerformed

    private void btnCountryRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountryRemoveActionPerformed
        removeSelected(countryList);
    }//GEN-LAST:event_btnCountryRemoveActionPerformed

    private void btnCountryAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountryAddActionPerformed
        ItemAdd ia = new ItemAdd(countryList);
        ia.setLocationRelativeTo(null);
        ia.setVisible(true);
    }//GEN-LAST:event_btnCountryAddActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnRegionAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegionAddActionPerformed
        ItemAdd ia = new ItemAdd(regionList);
        ia.setLocationRelativeTo(null);
        ia.setVisible(true);
    }//GEN-LAST:event_btnRegionAddActionPerformed

    private void btnAgingAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgingAddActionPerformed
        ItemAdd ia = new ItemAdd(agingList);
        ia.setLocationRelativeTo(null);
        ia.setVisible(true);
    }//GEN-LAST:event_btnAgingAddActionPerformed

    private void btnAgingRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgingRemoveActionPerformed
        removeSelected(agingList);
    }//GEN-LAST:event_btnAgingRemoveActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
    }//GEN-LAST:event_formWindowClosing

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        DefaultListModel model = (DefaultListModel) countryList.getModel();
        Vector<String> toSet = new Vector<String>();
        Object[] temp = model.toArray();
        for (Object s : temp) {
            toSet.add((String) s);
        }
        WineMainFrame.setCountries(toSet);

        model = (DefaultListModel) agingList.getModel();
        toSet = new Vector<String>();
        temp = model.toArray();
        for (Object s : temp) {
            toSet.add((String) s);
        }
        WineMainFrame.setTypes(toSet);

        model = (DefaultListModel) regionList.getModel();
        toSet = new Vector<String>();
        temp = model.toArray();
        for (Object s : temp) {
            toSet.add((String) s);
        }
        WineMainFrame.setRegions(toSet);
        
        String languageCode = chooserLanguage.getSelectedItem().toString().split("-")[1].trim();
        WineMainFrame.currentLocale = new Locale(languageCode, "US");
        WineMainFrame.strings = ResourceBundle.getBundle("winemanager/locales/StringsBundle", WineMainFrame.currentLocale);
        
        WineMainFrame.setConfigDataChanged(true);
        
        if(!originalLanguage.equals(chooserLanguage.getSelectedItem().toString())){
            JOptionPane.showMessageDialog(null, WineMainFrame.strings.getString("msg_LanguageChange"));                   
        }
        
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new OptionsEditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList agingList;
    private javax.swing.JButton btnAgingAdd;
    private javax.swing.JButton btnAgingRemove;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCountryAdd;
    private javax.swing.JButton btnCountryRemove;
    private javax.swing.JButton btnRegionAdd;
    private javax.swing.JButton btnRegionRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox chooserLanguage;
    private javax.swing.JList countryList;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblAgings;
    private javax.swing.JLabel lblCountries;
    private javax.swing.JLabel lblLanguage;
    private javax.swing.JLabel lblRegions;
    private javax.swing.JList regionList;
    // End of variables declaration//GEN-END:variables
}
