/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Daryl
 */


import javax.persistence.*;
import java.io.*;

@Entity(name = "communication")
public class Communication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name ="communication_id")
    private String communication_id;
    @Column(name = "project_name")
    private String project_name;
    @Column(name = "user_id")
    private String user_id;
     @Column(name = "subject")
    private String subject;
        @Column(name = "message")
    private String message;
    @Column(name = "date")
    private String date;
    
     
 
    public String getproject_name() {
        return project_name;
    }

    public void setproject_name(String project_name) {
        this.project_name = project_name;
    }

   
    public String getcommunication_id() {
        return communication_id;
    }

    public void setcommunication_id(String communication_id) {
        this.communication_id = communication_id;
    }

    public String getuser_id() {
        return user_id;
    }

    public void setuser_id(String user_id) {
        this.user_id = user_id;
       
    }
    
    
     public String getsubject() {
        return subject;
    }

    public void setsubject(String subject) {
        this.subject = subject;
       
    }
     public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
       
    }
     public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
       
    }
    
    
    
    
// return number of columns in the table

    public int getNumberOfColumns() {
        return 6;
    }
// return the data in column i

    public String getColumnData(int i) throws Exception {
        if (i == 0) {
            return getproject_name();
        } else if (i == 1) {
            return getcommunication_id();
        } else if (i == 2) {
            return getuser_id();
        }
        else if (i == 3) {
            return getsubject();
        }
        else if (i == 4) {
            return getmessage();
        }
         else if (i == 5) {
            return getdate();
        }
        
        
        
       else {
            throw new Exception("Error: invalid column index in courselist table");
        }
    }
// return the name of column i

    public String getColumnName(int i) throws Exception {
        String colName = null;
        if (i == 0) {
            colName = "project_name";
        } else if (i == 1) {
            colName = "communication_id";
        } else if (i == 2) {
            colName = "user_id";
        } 
        else if (i == 3) {
            colName = "subject";
        } 
        
        else if (i == 4) {
            colName = "message";
        } 
         else if (i == 5) {
            colName = "date";
        } 
        else {
            throw new Exception("Access to invalid column number in communication table");
        }
        return colName;
    }
// set data column i to value

    public void setColumnData(int i, Object value) throws Exception {
        if (i == 0) {
            project_name = (String) value;
        } else if (i == 1) {
           communication_id = (String) value;
        } else if (i == 2) {
         user_id =(String) value;
        
        }
        else if (i == 3) {
         subject =(String) value;
        }
        else if (i == 4) {
        message =(String) value;
        }
        
        else if(i==5)
        {
            date =(String)value;
        }
        
        
        
        
        else {
            throw new Exception("Error: invalid column index in communication table");
        }
    }

    @Override
    public String toString() {
        return "Communication [Project Name =" + project_name + ", "
                + " Communication ID =" + communication_id + ","
                + " User ID =" + user_id  + "," 
                +  " Subject =" + subject + "," 
                +  " Message =" + message  + "," 
                +  " Date =" + date  + "," 
                +
          "]";
    }
}
