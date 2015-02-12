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
import models.MemberProjectModel;
import view.MemberProjectPanel;

/**
 *
 * @author niki
 */
public class MemberProjectController  implements ListSelectionListener,TableModelListener {
        private MemberProjectModel tableModel;
        private MemberProjectPanel gui; 
	public MemberProjectController(MemberProjectPanel gui) {  
            this.gui = gui;            
            tableModel = new MemberProjectModel();
            tableModel.addTableModelListener(this);
	}   
	public TableModel getTableModel() {  
            return tableModel; 
        } 
	public void tableChanged(TableModelEvent e)
        {
            try {
		int firstIndex =  e.getFirstRow();    	
		tableModel = new MemberProjectModel(tableModel.getList(), tableModel.getEntityManager());
                tableModel.addTableModelListener(this);
		gui.updateTable();
                gui.setProjectName((String) tableModel.getValueAt(firstIndex,0));
		gui.setMemberName((String) tableModel.getValueAt(firstIndex,1));
			
            } catch(Exception exp) {
		exp.getMessage();
            }
	}
	public void valueChanged(ListSelectionEvent e) {  
            ListSelectionModel selectModel = (ListSelectionModel) e.getSource();  
            int firstIndex = selectModel.getMinSelectionIndex();   
            gui.setProjectName((String) tableModel.getValueAt(firstIndex,0));
            gui.setMemberName((String) tableModel.getValueAt(firstIndex,1));
           	 
         } 
	 public void addRow(String[] array) {  
		 tableModel.addRow(array);   
	 }
	
        public void deleteRow(String project_name,String user_id) {
                 tableModel.deleteRow(project_name,user_id);
        }
    
}
