package com.tleilaxu;

public class Timer {
	private long passedTime;
	private long lastTime;
	public Timer(){
		passedTime = 0;
		lastTime = 0;
	}
	public void time(){
		if(lastTime == 0) lastTime = System.currentTimeMillis();
 		long currentTime = System.currentTimeMillis();
		passedTime += currentTime-lastTime;
		lastTime = System.currentTimeMillis();
	}
	public long getTime(){
		return passedTime;
	}
	public void setTime(long time){
		passedTime = 0;
	}
	public static long toMillSeconds(double d){
		return (long) (d*1000);
	}
}
