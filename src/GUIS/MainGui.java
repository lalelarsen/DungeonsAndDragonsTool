package GUIS;

import Controllers.BoardController;
import Controllers.MainController;
import java.io.File;

public class MainGui extends javax.swing.JFrame {

    MainController MC;
    public MainGui(MainController MC) {
        this.MC = MC;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        LaunchBoardB = new javax.swing.JButton();
        PathForBGPic = new javax.swing.JTextField();
        FindPath = new javax.swing.JButton();
        RowsLabel = new javax.swing.JLabel();
        Rows = new javax.swing.JTextField();
        ColumnsLabel = new javax.swing.JLabel();
        Columns = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LaunchBoardB.setText("Launch board");
        LaunchBoardB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaunchBoardBActionPerformed(evt);
            }
        });

        PathForBGPic.setText("C:\\Users\\frederik.larsen\\Pictures\\Saved Pictures\\mig.jpg");
        PathForBGPic.setToolTipText("Path");
        PathForBGPic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PathForBGPicActionPerformed(evt);
            }
        });

        FindPath.setText("Find");
        FindPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindPathActionPerformed(evt);
            }
        });

        RowsLabel.setText("Rows:");

        Rows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RowsActionPerformed(evt);
            }
        });

        ColumnsLabel.setText("Columns:");

        Columns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColumnsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LaunchBoardB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FindPath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PathForBGPic, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RowsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Rows, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ColumnsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Columns, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LaunchBoardB)
                    .addComponent(PathForBGPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FindPath)
                    .addComponent(RowsLabel)
                    .addComponent(Rows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ColumnsLabel)
                    .addComponent(Columns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(266, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LaunchBoardBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaunchBoardBActionPerformed
        String Path = PathForBGPic.getText();
        String Rows = this.Rows.getText();
        String Columns = this.Columns.getText();
        MC.OpenBoard(Path,Rows,Columns);
    }//GEN-LAST:event_LaunchBoardBActionPerformed

    private void PathForBGPicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PathForBGPicActionPerformed
        
    }//GEN-LAST:event_PathForBGPicActionPerformed

    private void FindPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindPathActionPerformed
        int returnVal = jFileChooser1.showOpenDialog(this);
        File file = null;
        if (returnVal == jFileChooser1.APPROVE_OPTION) {
            file = jFileChooser1.getSelectedFile();
            //This is where a real application would open the file.
        }
        PathForBGPic.setText(file.getPath());
    }//GEN-LAST:event_FindPathActionPerformed

    private void RowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RowsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RowsActionPerformed

    private void ColumnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColumnsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ColumnsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Board().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Columns;
    private javax.swing.JLabel ColumnsLabel;
    private javax.swing.JButton FindPath;
    private javax.swing.JButton LaunchBoardB;
    private javax.swing.JTextField PathForBGPic;
    private javax.swing.JTextField Rows;
    private javax.swing.JLabel RowsLabel;
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables
}
