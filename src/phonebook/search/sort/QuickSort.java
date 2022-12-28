package phonebook.search.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSort {
    public <T> List<T> sort(List<T> list, Comparator<T> comparator){
        list = new ArrayList<>(list);

        partition(list, 0, list.size()-1, list.size()-1, comparator);

        return list;
    }

    private static <T> void partition(List<T> list, int left, int right, int pivot, Comparator<T> comparator){
        if(left>=right) return;

        T pivotVal = list.get(pivot);
        int i=left, j=right, k=left;
        while(k<=j){
            int result = comparator.compare(list.get(k), pivotVal);
            if(result < 0){
                if(k==i){
                    k++;
                    i++;
                } else{
                    swap(list, k, i++);
                }
            } else if(result > 0){
                swap(list, k, j--);
            } else{
                k++;
            }
        }
        partition(list, left, i-1, i-1, comparator);
        partition(list, j+1, right, right, comparator);
    }

    private static <T> void swap(List<T> list, int index1, int index2){
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
