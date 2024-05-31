package logic;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    // Variables to store minutes, seconds, and Timer instance
    private int minutes;
    private int seconds;
    private Timer timer;

    // Constructor initializes the timer with default values
    public CountdownTimer() {
        this.minutes = 6;  // Set initial minutes
        this.seconds = 0;  // Set initial seconds
        this.timer = new Timer();  // Create a new Timer instance
    }

    // Method to start the countdown timer
    public void start() {
        // Schedule a TimerTask to run at fixed rate
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Check if the countdown is complete
                if (minutes == 0 && seconds == 0) {
                    System.out.println("Time's up!");
                    timer.cancel();  // Stop the timer
                } else {
                    // Decrement minutes and reset seconds if necessary
                    if (seconds == 0) {
                        minutes--;
                        seconds = 59;
                    } else {
                        seconds--;
                    }
                }
            }
        }, 0, 1000);  // Delay before task starts and period between successive task executions
    }

    // Getter method for minutes
    public int getMins() {
        return minutes;
    }

    // Getter method for seconds
    public int getSecs() {
        return seconds;
    }
}
