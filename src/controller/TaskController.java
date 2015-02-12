/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Upasna Tandon
 */
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import models.TaskModel;
import view.TaskPanel;

public class TaskController implements ListSelectionListener, TableModelListener {
    
    private TaskModel tableModel;

    private TaskPanel gui;

    public TaskController(TaskPanel gui) {
        this.gui = gui;
        tableModel = new TaskModel();
        tableModel.addTableModelListener(this);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        try {
            int firstIndex = e.getFirstRow();
            tableModel = new TaskModel(tableModel.getList(),tableModel.getEntityManager());
            tableModel.addTableModelListener(this);
            gui.updateTable();
            gui.setProjectNameTextField((String) tableModel.getValueAt(firstIndex, 0));
            gui.setTaskIDTextField((String) tableModel.getValueAt(firstIndex, 1));
            gui.setTaskNameTextField((String) tableModel.getValueAt(firstIndex, 2));
            gui.setUserIDTextField((String) tableModel.getValueAt(firstIndex, 3));
            gui.setTaskDescriptionTextArea( (String)tableModel.getValueAt(firstIndex, 4));
            gui.setIsTaskDeliverable((String) tableModel.getValueAt(firstIndex, 5));
            gui.setTaskPriority((String) tableModel.getValueAt(firstIndex, 6));
	    gui.setProjectStatus( (String)tableModel.getValueAt(firstIndex, 7));
            gui.setTaskStartDateChooser((String) tableModel.getValueAt(firstIndex, 8));
            gui.setTaskEndDateChooser((String) tableModel.getValueAt(firstIndex, 9)); 
            gui.setActualStartDateChooser((String) tableModel.getValueAt(firstIndex, 10));
            gui.setActualEndDateChooser((String) tableModel.getValueAt(firstIndex, 11)); 
        } catch (Exception exp) {
            exp.getMessage();
        }
    }

    public TaskModel getTableModel() {
        return tableModel;
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
        int firstIndex = selectModel.getMinSelectionIndex();
        gui.setProjectNameTextField((String) tableModel.getValueAt(firstIndex, 0));
        gui.setTaskIDTextField((String) tableModel.getValueAt(firstIndex, 1));
        gui.setTaskNameTextField((String) tableModel.getValueAt(firstIndex, 2));
        gui.setUserIDTextField((String) tableModel.getValueAt(firstIndex, 3));
        gui.setTaskDescriptionTextArea( (String)tableModel.getValueAt(firstIndex, 4));
        gui.setIsTaskDeliverable((String) tableModel.getValueAt(firstIndex, 5));
        gui.setTaskPriority((String) tableModel.getValueAt(firstIndex, 6));
	gui.setProjectStatus( (String)tableModel.getValueAt(firstIndex, 7));
        gui.setTaskStartDateChooser((String) tableModel.getValueAt(firstIndex, 8));
        gui.setTaskEndDateChooser((String) tableModel.getValueAt(firstIndex, 9));  
        gui.setActualStartDateChooser((String) tableModel.getValueAt(firstIndex, 10));
        gui.setActualEndDateChooser((String) tableModel.getValueAt(firstIndex, 11)); 
    }
    public void addRow(String[] array) {
        tableModel.addRow(array);
    }
    
    public void deleteRow(String id) {
        tableModel.deleteRow(id);
    }

    public void updateRow(String[] array) {
        tableModel.updateRow(array);
    }
    public void applyFilter(String projectName){
        if(!tableModel.applyFilter(projectName)){
            gui.getErrorCode().setVisible(true);
        }
    }
    
}
