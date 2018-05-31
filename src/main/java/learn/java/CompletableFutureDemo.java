package learn.java;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CompletableFutureDemo {

    private List<Shop> shops = Arrays.asList(new Shop("a"),
                                             new Shop("b"),
                                             new Shop("c"),
                                             new Shop("d"),
                                             new Shop("e"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void randomDelay() {
        try {
            int delay = 500 + new Random().nextInt(2000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> findPrices(String product) {
        return shops.parallelStream()
                    .map(shop -> shop.getPrice(product))
                    .map(Quote::parse)
                    .map(Discount::applyDiscount)
                    .collect(toList());
    }

    public List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                     .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                     .map(future -> future.thenApply(Quote::parse))
                     .map(future -> future.thenCompose(
                             quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                     .collect(toList());

        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(toList());
    }

    public void findPricesStreamAccept(String product) {
        long start = System.currentTimeMillis();

        CompletableFuture[] futures = findPricesStream("test")
                .map(f -> f.thenAccept(s -> System.out.println(
                        s + " done in " +
                        (System.currentTimeMillis() - start))))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
    }

    private Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                    .map(future -> future.thenApply(Quote::parse))
                    .map(future -> future.thenCompose(
                            quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }

}

class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }


    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product) {
        CompletableFutureDemo.randomDelay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getName() {
        return name;
    }

}

class Discount {
    public static String applyDiscount(Quote quote) {
        return String.format("%s price is %.2f", quote.getShopName(), apply(quote.getPrice(), quote.getCode()));
    }

    private static double apply(double price, Code code) {
        CompletableFutureDemo.randomDelay();
        return price * (100 - code.percentage) / 100;
    }

    public enum Code {
        NONE(0), SLIVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }
}

class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code code;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.code = code;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code code = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, code);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }
}