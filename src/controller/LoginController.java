/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import javax.swing.*;
import javax.swing.event.*;
import models.LoginModel;
import entity.Login;
import flpm.GlobalSession;
import view.AdminPanel;
import view.ClientPanel;
import view.LoginPanel;
import view.ManagerPanel;
import view.StudentPanel;
import view.UsersPanel;

/**
 *
 * @author niki
 */
public class LoginController {
    private LoginModel loginModel;
    private LoginPanel gui; 
    public LoginController(LoginPanel gui) {  
        this.gui = gui;            
        loginModel = new LoginModel();
    }
    public LoginController() {  
        loginModel = new LoginModel();
    }
    public void addRow(String[] array) {  
        loginModel.addRow(array);    
    }
    public void removeRow(int id) {  
        loginModel.removeRow(id);   
    }
    public void authUser(String id,char[] pwd){
       if(loginModel.isPasswordCorrect(id, pwd)){
           String user_role=GlobalSession.user_role;
           if(user_role.equals("Admin")){
                GlobalSession.window.getHeaderButtons().setVisible(true);
                GlobalSession.centralPanel.removeAll();
                GlobalSession.centralPanel.add(new AdminPanel());
           }else if(user_role.equals("Manager") || user_role.equals("Faculty Advisor")){
                GlobalSession.window.getHeaderButtons().setVisible(true);
                GlobalSession.centralPanel.removeAll();
                GlobalSession.centralPanel.add(new ManagerPanel());
               
           }
           else if(user_role.equals("Student") ){
                GlobalSession.window.getHeaderButtons().setVisible(true);
                GlobalSession.centralPanel.removeAll();
                GlobalSession.centralPanel.add(new StudentPanel());
               
           }
            else if(user_role.equals("Client") ){
                GlobalSession.window.getHeaderButtons().setVisible(true);
                GlobalSession.centralPanel.removeAll();
                GlobalSession.centralPanel.add(new ClientPanel());
               
           }
           
           
       }else{
          gui.setErrorMessage();
       }
    }
}
