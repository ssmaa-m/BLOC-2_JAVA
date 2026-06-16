package main;

import java.util.function.Consumer;

public class ConsumerForEach  implements Consumer<String> {

    @Override
    public void accept(String s){
        System.out.println(s);
    }
}
