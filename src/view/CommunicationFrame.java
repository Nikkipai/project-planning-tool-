/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Daryl
 */




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
*
* @author Daryl
*/
public class CommunicationFrame  extends JFrame{
   private static final long serialVersionUID = 1L;
   private JFrame f;

   public CommunicationFrame() {
   //Don't create a new JFrame, you're already creating a TestFrame!
       //call all methods on the TestFrame, not the JFrame
       setVisible(true);
       setSize(1000, 800);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       JPanel p1 = new CommunicationPanel();
       p1.setVisible(true);
       add(p1);
   }

   
   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
           public void run() {
                new CommunicationFrame();
           }
       });
   }
   
}