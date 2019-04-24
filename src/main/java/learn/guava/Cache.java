package learn.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

import java.util.concurrent.TimeUnit;

public class Cache<K, V> {

    private LoadingCache<K, V> cache;

    public Cache() {
        cache = CacheBuilder.newBuilder()
                            .maximumSize(10000)
                            .build(new CacheLoader<K, V>() {
                                @Override
                                public V load(K k) throws Exception {
                                    return loadData(k);
                                }
                            });
    }

    /**
     * 超时缓存：数据写入缓存超过一定时间自动刷新
     *
     * @param duration
     * @param timeUtil
     */
    public Cache(long duration, TimeUnit timeUtil) {
        cache = CacheBuilder.newBuilder()
                            .expireAfterWrite(duration, timeUtil)
                            .build(new CacheLoader<K, V>() {
                                @Override
                                public V load(K k) throws Exception {
                                    return loadData(k);
                                }
                            });
    }

    /**
     * 限容缓存：缓存数据个数不能超过maxSize
     *
     * @param maxSize
     */
    public Cache(long maxSize) {
        cache = CacheBuilder.newBuilder()
                            .maximumSize(maxSize)
                            .build(new CacheLoader<K, V>() {
                                @Override
                                public V load(K k) throws Exception {
                                    return loadData(k);
                                }
                            });
    }

    /**
     * 权重缓存：缓存数据权重和不能超过maxWeight
     *
     * @param maxWeight
     * @param weigher：权重函数类，需要实现计算元素权重的函数
     */
    public Cache(long maxWeight, Weigher<K, V> weigher) {
        cache = CacheBuilder.newBuilder()
                            .maximumWeight(maxWeight)
                            .weigher(weigher)
                            .build(new CacheLoader<K, V>() {
                                @Override
                                public V load(K k) throws Exception {
                                    return loadData(k);
                                }
                            });
    }

    /**
     * 缓存数据加载方法
     *
     * @param k
     * @return
     * @author coshaho
     */
    protected V loadData(K k) {
        System.out.println("获取缓存失败: key=" + k);
        return null;
    }

    /**
     * 从缓存获取数据
     *
     * @param param
     * @return
     * @author coshaho
     */
    public V getCache(K param) {
        return cache.getUnchecked(param);
    }

    /**
     * 清除缓存数据，缓存清除后，数据会重新调用load方法获取
     *
     * @param k
     * @author coshaho
     */
    public void refresh(K k) {
        cache.refresh(k);
    }

    /**
     * 主动设置缓存数据
     *
     * @param k
     * @param v
     * @author coshaho
     */
    public void put(K k, V v) {
        cache.put(k, v);
    }

    public LoadingCache<K, V> getCache() {
        return cache;
    }

}
