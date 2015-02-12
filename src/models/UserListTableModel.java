/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author niki
 */
import entity.Project;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.*;
import services.*;
import entity.Users;
import java.util.*;

public class UserListTableModel extends AbstractTableModel {
	
    List<Users> userListResultList;  
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";      
    private static EntityManagerFactory factory;   
    private EntityManager manager;				
    private Users users; 
    private UserListService userlistService;
    int numcols, numrows; 

    public UserListTableModel() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        users = new Users();
        userlistService = new UserListService(manager);
        userListResultList = userlistService.readAll();
        numrows = userListResultList.size();
        numcols = users.getNumberOfColumns();
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
            public Object getValueAt(int rowIndex, int columnIndex) {
		try{
			return userListResultList.get(rowIndex).getColumnData(columnIndex);
                }catch(Exception e){
			return null;
		}
            }
            public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
            }	
            public Class<?> getColumnClass(int col) {
                return getValueAt(0, col).getClass();
            }	
            public String getColumnName(int col) {
		try {
                        return users.getColumnName(col);
		} catch (Exception err) {
			return err.toString();
                }             
            }
            public void setValueAt(Object aValue, int row, int col) {
                    try {
                         Users element = userListResultList.get(row);
		         element.setColumnData(col, aValue); 
		         fireTableCellUpdated(row, col);
                    } catch(Exception err) {
                    }	
            }
            public List<Users> getList() {
		return userListResultList;
            }

            public EntityManager getEntityManager() {
		return manager;
            }

            public UserListTableModel(List<Users> list, EntityManager em)  {
                userListResultList = list;
		numrows = userListResultList.size();
		users = new Users();
		numcols = users.getNumberOfColumns();     
                manager = em;  
                userlistService = new UserListService(manager);
            }	
            public void addRow(Object[] array) {		
		EntityTransaction userTransaction = manager.getTransaction();   
                userTransaction.begin(); 
		Users newRecord = userlistService.createUser((String)array[0], (String)array[1], (String)array[2], (String) array[3], (String)array[4]);  
                userTransaction.commit();     // set the current row to rowIndex        
		userListResultList.add(newRecord);    	 
                int row = userListResultList.size();         
                int col = 0;        
		for (Object data : array) {            
                    setValueAt((String) data, row-1, col++);        
		}         
            }	
            public void removeRow(String id){
                
            }

    public void updateRow(String[] array) {
        EntityTransaction userTransaction = manager.getTransaction();   
        userTransaction.begin(); 
        Users newRecord = userlistService.updateUser((String)array[0], (String)array[1], (String)array[2], (String) array[3], (String)array[4]);  
        userTransaction.commit();      
    }

    public void deleteRow(String id) {
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Users newrecord= userlistService.readUser(id);
        userlistService.deleteUser(id);
        userTransaction.commit();
        userListResultList.remove(newrecord);
        numrows--;  
  
    }

    public Users readRow(String id) {
        
        return userlistService.readUser(id);
    }
}

