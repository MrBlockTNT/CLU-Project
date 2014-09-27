package system.clu.main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import system.clu.crash.CrashReport;

public class Panel extends JPanel
{
	public void paintComponent(Graphics g)
	{
		try
		{
			Image bgImg = ImageIO.read(new File("assets/clu.jpg"));
			g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e)
		{
			CrashReport.generateFile();
			   try
			   {
				   e.printStackTrace(new PrintStream(CrashReport.fileName));
			   } catch (FileNotFoundException e1)
			   {
				   e1.printStackTrace();
			   }
			CLU.error.showMessageDialog(null, "Erreur", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
