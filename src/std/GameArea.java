package std;

import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

public class GameArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4520921686221960617L;

	public GameArea() {
		super();
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("DEBUG: Painting Game Area");
		super.paint(g);
		
		for (int x = 0; x < CellMap.XSIZE; x++) {
			for (int y = 0; y < CellMap.YSIZE; y++) {
				g.setColor(Color.BLACK);
				g.drawRect(x * 10, y * 10, 10, 10);
				
				if(!Exe.MAP.cellAt(x, y))
				{
					g.setColor(Color.WHITE);
				}
				g.fillRect(x * 10, y * 10, 10, 10);
			}
		}

	}

}
