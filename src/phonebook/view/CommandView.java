package phonebook.view;

public class CommandView {
    public void showStartLinearSearch(){
        System.out.println("Start searching (linear search)...");
    }

    public void showStartBubbleSortAndJumpSearch(){
        System.out.println("Start searching (bubble sort + jump search)...");
    }

    public void showStartQuickSortAndBinarySearch(){
        System.out.println("Start searching (quick sort + binary search)...");
    }

    public void showStartHashTable(){
        System.out.println("Start searching (hash table)...");
    }

    public void showSearchResult(int foundEntries, int totalEntries, long timeTaken){
        System.out.printf("Found %d / %d entries. Time taken: %s%n",
                foundEntries,
                totalEntries,
                formatTime(timeTaken));
    }

    public void showSortingTime(long time){
        System.out.printf("Sorting time: %s%n", formatTime(time));
    }

    public void showSortingInterrupted(long time){
        System.out.printf("Sorting time: %s %s%n", formatTime(time), "- STOPPED, moved to linear search");
    }

    public void showSearchingTime(long time){
        System.out.printf("Searching time: %s%n", formatTime(time));
    }

    public void showCreatingTime(long time){
        System.out.printf("Creating time: %s%n", formatTime(time));
    }

    private static String formatTime(long time){
        String timeFormat = "%d min. %d sec. %d ms.";
        return String.format(timeFormat, time/1000/60, time/1000%60, time%1000);
    }
}
