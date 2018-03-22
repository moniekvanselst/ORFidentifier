/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author van Selst
 */
public class GUIopen extends javax.swing.JFrame {

    /**
     * Creates new form GUIopen
     */
    public GUIopen() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        PreviusRadioButton = new javax.swing.JRadioButton();
        NewRadioButton = new javax.swing.JRadioButton();
        PreviusNameDropDown = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        InfoTextArea = new javax.swing.JTextArea();
        BrowseButton = new javax.swing.JButton();
        FileTextField = new javax.swing.JTextField();
        NameLabel = new javax.swing.JLabel();
        OrganismLAbel = new javax.swing.JLabel();
        DateLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        NameTextField = new javax.swing.JTextField();
        OrganismTextField = new javax.swing.JTextField();
        DateTextField = new javax.swing.JTextField();
        CodonDropDown = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setTitle("Open file");

        buttonGroup1.add(PreviusRadioButton);
        PreviusRadioButton.setText("Open previous file");
        PreviusRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviusRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(NewRadioButton);
        NewRadioButton.setSelected(true);
        NewRadioButton.setText("Open new file");
        NewRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewRadioButtonActionPerformed(evt);
            }
        });

        PreviusNameDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        PreviusNameDropDown.setEnabled(false);

        InfoTextArea.setColumns(20);
        InfoTextArea.setRows(5);
        InfoTextArea.setEnabled(false);
        jScrollPane1.setViewportView(InfoTextArea);

        BrowseButton.setText("Browse");
        BrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseButtonActionPerformed(evt);
            }
        });

        FileTextField.setText("File");

        NameLabel.setText("Name:");

        OrganismLAbel.setText("Organism:");

        DateLabel.setText("Date (dd-mm-yy):");

        jLabel1.setText("Codontable:");

        CodonDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flatworm mitochondrial", "Yeast mitochondrial", "Ascidian mitochondrial", "Euplotid nuclear", "Universal", "Invertebrate mitochondrial", "Blepharisma macronuclear", "Alternative yeast nuclear", "Bacterial", "Vertebrate mitochondrial", "Ciliate nuclear", "Mold mitochondrial", "Echinoderm mitochondrial" }));
        CodonDropDown.setSelectedIndex(4);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Open");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PreviusRadioButton)
                    .addComponent(PreviusNameDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(FileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BrowseButton))
                        .addComponent(NewRadioButton)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(NameLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(NameTextField))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(OrganismLAbel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(OrganismTextField))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(DateLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DateTextField))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CodonDropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PreviusRadioButton)
                    .addComponent(NewRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PreviusNameDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowseButton)
                    .addComponent(FileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NameLabel)
                            .addComponent(NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(OrganismLAbel)
                            .addComponent(OrganismTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DateLabel)
                            .addComponent(DateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CodonDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PreviusRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviusRadioButtonActionPerformed
        BrowseButton.setEnabled(false);
        NameTextField.setEnabled(false);
        OrganismTextField.setEnabled(false);
        DateTextField.setEnabled(false);
        InfoTextArea.setEnabled(true);
        PreviusNameDropDown.setEnabled(true);
        CodonDropDown.setEnabled(false);
        FileTextField.setEnabled(false);
    }//GEN-LAST:event_PreviusRadioButtonActionPerformed

    private void NewRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewRadioButtonActionPerformed
        BrowseButton.setEnabled(true);
        NameTextField.setEnabled(true);
        OrganismTextField.setEnabled(true);
        DateTextField.setEnabled(true);
        InfoTextArea.setEnabled(false);
        PreviusNameDropDown.setEnabled(false);
        CodonDropDown.setEnabled(true);
        FileTextField.setEnabled(true);
    }//GEN-LAST:event_NewRadioButtonActionPerformed
JFileChooser FileChooser;
    private void BrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseButtonActionPerformed
        // TODO add your handling code here:
        // - laat de gebruiker een bestand kiezen
        // - weergeeft het gekozen bestand in het textvak
                FileChooser = new JFileChooser();
        int reply = FileChooser.showOpenDialog(this);
            File selectFile = FileChooser.getSelectedFile();
            if (reply == JFileChooser.APPROVE_OPTION) {
                String bestand = (selectFile.getAbsolutePath());
                FileTextField.setText(selectFile.getName());
                Logica.readFile(bestand);
            }
    }//GEN-LAST:event_BrowseButtonActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // - if open previus laat logica alles ophalen uit de database en weergeven.
        // - if else geef bestand door aan logica en laat de seq zien in GUI met bijbehorende eiwit frames.
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void mainOpen() {
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
            java.util.logging.Logger.getLogger(GUIopen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIopen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIopen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIopen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIopen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrowseButton;
    private javax.swing.JComboBox<String> CodonDropDown;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JTextField DateTextField;
    private javax.swing.JTextField FileTextField;
    private javax.swing.JTextArea InfoTextArea;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JTextField NameTextField;
    private javax.swing.JRadioButton NewRadioButton;
    private javax.swing.JLabel OrganismLAbel;
    private javax.swing.JTextField OrganismTextField;
    private javax.swing.JComboBox<String> PreviusNameDropDown;
    private javax.swing.JRadioButton PreviusRadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}