package control;

import java.awt.Graphics2D;

import javax.swing.plaf.SliderUI;

public class Drawing {

	public static void drawCountdown(Graphics2D g2, int nummber)
	{
		
		g2.drawString(nummber + "", 290, 290);
		nummber--;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		g2.clearRect(290, 290, 50, 50);
		if(nummber >-1)
		drawCountdown(g2,nummber);
	}
}
