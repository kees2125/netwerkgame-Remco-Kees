package control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import model.Ball;
import model.Player;

public class GameManager<E> {

	public ArrayList<E> players = (ArrayList<E>) new ArrayList<Player>();
	public Ball ball = new Ball(300,300,20);
	private Controlmanager control;
	
	public GameManager(Controlmanager control)
	{
		this.control = control;
		for(int i =0; i< control.getClient().getTotalPlayers(); i++)
		{
			players.add((E) new Player(i+1));
		}
	}
	
	public void update()
	{
		if(control.isServer())
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
			control.getServer().setBall(new Point2D.Double(ball.getShape().getBounds2D().getX(), ball.getShape().getBounds2D().getY()));
			control.getServer().getInfo(0).setPosition(new Point2D.Double(((Player)players.get(0)).getPlayerShape().getBounds2D().getX(), ((Player)players.get(0)).getPlayerShape().getBounds2D().getY()));
			
			Shape playerShape = new Rectangle2D.Double();
			((Rectangle2D)playerShape).setFrame(control.getServer().getInfo(1).getPosition().getX(),control.getServer().getInfo(1).getPosition().getY(),5,100);
			((Player) players.get(1)).setPlayerShape(playerShape);
			for(int i = 0; i < players.size(); i++)
			{
				control.getServer().getInfo(i).setScore(((Player) players.get(i)).getScore());
			}
			
		}
		else
		{
			ball = new Ball((int) control.getClient().getBall().getX(), (int) control.getClient().getBall().getY(),20);
			Shape playerShape = new Rectangle2D.Double();
			((Rectangle2D)playerShape).setFrame(control.getClient().getPlayerInfo(0).getPosition().getX(),control.getClient().getPlayerInfo(0).getPosition().getY(),5,100);
			((Player) players.get(0)).setPlayerShape(playerShape);
			control.getClient().getPlayerInfo(1).setPosition(new Point2D.Double(((Player)players.get(1)).getPlayerShape().getBounds2D().getX(), ((Player)players.get(1)).getPlayerShape().getBounds2D().getY()));
		}
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setColor(Color.WHITE);
		if(control.isServer())
		{
			for(E p: players)
			{
				g2.fill(((Player) p).getPlayerShape());
				g2.drawString("Player"+((Player) p).getPlayerNmr() +": "+((Player) p).getScore(), 50, 50*((Player) p).getPlayerNmr());
			}
		}
		else
		{
			for(int i = 0; i < players.size(); i++)
			{
				g2.fill(((Player) players.get(i)).getPlayerShape());
				g2.drawString("Player"+((Player) players.get(i)).getPlayerNmr() +": "+control.getClient().getPlayerInfo(i).getScore(), 50, 50*((Player) players.get(i)).getPlayerNmr());
			}
		}
		g2.fill(ball.getShape());
		
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
