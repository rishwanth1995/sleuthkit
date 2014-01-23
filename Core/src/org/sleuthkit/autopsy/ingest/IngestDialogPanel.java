/*
 * Autopsy Forensic Browser
 * 
 * Copyright 2011-2013 Basis Technology Corp.
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
package org.sleuthkit.autopsy.ingest;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.sleuthkit.autopsy.corecomponents.AdvancedConfigurationDialog;
import org.sleuthkit.autopsy.coreutils.ModuleSettings;

/**
 * main configuration panel for all ingest modules, reusable JPanel component
 */
public class IngestDialogPanel extends javax.swing.JPanel {

    private IngestModuleAbstract currentModule;
    private ModulesTableModel tableModel;
    private String context;

    /**
     * Creates new form IngestDialogPanel
     */
    public IngestDialogPanel() {
        tableModel = new ModulesTableModel();
        context = ModuleSettings.DEFAULT_CONTEXT;
        initComponents();
        customizeComponents();
    }
    
    public void setContext(String context) {
        this.context = context;
    }
    
    
    public IngestModuleAbstract getCurrentIngestModule() {
        return currentModule;
    }
    
    public List<IngestModuleAbstract> getModulesToStart() {
        return tableModel.getSelectedModules();
    }
    
    public List<IngestModuleAbstract> getDisabledModules() {
        return tableModel.getUnSelectedModules();
    }
    
    public boolean processUnallocSpaceEnabled() {
        return processUnallocCheckbox.isSelected();
    }

    private void customizeComponents() {
        modulesTable.setModel(tableModel);
        modulesTable.setTableHeader(null);
        modulesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //custom renderer for tooltips
        ModulesTableRenderer renderer = new ModulesTableRenderer();
        
        //customize column witdhs
        final int width = modulesScrollPane.getPreferredSize().width;
        TableColumn column = null;
        for (int i = 0; i < modulesTable.getColumnCount(); i++) {
            column = modulesTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(((int) (width * 0.15)));
            } else {
                column.setCellRenderer(renderer);
                column.setPreferredWidth(((int) (width * 0.84)));
            }
        }

        modulesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();
                if (!listSelectionModel.isSelectionEmpty()) {
                    int index = listSelectionModel.getMinSelectionIndex();
                    currentModule = tableModel.getModule(index);
                    
                    // add the module-specific configuration panel, if there is one
                    simplePanel.removeAll();
                    if (currentModule.hasSimpleConfiguration()) {
                        simplePanel.add(currentModule.getSimpleConfiguration(context));
                    }
                    simplePanel.revalidate();
                    simplePanel.repaint();
                    advancedButton.setEnabled(currentModule.hasAdvancedConfiguration());
                } else {
                    currentModule = null;
                }
            }
        });
    }

    public void setProcessUnallocSpaceEnabled(final boolean enabled) {
        processUnallocCheckbox.setSelected(enabled);
    }
    
    public void setEnabledIngestModules(List<IngestModuleAbstract> enabledModules) {
        tableModel.setSelectedModules(enabledModules);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timeGroup = new javax.swing.ButtonGroup();
        modulesScrollPane = new javax.swing.JScrollPane();
        modulesTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        advancedButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        simplePanel = new javax.swing.JPanel();
        processUnallocPanel = new javax.swing.JPanel();
        processUnallocCheckbox = new javax.swing.JCheckBox();

        setMaximumSize(new java.awt.Dimension(5750, 3000));
        setMinimumSize(new java.awt.Dimension(522, 257));
        setPreferredSize(new java.awt.Dimension(575, 300));

        modulesScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(160, 160, 160)));
        modulesScrollPane.setPreferredSize(new java.awt.Dimension(160, 160));

        modulesTable.setBackground(new java.awt.Color(240, 240, 240));
        modulesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        modulesTable.setShowHorizontalLines(false);
        modulesTable.setShowVerticalLines(false);
        modulesScrollPane.setViewportView(modulesTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(160, 160, 160)));
        jPanel1.setPreferredSize(new java.awt.Dimension(338, 257));

        advancedButton.setText(org.openide.util.NbBundle.getMessage(IngestDialogPanel.class, "IngestDialogPanel.advancedButton.text")); // NOI18N
        advancedButton.setEnabled(false);
        advancedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advancedButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(250, 180));

        simplePanel.setLayout(new javax.swing.BoxLayout(simplePanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(simplePanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(advancedButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(advancedButton)
                .addContainerGap())
        );

        processUnallocPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(160, 160, 160)));

        processUnallocCheckbox.setText(org.openide.util.NbBundle.getMessage(IngestDialogPanel.class, "IngestDialogPanel.processUnallocCheckbox.text")); // NOI18N
        processUnallocCheckbox.setToolTipText(org.openide.util.NbBundle.getMessage(IngestDialogPanel.class, "IngestDialogPanel.processUnallocCheckbox.toolTipText")); // NOI18N
        processUnallocCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processUnallocCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout processUnallocPanelLayout = new javax.swing.GroupLayout(processUnallocPanel);
        processUnallocPanel.setLayout(processUnallocPanelLayout);
        processUnallocPanelLayout.setHorizontalGroup(
            processUnallocPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(processUnallocPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(processUnallocCheckbox)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        processUnallocPanelLayout.setVerticalGroup(
            processUnallocPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, processUnallocPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(processUnallocCheckbox)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modulesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(processUnallocPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modulesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(processUnallocPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void advancedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advancedButtonActionPerformed
        final AdvancedConfigurationDialog dialog = new AdvancedConfigurationDialog();
        dialog.addApplyButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.close();
                currentModule.saveAdvancedConfiguration();
            }
        });
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.close();
            }
        });
        dialog.display(currentModule.getAdvancedConfiguration(context));
    }//GEN-LAST:event_advancedButtonActionPerformed

    private void processUnallocCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processUnallocCheckboxActionPerformed
        // nothing to do here
    }//GEN-LAST:event_processUnallocCheckboxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton advancedButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JScrollPane modulesScrollPane;
    private javax.swing.JTable modulesTable;
    private javax.swing.JCheckBox processUnallocCheckbox;
    private javax.swing.JPanel processUnallocPanel;
    private javax.swing.JPanel simplePanel;
    private javax.swing.ButtonGroup timeGroup;
    // End of variables declaration//GEN-END:variables

    private class ModulesTableModel extends AbstractTableModel {
        
        private List<Map.Entry<IngestModuleAbstract, Boolean>>moduleData = new ArrayList<>();

        public ModulesTableModel() {
            List<IngestModuleAbstract> modules = IngestManager.getDefault().enumerateAllModules();
            for (IngestModuleAbstract ingestModuleAbstract : modules) {
                moduleData.add(new AbstractMap.SimpleEntry<>(ingestModuleAbstract, Boolean.TRUE));
            }
        }

        @Override
        public int getRowCount() {
            return moduleData.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map.Entry<IngestModuleAbstract, Boolean> entry = moduleData.get(rowIndex);
            if (columnIndex == 0) {
                return entry.getValue();
            } else {
                return entry.getKey().getName();
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 0;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                moduleData.get(rowIndex).setValue((Boolean)aValue);
            }
        }

        @Override
        public Class<?> getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        
        public List<IngestModuleAbstract> getSelectedModules() {
            List<IngestModuleAbstract> selectedModules = new ArrayList<>();
            for (Map.Entry<IngestModuleAbstract, Boolean> entry : moduleData) {
                if (entry.getValue().booleanValue()) {
                    selectedModules.add(entry.getKey());
                }
            }
            return selectedModules;
        }
        
        public List<IngestModuleAbstract> getUnSelectedModules() {
            List<IngestModuleAbstract> unselectedModules = new ArrayList<>();
            for (Map.Entry<IngestModuleAbstract, Boolean> entry : moduleData) {
                if (!entry.getValue().booleanValue()) {
                    unselectedModules.add(entry.getKey());
                }
            }
            return unselectedModules;
        }        
        
        /**
         * Sets the given modules as selected in the modules table
         * @param selectedModules 
         */
        public void setSelectedModules(List<IngestModuleAbstract> selectedModules) {
            // unselect all modules
            for (Map.Entry<IngestModuleAbstract, Boolean> entry : moduleData) {
                entry.setValue(Boolean.FALSE);
            }
            
            // select only the given modules
            for (IngestModuleAbstract selectedModule : selectedModules) {
                getEntryForModule(selectedModule).setValue(Boolean.TRUE);
            }
            
            // tell everyone about it
            fireTableDataChanged();
        }
        
        /**
         * Sets the given modules as NOT selected in the modules table
         * @param selectedModules 
         */
        public void setUnselectedModules(List<IngestModuleAbstract> unselectedModules) {
            // select all modules
            for (Map.Entry<IngestModuleAbstract, Boolean> entry : moduleData) {
                entry.setValue(Boolean.TRUE);
            }
            
            // unselect only the given modules
            for (IngestModuleAbstract unselectedModule : unselectedModules) {
                getEntryForModule(unselectedModule).setValue(Boolean.FALSE);
            }
            
            // tell everyone about it
            fireTableDataChanged();
        }
        
        public IngestModuleAbstract getModule(int row) {
            return moduleData.get(row).getKey();
        }
        
        private Map.Entry<IngestModuleAbstract, Boolean> getEntryForModule(IngestModuleAbstract module) {
            Map.Entry<IngestModuleAbstract, Boolean> entry = null;
            for (Map.Entry<IngestModuleAbstract, Boolean> anEntry : moduleData) {
                if (anEntry.getKey().equals(module)) {
                    entry = anEntry;
                    break;
                }
            }
            return entry;
        }
    }

    /**
     * Custom cell renderer for tool tips with module description
     */
    private class ModulesTableRenderer extends DefaultTableCellRenderer {
        
        List<String> tooltips = new ArrayList<>();

        public ModulesTableRenderer() {
            List<IngestModuleAbstract> modules = IngestManager.getDefault().enumerateAllModules();
            for (IngestModuleAbstract ingestModuleAbstract : modules) {
                tooltips.add(ingestModuleAbstract.getDescription());
            }
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {                
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (column == 1) {
                setToolTipText(tooltips.get(row));
            }
            return this;
        }
    }
}