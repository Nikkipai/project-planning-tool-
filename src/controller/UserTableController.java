/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author niki
 */
import entity.Users;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import view.UsersPanel;
import models.UserListTableModel;

public class UserTableController implements ListSelectionListener,TableModelListener{ 
        private UserListTableModel tableModel;
        private UsersPanel gui; 
	public UserTableController(UsersPanel gui) {  
            this.gui = gui;            
            tableModel = new UserListTableModel();
            tableModel.addTableModelListener(this);
	}   
       
	public TableModel getTableModel() {  
            return tableModel; 
        } 
	public void tableChanged(TableModelEvent e)
        {
            try {
		int firstIndex =  e.getFirstRow();    	
		tableModel = new UserListTableModel(tableModel.getList(), tableModel.getEntityManager());
                tableModel.addTableModelListener(this);
		gui.updateTable();
                gui.setUserId((String) tableModel.getValueAt(firstIndex,0));
		gui.setUserName((String) tableModel.getValueAt(firstIndex,1));
		gui.setUserRole((String)tableModel.getValueAt(firstIndex,2));
		gui.setUserEmail((String)tableModel.getValueAt(firstIndex,3));
		gui.setUserPhone((String)tableModel.getValueAt(firstIndex,4));		
            } catch(Exception exp) {
		exp.getMessage();
            }
	}
	public void valueChanged(ListSelectionEvent e) {  
            ListSelectionModel selectModel = (ListSelectionModel) e.getSource();  
            int firstIndex = selectModel.getMinSelectionIndex();   
            gui.setUserId((String) tableModel.getValueAt(firstIndex,0));
            gui.setUserName((String) tableModel.getValueAt(firstIndex,1));
            gui.setUserRole((String)tableModel.getValueAt(firstIndex,2));
            gui.setUserEmail((String)tableModel.getValueAt(firstIndex,3));
            gui.setUserPhone((String)tableModel.getValueAt(firstIndex,4));	 
         } 
	 public void addRow(String[] array) {  
		 tableModel.addRow(array);   
	 }
	
        public void updateRow(String[] array) {
                tableModel.updateRow(array);
        }

        public void deleteRow(String id) {
                 tableModel.deleteRow(id);
        }
        public Users readRow(String id){
           return  tableModel.readRow(id);
        }
	

}
	



