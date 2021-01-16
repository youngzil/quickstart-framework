//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: '()'
//输出: true
// 
//
// 示例 2: 
//
// 输入: '()[]{}'
//输出: true
// 
//
// 示例 3: 
//
// 输入: '(]'
//输出: false
// 
//
// 示例 4: 
//
// 输入: '([)]'
//输出: false
// 
//
// 示例 5: 
//
// 输入: '{[]}'
//输出: true 
// Related Topics 栈 字符串 
// 👍 2030 👎 0

package org.quickstart.code.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        String str = "([)]";
        System.out.println(solution.isValid(str));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {

            if (s.length() == 0 || s.length() % 2 == 1) {
                return false;
            }

            Map<Character, Character> pairs = new HashMap<>();
            pairs.put('(', ')');
            pairs.put('{', '}');
            pairs.put('[', ']');
            pairs.put('?', '?');

            if (!pairs.containsKey(s.charAt(0))) {
                return false;
            }

            Stack<Character> stack = new Stack<>();
            stack.push('?');
            for (char c : s.toCharArray()) {
                if (pairs.containsKey(c)) {
                    stack.push(c);
                } else if (pairs.get(stack.pop()) != c) {
                    return false;
                }
            }

            return stack.size() == 1;

        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution2 {
        public boolean isValid(String s) {

            Set<Character> offerSet = new HashSet<>();
            offerSet.add('(');
            offerSet.add('{');
            offerSet.add('[');

            Set<Character> pollSet = new HashSet<>();
            pollSet.add(')');
            pollSet.add('}');
            pollSet.add(']');

            if (!offerSet.contains(s.charAt(0)) || pollSet.contains(s)) {
                return false;
            }

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (offerSet.contains(s.charAt(i))) {
                    stack.push(s.charAt(i));
                } else if (pollSet.contains(s.charAt(i))) {
                    if (stack.isEmpty() || !isPairs(stack.pop(), s.charAt(i))) {
                        return false;
                    }
                }
            }

            return stack.isEmpty();

        }

        private boolean isPairs(char leftSymbol, char rightSymbol) {
            switch (leftSymbol) {
                case '(':
                    return rightSymbol == ')';
                case '{':
                    return rightSymbol == '}';
                case '[':
                    return rightSymbol == ']';
                default:
                    return false;
            }
        }
    }
}