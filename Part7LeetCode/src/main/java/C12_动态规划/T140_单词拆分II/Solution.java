/***********************************************************
 * @Description : 140.单词拆分II
 * https://leetcode-cn.com/problems/word-break-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T140_单词拆分II;

import java.util.ArrayList;
import java.util.List;

/**
 * Word Break II
 * 动规， 时间复杂度O(n^2)， 空间复杂度O(n^2)
 */
public class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        // 长度为n的字符串有n+1个隔板
        boolean[] memo = new boolean[s.length() + 1];
        // prev[i][j]为true， 表示s[j, i)是一个合法单词， 可以从j处切开
        // 第一行未用
        boolean[][] prev = new boolean[s.length() + 1][s.length()];
        memo[0] = true;
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if (memo[j] && wordDict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    prev[i][j] = true;
                }
            }
        }
        List<String> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        gen_path(s, prev, s.length(), path, result);
        return result;
    }

    /**
     * DFS遍历树， 生成路径
     */
    private static void gen_path(String s, boolean[][] prev, int cur, List<String> path, List<String> result) {
        if (cur == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = path.size() - 1; i >= 0; --i) {
                sb.append(path.get(i)).append(' ');
            }
            sb.deleteCharAt(sb.length() - 1);
            result.add(sb.toString());
        }
        for (int i = 0; i < s.length(); ++i) {
            if (prev[cur][i]) {
                path.add(s.substring(i, cur));
                gen_path(s, prev, i, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}
