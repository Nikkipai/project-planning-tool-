/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author niki
 */
@Entity
@Table(name = "member_project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MemberProject.findAll", query = "SELECT m FROM MemberProject m"),
    @NamedQuery(name = "MemberProject.findByUserId", query = "SELECT m FROM MemberProject m WHERE m.memberProjectPK.userId = :userId"),
    @NamedQuery(name = "MemberProject.findByProjectName", query = "SELECT m FROM MemberProject m WHERE m.memberProjectPK.projectName = :projectName")})
public class MemberProject implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MemberProjectPK memberProjectPK;
    
    

    public MemberProject(){
        
    }
    public MemberProject(MemberProjectPK memberProjectPK) {
        this.memberProjectPK = memberProjectPK;
    }

    public MemberProject(String userId, String projectName) {
        this.memberProjectPK = new MemberProjectPK(userId, projectName);
    }

    public MemberProjectPK getMemberProjectPK() {
        return memberProjectPK;
    }

    public void setMemberProjectPK(MemberProjectPK memberProjectPK) {
        this.memberProjectPK = memberProjectPK;
    }
    public String getUserID(){
        return memberProjectPK.getUserId();
    }
    public String getProjectName(){
        return memberProjectPK.getProjectName();
    }
    public void setUserId(String user_id){
        memberProjectPK.setUserId(user_id);
    }
    public void setProjectName(String project_name){
        memberProjectPK.setProjectName(project_name);
    }
    public int getNumberOfColumns() {
        return 2;
    }
     public String getColumnData(int i) throws Exception {
        if (i == 0) {
            return memberProjectPK.getProjectName();
        } else if (i == 1) {
            return memberProjectPK.getUserId();
        } 
       else {
            throw new Exception("Error: invalid column index in courselist table");
        }
    }
    public String getColumnName(int i) throws Exception {
        String colName = null;
        if (i == 0) {
            colName = "Project";
        } else if (i == 1) {
            colName = "Member";
        } 
        else {
            throw new Exception("Access to invalid column number in communication table");
        }
        return colName;
    }
    public void setColumnData(int i, Object value) throws Exception {
        if (i == 0) {
            memberProjectPK.setProjectName((String) value);
        } else if (i == 1) {
            memberProjectPK.setUserId((String)value);
        } 
        
        else {
            throw new Exception("Error: invalid column index in member project table");
        }
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberProjectPK != null ? memberProjectPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberProject)) {
            return false;
        }
        MemberProject other = (MemberProject) object;
        if ((this.memberProjectPK == null && other.memberProjectPK != null) || (this.memberProjectPK != null && !this.memberProjectPK.equals(other.memberProjectPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MemberProject[ memberProjectPK=" + memberProjectPK + " ]";
    }
    
}
