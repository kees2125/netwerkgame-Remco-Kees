package model;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;

public class Player implements Comparable,Comparator<Shape> {
private int score;
private int playerNmr;
private Shape playerShape;


	public Player(int Nmr)  
	{
		score = 0;
		playerNmr = Nmr;
		playerShape = new Rectangle2D.Double();
			if(Nmr == 4)
			{
				((Rectangle2D)playerShape).setFrame(300,5,100,5);
			}
			if(Nmr == 3)
			{
				((Rectangle2D)playerShape).setFrame(300,595,100,5);
			}
			if(Nmr == 2)
			{
				((Rectangle2D)playerShape).setFrame(595,300,5,100);
			}
			if(Nmr == 1)
			{
				((Rectangle2D)playerShape).setFrame(5,300,5,100);
			}
	}


	public int getScore() {
		return score;
	}
	
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int score)
	{
		this.score += score;
	}
	
	
	public Shape getPlayerShape() {
		return playerShape;
	}
	
	
	public void setPlayerShape(Shape playerShape) {
		this.playerShape = playerShape;
	}
	
	public void move(double toMove)
	{
		if(playerNmr >2)
		{
			if(playerShape.getBounds2D().getX()>0 && toMove<0 || playerShape.getBounds2D().getX() + playerShape.getBounds2D().getWidth() <600 && toMove>0)
			((Rectangle2D)playerShape).setFrame(playerShape.getBounds2D().getX()+toMove,playerShape.getBounds2D().getY(),100,5);
		}
		else
		{
			if(playerShape.getBounds2D().getY()>0 && toMove<0 || playerShape.getBounds2D().getY() + playerShape.getBounds2D().getHeight() <600 && toMove>0)
			((Rectangle2D)playerShape).setFrame(playerShape.getBounds2D().getX(),playerShape.getBounds2D().getY()+toMove,5,100);
		}
	}
	public boolean testIntersection(Shape shapeA) {
		   Area areaA = new Area(shapeA);
		   areaA.intersect(new Area(playerShape));
		   return !areaA.isEmpty();
		}
	
	public int getPlayerNmr()
	{
		return playerNmr;
	}


	@Override
	public int compareTo(Object shape) {
		
		   if(playerShape.equals(shape))
		   {
			   return 1;
		   }
		   else
		   {
			   return 0;
		   }
	}


	

	@Override
	public int compare(Shape shape1, Shape shape2) {
		Area areaA = new Area((Shape) shape1);
		   if(!areaA.equals(new Area((Shape) shape2)))
		   {
			   return 1;
		   }
		   else
		   {
			   return 0;
		   }
	}
}

