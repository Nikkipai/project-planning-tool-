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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Communication;
import flpm.GlobalSession;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author Daryl
 */
public class CommunicationService {

    private EntityManager manager;

    public CommunicationService(EntityManager manager) {
        this.manager = manager;
    }
// method to create a new record

    public Communication createCommunication(String project_name, String communication_id, String user_id,String subject,String message,String date)
    {
        Communication com = new Communication();
        com.setproject_name(project_name);
        com.setcommunication_id(communication_id);
        com.setuser_id(user_id);
        com.setsubject(subject);
        com.setmessage(message);
        com.setdate(date);
        manager.persist(com);
        return com;
    }

    public Communication readCommunication(String communication_id) {
        Communication com = manager.find(Communication.class,communication_id);
        return com;
    }

    public List<Communication> readAll() {
        TypedQuery<Communication> query=null;
        String role=GlobalSession.user_role;
        String user_id=GlobalSession.user_id;
        if(role.equals("Admin")){
            query= manager.createQuery("SELECT e FROM communication e", Communication.class);
        }else{
            query= manager.createQuery("SELECT e FROM communication e where e.project_name IN (Select m.memberProjectPK.projectName from MemberProject m" 
                    + " where m.memberProjectPK.userId=:user)", Communication.class);
            query.setParameter("user",user_id);

        }
        List<Communication> result = query.getResultList();
        return result;
    }

    public void deleteCommunication(String communication_id)
    {
        Communication com = manager.find(Communication.class,communication_id);
        if (com != null) 
        {
          //  CourseList course = new CourseList();
            manager.remove(com);
            

        }
    }
}
