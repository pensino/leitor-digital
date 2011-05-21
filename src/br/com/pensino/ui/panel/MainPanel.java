/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainPanel.java
 *
 * Created on 18/05/2011, 07:17:32
 */
package br.com.pensino.ui.panel;

import br.com.pensino.ui.components.AddButton;
import br.com.pensino.utils.fingerPrint.FingerprintEngine;
import javax.swing.JPanel;

/**
 *
 * @author emiliowl
 */
public class MainPanel extends JPanel {
    
    private FingerprintEngine fingerprintEngine = FingerprintEngine.getInstance();
    private FingerprintPanel fingerprintPanel = new FingerprintPanel();
    private AddButton addButton = AddButton.getInstance();

    /** Creates new form MainPanel */
    public MainPanel() {
        initComponents();
        this.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));
        fingerprintEngine.startObserve(fingerprintPanel);
        fingerprintContentPanel.add(fingerprintPanel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane3 = new javax.swing.JLayeredPane();
        fingerprintContainer = new javax.swing.JLayeredPane();
        fingerprintContentPanel = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();

        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(500, 600));
        setMinimumSize(new java.awt.Dimension(500, 600));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(500, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLayeredPane3.setMaximumSize(new java.awt.Dimension(227, 278));
        jLayeredPane3.setMinimumSize(new java.awt.Dimension(227, 278));
        add(jLayeredPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 250, 300));

        fingerprintContainer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fingerprintContainer.setMaximumSize(new java.awt.Dimension(227, 278));
        fingerprintContainer.setMinimumSize(new java.awt.Dimension(227, 278));

        fingerprintContentPanel.setBorder(null);
        fingerprintContentPanel.setMaximumSize(new java.awt.Dimension(227, 270));
        fingerprintContentPanel.setMinimumSize(new java.awt.Dimension(227, 270));
        fingerprintContentPanel.setOpaque(false);
        fingerprintContentPanel.setPreferredSize(new java.awt.Dimension(227, 270));

        javax.swing.GroupLayout fingerprintContentPanelLayout = new javax.swing.GroupLayout(fingerprintContentPanel);
        fingerprintContentPanel.setLayout(fingerprintContentPanelLayout);
        fingerprintContentPanelLayout.setHorizontalGroup(
            fingerprintContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        fingerprintContentPanelLayout.setVerticalGroup(
            fingerprintContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        fingerprintContentPanel.setBounds(10, 10, 230, 280);
        fingerprintContainer.add(fingerprintContentPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        add(fingerprintContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 250, 300));
        add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 550, 140));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane fingerprintContainer;
    private javax.swing.JPanel fingerprintContentPanel;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane3;
    // End of variables declaration//GEN-END:variables
}
