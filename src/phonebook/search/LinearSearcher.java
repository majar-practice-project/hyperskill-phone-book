package phonebook.search;

import java.util.List;

public class LinearSearcher {
    public int search(List<String> searchList, List<String> target){
        return (int) target.stream()
                .filter(searchList::contains)
                .count();
    }
}
