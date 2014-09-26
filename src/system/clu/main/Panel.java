package system.clu.main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
			e.printStackTrace();
			CLU.error.showMessageDialog(null, "Erreur", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
