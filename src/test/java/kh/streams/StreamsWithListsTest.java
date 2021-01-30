package kh.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StreamsWithListsTest {

    private static final List<Integer> LIST1 = Arrays.asList(1, 3, 4, 5, 7, 3, 5, 7, 2);
    private static final List<Integer> EXPECTED_RESULT1 = Arrays.asList(4, 2);
    
    
    @Test
    public void testFilterListEvenValues() {
        
        List<Integer> result = new StreamsWithLists().filterListEvenValues(LIST1);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(EXPECTED_RESULT1));
    }
}
