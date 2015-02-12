/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Daryl
 */
import entity.Budget;
import flpm.GlobalSession;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author Daryl
 */
public class BudgetService {

    private EntityManager manager;

    public BudgetService(EntityManager manager) {
        this.manager = manager;
    }
// method to create a new record

    public Budget createBudget(String item_id, String project_id, String item_name,String estimated_cost,String actual_cost)
    {
        Budget b = new Budget();
        b.setitem_id(item_id);
        b.setproject_id(project_id);
        b.setitem_name(item_name);
        b.setestimated_cost(estimated_cost);
        b.setactual_cost(actual_cost);
        manager.persist(b);
        return b;
    }
// method to read a record

    public Budget readBudget(String item_id) {
    Budget b = manager.find(Budget.class,item_id);
        return b;
    }
// method to read all records

    public List<Budget> readAll() {
        TypedQuery<Budget> query=null;
        String role=GlobalSession.user_role;
        String user_id=GlobalSession.user_id;
        if(role.equals("Admin")){
             query=manager.createQuery("SELECT e FROM budget e", Budget.class);
        }else {
               query=manager.createQuery("SELECT e FROM budget e where e.project_id IN (Select m.memberProjectPK.projectName from MemberProject m" 
                    + " where m.memberProjectPK.userId=:user)",Budget.class );
               query.setParameter("user",user_id);
        }
        List<Budget> result = query.getResultList();
        return result;
    }
    public List<Budget> readSelected(String projectName){
        String role=GlobalSession.user_role;
        String user_id=GlobalSession.user_id;
        TypedQuery<Budget> query=null;
        if(true){
           query = manager.createQuery("SELECT e FROM budget e where e.project_id=:pname", Budget.class);
        }else{
            query=manager.createQuery("SELECT e FROM budget e where e.project_id IN (Select m.memberProjectPK.projectName from MemberProject m" 
                    + " where m.memberProjectPK.userId=:user)",Budget.class );
            query.setParameter("userId",user_id);
         }
        query.setParameter("pname", projectName);
        List<Budget> result = query.getResultList();
        return result; 
    }

    public Budget updateBudget(String item_id, String project_id, String item_name,String estimated_cost,String actual_cost) {
        Budget b = manager.find(Budget.class,
                item_id);
        if (b != null) {
           b.setitem_id(item_id);
           b.setproject_id(project_id);
           b.setitem_name(item_name);
           b.setestimated_cost(estimated_cost);
           b.setactual_cost(actual_cost);
           manager.persist(b);
        }
        return b;
    }
    public void deleteBudget(String item_id)
    {
       Budget b = manager.find(Budget.class,item_id);
        if (b != null) 
        {
            manager.remove(b);
            
        }
    }
}
