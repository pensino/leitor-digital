/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FingerprintPanel.java
 *
 * Created on 16/05/2011, 07:40:07
 */
package br.com.pensino.ui.panel;

import br.com.pensino.utils.fingerPrint.FingerprintEngineObserver;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author emiliowl
 */
public class FingerprintPanel extends javax.swing.JPanel implements FingerprintEngineObserver {

    private BufferedImage fingerprintImage = null;

    /** Creates new form FingerprintPanel */
    public FingerprintPanel() {
        initComponents();
        this.setBorder(new CompoundBorder(
                new EmptyBorder(2, 2, 2, 2),
                new BevelBorder(BevelBorder.LOWERED)));
        this.setSize(230, 280);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean notifyImageAcquired(BufferedImage fingerprintImage) {
        this.fingerprintImage = fingerprintImage;
        repaint();
        return true;
    }

    @Override
    public boolean notifyTemplateExtracted(BufferedImage templateImage) {
        fingerprintImage = templateImage;
        repaint();
        return true;
    }

    /** Método utilizado para mostrar as semelhancas encontradas
     *
     */
    @Override
    public boolean showSimilarities(BufferedImage image) {
        //Utiliza o imageProducer para criar uma imagem da impressao digital
        fingerprintImage = image;
        //Se desenha a nova imagem
        repaint();
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fingerprintImage != null) {
            //Calcula o tamanho e posicao da imagem para ser pintada
            //o tamanho eh ajustado para que ocupe todo o tamanho do painel
            Insets insets = getInsets();
            int transX = insets.left;
            int transY = insets.top;
            int width = getWidth() - getInsets().right - getInsets().left;
            int height = getHeight() - getInsets().bottom - getInsets().top;

            //Se desenha a imagem
            g.drawImage(fingerprintImage, transX, transY, width, height, null);
        }

    }

    @Override
    public void notifyFingerDown() {
    }

    @Override
    public void notifyFingerUp() {
    }
}
