/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Daryl
 */


import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import models.BudgetModel;
import view.BudgetPanel;

public class BudgetController implements ListSelectionListener, TableModelListener {

    private BudgetModel tableModel;

    private BudgetPanel gui;

    public BudgetController(BudgetPanel gui) {
        this.gui = gui;
        tableModel = new BudgetModel();
        tableModel.addTableModelListener(this);
    }

    public void tableChanged(TableModelEvent e) {
        try {
            int firstIndex = e.getFirstRow();
            tableModel = new BudgetModel(tableModel.getList(),
            tableModel.getEntityManager());
            tableModel.addTableModelListener(this);
            gui.updateTable();
            gui.setitem_idTextField((String) tableModel.getValueAt(firstIndex, 0));
            gui.setproject_idTextField((String) tableModel.getValueAt(firstIndex, 1));
            gui.setitem_nameTextField((String) tableModel.getValueAt(firstIndex, 2));
            gui.setestimated_costTextField((String) tableModel.getValueAt(firstIndex, 3));
            gui.setactual_costTextField((String) tableModel.getValueAt(firstIndex, 4));
            
        } catch (Exception exp) {
            exp.getMessage();
            exp.printStackTrace();
        }
    }

// new code public TableModel
    public BudgetModel getTableModel() {
        return tableModel;
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
        int firstIndex = selectModel.getMinSelectionIndex();
        gui.setitem_idTextField((String) tableModel.getValueAt(firstIndex, 0));
        gui.setproject_idTextField((String) tableModel.getValueAt(firstIndex, 1));
        gui.setitem_nameTextField((String) tableModel.getValueAt(firstIndex, 2));
        gui.setestimated_costTextField((String) tableModel.getValueAt(firstIndex, 3));
        gui.setactual_costTextField((String) tableModel.getValueAt(firstIndex, 4));
            
    }
//
    public void addRow(String[] array) {
        tableModel.addRow(array);
    }
    
    public void deleteRow(String[] array) {
        tableModel.deleteRow(array);
    }
    public void applyFilter(String projectName){
        if(!tableModel.applyFilter(projectName)){
            gui.getErrorCode().setVisible(true);
        }
    }

    public void updateRow(String[] array) {
        tableModel.updateRow(array);
    }
    
    
    
    
}
