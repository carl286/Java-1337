package com.l1337.l301;


import java.util.*;
import java.util.stream.Collectors;


//Summary
//First do it in an extremely naive way, then improve it. Otherwise too much stuff mix together is terrible...
public class Solution {

    void removeInvalidParenthesesHelper(List<String> ret, String s, int i, int accLeft, int leftRemaining, int rightRemaining, char [] buffer, int index, int matchesSoFar, int maxPossibleMatch) {

        //reject invalid one first
        //accLeft + leftRemaining + matchesSoFar < maxPossibleMatch <----- so that it could reach max possible
        //(accLeft > rightRemaining) <---- make sure it can be matched.
        if (accLeft + leftRemaining + matchesSoFar < maxPossibleMatch || (accLeft > rightRemaining))
            return;
        if (i >= s.length()) {
            //at this point, leftRemaining, rightRemaining should be 0 now.
            if (matchesSoFar == maxPossibleMatch) {
                char [] tmp = new char [index];
                System.arraycopy(buffer, 0, tmp, 0, index);
                ret.add(new String(tmp));
            }
            return;
        }

//        if (matchesSoFar == maxPossibleMatch) {
//            while (i < s.length()) {
//                if (s.charAt(i) != ')' && s.charAt(i) != '(')
//                    buffer[index++] = s.charAt(i);
//                ++i;
//            }
//            //call func to add the result
//            removeInvalidParenthesesHelper(ret, s, i, accLeft, leftRemaining, rightRemaining, buffer, index, matchesSoFar, maxPossibleMatch);
//            return;
//        }

        //how do it selectively
            while (i < s.length() && s.charAt(i) != ')' && s.charAt(i) != '(')
                buffer[index++] = s.charAt(i++);

            if (i < s.length()) {

                if (s.charAt(i) == '(') {
                    //take it
                    buffer[index] = '(';
                    removeInvalidParenthesesHelper(ret, s, i+1, accLeft+1, leftRemaining-1, rightRemaining, buffer, index+1, matchesSoFar, maxPossibleMatch);

                    //how to not take it?

                    //How to skip???
                    while (i + 1 < s.length() && s.charAt(i+1) == '(') {
                        ++i;
                        --leftRemaining;
                    }
                    removeInvalidParenthesesHelper(ret, s, i+1, accLeft, leftRemaining-1, rightRemaining, buffer, index, matchesSoFar, maxPossibleMatch);
                } else {
//                s.charAt(i) == ')'
                    //take it?
                    if (accLeft > 0) {
                        buffer[index] = ')';
                        removeInvalidParenthesesHelper(ret, s, i+1, accLeft-1, leftRemaining, rightRemaining-1, buffer, index+1, matchesSoFar+1, maxPossibleMatch);
                    }

                    while (i + 1 < s.length() && s.charAt(i+1) == ')') {
                        ++i;
                        --rightRemaining;
                    }
                    removeInvalidParenthesesHelper(ret, s, i+1, accLeft, leftRemaining, rightRemaining-1, buffer, index, matchesSoFar, maxPossibleMatch);
                }
            } else {
                //terminate
                removeInvalidParenthesesHelper(ret, s, i, accLeft, leftRemaining, rightRemaining, buffer, index, matchesSoFar, maxPossibleMatch);
            }
    }

    List<String> removeInvalidParentheses(String s) {
        List<String> ret = new ArrayList<>();
        int f = s.indexOf('(');
        int e = s.lastIndexOf(')');

        int totalLeft = 0, totalRight = 0, matches = 0;
        int accLeft = 0;
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '(':
                    ++totalLeft;
                    ++accLeft;
                    break;
                case ')':
                    ++totalRight;
                    if (accLeft > 0) {
                        --accLeft;
                        ++matches;
                    }
                    break;
            }
        }

        //matches indicating the max match you can have in future.
        char [] buffer = new char[s.length()];
        removeInvalidParenthesesHelper(ret, s, 0, 0, totalLeft, totalRight, buffer, 0, 0, matches);

        return ret;
    }



    /*
    //		https://leetcode.com/discuss/67842/share-my-java-bfs-solution
//		The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ), check if they are valid, if found valid ones on the current level, put them to the final result list and we are done, otherwise, add them to a queue and carry on to the next level.
//		The good thing of using BFS is that we can guarantee the number of parentheses that need to be removed is minimal, also no recursion call is needed in BFS.
//		Thanks to @peisi, we don't need stack to check valid parentheses.
//		Time complexity: The idea is right
//		In BFS we handle the states level by level, in the worst case, we need to handle all the levels, we can analyze the time complexity level by level and add them up to get the final complexity.
//		On the first level, there's only one string which is the input string s, let's say the length of it is n, to check whether it's valid, we need O(n) time. On the second level, we remove one ( or ) from the first level, so there are C(n, n-1) new strings, each of them has n-1 characters, and for each string, we need to check whether it's valid or not, thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level, total time complexity is (n-2) x C(n, n-2), so on and so forth...
//		Finally we have this formula:
//		T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).

		/*
		List<String> res = new ArrayList<>();

		// sanity check
		if (s == null) return res;

		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();

		// initialize
		queue.add(s);
		visited.add(s);
		boolean found = false;

		while (!queue.isEmpty()) {
			s = queue.poll();

			if (isValidremoveInvalidParentheses(s)) {
				// found an answer, add to the result
				res.add(s);
				found = true;
			}
			//Why this wired check works?? STOP expand to next level as we can't shortest...
			if (found) continue;
			// generate all possible states
			for (int i = 0; i < s.length(); i++) {
				// we only try to remove left or right paren
				if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

				String t = s.substring(0, i) + s.substring(i + 1);

				if (!visited.contains(t)) {
					// for each state, if it's not visited, add it to the queue
					queue.add(t);
					visited.add(t);
				}
			}
		}

		return res;
		*/

    /*
    //With prune
    List<String> res = new ArrayList<>();

    // sanity check
    if (s == null) return res;

    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();

    // initialize
    queue.add(s);
    visited.add(s);
    boolean found = false;
    while (!queue.isEmpty()) {
        s = queue.poll();

        int mismatches = NumisValidremoveInvalidParentheses(s);
        if (mismatches == 0) {
            // found an answer, add to the result
            res.add(s);
            found = true;
        }
        //Why this wired check works?? STOP expand to next level as we can't shortest...
        if (found) continue;
        // generate all possible states
        for (int i = 0; i < s.length(); i++) {
            // we only try to remove left or right paren
            if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

            String t = s.substring(0, i) + s.substring(i + 1);
            int newMismatches = NumisValidremoveInvalidParentheses(t);

            if (!visited.contains(t) && ((mismatches >= 0 && newMismatches >= 0) || (mismatches <= 0 && newMismatches <= 0)) && (Math.abs(mismatches) >= Math.abs(newMismatches))) {
                // for each state, if it's not visited, add it to the queue
                queue.add(t);
                visited.add(t);
            }
        }
    }

    return res;
     */


    //		1. 预处理，删除头部的")"和尾部的“（”。
//		2. 每次删除一个character，需要判断删除后是否mis-match数减少或为0，如果减少加入candidate中进行进一步处理，如果为0则表示找到答案。需要处理完当前queue中的余下candidates，因为结果可能不止一个。
//		3. 用一个set可以避免处理重复元素。
//		深度优先搜索（DFS）+剪枝（Pruning）
//		利用评价函数计算字符串中未匹配括号的个数
//		尝试从输入字符串中移除括号，若得到的新字符串的失配括号比原字符串少，则继续搜索；
//		否则剪枝。
//http://bookshadow.com/weblog/2015/11/05/leetcode-remove-invalid-parentheses/
//		通过从输入字符串中移除每一个括号，生成新的字符串加入队列。
//		如果从队列中取出的字符串是有效的，则加入结果列表。
//		一旦发现有效的字符串，则不再向队列中补充新的可能字符串。
//		根据BFS的性质，当首次从队列中发现有效字符串时，其去掉的括号数一定是最小的。
//		而此时，队列中存在的元素与队头元素去掉的括号数的差值 ≤ 1
//		并且，只有与队头元素去掉括号数目相同的元素才有可能是候选答案（根据括号匹配的性质可知）。
//BFS 也可以剪纸
//	BFS	枚举去除的点，当找到后停止BFS树的扩展（因为要去除最少括号，所以即使有其他的结果，也一定在同一层）
//	DFS 统计左右括号能删的个数，进行DFS

//		https://leetcode.com/discuss/81478/easy-short-concise-and-fast-java-dfs-3-ms-solution

    //		Key Points:
//
//		Generate unique answer once and only once, do not rely on Set.
//		Do not need preprocess.
//		Runtime 3 ms.
//				Explanation: We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter. The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
//				To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.
//				After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order. For this, we keep tracking the last removal position and only remove ‘)’ after that.
//		Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘? The answer is: do the same from right to left. However a cleverer idea is: reverse the string and reuse the code! Here is the final implement in Java.
//		public List<String> removeInvalidParentheses(String s) {
//			List<String> ans = new ArrayList<>();
//			remove(s, ans, 0, 0, new char[]{'(', ')'});
//			return ans;
//		}
//
//		public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
//			for (int stack = 0, i = last_i; i < s.length(); ++i) {
//				if (s.charAt(i) == par[0]) stack++;
//				if (s.charAt(i) == par[1]) stack--;
//				if (stack >= 0) continue;
//				for (int j = last_j; j <= i; ++j)
//					if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
//						remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
//				return;
//			}
//			String reversed = new StringBuilder(s).reverse().toString();
//			if (par[0] == '(') // finished left to right
//				remove(reversed, ans, 0, 0, new char[]{')', '('});
//			else // finished right to left
//				ans.add(reversed);
//		}









































    private void helper(String s, int index, StringBuilder sb, Set<String> tmp, int [] lefts, int [] rights, int acc, int pairSoFar, int expectedPair)
    {
        if (index >= s.length())
        {
            if (acc == 0 && pairSoFar == expectedPair)
            {
                tmp.add(new String(sb));
            }

            return;
        }

        //no way to match reaming pairs
        if (acc > rights[index])
        {
            return;
        }

        if (s.charAt(index) == '(')
        {
            //take it
            if (pairSoFar < expectedPair)
            {
                sb.append(s.charAt(index));
                helper(s, index+1, sb, tmp, lefts, rights, acc+1, pairSoFar, expectedPair);
                sb.deleteCharAt(sb.length()-1);
            }

            //skip it.
            helper(s, index+1, sb, tmp, lefts, rights, acc, pairSoFar, expectedPair);
        }
        else if (s.charAt(index) == ')')
        {
            if (acc > 0)
            {
                sb.append(s.charAt(index));
                helper(s, index+1, sb, tmp, lefts, rights, acc-1, pairSoFar+1, expectedPair);
                sb.deleteCharAt(sb.length()-1);
            }
            helper(s, index+1, sb, tmp, lefts, rights, acc, pairSoFar, expectedPair);
        }
        else
        {
            sb.append(s.charAt(index));
            helper(s, index+1, sb, tmp, lefts, rights, acc, pairSoFar, expectedPair);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private int getMinimumPairCounts(String s)
    {
        int acc = 0;
        int left = 0;
        for(int i = 0; i < s.length(); ++i)
        {
            if (s.charAt(i) == '(')
            {
                ++left;
            }
            else if (s.charAt(i) == ')')
            {
                if (left > 0)
                {
                    --left;
                    ++acc;
                }
            }
        }

        return acc;
    }
    public List<String> removeInvalidParenthesesMay6_21(String s) {
        int n = s.length();
        int [] rights = new int [n];
        int [] lefts = new int [n];
        char [] data = s.toCharArray();
        int acc = 0;
        for(int i = n - 1; i >= 0; --i)
        {
            if (data[i] == ')')
                ++acc;
            rights[i] = acc;
        }
        acc = 0;
        for(int i = 0; i < n; ++i)
        {
            if (data[i] == '(')
                ++acc;
            lefts[i] = acc;
        }

        //get to know the minimum number of invalid parentheses to make the input string valid.
        int expectedPair = getMinimumPairCounts(s);

        Set<String> tmp = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        helper(s, 0, sb, tmp, lefts, rights, 0, 0, expectedPair);
        return tmp.stream().collect(Collectors.toList());
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        for (String ss : s.removeInvalidParentheses("(a)())()")) {
//            System.out.println(ss);
//        }
//        for (String ss : s.removeInvalidParenthesesMay6_21("()())")) {
        for (String ss : s.removeInvalidParenthesesMay6_21("()())")) {
            System.out.println(ss);
        }
    }
}
