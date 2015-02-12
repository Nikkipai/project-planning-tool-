/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import models.CommunicationModel;
import view.CommunicationPanel;


public class CommunicationController implements ListSelectionListener, TableModelListener {

    private CommunicationModel tableModel;

    private CommunicationPanel gui;

    public CommunicationController(CommunicationPanel gui) {
        this.gui = gui;
// create the tableModel using the data in the cachedRowSet      
        tableModel = new CommunicationModel();
        tableModel.addTableModelListener(this);
    }

    public void tableChanged(TableModelEvent e) {
        try {
// get the index of the inserted row
//tableModel.getRowSet().moveToCurrentRow();
            int firstIndex = e.getFirstRow();
// create a new table model with the new data
            tableModel = new CommunicationModel(tableModel.getList(),
                    tableModel.getEntityManager());
            tableModel.addTableModelListener(this);
// update the JTable with the data
            gui.updateTable();
// read the data in each column using getValueAt and display it on corresponding textfield
            gui.setproject_nameTextField((String) tableModel.getValueAt(firstIndex, 0));
            gui.setcommunication_idTextField((String) tableModel.getValueAt(firstIndex, 1));
            gui.setuser_idTextField((String) tableModel.getValueAt(firstIndex, 2));
            gui.setsubjectTextField((String) tableModel.getValueAt(firstIndex, 3));
            gui.setmessageTextArea((String) tableModel.getValueAt(firstIndex, 4));
            gui.setdateTextField((String) tableModel.getValueAt(firstIndex, 5));
            
            
            
            
        } catch (Exception exp) {
            exp.getMessage();
            exp.printStackTrace();
        }
    }

// new code public TableModel
    public CommunicationModel getTableModel() 
    {
        return tableModel;
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
        int firstIndex = selectModel.getMinSelectionIndex();
        // read the data in each column using getValueAt and display it on corresponding textfield  
         gui.setproject_nameTextField((String) tableModel.getValueAt(firstIndex, 0));
            gui.setcommunication_idTextField((String) tableModel.getValueAt(firstIndex, 1));
            gui.setuser_idTextField((String) tableModel.getValueAt(firstIndex, 2));
            gui.setsubjectTextField((String) tableModel.getValueAt(firstIndex, 3));
            gui.setmessageTextArea((String) tableModel.getValueAt(firstIndex, 4));
            gui.setdateTextField((String) tableModel.getValueAt(firstIndex, 5));
            
    }
//

    public void addRow(String[] array) {
        tableModel.addRow(array);
    }
    
    
    
    
    
}
