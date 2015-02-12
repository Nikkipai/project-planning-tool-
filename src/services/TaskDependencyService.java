package services;

import entity.TaskDependency;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.TaskDependencyPK;

/**
 *
 * @author Upasana Tandon
 */
public class TaskDependencyService {
    
    private EntityManager manager;
    public TaskDependencyService(EntityManager manager) {
        this.manager = manager;
    }
    public TaskDependency createTaskDependency(String project_name, String task_id, String dependency_id) {
        TaskDependencyPK mpk=new TaskDependencyPK();
    	TaskDependency td = new TaskDependency(mpk);
        td.setProjectName(project_name);
    	td.setTaskId(task_id);
        td.setDependencyId(dependency_id);
 	manager.persist(td);
 	return td;
     }
     public TaskDependency readTaskDependency(String task_id, String dependency_id) {
         TaskDependencyPK mpk=new TaskDependencyPK(task_id,dependency_id);
    	 TaskDependency mp = manager.find(TaskDependency.class,mpk);
    	 return mp;   	 
     }
     public List<TaskDependency> readAll() {
    	 TypedQuery<TaskDependency> query = manager.createQuery("SELECT e FROM TaskDependency e", TaskDependency.class);
    	 List<TaskDependency> result =  query.getResultList();
    	 return result;   	 
     }
     public List<TaskDependency> readSelected(String projectName){
        TypedQuery<TaskDependency> query;
        query = manager.createQuery("SELECT e FROM TaskDependency e where e.projectName=:pname", TaskDependency.class);
        query.setParameter("pname", projectName);
        List<TaskDependency> result = query.getResultList();
        return result; 
    }
     public TaskDependency updateTaskDependency(String project_name, String task_id,String dependency_id) {
         TaskDependencyPK mpk=new TaskDependencyPK(task_id,dependency_id);
    	 TaskDependency mp = manager.find(TaskDependency.class,mpk);
    	 if (mp != null) {
            mp.setProjectName(project_name);
            mp.setTaskId(task_id);
            mp.setDependencyId(dependency_id);
    		
    	 }
    	 return mp;
     }
     public void deleteTaskDependency(String task_id,String dependency_id) {
         TaskDependencyPK mpk=new TaskDependencyPK(task_id,dependency_id);
    	 TaskDependency mp = manager.find(TaskDependency.class, mpk);
    	 if (mp != null) {
    		 manager.remove(mp);
    	 }
    }
    
}
