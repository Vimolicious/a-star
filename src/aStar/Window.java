package aStar;

import javax.swing.JFrame;

public class Window {
	
	public static JFrame frame;
	
	public Window(String title, int width, int height, Game g) {
		frame = new JFrame(title);
		
		frame.setSize(width, height);
		frame.setResizable(false);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(g);
		frame.setVisible(true);
	}
	
	public static void close() {
		frame.setVisible(false);
		frame.dispose();
	}
}
