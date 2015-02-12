package models;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
import entity.TaskDependency;
import services.TaskDependencyService;
import entity.TaskDependencyPK;


/**
 *
 * @author Upasana Tandon
 */
public class TaskDependencyModel extends AbstractTableModel{
    
    List<TaskDependency> taskDependencyListResultList; // stores the model data in a List collection 
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
    private static EntityManagerFactory factory; // JPA
    private EntityManager manager; // JPA
    private TaskDependency taskDependencyList;
    private TaskDependencyService taskDependencyService;
    private int numcols, numrows; // number of rows and columns

    public TaskDependencyModel() {
        factory
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        TaskDependencyPK mpk=new TaskDependencyPK();
        taskDependencyList = new TaskDependency(mpk);
        taskDependencyService = new TaskDependencyService(manager);
        taskDependencyListResultList = taskDependencyService.readAll();
        numrows = taskDependencyListResultList.size();
        numcols = taskDependencyList.getNumberOfColumns();
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
            return taskDependencyListResultList.get(row).getColumnData(col);
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
            return taskDependencyList.getColumnName(col);
        } catch (Exception err) {
            return err.toString();
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        try {
            TaskDependency element = taskDependencyListResultList.get(row);
            element.setColumnData(col, aValue);
            fireTableCellUpdated(row, col);
        } catch (Exception err) 
        {
            err.toString();
        }
    }

    public List<TaskDependency> getList() {
        return taskDependencyListResultList;
    }

    public EntityManager getEntityManager() {
        return manager;
    }

    public TaskDependencyModel(List<TaskDependency> list, EntityManager em) {
        taskDependencyListResultList = list;
        numrows = taskDependencyListResultList.size();
        TaskDependencyPK mpk=new TaskDependencyPK();
        taskDependencyList = new TaskDependency(mpk);
        numcols = taskDependencyList.getNumberOfColumns();
        manager = em;
        taskDependencyService = new TaskDependencyService(manager);
    }

    public void addRow(Object[] array) {

        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        TaskDependency newRecord = taskDependencyService.createTaskDependency((String)array[0], (String) array[1], (String) array[2]);
        userTransaction.commit();
        taskDependencyListResultList.add(newRecord);
        int row = taskDependencyListResultList.size();
        int col = 0;
        for (Object data : array) {
            setValueAt((String) data, row - 1, col++);
        }
        numrows++;  
    }
    
    
    public void deleteRow(String task_id,String dependency_id)
    { 
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        TaskDependency newrecord= taskDependencyService.readTaskDependency(task_id,dependency_id);
        taskDependencyService.deleteTaskDependency(task_id,dependency_id);
        userTransaction.commit();
        taskDependencyListResultList.remove(newrecord);
        numrows--;  
  
    }
    
     public boolean applyFilter(String projectName) {
        taskDependencyListResultList = taskDependencyService.readSelected(projectName);
        if(taskDependencyListResultList.isEmpty()){
            return false;
        }
        return true;
        
    }
    
}
