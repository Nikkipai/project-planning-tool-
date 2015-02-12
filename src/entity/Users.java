/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author niki
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByUserRole", query = "SELECT u FROM Users u WHERE u.userRole = :userRole"),
    @NamedQuery(name = "Users.findByUserEmail", query = "SELECT u FROM Users u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "Users.findUserRoleById",query="SELECT u.userRole from Users u where u.userId=:userId"),
    @NamedQuery(name = "Users.findByUserPhone", query = "SELECT u FROM Users u WHERE u.userPhone = :userPhone")})
public class Users implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_phone")
    private String userPhone;
    @OneToOne(cascade=CascadeType.ALL, mappedBy="userRecord")
    private Login loginRecord;
  


    public Users() {
    }

    public Users(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        String oldUserId = this.userId;
        this.userId = userId;
        changeSupport.firePropertyChange("userId", oldUserId, userId);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        String oldUserName = this.userName;
        this.userName = userName;
        changeSupport.firePropertyChange("userName", oldUserName, userName);
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        String oldUserRole = this.userRole;
        this.userRole = userRole;
        changeSupport.firePropertyChange("userRole", oldUserRole, userRole);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        String oldUserEmail = this.userEmail;
        this.userEmail = userEmail;
        changeSupport.firePropertyChange("userEmail", oldUserEmail, userEmail);
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        String oldUserPhone = this.userPhone;
        this.userPhone = userPhone;
        changeSupport.firePropertyChange("userPhone", oldUserPhone, userPhone);
    }
    public Login getLoginRecord(){
        return loginRecord;
    }
    public void setLoginRecord(Login l){
        loginRecord=l;
        
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Users[ userId=" + userId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    public int getNumberOfColumns() {    
		return 5;  
    } 
    public String getColumnData(int i) throws Exception {    
	if (i == 0)    
            return getUserId();    
	else if (i == 1)     
            return getUserName();   
	else if (i == 2)      
            return getUserRole();    
	else if (i == 3)     
            return getUserEmail();  
	else if (i == 4)     
            return getUserPhone();   
        else
            throw new Exception("Error: invalid column index in courselist table");      
    }   
    public String getColumnName(int i) throws Exception {
	String colName = null;   
	if (i == 0)     
            colName = "user_id";    
	else if (i == 1)    
            colName = "user_name";   
        else if (i == 2)     
            colName = "user_role";  
	else if (i == 3)     
            colName = "user_email";   
        else if (i == 4)     
            colName = "user_phone";   
	else 
            throw new Exception("Access to invalid column number in courselist table");       
	return colName; 
    } 
    public void setColumnData(int i, Object value) throws Exception {   
	if (i == 0)    
            userId = (String)value;   
        else if (i == 1)      
            userName= (String) value;    
	else if (i == 2)      
            userRole = (String) value;    
        else if (i == 3)    
            userEmail = (String) value;   
	else if (i == 4)   
            userPhone= (String) value;      
        else    
            throw new Exception("Error: invalid column index in courselist table");     
    }    
    
}
