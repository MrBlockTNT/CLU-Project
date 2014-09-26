package system.clu.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javafx.scene.layout.Border;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame implements ComponentListener
{
	private static JTextField control_text = new JTextField("");
	private static JTextArea output_text = new JTextArea();
	private JScrollPane scroll = new JScrollPane(output_text, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private Button ok_button = new Button("OK");
	
	public Window(String name, int sizeX, int sizeY, Component location, int closeMode, boolean visible, Color panelBG, boolean resizable)
	{
		JPanel panel = new Panel();
		panel.setBackground(panelBG);
		panel.setLayout(new GridBagLayout());
		
		JPanel control = new PanelIn();
		control.setLayout(new BorderLayout());
		
		Font police = new Font("Arial", Font.BOLD, 14);
		
		ok_button.setPreferredSize(new Dimension(70, 30));
		ok_button.setBorder(BorderFactory.createEmptyBorder());
		
		control_text.setFont(police);
		control_text.setBackground(new Color(0, 0, 0, 0));
		control_text.setOpaque(false);
		control_text.setForeground(Color.WHITE);
		control_text.setPreferredSize(new Dimension(730, 30));
		control_text.addKeyListener(new Key());
		control_text.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		output_text.setPreferredSize(new Dimension(820, 450));
		output_text.setFont(police);
		output_text.setBackground(new Color(0, 0, 0, 0));
		output_text.setOpaque(false);
		output_text.setBorder(null);
		output_text.setBounds(40, 200, 820, 500);
		output_text.setForeground(Color.white);
		output_text.setEditable(false);
		output_text.setWrapStyleWord(true);
		output_text.setAutoscrolls(true);
		
		scroll.setWheelScrollingEnabled(true);
		scroll.setPreferredSize(new Dimension(820, 450));
		scroll.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		scroll.setBackground(new Color(0, 0, 0, 0));
		scroll.setOpaque(false);
		scroll.setViewportView(output_text);
		scroll.getViewport().setOpaque(false);
		((JComponent) scroll.getViewport().getView()).setOpaque(false);
		scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
		control.add(ok_button, BorderLayout.EAST);
		
		GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = gbc.gridy = 0;
        gbc.gridheight = 470; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbc.insets = new Insets(0, 10, 0, 0);
        panel.add(scroll, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 473;
        gbc.gridheight = 2; 
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbc.insets = new Insets(10, 10, 10, 0);
        panel.add(control_text, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 473;
        gbc.gridheight = 2; 
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbc.insets = new Insets(10, 10, 10, 0);
        panel.add(control, gbc);
		
        this.addComponentListener(this);
		this.setContentPane(panel);
		this.setTitle(name);
		this.setSize(sizeX, sizeY);
		this.setLocationRelativeTo(location);
		// 3 = EXIT_ON_CLOSE
		this.setDefaultCloseOperation(closeMode);
		this.setResizable(resizable);
		this.setVisible(visible);
	}
	
	public static String getTextboxText()
	{
		return control_text.getText();
	}
	
	public static void resetTextInput()
	{
		control_text.setText("");
	}
	
	public static void addTextToOutput(String text)
	{
		output_text.setText(text + "\n" + output_text.getText());
	}
	
	public static void clearOutput()
	{
		output_text.setText("");
	}
	
	public static String getOutputText()
	{
		return output_text.getText();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {}

	@Override
	public void componentMoved(ComponentEvent arg0) {}

	@Override
	public void componentResized(ComponentEvent arg0) {}

	@Override
	public void componentShown(ComponentEvent arg0) {}
}
