package kh.streams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsWithLists {

    /**
     * Filters a list and returns only even values.
     * 
     * @param list
     * @return
     */
    public List<Integer> filterListEvenValues(List<Integer> list){
        
        return list.stream()
                .filter(item -> item % 2 == 0)
                .collect(Collectors.toList());
    }
    
    /**
     * Finds the max value in a list.
     * 
     * @param list
     * @return
     */
    public Integer getMaxValue(List<Integer> list){
        
        return list.stream()
                .max(Integer::compare).get();
    }

    /**
     * Finds the min value in a list.
     * @param list
     * @return
     */
    public Integer getMinValue(List<Integer> list){
        
        return list.stream()
                .min(Integer::compare).get();
    }

    /**
     * Sorts a list of Integers in natural order.
     * 
     * @param list
     * @return
     */
    public List<Integer> sortIntegerList(List<Integer> list){
        
        return list.stream()
                .sorted() // sorts by natural order
                .collect(Collectors.toList());
    }

    
    
    /**
     * Counts occurrences of each element in the list using Collectors.groupingBy()
     * to group by each input element and count each group.
     * 
     * @param list
     * @return Map of counts for each input element
     */
    public Map<Integer, Long> countOccurrencesInList(List<Integer> list){
        
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Takes a nested list of lists and flatmaps the result to a single list.
     * 
     * @param list
     * @return
     */
    public List<Integer> flatMapNestedLists(List<List<Integer>> list){
        
        return list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
    
    /**
     * Given a list of of ints, find the lists where a digit exists.
     * 
     * Iterates over a sequential list of integers representing the list index.
     */
    public List<Integer> findIndexOfListWhereValueExistsWithIntList(int value, List<List<Integer>> list){
        return IntStream.range(0, list.size())
                .filter(i -> list.get(i)
                        .contains(Integer.valueOf(value)))
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
    }
    
    /**
     * Given a list of list of ints, find the first list where a digit exists.
     * 
     * See https://stackoverflow.com/questions/45455368/check-if-value-exists-in-listlistobjects-in-java
     * 
     * @return List that contains the matching integer
     */
    public List<Integer> findFirstListWhereValueExists(int value, List<List<Integer>> list){
        
        return list.stream()
                .filter(e -> e.contains(Integer.valueOf(value)))
                .findFirst()
                .get();
    }

    /**
     * Given a list of list of ints, find the first index of the list where each value in a list of ints exists
     */
    public int findIndexOfFirstListWhereValueExists(int value, List<List<Integer>> list){
        
        List<Integer> listContainingInt = list.stream()
                .filter(e -> e.contains(Integer.valueOf(value)))
                .findFirst()
                .get();
        
        int index = list.indexOf(listContainingInt);
        return index;
    }

    /**
     * Given a list of list of ints, find all the indexes of the lists where the passed value exists
     */
    public List<Integer> findIndexesOfListsWhereValueExists(int value, List<List<Integer>> list){
        
        List<List<Integer>> listsContainingInt = list.stream()
                .filter(e -> e.contains(Integer.valueOf(value)))
                .collect(Collectors.toList());
        
        List<Integer> listIndexes = new ArrayList<>();
        
        //get indexes where each list exists
        for(List<Integer> listContainingValue : listsContainingInt) {
            listIndexes.add(list.indexOf(listContainingValue));
        }
        return listIndexes;
    }

    /**
     * For each value in a List of List of ints, find index of the list where the int exists.
     * 
     * For example, given [ [1,2,3], [3,4,5], [3,6,7] ] 
     * 1 exists in list 0
     * 2 exists in list 0
     * 3 exists in 0, 1, 2
     * 4 exists in 1
     * etc
     * 
     */
    public Map<Integer, List<Integer>> findIndexesOfListWhereEachIntExists(List<List<Integer>> list){
        
        Map<Integer, List<Integer>> results = new HashMap<>();
        
        //get list of all values
        List<Integer> values = list.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        
        //for each of the values, get list index within list where it exists
        values.forEach(
                
                value -> {
                    List<Integer> listsContainingValue = findIndexesOfListsWhereValueExists(value, list);
                    if(results.get(value) == null) {
                        results.put(value, listsContainingValue);
                    }
                }
        );
        
        return results;
    }
}
