package kh.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class StreamsWithListsTest {

    private static final List<Integer> LIST1 = Arrays.asList(1, 3, 4, 5, 7, 3, 5, 7, 2);
    private static final List<Integer> EXPECTED_RESULT1 = Arrays.asList(4, 2);
    
    private static final List<List<Integer>> NESTED_LIST1 = Arrays.asList( Arrays.asList(1, 2),
            Arrays.asList(3, 4), Arrays.asList(5, 6), Arrays.asList(4, 7));
    private static final List<Integer> EXPECTED_RESULT2 = Arrays.asList(1, 2, 3, 4, 5, 6, 3, 4);
    
    @Test
    public void testFilterListEvenValues() {
        
        List<Integer> result = new StreamsWithLists().filterListEvenValues(LIST1);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(EXPECTED_RESULT1));
    }

    @Test
    public void testGetMinValue() {
        
        Integer result = new StreamsWithLists().getMinValue(LIST1);
        assertEquals(1, result.intValue());
    }

    @Test
    public void testGetMaxValue() {
        
        Integer result = new StreamsWithLists().getMaxValue(LIST1);
        assertEquals(7, result.intValue());
    }

    @Test
    public void testSortIntegerList() {
        List<Integer> result = new StreamsWithLists().sortIntegerList(LIST1);
        assertEquals(1, result.get(0).intValue());
        assertEquals(2, result.get(1).intValue());
        assertEquals(3, result.get(2).intValue());
        assertEquals(3, result.get(3).intValue());
        assertEquals(4, result.get(4).intValue());
    }
    
    @Test
    public void testSortTypeWithTwoComparators() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("bbbb", "cccc"));
        persons.add(new Person("bbbb", "aaaa"));
        persons.add(new Person("aaaa", "dddd"));
        persons.add(new Person("bbbb", "cccc"));
        
        List<Person> result = new StreamsWithLists().sortTypeWithTwoComparators(persons);
        Person person = result.get(0);
        assertTrue(person.getFirstName().equals("bbbb") && person.getLastName().equals("aaaa"));
    }
    
    @Test
    public void testCountOccurrencesInList() {
        
        Map<Integer, Long> result = new StreamsWithLists().countOccurrencesInList(LIST1);
        assertEquals(1, result.get(1).longValue());
        assertEquals(1, result.get(2).longValue());
        assertEquals(2, result.get(3).longValue());
        assertEquals(1, result.get(4).longValue());
        assertEquals(2, result.get(5).longValue());
        assertEquals(null, result.get(6));
    }
    
    @Test
    public void testflatMapNestedLists(){
        List<Integer> result = new StreamsWithLists().flatMapNestedLists(NESTED_LIST1);
        assertTrue(result.containsAll(EXPECTED_RESULT2));
        assertEquals(1, result.get(0).intValue());
        assertEquals(6, result.get(5).intValue());
    }
    
    
    @Test
    public void testFindIndexOfListWhereValueExistsWithIntList() {
        List<Integer> result = new StreamsWithLists().findIndexOfListWhereValueExistsWithIntList(4, NESTED_LIST1);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(3, result.get(1).intValue());
    }
    
    @Test
    public void testFindIndexOfListWhereValueExists_notInLists() {
        List<Integer> result = new StreamsWithLists().findIndexOfListWhereValueExistsWithIntList(8, NESTED_LIST1);
        assertEquals(0, result.size());
    }
    
    @Test
    public void testFindFirstListWhereValueExists_1() {
        List<Integer> result = new StreamsWithLists().findFirstListWhereValueExists(1, NESTED_LIST1);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList(1,2)));
    }

    @Test
    public void testFindFirstListWhereValueExists_4() {
        List<Integer> result = new StreamsWithLists().findFirstListWhereValueExists(4, NESTED_LIST1);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList(3,4)));
    }
    
    @Test
    public void testFindIndexOfFirstListWhereValueExists(){
       int result = new StreamsWithLists().findIndexOfFirstListWhereValueExists(4, NESTED_LIST1);
       assertEquals(1, result);
    }

    @Test
    public void testFindIndexesOfListsWhereValueExists_1() {
        List<Integer> result = new StreamsWithLists().findIndexesOfListsWhereValueExists(1, NESTED_LIST1);
        assertEquals(1, result.size());
        assertEquals(0, result.get(0).intValue());
    }

    @Test
    public void testFindIndexesOfListsWhereValueExists_4() {
        List<Integer> result = new StreamsWithLists().findIndexesOfListsWhereValueExists(4, NESTED_LIST1);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(3, result.get(1).intValue());
    }
    
    /**
     * [ [1, 2], [3, 4], [5, 6], [4, 7] ]
     */
    @Test
    public void testFindIndexesOfListWhereEachIntExists_1() {
        Map<Integer, List<Integer>> results = new StreamsWithLists().findIndexesOfListWhereEachIntExists(NESTED_LIST1);
        
        //check positions for 1
        List<Integer> results1 = results.get(1);
        assertEquals(1, results1.size());
        assertTrue(results1.containsAll(Arrays.asList(0)));
        
        //check positions for 3
        List<Integer> results3 = results.get(3);
        assertEquals(1, results3.size());
        assertTrue(results3.containsAll(Arrays.asList(1)));

        //check positions for 4
        List<Integer> results4 = results.get(4);
        assertEquals(2, results4.size());
        assertTrue(results4.containsAll(Arrays.asList(1, 3)));
        
        //negative test
        assertFalse(results4.containsAll(Arrays.asList(0, 2)));
        assertFalse(results4.containsAll(Arrays.asList(1, 2)));
        assertFalse(results4.containsAll(Arrays.asList(0, 3)));

    }
    
    /**
     * Given input list of [1,2,3], expected combinations are:
     * [1,2]
     * [1,3]
     * [2,3]
     */
    @Test
    public void testfindCombinationsOfPairsInList() {
        List<Integer> test = Arrays.asList(1,2,3);
        List<List<Integer>> results = new StreamsWithLists().findCombinationsOfPairsInList(test);
        assertEquals(3, results.size());
        
        results.forEach(list1 -> System.out.println(list1));
        assertEquals(Arrays.asList(1,2),results.get(0));
        assertEquals(Arrays.asList(1,3),results.get(1));
        assertEquals(Arrays.asList(2,3),results.get(2));
    }

    /**
     * Given input list of [1,2], expected combinations are:
     * [1,2]
     */
    @Test
    public void testfindCombinationsOfPairsInList_listLength2() {
        List<Integer> test = Arrays.asList(1,2);
        List<List<Integer>> results = new StreamsWithLists().findCombinationsOfPairsInList(test);
        assertEquals(1, results.size());
        
        results.forEach(list1 -> System.out.println(list1));
        assertEquals(Arrays.asList(1,2),results.get(0));
    }
    
    /**
     * Given input list of [1,2,3,4], expected combinations are:
     * [1,2]
     * [1,3]
     * [1,4]
     * [2,3]
     * [2,4]
     * [3,4]
     */
    @Test
    public void testfindCombinationsOfPairsInList_2() {
        List<Integer> test = Arrays.asList(1,2,3,4);
        List<List<Integer>> results = new StreamsWithLists().findCombinationsOfPairsInList(test);
        assertEquals(6, results.size());
        
        results.forEach(list1 -> System.out.println(list1));
        assertEquals(Arrays.asList(1,2),results.get(0));
        assertEquals(Arrays.asList(1,3),results.get(1));
        assertEquals(Arrays.asList(1,4),results.get(2));
        assertEquals(Arrays.asList(2,3),results.get(3));
        assertEquals(Arrays.asList(2,4),results.get(4));
        assertEquals(Arrays.asList(3,4),results.get(5));
    }
    
    @Test
    public void testFindCombinationsOfPairsInListOfLists() {
        List<List<Integer>> values = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,5,6),
                Arrays.asList(7,8,9));
        
        List<List<List<Integer>>> results = new StreamsWithLists().findCombinationsOfPairsInListOfLists(values);

        results.forEach(list -> System.out.println(list));
        
        //test outer list
        assertEquals(3, results.size());
        
        //test inner lists of combos
        assertEquals(Arrays.asList( Arrays.asList(1,2), Arrays.asList(1,3), Arrays.asList(2,3)), results.get(0));
        assertEquals(Arrays.asList( Arrays.asList(4,5), Arrays.asList(4,6), Arrays.asList(5,6)), results.get(1));
        assertEquals(Arrays.asList( Arrays.asList(7,8), Arrays.asList(7,9), Arrays.asList(8,9)), results.get(2));
    }
    
    @Test
    public void testFindCombinationsOfPairsInListOfLists_2() {
        List<List<Integer>> values = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,1,2),
                Arrays.asList(7,8,9));
        
        List<List<List<Integer>>> results = new StreamsWithLists().findCombinationsOfPairsInListOfLists(values);

        results.forEach(list -> System.out.println(list));
        
        //test outer list
        assertEquals(3, results.size());
        
        //test inner lists of combos
        assertEquals(Arrays.asList( Arrays.asList(1,2), Arrays.asList(1,3), Arrays.asList(2,3)), results.get(0));
        assertEquals(Arrays.asList( Arrays.asList(4,1), Arrays.asList(4,2), Arrays.asList(1,2)), results.get(1));
        assertEquals(Arrays.asList( Arrays.asList(7,8), Arrays.asList(7,9), Arrays.asList(8,9)), results.get(2));
    }
    
    /**
     * Given [ [1,2,3], [4,1,2], [7,8.9] ]
     * and list to find = [1,2]
     * 
     * Expected result: [0, 1] = exists in list 0 and 1
     */
    @Test
    public void testFindIndexesOfListsThatContainList() {
        
        List<Integer> listToFind = Arrays.asList(1,2);
        
        List<List<Integer>> values = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,1,2),
                Arrays.asList(7,8,9));
        
        
        List<Integer> result = new StreamsWithLists().findIndexesOfListsThatContainList_v1(listToFind, values);
        
        assertEquals(2, result.size());
        assertEquals(Integer.valueOf(0), result.get(0));
        assertEquals(Integer.valueOf(1), result.get(1));
    }

    /**
     * FindIndexesOfListsThatContainList_v1() doesn't work where lists repeat multiple times.
     * 
     * See FindIndexesOfListsThatContainList_v2() instead.
     */
    @Test
    public void testFindIndexesOfListsThatContainList_v1_withDuplicates() {
        
        List<Integer> listToFind = Arrays.asList(1,2);
        
        List<List<Integer>> values = Arrays.asList(
                Arrays.asList(9),
                Arrays.asList(1,2),
                Arrays.asList(1,2),
                Arrays.asList(7,8,9));
        
        
        List<Integer> result = new StreamsWithLists().findIndexesOfListsThatContainList_v1(listToFind, values);
        
        assertEquals(2, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
    }

    /**
     * This approach works, the v1 approach doesn't work because it only finds the first
     * occurrence and not all occurrences.
     */
    @Test
    public void testFindIndexesOfListsThatContainList_v2_withDuplicates() {
        
        List<Integer> listToFind = Arrays.asList(1,2);
        
        List<List<Integer>> values = Arrays.asList(
                Arrays.asList(9),
                Arrays.asList(1,2),
                Arrays.asList(1,2),
                Arrays.asList(7,8,9));
        
        
        List<Integer> result = new StreamsWithLists().findIndexesOfListsThatContainList_v2(listToFind, values);
        
        assertEquals(2, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
    }

    
    @Test
    public void testFindIndexesOfListsThatContainList_compareApproaches() {
        
        List<Integer> listToFind = Arrays.asList(1,2);
        
        List<List<Integer>> values = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,1,2),
                Arrays.asList(7,8,9));
        
        
        List<Integer> result = new StreamsWithLists().findIndexesOfListsThatContainList_v1(listToFind, values);
        
        assertEquals(2, result.size());
        assertEquals(Integer.valueOf(0), result.get(0));
        assertEquals(Integer.valueOf(1), result.get(1));

        List<Integer> result2 = new StreamsWithLists().findIndexesOfListsThatContainList_v2(listToFind, values);
        
        assertEquals(2, result2.size());
        assertEquals(Integer.valueOf(0), result2.get(0));
        assertEquals(Integer.valueOf(1), result2.get(1));

    }
    
    /**
     * Given [ [1,2,3], [4,1,2], [7,8.9] ]
     * and list to find = [1,4]
     * 
     * Expected result: [1] = exists only in list 1
     */
    @Test
    public void testFindIndexesOfListsThatContainList_2() {
        
        List<Integer> listToFind = Arrays.asList(1,4);
        
        List<List<Integer>> values = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,1,2),
                Arrays.asList(7,8,9));
        
        
        List<Integer> result = new StreamsWithLists().findIndexesOfListsThatContainList_v1(listToFind, values);
        
        assertEquals(1, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
    }

    /**
     * Given [ [1,2,3], [4,1,2], [7,8.9] ]
     * and list to find = [1,9]
     * 
     * Expected result: [] = this pair does not exist in any of the lists
     */
    @Test
    public void testFindIndexesOfListsThatContainList_3() {
        
        List<Integer> listToFind = Arrays.asList(1,9);
        
        List<List<Integer>> values = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,1,2),
                Arrays.asList(7,8,9));
        
        
        List<Integer> result = new StreamsWithLists().findIndexesOfListsThatContainList_v1(listToFind, values);
        
        assertEquals(0, result.size());
    }
    

    /**
     * Sample list of lists:
     * [[4], [1, 6], [1, 6], [1, 2, 5], [1, 2, 5, 6, 7], [2, 5, 6, 7], [9], [3], [8]]
     */
    @Test
    public void testFindListsContainingPairs() {

        List<List<Integer>> test = new ArrayList<>();
        test.add(Arrays.asList(4));
        test.add(Arrays.asList(1,6));
        test.add(Arrays.asList(1,6));
        test.add(Arrays.asList(1,2,5));
        test.add(Arrays.asList(1,2,5,6,7));
        test.add(Arrays.asList(2,5,6,7));
        test.add(Arrays.asList(9));
        test.add(Arrays.asList(3));
        test.add(Arrays.asList(8));
        
        Map<List<Integer>, List<Integer>> result = new StreamsWithLists().findListsContainingPairs(test);
        
        assertNotNull(result);
        //pair 1,6 is in lists 1, 2 and 4
        assertEquals(3, result.get(Arrays.asList(1,6)).size());
        assertEquals(Arrays.asList(1,2,4), result.get(Arrays.asList(1,6)));

        //pair 1,2 is in lists 3 and 4
        assertEquals(2, result.get(Arrays.asList(1,2)).size());
        assertEquals(Arrays.asList(3,4), result.get(Arrays.asList(1,2)));

        
    }


}
