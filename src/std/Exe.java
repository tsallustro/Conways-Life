package std;
import java.lang.reflect.InvocationTargetException;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import io.LifeIO;

public class Exe {
	public static final CellMap MAP = LifeIO.startPrompt();
	public static Window win;

	public static void main(String[] args) {
		win = new Window();
		Timer t = new Timer();
		t.schedule(new Runner(), 1000, 500);

	}

	static class Runner extends TimerTask {
		int turn = 0;

		@Override
		public void run() {

			System.out.println("Processing turn " + (turn + 1));
			MAP.processAll();
			try {
				SwingUtilities.invokeAndWait(new Runnable() {

					@Override
					public void run() {

						win.repaint();
					}

				});

			} catch (InterruptedException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			turn++;
		}

	}
}
