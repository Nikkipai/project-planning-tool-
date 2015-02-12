package entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByProjectName", query = "SELECT p FROM Project p WHERE p.projectName = :projectName"),
    @NamedQuery(name = "Project.findByInchargeId", query = "SELECT p FROM Project p WHERE p.inchargeId = :inchargeId"),
    @NamedQuery(name = "Project.findByClientId", query = "SELECT p FROM Project p WHERE p.clientId = :clientId"),
    @NamedQuery(name = "Project.findByProjectStatus", query = "SELECT p FROM Project p WHERE p.projectStatus = :projectStatus"),
    @NamedQuery(name = "Project.findByProjectDescription", query = "SELECT p FROM Project p WHERE p.projectDescription = :projectDescription"),
    @NamedQuery(name = "Project.findByProjectOutcome", query = "SELECT p FROM Project p WHERE p.projectOutcome = :projectOutcome"),
    @NamedQuery(name = "Project.findByProjectTag", query = "SELECT p FROM Project p WHERE p.projectTag = :projectTag"),
    @NamedQuery(name = "Project.findByStartDate", query = "SELECT p FROM Project p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Project.findByEndDate", query = "SELECT p FROM Project p WHERE p.endDate = :endDate")})
public class Project implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "incharge_id")
    private String inchargeId;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "project_status")
    private String projectStatus;
    @Column(name = "project_description")
    private String projectDescription;
    @Column(name = "project_outcome")
    private String projectOutcome;
    @Column(name = "project_tag")
    private String projectTag;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="project")
    private Set<Task> tasks;


    

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        String oldProjectName = this.projectName;
        this.projectName = projectName;
        changeSupport.firePropertyChange("projectName", oldProjectName, projectName);
    }

    public String getInchargeId() {
        return inchargeId;
    }

    public void setInchargeId(String inchargeId) {
        String oldInchargeId = this.inchargeId;
        this.inchargeId = inchargeId;
        changeSupport.firePropertyChange("inchargeId", oldInchargeId, inchargeId);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        String oldClientId = this.clientId;
        this.clientId = clientId;
        changeSupport.firePropertyChange("clientId", oldClientId, clientId);
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        String oldProjectStatus = this.projectStatus;
        this.projectStatus = projectStatus;
        changeSupport.firePropertyChange("projectStatus", oldProjectStatus, projectStatus);
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        String oldProjectDescription = this.projectDescription;
        this.projectDescription = projectDescription;
        changeSupport.firePropertyChange("projectDescription", oldProjectDescription, projectDescription);
    }

    public String getProjectOutcome() {
        return projectOutcome;
    }

    public void setProjectOutcome(String projectOutcome) {
        String oldProjectOutcome = this.projectOutcome;
        this.projectOutcome = projectOutcome;
        changeSupport.firePropertyChange("projectOutcome", oldProjectOutcome, projectOutcome);
    }

    public String getProjectTag() {
        return projectTag;
    }

    public void setProjectTag(String projectTag) {
        String oldProjectTag = this.projectTag;
        this.projectTag = projectTag;
        changeSupport.firePropertyChange("projectTag", oldProjectTag, projectTag);
    }

    public Date getStartDate() {
        return startDate;
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
    public void setTasks(Set<Task> tasks){
        this.tasks=tasks;
    }
    public Set<Task> getTasks(){
        return tasks;
    }
      // return number of columns in the table

    public int getNumberOfColumns() {
        return 9;
    }
// return the data in column i

    public String getColumnData(int i) throws Exception {
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        
        if (i == 0) {
            return getProjectName();
        } else if (i == 1) {
            return getInchargeId();
        } else if (i == 2) {
            return getClientId();
        }
     
        else if (i == 3) {
            return getProjectStatus();
        }
        else if (i == 4) {
            return getProjectDescription();
        }
        else if (i == 5) {
            return getProjectOutcome();
        }
        else if (i == 6) {
            return getProjectTag();
        }
        else if (i == 7) {
       return dateformat.format(getStartDate());
        }
        else if (i == 8) {
            
            return dateformat.format(getEndDate());
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
        }  else if (i == 1) {
            colName = "incharge_id";
        } 
        else if (i == 2) {
            colName = "client_id";
        } 
        
        else if (i == 3) {
            colName = "project_status";
        } 
         else if (i == 4) {
            colName = "project_description";
        } 
          else if (i == 5) {
            colName = "project_outcome";
        } 
           else if (i == 6) {
            colName = "project_tag";
        } 
            else if (i == 7) {
            colName = "start_date";
        } 
            else if (i == 8) {
            colName = "end_date";
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
         inchargeId = (String) value;
        
        }
        else if (i == 2) {
         clientId = (String) value;
        }
        else if (i == 3) {
         projectStatus =(String) value;
        }
        else if (i == 4) {
         projectDescription =(String) value;
        }
        else if (i == 5) {
         projectOutcome =(String) value;
        }
        else if (i == 6) {
         projectTag =(String) value;
        }
        else if (i == 7) {
         startDate = formatter.parse((String) value);
        }
        else if (i == 8) {
             endDate =formatter.parse((String) value);
        } 
              
        else {
            throw new Exception("Error: invalid column index in project table");
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectName != null ? projectName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projectName == null && other.projectName != null) || (this.projectName != null && !this.projectName.equals(other.projectName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.Project[ projectName=" + projectName +","
                + " Incharge ID =" + inchargeId  + "," 
                + " Incharge ID =" + inchargeId  + ","
                + " Project Status =" + projectStatus  + ","
                +  " Project Description =" + projectDescription + "," 
                +  " Project Outcome =" + projectOutcome + "," 
                +  " Project Tag =" + projectTag + ","
                +  " Start Date =" + startDate + "," 
                +  " End Date =" + endDate + ","
                +" ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
