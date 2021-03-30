package kh.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

}
