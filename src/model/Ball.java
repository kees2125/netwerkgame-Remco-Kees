package model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ball {
	
	private Shape shape = new Ellipse2D.Double();
	private double xVelosity,yVelosity;
	
	public Ball(int x,int y,int radius)
	{
		xVelosity = 15;
		yVelosity = 15;
		((Ellipse2D)shape).setFrame(x,y,radius,radius);
	}
	
	public void update(int colision)
	{
		
		if(colision ==1)
		{
			xVelosity = -xVelosity;
			xVelosity += 2 * Math.random();
			yVelosity += 2 * Math.random();
		}
		else if(colision == 2){
			yVelosity = -yVelosity;
			xVelosity += 2 * Math.random();
			yVelosity += 2 * Math.random();
		}
		else if(colision == 3 )
		{
			xVelosity = -xVelosity;
			yVelosity = -yVelosity;
			xVelosity += 2 * Math.random();
			yVelosity += 2 * Math.random();
		}
		Moveball(xVelosity,yVelosity);
		
	}
	
	private void Moveball(double x, double y)
	{
		((Ellipse2D)shape).setFrame(shape.getBounds2D().getX() + x,shape.getBounds2D().getY()+y,20,20);
	}
	
	public Shape getShape()
	{
		return shape;
	}
}
