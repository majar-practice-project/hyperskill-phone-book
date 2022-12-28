package phonebook.search;

import java.util.Comparator;
import java.util.List;

public class BinarySearch {
    public <T> int search(List<T> list, List<T> targets, Comparator<T> comparator){
        return (int) targets.stream()
                .filter(target -> binarySearch(list, target, comparator))
                .count();
    }

    private static <T> boolean binarySearch(List<T> list, T target, Comparator<T> comparator){
        int left = 0, right = list.size()-1;

        while(left<right){
            int mid = left + ((right-left)>>1);
            int result = comparator.compare(list.get(mid), target);
            if(result < 0){
                left = mid+1;
            } else if(result > 0){
                right = mid-1;
            } else{
                return true;
            }
        }

        return list.get(left).equals(target);
    }
}
