package model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ball {
	
	private Shape shape = new Ellipse2D.Double();
	private double xVelosity,yVelosity;
	private double speedAddit = 0.6;
	private int counter;
	
	public Ball(int x,int y,int radius)
	{
		xVelosity = 3;
		yVelosity = 1;
		((Ellipse2D)shape).setFrame(x,y,radius,radius);
	}
	
	public void update(int colision)
	{
		
		counter ++;
		if(counter >30)
		if(colision ==1)
		{
			addRandom();
			xVelosity = -xVelosity;
			counter = 0;
			
		}
		else if(colision == 2){
			addRandom();
			yVelosity = -yVelosity;
			counter = 0;
		}
		else if(colision == 3 )
		{
			addRandom();
			xVelosity = -xVelosity;
			yVelosity = -yVelosity;
			counter = 0;
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
	private void addRandom()
	{
		double temp = Math.random();
		if(xVelosity >0)
		{
			xVelosity +=  speedAddit * temp;
		}
		else
		{
			xVelosity -=  speedAddit * temp;
		}
		if(yVelosity>0)
		{
			yVelosity += speedAddit * temp;
		}
		else
		{
			yVelosity -= speedAddit * temp;
		}
	}
}
