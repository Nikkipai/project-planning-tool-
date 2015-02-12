/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Upasana Tandon
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.*;
import java.util.*;
import entity.Task;
import services.TaskService;


public class TaskModel extends AbstractTableModel {
    List<Task> taskListResultList; // stores the model data in a List collection of type CourseList
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
    private static EntityManagerFactory factory; // JPA
    private EntityManager manager; // JPA
    private Task taskList;// represents the entity courselist
    private TaskService taskService;
    private int numcols, numrows; // number of rows and columns

    public TaskModel() {
        factory
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        taskList = new Task();
        taskService = new TaskService(manager);
        taskListResultList = taskService.readAll();
        numrows = taskListResultList.size();
        numcols = taskList.getNumberOfColumns();
    }
   

    @Override
    public int getRowCount() {
        return numrows;
    }

    @Override
    public int getColumnCount() {
        return numcols;
    }

    @Override
    public Object getValueAt(int row, int col) {
        try {
            return taskListResultList.get(row).getColumnData(col);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int colIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }
    @Override
    public String getColumnName(int col) {
        try {
            return taskList.getColumnName(col);
        } catch (Exception err) {
            return err.toString();
        }
    }
    @Override
    public void setValueAt(Object aValue, int row, int col) {
        try {
            Task element = taskListResultList.get(row);
            element.setColumnData(col, aValue);
            fireTableCellUpdated(row, col);
        } catch (Exception err) 
        {
            err.toString();
        }
    }

    public List<Task> getList() {
        return taskListResultList;
    }

    public EntityManager getEntityManager() {
        return manager;
    }

// create a new table model using the existing data in list
    public TaskModel(List<Task> list, EntityManager em) {
        taskListResultList = list;
        numrows = taskListResultList.size();
        taskList = new Task();
        numcols = taskList.getNumberOfColumns();
        manager = em;
        taskService = new TaskService(manager);
    }

    public void addRow(Object[] array) {
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Task newRecord = taskService.createTask((String)array[0], (String) array[1], (String) array[2], 
                (String) array[3],(String) array[4],(String) array[5], 
                (String) array[6],(String) array[7], (String)array[8], 
                (String) array[9],(String)array[10],(String)array[11]);
        userTransaction.commit();
        taskListResultList.add(newRecord);
        int row = taskListResultList.size();
        int col = 0;
        for (Object data : array) {
            setValueAt((String) data, row - 1, col++);
        }
        numrows++;   
    }
    public void deleteRow(String id)
    { 
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Task newrecord= taskService.readTask(id);
        taskService.deleteProject(id);
        userTransaction.commit();
        taskListResultList.remove(newrecord);
        numrows--;  
  
    }

    public void updateRow(String[] array) {
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Task newRecord = taskService.updateTask((String)array[0], (String) array[1], (String) array[2], (String) array[3],
                (String) array[4],(String) array[5], (String) array[6],
                (String) array[7], (String)array[8], (String) array[9],(String)array[10],(String)array[11]);
        userTransaction.commit();        
    }

    public boolean applyFilter(String projectName) {
        taskListResultList = taskService.readSelected(projectName);
        if(taskListResultList.isEmpty()){
            return false;
        }
        return true;
        
    }
    
    
}
