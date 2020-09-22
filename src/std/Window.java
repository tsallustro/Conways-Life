package std;


import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;


public class Window extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2287256386593417927L;
	protected GameArea ga;
	public Window() {
		
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
		
		//Child Component setup
		ga = new GameArea();
		ga.setPreferredSize(new Dimension (CellMap.XSIZE*10, CellMap.YSIZE *10));
		this.setContentPane(ga);
		
		
		//Window Properties
		this.setTitle("Shmuck");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}
	
}
