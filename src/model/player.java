package model;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class player {
private int score;
private int playerNmr;
private Shape playerShape;


	public player(int Nmr)
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
			((Rectangle2D)playerShape).setFrame(playerShape.getBounds2D().getX()+toMove,playerShape.getBounds2D().getY(),100,5);
		}
		else
		{
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
}

