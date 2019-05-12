package learn.string;

import learn.sort.MinPQ;

/**
 * 霍夫曼编码
 */
public class Huffman {

    private static class Node implements Comparable<Node> {
        private char ch;
        private int freq;
        private final Node left;
        private final Node right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }

    /**
     * 构建霍夫曼编码单词查找树
     * @param freq
     * @return
     */
    private static Node buildTrie(int[] freq) {
        MinPQ<Node> pq = new MinPQ<>();
        for (char c = 0; c < freq.length; c++) {
            if (freq[c] > 0) {
                pq.insert(new Node(c, freq[c], null, null));
            }
        }

        while (pq.size() > 1) {
            // 合并两棵最小子树
            Node l = pq.delMin();
            Node r = pq.delMin();
            Node parent = new Node('\0', l.freq + r.freq, l, r);
            pq.insert(parent);
        }

        return pq.delMin();
    }
}
