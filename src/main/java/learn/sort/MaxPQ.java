package learn.sort;

/**
 * 优先队列
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;   // 基于堆的完全二叉树，第i个元素的两子节点为2*i，2*i+1
    private int n = 0;  // 存储于[1...n]中，pq[0]没有使用

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public void insert(Key v) {
        pq[++n] = v;
        swim(n);
    }

    public Key delMax() {
        Key max = pq[1];      // 从根节点得到最大元素
        exch(1, n--);       // 将其和最后一个节点交换
        pq[n + 1] = null;      // 防止越界
        sink(1);            // 恢复堆的有序性
        return max;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }

            if (!less(k, j)) {
                break;
            }

            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        MaxPQ<Integer> maxPQ = new MaxPQ<>(n);
        for (int i = 0; i < n; i++) {
            maxPQ.insert(i);
        }

        while (!maxPQ.isEmpty()) {
            System.out.println(maxPQ.delMax());
        }
    }

}
