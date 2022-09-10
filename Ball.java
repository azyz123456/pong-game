package pongGame;


import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 7;
	int qInitialSpeed = 3;
	
	Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		int randomYDirection = random.nextInt(2);

		
		if(randomXDirection == 0) {
			randomXDirection--;
		}
		
		if(randomYDirection == 0) {
			randomYDirection--;
		}
		
		if (Pong.getFrame().isCurve()) {
			setXDirection(randomXDirection*qInitialSpeed);
			setYDirection(randomYDirection*qInitialSpeed);

		} else {
			setXDirection(randomXDirection*initialSpeed);
			setYDirection(randomYDirection*initialSpeed);

		}
		
		
		

	
	}
	
	
	
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	public void move() {
		if(Pong.getFrame().isCurve())
		{
			if(Math.random() > 0.9)
			{
				x += xVelocity *  (Math.random() + 1) * 4;
				y += yVelocity * (Math.random() - 1) * 4;
			}else
			{
				x += xVelocity ;
				y += yVelocity ;
			}
		}else
		{
			x += xVelocity;
			y += yVelocity;
		}
	}
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fillOval(x, y, height, width);
	}
}