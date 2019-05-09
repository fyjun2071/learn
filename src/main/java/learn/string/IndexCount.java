package learn.string;

import java.util.Arrays;

/**
 * 索引计数法给字符串分组
 */
public class IndexCount {

    public static void group(Node[] a, int R) {
        int N = a.length;
        Node[] aux = new Node[N];
        int[] count = new int[R + 1];

        int r;
        // 计算频率
        for (int i = 0; i < N; i++) {
            r = a[i].key();
            count[r]++;
        }

        // 将频率转换为索引
        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }

        // 将元素分类
        for (int i = 0; i < N; i++) {
            r = a[i].key() - 1;
            aux[count[r]++] = a[i];
        }

        // 回写
        for (int i = 0; i < N; i++) {
            a[i] = aux[i];
        }
    }

    static class Node {
        private Object value;
        private int key;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public int key() {
            return key;
        }

        @Override
        public String toString() {
            return key + "-" + value;
        }
    }

    public static void main(String[] args) {
        Node[] a = new Node[]{new Node(2, "Anderson"),
                              new Node(3, "Brown"),
                              new Node(3, "Davis"),
                              new Node(4, "Garcia"),
                              new Node(1, "Harris"),
                              new Node(3, "Jackson"),
                              new Node(3, "Jones"),
                              new Node(1, "Martin"),
                              new Node(2, "Martinez"),
                              new Node(2, "Miller"),
                              new Node(1, "Moore"),
                              new Node(2, "Robinson"),
                              new Node(4, "Smith"),
                              new Node(3, "Taylor"),
                              new Node(4, "Thomas"),
                              new Node(4, "Thompson"),
                              new Node(2, "White"),
                              new Node(3, "Williams"),
                              new Node(4, "Wilson")
                              };

        group(a, 4);
        System.out.println(Arrays.toString(a));
    }

}
