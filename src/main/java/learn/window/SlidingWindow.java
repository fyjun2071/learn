package learn.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlidingWindow {
    /**
     * 没有重复字符的子字符的最大长度：给一个字符串，获得没有重复字符的最长子字符的长度
     * 例子：
     * 输入："abcabcbb"
     * 输出：3
     * 解释：因为没有重复字符的子字符是'abc'，所以长度是3
     */

    /**
     * 滑动窗口算法o(2n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {//窗口的左边是i，右边是j，下列算法将窗口的左右移动，截取出其中一段
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){//如果set中不存在该字母，就将j+1，相当于窗口右边向右移动一格，左边不动
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);//记录目前存在过的最大的子字符长度
            }
            else {//如果set中存在该字母，则将窗口左边向右移动一格，右边不动，直到该窗口中不存在重复的字符
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 优化的滑动窗口算法o(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        //使用hashmap记录遍历过的字符的索引，当发现重复的字符时，可以将窗口的左边直接跳到该重复字符的索引处
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {//j负责向右边遍历，i根据重复字符的情况进行调整
            if (map.containsKey(s.charAt(j))) {//当发现重复的字符时,将字符的索引与窗口的左边进行对比，将窗口的左边直接跳到该重复字符的索引处
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //记录子字符串的最大的长度
            ans = Math.max(ans, j - i + 1);
            //map记录第一次遍历到key时的索引位置，j+1,保证i跳到不包含重复字母的位置
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

}
