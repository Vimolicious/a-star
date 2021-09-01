package aStar;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Game extends Canvas{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 700, HEIGHT = 800;
//	public static int frames;
	private Handler handler;


	public Game() {
		super();
		handler = new Handler();
		this.setFocusable(true);

		KeyInput key = new KeyInput();
		this.addKeyListener(key);
		
		MouseInput mouse = new MouseInput();
		this.addMouseListener(mouse);
		
		MouseMove move = new MouseMove();
		this.addMouseMotionListener(move);

		new Window("A* Path Finding", WIDTH, HEIGHT + 22, this);
	}

	public void run() {
		long last = System.nanoTime();//, timer = System.currentTimeMillis();
		double ns = 1000000000 / 60.0, delta = 0;
//		frames = 0;

		while(true) {
			long now = System.nanoTime();
			delta += (now - last) / ns;

			last = now;

			while(delta > 0) {
				tick();
				delta--;
			}

			render();
//
//			frames++;
//			if(System.currentTimeMillis() - timer > 1000) {
//				timer += 1000;
//				System.out.println("fps " + frames);
//				frames = 0;
//			}
		}
	}

	public void tick() {
		handler.tick();
	}

	public void render() {
		BufferStrategy bfs = this.getBufferStrategy();

		if(bfs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bfs.getDrawGraphics();
		
		handler.render((Graphics2D)g);

		g.dispose();
		bfs.show();
	}
}
