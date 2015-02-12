/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.MemberProject;
import entity.MemberProjectPK;
import flpm.GlobalSession;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author niki
 */
public class MemberProjectService {
    private EntityManager manager;
    public MemberProjectService(EntityManager manager) {
        this.manager = manager;
    }
    public MemberProject createMemberProject(String project_name,String user_id) {
        MemberProjectPK mpk=new MemberProjectPK();
    	MemberProject mp = new MemberProject(mpk);
    	mp.setProjectName(project_name);
        mp.setUserId(user_id);
 	manager.persist(mp);
 	return mp;
     }
     public MemberProject readMemberProject(String project_name,String user_id) {
         MemberProjectPK mpk=new MemberProjectPK(user_id,project_name);
    	 MemberProject mp = manager.find(MemberProject.class,mpk);
    	 return mp;   	 
     }
     public List<MemberProject> readAll() {
    	 TypedQuery<MemberProject> query=null;
         String role=GlobalSession.user_role;
         String user=GlobalSession.user_id;
         if(role.equals("Admin")){
            query=manager.createQuery("SELECT e FROM MemberProject e", MemberProject.class);
         }else{
            query=manager.createQuery("SELECT e FROM MemberProject e where e.memberProjectPK.projectName IN (Select m.memberProjectPK.projectName from MemberProject m" 
                    + " where m.memberProjectPK.userId=:user)", MemberProject.class);
            query.setParameter("user",user);

         }
         List<MemberProject> result =  query.getResultList();

    	 return result;   	 
     }
     public MemberProject updateMemberProject(String project_name,String user_id) {
         MemberProjectPK mpk=new MemberProjectPK(user_id,project_name);
    	 MemberProject mp = manager.find(MemberProject.class,mpk);
    	 if (mp != null) {
            mp.setProjectName(project_name);
            mp.setUserId(user_id);
    		
    	 }
    	 return mp;
     }
     public void deleteMemberProject(String project_name,String user_id) {
         MemberProjectPK mpk=new MemberProjectPK(user_id,project_name);
    	 MemberProject mp = manager.find(MemberProject.class, mpk);
    	 if (mp != null) {
    		 manager.remove(mp);
    	 }
    }
	
    
}
