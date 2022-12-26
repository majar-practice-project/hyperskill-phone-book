package phonebook;

import phonebook.util.ListLoader;
import phonebook.util.Timer;
import phonebook.view.CommandView;
import phonebook.view.search.LinearSearcher;

import java.util.List;

public class Controller {
    private final CommandView view = new CommandView();
    private final ListLoader loader = new ListLoader();
    private final LinearSearcher linearSearcher = new LinearSearcher();
    private final Timer timer = new Timer();

    public void start(){
        performLinearSearch();
    }

    public void performLinearSearch(){
        List<String> searchList = loader.loadSearchList();
        List<String> targetList = loader.loadTargetList();

        timer.start();
        view.showStartSearching();
        int foundEntries = linearSearcher.search(searchList, targetList);
        long timeTaken = timer.stop();

        view.showSearchResult(foundEntries, targetList.size(), timeTaken);
    }
}
