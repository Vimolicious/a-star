package aStar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyInput extends KeyAdapter{
	
	static ArrayList<Integer> keysPressed;
	
	public KeyInput() {
		super();
		keysPressed = new ArrayList<Integer>();
	}
	
	public void keyPressed(KeyEvent e) {
		if(!keysPressed.contains(Integer.valueOf(e.getKeyCode())))
			keysPressed.add(Integer.valueOf(e.getKeyCode()));
	}
	
	public void keyReleased(KeyEvent e) {
		if(keysPressed.contains(Integer.valueOf(e.getKeyCode())))
			keysPressed.remove(Integer.valueOf(e.getKeyCode()));
	}
	
	public static boolean getKey(int key) {
		return keysPressed.contains(Integer.valueOf(key));
	}
	
	public static ArrayList<Integer> getKeys(){
		return keysPressed;
	}

}
