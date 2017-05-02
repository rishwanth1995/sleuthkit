/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011-2017 Basis Technology Corp.
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

import java.nio.file.Paths;
import java.util.logging.Level;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.autopsy.coreutils.MessageNotifyUtil;

/**
 * A panel that allows the user to view various properties of a case and change
 * the display name of the case.
 */
class CasePropertiesPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(CasePropertiesPanel.class.getName());
    private final Case theCase;

    /**
     * Constructs a panel that allows the user to view various properties of the
     * current case and change the display name of the case.
     *
     * @param aCase A case.
     */
    CasePropertiesPanel(Case aCase) {
        initComponents();
        theCase = aCase;
        caseNameTextField.setText(theCase.getDisplayName());
        String caseNumber = theCase.getNumber();
        if (!caseNumber.isEmpty()) {
            caseNumberField.setText(caseNumber);
        } else {
            caseNumberField.setText("N/A");
        }
        String examiner = theCase.getExaminer();
        if (!examiner.isEmpty()) {
            examinerField.setText(examiner);
        } else {
            examinerField.setText("N/A");
        }
        crDateField.setText(theCase.getCreatedDate());
        caseDirField.setText(theCase.getCaseDirectory());
        if (Case.CaseType.SINGLE_USER_CASE == theCase.getCaseType()) {
            dbNameField.setText(Paths.get(theCase.getCaseDirectory(), theCase.getMetadata().getCaseDatabaseName()).toString());
        } else {
            dbNameField.setText(theCase.getMetadata().getCaseDatabaseName());
        }
        Case.CaseType caseType = theCase.getCaseType();
        caseTypeField.setText(caseType.getLocalizedDisplayName());        
        deleteCaseButton.setEnabled(Case.CaseType.SINGLE_USER_CASE == caseType);
    }

    /**
     * In this generated code below, there are 2 strings "Path" and "Remove"
     * that are table column headers in the DefaultTableModel. When this model
     * is generated, it puts the hard coded English strings into the generated
     * code. And then about 15 lines later, it separately internationalizes them
     * using: imagesTable.getColumnModel().getColumn(0).setHeaderValue(). There
     * is no way to prevent the GUI designer from putting the hard coded English
     * strings into the generated code. So, they remain, and are not used.
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        caseNameLabel = new javax.swing.JLabel();
        crDateLabel = new javax.swing.JLabel();
        caseDirLabel = new javax.swing.JLabel();
        caseNameTextField = new javax.swing.JTextField();
        updateCaseNameButton = new javax.swing.JButton();
        deleteCaseButton = new javax.swing.JButton();
        caseNumberLabel = new javax.swing.JLabel();
        examinerLabel = new javax.swing.JLabel();
        lbDbType = new javax.swing.JLabel();
        lbDbName = new javax.swing.JLabel();
        caseNumberField = new javax.swing.JLabel();
        examinerField = new javax.swing.JLabel();
        crDateField = new javax.swing.JLabel();
        caseDirField = new javax.swing.JLabel();
        dbNameField = new javax.swing.JLabel();
        caseTypeField = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        caseNameLabel.setFont(caseNameLabel.getFont().deriveFont(caseNameLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        caseNameLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.caseNameLabel.text")); // NOI18N

        crDateLabel.setFont(crDateLabel.getFont().deriveFont(crDateLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        crDateLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.crDateLabel.text")); // NOI18N

        caseDirLabel.setFont(caseDirLabel.getFont().deriveFont(caseDirLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        caseDirLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.caseDirLabel.text")); // NOI18N

        caseNameTextField.setFont(caseNameTextField.getFont().deriveFont(caseNameTextField.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        caseNameTextField.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.caseNameTextField.text")); // NOI18N

        updateCaseNameButton.setFont(updateCaseNameButton.getFont().deriveFont(updateCaseNameButton.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        updateCaseNameButton.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.updateCaseNameButton.text")); // NOI18N
        updateCaseNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCaseNameButtonActionPerformed(evt);
            }
        });

        deleteCaseButton.setFont(deleteCaseButton.getFont().deriveFont(deleteCaseButton.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        deleteCaseButton.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.deleteCaseButton.text")); // NOI18N
        deleteCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCaseButtonActionPerformed(evt);
            }
        });

        caseNumberLabel.setFont(caseNumberLabel.getFont().deriveFont(caseNumberLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        caseNumberLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.caseNumberLabel.text")); // NOI18N

        examinerLabel.setFont(examinerLabel.getFont().deriveFont(examinerLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        examinerLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.examinerLabel.text")); // NOI18N

        lbDbType.setFont(lbDbType.getFont().deriveFont(lbDbType.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        lbDbType.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.lbDbType.text")); // NOI18N

        lbDbName.setFont(lbDbName.getFont().deriveFont(lbDbName.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        lbDbName.setText(org.openide.util.NbBundle.getMessage(CasePropertiesPanel.class, "CasePropertiesPanel.lbDbName.text")); // NOI18N

        caseDirField.setMinimumSize(new java.awt.Dimension(25, 14));

        dbNameField.setMinimumSize(new java.awt.Dimension(25, 14));

        caseTypeField.setMaximumSize(new java.awt.Dimension(1, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDbName)
                            .addComponent(lbDbType)
                            .addComponent(caseDirLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(caseDirField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(caseTypeField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dbNameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(crDateLabel)
                            .addComponent(examinerLabel)
                            .addComponent(caseNumberLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(examinerField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(caseNumberField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(caseNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateCaseNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(crDateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(caseNameLabel)
                    .addContainerGap(392, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caseNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCaseNameButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(caseNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(examinerField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(examinerLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(crDateField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crDateLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(caseDirLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(caseDirField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(caseTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDbType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDbName)
                            .addComponent(dbNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(caseNumberLabel))
                .addGap(10, 10, 10)
                .addComponent(deleteCaseButton)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(caseNameLabel)
                    .addContainerGap(173, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Updates the case display name.
     *
     * @param evt The action event
     */
    @NbBundle.Messages({
        "CasePropertiesPanel.errorDialog.emptyCaseNameMessage=No case name entered.",
        "CasePropertiesPanel.errorDialog.invalidCaseNameMessage=Case names cannot include the following symbols: \\, /, :, *, ?, \", <, >, |"
    })
    private void updateCaseNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCaseNameButtonActionPerformed
        String newCaseDisplayName = caseNameTextField.getText();
        if (newCaseDisplayName.equals(theCase.getDisplayName())) {
            return;
        }

        if (newCaseDisplayName.trim().isEmpty()) {
            MessageNotifyUtil.Message.error(Bundle.CasePropertiesPanel_errorDialog_emptyCaseNameMessage());
            return;
        }

        if (!Case.isValidName(newCaseDisplayName)) {
            MessageNotifyUtil.Message.error(Bundle.CasePropertiesPanel_errorDialog_invalidCaseNameMessage());
            return;
        }

        try {
            theCase.updateDisplayName(newCaseDisplayName);
        } catch (CaseActionException ex) {
            MessageNotifyUtil.Message.error(ex.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, "Failed to update case display name", ex); //NON-NLS
        }
    }//GEN-LAST:event_updateCaseNameButtonActionPerformed

    private void deleteCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCaseButtonActionPerformed
        CallableSystemAction.get(CaseDeleteAction.class).actionPerformed(evt);
    }//GEN-LAST:event_deleteCaseButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel caseDirField;
    private javax.swing.JLabel caseDirLabel;
    private javax.swing.JLabel caseNameLabel;
    private javax.swing.JTextField caseNameTextField;
    private javax.swing.JLabel caseNumberField;
    private javax.swing.JLabel caseNumberLabel;
    private javax.swing.JLabel caseTypeField;
    private javax.swing.JLabel crDateField;
    private javax.swing.JLabel crDateLabel;
    private javax.swing.JLabel dbNameField;
    private javax.swing.JButton deleteCaseButton;
    private javax.swing.JLabel examinerField;
    private javax.swing.JLabel examinerLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbDbName;
    private javax.swing.JLabel lbDbType;
    private javax.swing.JButton updateCaseNameButton;
    // End of variables declaration//GEN-END:variables

}