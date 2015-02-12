/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TaskController;
import flpm.GlobalSession;
import java.awt.BorderLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import models.TaskModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author niki
 */
public class TaskPanel extends javax.swing.JPanel {

    /**
     * Creates new form TaskPanel
     */
    private JTable jtable1; // the table displayed on the GUI
    private TaskController taskController; // glue between model and gui
    /**
     * Creates new form TaskGUI
     */
    public TaskPanel() {
        initComponents();
        errorCode.setVisible(false);
        taskController = new TaskController(this);
        addJTable();
        String role=GlobalSession.user_role;
        if(role.equals("Student")){
            addButton.setVisible(false);
            deleteButton.setVisible(false);
            updateButton.setVisible(false);
        }
    }
    public void addJTable() {
    	jtable1 = new JTable(taskController.getTableModel());
        jtable1.getSelectionModel().addListSelectionListener(taskController);
        JScrollPane scrollpane = new JScrollPane(jtable1);
    	jPanel2.setLayout(new BorderLayout());
    	jPanel2.add(scrollpane, BorderLayout.CENTER);
    }
    public void updateTable() {
    	jtable1.setModel(taskController.getTableModel());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        PersistenceUnitEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PersistenceUnit").createEntityManager();
        if(!GlobalSession.user_role.equals("Admin")){
            projectQuery = java.beans.Beans.isDesignTime() ? null : PersistenceUnitEntityManager.createQuery("SELECT p.projectName FROM Project p,MemberProject mp where p.projectName=mp.memberProjectPK.projectName and mp.memberProjectPK.userId=:user");
            projectQuery.setParameter("user", GlobalSession.user_id);
        }
        else{
            projectQuery = java.beans.Beans.isDesignTime() ? null : PersistenceUnitEntityManager.createQuery("SELECT p.projectName FROM Project p");
        }
        projectList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : projectQuery.getResultList();
        usersQuery = java.beans.Beans.isDesignTime() ? null : PersistenceUnitEntityManager.createQuery("SELECT u.memberProjectPK.userId FROM MemberProject u");
        usersList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : usersQuery.getResultList();
        jPanel1 = new javax.swing.JPanel();
        projectName = new javax.swing.JLabel();
        taskId = new javax.swing.JLabel();
        taskIDTextField = new javax.swing.JTextField();
        taskName = new javax.swing.JLabel();
        taskNameTextField = new javax.swing.JTextField();
        userId = new javax.swing.JLabel();
        taskDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskDescriptionTextArea = new javax.swing.JTextArea();
        isDeliverable = new javax.swing.JLabel();
        taskDeliverableComboBox = new javax.swing.JComboBox();
        taskPriority = new javax.swing.JLabel();
        taskPriorityComboBox = new javax.swing.JComboBox();
        taskStatus = new javax.swing.JLabel();
        taskStatusComboBox = new javax.swing.JComboBox();
        startDate = new javax.swing.JLabel();
        taskStartDate = new com.toedter.calendar.JDateChooser();
        endDate = new javax.swing.JLabel();
        taskEndDate = new com.toedter.calendar.JDateChooser();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        projectCombo = new org.jdesktop.swingx.JXComboBox();
        filterButton = new javax.swing.JButton();
        errorCode = new javax.swing.JLabel();
        userCombo = new org.jdesktop.swingx.JXComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        actualStartDate = new com.toedter.calendar.JDateChooser();
        actualEndDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        projectName.setText("Project Name");

        taskId.setText("Task ID");

        taskIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskIDTextFieldActionPerformed(evt);
            }
        });

        taskName.setText("Task Name");

        taskNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskNameTextFieldActionPerformed(evt);
            }
        });

        userId.setText("User ");

        taskDescription.setText("Task Description");

        taskDescriptionTextArea.setColumns(20);
        taskDescriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(taskDescriptionTextArea);

        isDeliverable.setText("Deliverable Task");

        taskDeliverableComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "True", "False", " " }));
        taskDeliverableComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskDeliverableComboBoxActionPerformed(evt);
            }
        });

        taskPriority.setText("Task Priority");

        taskPriorityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Medium", "Low" }));
        taskPriorityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskPriorityComboBoxActionPerformed(evt);
            }
        });

        taskStatus.setText("Task Status");

        taskStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Completed", "In Progress", "Aborted", " " }));
        taskStatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskStatusComboBoxActionPerformed(evt);
            }
        });

        startDate.setText("Estimated Start Date");

        endDate.setText("Estimated End Date");

        addButton.setBackground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(255, 255, 255));
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${resultList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, projectQuery, eLProperty, projectCombo);
        bindingGroup.addBinding(jComboBoxBinding);

        AutoCompleteDecorator.decorate(projectCombo);

        filterButton.setText("Apply Filter");
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        errorCode.setForeground(new java.awt.Color(0, 51, 255));
        errorCode.setText("No tasks to show");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${resultList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, usersQuery, eLProperty, userCombo);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel1.setText("Actual Start Date");

        jLabel2.setText("Actual End Date");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isDeliverable)
                            .addComponent(taskPriority)
                            .addComponent(taskStatus)
                            .addComponent(startDate)
                            .addComponent(endDate)
                            .addComponent(taskDescription))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                .addGap(146, 146, 146))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(taskStatusComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(taskPriorityComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(actualEndDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(actualStartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(taskStartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(taskEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(taskDeliverableComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(16, 16, 16))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(projectName)
                            .addComponent(taskId)
                            .addComponent(taskName)
                            .addComponent(userId))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(taskNameTextField)
                            .addComponent(userCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(projectCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(taskIDTextField))
                        .addGap(54, 54, 54)
                        .addComponent(filterButton)))
                .addGap(61, 61, 61))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(errorCode, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(errorCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(projectName)
                            .addComponent(projectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterButton))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(taskId)
                            .addComponent(taskIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(taskName)
                            .addComponent(taskNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userId)
                            .addComponent(userCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(taskDescription)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isDeliverable)
                            .addComponent(taskDeliverableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(taskPriority)
                            .addComponent(taskPriorityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(taskStatus)
                            .addComponent(taskStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDate)
                            .addComponent(taskStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(taskEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endDate))
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1))
                    .addComponent(actualStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(actualEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(updateButton)
                    .addComponent(deleteButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void taskNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taskNameTextFieldActionPerformed

    private void taskDeliverableComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskDeliverableComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taskDeliverableComboBoxActionPerformed

    private void taskPriorityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskPriorityComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taskPriorityComboBoxActionPerformed

    private void taskStatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskStatusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taskStatusComboBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String[] array =new String[jtable1.getColumnCount()];
        array[0] = projectCombo.getSelectedItem().toString();
        array[1] = taskIDTextField.getText();
        array[2] = taskNameTextField.getText();
        array[3] = userCombo.getSelectedItem().toString();
        array[4] = taskDescriptionTextArea.getText();
        array[5] = (String)taskDeliverableComboBox.getSelectedItem();
        array[6] = (String)taskPriorityComboBox.getSelectedItem();
        array[7] = (String)taskStatusComboBox.getSelectedItem();
        Date d = taskStartDate.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String stringDate = dateFormat.format(d);
        array[8] = stringDate;
        Date date = taskEndDate.getDate();
        SimpleDateFormat dFormat = new SimpleDateFormat();
        String strDate = dFormat.format(date);
        array[9] = strDate;
        d = actualStartDate.getDate();
        String asDate = dateFormat.format(d);
        array[10] = asDate;
        d = actualEndDate.getDate();
        String aeDate = dateFormat.format(d);
        array[11] = aeDate;
        taskController.addRow( array);
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        String[] array =new String[jtable1.getColumnCount()];
        array[0] = projectCombo.getSelectedItem().toString();
        array[1] = taskIDTextField.getText();
        array[2] = taskNameTextField.getText();
        array[3] = userCombo.getSelectedItem().toString();
        array[4] = taskDescriptionTextArea.getText();
        array[5] = (String)taskDeliverableComboBox.getSelectedItem();
        array[6] = (String)taskPriorityComboBox.getSelectedItem();
        array[7] = (String)taskStatusComboBox.getSelectedItem();
        Date d = taskStartDate.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String stringDate = dateFormat.format(d);
        array[8] = stringDate;
        Date date = taskEndDate.getDate();
        SimpleDateFormat dFormat = new SimpleDateFormat();
        String strDate = dFormat.format(date);
        array[9] = strDate;
         d = actualStartDate.getDate();
        String asDate = dateFormat.format(d);
        array[10] = asDate;
        d = actualEndDate.getDate();
        String aeDate = dateFormat.format(d);
        array[11] = aeDate;
        taskController.updateRow( array);
        ( (TaskModel) jtable1.getModel()).fireTableDataChanged();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String[] array = new String[jtable1.getColumnCount()];
        array[1] = taskIDTextField.getText();
        taskController.deleteRow(array[1]);
        ( (TaskModel) jtable1.getModel()).fireTableDataChanged();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        String projectName=projectCombo.getSelectedItem().toString();
        usersQuery = java.beans.Beans.isDesignTime() ? null : PersistenceUnitEntityManager.createQuery("SELECT u.memberProjectPK.userId FROM MemberProject u where u.memberProjectPK.projectName=:project");
        usersQuery.setParameter("project",projectName);
        usersList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : usersQuery.getResultList();

        taskController.applyFilter(projectName);
        ( (TaskModel) jtable1.getModel()).fireTableDataChanged();
    }//GEN-LAST:event_filterButtonActionPerformed

    private void taskIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taskIDTextFieldActionPerformed
    public String getProjectNameTextField() {
	return projectCombo.getSelectedItem().toString();
    }
    public void setProjectNameTextField(String projectName) {
        projectCombo.setSelectedItem(projectName);
       	
    }
    public JLabel getErrorCode(){
        return errorCode;
    }
    public String getTaskIDTextField() {
	return taskIDTextField.getText();
	}
    public void setTaskIDTextField(String taskId) {
	this.taskIDTextField.setText(taskId);
	}
    public String getTaskNameTextField() {
	return taskNameTextField.getText();
    }
    public void setTaskNameTextField(String
	taskName) {
	this.taskNameTextField.setText(taskName);
	}
    public String getUserIDTextField() {
	return userCombo.getSelectedItem().toString();
	}
    public void setUserIDTextField(String userID) {
	  userCombo.setSelectedItem(userID);
	}
     public String getTaskDescriptionTextArea() {
	return taskDescriptionTextArea.getText();
	}
    public void setTaskDescriptionTextArea(String taskDescription)
	{
	this.taskDescriptionTextArea.setText(taskDescription);
	}
    public void setIsTaskDeliverable(String isDeliverable) {
        taskDeliverableComboBox.setSelectedItem(isDeliverable);
    }
    public void setTaskPriority(String priority) {
        taskPriorityComboBox.setSelectedItem(priority);
    }
    public void setProjectStatus(String status) {
        taskStatusComboBox.setSelectedItem(status);
    }
    public void setTaskStartDateChooser (String d) {
        DateFormat format = new SimpleDateFormat();
        if(d==null){
            return;
        }
        Date date=null;
         try {
             date = format.parse(d);
         } catch (ParseException ex) {
             Logger.getLogger(ProjectPanel.class.getName()).log(Level.SEVERE, null, ex);
         }
        taskStartDate.setDate(date);
    }
    
    public void setTaskEndDateChooser(String d) {
        DateFormat format = new SimpleDateFormat();
        if(d==null){
            return;
        }
        Date date=null;
         try {
             date = format.parse(d);
         } catch (ParseException ex) {
             Logger.getLogger(ProjectPanel.class.getName()).log(Level.SEVERE, null, ex);
         }
        taskEndDate.setDate(date);
    }
    public void setActualStartDateChooser (String d) {
        DateFormat format = new SimpleDateFormat();
        Date date=null;
        if(d==null){
            return;
        }
         try {
             date = format.parse(d);
         } catch (ParseException ex) {
             Logger.getLogger(ProjectPanel.class.getName()).log(Level.SEVERE, null, ex);
         }
        actualStartDate.setDate(date);
    }
    
    public void setActualEndDateChooser(String d) {
        DateFormat format = new SimpleDateFormat();
        Date date=null;
        if(d==null){
            return;
        }
         try {
             date = format.parse(d);
         } catch (ParseException ex) {
             Logger.getLogger(ProjectPanel.class.getName()).log(Level.SEVERE, null, ex);
         }
        actualEndDate.setDate(date);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager PersistenceUnitEntityManager;
    private com.toedter.calendar.JDateChooser actualEndDate;
    private com.toedter.calendar.JDateChooser actualStartDate;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel endDate;
    private javax.swing.JLabel errorCode;
    private javax.swing.JButton filterButton;
    private javax.swing.JLabel isDeliverable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXComboBox projectCombo;
    private java.util.List<entity.Project> projectList;
    private javax.swing.JLabel projectName;
    private javax.persistence.Query projectQuery;
    private javax.swing.JLabel startDate;
    private javax.swing.JComboBox taskDeliverableComboBox;
    private javax.swing.JLabel taskDescription;
    private javax.swing.JTextArea taskDescriptionTextArea;
    private com.toedter.calendar.JDateChooser taskEndDate;
    private javax.swing.JTextField taskIDTextField;
    private javax.swing.JLabel taskId;
    private javax.swing.JLabel taskName;
    private javax.swing.JTextField taskNameTextField;
    private javax.swing.JLabel taskPriority;
    private javax.swing.JComboBox taskPriorityComboBox;
    private com.toedter.calendar.JDateChooser taskStartDate;
    private javax.swing.JLabel taskStatus;
    private javax.swing.JComboBox taskStatusComboBox;
    private javax.swing.JButton updateButton;
    private org.jdesktop.swingx.JXComboBox userCombo;
    private javax.swing.JLabel userId;
    private java.util.List<entity.Users> usersList;
    private javax.persistence.Query usersQuery;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
