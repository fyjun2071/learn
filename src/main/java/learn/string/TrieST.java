package learn.string;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 单词查找树
 */
public class TrieST<V> {

    private static int R = 256;
    private Node root = new Node();

    public V get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (V) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public Node put(String key, V val) {
        return put(root, key, val, 0);
    }

    private Node put(Node x, String key, V val, int d) {
        if (x == null) {
            x = new Node();
        }

        if (d == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new ArrayDeque<>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) {
            return ;
        }

        if (x.val != null) {
            q.add(pre);
        }

        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public static void main(String[] args) {
        TrieST<Integer> st = new TrieST<>();
        String[] a = new String[]{"she",
                                  "sells",
                                  "seashells",
                                  "by",
                                  "the",
                                  "seashore",
                                  "the",
                                  "shells",
                                  "she",
                                  "sells",
                                  "are",
                                  "surely",
                                  "seashells"
        };

        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }

        Iterable<String> keys = st.keysWithPrefix("sh");
        keys.forEach(System.out::println);
        System.out.println("=========================================");
        keys = st.keys();
        keys.forEach(System.out::println);
    }
}
