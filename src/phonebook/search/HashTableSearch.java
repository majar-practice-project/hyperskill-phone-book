package phonebook.search;

import java.util.List;
import java.util.Set;

public class HashTableSearch {
    public <T> int search(Set<T> set, List<T> targets){
        return (int) targets.stream()
                .filter(set::contains)
                .count();
    }
}