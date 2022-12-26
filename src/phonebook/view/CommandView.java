package phonebook.view;

public class CommandView {
    public void showStartSearching(){
        System.out.println("Start searching...");
    }

    public void showSearchResult(int foundEntries, int totalEntries, long timeTaken){
        System.out.printf("Found %d / %d entries. Time taken: %s%n",
                foundEntries,
                totalEntries,
                formatTime(timeTaken));
    }

    public String formatTime(long time){
        String timeFormat = "%d min. %d sec. %d ms.";
        return String.format(timeFormat, time/1000/60, time/1000%60, time%1000);
    }
}
