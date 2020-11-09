package std;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import io.IOWindow;
import io.LifeIO;

public class Exe {
	public static CellMap MAP;
	public static Window win;

	public static void main(String[] args) {
		win = new Window();
		MAP = new CellMap();
		IOWindow iowindow = new IOWindow(Thread.currentThread());

		// Text mode stuff
		// Scanner sysIn = new Scanner(System.in);
		// MAP = LifeIO.startPrompt(sysIn);

		/* Make this thread sleep until the iowindow is closed and interrupts */
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//Break out and move on to the sim
				break;
			}
		}
		// Though Swing is not thread safe, this is ok because the main thread does
		// nothing.
		Timer t = new Timer();
		t.schedule(new Runner(), 1000, 500);

	}

	static class Runner extends TimerTask {
		int turn = 1;

		@Override
		public void run() {

			System.out.println("Processing turn " + turn);
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
