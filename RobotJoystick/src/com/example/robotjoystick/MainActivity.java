package com.example.robotjoystick;

import com.igordeoliveira.robot.Robot;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	RobotController robotController = new RobotController();
	Thread robotThread = new Thread(robotController);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.enableDefaults();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		robotThread.start();
		
		Button btnForward = (Button)findViewById(R.id.btnForward);
		btnForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                	robotController.forward();
                	Log.v("robot", "forward");
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                	robotController.stop();
                }
                return true;
            }
        });
		
		Button btnBackward = (Button)findViewById(R.id.btnBackward);
		btnBackward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                	robotController.backward();
                	Log.v("robot", "backward");
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                	robotController.stop();
                }
                return true;
            }
        });		
		
		Button btnLeft = (Button)findViewById(R.id.btnLeft);
		btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                	robotController.left();
                	Log.v("robot", "left");
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                	robotController.stop();
                }
                return true;
            }
        });		
		
		Button btnRight = (Button)findViewById(R.id.btnRight);
		btnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                	robotController.right();
                	Log.v("robot", "right");
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                	robotController.stop();
                }
                return true;
            }
        });		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
