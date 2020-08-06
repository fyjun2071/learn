package learn.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class ForkJoinPoolTest {

    @Test
    public void test() {
        int n = 0x8000;
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        List<CompletableFuture> l = new ArrayList<>();
        list.parallelStream().forEach(i -> {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return i;
            });
            l.add(future);
        });

        for (CompletableFuture c : l) {
            try {
                System.out.println(c.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end.....");
    }

    @Test
    public void test2() {
        IntStream.range(0, 10000).parallel().forEach((x) -> {
            System.out.println("x:" + x);
            IntStream.range(0, 10000).parallel().forEach((y) -> {
                System.out.println("x:" + x + "--" + "y:" + y);
                IntStream.range(0, 10000).parallel().forEach((z) -> {
                    System.out.println("x:" + x + "--" + "y:" + y + "--" + "z:" + z);
                    // do some business logic which use jni and cost cpu much ,mean rtt is about 400ms and sometimes upper99 could reach 10s.
                    try {
                        Thread.sleep(1000 * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
        });
    }

}
