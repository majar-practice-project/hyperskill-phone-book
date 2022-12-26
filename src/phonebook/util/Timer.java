package phonebook.util;

public class Timer {
    private long startTime;
    public void start(){
        startTime = System.currentTimeMillis();
    }

    public long stop(){
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
