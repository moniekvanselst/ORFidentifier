package orfidentifier;


import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;


/**
 *
 * @author Moniek van Selst, Willem Korsten, Nicky van Bergen
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        SEQtextArea = new javax.swing.JTextArea();
        seqLabel = new javax.swing.JLabel();
        ORFLabel = new javax.swing.JLabel();
        ORFButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ORFtextArea = new javax.swing.JTextArea();
        openButton = new javax.swing.JButton();
        ORFdropDown = new javax.swing.JComboBox<>();
        BLASTbutton = new javax.swing.JButton();
        BLASTlabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        BLASTtable = new javax.swing.JTable();
        waitLabel = new javax.swing.JLabel();
        detailsBotton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SEQtextArea.setColumns(20);
        SEQtextArea.setRows(5);
        jScrollPane1.setViewportView(SEQtextArea);

        seqLabel.setText("Sequence:");

        ORFLabel.setText("Found ORF's:");

        ORFButton.setText("Find ORF's");
        ORFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ORFButtonActionPerformed(evt);
            }
        });

        ORFtextArea.setColumns(20);
        ORFtextArea.setRows(5);
        jScrollPane2.setViewportView(ORFtextArea);

        openButton.setText("Open file");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        ORFdropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        BLASTbutton.setText("BLAST");
        BLASTbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLASTbuttonActionPerformed(evt);
            }
        });

        BLASTlabel.setText("BLAST results:");

        BLASTtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Protein name", "E-value", "Coverage", "Identity", "Accession"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Long.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(BLASTtable);

        waitLabel.setText(".");

        detailsBotton.setText("show details BLAST result");
        detailsBotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsBottonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(ORFLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ORFButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(seqLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(waitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(openButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(ORFdropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BLASTbutton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(BLASTlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(detailsBotton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seqLabel)
                    .addComponent(openButton)
                    .addComponent(waitLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ORFLabel)
                    .addComponent(ORFButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ORFdropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BLASTbutton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BLASTlabel)
                    .addComponent(detailsBotton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        GUIopen.mainOpen();
        openButton.setEnabled(false);
    }//GEN-LAST:event_openButtonActionPerformed

    /**
     * Deze methode roept BLAST() en BLASTparser aan.
     * Ook zet hij de blast resultaten in de tabel.
     * @param evt 
     */
    private void BLASTbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLASTbuttonActionPerformed
        int index = ORFdropDown.getSelectedIndex();
        ORF orf = Sequentie.ORFlist.get(index);
        String seq = orf.getSeqorf();
        
        if (orf.table.isEmpty()){
            String path = new Logica().BLAST(seq);
            new Logica().BLASTparser(path, orf);
        }
        DefaultTableModel model = (DefaultTableModel) BLASTtable.getModel();
        model.setRowCount(0);
        for(Object[] row: orf.table){
            model.addRow(row);
        }
    }//GEN-LAST:event_BLASTbuttonActionPerformed

    
    private void ORFButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ORFButtonActionPerformed
        GUI.waitLabel.setText("is orf's aan het maken..");
        new Logica().findORF();
    }//GEN-LAST:event_ORFButtonActionPerformed

    private void detailsBottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsBottonActionPerformed
     GUIblast.BLASTmain();
    }//GEN-LAST:event_detailsBottonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws CompoundNotFoundException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
           
           
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BLASTbutton;
    private javax.swing.JLabel BLASTlabel;
    javax.swing.JTable BLASTtable;
    private javax.swing.JButton ORFButton;
    private javax.swing.JLabel ORFLabel;
    public static javax.swing.JComboBox<String> ORFdropDown;
    public static javax.swing.JTextArea ORFtextArea;
    public static javax.swing.JTextArea SEQtextArea;
    private javax.swing.JButton detailsBotton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton openButton;
    private javax.swing.JLabel seqLabel;
    public static javax.swing.JLabel waitLabel;
    // End of variables declaration//GEN-END:variables
}
