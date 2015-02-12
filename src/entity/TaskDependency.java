
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeSupport;
import javax.persistence.Transient;

/**
 *
 * @author Upasana Tandon
 */
@Entity
@Table(name = "task_dependency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaskDependency.findAll", query = "SELECT t FROM TaskDependency t"),
    @NamedQuery(name = "TaskDependency.findByProjectName", query = "SELECT t FROM TaskDependency t WHERE t.projectName = :projectName"),
    @NamedQuery(name = "TaskDependency.findByTaskId", query = "SELECT t FROM TaskDependency t WHERE t.taskDependencyPK.taskId = :taskId"),
    @NamedQuery(name = "TaskDependency.findByDependencyId", query = "SELECT t FROM TaskDependency t WHERE t.taskDependencyPK.dependencyId = :dependencyId")})
public class TaskDependency implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    @Column(name = "project_name")
    private String projectName;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TaskDependencyPK taskDependencyPK;

    public TaskDependency() {
    }

    public TaskDependency(TaskDependencyPK taskDependencyPK) {
        this.taskDependencyPK = taskDependencyPK;
    }

    public TaskDependency(String taskId, String dependencyId) {
        this.taskDependencyPK = new TaskDependencyPK(taskId, dependencyId);
    }

    public TaskDependencyPK getTaskDependencyPK() {
        return taskDependencyPK;
    }

    public void setTaskDependencyPK(TaskDependencyPK taskDependencyPK) {
        this.taskDependencyPK = taskDependencyPK;
    }
    
    public String getProjectName() {
      return projectName; 
    }
    
    public void setProjectName(String projectName) {
       String oldProjectName = this.projectName;
        this.projectName = projectName;
        changeSupport.firePropertyChange("projectName", oldProjectName, projectName);
    }
    
    public String getTaskId() {
        return taskDependencyPK.getTaskId();
    }
    public String getDependencyId() {
        return taskDependencyPK.getDependencyId();
    }
    public void setTaskId(String taskId) {
        taskDependencyPK.setTaskId(taskId);
    }
    public void setDependencyId(String dependencyId){
        taskDependencyPK.setDependencyId(dependencyId);
    }
    
    public int getNumberOfColumns() {
        return 3;
    }
     public String getColumnData(int i) throws Exception {
        if (i == 0) {
            return getProjectName();
        } else if (i == 1) {
            return taskDependencyPK.getTaskId();
        } else if (i == 2) {
            return taskDependencyPK.getDependencyId();
        } 
       else {
            throw new Exception("Error: invalid column index in task_dependency table");
        }
    }
    public String getColumnName(int i) throws Exception {
        String colName = null;
        if (i == 0) {
            colName = "project_name";
        } else if (i == 1) {
            colName = "task_id";
        } else if (i == 2) {
            colName = "dependency_id";
        } 
        else {
            throw new Exception("Access to invalid column number in task_dependency table");
        }
        return colName;
    }
    public void setColumnData(int i, Object value) throws Exception {
        if (i == 0) {
            setProjectName((String) value);
        } else if (i == 1) {
            taskDependencyPK.setTaskId((String)value);
        } else if (i == 2) {
            taskDependencyPK.setDependencyId((String)value);
        } 
        
        else {
            throw new Exception("Error: invalid column index in task_dependency table");
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskDependencyPK != null ? taskDependencyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskDependency)) {
            return false;
        }
        TaskDependency other = (TaskDependency) object;
        if ((this.taskDependencyPK == null && other.taskDependencyPK != null) || (this.taskDependencyPK != null && !this.taskDependencyPK.equals(other.taskDependencyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taskDependency.TaskDependency[ taskDependencyPK=" + taskDependencyPK + " ]";
    }

    
    
}
