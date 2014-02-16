package com.example.robotjoystick;

import com.igordeoliveira.robot.Robot;

public class RobotController implements Runnable {

	private static Robot robot = null;
	
	private final int STOP = 0;
	private final int FORWARD = 1;
	private final int BACKWARD = 2;
	private final int LEFT = 3;
	private final int RIGHT = 4;
	
	
	private int currentDirection = STOP;
	private int lastDirection = 0;
	
	public Robot getRobot() {
		if ( robot == null ) {
			robot = new Robot();
		}
		return robot;
	}
	
	@Override
	public void run() {
		while (true) {
			if (lastDirection != currentDirection) {
					
				if (currentDirection==FORWARD) {
					getRobot().forward();
				}
				else if (currentDirection == BACKWARD) {
					getRobot().backward();
				}
				else if (currentDirection == LEFT) {
					getRobot().left();
				}
				else if (currentDirection == RIGHT) {
					getRobot().right();
				}
				else if (currentDirection == STOP) {
					getRobot().stop();
				}
				lastDirection = currentDirection;
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void forward() {
		this.currentDirection = FORWARD;
	}

	public void backward() {
		this.currentDirection = BACKWARD;
	}

	public void left() {
		this.currentDirection = LEFT;
	}
	
	public void right() {
		this.currentDirection = RIGHT;
	}
	
	public void stop() {
		this.currentDirection = STOP;
	}

}
