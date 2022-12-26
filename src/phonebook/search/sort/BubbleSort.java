package phonebook.search.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BubbleSort {
    public <T> List<T> sort(List<T> list, Comparator<T> comparator) throws InterruptedException {
        list = new ArrayList<>(list);
        int size = list.size();
        for(int i=0; i<size-1; i++){
            for(int j=1; j<size-i; j++) {
                if (comparator.compare(list.get(j - 1), list.get(j)) > 0) {
                    swap(list, j - 1, j);
                }
            }
            if(Thread.interrupted()) throw new InterruptedException();
        }
        return list;
    }

    private static <T> void swap(List<T> list, int index1, int index2){
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
