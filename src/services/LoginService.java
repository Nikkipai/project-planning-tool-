/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entity.Login;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author niki
 */
public class LoginService {
    private EntityManager manager;
    
    public LoginService(EntityManager manager) {
        this.manager = manager;
    }
     public Login createUser(String id,String pwd) {
    	Login loginRecord = new Login();
    	loginRecord.setUserId(id);
 	loginRecord.setPassword(pwd);
        
 	manager.persist(loginRecord);
 	return loginRecord;
     }
     public Login readUser(String id) {
    	 Login user = manager.find(Login.class, id);
    	 return user;   	 
     }
     // method to read all records
     public List<Login> readAll() {
    	 TypedQuery<Login> query = manager.createQuery("SELECT e FROM Login e", Login.class);
    	 List<Login> result =  query.getResultList();
    	 return result;   	 
     }
     public Login updateUser(String id,String pwd) {
    	 Login user = manager.find(Login.class,id);
    	 if (user != null) {
    		 user.setPassword(pwd);
    	 }
    	 return user;
     }
     public void deleteUser(int id) {
    	 Login user = manager.find(Login.class, id);
    	 if (user != null) {
    		 manager.remove(user);
    	 }
    }
    
}
