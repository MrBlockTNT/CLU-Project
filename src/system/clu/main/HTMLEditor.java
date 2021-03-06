package system.clu.main;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.html.HTMLEditorKit;

import system.clu.crash.CrashReport;

public class HTMLEditor extends JFrame
{
	private JEditorPane editorPane, apercu;
	private JTabbedPane onglet = new JTabbedPane();
	   
	public HTMLEditor()
	{
		this.setSize(600, 400);
	    this.setTitle("�diteur HTML");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    editorPane = new JEditorPane();
	    editorPane.setText("<html>\n	<head>\n	</head>\n	<body>\n	</body>\n</html> ");
	      
	    apercu = new JEditorPane();
	    apercu.setEditable(false);
	      
	    onglet.addTab("Editeur HTML", new JScrollPane(editorPane));
	    onglet.addTab("Aper�u", new JScrollPane(apercu));
	    onglet.addChangeListener(new ChangeListener(){

	    public void stateChanged(ChangeEvent e) 
	    {            
	        FileWriter fw = null;            
	        try 
	        {
	        	fw = new FileWriter(new File("html/file.html"));
	        	fw.write(editorPane.getText());
	        	fw.close();
	        } 
	        catch (FileNotFoundException e1)
	        {
	        	CrashReport.generateFile();
	     	   try
	     	   {
	     		  e1.printStackTrace(new PrintStream(CrashReport.fileName));
	     	   } catch (FileNotFoundException e2)
	     	   {
	     		   e2.printStackTrace();
	     	   }
	        }
	        catch (IOException e1) 
	        {
	        	CrashReport.generateFile();
	     	   try
	     	   {
	     		  e1.printStackTrace(new PrintStream(CrashReport.fileName));
	     	   } catch (FileNotFoundException e2)
	     	   {
	     		   e2.printStackTrace();
	     	   }
	        }
	        try 
	        {
	        	File file = new File("html/file.html");
	        	apercu.setEditorKit(new HTMLEditorKit());               
	        	apercu.setPage(file.toURL());
	        } 
	        catch (IOException e1) 
	        {
	        	CrashReport.generateFile();
	     	   try
	     	   {
	     		  e1.printStackTrace(new PrintStream(CrashReport.fileName));
	     	   } catch (FileNotFoundException e2)
	     	   {
	     		   e2.printStackTrace();
	     	   }
	        }             
	      }            
	    });
	      
	    this.getContentPane().add(onglet, BorderLayout.CENTER);
	    this.setVisible(true);
	}
}
