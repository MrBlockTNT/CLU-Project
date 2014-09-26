package system.clu.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Initializing extends JFrame 
{
	  private Thread t;
	  private static JProgressBar bar;
	   
	  public Initializing(){      
	    this.setSize(300, 60);
	    this.setTitle("Please wait...");
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    this.setLocationRelativeTo(null);      
	      
	    t = new Thread(new Traitement());
	    bar  = new JProgressBar();
	    bar.setMaximum(100);
	    bar.setMinimum(0);
	    bar.setStringPainted(true);
	      
	    this.getContentPane().add(bar, BorderLayout.CENTER);

	    t = new Thread(new Traitement());
	    t.start();  
	    this.setVisible(true);      
	  }

	  public static void setValue(int value)
	  {
		  bar.setValue(value);
	  }
	  
	  class Traitement implements Runnable{   
	    public void run(){
	      while(bar.getValue() < 100)
	      {
	    	try
			{
				Thread.sleep(750);
			} 
	    	catch (InterruptedException e)
			{
				e.printStackTrace();
			}
	      }
	    }   
	}  
}