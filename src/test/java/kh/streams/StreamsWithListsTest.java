package kh.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class StreamsWithListsTest {

    private static final List<Integer> LIST1 = Arrays.asList(1, 3, 4, 5, 7, 3, 5, 7, 2);
    private static final List<Integer> EXPECTED_RESULT1 = Arrays.asList(4, 2);
    
    private static final List<List<Integer>> NESTED_LIST1 = Arrays.asList( Arrays.asList(1, 2),
            Arrays.asList(3, 4), Arrays.asList(5, 6), Arrays.asList(3, 4));
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
    public void testFindIndexOfListWhereValueExists() {
        List<Integer> result = new StreamsWithLists().findIndexOfListWhereValueExists(3, NESTED_LIST1);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(3, result.get(1).intValue());
    }
    
    @Test
    public void testFindIndexOfListWhereValueExists_notInLists() {
        List<Integer> result = new StreamsWithLists().findIndexOfListWhereValueExists(7, NESTED_LIST1);
        assertEquals(0, result.size());
    }
}
