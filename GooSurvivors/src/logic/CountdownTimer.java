package logic;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private int minutes;
    private int seconds;
    private Timer timer;

    public CountdownTimer() {
        this.minutes = 15;
        this.seconds = 0;
        this.timer = new Timer();
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (minutes == 0 && seconds == 0) {
                    System.out.println("Time's up!");
                    timer.cancel();
                } else {
                    if (seconds == 0) {
                        minutes--;
                        seconds = 59;
                    } else {
                        seconds--;
                    }
                 
                }
            }
        }, 0, 1000);
    }

    public int getMins() {
    	return minutes;
    }
    
    public int getSecs() {
    	return seconds;
    }

}
