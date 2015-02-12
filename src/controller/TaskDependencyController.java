 
package controller;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import models.TaskDependencyModel;
import view.TaskDependencyPanel;



/**
 *
 * @author Upasana Tandon
 */
public class TaskDependencyController implements ListSelectionListener,TableModelListener{
    
    private TaskDependencyModel tableModel;
        private TaskDependencyPanel gui; 
	public TaskDependencyController(TaskDependencyPanel gui) {  
            this.gui = gui;            
            tableModel = new TaskDependencyModel();
            tableModel.addTableModelListener(this);
	}   
	public TaskDependencyModel getTableModel() {  
            return tableModel; 
        } 
	public void tableChanged(TableModelEvent e)
        {
            try {
		int firstIndex =  e.getFirstRow();    	
		tableModel = new TaskDependencyModel(tableModel.getList(), tableModel.getEntityManager());
                tableModel.addTableModelListener(this);
		gui.updateTable();
                gui.setProjectName((String) tableModel.getValueAt(firstIndex,0));
                gui.setTaskID((String) tableModel.getValueAt(firstIndex,1));
		gui.setDependencyID((String) tableModel.getValueAt(firstIndex,2));
			
            } catch(Exception exp) {
		exp.getMessage();
            }
	}
	public void valueChanged(ListSelectionEvent e) {  
            ListSelectionModel selectModel = (ListSelectionModel) e.getSource();  
            int firstIndex = selectModel.getMinSelectionIndex();   
            gui.setProjectName((String) tableModel.getValueAt(firstIndex,0));
            gui.setTaskID((String) tableModel.getValueAt(firstIndex,1));
            gui.setDependencyID((String) tableModel.getValueAt(firstIndex,2));
           	 
         } 
	 public void addRow(String[] array) {  
		 tableModel.addRow(array);   
	 }
	
        public void deleteRow(String task_id,String dependency_id) {
                 tableModel.deleteRow(task_id,dependency_id);
        }
        public void applyFilter(String projectName){
        if(!tableModel.applyFilter(projectName)){
        }
    }
    
}
