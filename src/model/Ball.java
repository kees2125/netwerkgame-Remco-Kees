package model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ball {
	
	private Shape shape = new Ellipse2D.Double();
	private double xVelosity,yVelosity;
	private double speedAddit = 1.2;
	
	public Ball(int x,int y,int radius)
	{
		xVelosity = 3;
		yVelosity = 1;
		((Ellipse2D)shape).setFrame(x,y,radius,radius);
	}
	
	public void update(int colision)
	{
		
		if(colision ==1)
		{
			xVelosity += speedAddit * Math.random();
			yVelosity += speedAddit * Math.random();
			xVelosity = -xVelosity;
			
		}
		else if(colision == 2){
			xVelosity += speedAddit * Math.random();
			yVelosity += speedAddit * Math.random();
			yVelosity = -yVelosity;
			
		}
		else if(colision == 3 )
		{
			xVelosity += speedAddit * Math.random();
			yVelosity += speedAddit * Math.random();
			xVelosity = -xVelosity;
			yVelosity = -yVelosity;
			
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
