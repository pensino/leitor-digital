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
import br.com.pensino.domain.model.Fingerprint;
import br.com.pensino.domain.model.Person;
import br.com.pensino.domain.model.Student;
import br.com.pensino.ui.components.MiddleRegisterPanel;
import br.com.pensino.ui.exception.EmployeeNotFoundException;
import br.com.pensino.ui.exception.PersonNotFoundException;
import br.com.pensino.ui.exception.StudentNotFoundException;
import br.com.pensino.utils.db.EmployeeDAO;
import br.com.pensino.utils.db.FingerprintDAO;
import br.com.pensino.utils.db.StudentDAO;
import br.com.pensino.utils.fingerPrint.FingerprintEngine;
import br.com.pensino.utils.fingerPrint.FingerprintEngineObserver;
import br.com.pensino.utils.message.By;
import br.com.pensino.utils.message.MessageService;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author emiliowl
 */
public class RegisterPanel extends JPanel implements FingerprintEngineObserver {

    EmployeeDAO employeeDAO;
    FingerprintDAO fingerprintDAO;
    StudentDAO studentDAO;
    private FingerprintEngine fingerprintEngine = FingerprintEngine.getInstance();
    private FingerprintPanel fingerprintPanel = new FingerprintPanel();
    private MiddleRegisterPanel middlePanel = null;
    private static JLabel messageLabel = new JLabel(new ImageIcon("msg007.png"));
    private static JProgressBar progressBar = new JProgressBar();

    /** Creates new form MainPanel */
    public RegisterPanel(EmployeeDAO employeeDAO, StudentDAO studentDAO, FingerprintDAO fingerprintDAO) {
        initComponents();
        //initializing fingerprint matcher engine
        fingerprintEngine.startObserve(fingerprintPanel);
        fingerprintEngine.startObserve(this);
        fingerprintContentPanel.add(fingerprintPanel);
        //initializing the DAO objects
        this.employeeDAO = employeeDAO;
        this.studentDAO = studentDAO;
        this.fingerprintDAO = fingerprintDAO;
        //initializing central panel thus contains the employees and students to user selection
        middlePanel = new MiddleRegisterPanel(employeeDAO, studentDAO);
        //defining style of the screen elements
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
        RegisterPanel.setProgressStatus(75);
        return true;
    }

    @Override
    public boolean notifyTemplateExtracted(BufferedImage templateImage, byte[] templateData) {
        try {
            Person person = null;
            try {
                person = middlePanel.getSelectedPerson();
            } catch (PersonNotFoundException pnfe) {
                if (pnfe instanceof EmployeeNotFoundException) {
                    messageUpdater("msg006");
                    RegisterPanel.setProgressStatus(100);
                    messageUpdater("msg007");
                } else if (pnfe instanceof StudentNotFoundException) {
                    messageUpdater("msg008");
                    RegisterPanel.setProgressStatus(100);
                    messageUpdater("msg007");
                }
            }
            if (person instanceof Employee) {
                Employee employee = (Employee) person;
                Fingerprint fingerprint = new Fingerprint(templateData, employee);
                employee.addFingerprint(fingerprint);
                fingerprintDAO.save(fingerprint);
                if (employeeDAO.save(employee)) {
                    messageUpdater("msg009");
                    RegisterPanel.setProgressStatus(100);
                    messageUpdater("msg007");
                }
            } else if (person instanceof Student) {
                Student student = (Student) person;
                Fingerprint fingerprint = new Fingerprint(templateData, student);
                student.addFingerprint(fingerprint);
                fingerprintDAO.save(fingerprint);
                if (studentDAO.save(student)) {
                    messageUpdater("msg009");
                    RegisterPanel.setProgressStatus(100);
                    messageUpdater("msg007");
                }
            }
        } catch (Exception ex) {
            RegisterPanel.setProgressStatus(100);
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean showSimilarities(BufferedImage fingerprintImage) {
        return true;
    }

    @Override
    public void notifyFingerDown() {
        RegisterPanel.startProgress();
        RegisterPanel.setProgressStatus(25);
    }

    @Override
    public void notifyFingerUp() {
        RegisterPanel.setProgressStatus(50);
    }

    private void messageUpdater(String message) {
        try {
            Thread.sleep(750);
            messageLabel.setIcon(MessageService.getMessage(By.name(message)));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
