package learn.sort;

/**
 * 优先队列，小顶堆
 * @param <Key>
 */
public class MinPQ<Key extends Comparable<Key>> {

    private Key[] pq;    // 队列
    private int N;      // 队列大小

    public MinPQ() {
        pq = (Key[]) new Comparable[]{};
    }

    public MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private boolean great(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }

    public Key delMin() {
        Key min = pq[1];
        exch(1, N--);
        sink(1);
        return min;
    }

    private void swim(int k) {
        while (k / 2 > 1 && great( k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (great(2 * k, 2 * k + 1)) {
                j++;
            }

            if (!great(k, j)) {
                break;
            }

            exch(k, j);
            k = j;
        }
    }
}
