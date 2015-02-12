/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author User
 */
@Embeddable
public class TaskDependencyPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "task_id")
    private String taskId;
    @Basic(optional = false)
    @Column(name = "dependency_id")
    private String dependencyId;

    public TaskDependencyPK() {
    }

    public TaskDependencyPK(String taskId, String dependencyId) {
        
        this.taskId = taskId;
        this.dependencyId = dependencyId;
    }
    
   

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(String dependencyId) {
        this.dependencyId = dependencyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
       
        hash += (taskId != null ? taskId.hashCode() : 0);
        hash += (dependencyId != null ? dependencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskDependencyPK)) {
            return false;
        }
        TaskDependencyPK other = (TaskDependencyPK) object;
        
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        if ((this.dependencyId == null && other.dependencyId != null) || (this.dependencyId != null && !this.dependencyId.equals(other.dependencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taskDependency.TaskDependencyPK[taskId=" + taskId + ", dependencyId=" + dependencyId + " ]";
    }
    
    }
