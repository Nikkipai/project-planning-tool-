/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author User
 */
import entity.MemberProject;
import entity.MemberProjectPK;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Project;
import flpm.GlobalSession;

public class ProjectService {
    
    private EntityManager manager;
    public ProjectService(EntityManager manager) {
        this.manager = manager;
    }
    public Project createProject(String projectName,String inchargeId,String clientId,String projectStatus, String projectDescription, String projectOutcome, String projectTag, String startDate, String endDate)
    {
        Project p = new Project();
        p.setProjectName(projectName);
        p.setInchargeId(inchargeId);
        p.setClientId(clientId);
        p.setProjectStatus(projectStatus);
        p.setProjectDescription(projectDescription);
        p.setProjectOutcome(projectOutcome);
        p.setProjectTag(projectTag);
        DateFormat format = new SimpleDateFormat();
        Date date=null;
        try {
            date = format.parse(startDate);
        } catch (ParseException ex) {
            Logger.getLogger(ProjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setStartDate(date);
        try {
            date=format.parse(endDate);
        } catch (ParseException ex) {
            Logger.getLogger(ProjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setEndDate(date);
        manager.persist(p);
        TypedQuery<MemberProject> query=manager.createQuery("Select e from MemberProject e where e.memberProjectPK.userId=:user and e.memberProjectPK.projectName=:project",MemberProject.class);
        query.setParameter("user", clientId);
        query.setParameter("project", projectName);
        List<MemberProject> result =  query.getResultList();
        if(result.isEmpty()){
            MemberProject mp2=new MemberProject();
            MemberProjectPK  mpk=new MemberProjectPK(clientId,projectName);
            mp2.setMemberProjectPK(mpk);
            manager.persist(mp2);
        }
        query=manager.createQuery("Select e from MemberProject e where e.memberProjectPK.userId=:user and e.memberProjectPK.projectName=:project",MemberProject.class);
        query.setParameter("user", inchargeId);
        query.setParameter("project", projectName);
        result =  query.getResultList();
        if(result.isEmpty()){
            MemberProject mp2=new MemberProject();
            MemberProjectPK  mpk=new MemberProjectPK(inchargeId,projectName);
            mp2.setMemberProjectPK(mpk);
            manager.persist(mp2);
        }
        
        return p;
    }

    public Project readProject(String projectName) {
    Project p = manager.find(Project.class,projectName);
        return p;
    }

    public List<Project> readAll() {
        String role=GlobalSession.user_role;
        String user_id=GlobalSession.user_id;
        TypedQuery<Project> query=null;
        TypedQuery<MemberProject> memberQuery=null;
        if(role.equals("Admin")){
            query = manager.createQuery("SELECT e FROM Project e", Project.class);
        }else{
            System.out.println("User " + GlobalSession.user_id);
            memberQuery=manager.createQuery("Select e FROM MemberProject e where e.memberProjectPK.userId=:user",MemberProject.class);
            memberQuery.setParameter("user",GlobalSession.user_id);
            List<MemberProject> result = memberQuery.getResultList();  
            List<String> userProjectList=new ArrayList<String>();
            for(MemberProject m : result){
                userProjectList.add(m.getProjectName());
            }
            System.out.println(result);
            query = manager.createQuery("SELECT e FROM Project e where e.projectName IN :userProjectList", Project.class);
            query.setParameter("userProjectList",userProjectList );
        }
        List<Project> result = query.getResultList();
        System.out.println(result);
        return result;
    }

    public Project updateProject(String projectName, String inchargeId, String clientId, String projectStatus, String projectDescription, String projectOutcome, String projectTag, String startDate, String endDate) {
        Project p = manager.find(Project.class,projectName);
        if (p != null) {
            p.setProjectName(projectName);
            p.setInchargeId(inchargeId);
            p.setClientId(clientId);
            p.setProjectStatus(projectStatus);
            p.setProjectDescription(projectDescription);
            p.setProjectOutcome(projectOutcome);
            p.setProjectTag(projectTag);
            
            DateFormat format = new SimpleDateFormat();
            Date date=null;
        
            try {
                date = format.parse(startDate);
            } catch (ParseException ex) {
             }
            p.setStartDate(date);
            try {
             date=format.parse(endDate);
            } catch (ParseException ex) {
             }
            p.setEndDate(date);
        }
        return p;
    }
    public void deleteProject(String projectName)
    {
       Project p = manager.find(Project.class,projectName);
        if (p != null) {
            manager.remove(p); 
        }
    }

   
    
}
