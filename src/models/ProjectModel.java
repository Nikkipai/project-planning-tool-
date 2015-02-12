/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author User
 */
import entity.Project;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.*;
import java.util.*;
import services.ProjectService;

public class ProjectModel extends AbstractTableModel {
    List<Project> projectListResultList; // stores the model data in a List collection of type CourseList
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
    private static EntityManagerFactory factory; // JPA
    private EntityManager manager; // JPA
    private Project projectList;// represents the entity courselist
    private ProjectService projectService;
    private int numcols, numrows; // number of rows and columns

    public ProjectModel() {
        factory
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        projectList = new Project();
        projectService = new ProjectService(manager);
        projectListResultList = projectService.readAll();
        numrows = projectListResultList.size();
        numcols = projectList.getNumberOfColumns();
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
            return projectListResultList.get(row).getColumnData(col);
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
            return projectList.getColumnName(col);
        } catch (Exception err) {
            return err.toString();
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        try {
            Project element = projectListResultList.get(row);
            element.setColumnData(col, aValue);
            fireTableCellUpdated(row, col);
        } catch (Exception err) 
        {
            err.toString();
        }
    }

    public List<Project> getList() {
        return projectListResultList;
    }

    public EntityManager getEntityManager() {
        return manager;
    }

    public ProjectModel(List<Project> list, EntityManager em) {
        projectListResultList = list;
        numrows = projectListResultList.size();
        projectList = new Project();
        numcols = projectList.getNumberOfColumns();
        manager = em;
        projectService = new ProjectService(manager);
    }

    public void addRow(Object[] array) {

        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Project newRecord = projectService.createProject((String)array[0], (String) array[1], (String) array[2], (String) array[3], (String) array[4],(String) array[5],(String) array[6],(String) array[7], (String) array[8]);
        userTransaction.commit();
        projectListResultList.add(newRecord);
        int row = projectListResultList.size();
        int col = 0;
        for (Object data : array) {
            setValueAt((String) data, row - 1, col++);
        }
        numrows++;  
    }
    
    
    public void deleteRow(String name)
    { 
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Project newrecord= projectService.readProject(name);
        projectService.deleteProject(name);
        userTransaction.commit();
        projectListResultList.remove(newrecord);
        numrows--;  
  
    }

    public void updateRow(String[] array) {
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Project p=projectService.updateProject(array[0], array[1], array[2], array[3], array[4],array[5],array[6], array[7],array[8]);
        userTransaction.commit();

        
    }
    
}
