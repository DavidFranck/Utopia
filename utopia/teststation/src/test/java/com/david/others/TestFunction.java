import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestFunction {
    @Test
    public void testPredicate() {
        ImmutableList<Integer> of = ImmutableList.of(1, 2, 3, 4, 5, 6);
        eval(of, integer -> integer > 3);
    }

    @Test
    public void testFunction() {
        Integer transfer = transfer(o -> o + 1, 1);
        System.out.println(transfer);
    }

    @Test
    public void testSupplier() {
        String generate = generate(() -> "hahaha");
        System.out.println(generate);
    }

    public static <out> out generate(Supplier<out> supplier) {
        return supplier.get();
    }

    public static <in, out> out transfer(Function<in, out> function, in in) {
        out out = function.apply(in);
        return out;
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(integer -> {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        });
    }
}
