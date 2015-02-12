/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Daryl
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
import entity.Budget;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.*;
import javax.persistence.*;
import java.util.*;
import services.BudgetService;

/**
 *
 * @author rgrover
 */
public class BudgetModel extends AbstractTableModel {

    List<Budget> budgetListResultList; // stores the model data in a List collection of type CourseList
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
    private static EntityManagerFactory factory; // JPA
    private EntityManager manager; // JPA
    private Budget budgetlist;
    private BudgetService budgetService;
    private int numcols, numrows; 

    public BudgetModel() {
        factory= Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        budgetlist = new Budget();
        budgetService = new BudgetService(manager);
        budgetListResultList = budgetService.readAll();
        numrows = budgetListResultList.size();
        numcols = budgetlist.getNumberOfColumns();
   }

    public int getRowCount() {
        return numrows;
    }

    public int getColumnCount() {
        return numcols;
    }
// returns the data at the given row and column number

    public Object getValueAt(int row, int col) {
        try {
            return budgetListResultList.get(row).getColumnData(col);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
// table cells are not editable

    public boolean isCellEditable(int rowIndex, int colIndex) {
        return false;
    }

    public Class<?> getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }
// returns the name of the column

    public String getColumnName(int col) {
        try {
            return budgetlist.getColumnName(col);
        } catch (Exception err) {
            return err.toString();
        }
    }
// update the data in the given row and column to aValue

    public void setValueAt(Object aValue, int row, int col) {
//data[rowIndex][columnIndex] = (String) aValue;
        try {
            Budget element = budgetListResultList.get(row);
            element.setColumnData(col, aValue);
            fireTableCellUpdated(row, col);
        } catch (Exception err) 
        {
            err.toString();
        }
    }

    public List<Budget> getList() {
        return budgetListResultList;
    }

    public EntityManager getEntityManager() {
        return manager;
    }

// create a new table model using the existing data in list
    public BudgetModel(List<Budget> list, EntityManager em) {
        budgetListResultList = list;
        numrows = budgetListResultList.size();
        budgetlist = new Budget();
        numcols = budgetlist.getNumberOfColumns();
        manager = em;
        budgetService = new BudgetService(manager);
    }

    public void addRow(Object[] array) {

        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Budget newRecord = budgetService.createBudget((String) array[0], (String) array[1],
                        (String) array[2],(String) array[3],(String) array[4]);                
        userTransaction.commit();
        budgetListResultList.add(newRecord);
        int row = budgetListResultList.size();
        int col = 0;
        for (Object data : array) {
            setValueAt((String) data, row - 1, col++);
        }
        numrows++;    
    } 
    public void deleteRow(Object[] array)
    {
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Budget newrecord= budgetService.readBudget((String) array[0]);
        budgetService.deleteBudget((String) array[0]);
        userTransaction.commit();
        budgetListResultList.remove(newrecord);
        numrows--;
    }
     public void updateRow(Object[] array) {

        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Budget newRecord = budgetService.updateBudget((String) array[0], (String) array[1],
                        (String) array[2],(String) array[3],(String) array[4]);                
        userTransaction.commit();
    
    } 
    public boolean applyFilter(String projectName) {
        budgetListResultList = budgetService.readSelected(projectName);
        if(budgetListResultList.isEmpty()){
            return false;
        }
        return true;
        
    }
   
}
