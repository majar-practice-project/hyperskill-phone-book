package phonebook;

import phonebook.search.BinarySearch;
import phonebook.search.HashTableSearch;
import phonebook.search.JumpSearcher;
import phonebook.search.LinearSearcher;
import phonebook.search.sort.BubbleSort;
import phonebook.search.sort.QuickSort;
import phonebook.util.FileLoader;
import phonebook.util.Timer;
import phonebook.view.CommandView;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Controller {
    private final CommandView view = new CommandView();
    private final FileLoader loader = new FileLoader();
    private final Timer timer = new Timer();
    private long linearSearchTime;

    private List<String> searchList;
    private List<String> targetList;

    public void start() {
        performLinearSearch();

        performJumpSearch();

        performBinarySearch();

        performHashTableSearch();
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
        view.showStartBubbleSortAndJumpSearch();
        timer.start();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<String>> future = executor.submit(() -> {
            try {
                return new BubbleSort().sort(searchList, Comparator.naturalOrder());
            } catch (InterruptedException e) {
                throw new InterruptedException();
            }
        });

        try {
            List<String> sortedList = future.get(linearSearchTime * 10, TimeUnit.MILLISECONDS);
            long sortingTime = timer.stop();

            timer.start();
            int foundEntries = new JumpSearcher().search(sortedList, targetList, Comparator.naturalOrder());
            long searchingTime = timer.stop();

            view.showSearchResult(foundEntries, targetList.size(), sortingTime + searchingTime);
            view.showSortingTime(sortingTime);
            view.showSearchingTime(searchingTime);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            long sortingTime = timer.stop();
            future.cancel(true);
            executor.shutdown();

            timer.start();
            int foundEntries = new LinearSearcher().search(searchList, targetList);
            long searchingTime = timer.stop();

            view.showSearchResult(foundEntries, targetList.size(), sortingTime + searchingTime);
            view.showSortingInterrupted(sortingTime);
            view.showSearchingTime(searchingTime);
        }
    }

    public void performBinarySearch() {
        view.showStartQuickSortAndBinarySearch();
        timer.start();
        List<String> sortedList = new QuickSort().sort(searchList, Comparator.naturalOrder());
        long sortingTime = timer.stop();

        timer.start();
        int foundEntries = new BinarySearch().search(sortedList, targetList, Comparator.naturalOrder());
        long searchingTime = timer.stop();

        view.showSearchResult(foundEntries, targetList.size(), sortingTime + searchingTime);
        view.showSortingTime(sortingTime);
        view.showSearchingTime(searchingTime);
    }

    public void performHashTableSearch() {
        view.showStartHashTable();
        timer.start();
        Set<String> hashTable = new HashSet<>(searchList);
        long creatingTime = timer.stop();

        timer.start();
        int foundEntries = new HashTableSearch().search(hashTable, targetList);
        long searchingTime = timer.stop();

        view.showSearchResult(foundEntries, targetList.size(), creatingTime + searchingTime);
        view.showCreatingTime(creatingTime);
        view.showSearchingTime(searchingTime);
    }
}
