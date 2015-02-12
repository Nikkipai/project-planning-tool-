/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.persistence.*;
import java.io.*;

@Entity(name = "budget")
public class Budget implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "item_id")
    private String item_id;
    @Column(name ="project_id")
    private String project_id;
    @Column(name = "item_name")
    private String item_name;
    @Column(name = "estimated_cost")
    private String estimated_cost;
       @Column(name = "actual_cost")
    private String actual_cost;
    public String getitem_id() {
        return item_id;
    }
    public void setitem_id(String item_id) {
        this.item_id = item_id;
    }
    public String getproject_id() {
        return project_id;
    }

    public void setproject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getitem_name() {
        return item_name;
    }

    public void setitem_name(String item_name) {
        this.item_name = item_name;
       
    }
     public String getestimated_cost() {
        return estimated_cost;
    }

    public void setestimated_cost(String estimated_cost) {
        this.estimated_cost = estimated_cost;
       
    }
    
     public String getactual_cost() {
        return actual_cost;
    }

    public void setactual_cost(String actual_cost) {
        this.actual_cost = actual_cost;
       
    }
    
    
// return number of columns in the table

    public int getNumberOfColumns() {
        return 5;
    }
// return the data in column i

    public String getColumnData(int i) throws Exception {
        if (i == 0) {
            return getitem_id();
        } else if (i == 1) {
            return getproject_id();
        } else if (i == 2) {
            return getitem_name();
        }
        else if (i == 3) {
            return getestimated_cost();
        }
        else if (i == 4) {
            return getactual_cost();
        }
        
       else {
            throw new Exception("Error: invalid column index in courselist table");
        }
    }
// return the name of column i

    public String getColumnName(int i) throws Exception {
        String colName = null;
        if (i == 0) {
            colName = "item_id";
        } else if (i == 1) {
            colName = "project_id";
        } else if (i == 2) {
            colName = "item_name";
        } 
        else if (i == 3) {
            colName = "estimated_cost";
        } 
        else if (i == 4) {
            colName = "actual_cost";
        } 
        else {
            throw new Exception("Access to invalid column number in communication table");
        }
        return colName;
    }
// set data column i to value

    public void setColumnData(int i, Object value) throws Exception {
        if (i == 0) {
            item_id = (String) value;
        } else if (i == 1) {
            project_id = (String) value;
        } else if (i == 2) {
         item_name =(String) value;
        
        }
        else if (i == 3) {
         estimated_cost =(String) value;
        }
        else if (i == 4) {
         actual_cost =(String) value;
        }        
        else {
            throw new Exception("Error: invalid column index in communication table");
        }
    }

    @Override
    public String toString() {
        return "Budget [Item ID =" + item_id + ", "
                + " Project ID =" + project_id + ","
                + " Item Name =" + item_name  + "," 
                +  " Estimated Cost =" + estimated_cost  + "," 
                +  " Actual Cost =" + actual_cost  + "," +
          "]";
    }
}
