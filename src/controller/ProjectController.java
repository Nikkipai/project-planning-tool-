/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author User
 */
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import models.ProjectModel;
import view.ProjectPanel;


public class ProjectController implements ListSelectionListener, TableModelListener {
    private ProjectModel tableModel;

    private ProjectPanel gui;

    public ProjectController(ProjectPanel gui) {
        this.gui = gui;
        tableModel = new ProjectModel();
        tableModel.addTableModelListener(this);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        try {
            int firstIndex = e.getFirstRow();
            tableModel = new ProjectModel(tableModel.getList(),tableModel.getEntityManager());
            tableModel.addTableModelListener(this);
            gui.updateTable();
            gui.setProjectNameTextField((String) tableModel.getValueAt(firstIndex, 0));
            gui.setProjectInchargeIDTextField((String) tableModel.getValueAt(firstIndex, 1));
            gui.setProjectClientIDTextField((String) tableModel.getValueAt(firstIndex, 2));
            gui.setProjectStatus((String) tableModel.getValueAt(firstIndex, 3));
            gui.setProjectDescriptionTextArea( (String)tableModel.getValueAt(firstIndex, 4));
	    gui.setProjectOutcomeTextField( (String)tableModel.getValueAt(firstIndex, 5));
            gui.setProjectTagTextField((String) tableModel.getValueAt(firstIndex, 6));
            gui.setProjectStartDateChooser((String) tableModel.getValueAt(firstIndex, 7)); 
            gui.setProjectEndDateChooser((String) tableModel.getValueAt(firstIndex, 8));
        } catch (Exception exp) {
            exp.getMessage();
        }
    }
    public ProjectModel getTableModel() {
        return tableModel;
    }
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
        int firstIndex = selectModel.getMinSelectionIndex();
        gui.setProjectNameTextField((String) tableModel.getValueAt(firstIndex, 0));  
        gui.setProjectInchargeIDTextField((String) tableModel.getValueAt(firstIndex, 1));
        gui.setProjectClientIDTextField((String) tableModel.getValueAt(firstIndex, 2));
        gui.setProjectStatus((String) tableModel.getValueAt(firstIndex, 3));
        gui.setProjectDescriptionTextArea((String) tableModel.getValueAt(firstIndex, 4));
        gui.setProjectOutcomeTextField((String) tableModel.getValueAt(firstIndex, 5));
        gui.setProjectTagTextField((String) tableModel.getValueAt(firstIndex, 6));
        gui.setProjectStartDateChooser((String) tableModel.getValueAt(firstIndex, 7));
        gui.setProjectEndDateChooser((String) tableModel.getValueAt(firstIndex, 8));
    }
    public void addRow(String[] array) {
        tableModel.addRow(array);
    }
    public void deleteRow(String id) {
        tableModel.deleteRow(id);
    }
    public void updateRow(String[] array){
        tableModel.updateRow(array);
    }
    
}
