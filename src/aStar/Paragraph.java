package aStar;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Paragraph {
	
	private int x, y, offSet;
	private ArrayList<String> lines;
	private Font face;
	
	public Paragraph(int x, int y, String fileDirectory, int offSet) {
		lines = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileDirectory))){
			for(String line; (line = br.readLine()) != null;)
				lines.add(line);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		face = new Font("Trebuchet MS", Font.BOLD, 30);
		
		
		this.x = x;
		this.y = y;
		this.offSet = offSet;
	}
	
	public void render(Graphics2D g) {
		g.setFont(face);
		FontMetrics m = g.getFontMetrics();
		
		int maxSize = 0;
		for(String s : lines)
			if(maxSize < m.stringWidth(s))
				maxSize = m.stringWidth(s);
		
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(x, y, maxSize + offSet*4, lines.size()*m.getAscent() + offSet*3);
		
		g.setColor(Color.WHITE);
		for(int i = 0; i < lines.size(); i++) 
			g.drawString(lines.get(i), offSet*2 + x, y + (i+1)*m.getAscent() + offSet);
	}
}
