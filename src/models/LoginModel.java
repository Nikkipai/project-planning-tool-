/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.*;
import javax.persistence.*;
import services.*;
import entity.Login;
import entity.Users;
import flpm.GlobalSession;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author niki
 */
public class LoginModel {
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";      
    private static EntityManagerFactory factory;    
    private EntityManager manager;				
    private Login login; 
    private LoginService loginService;

    public LoginModel() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        login = new Login();
        loginService = new LoginService(manager);
    }
    public void addRow(Object[] array) {		
	EntityTransaction loginTransaction = manager.getTransaction();   
        loginTransaction.begin(); 
	Login newRecord = loginService.createUser((String)array[0], (String)array[1]);  
        loginTransaction.commit();                             
    }	
    public void removeRow(int id){
        EntityTransaction loginTransaction = manager.getTransaction();   
        loginTransaction.begin(); 
        loginService.deleteUser(id);
        loginTransaction.commit();
                	 
    }
    public boolean isPasswordCorrect(String id,char[] pwd){
        Query q=manager.createNamedQuery("Login.findByUserId");
        q.setParameter("userId", id);
        List<Login> logins = (List<Login>) q.getResultList();
        if(logins.isEmpty()){
            return false;
        }
        Users u=logins.get(0).getUserRecord();
        char[] correctPwd=logins.get(0).getPassword().toCharArray();
        System.out.println(correctPwd);
        System.out.println(pwd);
        if(Arrays.equals(correctPwd,pwd)){
            GlobalSession.user_role=u.getUserRole();
            GlobalSession.user_id=u.getUserId();
            return true;
        }
        return false;
    }
    
}
