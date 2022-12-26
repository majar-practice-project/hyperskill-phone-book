package phonebook.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListLoader {
//    private final String FILE_DIR = "../test.txt";
    private final String FILE_DIR = "../directory.txt";
    private final String FIND_DIR = "../find.txt";

    public List<String> loadSearchList(){
        List<String> numbers = new ArrayList<>();
        List<String> persons = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(FILE_DIR))){
            while(scanner.hasNext()){
                numbers.add(scanner.next());
                persons.add(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public List<String> loadTargetList(){
        List<String> targetList = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(FIND_DIR))){
            while(scanner.hasNext()){
                targetList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return targetList;
    }
}
