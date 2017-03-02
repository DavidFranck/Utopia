import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


/**
 * Created by David
 * on 2017/1/6
 */
public class TestStation {
    @Test
    public void testSum(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        int sum = integers.stream().mapToInt(Integer::byteValue).sum();
        System.out.println(sum);
        System.out.println(integers.stream().count());
    }
}
