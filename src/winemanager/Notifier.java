/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Notifier.java
 *
 * Created on Jun 6, 2009, 4:24:38 PM
 */

package winemanager;

/**
 *
 * @author Luis
 */
public class Notifier extends javax.swing.JPanel {

    /** Creates new form Notifier */
    public Notifier() {
        initComponents();
        lblNotifier1.setText(WineMainFrame.strings.getString("lblNotifier1"));
        lblNotifier2.setText(WineMainFrame.strings.getString("lblNotifier2"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNotifier1 = new javax.swing.JLabel();
        lblNotifier2 = new javax.swing.JLabel();

        lblNotifier1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNotifier1.setText("El vino no fue encontrado en la base de datos.");

        lblNotifier2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNotifier2.setText("Por favor ingrese la informacion manualmente.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNotifier1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(lblNotifier2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblNotifier1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNotifier2)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblNotifier1;
    private javax.swing.JLabel lblNotifier2;
    // End of variables declaration//GEN-END:variables

}
