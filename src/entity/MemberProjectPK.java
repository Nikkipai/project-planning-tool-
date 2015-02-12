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
 * @author niki
 */
@Embeddable
public class MemberProjectPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
    @Column(name = "project_name")
    private String projectName;

    public MemberProjectPK() {
    }

    public MemberProjectPK(String userId, String projectName) {
        this.userId = userId;
        this.projectName = projectName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        hash += (projectName != null ? projectName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MemberProjectPK)) {
            return false;
        }
        MemberProjectPK other = (MemberProjectPK) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        if ((this.projectName == null && other.projectName != null) || (this.projectName != null && !this.projectName.equals(other.projectName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MemberProjectPK[ userId=" + userId + ", projectName=" + projectName + " ]";
    }
    
}
