package kh.streams;

import java.util.List;
import java.util.stream.Collectors;

public class StreamsWithLists {

    public List<Integer> filterListEvenValues(List<Integer> list){
        
        return list.stream()
                .filter(item -> item % 2 == 0)
                .collect(Collectors.toList());
    }
}
