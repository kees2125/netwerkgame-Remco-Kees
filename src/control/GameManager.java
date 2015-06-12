package control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.util.ArrayList;

import model.Ball;
import model.Player;

public class GameManager<E> {

	
	public ArrayList<E> players = (ArrayList<E>) new ArrayList<Player>();
	public Ball ball = new Ball(300,300,20);
	
	public GameManager(int player)
	{
		for(int i =0; i< player; i++)
		{
			players.add((E) new Player(i+1));
		}
		
	}
	
	public void update()
	{
		int temp =0;
		Area tempArea = new Area(ball.getShape());
		if(players.size()  == 2)
		{
			if(ball.getShape().getBounds2D().getY() <0 || ball.getShape().getBounds2D().getY() + ball.getShape().getBounds2D().getHeight() > 600 )
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
		for(E p: players)
		{
			if(((Player) p).testIntersection(tempArea))
			{
				if(((Player) p).getPlayerNmr() >2)
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
		
		if(ball.getShape().getBounds2D().getX() <-30)
		{
			((Player) players.get(0)).addScore(1);
			ball = new Ball(300,300,20);
		}
		else if(ball.getShape().getBounds2D().getX() >600)
		{
			((Player) players.get(1)).addScore(1);
			ball = new Ball(300,300,20);
		}
		else if(players.size()>2)
		{
			if(ball.getShape().getBounds2D().getY() > 600 + ball.getShape().getBounds2D().getHeight())
			{
				((Player) players.get(2)).addScore(1);
				ball = new Ball(300,300,20);
			}
			else if(players.size()>3 && ball.getShape().getBounds2D().getY() <-30 )
			{
				((Player) players.get(3)).addScore(1);
				ball = new Ball(300,300,20);
			}
		}
			ball.update(temp);
		
		
		
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setColor(Color.WHITE);
		for(E p: players)
		{
			g2.fill(((Player) p).getPlayerShape());
			g2.drawString("Player"+((Player) p).getPlayerNmr() +": "+((Player) p).getScore(), 50, 50*((Player) p).getPlayerNmr());
		}
		g2.fill(ball.getShape());
		//g2.setColor(Color.green);
		
	}
	
	public Player getPlayer(int player)
	{
		if(player>0 && player <= players.size())
		{
			return (Player) players.get(player-1);
		}
		
		return null;
	}
	

	
	
	
	
}
