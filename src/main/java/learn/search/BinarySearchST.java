package learn.search;

/**
 * 二叉查找符号表
 */
public class BinarySearchST<K extends Comparable<K>, V> {

    private K[] keys;   // 键
    private V[] vals;   // 值
    private int N;      // 表大小

    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public V get(K key) {
        if (isEmpty()) {
            return null;
        }

        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    public void put(K key, V val) {
        // 查找键，找到则更新，否则新建元素
        int i = rank(key);

        // 找到键
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return ;
        }

        // 添加键值对
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    private int rank(K key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return lo;
    }

    public static void main(String[] args) {
        BinarySearchST<Integer, Integer> st = new BinarySearchST<>(10);
        st.put(3, 3);
        st.put(7, 3);
        st.put(4, 3);
        st.put(5, 3);
        st.put(2, 3);
        System.out.println(st.get(1));
    }

}
