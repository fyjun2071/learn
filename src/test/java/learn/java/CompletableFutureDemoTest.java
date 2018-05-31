package learn.java;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemoTest {

    @Test
    public void findPrices() {
        long start = System.currentTimeMillis();
        List<String> list = new CompletableFutureDemo().findPrices("test");
        long end = System.currentTimeMillis();
        System.out.println(list);
        System.out.println(end - start);
    }

    @Test
    public void findPricesAsync() {
        long start = System.currentTimeMillis();
        List<String> list = new CompletableFutureDemo().findPricesAsync("test");
        long end = System.currentTimeMillis();
        System.out.println(list);
        System.out.println(end - start);
    }

    @Test
    public void findPricesStreamAccept() {
        long start = System.currentTimeMillis();
        new CompletableFutureDemo().findPricesStreamAccept("test");
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
