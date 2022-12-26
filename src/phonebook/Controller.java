package phonebook;

import phonebook.search.JumpSeaarcher;
import phonebook.search.LinearSearcher;
import phonebook.search.sort.BubbleSort;
import phonebook.util.ListLoader;
import phonebook.util.Timer;
import phonebook.view.CommandView;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class Controller {
    private final CommandView view = new CommandView();
    private final ListLoader loader = new ListLoader();
    private final Timer timer = new Timer();
    private long linearSearchTime;

    private List<String> searchList;
    private List<String> targetList;

    public void start() {
        performLinearSearch();

        performJumpSearch();
    }

    public void performLinearSearch() {
        view.showStartLinearSearch();
        timer.start();

        searchList = loader.loadSearchList();
        targetList = loader.loadTargetList();
        
        int foundEntries = new LinearSearcher().search(searchList, targetList);
        linearSearchTime = timer.stop();

        view.showSearchResult(foundEntries, targetList.size(), linearSearchTime);
    }

    public void performJumpSearch() {
        view.showBubbleSortAndJumpSearch();
        timer.start();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<String>> future = executor.submit(() -> {
            try {
                return new BubbleSort().sort(searchList, Comparator.naturalOrder());
            } catch (InterruptedException e) {
                return null;
            }
        });

        try {
            List<String> sortedList = future.get(linearSearchTime * 10, TimeUnit.MILLISECONDS);
            long sortingTime = timer.stop();

            timer.start();
            int foundEntries = new JumpSeaarcher().search(sortedList, targetList, Comparator.naturalOrder());
            long searchingTime = timer.stop();

            view.showSearchResult(foundEntries, targetList.size(), sortingTime + searchingTime);
            view.showSortingTime(sortingTime);
            view.showSearchingTime(searchingTime);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            long sortingTime = timer.stop();
            future.cancel(true);

            timer.start();
            int foundEntries = new LinearSearcher().search(searchList, targetList);
            long searchingTime = timer.stop();

            view.showSearchResult(foundEntries, targetList.size(), sortingTime + searchingTime);
            view.showSortingInterrupted(sortingTime);
            view.showSearchingTime(searchingTime);
        }
    }
}
