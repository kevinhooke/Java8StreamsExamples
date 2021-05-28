package kh.streams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
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
     * Sort type using a comparator using 2 fields
     * @param persons
     * @return List of Person sorted by lastName then firstName
     */
    public List<Person> sortTypeWithTwoComparators(List<Person> persons){
                
        return persons.stream()
                .sorted(
                        Comparator.comparing(Person::getLastName)
                            .thenComparing(Person::getFirstName)
                        )
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
    
    /**
     * Finds combinations of pairs of values in a list. Note that [1,2] and [2,1] are considered
     * the same and we're only looking for combinations, not permutations (not all possible combinations
     * where order is relevant).
     * 
     * 
     * 
     * e.g. given [1,2,3] returns:
     * [1,2], [1,3], [2,3]
     * @return
     */
    //TODO: refactor to use Streams
    public List<List<Integer>> findCombinationsOfPairsInList(List<Integer> values){
        List<List<Integer>> result = new ArrayList<>();
        
            for(int startingInt = 0; startingInt < values.size(); startingInt++) {

                for(int secondValue = startingInt + 1 ; secondValue < values.size(); secondValue++) {
                    List<Integer> tempOneComboList = new ArrayList<>();
                    tempOneComboList.add(values.get(startingInt));
                    tempOneComboList.add(values.get(secondValue));
                    result.add(tempOneComboList);
                }
                
            }
        
        return result;
    }
    
    /**
     * Given a list of list of ints, find combinations in each of the lists.
     * 
     * Given: [ [1,2,3], [4,5,6], [7,8,9] ]
     * Returns:
     * [ [[1,2], [1,3], [2,3]], [[4,5], [4,6], [5,6]], [[7,8], [7,9], [8,9]] ]
     * @param list of list of ints
     * @return list of list of lists
     */
    public List<List<List<Integer>>> findCombinationsOfPairsInListOfLists(List<List<Integer>> values){
        List<List<List<Integer>>> result = new ArrayList<>();
        
        values.forEach(listOfInts -> {
            List<List<Integer>> combosInList = this.findCombinationsOfPairsInList(listOfInts);
            result.add(combosInList);
        });
        
        return result;
    }
    
    /**
     * Given a list of list of ints, find all the indexes of the lists that contains the passed list.
     * 
     */
    public List<Integer> findIndexesOfListsThatContainList(List<Integer> listToFind, List<List<Integer>> list){
        
        List<List<Integer>> listsContainingInt = list.stream()
                .filter(e -> e.containsAll(listToFind))
                .collect(Collectors.toList());
        
        List<Integer> listIndexes = new ArrayList<>();
        
        //get indexes where each list exists
        for(List<Integer> listContainingValue : listsContainingInt) {
            listIndexes.add(list.indexOf(listContainingValue));
        }
        return listIndexes;
    }
    
    /**
     * Find indexes of lists that contain a list.
     * 
     * Given a list of list of ints like: [ [1,2,3], [4,1,2], [7,8,9] ]
     * finds the indexes of where each pair of values exists in each of the other lists.
     * 
     * For example:
     * Pair [1,2] exists in list indexes 0 and 1, returned as a list [0, 1]
     * Pair [2,3] only exists in [0]
     * Similar for all other pairs, then only exist in one of the lists.
     *  
     * @param values
     * @return map of pairs with a list of the indexes of the lists where that pair exists,
     * for example:
     * [1,2] : [0, 1] // the pair [1,2] exists in list 0 and list 1
     * [2,3] : [0]
     * [4,1] : [1]
     * [4,2] : [1]
     * etc
     */
    public Map<List<Integer>, List<Integer>> findListsContainingPairs(List<List<Integer>> values){
        Map<List<Integer>, List<Integer>> result = new HashMap<>();
        
        
        // Given: [ [1,2,3], [4,5,6], [7,8,9] ]
        List<List<List<Integer>>> listOfCombinations = this.findCombinationsOfPairsInListOfLists(values);
        //Returns: [
        //           [[1,2], [1,3], [2,3]], 
        //           [[4,5], [4,6], [5,6]],
        //           [[7,8], [7,9], [8,9]]
        //         ]

        
        listOfCombinations.stream()
            .forEach(listOfPairs -> listOfPairs.forEach(pairInList -> {
                
                List<Integer> listsContaining = findIndexesOfListsThatContainList(pairInList, values);
                List<Integer> listsContainingSoFar = result.get(pairInList);
                if(listsContainingSoFar == null) {
                    //add new entry for this list
                    result.put(pairInList, listsContaining);                    
                    //TODO continue here
                }
                else {
                    //this list is already in the list, add the next set of indexes were it exists
                    listsContainingSoFar.addAll(listsContaining);
                    result.put(pairInList, listsContainingSoFar);
                }
            }));
        
        
        return result;
    }
}
