/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QuantityChange.java
 *
 * Created on Jun 17, 2009, 4:08:21 PM
 */
package winemanager;

import javax.swing.SwingUtilities;

/**
 *
 * @author Luis
 */
public class QuantityChange extends javax.swing.JFrame {

    private boolean wineFound;
    private Vino wine;
    private String action = "Removido";

    /** Creates new form QuantityChange */
    public QuantityChange() {
        initComponents();
        jTable1.setModel(new HistoryModel());
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.setIconImage(Utils.getFrameIcon());
        setUIText();
    }
    
    private void setUIText(){
        btnSave.setText(WineMainFrame.strings.getString("menuItemSave"));
        lblAddRemoveQty.setText(WineMainFrame.strings.getString("lblAddRemoveQty"));
        radioBtnAdd.setText(WineMainFrame.strings.getString("btnAdd"));
        radioBtnRemove.setText(WineMainFrame.strings.getString("btnRemove"));
        this.setTitle(WineMainFrame.strings.getString("quantityChange_title"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        radioBtnRemove = new javax.swing.JRadioButton();
        radioBtnAdd = new javax.swing.JRadioButton();
        lblAddRemoveQty = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Cantidades");

        btnSave.setText("Finalizar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel6.setText("UPC");

        jLabel10.setForeground(new java.awt.Color(255, 51, 0));

        jFormattedTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jFormattedTextField2CaretUpdate(evt);
            }
        });

        buttonGroup1.add(radioBtnRemove);
        radioBtnRemove.setSelected(true);
        radioBtnRemove.setText("Remover");
        radioBtnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnRemoveActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioBtnAdd);
        radioBtnAdd.setText("Anadir");
        radioBtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnAddActionPerformed(evt);
            }
        });

        lblAddRemoveQty.setText("Cantidad a remover/anadir");

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 171, Short.MAX_VALUE)
                        .addComponent(btnSave))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblAddRemoveQty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(radioBtnAdd)
                            .addComponent(radioBtnRemove)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(radioBtnRemove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioBtnAdd)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddRemoveQty)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116)
                .addComponent(btnSave)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Cantidad", "Accion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jFormattedTextField2CaretUpdate
        wineFound = false;
        jLabel10.setText("");
        int length = jFormattedTextField2.getText().length();
        class ResetField implements Runnable {

            public void run() {
                jFormattedTextField2.setText("");
            }
        }
        if (length == 12 || length == 13) {
            for (int i = 0; i < WineMainFrame.getVinos().size(); i++) {
                if (WineMainFrame.getVinos().get(i).getUPC().equals(jFormattedTextField2.getText())) {
                    wine = WineMainFrame.getVinos().get(i);
                    int oldQuantity = wine.getQuantity();
                    int quantity;
                    if (action.equals("Removido")) {
                        quantity = wine.getQuantity() - (Integer) jSpinner2.getValue();
                        if (quantity < 0) {
                            quantity = 0;
                        }
                    } else {
                        quantity = wine.getQuantity() + (Integer) jSpinner2.getValue();
                    }
                    wine.setQuantity(quantity);
                    try {
                        ListFrame.getListModel().fireTableDataChanged();
                    } catch (Exception e) {
                    }
                    Entry e = new Entry(wine.getName(), quantity, oldQuantity);
                    ((HistoryModel) jTable1.getModel()).addEntry(e);
                    wineFound = true;
                    WineMainFrame.setInfoChanged(true);
                    SwingUtilities.invokeLater(new ResetField());
                }
            }
            if (!wineFound) {
                jLabel10.setText(WineMainFrame.strings.getString("addWineFrame_incorrectUPC"));
            }
        }
}//GEN-LAST:event_jFormattedTextField2CaretUpdate

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        this.setVisible(false);
        this.dispose();
}//GEN-LAST:event_btnSaveActionPerformed

    private void radioBtnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnRemoveActionPerformed
        action = "Removido";
}//GEN-LAST:event_radioBtnRemoveActionPerformed

    private void radioBtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnAddActionPerformed
        action = "Anadido";
}//GEN-LAST:event_radioBtnAddActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new QuantityChange().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAddRemoveQty;
    private javax.swing.JRadioButton radioBtnAdd;
    private javax.swing.JRadioButton radioBtnRemove;
    // End of variables declaration//GEN-END:variables
}
