//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串 
// 👍 1377 👎 0

package org.quickstart.code.leetcode.editor.cn;

class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
        String[] strs = new String[] {"flower", "flow", "flight"};
        String prefix = solution.longestCommonPrefix(strs);
        System.out.println(prefix);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }
            String prefix = strs[0];
            for (int i = 1; i < strs.length; i++) {
                int j = 0;
                for (; j < prefix.length() && j < strs[i].length(); j++) {
                    if (prefix.charAt(j) != strs[i].charAt(j)) {
                        break;
                    }
                }
                prefix = prefix.substring(0, j);
                if ("".equals(prefix)) {
                    return "";
                }
            }
            return prefix;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        public String longestCommonPrefix(String[] strs) {

            String prefix = "";
            if (strs.length == 0) {
                return prefix;
            }

            if (strs.length == 1) {
                return strs[0];
            }

            boolean flag = true;
            for (int i = 0; i < strs[0].length(); i++) {
                String tempPrefix = strs[0].substring(0, i + 1);

                for (int j = 1; j < strs.length; j++) {
                    if (!strs[j].startsWith(tempPrefix)) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) {
                    break;
                }

                prefix = tempPrefix;
            }
            return prefix;

        }
    }

}