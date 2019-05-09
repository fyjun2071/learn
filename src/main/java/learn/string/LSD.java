package learn.string;

import java.util.Arrays;

/**
 * 低位优先的字符串排序
 */
public class LSD {

    public static void sort(String[] a, int W) {
        // 通过前W个字符串排序
        int N = a.length;
        int R = 256;

        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            int r;
            // 计算频率
            for (int i = 0; i < N; i++) {
                r = a[i].charAt(d);
                count[r]++;
            }

            // 将频率转换为索引
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }

            // 将元素分类
            for (int i = 0; i < N; i++) {
                r = a[i].charAt(d) - 1;
                aux[count[r]++] = a[i];
            }

            // 回写
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] a = new String[]{"4PGC839",
                                  "2IYE230",
                                  "3CIO720",
                                  "1ICK750",
                                  "1OHV845",
                                  "4JZY524",
                                  "1ICK750",
                                  "3CIO720",
                                  "1OHV845",
                                  "1OHV845",
                                  "2RLA629",
                                  "2RLA629",
                                  "3ATW723"
                                  };
        sort(a, 7);
        Arrays.stream(a).forEach(System.out::println);
    }

}
