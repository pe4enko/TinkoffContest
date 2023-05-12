package kirya;

import java.util.Objects;
import proit.Str;

@FunctionalInterface
public interface Inter1 {
    String doWork();
//    String doWork1();

    private int asd() {
        int a = 1 ;
        return Objects.hash(a);
    }
}
