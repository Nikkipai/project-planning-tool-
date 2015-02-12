/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Upasana Tandon
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Task;
import flpm.GlobalSession;

public class TaskService {
    
    private EntityManager manager;

    public TaskService(EntityManager manager) {
        this.manager = manager;
    }
    public Task createTask(String projectName, String taskId, String taskName, String userId, String taskDescription, String isDeliverable, String taskPriority, String taskStatus,  String startDate, String endDate,String actualStart,String actualEnd) 
    {
        Task p = new Task();
        p.setTaskId(taskId);
        p.setProjectName(projectName);
        p.setUserId(userId);
        p.setTaskName(taskName);
        p.setTaskDescription(taskDescription);
        p.setIsDeliverable(Boolean.parseBoolean(isDeliverable));
        p.setTaskPriority(taskPriority);
        p.setTaskStatus(taskStatus);
        DateFormat format = new SimpleDateFormat();
        Date date=null;
        try {
            date = format.parse(startDate);
        } catch (ParseException ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        p.setStartDate(date);
        try {
            date=format.parse(endDate);
        } catch (ParseException ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setEndDate(date);
        try {
            date=format.parse(actualStart);
        } catch (ParseException ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setActualStartDate(date);
        try {
            date=format.parse(actualEnd);
        } catch (ParseException ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setActualEndDate(date);
           
        
        manager.persist(p);
        return p;
    }

    public Task readTask(String taskID) {
        Task p = manager.find(Task.class,taskID);
        return p;
    }

    public List<Task> readAll() {
        TypedQuery<Task> query=null;
        String role=GlobalSession.user_role;
        String user_id=GlobalSession.user_id;
        if(role.equals("Admin")){
         query = manager.createQuery("SELECT e FROM Task e", Task.class);
        }else {
            
           query=manager.createQuery("SELECT e FROM Task e where e.projectName IN (Select m.memberProjectPK.projectName from MemberProject m" 
                    + " where m.memberProjectPK.userId=:user)",Task.class );
           query.setParameter("user",user_id);
        }
        List<Task> result = query.getResultList();
        return result;
    }
    public List<Task> readSelected(String projectName){
        TypedQuery<Task> query;
        query = manager.createQuery("SELECT e FROM Task e where e.projectName=:pname", Task.class);
        query.setParameter("pname", projectName);
        List<Task> result = query.getResultList();
        return result; 
    }
    public Task updateTask(String projectName, String taskId, String taskName, String userId, String taskDescription, String isDeliverable, String taskPriority, String taskStatus,  String startDate, String endDate,String actualStart,String actualEnd) 
    {
        Task p = manager.find(Task.class,taskId);
        if(p!=null){
            p.setTaskId(taskId);
            p.setProjectName(projectName);
            p.setUserId(userId);
            p.setTaskName(taskName);
            p.setTaskDescription(taskDescription);
            p.setIsDeliverable(Boolean.parseBoolean(isDeliverable));
            p.setTaskPriority(taskPriority);
            p.setTaskStatus(taskStatus);
            DateFormat format = new SimpleDateFormat();
            Date date=null;
            try {
                date = format.parse(startDate);
            } catch (ParseException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }

            p.setStartDate(date);
            try {
                date=format.parse(endDate);
            } catch (ParseException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }
            p.setEndDate(date);
            try {
            date=format.parse(actualStart);
        } catch (ParseException ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setActualStartDate(date);
        try {
            date=format.parse(actualEnd);
        } catch (ParseException ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setActualEndDate(date);
           
        }
        else{
            
        }
        manager.persist(p);
        return p;
    }
    public void deleteProject(String taskID)
    {
       Task p = manager.find(Task.class,taskID);
        if (p != null) 
        {
            manager.remove(p);
            

        }
    }
    
}
