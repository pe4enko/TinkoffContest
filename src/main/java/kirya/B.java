package kirya;

import java.util.HashMap;

public class B implements Inter{

    @Override
    public Integer doWork() {

        HashMap<byte[], String> a = new HashMap<>();

        HelloWorld helloWorld = new HelloWorld(3) {
            @Override
            public Integer doWork() {
                return super.doWork();
            }
        };

        return null;
    }
}
