/*
 * CoinBankView.java
 */

package coinbank;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import business.Bank;
import java.text.NumberFormat;
/**
 * The application's main frame.
 */
public class CoinBankView extends FrameView {
    
    Bank bk;

    public CoinBankView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = CoinBankApp.getApplication().getMainFrame();
            aboutBox = new CoinBankAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        CoinBankApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlblQuarters = new javax.swing.JLabel();
        jlblDimes = new javax.swing.JLabel();
        jlblNickels = new javax.swing.JLabel();
        jlblPen = new javax.swing.JLabel();
        jtxtQuart = new javax.swing.JTextField();
        jtxtDimes = new javax.swing.JTextField();
        jtxtNickels = new javax.swing.JTextField();
        jtxtPen = new javax.swing.JTextField();
        jbtnLoad = new javax.swing.JButton();
        jbtnQAdd = new javax.swing.JButton();
        jbtnQSub = new javax.swing.JButton();
        jbtnDAdd = new javax.swing.JButton();
        jbtnDSub = new javax.swing.JButton();
        jbtnNAdd = new javax.swing.JButton();
        jbtnNSub = new javax.swing.JButton();
        jbtnPAdd = new javax.swing.JButton();
        jbtnPSub = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jtxtTotal = new javax.swing.JTextField();
        jbtnClear = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(coinbank.CoinBankApp.class).getContext().getResourceMap(CoinBankView.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jlblQuarters.setFont(resourceMap.getFont("jlblQuarters.font")); // NOI18N
        jlblQuarters.setText(resourceMap.getString("jlblQuarters.text")); // NOI18N
        jlblQuarters.setName("jlblQuarters"); // NOI18N

        jlblDimes.setFont(resourceMap.getFont("jlblDimes.font")); // NOI18N
        jlblDimes.setText(resourceMap.getString("jlblDimes.text")); // NOI18N
        jlblDimes.setName("jlblDimes"); // NOI18N

        jlblNickels.setFont(resourceMap.getFont("jlblNickels.font")); // NOI18N
        jlblNickels.setText(resourceMap.getString("jlblNickels.text")); // NOI18N
        jlblNickels.setName("jlblNickels"); // NOI18N

        jlblPen.setFont(resourceMap.getFont("jlblPen.font")); // NOI18N
        jlblPen.setText(resourceMap.getString("jlblPen.text")); // NOI18N
        jlblPen.setName("jlblPen"); // NOI18N

        jtxtQuart.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtQuart.setText(resourceMap.getString("jtxtQuart.text")); // NOI18N
        jtxtQuart.setName("jtxtQuart"); // NOI18N

        jtxtDimes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtDimes.setText(resourceMap.getString("jtxtDimes.text")); // NOI18N
        jtxtDimes.setName("jtxtDimes"); // NOI18N

        jtxtNickels.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtNickels.setText(resourceMap.getString("jtxtNickels.text")); // NOI18N
        jtxtNickels.setName("jtxtNickels"); // NOI18N

        jtxtPen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtPen.setText(resourceMap.getString("jtxtPen.text")); // NOI18N
        jtxtPen.setName("jtxtPen"); // NOI18N

        jbtnLoad.setText(resourceMap.getString("jbtnLoad.text")); // NOI18N
        jbtnLoad.setName("jbtnLoad"); // NOI18N
        jbtnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoadActionPerformed(evt);
            }
        });

        jbtnQAdd.setText(resourceMap.getString("jbtnQAdd.text")); // NOI18N
        jbtnQAdd.setEnabled(false);
        jbtnQAdd.setName("jbtnQAdd"); // NOI18N
        jbtnQAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnQAddActionPerformed(evt);
            }
        });

        jbtnQSub.setText(resourceMap.getString("jbtnQSub.text")); // NOI18N
        jbtnQSub.setEnabled(false);
        jbtnQSub.setName("jbtnQSub"); // NOI18N
        jbtnQSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnQSubActionPerformed(evt);
            }
        });

        jbtnDAdd.setText(resourceMap.getString("jbtnDAdd.text")); // NOI18N
        jbtnDAdd.setEnabled(false);
        jbtnDAdd.setName("jbtnDAdd"); // NOI18N

        jbtnDSub.setText(resourceMap.getString("jbtnDSub.text")); // NOI18N
        jbtnDSub.setEnabled(false);
        jbtnDSub.setName("jbtnDSub"); // NOI18N

        jbtnNAdd.setText(resourceMap.getString("jbtnNAdd.text")); // NOI18N
        jbtnNAdd.setEnabled(false);
        jbtnNAdd.setName("jbtnNAdd"); // NOI18N

        jbtnNSub.setText(resourceMap.getString("jbtnNSub.text")); // NOI18N
        jbtnNSub.setEnabled(false);
        jbtnNSub.setName("jbtnNSub"); // NOI18N

        jbtnPAdd.setText(resourceMap.getString("jbtnPAdd.text")); // NOI18N
        jbtnPAdd.setEnabled(false);
        jbtnPAdd.setName("jbtnPAdd"); // NOI18N

        jbtnPSub.setText(resourceMap.getString("jbtnPSub.text")); // NOI18N
        jbtnPSub.setEnabled(false);
        jbtnPSub.setName("jbtnPSub"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jtxtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtTotal.setText(resourceMap.getString("jtxtTotal.text")); // NOI18N
        jtxtTotal.setName("jtxtTotal"); // NOI18N

        jbtnClear.setText(resourceMap.getString("jbtnClear.text")); // NOI18N
        jbtnClear.setName("jbtnClear"); // NOI18N
        jbtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel1))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblQuarters)
                            .addComponent(jlblDimes)
                            .addComponent(jlblNickels)
                            .addComponent(jlblPen))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtnLoad, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(jtxtQuart)
                            .addComponent(jtxtDimes)
                            .addComponent(jtxtNickels)
                            .addComponent(jtxtPen))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbtnQAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                    .addComponent(jbtnDAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnNAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnPAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jbtnDSub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                    .addComponent(jbtnPSub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnNSub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnQSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnClear)
                .addGap(87, 87, 87))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblQuarters)
                    .addComponent(jtxtQuart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnQAdd)
                    .addComponent(jbtnQSub, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblDimes)
                    .addComponent(jtxtDimes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnDAdd)
                    .addComponent(jbtnDSub))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNickels)
                    .addComponent(jtxtNickels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNAdd)
                    .addComponent(jbtnNSub))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblPen)
                    .addComponent(jtxtPen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPAdd)
                    .addComponent(jbtnPSub))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jbtnLoad))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnClear)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(coinbank.CoinBankApp.class).getContext().getActionMap(CoinBankView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLoadActionPerformed
        statusMessageLabel.setText("");
        double q,d,n,p;
        
        try {
            q = Double.parseDouble(jtxtQuart.getText());
        } catch (NumberFormatException e) {
            statusMessageLabel.setText("Illegal Quarters Type");
            jtxtQuart.requestFocusInWindow();
            return;
            
        }
        
         try {
            d = Double.parseDouble(jtxtDimes.getText());
        } catch (NumberFormatException e) {
            statusMessageLabel.setText("Illegal Dimes Type");
            jtxtDimes.requestFocusInWindow();
            return;
        }
         
         try {
            n = Double.parseDouble(jtxtNickels.getText());
        } catch (NumberFormatException e) {
            statusMessageLabel.setText("Illegal Nickels Type");
            jtxtNickels.requestFocusInWindow();
            return;
        }
         
         try {
             p = Double.parseDouble(jtxtPen.getText());
            
        } catch (NumberFormatException e) {
            statusMessageLabel.setText("illegal Pen Type");
            jtxtPen.requestFocusInWindow();
            return; 
        }
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        Bank bk = new Bank(q,d,n,p);
        if (!bk.getErrorMsg().isEmpty()) {
            statusMessageLabel.setText(bk.getErrorMsg());
            
        } else {
            jtxtTotal.setText(curr.format(bk.getResult()));
            jbtnQAdd.setEnabled(true);
            
            jbtnQSub.setEnabled(true);
            jbtnDAdd.setEnabled(true);
            jbtnDSub.setEnabled(true);
            jbtnNAdd.setEnabled(true);
            jbtnNSub.setEnabled(true);
            jbtnPAdd.setEnabled(true);
            jbtnPSub.setEnabled(true);
            
        
        }
        
        if(jbtnDAdd.isSelected()) {
            jtxtQuart.setText(String.valueOf(bk.getAddCoint(q)));
         
        }
        
        if(jbtnQSub.isSelected()) {
            jtxtQuart.setText(String.valueOf(bk.getSubCoint(q)));
        }
        
        
        
            
        
        
        
        
        
        
       
        
        
        
        
            
        
        
          
        
        
        
         
        
         
         
        
                 
                 
    
             
          
             
         
         
         
        
        
        
    

    }//GEN-LAST:event_jbtnLoadActionPerformed

    private void jbtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearActionPerformed
        statusMessageLabel.setText("");
        jtxtQuart.setText("");
        jtxtDimes.setText("");
        jtxtNickels.setText("");
        jtxtPen.setText("");
        jtxtTotal.setText("");
        jtxtQuart.requestFocusInWindow();
        
    }//GEN-LAST:event_jbtnClearActionPerformed

    private void jbtnQAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnQAddActionPerformed
     
       /*  double q;
        statusMessageLabel.setText("");
        q = Double.parseDouble(jtxtQuart.getText());
        
        if (!bk.getErrorMsg().isEmpty()) {
            statusMessageLabel.setText(bk.getErrorMsg());
        } else {
            jtxtQuart.setText(String.valueOf(bk.getAddCoint(q)));
        } */    
        
        
        
        
            
        
       
        
        
        
        
         
        
           
    }//GEN-LAST:event_jbtnQAddActionPerformed

    private void jbtnQSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnQSubActionPerformed
     /*  double q;
        statusMessageLabel.setText("");
        q = Double.parseDouble(jtxtQuart.getText());
        
        if (!bk.getErrorMsg().isEmpty()) {
            statusMessageLabel.setText(bk.getErrorMsg());
        } else {
            jtxtQuart.setText(String.valueOf(bk.getSubCoint(q)));
        } */
    }//GEN-LAST:event_jbtnQSubActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnDAdd;
    private javax.swing.JButton jbtnDSub;
    private javax.swing.JButton jbtnLoad;
    private javax.swing.JButton jbtnNAdd;
    private javax.swing.JButton jbtnNSub;
    private javax.swing.JButton jbtnPAdd;
    private javax.swing.JButton jbtnPSub;
    private javax.swing.JButton jbtnQAdd;
    private javax.swing.JButton jbtnQSub;
    private javax.swing.JLabel jlblDimes;
    private javax.swing.JLabel jlblNickels;
    private javax.swing.JLabel jlblPen;
    private javax.swing.JLabel jlblQuarters;
    private javax.swing.JTextField jtxtDimes;
    private javax.swing.JTextField jtxtNickels;
    private javax.swing.JTextField jtxtPen;
    private javax.swing.JTextField jtxtQuart;
    private javax.swing.JTextField jtxtTotal;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
