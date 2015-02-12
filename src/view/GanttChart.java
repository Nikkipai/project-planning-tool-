/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import entity.Users;
import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


/**
 * A simple demonstration application showing how to create a Gantt chart.
 * <P>
 * This demo is intended to show the conceptual approach rather than being a polished
 * implementation.
 *
 *
 */
public class GanttChart extends JPanel {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public GanttChart(final String title) {
        final IntervalCategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
       
        //setContentPane(chartPanel);
    }

    public static IntervalCategoryDataset createDataset() {
        
        String PERSISTENCE_UNIT_NAME = "PersistenceUnit";      
        EntityManagerFactory factory;   
        EntityManager manager;	
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        TypedQuery<entity.Task> query = manager.createQuery("SELECT e FROM Task e where e.projectName=:project", entity.Task.class);
    	query.setParameter("project", project);
        List<entity.Task> result =  query.getResultList();
        final TaskSeries s1 = new TaskSeries("Scheduled");
        for(int i=0;i<result.size();i++){
            entity.Task t=result.get(i);
            s1.add(new Task(t.getTaskName(),new SimpleTimePeriod(t.getStartDate(),t.getEndDate())));
        }
        
        
        final TaskSeries s2 = new TaskSeries("Actual");
        for(int i=0;i<result.size();i++){
            entity.Task t=result.get(i);
            s2.add(new Task(t.getTaskName(),new SimpleTimePeriod(t.getActualStartDate(),t.getActualEndDate())));
        }
        
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        collection.add(s2);

        return collection;
    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return a date.
     */
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

    }
        
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    public JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
            "Gantt Chart Demo",  // chart title
            "Task",              // domain axis label
            "Date",              // range axis label
            dataset,             // data
            true,                // include legend
            true,                // tooltips
            false                // urls
        );    
//        chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        return chart;    
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

       final GanttChart demo = new GanttChart("Gantt Chart Demo 1");
       
      // demo.pack();
       //RefineryUtilities.centerFrameOnScreen(demo);
       demo.setVisible(true);

    }
    public static void setProject(String q){
        project=q;
    }
    private static String project;

}

           
       