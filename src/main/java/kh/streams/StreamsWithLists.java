package kh.streams;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsWithLists {

    public List<Integer> filterListEvenValues(List<Integer> list){
        
        return list.stream()
                .filter(item -> item % 2 == 0)
                .collect(Collectors.toList());
    }
    
    public Integer getMaxValue(List<Integer> list){
        
        return list.stream()
                .max(Integer::compare).get();
    }

    public Integer getMinValue(List<Integer> list){
        
        return list.stream()
                .min(Integer::compare).get();
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
     * Given a list of list of ints, find the first index of tge list where each value in a list of ints exists
     */
    public int findIndexOfFirstListWhereValueExists(int value, List<List<Integer>> list){
        
        List<Integer> listContainingInt = list.stream()
                .filter(e -> e.contains(Integer.valueOf(value)))
                .findFirst()
                .get();
        
        int index = list.indexOf(listContainingInt);
        return index;
    }

}
