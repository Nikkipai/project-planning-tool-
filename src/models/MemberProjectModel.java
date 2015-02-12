/*
 * To change this license header, choose License Headers in MemberProject Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entity.MemberProject;
import entity.MemberProjectPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
import services.MemberProjectService;

/**
 *
 * @author niki
 */
public class MemberProjectModel extends AbstractTableModel{
    List<MemberProject> projectListResultList; // stores the model data in a List collection of type CourseList
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
    private static EntityManagerFactory factory; // JPA
    private EntityManager manager; // JPA
    private MemberProject projectList;// represents the entity courselist
    private MemberProjectService projectService;
    private int numcols, numrows; // number of rows and columns

    public MemberProjectModel() {
        factory
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        MemberProjectPK mpk=new MemberProjectPK();
        projectList = new MemberProject(mpk);
        projectService = new MemberProjectService(manager);
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
            MemberProject element = projectListResultList.get(row);
            element.setColumnData(col, aValue);
            fireTableCellUpdated(row, col);
        } catch (Exception err) 
        {
            err.toString();
        }
    }

    public List<MemberProject> getList() {
        return projectListResultList;
    }

    public EntityManager getEntityManager() {
        return manager;
    }

    public MemberProjectModel(List<MemberProject> list, EntityManager em) {
        projectListResultList = list;
        numrows = projectListResultList.size();
        MemberProjectPK mpk=new MemberProjectPK();
        projectList = new MemberProject(mpk);
        numcols = projectList.getNumberOfColumns();
        manager = em;
        projectService = new MemberProjectService(manager);
    }

    public void addRow(Object[] array) {

        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        MemberProject newRecord = projectService.createMemberProject((String)array[0], (String) array[1]);
        userTransaction.commit();
        projectListResultList.add(newRecord);
        int row = projectListResultList.size();
        int col = 0;
        for (Object data : array) {
            setValueAt((String) data, row - 1, col++);
        }
        numrows++;  
    }
    
    
    public void deleteRow(String project_name,String user_id)
    { 
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        MemberProject newrecord= projectService.readMemberProject(project_name,user_id);
        projectService.deleteMemberProject(project_name,user_id);
        userTransaction.commit();
        projectListResultList.remove(newrecord);
        numrows--;  
  
    }

   
    
    
}
