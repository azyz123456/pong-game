package pongGame;

import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{

	int id;
	int yVelocity;
	int xVelocity;
	int speed = 10;
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id=id;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(speed);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			break;
		case 3:
			if(e.getKeyCode()==KeyEvent.VK_A) {
				setXDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_D) {
				setXDirection(speed);
			}
			break;
			
		case 4:
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				setXDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				setXDirection(speed);
			}
			break;
			
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;

		case 3:
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				setXDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				setXDirection(0);
			}
			break;

		case 4:
			if(e.getKeyCode()==KeyEvent.VK_D) {
				setXDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_A) {
				setXDirection(0);
			}
			break;
		}
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}
	
	public void move() {
		y = y + yVelocity;
		x = x + xVelocity;
	}
	public void draw(Graphics g) {
		if(id==1)
			g.setColor(Color.pink);
		else
			g.setColor(new Color(0xC7A68F));
		g.fillRect(x, y, width, height);
	}
}

