package learn.string;

/**
 * 三向单词查找树符号表
 */
public class TST<V> {

    private Node root;

    private class Node {
        char c;                 // 字符
        Node left,mid, right;   // 左中右子三向单词查找树
        V val;                  // 和字符串相关联的值
    }

    public V get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }

        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }

    public void put(String key, V val) {
        if (root == null) {
            root = new Node();
            root.c = key.charAt(0);
        }
        put(root, key, val, 0);
    }

    private Node put(Node x, String key, V val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }

        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            x.val = val;
        }

        return x;
    }

    public static void main(String[] args) {
        TST<Integer> st = new TST<>();
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


    }

}
