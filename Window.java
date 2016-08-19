import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;





public class Window extends JFrame implements ActionListener, MouseListener

{

	private Render render;
	
	public Window(Render render)
	{
		this.render = render;
		this.setSize(1200, 1000);
		init();
		create();
	}
	
	public void init()
	{
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
		this.add(render);
		render.setPreferredSize(new Dimension(1080, 800));
		Container panel = this.getContentPane();
		JPanel buttons = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(render, BorderLayout.CENTER);
		render.addMouseListener(this);
		panel.add(buttons,BorderLayout.EAST);

	}
	
	
	public void create()
	{
		this.setVisible(true);
	}
	
	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("Click " + arg0.getX() + " " + arg0.getY());
			Game.engine.mousePressed(arg0.getX(), arg0.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
