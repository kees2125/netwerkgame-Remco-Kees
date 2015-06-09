package control;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import model.Ball;
import model.Playerasdfasdf;

public class GameManager {

	
	public ArrayList<Playerasdfasdf> players = new ArrayList<Playerasdfasdf>();
	public Ball ball = new Ball(300,300,20);
	public ArrayList<Shape> bounderys = new ArrayList<Shape>();
	public GameManager()
	{
		
		
		
	}
	
	public void Update()
	{
		int temp =0;
		Area tempArea = new Area(ball.getShape());
		if(players.size()  == 2)
		{
			if(ball.getShape().getBounds2D().getY() <0 || ball.getShape().getBounds2D().getY() > 600 + ball.getShape().getBounds2D().getHeight())
			{
				temp += 2;
			}
			
		}
		else if(players.size() == 3)
		{
			if(ball.getShape().getBounds2D().getY() <0 )
			{
				temp += 2;
			}
		}
		for(Playerasdfasdf p: players)
		{
			if(p.testIntersection(tempArea))
			{
				if(p.getPlayerNmr() >2)
				{
					temp +=2;
				}
				else
				{
					temp +=1;
				}
				break;
			}
		}
		ball.update(temp);
	}
	

	
	
	
	
}
