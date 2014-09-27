package system.clu.main;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ButtonExit extends JButton implements MouseListener
{
	private String name;
	private Image img;
	public ButtonExit(String name)
	{
		super(name);
		this.name = name;
		/*try
		{
			img = ImageIO.read(new File("assets/button.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			CLU.error.showMessageDialog(null, "Erreur", "Erreur", JOptionPane.ERROR_MESSAGE);
		}*/
		this.setBackground(Color.BLACK);
		this.setOpaque(false);
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
	    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
	    g2d.setPaint(gp);
	    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    g2d.setColor(Color.BLACK);
	    g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() / 2 /4), (this.getHeight() / 2) + 5);
	}
	
	@Override
	public void mousePressed(MouseEvent event)
	{ 
		if(Window.getTextboxText() != null && Window.getTextboxText() != "" && !Window.getTextboxText().isEmpty())
		{
			CLU.analyse(Window.getTextboxText());
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		this.setBorder(BorderFactory.createEmptyBorder());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
