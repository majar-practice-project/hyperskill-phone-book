package phonebook.view.search;

import java.util.List;

public class LinearSearcher {
    public int search(List<String> searchList, List<String> target){
        // I'm too lazy ;)
        try {
            Thread.sleep(7000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (int) target.stream()
                .filter(searchList::contains)
                .count();
    }
}
