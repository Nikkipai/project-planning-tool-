/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Upasana Tandon
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByProjectName", query = "SELECT t FROM Task t WHERE t.projectName = :projectName"),
    @NamedQuery(name = "Task.findByTaskId", query = "SELECT t FROM Task t WHERE t.taskId = :taskId"),
    @NamedQuery(name = "Task.findByTaskName", query = "SELECT t FROM Task t WHERE t.taskName = :taskName"),
    @NamedQuery(name = "Task.findByUserId", query = "SELECT t FROM Task t WHERE t.userId = :userId"),
    @NamedQuery(name = "Task.findByTaskDescription", query = "SELECT t FROM Task t WHERE t.taskDescription = :taskDescription"),
    @NamedQuery(name = "Task.findByIsDeliverable", query = "SELECT t FROM Task t WHERE t.isDeliverable = :isDeliverable"),
    @NamedQuery(name = "Task.findByTaskPriority", query = "SELECT t FROM Task t WHERE t.taskPriority = :taskPriority"),
    @NamedQuery(name = "Task.findByTaskStatus", query = "SELECT t FROM Task t WHERE t.taskStatus = :taskStatus"),
    @NamedQuery(name = "Task.findByStartDate", query = "SELECT t FROM Task t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "Task.findByEndDate", query = "SELECT t FROM Task t WHERE t.endDate = :endDate")})
public class Task implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Column(name = "project_name")
    private String projectName;
    @Id
    @Basic(optional = false)
    @Column(name = "task_id")
    private String taskId;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "task_description")
    private String taskDescription;
    @Column(name = "is_deliverable")
    private Boolean isDeliverable;
    @Column(name = "task_priority")
    private String taskPriority;
    @Column(name = "task_status")
    private String taskStatus;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "actual_start_date")
    @Temporal(TemporalType.DATE)
    private Date actualStartDate;
    @Column(name = "actual_end_date")
    @Temporal(TemporalType.DATE)
    private Date actualEndDate;
    
    
    @ManyToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name="project_name")
    private Project project;



    public Task() {
    }

    public Task(String taskId) {
        this.taskId = taskId;
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
        return taskId;
    }

    public void setTaskId(String taskId) {
        String oldTaskId = this.taskId;
        this.taskId = taskId;
        changeSupport.firePropertyChange("taskId", oldTaskId, taskId);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        String oldTaskName = this.taskName;
        this.taskName = taskName;
        changeSupport.firePropertyChange("taskName", oldTaskName, taskName);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        String oldUserId = this.userId;
        this.userId = userId;
        changeSupport.firePropertyChange("userId", oldUserId, userId);
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        String oldTaskDescription = this.taskDescription;
        this.taskDescription = taskDescription;
        changeSupport.firePropertyChange("taskDescription", oldTaskDescription, taskDescription);
    }

    public Boolean getIsDeliverable() {
        return isDeliverable;
    }

    public void setIsDeliverable(Boolean isDeliverable) {
        Boolean oldIsDeliverable = this.isDeliverable;
        this.isDeliverable = isDeliverable;
        changeSupport.firePropertyChange("isDeliverable", oldIsDeliverable, isDeliverable);
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        String oldTaskPriority = this.taskPriority;
        this.taskPriority = taskPriority;
        changeSupport.firePropertyChange("taskPriority", oldTaskPriority, taskPriority);
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        String oldTaskStatus = this.taskStatus;
        this.taskStatus = taskStatus;
        changeSupport.firePropertyChange("taskStatus", oldTaskStatus, taskStatus);
    }

    public Date getStartDate() {
        return startDate;
    }
    public Date getActualStartDate(){
        return actualStartDate;
    }
    public void setActualStartDate(Date startDate){
        Date oldStartDate = this.actualStartDate;
        this.actualStartDate=startDate;
        changeSupport.firePropertyChange("actualStartDate", oldStartDate, startDate);
    }
    public void setActualEndDate(Date endDate){
        Date oldEndDate = this.actualEndDate;
        this.actualEndDate=endDate;
        changeSupport.firePropertyChange("actualEndDate", oldEndDate, endDate);
    }
    
    public Date getActualEndDate(){
        return actualEndDate;
    }
    
    public void setStartDate(Date startDate) {
        Date oldStartDate = this.startDate;
        this.startDate = startDate;
        changeSupport.firePropertyChange("startDate", oldStartDate, startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        Date oldEndDate = this.endDate;
        this.endDate = endDate;
        changeSupport.firePropertyChange("endDate", oldEndDate, endDate);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }
     // return number of columns in the table

    public int getNumberOfColumns() {
        return 12;
    }
    
     public String getColumnData(int i) throws Exception {
         
        SimpleDateFormat dateformat = new SimpleDateFormat();
        
        if (i == 0) {
            return getProjectName();
        } else if (i == 1) {
            return getTaskId();
        } else if (i == 2) {
            return getTaskName();
        }
        else if (i == 3) {
            return getUserId();
        }
        else if (i == 4) {
            return getTaskDescription();
        }
        else if (i == 5) {
            return Boolean.toString(getIsDeliverable());
        }
        else if (i == 6) {
            return getTaskPriority();
        }
        else if (i == 7) {
            return getTaskStatus();
        }
        else if (i == 8) {
            
            return dateformat.format(getStartDate());
        }
        else if (i == 9) {
            
            return dateformat.format(getEndDate());
        }
        else if(i==10){
            return dateformat.format(getActualStartDate());
        }
        else if(i==11){
            return dateformat.format(getActualEndDate());
        }
       else {
            throw new Exception("Error: invalid column index in Project table");
        }
    }
    
    // return the name of column i

    public String getColumnName(int i) throws Exception {
        String colName = null;
        if (i == 0) {
            colName = "project_name";
        } else if (i == 1) {
            colName = "task_id";
        } else if (i == 2) {
            colName = "task_name";
        } 
        else if (i == 3) {
            colName = "user_id";
        } 
        else if (i == 4) {
            colName = "task_description";
        } 
         else if (i == 5) {
            colName = "is_deliverable";
        } 
          else if (i == 6) {
            colName = "task_priority";
        } 
           else if (i == 7) {
            colName = "task_status";
        } 
            else if (i == 8) {
            colName = "start_date";
        } 
            else if (i == 9) {
            colName = "end_date";
        } 
            else if(i==10){
                colName="actual_start_date";
            }  
            else if(i==11){
                colName="actual_end_date";
            }
        
        else {
            throw new Exception("Access to invalid column number in Project table");
        }
        return colName;
    }
    
     // set data column i to value

    public void setColumnData(int i, Object value) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat();
        if (i == 0) {
            projectName = (String) value;
        } else if (i == 1) {
            taskId = (String) value;
        } else if (i == 2) {
            taskName = (String) value;
        
        }
        else if (i == 3) {
            userId = (String) value;
            
        }
        else if (i == 4) {
            taskDescription =(String) value;
        }
        else if (i == 5) {
            isDeliverable = Boolean.parseBoolean ((String) value);//Integer.parseInt((String) value);
        }
        else if (i == 6) {
            taskPriority =(String) value;
        }
        else if (i == 7) {
            taskStatus =(String) value;
        }
        else if (i == 8) {
            startDate = formatter.parse((String) value);
        }
        else if (i == 9) {
            endDate =formatter.parse((String) value);
        } 
        else if (i == 10) {
            actualStartDate = formatter.parse((String) value);
        }
        else if (i == 11) {
            actualEndDate =formatter.parse((String) value);
        }  
        
        else {
            throw new Exception("Error: invalid column index in project table");
        }
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.Task [taskId=" + taskId + ", "
                + " Project Name =" + projectName + ","
                + " Task Name =" + taskName  + ","
                + " User ID =" + userId  + "," 
                +  " Task Description =" + taskDescription + "," 
                +  " Is Deliverable =" + isDeliverable + "," 
                +  " Task Priority =" + taskPriority + ","
                +  "Task Status  =" + taskStatus  + ","
                +  " Start Date =" + startDate + "," 
                +  " End Date =" + endDate + ","
                 +  " Actual Start Date =" + actualStartDate + "," 
                +  " Actual End Date =" + actualEndDate + ","
                +" ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
