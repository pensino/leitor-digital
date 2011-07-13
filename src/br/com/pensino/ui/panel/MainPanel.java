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

import br.com.pensino.domain.model.Employee;
import br.com.pensino.ui.components.ClassStartPanel;
import br.com.pensino.utils.db.EmployeeDAO;
import br.com.pensino.utils.db.StudentDAO;
import br.com.pensino.utils.fingerPrint.FingerprintEngine;
import br.com.pensino.utils.fingerPrint.FingerprintEngineObserver;
import br.com.pensino.utils.message.By;
import br.com.pensino.utils.message.MessageService;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *lateData
 * @author emiliowl
 */
public class MainPanel extends JPanel implements FingerprintEngineObserver {

    private FingerprintEngine fingerprintEngine = FingerprintEngine.getInstance();
    private FingerprintPanel fingerprintPanel = new FingerprintPanel();
    private StudentDAO studentDAO;
    private EmployeeDAO employeeDAO;
    private JPanel middlePanel = null;
    private Boolean aulaIniciada = false;
    private static JLabel messageLabel = new JLabel(MessageService.getMessage(By.name("msg001")));
    private static JProgressBar progressBar = new JProgressBar();

    /** Creates new form MainPanel */
    public MainPanel(EmployeeDAO employeeDAO, StudentDAO studentDAO) {
        initComponents();
        //initializing the fingerprint matcher engine
        fingerprintEngine.startObserve(fingerprintPanel);
        fingerprintEngine.startObserve(this);
        fingerprintContentPanel.add(fingerprintPanel);
        //initializing the DAO objects
        this.employeeDAO = employeeDAO;
        this.studentDAO = studentDAO;
        //loading the class panel that shows user information
        middlePanel = new ClassStartPanel();
        //setting layout components
        this.add(middlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 325, -1, -1));
        this.add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));
        this.add(progressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));
        progressBar.setVisible(false);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        this.setMinimumSize(new java.awt.Dimension(365, 500));
        this.setPreferredSize(new java.awt.Dimension(365, 500));
    }

    public static void startProgress() {
        messageLabel.setVisible(false);
        progressBar.setVisible(true);
    }

    public static void setProgressStatus(int status) {
        progressBar.setValue(status);
        if (status >= 100) {
            progressBar.setValue(0);
            progressBar.setVisible(false);
            messageLabel.setVisible(true);
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

        jLayeredPane3 = new javax.swing.JLayeredPane();
        fingerprintContainer = new javax.swing.JLayeredPane();
        fingerprintContentPanel = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(365, 500));
        setMinimumSize(new java.awt.Dimension(365, 500));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(365, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLayeredPane3.setMaximumSize(new java.awt.Dimension(227, 278));
        jLayeredPane3.setMinimumSize(new java.awt.Dimension(227, 278));
        add(jLayeredPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 150, 200));

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
            .addGap(0, 130, Short.MAX_VALUE)
        );
        fingerprintContentPanelLayout.setVerticalGroup(
            fingerprintContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        fingerprintContentPanel.setBounds(10, 10, 130, 180);
        fingerprintContainer.add(fingerprintContentPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        add(fingerprintContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 150, 200));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane fingerprintContainer;
    private javax.swing.JPanel fingerprintContentPanel;
    private javax.swing.JLayeredPane jLayeredPane3;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean notifyImageAcquired(BufferedImage fingerprintImage) {
        MainPanel.setProgressStatus(75);
        return true;
    }

    @Override
    public boolean notifyTemplateExtracted(BufferedImage templateImage, byte[] templateData) {
        /*
         * 1 - verifica se tem aula iniciada
         *  1.1 - Não --> procura professor com essa digital.
         *   1.1.2 - Verifica se o professor tem aula corrente
         *   1.1.3 - Se tiver carrega esta aula.
         *   1.1.4 - Verifica se aula está iniciada
         *   1.1.5 - Se não estiver, inicia a aula
         *   1.1.6 - Configura aula iniciada em memória
         */
        if (!aulaIniciada) {
            try {
                List<Employee> professors = employeeDAO.all();                
                if (fingerprintEngine.checkFingerprint(templateData)) {
                    aulaIniciada = true;
                    messageUpdater("msg002");
                    messageUpdater("msg005");
                } else {
                    String beforeIcon = messageLabel.getIcon().toString();
                    beforeIcon = beforeIcon.substring(beforeIcon.indexOf("msg"), beforeIcon.indexOf("msg") + 6);
                    messageUpdater("msg004");
                    messageUpdater(beforeIcon);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            MainPanel.setProgressStatus(100);
        }
        return true;
    }

    @Override
    public boolean showSimilarities(BufferedImage fingerprintImage) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public void notifyFingerDown() {
        MainPanel.startProgress();
        MainPanel.setProgressStatus(25);
    }

    @Override
    public void notifyFingerUp() {
        MainPanel.setProgressStatus(50);
    }

    private void messageUpdater(String message) {
        try {
            Thread t1 = new Thread();
            t1.sleep(600);
            messageLabel.setIcon(MessageService.getMessage(By.name(message)));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
