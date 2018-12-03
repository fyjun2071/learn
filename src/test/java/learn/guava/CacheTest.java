package learn.guava;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CacheTest {

    @Test
    public void expired() throws InterruptedException {
        Cache<String, String> cache = new Cache<>(2, TimeUnit.SECONDS);
        cache.put("1", "aaa");
        System.out.println(cache.getCache().getIfPresent("1"));
        Thread.sleep(3000);
        System.out.println(cache.getCache().size());
        System.out.println(cache.getCache().getIfPresent("1"));
        System.out.println(cache.getCache().size());
    }

}