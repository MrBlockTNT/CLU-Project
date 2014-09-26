package system.clu.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import system.clu.misc.Save;

public class Key implements KeyListener
{
	@Override
	public void keyPressed(KeyEvent event)
	{
		if(event.getKeyCode() == event.VK_ENTER && (Window.getTextboxText() != null && Window.getTextboxText() != "" && !Window.getTextboxText().isEmpty()))
		{
			CLU.analyse(Window.getTextboxText());
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		
	}

	@Override
	public void keyTyped(KeyEvent event)
	{
		
	}
}
