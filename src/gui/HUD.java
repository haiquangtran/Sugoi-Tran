package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import main.Constants;
import objects.Player;
import tools.ImageLibrary;

public class HUD {
//60,20,183,25
	public void draw(Graphics g, Player p, double width, double height){
		BufferedImage hud = ImageLibrary.get("hudSprite.png");
		int x = (int)(width-hud.getWidth());
		int y = 0;
		g.drawImage(hud,x,y,null);
		int barX = 60;
		int barY = 20;
		int barLength = 183-60;
		int barHeight = 25-20;
		g.setColor(Color.YELLOW);
		g.fillRect(barX+x, barY+y, (int)((double)barLength * ((double)p.energy/(double)Constants.MAX_ENERGY)), barHeight);
		Font f = new Font("Sans", Font.PLAIN, 12);
		g.setColor(Color.RED);
		g.setFont(f);
		int scoreY = 52;
		g.drawString(String.valueOf(p.points), barX+x, scoreY+y);
		f = new Font("Sans", Font.PLAIN, 50);
		g.setFont(f);
		if(p.dead){
			String s = "Press Space to restart";
			int strwidth = SwingUtilities.computeStringWidth(g.getFontMetrics(), s);
			g.drawString(s, (int)(width/2-strwidth/2), (int)(height/2));
		}
	}

}
