package phonebook.search;

import java.util.Comparator;
import java.util.List;

public class JumpSearcher {
    public <T> int search(List<T> list, List<T> targets, Comparator<T> comparator){
        return (int) targets.stream()
                .filter(t -> jumpSearch(list, t, comparator))
                .count();
    }

    private static <T> boolean jumpSearch(List<T> list, T target, Comparator<T> comparator){
        int length = list.size();
        int step = (int)Math.sqrt(length);

        int prev = -1;

        for(int i=step; i<length; i = Math.min(i+step, length-1)){
            if(comparator.compare(list.get(i), target) >= 0){
                for(int j=i; j>prev; j--){
                    if(comparator.compare(list.get(j), target)==0) return true;
                }
                break;
            }
            if(i == length-1) break;
            prev = i;
        }

        return false;
    }
}
