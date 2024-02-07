import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean running = false;
		boolean gamestart = false;
		boolean program = true;
	    while (true) {
			if (!running) {
				running = true;
				Game game = new Game();
					if (!gamestart) {
						game.start();
						gamestart = true;
					}
					while (gamestart) {
						int resp = game.isrunning();
						if (resp == 0) {
							running = false;
							gamestart = false;
						}
						else if(resp == 1 || resp == -1) {
							program = false;
							gamestart = false;
						}
						else {
							continue;
						}
					}
					game.Clear();
					if (!program) {
						break;
					}
		}
	    }
	}
}