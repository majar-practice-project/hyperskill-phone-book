package phonebook.util;

public class Timer {
    private long startTime;
    private boolean started;
    public void start(){
        if(!started) {
            started = true;
            startTime = System.currentTimeMillis();
        }
    }

    public long stop(){
        long endTime = System.currentTimeMillis();
        started = false;
        return endTime - startTime;
    }
}
