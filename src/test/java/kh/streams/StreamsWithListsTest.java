package kh.streams;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StreamsWithListsTest {

    private static final List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 3, 5, 7, 2);
    
    @Test
    public void testFilterListEvenValues() {
        
        List<Integer> result = new StreamsWithLists().filterListEvenValues(list1);
        
        //TODO assert
        
        
        
    }
}
