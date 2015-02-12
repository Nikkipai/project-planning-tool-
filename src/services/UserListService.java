/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entity.Login;
import entity.Users;
import javax.persistence.*;
import java.util.*;
/**
 *
 * @author niki
 */

 public class UserListService {
    private EntityManager manager;
    public UserListService(EntityManager manager) {
        this.manager = manager;
    }
     public Users createUser(String id,String name, String role, String email, String phone) {
    	Users user = new Users();
    	user.setUserId(id);
 	user.setUserName(name);
 	user.setUserRole(role);
 	user.setUserEmail(email);
 	user.setUserPhone(phone);
        /* create a login record */
        Login loginRecord=new Login();
        loginRecord.setUserId(id);
        loginRecord.setPassword("password");
        user.setLoginRecord(loginRecord);
        
 	manager.persist(user);
 	return user;
     }
     public Users readUser(String id) {
    	 Users user = manager.find(Users.class, id);
    	 return user;   	 
     }
     // method to read all records
     public List<Users> readAll() {
    	 TypedQuery<Users> query = manager.createQuery("SELECT e FROM Users e", Users.class);
    	 List<Users> result =  query.getResultList();
    	 return result;   	 
     }
     public Users updateUser(String id,String name, String role,String email,String phone) {
    	 Users user = manager.find(Users.class,id);
    	 if (user != null) {
    		 user.setUserName(name);
    		 user.setUserRole(role);
    		 user.setUserEmail(email);
    		 user.setUserPhone(phone);
    		
    	 }
    	 return user;
     }
     public void deleteUser(String id) {
    	 Users user = manager.find(Users.class, id);
    	 if (user != null) {
    		 manager.remove(user);
    	 }
    }
	
}

