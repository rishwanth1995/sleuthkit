/*
 * Autopsy Forensic Browser
 *
 * Copyright 2012-2014 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.casemodule;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.datamodel.SleuthkitCase;
import org.sleuthkit.datamodel.TskCoreException;

class MissingImageDialog extends javax.swing.JDialog {

    private static final Logger logger = Logger.getLogger(MissingImageDialog.class.getName());
    long obj_id;
    SleuthkitCase db;
    static final GeneralFilter rawFilter = new GeneralFilter(GeneralFilter.RAW_IMAGE_EXTS, GeneralFilter.RAW_IMAGE_DESC);
    static final GeneralFilter encaseFilter = new GeneralFilter(GeneralFilter.ENCASE_IMAGE_EXTS, GeneralFilter.ENCASE_IMAGE_DESC);
    static final List<String> allExt = new ArrayList<String>();

    static {
        allExt.addAll(GeneralFilter.RAW_IMAGE_EXTS);
        allExt.addAll(GeneralFilter.ENCASE_IMAGE_EXTS);
    }
    static final String allDesc = NbBundle.getMessage(MissingImageDialog.class, "MissingImageDialog.allDesc.text");
    static final GeneralFilter allFilter = new GeneralFilter(allExt, allDesc);
    private JFileChooser fc = new JFileChooser();

    private MissingImageDialog(long obj_id, SleuthkitCase db) {
        super(new JFrame(), true);
        this.obj_id = obj_id;
        this.db = db;
        initComponents();

        fc.setDragEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(false);

        fc.addChoosableFileFilter(rawFilter);
        fc.addChoosableFileFilter(encaseFilter);
        fc.setFileFilter(allFilter);


        customInit();
    }

//    
//     * Client call to create a MissingImageDialog.
//     * 
//     * @param obj_id obj_id of the missing image
//     * @param db the current SleuthkitCase connected to a db
//     
    static void makeDialog(long obj_id, SleuthkitCase db) {
        final MissingImageDialog dialog = new MissingImageDialog(obj_id, db);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.cancel();
            }
        });
        dialog.display();
    }

    private void customInit() {

        selectButton.setEnabled(false);
    }

    private void display() {
        this.setTitle(NbBundle.getMessage(this.getClass(), "MissingImageDialog.display.title"));
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        // set the popUp window / JFrame
        int w = this.getSize().width;
        int h = this.getSize().height;
        // set the location of the popUp Window on the center of the screen
        setLocation((screenDimension.width - w) / 2, (screenDimension.height - h) / 2);

        this.setVisible(true);
    }

//    
//     * Focuses the select button for easy enter-pressing access.
//     
    private void moveFocusToSelect() {
        this.selectButton.requestFocusInWindow();
    }

//    
//     * Enables/disables the select button based off the current panel.
//     
    private void updateSelectButton() {

        // Enable this based on whether there is a valid path
        if (!pathNameTextField.getText().isEmpty()) {
            String filePath = pathNameTextField.getText();
            boolean isExist = Case.pathExists(filePath) || Case.driveExists(filePath);
            selectButton.setEnabled(isExist);
        }
    }

//    
//     * This method is called from within the constructor to initialize the form.
//     * WARNING: Do NOT modify this code. The content of this method is always
//     * regenerated by the Form Editor.
//     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPanel = new javax.swing.JPanel();
        selectButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        containerPanel = new javax.swing.JPanel();
        pathNameTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        titleSeparator = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        org.openide.awt.Mnemonics.setLocalizedText(selectButton, org.openide.util.NbBundle.getMessage(MissingImageDialog.class, "MissingImageDialog.selectButton.text")); // NOI18N
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(cancelButton, org.openide.util.NbBundle.getMessage(MissingImageDialog.class, "MissingImageDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(selectButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pathNameTextField.setText(org.openide.util.NbBundle.getMessage(MissingImageDialog.class, "MissingImageDialog.pathNameTextField.text")); // NOI18N
        pathNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathNameTextFieldActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(browseButton, org.openide.util.NbBundle.getMessage(MissingImageDialog.class, "MissingImageDialog.browseButton.text")); // NOI18N
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containerPanelLayout = new javax.swing.GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pathNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(browseButton)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N NON-NLS
        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(MissingImageDialog.class, "MissingImageDialog.titleLabel.text")); // NOI18N

        titleSeparator.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titleSeparator)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleLabel)
                    .addComponent(titleSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
        try {
            String newPath = pathNameTextField.getText();
            //TODO handle local files
            db.setImagePaths(obj_id, Arrays.asList(new String[]{newPath}));
        } catch (TskCoreException ex) {
            logger.log(Level.WARNING, "Error setting image paths", ex); //NON-NLS
        }
        this.dispose();
    }//GEN-LAST:event_selectButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancel();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void pathNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathNameTextFieldActionPerformed
        // TODO add your handling code here:

        updateSelectButton();
    }//GEN-LAST:event_pathNameTextFieldActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed

        String oldText = pathNameTextField.getText();

        // set the current directory of the FileChooser if the ImagePath Field is valid
        File currentDir = new File(oldText);
        if (currentDir.exists()) {
            fc.setCurrentDirectory(currentDir);
        }

        int retval = fc.showOpenDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            pathNameTextField.setText(path);
        }
        //pcs.firePropertyChange(DataSourceProcessor.DSP_PANEL_EVENT.FOCUS_NEXT.toString(), false, true);

        updateSelectButton();
    }//GEN-LAST:event_browseButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JTextField pathNameTextField;
    private javax.swing.JButton selectButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JSeparator titleSeparator;
    // End of variables declaration//GEN-END:variables

//    
//     * Verify the user wants to cancel searching for the image.
//     
    void cancel() {
        int ret = JOptionPane.showConfirmDialog(null,
                NbBundle.getMessage(this.getClass(),
                "MissingImageDialog.confDlg.noFileSel.msg"),
                NbBundle.getMessage(this.getClass(),
                "MissingImageDialog.confDlg.noFileSel.title"),
                JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }
}