package aStar;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	private static boolean pressed, inApp, clicked, second;
	private static int x, y;

	public MouseInput() {
		pressed = false;
		inApp = true;
		clicked = false;
		second = false;

		x = 0;
		y = 0;
	}

	public static int getX() { 
		return x;
	}

	public static int getY() { 
		return y;
	}

	public static boolean getSecond() {
		return second;
	}

	public static boolean getPressed() {
		return pressed;
	}

	public static boolean getInApp() {
		return inApp;
	}

	public static boolean getClicked() {
		return clicked;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			pressed = true;
		else
			second = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
		second = false;
		clicked = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		inApp = true;
		MouseMove.x = e.getX();
		MouseMove.y = e.getY();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		inApp = false;
	}
}