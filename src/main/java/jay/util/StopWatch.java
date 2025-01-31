package jay.util;

public class StopWatch extends PeriodicClass{

    private double passed;
    private boolean started = false, paused = false;

    public StopWatch(){
        passed = 0;
    }

    public void startIfNotStarted(){
        if(!started) {
            passed = 0;
            started = true;
        }
    }

    public void pause(){
        paused = true;
    }

    public void unpause(){
        paused = false;
    }

    public void stop(){
        started = false;
        paused = false;
        passed = 0;
    }

    @Override
    public void periodic() {
        try {
            if(started && !paused) {
                Thread.sleep(1000);
                passed++;
            }
        } catch (Exception e){}
    }
}
