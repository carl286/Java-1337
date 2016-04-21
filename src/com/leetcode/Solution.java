package com.leetcode;

import java.util.*;

public class Solution {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	//	https://leetcode.com/problems/two-sum/
	public static int[] twoSum(int[] numbers, int target) {
		int[] ret = new int[2];

//		Map<Integer, Integer> map = new java.util.Hashtable<Integer, Integer>();
		Map<Integer, Integer> map = new Hashtable<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(target - numbers[i])) {
				ret[0] = map.get(target - numbers[i]) + 1;
				ret[1] = i + 1;
				break;
			} else {
				map.put(numbers[i], i);
			}
		}

		return ret;
	}

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 * int val;
	 * ListNode next;
	 * ListNode(int x) {
	 * val = x;
	 * next = null;
	 * }
	 * }
	 */

//	https://leetcode.com/problems/add-two-numbers/
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		return helpAddTwoNumbers(l1, l2, 0);
	}

	public ListNode helpAddTwoNumbers(ListNode l1, ListNode l2, int carryon) {
		if (l1 == null && l2 == null) {
			if (carryon == 0)
				return null;
			else
				return new ListNode(carryon);
		}

		ListNode ret = new ListNode(carryon);
		if (l1 != null) {
			ret.val += l1.val;
			l1 = l1.next;
		}

		if (l2 != null) {
			ret.val += l2.val;
			l2 = l2.next;
		}

		if (ret.val >= 10) {
			ret.val -= 10;
			carryon = 1;
		} else {
			carryon = 0;
		}
		ret.next = helpAddTwoNumbers(l1, l2, carryon);
		return ret;
	}

	public int lengthOfLongestSubstring(String s) {
		int length = s.length();
		if (length <= 1)
			return length;

		int ret = 1, start = 0;
		Set<Character> set = new HashSet<Character>();
		set.add(s.charAt(0));
		for (int i = 1; i < length; i++) {
			if (set.contains(s.charAt(i))) {
				while (s.charAt(start) != s.charAt(i)) {
					set.remove(s.charAt(start));
					start++;
				}
				++start;
			} else {
				set.add(s.charAt(i));
			}
			if ((i - start + 1) > ret)
				ret = i - start + 1;
		}

		return ret;
	}

	public int countSignleBits(int num) {
		int count = 0;
		while (num > 0) {
			num &= (num - 1);
			count++;
		}
		return count;
	}

	public int[] countBits(int num) {
		if (num < 0)
			return null;

		int[] result = new int[num + 1];
		for (int i = 0; i <= num; i++)
			result[i] = countSignleBits(i);
		return result;
	}

	public int[] countBits2(int num) {
		if (num < 0)
			return null;

		int[] result = new int[num + 1];
		int end = 1;
		while (end <= num) {
			int start = end;
			int newEnd = end << 1;
			while (start <= num && start < newEnd) {
				result[start] = 1 + result[start - end];
				++start;
			}
			end = start;
		}
		return result;
	}

	//	https://leetcode.com/problems/house-robber/
//	198. House Robber
	public int robI(int[] nums) {
		if (nums == null)
			return 0;

		if (nums.length == 0)
			return 0;

		if (nums.length == 1)
			return nums[0];

		/*
		int c1 = nums[0], c2 = nums[1];
		int g1 = c1, g2 = Math.max(c1, c2);

		for (int i = 2; i < nums.length; i++) {
			if ((i & 0x01) == 0) {
				//even
				c1 = g1 + nums[i];
				g1 = Math.max(c1, g2);
			} else {
				c2 = g2 + nums[i];
				g2 = Math.max(g1, c2);
			}
		}

		return Math.max(g1, g2);
		*/
		int g1 = nums[0], g2 = Math.max(g1, nums[1]);
		for (int i = 2; i < nums.length; i++) {
			int g3 = Math.max(g1 + nums[i], g2);
			g1 = g2;
			g2 = g3;
		}
		return Math.max(g1, g2);
	}

	//	https://leetcode.com/problems/house-robber-ii/
	public int robII(int[] nums) {
		if (nums == null)
			return 0;

		if (nums.length <= 0)
			return 0;

		if (nums.length == 1)
			return nums[0];

		if (nums.length == 2)
			return Math.max(nums[0], nums[1]);

		//At least 3 elements in the array
		int ret = 0;
		int g1 = nums[0], g2 = Math.max(nums[0], nums[1]);

		for (int i = 2; i + 1 < nums.length; i++) {
			if ((i & 0x01) == 0) {
				g1 = Math.max(g1 + nums[i], g2);
			} else {
				g2 = Math.max(g1, g2 + nums[i]);
			}
		}

		ret = Math.max(g1, g2);

		g1 = nums[1];
		g2 = Math.max(nums[1], nums[2]);
		for (int i = 3; i < nums.length; i++) {
			if ((i & 0x01) != 0) {
				g1 = Math.max(g1 + nums[i], g2);
			} else {
				g2 = Math.max(g1, g2 + nums[i]);
			}
		}
		ret = Math.max(ret, Math.max(g1, g2));

		return ret;
	}


	private int robIIIHelper(TreeNode root, Map<TreeNode, Integer> map) {
		//base case
		if (root.left == null && root.right == null) {
			map.put(root, root.val);
			return root.val;
		}

		int maxSubLeft = 0, maxSubLeftII = 0;
		if (root.left != null) {
			if (!map.containsKey(root.left)) {
				maxSubLeft = robIIIHelper(root.left, map);
			} else {
				maxSubLeft = map.get(root.left);
			}

			//go to the third level
			if (root.left.left != null) {
				maxSubLeftII += map.get(root.left.left);
			}
			if (root.left.right != null) {
				maxSubLeftII += map.get(root.left.right);
			}
		}

		int maxSubRight = 0, maxSubRightII = 0;
		if (root.right != null) {
			if (!map.containsKey(root.right)) {
				maxSubRight = robIIIHelper(root.right, map);
			} else {
				maxSubRight = map.get(root.right);
			}
			//go to the third level
			if (root.right.left != null) {
				maxSubRightII += map.get(root.right.left);
			}
			if (root.right.right != null) {
				maxSubRightII += map.get(root.right.right);
			}
		}

		map.put(root, Math.max(root.val + maxSubLeftII + maxSubRightII, maxSubLeft + maxSubRight));

		return map.get(root);
	}

	//	https://leetcode.com/problems/house-robber-iii/
	public int robIII(TreeNode root) {
		if (root == null)
			return 0;

		Map map = new <TreeNode, Integer>HashMap();
		return robIIIHelper(root, map);
	}

	//	https://leetcode.com/problems/palindrome-pairs/
//	Too Many Edge Case for this problem
	public boolean isPalindrome(String s) {
		if (s.length() <= 1)
			return true;

		int i = 0, j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i) == s.charAt(j)) {
				++i;
				--j;
			} else {
				return false;
			}
		}

		return true;
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> result = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], i);
		}

		for (int i = 0; i < words.length; i++) {
			int len = words[i].length();
//			if (len == 0) {
//				for (int k = 0; k < words.length; k++) {
//					if (isPalindrome(words[k]) && i != k) {
//						List<Integer> sol = new ArrayList<>();
//						sol.add(k);
//						sol.add(i);
//						result.add(sol);
//
//						List<Integer> sol2 = new ArrayList<>();
//						sol2.clear();
//						sol2.add(i);
//						sol2.add(k);
//						result.add(sol2);
//					}
//				}
//			}
			for (int j = 0; j < len; j++) {
				if (isPalindrome(words[i].substring(0, j))) {
					String s2 = new StringBuilder(words[i].substring(j, len)).reverse().toString();
					if (j == 0 && isPalindrome(words[i]) && map.containsKey("")) {
						List<Integer> sol = new ArrayList<>();
						sol.add(map.get(""));
						sol.add(i);
						result.add(sol);
					} else if (map.containsKey(s2) && map.get(s2) != i) {
						List<Integer> sol = new ArrayList<>();
						sol.add(map.get(s2));
						sol.add(i);
						result.add(sol);
					}
				}

				if (isPalindrome(words[i].substring(j, len))) {
					String s2 = new StringBuilder(words[i].substring(0, j)).reverse().toString();
					//Exclude himself
					if (map.containsKey(s2) && map.get(s2) != i) {
						List<Integer> sol = new ArrayList<>();
						sol.add(i);
						sol.add(map.get(s2));
						result.add(sol);
					}
				}
			}
		}
		return result;
	}

	//	https://leetcode.com/problems/self-crossing/
	public boolean isSelfCrossing(int[] x) {
		int n = x.length;
		if (n < 4) return false;
		int t1 = 0, t2 = x[0], t3 = x[1], t4 = x[2], t5;
		boolean increase = t4 > t2 ? true : false;
		for (int i = 3; i < n; i++) {
			t5 = x[i];
			if (increase && t3 >= t5) {
				if (t5 + t1 < t3 || i + 1 < n && x[i + 1] + t2 < t4)
					increase = false;
				else if (i + 1 < n)
					return true;
			} else if (!increase && t3 <= t5)
				return true;
			t1 = t2;
			t2 = t3;
			t3 = t4;
			t4 = t5;
		}
		return false;
	}

	/*               i-2
    case 1 : i-1┌─┐
                └─┼─>i
                 i-3

                    i-2
    case 2 : i-1 ┌────┐
                 └─══>┘i-3
                 i  i-4      (i overlapped i-4)

    case 3 :    i-4
               ┌──┐
               │i<┼─┐
            i-3│ i-5│i-1
               └────┘
                i-2

	*/

	boolean isSelfCrossing2(int[] x) {
		for (int i = 3, l = x.length; i < l; i++) {
			// Case 1: current line crosses the line 3 steps ahead of it
			if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) return true;
				// Case 2: current line crosses the line 4 steps ahead of it
			else if (i >= 4 && x[i - 1] == x[i - 3] && x[i] + x[i - 4] >= x[i - 2]) return true;
				// Case 3: current line crosses the line 6 steps ahead of it
			else if (i >= 5 && x[i - 2] >= x[i - 4] && x[i] + x[i - 4] >= x[i - 2] && x[i - 1] <= x[i - 3] && x[i - 1] + x[i - 5] >= x[i - 3])
				return true;
		}
		return false;
	}

	//	https://leetcode.com/problems/increasing-triplet-subsequence/
	public boolean increasingTriplet(int[] nums) {
		if (nums == null || nums.length <= 2)
			return false;

		int i = 0;
		while (i + 1 < nums.length && nums[i] >= nums[i + 1])
			++i;
		int curMin = nums[i];
		++i;
		while (i + 1 < nums.length) {
			int mid = nums[i];
			while (i + 1 < nums.length && nums[i] >= nums[i + 1])
				++i;

			if (i + 1 < nums.length) {
				if (nums[i] > curMin)
					return true;
				else if (i + 1 < nums.length && nums[i + 1] > mid) {
					return true;
				} else {
					curMin = nums[i];
				}
			}
			++i;
		}
		return false;
	}

//	https://leetcode.com/problems/reconstruct-itinerary/
//	https://leetcode.com/discuss/84706/share-solution-java-greedy-stack-15ms-with-explanation]
//	https://en.wikipedia.org/wiki/Eulerian_path#Hierholzer.27s_algorithm
//public class Logger {
//	LinkedList<String> res;
//	Map<String, PriorityQueue<String>> mp;
//
//	public List<String> findItinerary(String[][] tickets) {
//		if (tickets==null || tickets.length==0) return new LinkedList<String>();
//		res = new LinkedList<String>();
//		mp = new HashMap<String, PriorityQueue<String>>();
//		for (String[] ticket : tickets) {
//			if (!mp.containsKey(ticket[0])) {
//				mp.put(ticket[0], new PriorityQueue<String>());
//			}
//			mp.get(ticket[0]).offer(ticket[1]);
//		}
//		dfs("JFK");
//		return res;
//	}
//
//	public void dfs(String cur) {
//		while (mp.containsKey(cur) && !mp.get(cur).isEmpty()) {
//			dfs(mp.get(cur).poll());
//		}
//		res.addFirst(cur);
//	}
//}
	/*
	public boolean findItineraryHelper(String start, HashMap<String, List<String>> map, Set<String> h, int total, List<String> ret) {
		if (ret.size() >= total)
			return true;

		List<String> list = map.get(start);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				String key = start + i;
				if (h.contains(key))
					continue;

				h.add(key);
				String next = list.get(i);
				ret.add(next);
				if (findItineraryHelper(next, map, h, total, ret)) {
					return true;
				} else {
					h.remove(key);
					ret.remove(ret.size()-1);
				}
			}
		}

		return false;
	}
	public List<String> findItinerary(String[][] tickets) {
		int total = tickets.length + 1;
		List<String> ret = new ArrayList<>(total);
		HashMap<String, List<String>> map = new HashMap<>();

		for (int i = 0; i < tickets.length; i++) {
			if (map.containsKey(tickets[i][0])) {
				map.get(tickets[i][0]).add(tickets[i][1]);
			} else {
				List<String> l = new ArrayList<>(Arrays.asList(tickets[i][1]));
				map.put(tickets[i][0], l);
			}
		}

		for (String key : map.keySet()) {
			Collections.sort(map.get(key));
//			System.out.println(key + ":");
//			System.out.println(map.get(key));
		}

		Set<String> h = new HashSet<>();
		String start = new String("JFK");
//		String start = new String("A");
		ret.add(start);
		if (findItineraryHelper(start, map, h, total, ret))
			return ret;
		else
			return null;
	}

	*/

//	https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
//	http://bookshadow.com/weblog/2016/02/01/leetcode-verify-preorder-serialization-binary-tree/
//解法II 统计树的出度（out-degree）和入度（in-degree）
//	这个方法简单的说就是不断的砍掉叶子节点。最后看看能不能全部砍掉。
//
//	已例子一为例，：”9,3,4,#,#,1,#,#,2,#,6,#,#” 遇到x # #的时候，就把它变为 #
//
//	我模拟一遍过程：
//
//			9,3,4,#,# => 9,3,# 继续读
//	9,3,#,1,#,# => 9,3,#,# => 9,# 继续读
//	9,#2,#,6,#,# => 9,#,2,#,# => 9,#,# => #
//	class  SerializationNode {
//		boolean isValid;
//		int subTreeSize;
//		SerializationNode(int subTreeSize, boolean isValid) {
//			this.isValid = isValid;
//			this.subTreeSize = subTreeSize;
//		}
//	}
//
//	public SerializationNode isValidSerializationHelper(int start, int length, String [] array) {
////		System.out.println("start:\t" + start + "\t" +length);
////		System.out.println("Start:\t"+array[start]+"\tEnd:"+"\t"+array[start+length-1]);
////		System.out.println("XXXXXXX");
//		//Char array is not working due to input can have 2 or more characters
//		//In Java, always use equals to check String
//		//Becareful that a character String representation, not '#'
//		SerializationNode ret = new SerializationNode(length, false);
//		if (length <= 0)
//			return ret;
//
//		if (array[start].equals("#")) {
//			ret.isValid = true;
//			ret.subTreeSize = 1;
//			return ret;
//		}
//
//		if (length == 1) {
//			return ret;
//		}
//
//		SerializationNode left = isValidSerializationHelper(start + 1, length - 1, array);
//		if (!left.isValid)
//			return ret;
//
//		SerializationNode right = isValidSerializationHelper(start + 1 + left.subTreeSize, length - 1 - left.subTreeSize, array);
//		if (!right.isValid)
//			return ret;
//
//		ret.subTreeSize = 1 + left.subTreeSize + right.subTreeSize;
//		ret.isValid = true;
//		return ret;
//	}
//	public boolean isValidSerialization(String preorder) {
//		String [] array = preorder.split(",");
//		for (String s : array)
//			System.out.println(s);
//
//		//Optimization
//		if (!array[array.length-1].equals("#"))
//			return false;
//		SerializationNode ret = isValidSerializationHelper(0, array.length, array);
//		return ret.isValid && (ret.subTreeSize == array.length);
//	}

//	https://leetcode.com/problems/patching-array/
//	https://leetcode.com/discuss/82822/solution-explanation
//	https://leetcode.com/discuss/82895/actually-patching
//	假设数组nums的“部分元素和”可以表示范围[1, total)内的所有数字，
//	那么向nums中添加元素add可以将表示范围扩充至[1, total + add)，其中add ≤ total，当且仅当add = total时取到范围上界[1, 2 * total)
//	若nums数组为空，则构造[1, n]的nums为[1, 2, 4, 8, ..., k]，k为小于等于n的2的幂的最大值。

	public int minPatches(int[] nums, int n) {
		if (nums == null || nums.length <= 0) {
			int k = 0;
			while (n > 0) {
				n >>= 1;
				k++;
			}
			return k;
		}

		int ret = 0;
		int curMax = 0;
		int i = 0;
		//Below code will fail for [1,2,31,33], 2147483647 because of overflow
//		while (curMax < n) {
//			if (i >= nums.length || nums[i] > curMax+1) {
//				ret++;
//				System.out.println(curMax);
//				System.out.println(curMax+1);
//				curMax = 2*curMax + 1;
//			} else {
//				curMax += nums[i];
//				++i;
//			}
//		}

		while (curMax < n) {
			int preMax = curMax;
			if (i >= nums.length || nums[i] - 1 > curMax) {
				ret++;
				curMax = 2 * curMax + 1;
			} else {
				curMax += nums[i];
				++i;
			}
			//Protector for int overflow/Use Long int
			if (preMax >= curMax)
				break;
		}

		return ret;
	}

	//	https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
//	Wired, local test pass, online failed at [[1]] case, saying my output is 1.
//	http://bookshadow.com/weblog/2016/01/20/leetcode-longest-increasing-path-matrix/
	public int longestIncreasingPathHelper(int i, int j, int[][] matrix, int[][] map) {
		//i,j are assumed to be valid index
		int maxSub = -1;
		int ret = 0;
		//up
		if (i > 0) {
			if (matrix[i - 1][j] > matrix[i][j]) {
				if (map[i - 1][j] < 0)
					ret = Math.max(longestIncreasingPathHelper(i - 1, j, matrix, map), ret);
				maxSub = Math.max(map[i - 1][j], maxSub);
			}
		}
		//down
		if (i != matrix.length - 1) {
			if (matrix[i + 1][j] > matrix[i][j]) {
				if (map[i + 1][j] < 0)
					ret = Math.max(longestIncreasingPathHelper(i + 1, j, matrix, map), ret);
				maxSub = Math.max(map[i + 1][j], maxSub);
			}
		}

		//left
		if (j > 0) {
			if (matrix[i][j - 1] > matrix[i][j]) {
				if (map[i][j - 1] < 0)
					ret = Math.max(longestIncreasingPathHelper(i, j - 1, matrix, map), ret);
				maxSub = Math.max(map[i][j - 1], maxSub);
			}
		}

		//right
		if (j != matrix[0].length - 1) {
			if (matrix[i][j + 1] > matrix[i][j]) {
				if (map[i][j + 1] < 0)
					ret = Math.max(longestIncreasingPathHelper(i, j + 1, matrix, map), ret);
				maxSub = Math.max(map[i][j + 1], maxSub);
			}
		}

		map[i][j] = 1 + maxSub;
		return Math.max(map[i][j], ret);
	}

	public int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int[][] map = new int[matrix.length][];
		for (int i = 0; i < map.length; i++) {
			map[i] = new int[matrix[0].length];
			Arrays.fill(map[i], -1);
		}

		int curMax = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (map[i][j] < 0) {
					curMax = Math.max(longestIncreasingPathHelper(i, j, matrix, map), curMax);
				}
			}
		}
		return curMax > 0 ? (1 + curMax) : 0;
	}

	//	https://leetcode.com/problems/odd-even-linked-list/
	public ListNode oddEvenList(ListNode head) {
		if (head == null)
			return head;

		ListNode oddTail = head;
		ListNode evenHead = new ListNode(0);
//		evenHead.next = null;
		ListNode evenTail = evenHead;

		ListNode cur = head.next;
		int counter = 2;
		while (cur != null) {
			ListNode next = cur.next;
			if ((counter & 0x01) != 0) {
				oddTail.next = cur;
				oddTail = cur;
			} else {
				cur.next = evenTail.next;
				evenTail.next = cur;
				evenTail = cur;
			}
			++counter;
			cur = next;
		}
		oddTail.next = evenHead.next;
		return head;
	}

	//	https://leetcode.com/problems/power-of-three/
//Could you do it without using any loop / recursion?
	public boolean isPowerOfThreeHelper(int n) {
		if (n == 1)
			return true;
		if (n % 3 != 0)
			return false;
		return isPowerOfThreeHelper(n / 3);
	}

	public boolean isPowerOfThree(int n) {
		//boy, -3 is not a power three,
//		n = Math.abs(n);
//		if (n == 0)
//			return false;

//		If you divide the number iteratively by 3, you will get 1 at the end.
//
//				class Logger {
//			public:
//			bool isPowerOfThree(int n) {
//				while (n && (n % 3 == 0)) n /= 3;
//				return n == 1;
//			}
//		};

//		Alternatively, you can do it from the other direction (multiplication by three each time). But be careful of integer overflow so you have to use unsigned type or a 64-bit integer type e.g. long long.
//		From above method, we know that the biggest signed-integer (32-bit) that can be divided by 3 is 1162261467. Then we can say the any power-of-three can be divided by this number.
//		 public boolean isPowerOfThree(int n) {
//		2     return n>0 && Math.pow(3, (int)(Math.log(0x7fffffff)/Math.log(3)))%n==0;
//		3 }
		if (n <= 0)
			return false;
		if (n == 1)
			return true;
		return isPowerOfThreeHelper(n);
	}

//	https://leetcode.com/problems/wiggle-sort-ii/
//	https://www.hrwhisper.me/leetcode-wiggle-sort-ii/
//	https://leetcode.com/discuss/77133/o-n-o-1-after-median-virtual-indexing
//	public void wiggleSort(int[] nums) {
	//v1
//		排序，然后两边分别取，复杂度O(nlogn)
//      Extreme case, 1,2,1,2,1, N(smaller)=N(large)+1, this is extreme
//		注意排完序之后应该倒着来。比如[4,5,5,6]这个 数据。
//		Arrays.sort(nums);
//		int[] temp = new int[nums.length];
//		int s = (nums.length + 1) >> 1, t = nums.length;
//		for (int i = 0; i < nums.length; i++) {
//			temp[i] = (i & 1) == 0 ?  nums[--s] : nums[--t] ;
//		}
//
//		for (int i = 0; i < nums.length; i++)
//			nums[i] = temp[i];


//		用快排的思想查找中位数，然后再合并两边。最坏复杂度O(n^2)，平均复杂度O(n)
//	}

//	void wiggleSort(vector<int>& nums) {
//		int n = nums.size();
//
//		// Find a median.
//		auto midptr = nums.begin() + n / 2;
//		nth_element(nums.begin(), midptr, nums.end());
//		int mid = *midptr;
//
//		// Index-rewiring.
//		#define A(i) nums[(1+2*(i)) % (n|1)]
//
//		// 3-way-partition-to-wiggly in O(n) time with O(1) space.
//		int i = 0, j = 0, k = n - 1;
//		while (j <= k) {
//			if (A(j) > mid)
//				swap(A(i++), A(j++));
//			else if (A(j) < mid)
//				swap(A(j), A(k--));
//			else
//				j++;
//		}
//	}

	public void wiggleSort(int[] nums) {
		int medium = findMedium(nums, 0, nums.length - 1, (nums.length + 1) >> 1);
		int s = 0, t = nums.length - 1, mid_index = (nums.length + 1) >> 1;
		int[] temp = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < medium)
				temp[s++] = nums[i];
			else if (nums[i] > medium)
				temp[t--] = nums[i];
		}

		while (s < mid_index) temp[s++] = medium;
		while (t >= mid_index) temp[t--] = medium;

		t = nums.length;
		for (int i = 0; i < nums.length; i++)
			nums[i] = (i & 1) == 0 ? temp[--s] : temp[--t];
	}

	private int findMedium(int[] nums, int L, int R, int k) {
		if (L >= R) return nums[R];
		int i = partition(nums, L, R);
		int cnt = i - L + 1;
		if (cnt == k) return nums[i];
		return cnt > k ? findMedium(nums, L, i - 1, k) : findMedium(nums, i + 1, R, k - cnt);
	}

	private int partition(int[] nums, int L, int R) {
		int val = nums[L];
		int i = L, j = R + 1;
		while (true) {
			while (++i < R && nums[i] < val) ;
			while (--j > L && nums[j] > val) ;
			if (i >= j) break;
			swap(nums, i, j);
		}
		swap(nums, L, j);
		return j;
	}

	private void swap(Integer[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

//	https://leetcode.com/problems/coin-change/
	/* Not correct
	public void coinChangeHelper(int [] map, int []coins, int length, int amount) {
		if (length <= 0 || amount <= 0)
			return;

		if (map[amount] == 0) {
			while (length > 0 &&  coins[length-1]>amount)
				--length;

			if (length > 0) {
				int c = 0;
				int k = amount / coins[length-1];
				int product = k * coins[length-1];
				if (product == amount) {
					c = k;
					--k;
					product -= coins[length-1];
				}
				while (k >= 0) {
					coinChangeHelper(map, coins, length-1, amount-product);
//					System.out.println("amount:" + (amount-product));
					if (map[amount-product] > 0) {
						c = c == 0 ? k + map[amount-product]: Math.min(c, k + map[amount-product]);
					}
					--k;
					product -= coins[length-1];
				}
				map[amount] = (c == 0) ? -1 : c;
			}
		}
	}
	*/

	public int coinChange(int[] coins, int amount) {
		if (coins == null || amount <= 0)
			return -1;
		if (amount == 0)
			return 0;
		//Time out, too slow
//		int [] map = new int [amount+1];
//		for (int i = 0; i < coins.length; i++) {
//			//Becareful about cases like [2], 1, amount is less than the data in array
//			if (coins[i] <= amount)
//				map[coins[i]] = 1;
//		}
//
//		if (map[1] == 0)
//			map[1] = -1;
//
//		for (int i = 2; i < map.length; i++) {
//			if (map[i] == 0) {
//				int mid = i / 2;
//				int c = 0;
//				for (int j = 1; j <= mid; j++) {
//					if (map[j] != -1 && map[i-j] != -1) {
//						if (c != 0)
//							c = Math.min(map[j]+map[i-j], c);
//						else
//							c = map[j]+map[i-j];
//					}
//				}
//				map[i] = (c == 0) ? -1 : c;
//			}
//		}
//		return map[amount];

		/* Wrong answer
		Arrays.sort(coins);
		int [] map = new int [amount+1];
		int i = 0;
		for (; i < coins.length && coins[i] <= amount; i++) {
				map[coins[i]] = 1;
		}

		coinChangeHelper(map, coins, i, amount);
		return map[amount] > 0 ? map[amount] : -1;
		*/
//		dp，设dp[i] 为兑换目标i最少的硬币数。
//		则有：dp[i + coins[j] ] = min(dp[i + coins[j] ] , dp[i] + 1）
		//Compare mine to see why previous one is bad....
		int dp[] = new int[amount + 1];
		final int INF = 0x7ffffffe;
		for (int i = 1; i <= amount; i++) dp[i] = INF;
		for (int i = 0; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (i + coins[j] <= amount)
					dp[i + coins[j]] = Math.min(dp[i + coins[j]], dp[i] + 1);
			}
		}
		return dp[amount] == INF ? -1 : dp[amount];

		//将问题转化为求X轴0点到坐标点amount的最短距离（每次向前行进的合法距离为coin的面值）
	}

//	https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/#introduction
//	http://www.cnblogs.com/zhangshu/archive/2011/08/16/2141396.html
//当 i 为奇数时，ci=ai ；当 i 为偶数时，就要看 i 的因子中最多有二的多少次幂，例如，6 的因子中有 2 的一次幂，等于 2 ，所以 c6=a5+a6（由六向前数两个数的和），4 的因子中有 2 的两次幂，等于 4 ，所以 c4=a1+a2+a3+a4（由四向前数四个数的和）。
//	有公式：cn=a(n-a^k+1)+.........+an（其中 k 为 n 的二进制表示中从右往左数的 0 的个数）。
//		Basically, in this problem, we use BIT to count the number of integers that are less than a specific number.
//		Suppose that a number N = A1B > 0 in binary representation, where B contains all 0 . The array tree is a BIT where tree[N] count the number of integers that are from A0B and A1B - 1 .
//We also know that A0B = N & (N-1) using bit manipulation. (NOTE: on the Topcoder, they use A0B= N - (N & -N) .
	//	https://www.hrwhisper.me/binary-indexed-tree-fenwick-tree/
////	https://leetcode.com/problems/count-of-range-sum/
////	https://leetcode.com/discuss/79083/share-my-solution

//	http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
//	http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
//	class countRangeSumSegmentNode {
//		int sum;
//		int left;
//		int right;
//		countRangeSumSegmentNode ln;
//		countRangeSumSegmentNode rn;
//		countRangeSumSegmentNode(int sum, int left, int right, countRangeSumSegmentNode ln, countRangeSumSegmentNode rn) {
//			this.sum = sum;
//			this.left = left;
//			this.right = right;
//			this.ln = ln;
//			this.rn = rn;
//		}
//	}
//	private countRangeSumSegmentNode countRangeSumSegmentTreeBuild(int left, int right) {
//		if (left > right)
//			return null;
//		countRangeSumSegmentNode ret = new countRangeSumSegmentNode(0, left, right, null, null);
//		int mid = (right - left) / 2 + left;
//		ret.ln = countRangeSumSegmentTreeBuild(left, mid);
//		ret.rn = countRangeSumSegmentTreeBuild(mid + 1, right);
//		return ret;
//	}
//
//	private void countRangeSumSegmentUpdate(countRangeSumSegmentNode root, int i, int val) {
//		if (root == null || i < root.left || i > root.right)
//			return;
//		root.sum += val;
//		countRangeSumSegmentUpdate(root.ln, i, val);
//		countRangeSumSegmentUpdate(root.rn, i, val);
//	}
//	private int countRangeSumSegmentQuery(countRangeSumSegmentNode root, int lower, int upper) {
//		if (root == null || lower < root.left || upper > root.right)
//			return 0;
//		if (lower <= root.left && root.right <= upper)
//			return root.sum;
//
//		return countRangeSumSegmentQuery(root.ln, lower, upper) + countRangeSumSegmentQuery(root.rn, lower, upper);
//	}

//	http://bookshadow.com/weblog/2016/01/11/leetcode-count-of-range-sum/

	class FenwickTree {
		int[] bitarray;

		FenwickTree(int maxN) {
			bitarray = new int[maxN + 1];
		}

		private int lowbit(int x) {
			return x & -x;
		}

		void add(int index, int val) {
			while (index < bitarray.length) {
				bitarray[index] += val;
				index += lowbit(index);
			}
		}

		int sum(int index) {
			int ret = 0;
			while (index > 0) {
				ret += bitarray[index];
				index -= lowbit(index);
			}
			return ret;
		}


	}

	//return index satisfies: nums[t] >= val, nums[t-1] < val;
	int leftMost(long[] nums, long val) {
		int left = -1, right = nums.length;
		while (left + 1 != right) {
			int mid = (right - left) / 2 + left;
			if (nums[mid] >= val)
				right = mid;
			else
				left = mid;
		}
		return right;
	}

	//return index satisfies: nums[t] <= val, nums[t+1] > val;
	int rightMost(long[] nums, long val) {
		int left = -1, right = nums.length;
		while (left + 1 != right) {
			int mid = (right - left) / 2 + left;
			if (nums[mid] > val)
				right = mid;
			else
				left = mid;
		}
		return left;
	}

	public int countRangeSum(int[] nums, int lower, int upper) {
		if (nums == null || nums.length <= 0)
			return 0;

		//TLE
		/*
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 0; i < nums.length; i++) {
			Iterator k = set.descendingIterator();
			//If you add to original set directly, negative value will make the loop broken seriously.
			TreeSet<Integer> set2 = new TreeSet<>();
			while (k.hasNext()) {
				set2.add((int)k.next() + nums[i]);
			}
			set.addAll(set2);
			set.add(nums[i]);
		}
		int ret = 0;
		Iterator k = set.iterator();
		boolean flag1 = false;
		while (k.hasNext()) {
			int tmp = (int) k.next();
			if (!flag1) {
				if (tmp >= lower)
					flag1 = true;
			}
			if (flag1) {
				if (tmp <= upper) {
					ret++;
				} else {
					break;
				}
			}

		}
		return ret;
		*/

//		Range Sum Query 2D - Mutable, 308
//		https://evanyang.gitbooks.io/leetcode/content/LeetCode/range_sum_query_2d_-_mutable.html
//		http://blog.csdn.net/u012175043/article/details/50093933


//		还记得 Count of Smaller Numbers After Self  么？
//		那时候，我们用Fenwick树或者线段树，先离散化，然后从右向左扫描，每扫描一个数，对小于它的求和。然后更新…..
//		这题也差不多，需要找满足条件 lower ≤ sum[j] – sum[i – 1] ≤ upper ，也就是lower + sum[i – 1] ≤ sum[j] ≤ upper + sum[i – 1]
//		我们同样的求出和，然后离散化，接着从右向左扫描，对每个i 查询满足在[ lower + sum[i – 1], upper + sum[i – 1] ]范围内的个数（用线段树或者Fenwick Tree）
//		这样复杂度就是O(nlogn)

//		http://bookshadow.com/weblog/2016/01/11/leetcode-count-of-range-sum/
//		预处理前n项和数组sums, 将sums数组离散化（排序+去重）得到数组osums,
//		遍历sums，记sumi = sums[i]
//		用二分查找得到[sumi - upper, sumi - lower]的离散化下标[left, right]
//		用树状数组统计范围[left, right]内的元素个数，并累加至最终结果ans
//		若lower <= sumi <= upper，额外地令ans+1
//		将sumi的离散化下标记入树状数组
//		等价于, 对于数组sums中的每一个元素sumi，统计出现在sumi左侧，并且数值在[sumi - upper, sumi - lower]范围内的元素个数。
//		这就等价于统计区间和[0, i]，[1, i]... [i - 1, i]当中所有落在范围[lower, upper]之内的区间个数。

		/* Int array could not handle OVERFLOW.....
		HashSet set = new HashSet();
		set.add(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			nums[i] += nums[i - 1];
			set.add(nums[i]);
		}

		Integer [] osnum = new Integer [set.size()];
		*/

//		HashSet set = new HashSet();
//		long [] org = new long[nums.length];
//		org[0] = nums[0];
//		set.add(org[0]);
//		for (int i = 1; i < nums.length; i++) {
//			org[i] = org[i-1] + nums[i];
//			set.add(org[i]);
//		}
//		long [] osnum = new long[set.size()];
//		Iterator i = set.iterator();
//		int k = 0;
//		while (i.hasNext()) {
//			osnum[k++] = (long) i.next();
//		}
//
//		Arrays.sort(osnum);
//		FenwickTree ft = new FenwickTree(osnum.length);
//		int ret = 0;
//		for (long s : org) {
//			/* Java is not as convenient as Python
//			int left = Arrays.binarySearch(osnum, s - upper);
//			int right = Arrays.binarySearch(osnum, s - lower);
//			*/
//			int left = leftMost(osnum, s - upper);
//			int right = rightMost(osnum, s - lower);
//			if (s <= upper && s >= lower)
//				ret++;
//			if (left < osnum.length && right >= 0)
//				ret += (ft.sum(right+1) - ft.sum(left));
//
//			ft.add(rightMost(osnum, s)+1, 1);
//		}
//		return ret;

		//Merge Sort, https://leetcode.com/discuss/79083/share-my-solution
//		Recall count smaller number after self where we encountered the problem
//		count[i] = count of nums[j] - nums[i] < 0 with j > i
//		Here, after we did the preprocess, we need to solve the problem
//		count[i] = count of a <= S[j] - S[i] <= b with j > i
//				ans = sum(count[:])
		return 0;
	}

////	https://leetcode.com/problems/count-of-smaller-numbers-after-self/

	//	Count of Smaller Numbers After Self
//	简单的说就是求逆序数。
//	使用逆序数有经典的解法为合并排序。
//	用Fenwick树  关于Fenwick 树介绍 Binary indexed tree (Fenwick tree)
//	简单说就是看当前数在nums中排第几，然后对小于它的数求个数和
//	用AVL, 从右向左遍历nums，在向BST插入节点时顺便做统计
//	http://traceformula.blogspot.com/2015/12/count-of-smaller-numbers-after-self.html
	private int[] countSmallerMerge(int[] nums, int left, int right, Integer[] ret) {
//		System.out.println(""+left+"\t"+right);
//		if (left > right)
//			return null;

		if (left == right)
			return new int[]{left};

		int mid = left + (right - left) / 2;
		int[] leftIndex = countSmallerMerge(nums, left, mid, ret);
		int[] rightIndex = countSmallerMerge(nums, mid + 1, right, ret);

		int k = 0, length = right - left + 1;
		int l = 0, r = 0;
		int c = 0;
		int[] merge = new int[length];


		while (k < length && l < leftIndex.length && r < rightIndex.length) {
// This code is incorrect for array contains duplicates, test, think....
//			if (nums[leftIndex[l]] < nums[rightIndex[r]]) {
//				ret[leftIndex[l]] += c;
//				merge[k++] = leftIndex[l++];
//			} else {
//				c++;
//				merge[k++] = rightIndex[r++];
//			}
			if (nums[leftIndex[l]] <= nums[rightIndex[r]]) {
				ret[leftIndex[l]] += c;
				merge[k++] = leftIndex[l++];
			} else {
				c++;
				merge[k++] = rightIndex[r++];
			}
		}
		//merge together
		while (l < leftIndex.length) {
			ret[leftIndex[l]] += c;
			merge[k++] = leftIndex[l++];
		}
		while (r < rightIndex.length) {
			merge[k++] = rightIndex[r++];
		}
		return merge;
	}

	private void countSmalleradd(int[] bit, int i, int val) {
		for (; i < bit.length; i += i & -i) bit[i] += val;
	}

	private int countSmallerquery(int[] bit, int i) {
		int ans = 0;
		for (; i > 0; i -= i & -i) ans += bit[i];
		return ans;
	}

	public List<Integer> countSmallerBIT(int[] nums) {
		int[] tmp = nums.clone();
		Arrays.sort(tmp);
		for (int i = 0; i < nums.length; i++) nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
		int[] bit = new int[nums.length + 1];
		Integer[] ans = new Integer[nums.length];
		for (int i = nums.length - 1; i >= 0; i--) {
			ans[i] = countSmallerquery(bit, nums[i] - 1);
			countSmalleradd(bit, nums[i], 1);
		}
		return Arrays.asList(ans);
	}

	//	O(NlogN) divide and conquer java solution based on bit by bit comparison
//	https://leetcode.com/discuss/74994/nlogn-divide-and-conquer-java-solution-based-bit-comparison
//	https://leetcode.com/discuss/74110/11ms-java-solution-using-merge-sort-with-explanation
//	https://leetcode.com/discuss/73233/complicated-segmentree-solution-hope-to-find-a-better-one
	public List<Integer> countSmaller(int[] nums) {
		if (nums == null)
			return null;

		//Corner case
		if (nums.length == 0)
			return new ArrayList();

		/*
		Integer [] ret = new Integer [nums.length];
		Arrays.fill(ret, 0);
		countSmallerMerge(nums, 0, nums.length-1, ret);

		return Arrays.asList(ret);
		*/

//		首先可以使用用二分搜索法，思路是将给定数组从最后一个开始，用二分法插入到一个新的数组，这样新数组就是有序的，那么此时该数字在新数组中的坐标就是原数组中其右边所有较小数字的个数，参见代码如下：
//		Integer [] ret = new Integer [nums.length];
//		Arrays.fill(ret, 0);
//		//binary insert
//		for (int i = nums.length - 1; i >= 0; i--) {
//			int left = i, right = nums.length;
		//Sad.... In Java, Arrays.binarySearch did all this...
//			while (left + 1 != right) {
//				int mid = ((right - left) >> 1) + left;
//				if (nums[i] <= nums[mid])
//					right = mid;
//				else
//					left = mid;
//			}
//			//move
//			int tmp = nums[i];
//			for (int k = i; k < right-1; k++)
//				nums[k] = nums[k+1];
//			nums[right-1] = tmp;
//			//assign
//			ret[i] = right - i - 1;
//
////			for (int k =0; k < nums.length; k++ )
////				System.out.print(nums[k]+"\t");
////			System.out.println();
//		}
//
////		for (int i =0; i < nums.length; i++ )
////			System.out.println(nums[i]);
//		return Arrays.asList(ret);

//		Having this in mind, to solve the problem we run from the back of the array, try each element. At the position i , we can simply calculate f[nums[i]] and put it into the result. However, we need to update the BIT here, because we have found another integer. So the natural question is which element we need to update in the BIT? Obviously, we need to update tree[N+1] by increasing its value by 1. But we do not stop there. Let N+1 = C1D where D has all 0 . As you can see, let  g[N+1] = C1D + 1D , we need to update g[N+1] also. And in turn, we need to update g[g[N+1]],so on...
//		http://traceformula.blogspot.com/2015/12/count-of-smaller-numbers-after-self.html
// BIT -> Change this problem to a couting problem.

//		最后给出线段树(segment tree)的解法，时间复杂度也为O(NlgN)，相似题目请参考Range Sum Query - Mutable，
		return null;
	}

	public int bulbSwitch(int n) {
		int k = 1, ret = 0, pow = 1;
		while (n >= pow) {
			ret++;
			k++;
			pow = k * k;
		}
		return ret;
//		return int(math.sqrt(n)), can you SB more.....
//		return (int) Math.sqrt(n);
	}

	//	https://leetcode.com/problems/create-maximum-number/
	public int[] maxNumberHelper(int[] num, int k) {
		if (k == 0)
			return null;
		if (k == num.length)
			return num;
		//You can avoid this stack by saving to the array directly....
		Stack<Integer> st = new Stack();

		for (int i = 0; i < num.length; i++) {
			while (!st.isEmpty() && (st.size() + num.length - i > k) && st.peek() < num[i]) {
				st.pop();
			}
			st.push(num[i]);
		}

		while (st.size() > k) {
			st.pop();
		}
		//k <= nums.length, k >= 1
		int[] ret = new int[k];

		while (!st.isEmpty()) {
			ret[st.size() - 1] = st.pop();
		}
		return ret;
	}

	private int maxNumberCompare2(int[] num1, int start1, int[] num2, int start2) {
		//basically, compare 1 and 2 directly.
		//-1, left side is larger, +1, right side is larger.
		int i = start1, j = start2;
		while (i < num1.length && j < num2.length) {
			if (num1[i] == num2[j]) {
				++i;
				++j;
			} else if (num1[i] > num2[j])
				return -1;
			else
				return +1;
		}

		if (i == num1.length && j == num2.length)
			return 0;
		return i == num1.length ? 1 : -1;
	}

	private int[] maxNumberMerge(int[] num1, int[] num2) {
		if (num1 == null || num2 == null)
			return num1 == null ? num2 : num1;
		int[] ret = new int[num1.length + num2.length];
		int i = 0, j = 0, k = 0;
		//Simply merge will be fail in below cases
		//int [] nums1 = {6,7};
		//int [] nums2 = {6,0,4};
		//Why???? Because two arrays are not necessraily sorted
//		while (i < num1.length && j < num2.length) {
//			if (num1[i] > num2[j])
//				ret[k++] = num1[i++];
//			else
//				ret[k++] = num2[j++];
//		}
		while (i < num1.length && j < num2.length) {
			if (maxNumberCompare2(num1, i, num2, j) < 0) {
				ret[k++] = num1[i++];
			} else {
				ret[k++] = num2[j++];
			}
		}

		while (i < num1.length)
			ret[k++] = num1[i++];
		while (j < num2.length)
			ret[k++] = num2[j++];
		return ret;
	}

	private int maxNumberCompare(int[] num1, int[] num2) {
		for (int i = 0; i < num1.length; i++) {
			int delta = num2[i] - num1[i];
			if (delta != 0)
				return delta > 0 ? 1 : -1;
		}

		return 0;
	}

	//	http://bookshadow.com/weblog/2015/12/24/leetcode-create-maximum-number/
//	https://www.hrwhisper.me/leetcode-create-maximum-number/
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int[] ret = new int[k];
		for (int len1 = 0; len1 <= nums1.length && len1 <= k; len1++) {
			int len2 = k - len1;
			if (len2 <= nums2.length) {
				int[] l1 = maxNumberHelper(nums1, len1);
				int[] l2 = maxNumberHelper(nums2, len2);
				int[] tmp = maxNumberMerge(l1, l2);
				if (maxNumberCompare(ret, tmp) > 0) {
					System.arraycopy(tmp, 0, ret, 0, k);
				}
			}
		}
		return ret;
	}

	private int findMinLastPos(Map<Character, Integer> lastPosMap) {
		if (lastPosMap == null || lastPosMap.isEmpty()) return -1;
		int minLastPos = Integer.MAX_VALUE;
		for (int lastPos : lastPosMap.values()) {
			minLastPos = Math.min(minLastPos, lastPos);
		}
		return minLastPos;
	}

	//	https://leetcode.com/problems/remove-duplicate-letters/
//	https://www.hrwhisper.me/leetcode-remove-duplicate-letters/
//	https://leetcode.com/discuss/74121/some-python-solutions
	public String removeDuplicateLetters(String s) {
		if (s == null || s.length() <= 1)
			return s;
//		1. 用栈。
//		首先对字符串出现的个数进行统计。
//		然后对字符串扫描，每遇到一个字符串，判断其是否在栈中，如果在则跳过。（计数 – 1）
//		如果不在栈中，和栈顶的元素判断，要是当前栈顶的元素比较大而且cnt不为0（也就是说之后还有这个元素），就把栈顶弹出。然后把当前的元素入栈。
		/*
		boolean set [] = new boolean[26];
		int map [] = new int[26];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			map[c-'a']++;
		}

		Stack<Character> st = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!set[c-'a']) {
				while (!st.isEmpty()) {
					char top = st.peek();
					if (map[top-'a'] > 0 && top > c) {
						set[st.pop()-'a'] = false;
					} else {
						break;
					}
				}

				st.push(c);
				set[c-'a'] = true;
			}

			map[c-'a']--;
		}
		StringBuffer sb = new StringBuffer(new String(new char[st.size()]));
		while (!st.isEmpty()) {
			sb.setCharAt(st.size()-1, st.peek());
			st.pop();
		}
		return sb.toString();
		*/

//		2.和这个差不多https://leetcode.com/discuss/73777/easy-to-understand-iterative-java-solution
//		找每个字符出现的最后的位置， 取其中最小的作为end。（这个元素必须在end或者end以前出现，否则就没了= =）
//		从start（一开始设为0）枚举到end，最小的那个作为当前的字符。如此循环。
//		如：dcbacdcd
//		最后出现的位置：a:3 , b: 2, c : 6, d: 7
//		于是0~2中最小的元素为b 接着3~3 为a  接着 4~6 为c 接着 7~7 为d  所以为bacd
		/*
		Map<Character, Integer> lastPosMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			lastPosMap.put(s.charAt(i), i);
		}
		char[] result = new char[lastPosMap.size()];
		int begin = 0, end = findMinLastPos(lastPosMap);
		for (int i = 0; i < result.length; i++) {
			char minChar = 'z' + 1;
			for (int k = begin; k <= end; k++) {
				if (lastPosMap.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
					minChar = s.charAt(k);
					begin = k+1;
				}
			}

			result[i] = minChar;

			lastPosMap.remove(minChar);
			if (s.charAt(end) == minChar) end = findMinLastPos(lastPosMap);
		}

		return new String(result);
		*/

//		每次对每个字母出现的次数进行统计 cnt
//		然后扫描数组，找最小的字符min_c，并对于经过的每一个元素 cnt – 1 ，直到数组扫描完毕或者遇到cnt = 0的（cnt = 0说明后面没有这个元素了，不能继续，否则就丢掉了这个元素）
//		然后把字符串s中 min_c 替换成空 如此循环。

		return null;
	}

	//	https://leetcode.com/problems/maximum-product-of-word-lengths/
	public int maxProduct(String[] words) {
		if (words == null || words.length <= 1)
			return 0;
		int ret = 0;
		int[] masks = new int[words.length];
		int[] lens = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			lens[i] = words[i].length();
			for (int j = 0; j < lens[i]; j++)
				masks[i] |= 1 << (words[i].charAt(j) - 'a');
		}
		for (int i = 0; i < words.length; i++)
			for (int j = i + 1; j < words.length; j++) {
				if ((masks[i] & masks[j]) == 0)
					ret = Math.max(ret, lens[i] * lens[j]);
			}
		return ret;
	}

	public int nthSuperUglyNumber(int n, int[] primes) {
		/* ETL
		if (n <= 1)
			return n;
		int last = 1;
		PriorityQueue<Integer> [] queues = new PriorityQueue [primes.length];
		for (int i = 0; i < primes.length; i++) {
			queues[i] = new PriorityQueue<>();
			queues[i].add(1);
		}
		--n;
		int [] tmp = new int [primes.length];
		while (n > 0) {
			int min = queues[0].peek() * primes[0];
			tmp[0] = min;
			for (int i = 1; i < primes.length; i++) {
				tmp[i] = queues[i].peek() * primes[i];
				min = Math.min(min, tmp[i]);
			}
			if (min > last) {
				--n;
				last = min;
			}
			for (int i = 0; i < primes.length; i++) {
				if (min == tmp[i])
					queues[i].remove();
				if (!queues[i].contains(min))
					queues[i].add(min);
			}
		}

		return last;
		*/
		int[] ret = new int[n];
		ret[0] = 1;

		int[] indexes = new int[primes.length];

		for (int i = 1; i < n; i++) {
			ret[i] = Integer.MAX_VALUE;

			for (int j = 0; j < primes.length; j++) {
				ret[i] = Math.min(ret[i], primes[j] * ret[indexes[j]]);
			}

			//This part is tricky... We might have primes come to this stages at different steps......
			for (int j = 0; j < indexes.length; j++) {
				if (ret[i] == primes[j] * ret[indexes[j]]) {
					indexes[j]++;
				}
			}
		}

		return ret[n - 1];
	}

	//	https://leetcode.com/problems/ugly-number/
	public boolean isUgly(int num) {
		if (num <= 0)
			return false;
		while (((num & 0x01) == 0))
			num >>= 1;
		while (num % 3 == 0)
			num /= 3;
		while (num % 5 == 0)
			num /= 5;
		return num == 1;
	}

	//	https://leetcode.com/problems/ugly-number-ii/
	public int nthUglyNumber(int n) {
//		Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
		if (n <= 1)
			return n;
		PriorityQueue<Integer> l2 = new PriorityQueue();
		PriorityQueue<Integer> l3 = new PriorityQueue();
		PriorityQueue<Integer> l5 = new PriorityQueue();
		l2.add(1);
		l3.add(1);
		l5.add(1);
		--n;
		int last = 1;
		while (n > 0) {
			int m2 = l2.peek() * 2;
			int m3 = l3.peek() * 3;
			int m5 = l5.peek() * 5;
			int min = Math.min(m2, Math.min(m3, m5));
			if (min > last) {
				--n;
				last = min;
			}
			if (m2 == min) {
				l2.remove();
			}
			l2.add(min);
			if (m3 == min) {
				l3.remove();
			}
			l3.add(min);
			if (m5 == min) {
				l5.remove();
			}
			l5.add(min);
		}
		return last;
	}

	//	https://leetcode.com/problems/burst-balloons/
//	http://algobox.org/burst-balloons/
//时间复杂度O(n ^ 3)参考peisi的答案：https://leetcode.com/discuss/72216/share-some-analysis-and-explanations
//	以最后被爆破的气球m为界限，把数组分为左右两个子区域 状态转移方程：
//	dp[l][r] = max(dp[l][r], nums[l] * nums[m] * nums[r] + dp[l][m] + dp[m][r])
	public int maxCoins(int[] inums) {
		int length = inums.length;
		if (inums == null || length <= 0)
			return 0;

		/* Think why below code is wrong????
		int [][]dp = new int[length][];
		for (int i = 0; i < length; i++)
			dp[i] = new int [length];

		//len 1
		for (int i = 0; i < length; i++)
			dp[i][i] = nums[i];

		for (int l = 2; l <= length; l++) {
			//two end
			for (int i = 0; i <= length - l; i++) {
				//left end
				dp[i][i+l-1] = nums[i]*nums[i+1]+dp[i+1][i+l-1];
				for (int j = i+1; j < i+l-1; j++) {
					dp[i][i+l-1] = Math.max(dp[i][i+l-1], dp[i][j-1]+dp[j+1][i+l-1]+nums[j-1]*nums[j]*nums[j+1]);
				}
				//right end
				dp[i][i+l-1] = Math.max(dp[i][i+l-1], dp[i][i+l-2]+nums[i+l-2]*nums[i+l-1]);
			}

		}

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++)
				System.out.print(dp[i][j] + "\t");
			System.out.println();
		}
		return dp[0][length-1];
		*/

//		reverse thinking. Like I said the coins you get for a balloon does not depend on the balloons already burst. Therefore instead of divide the problem by the first balloon to burst, we divide the problem by the last balloon to burst.
//		For the first we have nums[i-1]*nums[i]*nums[i+1] for the last we have nums[-1]*nums[i]*nums[n].
//		Here comes the final solutions. Note that we put 2 balloons with 1 as boundaries and also burst all the zero balloons in the first round since they won't give any coins. The algorithm runs in O(n^3) which can be easily seen from the 3 loops in dp solution.
//		dp[left][right] means the maximum value when we burst all balloons between nums[left] to nums[right](pay attention that balloons left and right are not included)
		int[] nums = new int[inums.length + 2];
		int n = 1;
		for (int x : inums) if (x > 0) nums[n++] = x;
		nums[0] = nums[n++] = 1;

		int[][] dp = new int[n][n];
		for (int k = 2; k < n; ++k)
			for (int l = 0; l < n - k; ++l) {
				int r = l + k;
				for (int m = l + 1; m < r; ++m)
					dp[l][r] = Math.max(dp[l][r],
							nums[l] * nums[m] * nums[r] + dp[l][m] + dp[m][r]);
			}

		return dp[0][n - 1];
	}

	//	https://leetcode.com/problems/minimum-height-trees/
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
//		Time Limit Exceeded
//
//		List<Integer> ret = new ArrayList<>();
//		int [][] depth = new int [n][];
//		for (int i = 0; i < n; i++)
//			depth[i] = new int [n];
//		for (int [] edge : edges) {
//			depth[edge[0]][edge[1]] = 1;
//			depth[edge[1]][edge[0]] = 1;
//			for (int e = 0; e < n; e++) {
//				if (depth[edge[0]][e] > 0) {
//					if (depth[edge[1]][e] == 0 ||
//							depth[edge[1]][e] > depth[edge[1]][edge[0]] + depth[edge[0]][e]) {
//						depth[edge[1]][e] = depth[e][edge[1]] = depth[edge[0]][e] + depth[edge[0]][edge[1]];
//					}
//				}
//			}
//			for (int e = 0; e < n; e++) {
//				if (depth[edge[1]][e] > 0) {
//					if (depth[edge[0]][e] == 0 ||
//							depth[edge[0]][e] > depth[edge[0]][edge[1]] + depth[edge[1]][e]) {
//						depth[edge[0]][e] = depth[e][edge[0]] = depth[edge[1]][e] + depth[edge[1]][edge[0]];
//					}
//				}
//			}
//		}
//
//		int global = n;
//		for (int i = 0; i < n; i++) {
//			int minDepth = 0;
//			for (int j = 0; j < n; j++) {
//				if (i == j)
//					continue;
//				minDepth = Math.max(minDepth, depth[i][j]);
//			}
//			if (minDepth < global) {
//				global = minDepth;
//				ret.clear();
//				ret.add(i);
//			} else if (minDepth == global) {
//				ret.add(i);
//			}
//		}
//
////		for (int i = 0; i < depth.length; i++) {
////			for (int j = 0; j < depth.length; j++)
////				System.out.print(depth[i][j] + "\t");
////			System.out.println();
////		}
//
//		return ret;


//		https://leetcode.com/problems/minimum-height-trees/
//		答案一定是最长距离的中间结点位置上。
//		我们要的是中间结点，沿着树的外围每次把叶子结点砍掉，那么，最后剩下的不就是中间结点了么？
//		For a path graph of n nodes, find the minimum height trees is trivial. Just designate the middle point(s) as roots.
//		For a tree we can do some thing similar. We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the pointers move the same speed. When two pointers meet, we keep only one of them, until the last two pointers meet or one step away we then find the roots.
//		It is easy to see that the last two pointers are from the two ends of the longest path in the graph.
//		The actual implementation is similar to the BFS topological sort. Remove the leaves, update the degrees of inner vertexes. Then remove the new leaves. Doing so level by level until there are 2 or 1 nodes left. What's left is our answer!
//		The time complexity and space complexity are both O(n).
//		Note that for a tree we always have V = n, E = n-1.
//		https://leetcode.com/discuss/71763/share-some-thoughts
		if (n == 1) return Collections.singletonList(0);

		List<Set<Integer>> adj = new ArrayList<>(n);
		for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}

		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; ++i)
			if (adj.get(i).size() == 1) leaves.add(i);

		while (n > 2) {
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			for (int i : leaves) {
				//Because leave has Only exactly 1 nodes to connect
				int j = adj.get(i).iterator().next();
				adj.get(j).remove(i);
				if (adj.get(j).size() == 1) newLeaves.add(j);
			}
			leaves = newLeaves;
		}
		return leaves;
	}

	//	https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
	public int maxProfitWithCooldown(int[] prices) {
		if (prices == null || prices.length <= 1)
			return 0;
		/* ETL, why I always has  one more loop.
		int length = prices.length;
		int [][] dp = new int [length][];
		for (int i = 0; i < length; i++)
			dp[i] = new int [length];

		for (int l = 2; l <= length; l++) {
			for (int i = 0; i <= length - l; i++) {
				dp[i][i+l-1] = prices[i+l-1]-prices[i];
//				dp[i][i+l-1] = Math.max(dp[i+1][i+l-1], dp[i][i+l-1]);
//				dp[i][i+l-1] = Math.max(dp[i+l-2][i+l-1], dp[i][i+l-1]);
				for (int k = i + 1; k <= i + l -2; k++)
					dp[i][i+l-1] = Math.max(dp[i][k-1]+dp[k+1][i+l-1], dp[i][i+l-1]);
				dp[i][i+l-1] = Math.max(0, dp[i][i+l-1]);
			}
		}

		return dp[0][length-1];
		*/

//		buy[i]: Max profit till index i. The series of transaction is ending with a buy.
//		sell[i]: Max profit till index i. The series of transaction is ending with a sell.
		//Becarefull about this definition... A better defition....
//		buy[i] : Maximum profit which end with buying on day i or end with buying on a day before i and takes rest until the day i since then.
//		sell[i] : Maximum profit which end with selling on day i or end with selling on a day before i and takes rest until the day i since then.
//		Till index i, the buy / sell action must happen and must be the last action. It may not happen at index i. It may happen at i - 1, i - 2, ... 0.
//		In the end n - 1, return sell[n - 1]. Apparently we cannot finally end up with a buy. In that case, we would rather take a rest at n - 1.
//		For special case no transaction at all, classify it as sell[i], so that in the end, we can still return sell[n - 1].

//		buy[i]: To make a decision whether to buy at i, we either take a rest, by just using the old decision at i - 1, or sell at/before i - 2, then buy at i, We cannot sell at i - 1, then buy at i, because of cooldown.
//		sell[i]: To make a decision whether to sell at i, we either take a rest, by just using the old decision at i - 1, or buy at/before i - 1, then sell at i.
//				So we get the following formula:
//		buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
//		sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);

//		Optimize to O(1) Space
//		DP solution only depending on i - 1 and i - 2 can be optimized using O(1) space.
//		Let b2, b1, b0 represent buy[i - 2], buy[i - 1], buy[i]
//		Let s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]
//		Then arrays turn into Fibonacci like recursion:
//		b0 = Math.max(b1, s2 - prices[i]);
//		s0 = Math.max(s1, b1 + prices[i]);

//		First we define the initial states at i = 0:
//		We can buy. The max profit at i = 0 ending with a buy is -prices[0].
//		We cannot sell. The max profit at i = 0 ending with a sell is 0.

//		int b1 = -prices[0];
//		int s2 = 0;
//		int s1 = 0;
//		for (int i = 1; i <= prices.length; i++) {
//			int b0 = Math.max(b1, s2 - prices[i - 1]);
//			int s0 = Math.max(s1, b1 + prices[i - 1]);
//			b1 = b0;
//			s2 = s1;
//			s1 = s0;
//		}
//
//		return s1;

//		sells[i]表示在第i天卖出股票所能获得的最大累积收益
//		buys[i]表示在第i天买入股票所能获得的最大累积收益
//		初始化令sells[0] = 0，buys[0] = -prices[0]
//		第i天交易时获得的累计收益只与第i-1天与第i-2天有关
//		记第i天与第i-1天的价格差：delta = price[i] - price[i - 1]
//		状态转移方程为：
//		sells[i] = max(buys[i - 1] + prices[i], sells[i - 1] + delta)
//		buys[i] = max(sells[i - 2] - prices[i], buys[i - 1] - delta)
//		上述方程的含义为：
//
//		第i天卖出的最大累积收益 = max(第i-1天买入~第i天卖出的最大累积收益, 第i-1天卖出后反悔~改为第i天卖出的最大累积收益)
//		第i天买入的最大累积收益 = max(第i-2天卖出~第i天买入的最大累积收益, 第i-1天买入后反悔~改为第i天买入的最大累积收益)
//		而实际上：
//		第i-1天卖出后反悔，改为第i天卖出 等价于 第i-1天持有股票，第i天再卖出
//		第i-1天买入后反悔，改为第i天买入 等价于 第i-1天没有股票，第i天再买入
//		所求的最大收益为max(sells)。显然，卖出股票时才可能获得收益。
//		def maxProfit(self, prices):
//		"""
//		:type prices: List[int]
//		:rtype: int
//		"""
//		size = len(prices)
//		if not size:
//		return 0
//		buys = [None] * size
//		sells = [None] * size
//		sells[0], buys[0] = 0, -prices[0]
//		for x in range(1, size):
//		delta = prices[x] - prices[x - 1]
//		sells[x] = max(buys[x - 1] + prices[x], sells[x - 1] + delta)
//		buys[x] = max(buys[x - 1] - delta, \
//				sells[x - 2] - prices[x] if x > 1 else None)
//		return max(sells)

//		引入辅助数组sells和buys
//		sells[i]表示在第i天不持有股票所能获得的最大累计收益
//		buys[i]表示在第i天持有股票所能获得的最大累计收益
//		初始化数组：
//		sells[0] = 0
//		sells[1] = max(0, prices[1] - prices[0])
//		buys[0] = -prices[0]
//		buys[1] = max(-prices[0], -prices[1])
//		状态转移方程：
//		sells[i] = max(sells[i - 1], buys[i - 1] + prices[i])
//		buys[i] = max(buys[i - 1], sells[i - 2] - prices[i])
//		所求最大收益为sells[-1]
//
//		Python代码：
//		class Logger(object):
//		def maxProfit(self, prices):
//		"""
//		:type prices: List[int]
//		:rtype: int
//		"""
//		size = len(prices)
//		if size < 2:
//		return 0
//		buys = [None] * size
//		sells = [None] * size
//		sells[0], sells[1] = 0, max(0, prices[1] - prices[0])
//		buys[0], buys[1] = -prices[0], max(-prices[0], -prices[1])
//		for x in range(2, size):
//		sells[x] = max(sells[x - 1], buys[x - 1] + prices[x])
//		buys[x] = max(buys[x - 1], sells[x - 2] - prices[x])
//		return sells[-1]


//		对于当天最终未持股的状态，最终最大利润有两种可能，一是今天没动作跟昨天未持股状态一样，二是昨天持股了，今天卖了。所以我们只要取这两者之间最大值即可，表达式如下：
//		sellDp[i] = Math.max(sellDp[i - 1], buyDp[i - 1] + prices[i]);
//		对于当天最终持股的状态，最终最大利润有两种可能，一是今天没动作跟昨天持股状态一样，二是前天还没持股，今天买了股票，这里是因为cooldown的原因，所以今天买股要追溯到前天的状态。我们只要取这两者之间最大值即可，表达式如下：
//		buyDp[i] = Math.max(buyDp[i - 1], sellDp[i - 2] - prices[i]);
//		最终我们要求的结果是
//		sellDp[n - 1] 表示最后一天结束时手里没股票时的累积最大利润


//		维护三个一维数组buy, sell，和rest。其中：
//		buy[i]表示在第i天之前最后一个操作是买，此时的最大收益。
//		sell[i]表示在第i天之前最后一个操作是卖，此时的最大收益。
//		rest[i]表示在第i天之前最后一个操作是冷冻期，此时的最大收益。
//		我们写出递推式为：
//
//		buy[i]  = max(rest[i-1] - price, buy[i-1])
//		sell[i] = max(buy[i-1] + price, sell[i-1])
//		rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
//
//		上述递推式很好的表示了在买之前有冷冻期，买之前要卖掉之前的股票。一个小技巧是如何保证[buy, rest, buy]的情况不会出现，这是由于buy[i] <= rest[i]， 即rest[i] = max(sell[i-1], rest[i-1])，这保证了[buy, rest, buy]不会出现。
//		另外，由于冷冻期的存在，我们可以得出rest[i] = sell[i-1]，这样，我们可以将上面三个递推式精简到两个：
//
//		buy[i]  = max(sell[i-2] - price, buy[i-1])
//		sell[i] = max(buy[i-1] + price, sell[i-1])
		return 0;
	}

	//	https://leetcode.com/problems/additive-number/
//Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
//Follow up: How would you handle overflow for very large input integers?
	//Very tedious work, how to deal with overflow, leading 0..
	public boolean isAdditiveNumber(String num) {
		if (num == null || num.length() <= 2)
			return false;
		int length = num.length();
		for (int i = 0; i < length; i++) {
			if ((num.charAt('0') == '0' && i > 0))
				return false;
			for (int j = i + 1; j < length; j++) {
				// System.out.println(i+"\t" + j);
				//from start to i, i - start + 1
				//from i + 1 to j, j - i
				//remaining length, length - 1 - j, from j+1
				//not enough space for result.
				int remaingLength = length - 1 - j;
				if (Math.max(i + 1, j - i) > remaingLength)
					break;

				if (j != i + 1 && num.charAt(i + 1) == '0')
					continue;
				//Check sum
				//YOU DON'T NEED DO MANUAL WORK AS C++, JAVA is....
				long a = Integer.parseInt(num.substring(0, i + 1));
				long b = Integer.parseInt(num.substring(i + 1, j + 1));
				long c;
				String cs;
				int cL;
				int k = j + 1;
				boolean flag = false;
				while (k < length) {
					flag = true;
					c = a + b;
					cs = String.valueOf(c);
					cL = cs.length();
					// 	System.out.println(num.substring(k));
//					System.out.println(num.substring(k, k + cL));
//					System.out.println(cs.equals(num.substring(k , k + cL)));
					// 	System.out.println("XXXXX");
					if (length - k < cL)
						break;

					if (cs.equals(num.substring(k, k + cL))) {
						a = b;
						b = c;
						k += cL;
					} else {
						break;
					}
				}

				if (flag && k == length)
					return true;
			}
		}
		return false;
	}

	//	https://leetcode.com/problems/remove-invalid-parentheses/
// helper function checks if string s contains valid parantheses
	boolean isValidremoveInvalidParentheses(String s) {
		int count = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') count++;
			if (c == ')' && count-- == 0) return false;
		}

		return count == 0;
	}

	int NumisValidremoveInvalidParentheses(String s) {
		/* Why below code is wrong???
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') count++;
			else if (c == ')')
				count--;
		}
		return count;
		*/
		int count = 0;
		int acc = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				acc++;
			} else if (c == ')') {
				acc--;
				if (acc < 0) {
					acc = 0;
					count++;
				}
			}
		}
		return count + acc;
	}

	public List<String> removeInvalidParentheses(String s) {

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
	}

	public String getHint(String secret, String guess) {
		int a = 0, b = 0;
		int[] map1 = new int[10];
		int[] map2 = new int[10];
		int length = secret.length();
		for (int i = 0; i < length; i++) {
			if (secret.charAt(i) == guess.charAt(i)) {
				a++;
			} else {
				map1[secret.charAt(i) - '0']++;
				map2[guess.charAt(i) - '0']++;
			}
		}

		for (int i = 0; i < 10; i++)
			b += Math.min(map1[i], map2[i]);
		return Integer.toString(a) + "A" + Integer.toString(b) + "B";
	}

	//	https://leetcode.com/problems/word-pattern/
	public boolean wordPattern(String pattern, String str) {
		if (pattern == null && str == null)
			return true;
		if (str == null)
			return false;

		String[] splited = str.split("\\s+");
		if (pattern == null)
			return splited.length == 0;

		if (pattern.length() != splited.length)
			return false;

		Map<Character, String> map = new HashMap<>();
		Set<String> s = new HashSet<>();
		for (int i = 0; i < pattern.length(); i++) {
			if (map.get(pattern.charAt(i)) == null) {
				if (s.contains(splited[i]))
					return false;
				map.put(pattern.charAt(i), splited[i]);
				s.add(splited[i]);
			} else if (!map.get(pattern.charAt(i)).equals(splited[i])) {
				//JAVA != and == are broken....
				return false;
			}
		}

		return true;
	}

	//	https://leetcode.com/problems/longest-increasing-subsequence/
//	Your algorithm should run in O(n2) complexity. Follow up: Could you improve it to O(n log n) time complexity?
//	https://en.wikipedia.org/wiki/Longest_increasing_subsequence
//	http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
//	http://www.algorithmist.com/index.php/Longest_Increasing_Subsequence
//	https://www.quora.com/How-do-you-compute-longest-increasing-subsequence
//	https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
	public int lengthOfLIS(int[] nums) {
		if (nums == null)
			return 0;
		int length = nums.length;

		if (length <= 1)
			return length;
		/* O(n2)
		int [] check = new int[length];
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i])
					check[i] = Math.max(check[j]+1, check[i]);
			}
//			System.out.println(nums[i]);
//			for (int k = 0; k < length; k++)
//				System.out.print(check[k]);
//			System.out.println();
		}
		int ret = 0;
		for (int i = 0; i < length; i++)
			ret = Math.max(ret, check[i]);
		return ret + 1;
		*/

		//O(nlogn)
//		Our observation is, assume that the end element of largest sequence is E. We can add (replace) current element A[i] to the existing sequence if there is an element A[j] (j > i) such that E < A[i] < A[j] or (E > A[i] < A[j] – for replace).
//		In general, we have set of active lists of varying length. We are adding an element A[i] to these lists. We scan the lists (for end elements) in decreasing order of their length. We will verify the end elements of all the lists to find a list whose end element is smaller than A[i] (floor value).
//		Our strategy determined by the following conditions,
//		1. If A[i] is smallest among all end candidates of active lists, we will start new active list of length 1.
//		2. If A[i] is largest among all end candidates of active lists, we will clone the largest active list, and extend it by A[i].
//		3. If A[i] is in between, we will find a list with largest end element that is smaller than A[i]. Clone and extend this list by A[i]. We will discard all other lists of same length as that of this modified list.

		//If we only need to return length, we don't really care who is in the sequence....
//		Complexity:
//		The loop runs for N elements. In the worst case (what is worst case input?), we may end up querying ceil value using binary search (log i) for many A[i].
//		Therefore, T(n) < O( log N! )  = O(N log N). Analyse to ensure that the upper and lower bounds are also O( N log N ). The complexity is THETA (N log N).

		//Save memory, for case with huge amounts of 1 and a 2....This array consumes too much memory
//		List [] LISArray = new LinkedList [length];
//		for (int i = 0; i < length; i++)
//			LISArray[i] = new LinkedList<Integer>();
//		int [] lis = new int [length];
//		lis[0] = nums[0];
		List lis = new ArrayList<>();
		lis.add(nums[0]);
//		List LISArray = new LinkedList<LinkedList>();
//		LinkedList tmp = new LinkedList<Integer>();
//		tmp.add(nums[0]);
//		LISArray.add(tmp);
		int ret = 1;
		for (int i = 1; i < length; i++) {
			int index = Arrays.binarySearch(lis.toArray(), 0, lis.size(), nums[i]);
			if (index < 0) {
//				System.out.println("COPY");
				int ins = -(index + 1);
//				if (ins == ret) {
//					LISArray[ins] = new LinkedList<Integer>();
//					++ret;
//				}
//				if (ins == lis.size()) {
//					lis
//				}
//				lis[ins] = nums[i];
				if (ins == lis.size()) {
					lis.add(new Integer(nums[i]));
//					LISArray.add(new LinkedList<>());
				} else {
					lis.set(ins, nums[i]);
				}

//				if (ins > 0) {
//					LISArray.set(ins, (LinkedList) (((LinkedList)LISArray.get(ins-1)).clone()));
//					((LinkedList) LISArray.get(ins)).add(nums[i]);
//				}
			}
		}
		return lis.size();
	}

	//	http://www.keithschwarz.com/interesting/code/?dir=find-duplicate
//Very cool explanation in Chinese below...
//	http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/
//	https://en.wikipedia.org/wiki/Cycle_detection#Tortoise_and_hare
	//Looks more cleanr here
//	http://bookshadow.com/weblog/2015/07/10/leetcode-linked-list-cycle-ii/
	//Another way
	//https://segmentfault.com/a/1190000003817671
//	http://www.cnblogs.com/grandyang/p/4843654.html
	public int findDuplicate(int[] nums) {
		//v1
		/*
		HashSet set = new HashSet();
		for (int i = 0 ; i < nums.length; i++)
			if (set.contains(nums[i]))
				return nums[i];
			else
				set.add(nums[i]);
		return 0;
		*/

		//v2
		/*
		int length = nums.length;
		int begin = 1, end = length - 1;
		while (begin < end) {
			int mid = begin + ((end - begin) >> 1);
			int less = 0;
			int larger = 0;
			for (int i = 0; i < length; i++) {
				if (begin <= nums[i] && nums[i] <= end) {
//				http://www.cnblogs.com/grandyang/p/4843654.html
//There is another trick to bypass this double check
					if (nums[i] <= mid)
						less++;
					else
						larger++;
				}
			}
			if (less > larger)
				end = mid;
			else
				begin = mid + 1;
		}
		return begin;
		*/
		int slow = 0;
		int fast = 0;
		// 找到快慢指针相遇的地方
		do {
			slow = nums[slow];
			fast = nums[nums[fast]];
		} while (slow != fast);
		int find = 0;
		// 用一个新指针从头开始，直到和慢指针相遇
		while (find != slow) {
			slow = nums[slow];
			find = nums[find];
		}
		return find;
	}

	//	https://leetcode.com/problems/move-zeroes/
	public void moveZeroes(int[] nums) {
		if (nums == null || nums.length <= 1)
			return;
		int c = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0)
				nums[c++] = nums[i];
		}
		while (c < nums.length) {
			nums[c++] = 0;
		}
	}

	boolean isValidCell(int[][] board, int i, int j) {
		return (i >= 0 && i <= board.length - 1) && (j >= 0 && j <= board[0].length - 1);
	}

	//	https://leetcode.com/problems/game-of-life/
//	https://segmentfault.com/a/1190000003819277
	public void gameOfLife(int[][] board) {
		final int HIGHBITS = 0xF0;
		final int LOWBITS = 0x0F;
		int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				//check boundary
				int counter = 0;
				for (int k = 0; k < directions.length; k++) {
					int x = i + directions[k][0];
					int y = j + directions[k][1];
					if (isValidCell(board, x, y) && ((board[x][y] & LOWBITS) != 0)) {
						counter++;
					}
				}
				if ((counter == 2 && ((board[i][j] & LOWBITS) > 0)) || (counter == 3)) {
					board[i][j] |= HIGHBITS;
				}
			}

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				if ((board[i][j] & HIGHBITS) != 0)
					board[i][j] = 1;
				else
					board[i][j] = 0;
	}


	public int numSquaresHelper(int n, int[] numSquaresTable) {
		if (n == 0)
			return 0;
		if (numSquaresTable[n] != 0)
			return numSquaresTable[n];
		int upper = (int) Math.sqrt(n);
		numSquaresTable[n] = n;
		while (upper > 1) {
			numSquaresTable[n] = Math.min(numSquaresTable[n], 1 + numSquaresHelper(n - upper * upper, numSquaresTable));
			upper--;
		}
		return numSquaresTable[n];
	}

	//	https://leetcode.com/problems/perfect-squares/
//	http://bookshadow.com/weblog/2015/09/09/leetcode-perfect-squares/
//	http://www.cnblogs.com/grandyang/p/4800552.html
	public int numSquares(int n) {
		int[] numSquaresTable = new int[n + 1];
		return numSquaresHelper(n, numSquaresTable);
//		任意一个正整数均可表示为4个整数的平方和，其实是可以表示为4个以内的平方数之和，那么就是说返回结果只有1,2,3或4其中的一个，首先我们将数字化简一下，由于一个数如果含有因子4，那么我们可以把4都去掉，并不影响结果
//		如果一个数除以8余7的话，那么肯定是由4个完全平方数组成
//		时间复杂度：O(n * sqrt n)
//		int dp[] = new int[n + 1];
//		//仔细观察，不需要担心溢出
//		Arrays.fill(dp, Integer.MAX_VALUE);
//		for (int i = 1; i * i <= n; i++) {
//			dp[i * i] = 1;
//		}
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; i + j * j <= n; j++) {
//				dp[i + j * j] = Math.min(dp[i] + 1, dp[i + j * j]);
//			}
//		}
//		return dp[n];
	}

	//	https://leetcode.com/problems/first-bad-version/
	boolean isBadVersion(int version) {
		if (version >= 1)
			return false;
		else
			return true;
	}

	public int firstBadVersion(int n) {
		//A nice place to play type conversion...
		/* Don't do that
		long l = 0, r = ((long)n) + 1;
		while (l + 1 != r) {
			long mid = l + ((r - l) >> 1);
// 			System.out.println(mid);
			if (isBadVersion((int)mid)) {
				r = mid;
			} else {
				l = mid;
			}
		}
		return (int)r;
		*/
		int left = 0, right = n;
		while (left + 1 != right) {
			int mid = left + ((right - left) >> 1);
			if (isBadVersion(mid)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return right;
	}

	//	https://leetcode.com/problems/h-index/
	public int hIndexI(int[] citations) {
		if (citations == null)
			return 0;
		int length = citations.length;
		int[] table = new int[length + 1];
		for (int i = 0; i < length; i++)
			if (citations[i] > length)
				table[length]++;
			else
				table[citations[i]]++;
		int acc = 0;
		for (int i = length; i >= 0; i--) {
			acc += table[i];
			if (acc >= i)
				return i;
		}
		return 0;
	}

	//	https://leetcode.com/problems/h-index-ii/
	public int hIndexII(int[] citations) {
		//citations is sorted
		if (citations == null || citations.length == 0)
			return 0;

//		在升序的引用数数组中，假设数组长为N，下标为i，则N - i就是引用次数大于等于下标为i的文献所对应的引用次数的文章数。如果该位置的引用数小于文章数，则说明则是有效的H指数，如果一个数是H指数，那最大的H指数一定在它的后面（因为是升序的）。根据这点就可已进行二分搜索了。这里min = mid + 1的条件是citations[mid] < n - mid，确保退出循环时min肯定是指向一个有效的H指数。
//		for paper[m]. there should be at least (len – m) papers with citations >= citations[m]

		int length = citations.length;
		int l = 0, r = length - 1;
		while (l <= r) {
			int mid = l + ((r - l) >> 1);
//			if (citations[mid] == (length - mid))
//				return citations[mid];
			// 如果该点是有效的H指数，则最大H指数一定在右边
			if (citations[mid] < (length - mid)) {
				l = mid + 1;
				// 否则最大H指数在左边
			} else {
				r = mid - 1;
			}
		}
		return length - l;
	}

	//	https://leetcode.com/problems/binary-tree-paths/
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> ret = new ArrayList<>();
		if (root == null)
			return ret;

		if (root.left == null && root.right == null) {
			ret.add(new String(Integer.toString(root.val)));
			return ret;
		}
		String header = new String(Integer.toString(root.val));
		header += "->";
		if (root.left != null) {
			List<String> subLeft = binaryTreePaths(root.left);
			for (int i = 0; i < subLeft.size(); i++)
				ret.add(header + subLeft.get(i));
		}
		if (root.right != null) {
			List<String> subRight = binaryTreePaths(root.right);
			for (int i = 0; i < subRight.size(); i++)
				ret.add(header + subRight.get(i));
		}
		return ret;
	}

//	https://leetcode.com/problems/expression-add-operators/

	public void addOperatorsHelper(String num, int start, int length, long lowAcc, long acc, long target, char lowOp, StringBuilder st, List<String> ret) {
		if (start >= length) {
			//final calculation
			if (acc == target) {
				ret.add(st.toString());
			}
			return;
		}

		int checkLength = length;
		//so that we won't have numbers with leading 0s...
		if (num.charAt(start) == '0')
			checkLength = start + 1;
		for (int i = start; i < checkLength; i++) {
			long current = Long.parseLong(num.substring(start, i + 1));

//			+1+0+5, +1+5, -1-0+5, +10-5 how to bypass initall sign??
//			how to by pass number like 05???
			addOperatorsHelper(num, i + 1, length, acc, acc + current, target, '+', new StringBuilder(st + "+" + current), ret);
			addOperatorsHelper(num, i + 1, length, acc, acc - current, target, '-', new StringBuilder(st + "-" + current), ret);
			switch (lowOp) {
				case '+':
					addOperatorsHelper(num, i + 1, length, lowAcc, lowAcc + (acc - lowAcc) * current, target, lowOp, new StringBuilder(st + "*" + current), ret);
					break;
				case '-':
					addOperatorsHelper(num, i + 1, length, lowAcc, lowAcc + (acc - lowAcc) * current, target, lowOp, new StringBuilder(st + "*" + current), ret);
					break;
				default:
					addOperatorsHelper(num, i + 1, length, acc, acc * current, target, lowOp, new StringBuilder(st + "*" + current), ret);
					break;
			}
		}
	}

	//	https://leetcode.com/problems/expression-add-operators/
//	https://segmentfault.com/a/1190000003797204
//	http://www.cnblogs.com/grandyang/p/4814506.html
	public List<String> addOperators(String num, int target) {
		List<String> ret = new ArrayList<>();
		if (num == null || num.length() <= 0)
			return ret;
		int length = num.length();
		int checkLength = length;
		//so that we won't have numbers with leading 0s...
		if (num.charAt(0) == '0')
			checkLength = 0 + 1;
		for (int i = 0; i < checkLength; i++) {
			//BUG PARSE....
			long current = Long.parseLong(num.substring(0, i + 1));
			addOperatorsHelper(num, i + 1, length, current, current, target, ' ', new StringBuilder(num.substring(0, i + 1)), ret);
		}
		return ret;
	}


	//	Largest BST Subtree, 333
//Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
//	Note:
//	A subtree must include all of its descendants.
//	Here's an example:
//			10
//			/ \
//			5  15
//			/ \   \
//			1   8   7
//	The Largest BST Subtree in this case is the highlighted one.
//	The return value is the subtree's size, which is 3.
//	Hint:
//	You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.
//	Follow up:
//	Can you figure out ways to solve it with O(n) time complexity?
	public static class LargestBSTSubtreeNode {
		int size;
		boolean isBst;

		LargestBSTSubtreeNode(int size, boolean isBst) {
			this.size = size;
			this.isBst = isBst;
		}
	}

	LargestBSTSubtreeNode largestBSTSubtreeHelper(TreeNode root, long min, long max) {
		if (root == null)
			return new LargestBSTSubtreeNode(0, true);

		LargestBSTSubtreeNode left = largestBSTSubtreeHelper(root.left, min, root.val);
		LargestBSTSubtreeNode right = largestBSTSubtreeHelper(root.right, root.val, max);
		LargestBSTSubtreeNode ret = new LargestBSTSubtreeNode(1, true);
		if (root.val < max && root.val > min)
			ret.isBst = true;
		else
			ret.isBst = false;

		ret.isBst &= (left.isBst && right.isBst);
		if (ret.isBst) {
			ret.size += (left.size + right.size);
		} else {
			ret.size = Math.max(1, Math.max(left.size, right.size));
		}
		return ret;
	}

	public int largestBSTSubtree(TreeNode root) {
		if (root == null)
			return 0;
		return largestBSTSubtreeHelper(root, Long.MIN_VALUE, Long.MAX_VALUE).size;
	}

	//	Maximum Size Subarray Sum Equals k, 325
//Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
//
//	Example 1:
//	Given nums = [1, -1, 5, -2, 3], k = 3,
//			return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
//
//	Example 2:
//	Given nums = [-2, -1, 2, 1], k = 1,
//			return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
//
//	Follow Up:
//	Can you do it in O(n) time?
	public int maxSubArrayLen(int[] nums, int k) {

		//Hash is always so helpful....
		int sum = 0, max = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			if (sum == k) max = i + 1;
			else if (map.containsKey(sum - k))
				max = Math.max(max, i - map.get(sum - k));
			if (!map.containsKey(sum)) map.put(sum, i);
		}
		return max;
	}


//	Number of Connected Components in an Undirected Graph, 323
//Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
//	Example 1:
//			0          3
//			|          |
//			1 --- 2    4
//	Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
//	Example 2:
//			0           4
//			|           |
//			1 --- 2 --- 3
//	Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
//	Note:
//	You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
//	https://leetcode.com/discuss/76594/short-union-find-in-python-ruby-c
//	http://algs4.cs.princeton.edu/lectures/15UnionFind.pdf

	//	http://algs4.cs.princeton.edu/15uf/WeightedQuickUnionUF.java.html
	public class WeightedQuickUnionUF {
		private int[] parent;   // parent[i] = parent of i
		private int[] size;     // size[i] = number of sites in subtree rooted at i
		private int count;      // number of components

		public WeightedQuickUnionUF(int N) {
			count = N;
			parent = new int[N];
			size = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		public int count() {
			return count;
		}

		public int find(int p) {
			//This part is Cool,,,, flatten the tree while finding the parent
			while (p != parent[p])
				p = parent[p];
			return p;
		}

		public boolean connected(int p, int q) {
			return find(p) == find(q);
		}

		public void union(int p, int q) {
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) return;

			// make smaller root point to larger one
			if (size[rootP] < size[rootQ]) {
				parent[rootP] = rootQ;
				size[rootQ] += size[rootP];
			} else {
				parent[rootQ] = rootP;
				size[rootP] += size[rootQ];
			}
			//COOL. Why not get component number on the way...
			count--;
		}
	}

	public int countComponents(int n, int[][] edges) {
		if (n <= 0 || edges == null)
			return 0;

//		Quick union with path compression. Just after computing the root of p, set the id[] of each examined node to point to that root.
//		Two-pass implementation: add second loop to find() to set the id[] of each examined node to the root.
//		Simpler one-pass variant (path halving): Make every other node in path point to its grandparent.
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
		for (int i = 0; i < edges.length; i++)
			uf.connected(edges[i][0], edges[i][1]);

		return uf.count();
	}

//	public int countComponents(int n, int[][] edges) {
//		int[] p = new int[n];
//		for (int i=0; i<n; i++)
//			p[i] = i;
//		for (int[] edge : edges) {
//			int v = edge[0], w = edge[1];
//			while (p[v] != v) v = p[v] = p[p[v]];
//			while (p[w] != w) w = p[w] = p[p[w]];
//			p[v] = w;
//			if (v != w)
//				n--;
//		}
//		return n;
//	}

	//	Generalized Abbreviation
//	Write a function to generate the generalized abbreviations of a word.
//	Example:
//	Given word = "word", return the following list (order does not matter):
//	["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", >"1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
//	https://leetcode.com/discuss/76783/easiest-14ms-java-solution-beats-100%25
	public List<String> generateAbbreviations(String word) {
		//No idea what is talking here..
		return null;
	}

//	Shortest Distance from All Buildings, 317
//You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
//
//	Each 0 marks an empty land which you can pass by freely.
//	Each 1 marks a building which you cannot pass through.
//	Each 2 marks an obstacle which you cannot pass through.
//	For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
//
//			1 - 0 - 2 - 0 - 1
//			|   |   |   |   |
//			0 - 0 - 0 - 0 - 0
//			|   |   |   |   |
//			0 - 0 - 1 - 0 - 0
//	The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
//
//	Note:
//	There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

	static class ShortestDistance {
		int x;
		int y;

		ShortestDistance(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public void buildShortestDistanceMap(int[][] grid, int i, int j, int m, int n, int[][] map) {
		//BFS
		LinkedList<ShortestDistance> q = new LinkedList<>();
		int level = 0;
		q.add(new ShortestDistance(i, j));

		while (!q.isEmpty()) {
			LinkedList<ShortestDistance> next_q = new LinkedList<>();
			while (!q.isEmpty()) {
				ShortestDistance s = q.poll();
				i = s.x;
				j = s.y;
//				System.out.println(i+"\t"+j);
				map[i][j] = level;
				//UP
				if (i != 0 && grid[i - 1][j] == 0 && map[i - 1][j] == 0)
					next_q.add(new ShortestDistance(i - 1, j));
				//DOWN
				if (i + 1 != m && grid[i + 1][j] == 0 && map[i + 1][j] == 0)
					next_q.add(new ShortestDistance(i + 1, j));

				//LEFT
				if (j != 0 && grid[i][j - 1] == 0 && map[i][j - 1] == 0)
					next_q.add(new ShortestDistance(i, j - 1));

				//RIGHT
				if (j + 1 != n && grid[i][j + 1] == 0 && map[i][j + 1] == 0)
					next_q.add(new ShortestDistance(i, j + 1));
			}
			q = next_q;
			level++;
		}

	}

	public int shortestDistance(int[][] grid) {

		int ret = -1;
		int m = grid.length, n = grid[0].length;
		List<int[][]> list = new ArrayList<>();
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					//Build a map for each building
					int[][] map = new int[m][n];
//					LinkedList<Integer> queue = new LinkedList<Integer>();
//	queue.offer(i*n+j); //You can use one dimention array to represent two dimention
//					http://www.cnblogs.com/EdwardLiu/p/5094091.html, Just accumulate on previous ONE, save spaces...
					buildShortestDistanceMap(grid, i, j, m, n, map);
					map[i][j] = -1;
//					for (int u = 0; u < m; u++) {
//						for (int v = 0; v < n; v++)
//							System.out.print(map[u][v] + "\t");
//						System.out.println();
//					}
//					System.out.println("******");

					list.add(map);
				}
			}

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) {
					int sum = 0;
					boolean succ = true;
					for (int k = 0; k < list.size(); k++) {
						if (list.get(k)[i][j] > 0)
							sum += list.get(k)[i][j];
						else {
							succ = false;
							break;
						}
					}

					if (succ) {
						if (ret < 0)
							ret = sum;
						else
							ret = Math.min(ret, sum);
					}
				}
			}
		return ret;
	}


	//	Binary Tree Vertical Order Traversal, 314
//	http://buttercola.blogspot.com/2016/01/leetcode-binary-tree-vertical-order.html, clear problem desciption
//	http://algorithms.tutorialhorizon.com/print-the-binary-tree-in-vertical-order-path/, better problem desciption
//Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
//
//	If two nodes are in the same row and column, the order should be from left to right.
//
//			Examples:
//	Given binary tree [3,9,20,null,null,15,7],
//
//			3
//			/ \
//			9  20
//			/  \
//			15   7
//
//
//			return its vertical order traversal as:
//
//			[
//			[9],
//			[3,15],
//			[20],
//			[7]
//			]
//
//
//	Given binary tree [3,9,20,4,5,2,7],
//
//	_3_
//	/   \
//			9    20
//			/ \   / \
//			4   5 2   7
//
//
//			return its vertical order traversal as:
//
//			[
//			[4],
//			[9],
//			[3,5,2],
//			[20],
//			[7]
//			]
	void verticalOrderHelper(TreeMap<Integer, List<Integer>> map, TreeNode root, int pos) {
		if (root == null)
			return;
		if (!map.containsKey(pos)) {
			map.put(pos, new ArrayList<>());
		}
		map.get(pos).add(root.val);
		verticalOrderHelper(map, root.left, pos - 1);
		verticalOrderHelper(map, root.left, pos + 1);
	}

	//	http://buttercola.blogspot.com/2016/01/leetcode-binary-tree-vertical-order.html
//	http://geth1b.com/leetcode/314-binary-tree-vertical-order-traversal/
	public List<List<Integer>> verticalOrder(TreeNode root) {

		//Wired order
		//Below code is incorrect? Why?
/* 4 will be printed before 6 if using pre-order. We have to do it with level? How? Store each node with pos information together...
	 5
	/ \
   1   6
	\
	 3
	 /\
	2  4
*/
		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
		verticalOrderHelper(map, root, 0);
		List<List<Integer>> ret = new ArrayList<>();
		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			ret.add(entry.getValue());
		}
		return ret;
	}


	//	Sparse Matrix Multiplication, 311
//Given two sparse matrices A and B, return the result of AB.
	public int[][] multiply(int[][] A, int[][] B) {
//		https://leetcode.com/discuss/71912/easiest-java-solution
		int m = A.length, n = A[0].length, nB = B[0].length;
		int[][] C = new int[m][nB];

		//See what's the differences between the normal orders
		//Avoid unnecessary multiplication
		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				if (A[i][k] != 0) {
					for (int j = 0; j < nB; j++) {
						if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
		return C;
	}

	public boolean isPowerOfFour(int num) {
		if (num <= 0)
			return false;
		if (num == 1)
			return true;
//		System.out.println(Integer.toHexString(num & 0b11));
		//What determins a power of 4????
		int mask1 = 0xAAAAAAAA;
		if ((num & mask1) != 0)
			return false;
		int mask = 0x55555555;
		num &= mask;
//		System.out.println(Integer.toHexString(num));
		return (num & (num - 1)) == 0;
	}

	//	https://leetcode.com/problems/count-complete-tree-nodes/
//	http://bookshadow.com/weblog/2015/06/06/leetcode-count-complete-tree-nodes/
//	http://yucoding.blogspot.com/2015/10/leetcode-question-question-count.html
//	A very clean ans, pretty Cool stuff
//	https://leetcode.com/discuss/78370/java-solution-beats-90%25
//	http://bookshadow.com/weblog/2015/06/06/leetcode-count-complete-tree-nodes/
//	高度为h的完全二叉树，其节点个数等于高度为h-1的满二叉树的节点个数 + 最后一层的节点个数。
//	因此，只需要二分枚举第h层的节点个数即可。
//	将第h层的节点从0至2^h - 1依次编号（根节点的层数记为0）
//	若用0表示左孩子，1表示右孩子，则这些编号的二进制形式是从根节点出发到叶子节点的“路径”。
//	例如高度为2，包含6个节点的完全二叉树：
//	Lv0        1
//			/    \
//	Lv1     2      3
//			/  \   /  \
//	Lv2   4   5  6   -
//	No.   00  01 10
//	从1号节点（根节点）出发，到第5号节点的路径为01（左右），同时该节点为第2层的第2个节点。
// <-- With this encoding, you can understand how binary search works...
	public int countNodes(TreeNode root) {
		if (root == null)
			return 0;

		// TLE????
		/*
		int ret = 0;
		Deque<TreeNode> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			Deque<TreeNode> next_q = new LinkedList<>();
			boolean breakInMiddle = false;
			while (!q.isEmpty()) {
				TreeNode cur = q.poll();
				ret++;
				if (cur.left != null)
					next_q.offer(cur.left);
				else {
					breakInMiddle = true;
					break;
				}
				if (cur.right != null)
					next_q.offer(cur.right);
				else {
					breakInMiddle = true;
					break;
				}
			}

			if (breakInMiddle)
				return ret + q.size() + next_q.size();
			q = next_q;
		}

		return ret;
		*/

		// TLE as well, see Java require explicit conversion...
//		int lv = 0, rv = 0;
//		for (TreeNode tmp = root; tmp != null; tmp=tmp.left, lv++);
//		for (TreeNode tmp = root; tmp != null; tmp=tmp.right, rv++);
//		if (lv == rv) return (int)Math.pow(2, lv) - 1;
//		return 1 + countNodes(root.left) + countNodes(root.right);

		//Use Binary Search

		return 0;
	}

	//	https://leetcode.com/problems/reverse-vowels-of-a-string/
	public String reverseVowels(String s) {
		char[] array = s.toCharArray();
		int i = 0, j = array.length - 1;
		while (i < j) {
			boolean found = false;
			while (i < j && !found) {
				switch (array[i]) {
					case 'a':
					case 'e':
					case 'i':
					case 'o':
					case 'u':
						found = true;
						break;
					default:
						++i;
						break;
				}
			}

			found = false;
			while (i < j && !found) {
				switch (array[j]) {
					case 'a':
					case 'e':
					case 'i':
					case 'o':
					case 'u':
						found = true;
						break;
					default:
						--j;
						break;
				}
			}
			if (i < j) {
				char tmp = array[j];
				array[j] = array[i];
				array[i] = tmp;
			}
		}
		return new String(array);
	}

//	https://leetcode.com/problems/shortest-palindrome/

	//	http://yucoding.blogspot.com/2015/10/leetcode-question-shortest-palindrome.html
	public String shortestPalindrome(String s) {
		if (s == null)
			return null;
		int length = s.length();
		if (length <= 1)
			return s;
		/*
		//Two D array use too much memory...
		boolean [][] table = new boolean[length][length];
		for (int i = 0; i < length; i++)
			table[i][i] = true;
		for (int i = length - 2; i >= 0; i--) {
			for (int j = i + 1; j < length; j++) {
				if (j - i == 1)
					table[i][j] = s.charAt(i) == s.charAt(j);
				else
					table[i][j] = (s.charAt(i) == s.charAt(j)) && (table[i+1][j-1]);
			}
		}


		int k = length - 1;
		while (k > 0 && !table[0][k])
			--k;
		if (k == length - 1)
			return s;
		else
			return (new StringBuilder(s.substring(k+1,length)).reverse().toString()) + s;
		*/

		boolean[] table = new boolean[length];
		table[length - 1] = true;
		for (int i = length - 2; i >= 0; i--) {
			//fill table[i] to table[length-1]
			table[i] = true;
			for (int j = length - 1; j > i; j--) {
//				if (j == i + 1)
//					table[j] = s.charAt(i) == s.charAt(j);
//				else
//					table[j] = (s.charAt(i) == s.charAt(j)) && table[j-1];

				//Still TLE???
				if (s.charAt(i) == s.charAt(j)) {
					if (j == i + 1)
						table[j] = true;
					else
						table[j] = table[j - 1];
				} else {
					table[j] = false;
				}
			}
		}
		int k = length - 1;
		while (k > 0 && !table[k])
			--k;
		if (k == length - 1)
			return s;
		else
			return (new StringBuilder(s.substring(k + 1, length)).reverse().toString()) + s;
	}

//	Number of Islands II, 305
//	Problem Description:
//	A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//	Example:
//	Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
//	Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
//
//			0 0 0
//			0 0 0
//			0 0 0
//	Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
//	1 0 0
//			0 0 0   Number of islands = 1
//			0 0 0
//	Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
//	1 1 0
//			0 0 0   Number of islands = 1
//			0 0 0
//	Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
//	1 1 0
//			0 0 1   Number of islands = 2
//			0 0 0
//	Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
//	1 1 0
//			0 0 1   Number of islands = 3
//			0 1 0
//	We return the result as an array: [1, 1, 2, 3]
//	Challenge:
//	Can you do it in time complexity O(k log mn), where k is the length of the positions?

	//Very very good notes....
//	https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
	//Two-D is actually one D.... BUGAR...
//	When programming, convenient to name them 0 to N-1.
//			• Hide details not relevant to union-find.
//	• Integers allow quick access to object-related info.
//			• Could use symbol table to translate from object names
//	Each union command reduces by 1 the number of components
//	http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
//	http://introcs.cs.princeton.edu/java/24percolation/
	//Below sol only works if you sort position first...
//	https://leetcode.com/discuss/69572/easiest-java-solution-with-explanations
	int numIslands2CooridnateConverter(int i, int j, int n) {
		return i * n + j;
	}

	int parent(int i, int[] ids) {
		while (i != ids[i]) {
			i = ids[i] = ids[ids[i]];
		}
		return i;
	}

	void union(int i, int j, int[] ids) {
		int pi = parent(i, ids);
		int pj = parent(j, ids);
		ids[pi] = ids[pj];
	}

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		//index mapping
		//i*n+j, 0<=i<m, 0<=j<n;
		//from total to two D, (total/n, total%n) -> i,j
		int length = m * n;
		int[] ids = new int[length];
		for (int i = 0; i < length; i++)
			ids[i] = i;
		int[][] directions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < positions.length; i++) {
			//union positions[i][0],positions[i][1]
//			System.out.println(i);
			int index = positions[i][0] * n + positions[i][1];
			for (int k = 0; k < directions.length; k++) {
				int x = positions[i][0] + directions[k][0];
				int y = positions[i][1] + directions[k][1];
				if (x >= 0 && x < m && y >= 0 && y < n) {
					int index2 = x * n + y;
					if (set.contains(index2)) {
//						System.out.println(index + "\t" + index2);
						//You should only union when they are not belongs to the same region...
						union(index, index2, ids);
					}
				}
			}
			set.add(index);
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < positions.length; i++) {
			int index = positions[i][0] * n + positions[i][1];
			list.add(parent(index, ids));
		}
//		Iterator inter = set.iterator();
//		while (inter.hasNext()) {
//			list.add(parent((int)(inter.next()), ids));
//		}
		return list;
	}


//	Smallest Rectangle Enclosing Black Pixels, 302
//An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
//	For example, given the following image:
//			[
//			"0010",
//			"0110",
//			"0100"
//			]
//	and x = 0, y = 2,
//	Return 6.


//	Theorem
//	If there are only one black pixel region, then in a projected 1D array all the black pixels are connected.
//	This means we can do a binary search in each half to find the boundaries, if we know one black pixel's position. And we do know that.
//	To find the left boundary, do the binary search in the [0, y) range and find the first column vector who has any black pixel.
//	To determine if a column vector has a black pixel is O(m) so the search in total is O(m log n)
//	We can do the same for the other boundaries. The area is then calculated by the boundaries. Thus the algorithm runs in O(m log n + n log m)
//	https://leetcode.com/discuss/85146/java-binary-search-o-nlogm-mlogn-runtime
	//Check col/row manually...


	//Below method is too slow..
	public void minAreaHelperBFS(char[][] image, boolean[][] flags, int[] maxDirects, int[][] directions, int x, int y) {
		flags[x][y] = true;
		//上
		if (x < maxDirects[0])
			maxDirects[0] = x;
		//下
		if (x > maxDirects[1])
			maxDirects[1] = x;
		//左
		if (y < maxDirects[2])
			maxDirects[2] = y;
		//右
		if (y > maxDirects[3])
			maxDirects[3] = y;

		for (int i = 0; i < directions.length; i++) {
			int u = x + directions[i][0];
			int v = y + directions[i][1];
			if (u >= 0 && u < image.length && v >= 0 && v < image[0].length && !flags[u][v] && image[u][v] == '1') {
				minAreaHelperBFS(image, flags, maxDirects, directions, u, v);
			}
		}
	}

	public int minArea(char[][] image, int x, int y) {
		//assume x,y, image are all valid..
		//Left, Right, Up, Down
		int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		//上,下,左,右
		int[] maxDirects = new int[4];
		maxDirects[0] = x;
		maxDirects[1] = x;
		maxDirects[2] = y;
		maxDirects[3] = y;
		//Or use a hashset to indicate it...
		//Or change image value to sth else...
		boolean[][] flags = new boolean[image.length][image[0].length];
		minAreaHelperBFS(image, flags, maxDirects, directions, x, y);
		return (maxDirects[1] - maxDirects[0] + 1) * (maxDirects[3] - maxDirects[2] + 1);
	}


//	Binary Tree Longest Consecutive Sequence, 298
//Given a binary tree, find the length of the longest consecutive sequence path.
//The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
//	For example,
//	1
//	 \
//	  3
//	 / \
//	2   4
//	     \
//		  5
//	Longest consecutive sequence path is 3-4-5, so return 3.
//			2
//			\
//			3
//			/
//		   2
//		  /
//		 1
//	Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.
	//The problem with the example is too difficult to understand, no idea what hell is that. SKIP it.
//	http://buttercola.blogspot.com/2015/12/blog-post.html
	static class LongestConsecutiveTreeNode {
		int local;
		int global;
		LongestConsecutiveTreeNode() {
			local = 0;
			global = 0;
		}
	}
	LongestConsecutiveTreeNode longestConsecutiveHelper(TreeNode root) {
		//if (root == null) ?? is that possible???
		LongestConsecutiveTreeNode ret = new LongestConsecutiveTreeNode();

		//no null here.
		LongestConsecutiveTreeNode left = null, right = null;
		if (root.left != null) {
			left = longestConsecutiveHelper(root.left);
			if (root.left.val == root.val + 1)
				ret.local = 1 + left.local;
		}

		if (root.right != null) {
			right = longestConsecutiveHelper(root.right);
			if (root.right.val == root.val + 1)
				ret.local = Math.max(ret.local, 1 + right.local);
		}
		ret.global = Math.max(ret.local, Math.max(left != null ? left.global : 1, right != null ? right.global : 1));
		return ret;
	}

	LongestConsecutiveTreeNode longestConsecutiveHelper2(TreeNode root) {
		LongestConsecutiveTreeNode ret = new LongestConsecutiveTreeNode();
		//allow null here
		if (root == null)
			return ret;//it will be init as 0s.

		//no null here.
		LongestConsecutiveTreeNode left = longestConsecutiveHelper2(root.left), right = longestConsecutiveHelper2(root.right);
		//we need check it anyway
		ret.local = 1 + Math.max((root.left != null && root.left.val == root.val + 1) ? left.local : 0,
				(root.right != null && root.right.val == root.val + 1) ? right.local : 0);
		//global is cleaner in this case...
		ret.global = Math.max(ret.local, Math.max(left.global, right.global));
		return ret;
	}
	//Actually we can save the return object with giving accumulated length from the input.

//	public class Logger {
//		int max = 0;
//		public int longestConsecutive(TreeNode root) {
//			if(root==null) return 0;
//			helper(root,0,root.val);
//			return max;
//		}
//
//		public void helper(TreeNode root,int cur, int target){
//			if(root==null) return;
//			if(root.val==target) cur++;
//			else cur=1;
//			max = Math.max(cur,max);
//			helper(root.left, cur, root.val+1);
//			helper(root.right, cur, root.val+1);
//		}
//	}

	public int longestConsecutive(TreeNode root) {
		if (root == null)
			return 0;
		return 1;
	}

	//	https://leetcode.com/problems/search-a-2d-matrix-ii/
	public boolean searchMatrix(int[][] matrix, int target) {
//		从右上角开始, 比较target 和 matrix[i][j]的值. 如果小于target, 则该行不可能有此数,  所以i++; 如果大于target, 则该列不可能有此数, 所以j--. 遇到边界则表明该矩阵不含target.
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;

		int i = matrix.length - 1;
		int j = 0;
		while (i >= 0 && j < matrix[0].length) {
			if (matrix[i][j] == target)
				return true;
			else if (matrix[i][j] < target)
				++j;
			else
				--i;
		}
		return false;
	}

	//	https://leetcode.com/problems/sliding-window-maximum/
	//This a very good question...
//	http://articles.leetcode.com/sliding-window-maximum/
//	http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
//	http://bookshadow.com/weblog/2015/07/18/leetcode-sliding-window-maximum/
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k <= 0)
			return new int[0];
		if (k == 1)
			return nums;
		int[] ret = new int[nums.length - k + 1];
		Deque<Integer> dq = new ArrayDeque<>();
		//build
		int i = 0;
		dq.add(nums[i++]);
		while (i < k) {

			//You can make below code more cleaner...
			if (dq.getLast() > nums[i])
				dq.add(nums[i]);
			else {
				//??Why no equals
				while (!dq.isEmpty() && dq.getLast() < nums[i])
					dq.removeLast();
				dq.add(nums[i]);
			}
			++i;
		}

		ret[0] = dq.getFirst();
		for (i = k; i < nums.length; i++) {
			//remove nums[i-k]
			//add nums[i]
			//fill ret[i-k+1]
			if (nums[i - k] == dq.getFirst())
				dq.removeFirst();
			if (dq.getLast() > nums[i])
				dq.add(nums[i]);
			else {
				//??Why no equals
				while (!dq.isEmpty() && dq.getLast() < nums[i])
					dq.removeLast();
				dq.add(nums[i]);
			}
			ret[i - k + 1] = dq.getFirst();
		}
		return ret;
	}

	//	https://leetcode.com/problems/product-of-array-except-self/
	public int[] productExceptSelf(int[] nums) {
		int length = nums.length;
		int[] ret = new int[length];
		ret[length - 1] = 1;
		int i = length - 2;
		while (i >= 0) {
			ret[i] = ret[i + 1] * nums[i + 1];
			--i;
		}
		int acc = 1;
		for (i = 1; i < length; i++) {
			acc *= nums[i - 1];
			ret[i] *= acc;
		}
		return ret;
	}

//	Best Meeting Point, 296
//	A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
//
//	For example, given three people living at (0,0), (0,4), and (2,2):
//
//			1 - 0 - 0 - 0 - 1
//			|   |   |   |   |
//			0 - 0 - 0 - 0 - 0
//			|   |   |   |   |
//			0 - 0 - 1 - 0 - 0
//	The point (0,2) is an ideal meeting point, as the total travel
//	distance of 2+2+2=6 is minimal. So return 6.

	//	二维的等于一维的相加, 一维的最小点必在median点(用反证法可以证明).
//	http://math.stackexchange.com/questions/113270/the-median-minimizes-the-sum-of-absolute-deviations
	public int minTotalDistance(int[][] grid) {
		List<Integer> ipos = new ArrayList<Integer>();
		List<Integer> jpos = new ArrayList<Integer>();
		// 统计出有哪些横纵坐标
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					ipos.add(i);
					jpos.add(j);
				}
			}
		}
		int sum = 0;
		// 计算纵坐标到纵坐标中点的距离，这里不需要排序，因为之前统计时是按照i的顺序??? Really? I dont' think so.
		for (Integer pos : ipos) {
			sum += Math.abs(pos - ipos.get(ipos.size() / 2));
		}
		// 计算横坐标到横坐标中点的距离，这里需要排序，因为统计不是按照j的顺序??? Really? I dont' think so.
		Collections.sort(jpos);
		for (Integer pos : jpos) {
			sum += Math.abs(pos - jpos.get(jpos.size() / 2));
		}
		return sum;
	}

	//	Flip Game, 293
//	Problem Description:
//	You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
//	Write a function to compute all possible states of the string after one valid move.
//	For example, given s = "++++", after one move, it may become one of the following states:
//			[
//			"--++",
//			"+--+",
//			"++--"
//			]
//	If there is no valid move, return an empty list [].
	//Too easy, The problem only asks for flipping "++" into "--", NOT -- to ++. So the solution is to move all consecutive "++" into "--".
	public List<String> generatePossibleNextMoves(String s) {
		List<String> result = new ArrayList<>();
		if (s == null || s.length() < 2) {
			return result;
		}

		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
				String s1 = s.substring(0, i);
				String s2 = "--";
				String s3 = s.substring(i + 2);
				String temp = s1 + s2 + s3;
				result.add(temp);
			}
		}

		return result;
	}

	//	Flip Game II, 294
//	You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
//	Write a function to determine if the starting player can guarantee a win.
//	For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
//	Follow up:
//	Derive your algorithm's runtime complexity.
//	https://leetcode.com/discuss/64350/short-java-%26-ruby?show=64350#q64350
//The idea is very straightforward: if you can make s non-winnable by a move, then you will win.
//	https://leetcode.com/discuss/64344/theory-matters-from-backtracking-128ms-to-dp-0ms
	//You can use HashMap to sotre historical results...
	public boolean canWin(String s) {
		for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; )
//		You win if and only if you can make some move so that the resulting state can't be won. One thing I do differently from everyone else so far is that I replace "++" with "-" instead of "--".
			//Just make it shorter...
			if (!canWin(s.substring(0, i) + "-" + s.substring(i + 2)))
				return true;
		return false;
	}


//	Word Pattern II, 291
//Given a pattern and a string str, find if str follows the same pattern.
//	Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
//	Examples:
//	pattern = "abab", str = "redblueredblue" should return true. (a->red, b->blue)
//	pattern = "aaaa", str = "asdasdasdasd" should return true. (a->asd)
//	pattern = "aabb", str = "xyzabcxzyabc" should return false.
//	Notes: You may assume both pattern and str contains only lowercase letters.
//No idea what the hell is talking here.

	//Below is a good one for explaining the problems..
	//https://leetcode.com/discuss/63252/share-my-java-backtracking-solution
	public boolean wordPatternMatchHelper(String pattern, int i, String str, int j, HashMap<Character, String> map) {
		if (i == pattern.length() && j == str.length())
			return true;
		if (i == pattern.length() || j == str.length() || (pattern.length() - i) > (str.length() - j))
			return false;
		if (map.containsKey(pattern.charAt(i))) {
			//mapping check
			String tmp = map.get(pattern.charAt(i));
			if ((str.length() - j) < tmp.length() || !tmp.equals(str.substring(j, j + tmp.length())))
				return false;
			return wordPatternMatchHelper(pattern, i + 1, str, j + tmp.length(), map);
		} else {
			for (int k = j; k < str.length(); k++) {
				String tmp = str.substring(j, k + 1);

				if (map.containsValue(tmp))
					continue;

				map.put(pattern.charAt(i), tmp);
				if (wordPatternMatchHelper(pattern, i + 1, str, k + 1, map))
					return true;
			}
			map.remove(pattern.charAt(i));
		}
		return false;
	}

	public boolean wordPatternMatch(String pattern, String str) {
		//as both are not null
		if (pattern.length() > str.length())
			return false;
		HashMap<Character, String> map = new HashMap<>();
		return wordPatternMatchHelper(pattern, 0, str, 0, map);
	}


//	Unique Word Abbreviation, 288
//An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
//	a) it                      --> it    (no abbreviation)
//	1
//	b) d|o|g                   --> d1g
//	1    1  1
//			1---5----0----5--8
//	c) i|nternationalizatio|n  --> i18n
//	1
//			1---5----0
//	d) l|ocalizatio|n          --> l10n
//	Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
//	Example:
//	Given dictionary = [ "deer", "door", "cake", "card" ]
//
//	isUnique("dear") -> false
//	isUnique("cart") -> true
//	isUnique("cane") -> false
//	isUnique("make") -> true
	//No idea what the hell is talking here...
//	Idea:
//	You build a dictionary first in which key is every word first letter and last letter plus the length between them. Then a word (transformed into first letter+length+last letter) is unique if:
//			1. the dictionary doesn’t have the word as key at all
//	2. the dictionary has the word as key. But the value corresponding to the key is a set only containing the word. That means, the current word is the only word with same transformation in dictionary.
//	public boolean isUnique(String word) {
//		return false;
//	}

	//	Walls and Gates, 286
//	You are given a m x n 2D grid initialized with these three possible values.
//	-1 - A wall or an obstacle.
//	0 - A gate.
//	INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than2147483647.
//	Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
//	For example, given the 2D grid:
//	INF  -1  0  INF
//	INF INF INF  -1
//	INF  -1 INF  -1
//	0  -1 INF INF
//	After running your function, the 2D grid should be:
//			3  -1   0   1
//			2   2   1  -1
//			1  -1   2  -1
//			0  -1   3   4
//	https://leetcode.com/discuss/60149/straightforward-python-solution-without-recursion
	void wallsAndGatesHelper(int[][] rooms, int i, int j, int[][] directions, int level) {
		rooms[i][j] = level;
		//Below is DFS, not BFS. BOYs....
		/*
		for (int k = 0; k < directions.length; k++) {
			int r = i + directions[k][0];
			int c = j + directions[k][1];
			if (r >= 0 && r < rooms.length && c >= 0 && c < rooms[0].length && rooms[r][c] > 0 && rooms[r][c] > level) {
				wallsAndGatesHelper(rooms, r, c, directions, level+1);
			}
		}
		*/
		List<Integer> queue = new LinkedList<>();
//		HashSet<Integer> set = new HashSet<>(); I think we don't need this set here as we use a level check
		int n = rooms[0].length;
		queue.add(i * n + j);
		level = 0;
		while (!queue.isEmpty()) {
			List<Integer> next_queue = new LinkedList<>();
			while (!queue.isEmpty()) {
				int m = queue.remove(0);
//				set.add(m);
				i = m / n;
				j = m % n;
				rooms[i][j] = level;
				for (int k = 0; k < directions.length; k++) {
					int r = i + directions[k][0];
					int c = j + directions[k][1];
					if (r >= 0 && r < rooms.length && c >= 0 && c < rooms[0].length && rooms[r][c] > 0 && rooms[r][c] > level) {
						next_queue.add(r * n + c);
					}
				}
			}
			queue = next_queue;
			level++;
		}
	}

	public void wallsAndGates(int[][] rooms) {
		if (rooms == null)
			return;
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for (int i = 0; i < rooms.length; i++)
			for (int j = 0; j < rooms[0].length; j++) {
				//Gate
				if (rooms[i][j] == 0) {
					wallsAndGatesHelper(rooms, i, j, directions, 0);
				}
			}
	}

	//	Inorder Successor in BST, 285
//Problem Description:
//	Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
//	Note: If the given node has no in-order successor in the tree, return null.
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if (root == null || p == null)
			return null;
		//assume p must be in the tree now..
		TreeNode cur = root, pre = null;

//		There are just two cases:
//		The easier one: p has right subtree, then its successor is just the leftmost child of its right subtree;
//		The harder one: p has no right subtree, then a traversal is needed to find its successor.
//		Traversal: we start from the root, each time we see a node with val larger than p -> val,
// we know this node may be p's successor. So we record it in suc.
// Then we try to move to the next level of the tree: if p -> val > root -> val, which means p is in the right subtree,
// then its successor is also in the right subtree, so we update root = root -> right; if p -> val < root -> val, we update root = root -> left similarly;
// once we find p -> val == root -> val, we know we've reached at p and the current sucis just its successor.
		while (cur != null && !cur.equals(p)) {
			/* Sorry, below logic is wrong...
			pre = cur;
			if (cur.val < p.val)
				cur = cur.left;
			else
				cur = cur.right;
			*/
			if (cur.val < p.val) {
				pre = cur;
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}

		if (cur != null) {
			if (cur.right == null)
				return pre;
			else {
				TreeNode ret = cur.right;
				while (ret.left != null)
					ret = ret.left;
			}
		}
		return null;
	}

//	Wiggle Sort, 280, too native compared with II
//	Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
//	For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
//void wiggleSort(vector<int>& nums) {

//	Find the Celebrity, 277
//	Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1people know him/her but he/she does not know any of them.
//	Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
//	You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
//	Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
//	双指针，O(n)时间，O(1)空间。
//int findCelebrity(int n) {
//	int l = 0, r = n - 1;
//	while (l < r) {
//		if (knows(l, r)) ++l;
//		else --r;
//	}
//	for (int i = 0; i < n; ++i) if (i != l) {
//		if (knows(l, i) || !knows(i, l)) return -1;
//	}
//	return l;
//}

//	There is a fence with n posts, each post can be painted with one of the k colors.
//	You have to paint all the posts such that no more than two adjacent fence posts have the same color.
//	Return the total number of ways you can paint the fence.
//	Note:n and k are non-negative integers.

//	这种给定一个规则，计算有多少种结果的题目一般都是动态规划，因为我们可以从这个规则中得到递推式。根据题意，不能有超过连续两根柱子是一个颜色，也就意味着第三根柱子要么根第一个柱子不是一个颜色，要么跟第二根柱子不是一个颜色。如果不是同一个颜色，计算可能性的时候就要去掉之前的颜色，也就是k-1种可能性。假设dp[1]是第一根柱子及之前涂色的可能性数量，dp[2]是第二根柱子及之前涂色的可能性数量，则dp[3]=(k-1)*dp[1] + (k-1)*dp[2]。
//	递推式有了，下面再讨论下base情况，所有柱子中第一根涂色的方式有k中，第二根涂色的方式则是k*k，因为第二根柱子可以和第一根一样。

	//	设S(n)表示当前杆和前一个杆颜色相同的个数，D(n)表示当前杆和前一个颜色不相同的个数。
//	则递推关系式为：
//	S(n) = D(n - 1)， 即若当前杆和前一个杆颜色相同的个数等于前一个杆和再前一个杆颜色不相同的个数。
//	D(n) = (k - 1) * (D(n - 1) + S(n - 1))，即前一个杆和再前一个杆所有可能的颜色组合，乘以当前杆与前一个杆颜色不相同的个数，即（k - 1）。
//	用两个变量记录D和S，并交替更新即可。
	public int numWays(int n, int k) {
		if (n == 0 || k == 0)
			return 0;
		if (n == 1)
			return k;
		int lastS = k;
		int lastD = k * (k - 1);
		for (int i = 2; i < n; i++) {
			int tempD = (k - 1) * (lastS + lastD);
			lastS = lastD;
			lastD = tempD;
		}
		return lastS + lastD;
	}

//	Closest Binary Search Tree Value I, 270
//Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
//Note: Given target value is a floating point. You are guaranteed to have only one unique value in the BST that is closest to the target.

	//	http://buttercola.blogspot.com/2015/09/leetcode-closest-binary-search-tree.html
	//OR recursions....
//	https://segmentfault.com/a/1190000003797291
	public int closestValue(TreeNode root, double target) {
		boolean flag;
		if (root.val < target)
			flag = true;
		else
			flag = false;

		double delta = Math.abs(target - root.val);
		int ret = root.val;

		while (root != null) {
			//find the first point to switch
			boolean newflag;
			if (root.val < target)
				newflag = true;
			else
				newflag = false;

			double d1 = Math.abs(target - root.val);
			if (d1 < delta) {
				delta = d1;
				ret = root.val;
			}

			if (flag != newflag)
				break;

			if (newflag) {
				root = root.left;
			} else {
				root = root.right;
			}
		}

		if (root != null) {
			if (root.val < target) {
				while (root.right != null)
					root = root.right;
			} else {
				while (root.left != null)
					root = root.left;
			}
			if (Math.abs(root.val - target) < delta)
				ret = root.val;
		}

		return ret;
	}

	//	Closest Binary Search Tree Value II, 272
//Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
//Note: Given target value is a floating point.
// You may assume k is always valid, that is: k ≤ total nodes.
// You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
// Follow up: Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
//	Hint:
//	Consider implement these two helper functions:
//	getPredecessor(N), which returns the next smaller node to N.
//	getSuccessor(N), which returns the next larger node to N.
//	Try to assume that each node has a parent pointer, it makes the problem much easier.
//	Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
//	You would need two stacks to track the path in finding predecessor and successor node separately.
//	https://leetcode.com/discuss/55240/ac-clean-java-solution-using-two-stacks
//	https://leetcode.com/discuss/55682/o-logn-java-solution-with-two-stacks-following-hint
	public static class ClosestKValuesComparator implements Comparator<TreeNode> {
		double target;

		ClosestKValuesComparator(double target) {
			this.target = target;
		}

		public int compare(TreeNode a, TreeNode b) {
			double delta1 = Math.abs(a.val - target);
			double delta2 = Math.abs(b.val - target);
			if (delta2 > delta1)
				return -1;
			else
				return 1;
		}
	}

	void closestKValuesVisit(TreeNode root, double target, PriorityQueue<TreeNode> pq, int k) {
		if (pq.size() < k) {
			pq.add(root);
			return;
		} else {
			double delta = Math.abs(root.val - target);
			if (delta < Math.abs(pq.peek().val - target)) {
				pq.poll();
				pq.add(root);
			}
		}
	}

	//It can be much more easier like this..
//	二叉搜索树的中序遍历就是顺序输出二叉搜索树，所以我们只要中序遍历二叉搜索树，同时维护一个大小为K的队列，前K个数直接加入队列，之后每来一个新的数（较大的数），如果该数和目标的差，相比于队头的数离目标的差来说，更小，则将队头拿出来，将新数加入队列。如果该数的差更大，则直接退出并返回这个队列，因为后面的数更大，差值也只会更大。
//	http://blog.csdn.net/xudli/article/details/48752907
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		PriorityQueue<TreeNode> pq = new PriorityQueue(k, new ClosestKValuesComparator(target));

		while (root != null) {
			if (root.left == null) {
				//visit root
				closestKValuesVisit(root, target, pq, k);
				root = root.right;
			}
			TreeNode cur;
			for (cur = root.left; cur.right != null && cur.right != root; cur = cur.right) ;
			if (cur.right == null) {
				cur.right = root;
				root = root.left;
			} else {
				cur.right = null;
				//visitor root;
				closestKValuesVisit(root, target, pq, k);
				root = root.right;
			}
		}
		List<Integer> ret = new LinkedList<>();
		Iterator iter = pq.iterator();
		while (iter.hasNext()) {
			ret.add((Integer) iter.next());
		}
		return ret;
	}

	//	https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return root;
		if (p == q)
			return p;

		while (root != null) {
			if (root.val < p.val && root.val < q.val)
				root = root.right;
			else if (root.val > p.val && root.val > q.val)
				root = root.left;
			else
				return root;
		}
		return null;
	}

//	236. Lowest Common Ancestor of a Binary Tree
//	https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
TreeNode lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
	//both p != null, q != null, p != q
	if (root == null || root == p || root == q)
		return root;

	TreeNode left = lowestCommonAncestorHelper(root.left, p, q);
	TreeNode right = lowestCommonAncestorHelper(root.right, p, q);
	if (left != null && right != null)
		return root;
	return left != null ? left : right;
}
	public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
		//Assume both node are in the tree??
		if (p == null || q == null)
			return p == null ? q : p;
		if (p == q)
			return p;
		return lowestCommonAncestorHelper(root, p, q);
	}

//	Alien Dictionary, 269
//There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
//	For example, Given the following words in dictionary,
//			[
//			"wrt",
//			"wrf",
//			"er",
//			"ett",
//			"rftt"
//			]
//	The correct order is: "wertf".
//	Note: You may assume all letters are in lowercase. If the order is invalid, return an empty string. There may be multiple valid order of letters, return any one of them is fine.
	public String alienOrder(String[] words) {
		if (words == null || words.length == 0)
			return null;
		if (words.length == 1)
			return words[0];
		//at least two
		//Why use a set instead of a list here??? Avoid add known order mapping.
		HashMap<Character, Set<Character>> map = new HashMap<>();
		int [] counter = new int [26];
		Set<Character> set = new HashSet<>();

		for (int i = 0; i < words[0].length(); i++)
			set.add(words[0].charAt(i));

		for (int i = 1; i < words.length; i++) {
			int k = 0;
			while (k < words[i-1].length() &&
					k < words[i].length() &&
					words[i-1].charAt(k) == words[i].charAt(k)) {
				set.add(words[i].charAt(k));
				++k;
			}
			if (k < words[i-1].length() && k < words[i].length()) {
				//This means (words[i-1].charAt(k) < words[i].charAt(k))
					counter[words[i-1].charAt(k) - 'a']++;
					if (!map.containsKey(words[i].charAt(k))) {
						Set<Character> tmpset = new HashSet<>();
						map.put(words[i].charAt(k), tmpset);
					}
					Set<Character> tmpset = map.get(words[i].charAt(k));
					tmpset.add(words[i-1].charAt(k));
			}
			while (set.size() < 26 && k < words[i].length()) {
				set.add(words[i].charAt(k++));
			}
		}// end of for

		char [] ret = new char[set.size()];
		List<Character> candidates = new LinkedList<>();
		for (int i = 0; i < counter.length; i++) {
			char c = (char) ('a' + i);
			if (counter[i] == 0 && set.contains(c)) {
				candidates.add(c);
				set.remove(c);
			}
		}

		int k = ret.length;
		while (candidates.size() != 0) {
			Character c = candidates.remove(0);
			ret[--k] = c;
			Set<Character> tmpset = map.get(c);
			if (tmpset != null) {
				Iterator iter = tmpset.iterator();
				while (iter.hasNext()) {
					c = (char)iter.next();
					counter[c - 'a']--;
					if (counter[c-'a'] == 0)
						candidates.add(c);
				}
			}
		}

		for (char c : ret)
			System.out.println(c);
		if (k == 0)
			return new String(ret);
		else
			return null;
	}

//	Palindrome Permutation, 266
//	Given a string, determine if a permutation of the string could form a palindrome.
//	For example, "code" -> False, "aab" -> True, "carerac" -> True.
//	Hint:
//  Consider the palindromes of odd vs even length. What difference do you notice?
//	Count the frequency of each character.
//	If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
//	http://www.cnblogs.com/jcliBlogger/p/4748554.html
	public boolean canPermutePalindrome(String s) {
		if (s == null || s.length() <= 1)
			return true;
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!map.containsKey(c))
				map.put(c, 0);
			map.put(c, map.get(c) + 1);
		}
		boolean hasOdd = false;
		for (Integer val : map.values()) {
			if ((val & 0x01) == 0)
				continue;
			else if (!hasOdd)
				hasOdd = true;
			else
				return false;
		}
		return true;
	}

//	Palindrome Permutation II, 267
//	Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
//	Given s = "aabb", return ["abba", "baab"].
//	Given s = "abc", return [].
//	Hint: If a palindromic permutation exists, we just need to generate the first half of the string.
//	To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.

	//Not interesting, only manual work...
//	https://leetcode.com/discuss/53613/my-accepted-java-solution
	public List<String> generatePalindromes(String s) {
		List<String> ret = new LinkedList<>();

		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!map.containsKey(c))
				map.put(c, 1);
			else
				map.put(c, map.get(c) + 1);
		}

		char mid = ' ';
		boolean hasOdd = false;
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if ((entry.getValue() & 0x01) == 0)
				continue;
			else if (!hasOdd) {
				hasOdd = true;
				mid = entry.getKey();
			} else
				return ret;
		}

//Only even is there...
		int last = s.length()/2;
		StringBuilder sb = new StringBuilder();

		return ret;
	}

	//paint house, 256
//	There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
//	The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
//	Note: All costs are positive integers.

//	https://leetcode.com/discuss/51721/simple-java-dp-solution?show=51755#a51755
	public int minCost(int[][] costs) {
		int length = costs.length;
		for (int i = 1; i < length; i++) {
			for (int k = 0; k < 3; k++) {
				costs[i][k] += Math.min(costs[i-1][(k+1)%3], costs[i-1][(k+2)%3]);
			}
		}
		return Math.min(costs[length-1][0], Math.min(costs[length-1][1],costs[length-1][2]));
	}

//	Paint House II, 265
//	There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
//	The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs0 is the cost of painting house 0 with color 0; costs1 is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
//	Note: All costs are positive integers.
//	Follow up: Could you solve it in O(nk) runtime?
	public int minCostII(int[][] costs) {
		//The same as before, nothing funny.
//		和I的思路一样，不过这里我们有K个颜色，不能简单的用Math.min方法了。如果遍历一遍颜色数组就找出除了自身外最小的颜色呢？我们只要把最小和次小的都记录下来就行了，这样如果和最小的是一个颜色，就加上次小的开销，反之，则加上最小的开销。
		return 0;
	}

//	Graph Valid Tree, 261
//Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
//	For example:
//	Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
//	Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
//	Hint:
//	Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
// PLEASE confirm with interviewr about duplicate edges... Possible inputs??
// Show More Hint Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
	public boolean validTree(int n, int[][] edges) {
		if (n <= 0 || n != edges.length + 1)
			return false;

		//make sure no cycle...
		int [] ids = new int [n];
		for (int i = 1; i < n; i++)
			ids[i] = i;
		for (int i = 0; i < edges.length; i++) {
			//connect edges[i][0], edges[i][1]
			int a = edges[i][0];
			while (ids[a] != a)
				a = ids[a] = ids[ids[a]];
			int b = edges[i][1];
			while (ids[b] != b)
				b = ids[b] = ids[ids[b]];
			if (a == b)
				return false;
			ids[a] = b;
			--n;
		}

		return n == 1;
	}

//	Single Number III, 260
//	https://leetcode.com/problems/single-number-iii/

	// You don't need to actually swap the elements in the array. You can get them during the run..
//	int xor1 = 0;
//	int xor2 = 0;
//	for (int num : nums) {
//		if ((num & mask) == 0) {
//			xor1 ^= num;
//		} else {
//			xor2 ^= num;
//		}
//	}
//	http://buttercola.blogspot.com/2015/09/leetcode-single-number-iii.html

	//How to get the last 1 bit...
	// get the last 1 bit of x1xorx2, e.g. 1010 ==> 0010
//	int last1Bit = x1xorx2 - (x1xorx2 & (x1xorx2 - 1));

	public int[] singleNumberIII(int[] nums) {
		//at least two numbers
		int [] ret = new int [2];

		int x = nums[0];
		for (int i = 1; i < nums.length; i++)
			x ^= nums[i];

		//find the mast
		int mask = 0x01;
		while ((x & mask) == 0)
			mask <<= 1;

		//with 0 left
		int i = 0, j = nums.length - 1;
		while (i < j) {
			if ((nums[i] & mask) == 0) {
				++i;
			} else {
				//swap
				int tmp = nums[i];
				nums[i] = nums[j];
				nums[j] = tmp;
				--j;
			}
		}
		//find the boundary..
		if ((nums[i] & mask) == 0)
			++i;
		ret[0] = nums[0];
		for (int k = 1; k < i; k++)
			ret[0] ^= nums[k];
		ret[1] = nums[i];
		for (int k = i + 1; k < nums.length; k++)
			ret[1] ^= nums[k];
		return ret;
	}

//	137. Single Number II
	//	https://leetcode.com/problems/single-number-ii/
//	http://www.acmerblog.com/leetcode-single-number-ii-5394.html
	//Kind of intuitive in below way...
	//ones   代表第ith 位只出现一次的掩码变量
//twos  代表第ith 位只出现两次次的掩码变量
//	threes  代表第ith 位只出现三次的掩码变量
//int singleNumber(int A[], int n) {
//	int ones = 0, twos = 0, threes = 0;
//	for (int i = 0; i < n; i++) {
//		twos |= ones & A[i];
//		ones ^= A[i];// 异或3次 和 异或 1次的结果是一样的
//		//对于ones 和 twos 把出现了3次的位置设置为0 （取反之后1的位置为0）
//		threes = ones & twos;
//		ones &= ~threes;
//		twos &= ~threes;
//	}
//	return ones;
//}
//	http://yucoding.blogspot.com/2014/10/single-number-ii.html
	public int singleNumberII(int[] nums) {
		//We should have at least 4 elements here...
		int ret = nums[0];
		int carryon = 0;
		for (int i = 1; i < nums.length; i++) {
			//How to use cancellation??? Why do you need a carryon?? How to use carryon???
			//Cancel three bits on the same pos..
			int next_carryon = (ret & nums[i] & ~carryon) | (~nums[i] & ~ret & carryon);
			ret = (nums[i] & ret & carryon) | (~nums[i] & ret & ~carryon) | (nums[i] & ~ret & ~carryon);
			carryon = next_carryon;
		}
		return ret;
	}

	// 136. Single Number
//	https://leetcode.com/problems/single-number/
	public int singleNumberI(int[] nums) {
		//assume this array has at least one element
		int ret = nums[0];
		for (int i = 1; i < nums.length; i++)
			ret ^= nums[i];
		return ret;
	}

//	3Sum Smaller, 259
//Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
//	For example, given nums = [-2, 0, 1, 3], and target = 2.
//	Return 2. Because there are two triplets which sums are less than 2:
//			[-2, 0, 1]
//			[-2, 0, 3]
//	Follow up:
//	Could you solve it in O(n^2) runtime?
	public int threeSumSmaller(int[] nums, int target) {
		Arrays.sort(nums);
		int cnt = 0;
		for(int i = 0; i < nums.length - 2; i++){
			int left = i + 1, right = nums.length - 1;
			while(left < right){
				int sum = nums[i] + nums[left] + nums[right];
				// 如果三个数的和大于等于目标数，那将尾指针向左移
				if(sum >= target){
					right--;
					// 如果三个数的和小于目标数，那将头指针向右移
				} else {
					// right - left个组合都是小于目标数的
					cnt += right - left;
					left++;
				}
			}
		}
		return cnt;
	}


//	VERIFY PREORDER SEQUENCE IN BINARY SEARCH TREE, 255
//	Question:
//	Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
//	You may assume each number in the sequence is unique.
//	Follow up:
//	Could you do it using only constant space complexity?

	boolean verifyPreorderHelper(int[] preorder, int start, int len, long min, long max) {
		if (len <= 0)
			return true;
		if (preorder[start] <= min || preorder[start] >= max)
			return false;
		int l2 = 0;
		while (l2 < len && preorder[start + l2] <= preorder[start])
			l2++;
		return verifyPreorderHelper(preorder, start + 1, l2 - 1, min, preorder[start]) &&
				verifyPreorderHelper(preorder, start + l2, len - l2, preorder[start], max);
	}

//	https://segmentfault.com/a/1190000003874375
//	http://buttercola.blogspot.com/2015/09/leetcode-verify-preorder-sequence-in.html
//	https://leetcode.com/discuss/51543/java-o-n-and-o-1-extra-space
	public boolean verifyPreorder(int[] preorder) {
		if (preorder == null || preorder.length <= 0)
			return false;
		return verifyPreorderHelper(preorder, 0, preorder.length, Long.MIN_VALUE, Long.MAX_VALUE);
	}

//	Factor Combinations, 254
//Numbers can be regarded as product of its factors. For example,
//	8 = 2 x 2 x 2;
//	= 2 x 4.
//	Write a function that takes an integer n and return all possible combinations of its factors.
//	Note:
//	Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
//	You may assume that n is always positive.
//	Factors should be greater than 1 and less than n.
//	Examples:
//	input: 1
//	output:
//			[]
//	input: 37
//	output:
//			[]
//	input: 12
//	output:
//			[
//			[2, 6],
//			[2, 2, 3],
//			[3, 4]
//			]
//	input: 32
//	output:
//			[
//			[2, 16],
//			[2, 2, 8],
//			[2, 2, 2, 4],
//			[2, 2, 2, 2, 2],
//			[2, 4, 4],
//			[4, 8]
//			]

	/* Below is wrong, could not handle duplication...Sad.... */
	//Got a way to bypass that....With that min val....
	public List<List<Integer>> getFactorsHelper(int n, int min) {
		if (n == 1)
			return new ArrayList<>();

		List<List<Integer>> ret = new ArrayList<>();
		int mid = (int) Math.sqrt(n);

		//Why <= mid is not correct below..
		//How to avoid duplication????
		//Can't find a good except use sort/comparion to deduplicate them....
//		if (mid == 2)
//			++mid;

		for (int i = Math.max(2, min); i <= mid; i++) {
			if (n % i == 0) {
				List<List<Integer>> tmp = getFactorsHelper(n / i, i);

				for (List<Integer> l : tmp) {
					l.add(i);
					Collections.sort(l);
					ret.add(l);
				}
			}
		}
		//aslist return an immutable one...
//		ret.add(Arrays.asList(new Integer [] {n}));
		ret.add(new ArrayList<Integer>(){{add(n);}});
		return ret;
	}

	//All need some trick to avoid the duplicates...
	//http://buttercola.blogspot.com/2015/08/leetcode-factor-combinations.html
//	http://www.cnblogs.com/airwindow/p/4822572.html
	public List<List<Integer>> getFactors(int n) {
		if (n <= 1)
			return new ArrayList<>();
		List<List<Integer>> ret = getFactorsHelper(n, 1);
		int i = 0;
		while (i < ret.size()) {
			if (ret.get(i).size() > 1) {
				++i;
			} else {
				ret.remove(i);
			}
		}
		return ret;
	}

//	Meeting Rooms, 252
//	Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
//	For example,
//	Given [[0, 30],[5, 10],[15, 20]],
//			return false.
public static class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}
	public class IntervalComparator implements Comparator<Interval> {
		public int compare(Interval e1, Interval e2) {
			return e1.start - e2.start;
		}
	}

	//Define a overlap API might be more clean here...
public boolean canAttendMeetingsI(Interval[] intervals) {
	if (intervals == null || intervals.length <= 1)
		return true;

	//Check if overlap?
	Arrays.sort(intervals, new IntervalComparator());
	int end = intervals[0].end;
	for (int i = 1; i < intervals.length; i++)
		if (end > intervals[i].start)
			return false;
		else
			end = intervals[i].end;
	return true;
}

//	Meeting Rooms II, 253
//	Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
//	For example, Given [[0, 30],[5, 10],[15, 20]], return 2.

	public static class MeetingPoints implements Comparable<MeetingPoints> {
		int pos;
		boolean isStart;

		MeetingPoints(int pos, boolean isStart) {
			this.pos = pos;
			this.isStart = isStart;
		}

		public int compareTo(MeetingPoints p2) {

			if (this.pos != p2.pos)
				return this.pos - p2.pos;
			else if (this.isStart == p2.isStart)
				return 0;
			else if (p2.isStart)
				return -1;
			else
				return +1;
		}
	}
//	https://segmentfault.com/a/1190000003894670
//	https://leetcode.com/discuss/50783/java-ac-code-using-comparator
	//When pq poll, it might need a while loop to do so...
//	http://buttercola.blogspot.com/2015/08/leetcode-meeting-rooms-ii.html
	public int minMeetingRooms(Interval[] intervals) {
		MeetingPoints [] points = new MeetingPoints[2 * intervals.length];
		int k = 0;
		for (int i = 0; i < intervals.length; i++) {
			points[k++] = new MeetingPoints(intervals[i].start, true);
			points[k++] = new MeetingPoints(intervals[i].end, false);
		}
		Arrays.sort(points);

		int ret = 1;
		int acc = 0;
		for (int i = 0; i < points.length; i++) {
			if (points[i].isStart) {
				ret = Math.max(ret, ++acc);
			} else {
				--acc;
			}
		}
		return ret;
	}

//	Count Univalue Subtrees, 250
//Given a binary tree, count the number of uni-value subtrees.
//A Uni-value subtree means all nodes of the subtree have the same value.
//For example:
//Given binary tree,
//		5
//	   / \
//	  1   5
//	 / \  \
//	5  5   5
//		return 4.

	public static class CountUnivalSubtrees {
		boolean isUnivalSubTree;
		int count;
		CountUnivalSubtrees(boolean isUnivalSubTree, int count) {
			this.isUnivalSubTree = isUnivalSubTree;
			this.count = count;
		}
	}
	public CountUnivalSubtrees countUnivalSubtreesHelper(TreeNode root) {
		if (root == null)
			return new CountUnivalSubtrees(true, 0);
		CountUnivalSubtrees ret = new CountUnivalSubtrees(true, 0);
		if (root.left != null) {
			CountUnivalSubtrees left = countUnivalSubtreesHelper(root.left);
			if (!left.isUnivalSubTree || root.val != root.left.val)
				ret.isUnivalSubTree = false;
			ret.count += left.count;
		}
		if (root.right != null) {
			CountUnivalSubtrees right = countUnivalSubtreesHelper(root.right);
			if (!right.isUnivalSubTree || root.val != root.right.val)
				ret.isUnivalSubTree = false;
			ret.count += right.count;
		}

		if (ret.isUnivalSubTree)
			++ret.count;
		return ret;
	}

	//See below how to bypass that you can't even pass Integer object between recursion if you want to change sth.
//	https://hzhou.me/LeetCode/LeetCode-Count-Univalue-Subtrees.html
	public int countUnivalSubtrees(TreeNode root) {
		if (root == null)
			return 0;

		return countUnivalSubtreesHelper(root).count;
	}

//	Group Shifted Strings, 249
//Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//	"abc" -> "bcd" -> ... -> "xyz"
//	Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
//	For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
//	Return:
//			[
//			["abc","bcd","xyz"],
//			["az","ba"],
//			["acef"],
//			["a","z"]
//			]
//	Note: For the return value, each inner list's elements must follow the lexicographic order.

	//actually, we don't need to sort it at all....
	public class GroupStringsComparator implements Comparator<String> {
		public int compare(String e1, String e2) {
			int length1 = e1.length(), length2 = e2.length();
			if (length1 < length2)
				return -1;
			else if (length1 > length2)
				return +1;
			/* I don't think we care about comparion here when the strings of the same length..
			for (int i = 0; i < length1; i++)
				if (e1.charAt(i) != e2.charAt(i)) {
					if (e1.charAt(i) < e2.charAt(i))
						return -1;
					else
						return +1;
				}
			*/
			return 0;
		}
	}
	String getgroupStringsHash(String input) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < input.length(); i++)
			builder.append((char) ((((input.charAt(i) - input.charAt(0)) + 26) % 26) + 'a'));
		return builder.toString();
	}

public List<List<String>> groupStrings(String[] strings) {
//	Arrays.sort(strings, new GroupStringsComparator());
	List<List<String>> ret = new ArrayList<>();
	HashMap<String, Integer> map = new HashMap<>();
	for (int i = 0; i < strings.length; i++) {
		String hash = getgroupStringsHash(strings[i]);
		if (!map.containsKey(hash)) {
			List<String> tmp = new ArrayList<>();
			tmp.add(strings[i]);
			ret.add(tmp);
			map.put(hash, ret.size()-1);
		} else {
			ret.get(map.get(hash)).add(strings[i]);
		}
	}

	//You may need to sort each small list...
	return ret;
}


//	https://tonycao.gitbooks.io/leetcode-locked/content/LeetCode%20Locked/c1.5.html
//	Strobogrammatic Number, 243
//A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//	Write a function to determine if a number is strobogrammatic. The number is represented as a string.
//	For example, the numbers "69", "88", and "818" are all strobogrammatic.
	public boolean isStrobogrammatic(String num) {
		if (num == null)
			return false;
		int length = num.length();
		if (length <= 0 || (length > 1 && num.charAt(0) == '0'))
			return false;
		//leading 00000 should be considered??

		//assume only digits there
		final char [] array1 = {'0', '1', '*', '*', '*', '*', '9', '*', '8', '6'};
		int i = 0, j = num.length() - 1;
		while (i < j) {
			if (array1[num.charAt(i) - '0'] != num.charAt(j))
				return false;
			++i;
			--j;
		}
		//you must check mid
		if (i == j) {
			//if it's single, it can only be 0,1,8
			if (array1[num.charAt(i)-'0'] == '*' || array1[num.charAt(i)-'0'] == '9' || array1[num.charAt(i)-'0'] == '6' )
				return false;
		}
		return true;
	}

	//	Strobogrammatic Number II, 244
//	A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//	Find all strobogrammatic numbers that are of length = n.
//	Given n = 2, return ["11","69","88","96"].
//	http://ryanleetcode.blogspot.com/2015/10/strobogrammatic-number-ii-leetcode.html
	void findStrobogrammaticHelper(List<String> ret, StringBuilder sb, int i, int j, final char [] array1, final char [] array2) {
		if (i > j) {
			ret.add(sb.toString());
			return;
		}

		if (i == j) {
			for (int k = 0; k < 3; k++) {
				//skip leading 0s.
				if (i == 0 && k == 0)
					continue;
				sb.setCharAt(i, array1[k]);
				ret.add(sb.toString());
			}
			return;
		}

		//i < j
		for (int k = 0; k < array1.length; k++) {
			if (i == 0 && k == 0)
				continue;
			sb.setCharAt(i, array1[k]);
			sb.setCharAt(j, array2[k]);
			findStrobogrammaticHelper(ret, sb, i + 1, j - 1, array1, array2);
		}
	}
	public List<String> findStrobogrammatic(int n) {
		final char [] array1 = {'0', '1', '8', '6', '9'};
		final char [] array2 = {'0', '1', '8', '9', '6'};
		List<String> ret = new ArrayList<>();
		if (n <= 0)
			return ret;
		if (n == 1) {
			for (int i = 0; i < 3; i++)
				ret.add(Character.toString(array1[i]));
		} else {
			//YOu can use a char [] as tmp instead of SB....
			StringBuilder sb = new StringBuilder(n);
			for (int i = 0; i < n; i++)
				sb.append(' ');
			int i = 0, j = n - 1;
			findStrobogrammaticHelper(ret, sb, i, j, array1, array2);
		}
		return ret;
	}

//	Strobogrammatic Number III, 245
//A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//	Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
//			For example,
//	Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
//	Note:
//	Because the range might be a large number, the low and high numbers are represented as string.

	//Below code is wrong...
	int strobogrammatic(String input) {
							//0   1  2  3  4  5  6  7,  8   9
		final int [] counts = {1, 2, 2, 2, 2, 2, 3, 3,  4,  5};
							    //0  1   2   3   4   5    6    7    8    9
		final char [] array1 = {'0','1','*','*','*','*', '9', '*', '8', '6'};
		int length = input.length();
		if (length == 0)
			return 0;

		int ret = 0;
		if (array1[input.charAt(0) - '0'] == '*') {
			if (length == 1)
				return counts[input.charAt(0) - '0'];

			// at least length 2:
			ret = 9;
			ret = (int) (counts[input.charAt(0) - '0'] * Math.pow(5, (length-1) >> 1));
		} else {
			if (length == 1)
				return counts[input.charAt(0) - '0'] - 1;

			if (counts[input.charAt(0) - '0'] > 1)
				ret += (int) ((counts[input.charAt(0) - '0'] - 1) * Math.pow(5, length >> 1));
			ret += strobogrammatic(input.substring(1, length-1));
		}
		return ret;
	}

	//Don't need to be perfect... Have some basic ideas are OK. You don't have to be too advanced...
//	https://leetcode.com/discuss/50624/clean-and-easy-understanding-java-solution
	public int strobogrammaticInRange(String low, String high) {
		System.out.println(strobogrammatic("2000"));
		return 0;
//		return strobogrammatic(high) - strobogrammatic(low);
	}


//	Shortest Word Distance, 243
//Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//	For example,
//	Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//	Given word1 = "coding", word2 = "practice", return 3.
//	Given word1 = "makes", word2 = "coding", return 1.
//	Note:
	//Nice to have these edge cases...
//	You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
public int shortestDistance(String[] words, String word1, String word2) {
	int ret = 9999999;
	int l = -1, r = -1;
	for (int i = 0; i < words.length; i++) {
		if (words[i].equals(word1))
			l = i;
		else if (words[i].equals(word2))
			r = i;
		if (l >= 0 && r >= 0)
			ret = Math.min(ret, Math.abs(l - r));
	}

	return ret;
}

	//	Shortest Word Distance III, 245
//	This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
//	Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//	word1 and word2 may be the same and they represent two individual words in the list.
//	For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//	Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = "makes", word2 = "makes", return 3.
//	Note: You may assume word1 and word2 are both in the list.

//	https://tonycao.gitbooks.io/leetcode-locked/content/LeetCode%20Locked/c1.4.html
	public int shortestWordDistanceIII(String[] words, String word1, String word2) {
		//Why below code not working?
//		return shortestDistance(words, word1, word2);

		if (!word1.equals(word2))
			return shortestDistance(words, word1, word2);

		int ret = Integer.MAX_VALUE;
		int pre = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				if (pre != -1)
					ret = Math.min(ret, i - pre);
				pre = i;
			}
		}
		return ret;
	}

	private boolean isOperator(char operator) {
		return (operator == '+') || (operator == '-') || (operator == '*');
	}
	private int calculate(int num1, int num2, char operator) {
		int result = 0;

		switch(operator) {
			case '+' : result = num1 + num2;
				break;

			case '-' : result = num1 - num2;
				break;

			case '*' : result = num1 * num2;
				break;
		}

		return result;
	}
	//	Different Ways to Add Parentheses, 241
//	https://leetcode.com/problems/different-ways-to-add-parentheses/
	public List<Integer> diffWaysToCompute(String input) {
//		这题就是分治法- Divide and Conquer的一个例子。在递归的过程中，根据符号位，不断将一个字符串分成两个子串，然后将两个子串的结果merge起来。
//		The question can be solved by using divide-and-conquer. We first cut the expression into two halves and calculate the list of result for each half. Then we traverse the two lists and get the final result.

//		The problem itself is not hard. The essence of the problem is to compute the different ways to compute the expression. There is only one trick here is how to handle the input has only one number there, e.g. "1".
// Then we need to add the number into the result list. The idea is to check if the result is empty after the divide-and-conquer step. If it is empty, means only one number left in the input.
// Note that we cannot check using if the first character of the input string is digit, because each and every input string starts from a number.
 		List<Integer> result = new ArrayList<>();
		if (input == null || input.length() == 0) {
			return result;
		}

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (!isOperator(c)) {
				continue;
			}

			List<Integer> left = diffWaysToCompute(input.substring(0, i));
			List<Integer> right = diffWaysToCompute(input.substring(i + 1));

			for (int num1 : left) {
				for (int num2 : right) {
					int val = calculate(num1, num2, c);
					result.add(val);
				}
			}
		}

		// only contains one number
		if (result.isEmpty()) {
			result.add(Integer.parseInt(input));
		}

		return result;
	}

//	234. Palindrome Linked List
//	https://leetcode.com/problems/palindrome-linked-list/
	void reverse(ListNode begin, ListNode end) {
		end.next = null;
		ListNode pre = null, cur = begin;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
	}
	//Or you can reverse from the back, then compare from the beginning
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null)
			return true;

		//let's do it in a stupid way
		int steps = 0;
		ListNode cur = head;
		while (cur.next != null) {
			steps++;
			cur = cur.next;
		}
		int half = steps / 2;
		if ((steps & 0x01) == 0)
			half--;
		ListNode begin = head, end = head;
		while (half > 0) {
			end = end.next;
			--half;
		}
		ListNode mid = end.next;
		reverse(begin, end);
		if ((steps & 0x01) == 0)
			mid = mid.next;

		//compare
		while (mid != null) {
			if (mid.val == end.val) {
				mid = mid.next;
				end = end.next;
			} else {
				return false;
			}
		}
		return true;
	}

//	https://leetcode.com/problems/number-of-digit-one/
//	http://blog.sina.com.cn/s/blog_3fc85e260100mbss.html
//	233. Number of Digit One
	public int countDigitOne(int n) {
		if (n <= 0)
			return 0;

		int total = 0;
		int level = 1;
		int remaining = 0;
		int a9 = 0;
		while (n > 0) {
			int digit = n % 10;

			if (digit > 0) {
				if (digit == 1) {
					total += (remaining + 1);
				} else {
					total += level;
				}

				total += digit * a9;
			}

			remaining = digit * level + remaining;
			a9 = 10*a9 + level;
			level *= 10;

			n /= 10;
		}

		return total;
	}

//	https://leetcode.com/problems/kth-smallest-element-in-a-bst/
	public void kthSmallestHelper(TreeNode root, int [] k, int [] ret) {
		if (root == null || k[0] == 0)
			return;
		kthSmallestHelper(root.left, k, ret);
		--k[0];
		if (k[0] == 0) {
			ret[0] = root.val;
			return;
		}
		kthSmallestHelper(root.right, k, ret);
	}

	//You can just iteratre through the tree without recursion... Moris inorder...
	public int kthSmallest(TreeNode root, int k) {
		/*
		int [] ret = new int [1];
		int [] kk = new int [1];
		kk[0] = k;
		kthSmallestHelper(root, kk, ret);
		return ret[0];
		*/

		//let's use stack here
		Stack<TreeNode> st = new Stack<>();
		while (root != null || !st.isEmpty()) {
			if (root == null) {
				//pick up the top, visit it.
				//visit root;
				root = st.pop();
				--k;
				if (k == 0)
					return root.val;
				root = root.right;
			} else {
				while (root != null) {
					st.push(root);
					root = root.left;
				}
			}
		}
		return 0;
	}

//	https://leetcode.com/problems/majority-element-ii/
//	http://www.programcreek.com/2014/07/leetcode-majority-element-ii-java/
	public List<Integer> majorityElement(int[] nums) {
		//at most 2, may be 1
		List<Integer> ret = new ArrayList();
		int x = 0, y = 0, c1 = 0, c2 = 0;
		for (int i = 0; i < nums.length; i++) {
			if (c1 == 0 && c2 == 0) {
				x = nums[i];
				c1 = 1;
			} else if (c1 == 0) {
				if (y == nums[i])
					c2++;
				else {
					x = nums[i];
					c1 = 1;
				}
			} else if (c2 == 0) {
				if (x == nums[i]) {
					c1++;
				} else {
					y = nums[i];
					c2 = 1;
				}
			} else {
				if (x == nums[i])
					c1++;
				else if (y == nums[i])
					c2++;
				else {
					c1--;
					c2--;
				}
			}
		}
		if (c1 > 0) {
			int c = 0;
			for (int i = 0; i < nums.length; i++)
				if (x == nums[i])
					c++;
			if (c > nums.length/3)
				ret.add(x);
		}
		if (c2 > 0) {
			int c = 0;
			for (int i = 0; i < nums.length; i++)
				if (y == nums[i])
					c++;
			if (c > nums.length/3)
				ret.add(y);
		}
		return ret;
	}

//	https://leetcode.com/problems/longest-consecutive-sequence/
//	128. Longest Consecutive Sequence
	static class LongestConsecutiveNode {
		int left;
		int right;
		LongestConsecutiveNode(int l, int r) {
			left = l;
			right = r;
		}
	}

	//See my previous submission for references.
//	https://leetcode.com/submissions/detail/34018770/
//	http://www.programcreek.com/2013/01/leetcode-longest-consecutive-sequence-java/
	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length <= 0)
			return 0;
		int ret = 1;
		HashMap<Integer, LongestConsecutiveNode> map = new HashMap();
		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i])) {
				LongestConsecutiveNode newNode = new LongestConsecutiveNode(nums[i], nums[i]);
//				map.put(nums[i], newNode);
				//merge left
				if (map.containsKey(nums[i] - 1)) {
					LongestConsecutiveNode n1 = map.get(nums[i] - 1);
					n1.right = newNode.right;
					newNode.left = n1.left;
//					map.put(nums[i] - 1, n1);

					LongestConsecutiveNode n2 = map.get(n1.left);
					n2.right = newNode.right;
					map.put(n1.left, n2);
				}

				//merge right
				if (map.containsKey(nums[i] + 1)) {
					LongestConsecutiveNode n1 = map.get(nums[i] + 1);
					n1.left = newNode.left;
					newNode.right = n1.right;
//					map.put(nums[i] + 1, n1);

					LongestConsecutiveNode n2 = map.get(n1.right);
					n2.left = newNode.left;
					map.put(n1.right, n2);

					//God, you need to merge it back...
					if (newNode.left != nums[i]) {
						LongestConsecutiveNode n3 = map.get(newNode.left);
						n3.right = newNode.right;
						map.put(newNode.left, n3);
					}
				}
				map.put(nums[i], newNode);
				ret = Math.max(ret, newNode.right - newNode.left + 1);
			}
		}
		return ret;
	}

//	42. Trapping Rain Water
	//	https://leetcode.com/problems/trapping-rain-water/
	public int trap(int[] height) {
		if (height == null || height.length <= 1)
			return 0;
		int ret = 0;
		int [] left = new int [height.length];
		int [] right = new int [height.length];
		int max = height[0];
		for (int i = 1; i < height.length; i++) {
			if (max < height[i])
				max = height[i];
			left[i] = max;
		}
		max = height[height.length-1];
		for (int i = height.length - 2; i >= 0; i--) {
			if (max < height[i])
				max = height[i];
			right[i] = max;
		}

		for (int i = 1; i < height.length - 1; i++)
			ret += Math.min(left[i], right[i]) - height[i];
		return ret;
	}

//	https://leetcode.com/problems/largest-number/
	static class LargestNumberComparator implements Comparator<String> {
		public int compare(String a, String b) {
			//s1 + s2 compare with s2 + s1
			//what is starting 0? That's OK...
			String x = a + b;
			String y = b + a;
			//Larger number goes first....
			return y.compareTo(x);
//			for (int i = 0; i < x.length(); i++)
//				/* Below order is reversed....
//				if (x.charAt(i) < y.charAt(i))
//					return -1;
//				else if (x.charAt(i) > y.charAt(i))
//					return +1;
//				*/
//
//			return 0;
		}
	}
	public String largestNumber(int[] nums) {
		if (nums == null || nums.length <= 0)
			return null;
//what is all 0??
		String [] tmp = new String [nums.length];
		for (int i = 0; i < nums.length; i++)
			tmp[i] = Integer.toString(nums[i]);
		Arrays.sort(tmp, new LargestNumberComparator());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tmp.length; i++)
			sb.append(tmp[i]);
		if (sb.charAt(0) == '0')
			return new String ("0");
		else
			return sb.toString();
	}

//	187. Repeated DNA Sequences
//	http://bookshadow.com/weblog/2015/02/06/leetcode-repeated-dna-sequences/
	public List<String> findRepeatedDnaSequences(String s) {
		HashSet<String> set = new HashSet<>();
		HashSet<String> retSet = new HashSet<>();

		//you can use bits to denote each ch
		for (int i = 0; i <= s.length() - 10; i++) {
			String tmp = s.substring(i, i + 10);
			if (set.contains(tmp)) {
				retSet.add(tmp);
			} else {
				set.add(tmp);
			}
		}
		return new ArrayList<>(retSet);
	}

	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || nums.length <= 1 || k <= 0 || t < 0)
			return false;

//		TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
		/*
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		HashMap<Integer, Integer> hashMap = new HashMap<>();

		//we need abs(nums[i] - nums[j]) <= t and abs(j - i) <= k
		for (int i = 0; i < nums.length; i++) {
			//check existence first
			Integer floorKey = treeMap.floorKey(nums[i]);
			if (floorKey != null && (nums[i] - floorKey) <= t)
				return true;
			Integer higherKey = treeMap.higherKey(nums[i]);
			if (higherKey != null && (higherKey - nums[i]) <= t)
				return true;
			//this key must not exists in the tree by now...
			treeMap.put(nums[i], i);

		}
		*/
		TreeSet<Integer> treeSet = new TreeSet<>();
		for (int i = 0; i < nums.length; i++) {
			Integer floor = treeSet.floor(nums[i]);
			if (floor != null && ((long)nums[i] - floor) <= t)
				return true;
			Integer higher = treeSet.higher(nums[i]);
			if (higher != null && ((long)higher - nums[i]) <= t)
				return true;
			//nums[i] must not in the tree...
			treeSet.add(nums[i]);
			if (treeSet.size() > k)
				treeSet.remove(nums[i- k]);
		}

		return false;
	}

//	https://leetcode.com/problems/the-skyline-problem/
//	218. The Skyline Problem
	static class SkylineNode implements Comparable<SkylineNode>{
		int x;//index
		int h;//val
		SkylineNode(int x, int y) {
			this.x = x;
			this.h = y;
		}
		public int compareTo(SkylineNode b) {
			if (this.x != b.x) {
				//left one go first
				return Integer.compare(this.x, b.x);
			}
			else if (this.x > 0 && b.x > 0) {
				//Positive highest one go first
				return Integer.compare(b.h, this.h);
			} else if (this.x < 0 && b.x < 0) {
				//negative most one go first
				return Integer.compare(this.h, b.h);
			} else if (this.x > 0)
				//positive one goes first....
				return -1;
			else
				return +1;
		}
	}
//	http://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
//	https://briangordon.github.io/2014/08/the-skyline-problem.html
	public List<int[]> getSkyline(int[][] buildings) {
		List<int[]> ret = new ArrayList<>();
		SkylineNode nodes [] = new SkylineNode[buildings.length * 2];
		for (int i = 0; i < buildings.length; i++) {
			nodes[2*i] = new SkylineNode(buildings[i][0], buildings[i][2]);
			nodes[2*i + 1] = new SkylineNode(buildings[i][1], -buildings[i][2]);
		}
		Arrays.sort(nodes);
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int maxH = 0;
		//assume we don't have (1,1,10), Ri - Li > 0
		int i = 0;
		while (i < nodes.length) {
			while (true) {
				if (nodes[i].h > 0) {
					if (map.containsKey(nodes[i].h)) {
						map.put(nodes[i].h, map.get(nodes[i].h) + 1);
					} else {
						map.put(nodes[i].h, 1);
					}
				} else {
					int h = -nodes[i].h;
					//map must conatins key h
					int v = map.get(h);
					--v;
					if (v == 0)
						map.remove(h);
					else
						map.put(h, v);
				}
				++i;
				if (i >= nodes.length || nodes[i].x != nodes[i-1].x)
					break;
			}
			if (map.size() == 0) {
				ret.add(new int [] {nodes[i-1].x, 0});
			} else {
				int lastV = map.lastKey();
				if (lastV != maxH) {
					maxH = lastV;
					ret.add(new int [] {nodes[i-1].x, maxH});
				}
			}
		}
		return ret;
	}

//	https://leetcode.com/problems/maximal-square/
//	221. Maximal Square
	//Can also be solved by dp... See how the dp is defined..
//	http://yucoding.blogspot.com/2015/10/leetcode-question-maximal-square.html

	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0)
			return 0;
		int length = matrix[0].length;
		int [] tmp = new int [length + 1];
		tmp[length] = -1;
		int ret = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == '1')
					++tmp[j];
				else
					tmp[j] = 0;
			}
//			for (int j = 0; j < matrix[i].length; j++)
//				System.out.print(tmp[j] + "\t");
//			System.out.println();

			Stack<Integer> st = new Stack<>();
			int k = 0;
			while (k < tmp.length) {
				if (st.isEmpty() || tmp[st.peek()] < tmp[k]) {
					st.push(k);
				} else {
					while (!st.isEmpty() && tmp[st.peek()] > tmp[k]) {
						int cur = st.pop();
						if (st.isEmpty())
							ret = Math.max(ret, Math.min(k, tmp[cur]));
						else
							ret = Math.max(ret, Math.min(k - st.peek() - 1, tmp[cur]));
					}
					st.push(k);
				}
				k++;
			}
		}
		return ret*ret;
	}

//	https://leetcode.com/problems/largest-rectangle-in-histogram/
//	http://www.programcreek.com/2014/05/leetcode-largest-rectangle-in-histogram-java/
//	http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
//	http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
//	http://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
	public int largestRectangleArea(int[] heights) {
		if (heights == null || heights.length <= 0)
			return 0;
		int ret = 0;
		int i = 0;
		Stack<Integer> st = new Stack<>();

		while (i < heights.length) {
			if (st.isEmpty() || heights[st.peek()] < heights[i]) {
				st.push(i);
			} else {
				while (!st.isEmpty() && heights[st.peek()] > heights[i]) {
					int h = heights[st.pop()];
					if (st.isEmpty())
						ret = Math.max(h * i, ret);
					else
						ret = Math.max(h * (i - st.peek() - 1), ret);
				}
				st.push(i);
			}
			++i;
		}
		if (!st.isEmpty()) {
			while (!st.isEmpty()) {
				int h = heights[st.pop()];
				if (st.isEmpty())
					ret = Math.max(h * i, ret);
				else
					ret = Math.max(h * (i - st.peek() - 1), ret);
			}
		}

		return ret;
	}

//	39. Combination Sum
//	https://leetcode.com/problems/combination-sum/
//	?? {0,7}, {0,0,7}, {0,0,0,7}.... it must be at least 1...

	void combinationSumIHelper(int[] candidates, int start, int target, List<List<Integer>> ret, List<Integer> tmp) {
		if (target == 0) {
			//tmp must be at least of length 1???
			ret.add(new ArrayList<>(tmp));
			return;
		}
		if (start < 0)
			return;

		for (int i = start; i >= 0; i--) {
			if (candidates[i] <= target) {
				tmp.add(candidates[i]);
				combinationSumIHelper(candidates, i, target - candidates[i], ret, tmp);
				tmp.remove(tmp.size() - 1);
			}
		}
	}
	public List<List<Integer>> combinationSumI(int[] candidates, int target) {
		List<List<Integer>> ret = new ArrayList<>();
		combinationSumIHelper(candidates, candidates.length - 1, target, ret, new ArrayList<>());

		return ret;
	}

	void combinationSumIIHelper(int[] candidates, int start, int target, List<List<Integer>> ret, List<Integer> tmp) {
		if (target == 0) {
			//tmp must be at least of length 1???
			ret.add(new ArrayList<>(tmp));
			return;
		}
		if (start < 0)
			return;

		int i = start;
		while (i >= 0) {
			if (candidates[i] <= target) {
				//select
				tmp.add(candidates[i]);
				combinationSumIIHelper(candidates, i-1, target - candidates[i], ret, tmp);
				tmp.remove(tmp.size() - 1);
			}
			--i;
			while (i >= 0 && candidates[i] == candidates[i+1])
				--i;
			//think about it. you made mistake there every time....
			//dont't select, you must skip all same values so that you can continue...
//			if (i >= 0 && candidates[i] <= target)
//				combinationSumIIHelper(candidates, i, target, ret, tmp);
		}

	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> ret = new ArrayList<>();
		Arrays.sort(candidates);
		combinationSumIIHelper(candidates, candidates.length - 1, target, ret, new ArrayList<>());

		return ret;
	}

	void combinationSumIIIHelper(int [] candidates, int start, int k, int target, List<List<Integer>>ret, Integer []tmp) {
		if (k == 0) {
			if (target == 0) {
				ArrayList<Integer> dest= new ArrayList<>();
				dest.addAll(Arrays.asList(tmp));
				ret.add(dest);
			}

			return;
		}

		for (int i = start; i >= 0; i--) {
			if (candidates[i] <= target) {
				tmp[k-1] = candidates[i];
				combinationSumIIIHelper(candidates, i - 1, k - 1, target - candidates[i], ret, tmp);
			}
		}
	}

//	https://leetcode.com/problems/combination-sum-iii/
//	216. Combination Sum III
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> ret = new ArrayList<>();
		int [] candidates = {1,2,3,4,5,6,7,8,9};
		Integer [] tmp = new Integer [k];
		combinationSumIIIHelper(candidates, candidates.length - 1, k, n, ret, tmp);

		return ret;
	}

//	https://leetcode.com/problems/reverse-bits/
	public int reverseBits(int n) {
		//shift 1 bit, 0x55555555
		n = ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);

		//shift 2 bit
		n = ((n & 0xCCCCCCCC) >>> 2) | ((n & 0x33333333) << 2);

		//shift 4 bit
		n = ((n & 0xF0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F) << 4);

		//shift 8 bit
		n = ((n & 0xFF00FF00) >>> 8) | ((n & 0x00FF00FF) << 8);

		//shift 16 bit
		n = ((n & 0xFFFF0000) >>> 16) | ((n & 0xFFFF) << 16);

		return n;
	}

	private void intSwap(int []nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
//	https://leetcode.com/problems/kth-largest-element-in-an-array/
	private int findKthLargest(int[] nums, int start, int end, int k) {
//		System.out.println(start + "\t" + end + "\t" + k);
//		System.out.println("****");
//		for (int t = start; t <= end; t++)
//			System.out.print(nums[t] + "\t");
//		System.out.println("****");
//		System.out.println("****");

		if (start == end) {
			//k must be 1 now...
			return nums[start];
		}
		//end must larger than start now..
		/*
		//Below will result in endless loop, how to bypass this. We should hope make progress in each iteration.
		//So make obj only < privatol to the left instead of <=???
		//Still not working, consider, 9,9,9,9,9,9,9,9,9,, right now, our iteration loop could not make the size become smaller...
		//how to make progress???
		//Petition into three parts, <, =, >
		int l = start, r = end;
		while (l <= r) {
			if (nums[l] <= nums[start])
				++l;
			else {
				//swap
				int tmp = nums[end];
				nums[end] = nums[l];
				nums[l] = tmp;
				--r;
			}
		}

		//the key point is that you could exclude kth element here....
		//And you forget that you should put the pivotal in the middle...
		//You should read quick sort....
		if (k <= (l - start))
			return findKthLargest(nums, start, l - 1, k);
		else
			return findKthLargest(nums, l, end, k - (l - start));
		*/
		int less = start - 1, larger = end;
		int pivotal = nums[start];
		int cur = start;
		while (cur <= larger) {
			if (nums[cur] < pivotal) {
				intSwap(nums, cur++, ++less);
			} else if (nums[cur] > pivotal) {
				intSwap(nums, cur, larger--);
				while (cur <= larger && nums[cur] > larger) {
					--larger;
				}
			} else {
				++cur;
			}
		}

//		System.out.println("XXXXX");
//		for (int t = start; t <= end; t++)
//			System.out.print(nums[t] + "\t");
//		System.out.println("XXXXX");
		//less
		less = less - start + 1;
		larger = end - larger;
		int mid = end - start + 1 - less - larger;
		if (k <= less)
			return findKthLargest(nums, start, start + less - 1, k);
		else if (less + mid >= k)
			return nums[start + less + mid - 1];
		else
			return findKthLargest(nums, start + less + mid, end, k - (less + mid));
	}
//	http://www.programcreek.com/2014/05/leetcode-kth-largest-element-in-an-array-java/
//	https://leetcode.com/problems/kth-largest-element-in-an-array/
	public int findKthLargest(int[] nums, int k) {
		//k is assured between 1 and nums.length;

		//for God sake, this problem is so tricy...
//		return findKthLargest(nums, 0, nums.length - 1, k);
		return findKthLargest(nums, 0, nums.length - 1, nums.length + 1 - k);

	}

//	https://leetcode.com/problems/sort-colors/
//Sort Colors
	public void sortColors(int[] nums) {
		if (nums == null || nums.length <= 1)
			return;
		int red = -1;
		int cur = 0, end = nums.length - 1;
		while (cur <= end) {
			if (nums[cur] == 0) {
				intSwap(nums, cur++, ++red);
			} else if (nums[cur] == 2) {
				intSwap(nums, cur, end);
				while (cur <= end && nums[end] == 2) {
					--end;
				}
			} else {
				cur++;
			}
		}
	}

//	https://leetcode.com/problems/word-search/
//	79. Word Search
	private  boolean wordSearchExistHelper(char[][] board, int i, int j, boolean [][] map, String word, int start) {
//		System.out.println(i + "\t" + j + "\t" + start);
		if (word.charAt(start) != board[i][j])
			return false;
		if (start + 1 == word.length())
			return true;

		map[i][j] = true;
		//up
		if (i > 0 && !map[i-1][j] && wordSearchExistHelper(board, i - 1, j, map, word, start + 1))
			return true;
		//down
		if (i + 1 < map.length && !map[i+1][j] && wordSearchExistHelper(board, i +1, j, map, word, start + 1))
			return true;

		//left
		if (j > 0 && !map[i][j-1] && wordSearchExistHelper(board, i, j-1, map, word, start + 1))
			return true;

		//right
		if (j + 1 < map[0].length && !map[i][j+1] && wordSearchExistHelper(board, i, j +1, map, word, start + 1))
			return true;


		map[i][j] = false;
		return false;
	}

	//How to avoid using a map...
//	http://www.programcreek.com/2014/06/leetcode-word-search-java/
	public boolean wordSearchExist(char[][] board, String word) {

		boolean [][] map = new boolean[board.length][];
		for (int k = 0; k < map.length; k++)
			map[k] = new boolean[board[0].length];

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == word.charAt(0) && wordSearchExistHelper(board, i, j, map, word, 0)) {
					return true;
				}
			}
		return false;
	}

//	https://leetcode.com/problems/word-search-ii/
//	212. Word Search II
	public void addWord(TrieNode root, String word) {
		TrieNode cur = root;
		for (int i = 0; i < word.length(); i++) {
			if (cur.array[word.charAt(i) - 'a'] == null)
				cur.array[word.charAt(i) - 'a'] = new TrieNode();
			cur = cur.array[word.charAt(i) - 'a'];
		}
		cur.ending = true;
	}

	private void findWordsHelper(char[][] board, int i, int j, boolean [][] map, TrieNode root, HashSet<String> set, StringBuilder sb) {
		if (root.array[board[i][j] - 'a'] != null) {
			sb.append(board[i][j]);
			root = root.array[board[i][j] - 'a'];
			if (root.ending) {
				set.add(sb.toString());
			}

			map[i][j] = true;
			//up
			if (i > 0 && !map[i-1][j])
				findWordsHelper(board, i - 1, j, map, root, set, sb);

			//down
			if (i + 1 < map.length && !map[i+1][j])
				findWordsHelper(board, i + 1, j, map, root, set, sb);

			//left
			if (j > 0 && !map[i][j-1])
				findWordsHelper(board, i, j - 1, map, root, set, sb);

			//right
			if (j + 1 < map[0].length && !map[i][j+1])
				findWordsHelper(board, i, j + 1, map, root, set, sb);

			sb.deleteCharAt(sb.length() - 1);
			map[i][j] = false;
		}
	}
	public List<String> findWords(char[][] board, String[] words) {
		TrieNode root = new TrieNode();
		boolean [][] map = new boolean[board.length][];
		for (int k = 0; k < map.length; k++)
			map[k] = new boolean[board[0].length];

		//how to avoid duplicates???
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < words.length; i++) {
			addWord(root, words[i]);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				findWordsHelper(board, i, j, map, root, set, sb);
			}
		return new ArrayList<>(set);
	}

//	https://leetcode.com/problems/course-schedule/
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		List<Integer> candidates = new LinkedList<>();
		int [] counts = new int [numCourses];

		for (int i = 0; i < prerequisites.length; i++) {
			if (!map.containsKey(prerequisites[i][1])) {
				map.put(prerequisites[i][1], new ArrayList<>());
			}
			//assume no duplicate edges...
			List<Integer> list = map.get(prerequisites[i][1]);
			list.add(prerequisites[i][0]);
			++counts[prerequisites[i][0]];
			map.put(prerequisites[i][1], list);
		}
		for (int i = 0; i < counts.length; i++)
			if (counts[i] == 0)
				candidates.add(i);
		int ava = 0;
		while (candidates.size() != 0) {
			int cur = candidates.remove(0);
			++ava;
			List<Integer> list = map.get(cur);
			if (list != null) {
				Iterator<Integer> iterator = list.iterator();
				while (iterator.hasNext()) {
					int val = iterator.next();
					--counts[val];
					if (counts[val] == 0)
						candidates.add(val);
				}
			}
		}
		return ava == numCourses;
	}

//	https://leetcode.com/problems/course-schedule-ii/
//	210. Course Schedule II
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		List<Integer> candidates = new LinkedList<>();
		int [] counts = new int [numCourses];

		for (int i = 0; i < prerequisites.length; i++) {
			if (!map.containsKey(prerequisites[i][1])) {
				map.put(prerequisites[i][1], new ArrayList<>());
			}
			//assume no duplicate edges...
			List<Integer> list = map.get(prerequisites[i][1]);
			list.add(prerequisites[i][0]);
			++counts[prerequisites[i][0]];

			map.put(prerequisites[i][1], list);
		}
		for (int i = 0; i < numCourses; i++)
			if (counts[i] == 0)
				candidates.add(i);

		int[] ret = new int [numCourses];
		int k = 0;
		while (candidates.size() != 0) {
			List<Integer> next = new ArrayList<>();
			while (candidates.size() != 0) {
				int cur = candidates.remove(0);
				ret[k++] = cur;
				List<Integer> list = map.get(cur);
				if (list != null) {
					Iterator<Integer> iterator = list.iterator();
					while (iterator.hasNext()) {
						int val = iterator.next();
						--counts[val];
						if (counts[val] == 0)
							//you can add orignal queue tail directly....
							//	BFS
							next.add(val);
					}
				}
			}
			candidates = next;
		}
		if (k == ret.length)
			return ret;
		else
			return new int [0];
	}

//	https://leetcode.com/problems/minimum-size-subarray-sum/
//	http://www.cnblogs.com/grandyang/p/4501934.html
	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length <= 0)
			return 0;
		if (s <= 0)
			return 1;
		int ret = nums.length + 1;
		long sum = 0;
		//overflow issues....
//		int sum = 0;
		int l = 0;
		for (int i = 0; i < nums.length && ret != 1; i++) {
			sum += nums[i];
			l++;
			if (sum >= s) {
				while (sum - nums[i + 1 - l]>= s) {
					sum -= nums[i + 1 - l];
					--l;
				}
				ret = Math.min(ret, l);
			}
		}

		if (ret > nums.length)
			return 0;
		else
			return ret;
	}

//	https://leetcode.com/problems/isomorphic-strings/
	public boolean isIsomorphic(String s, String t) {
		if (s == null && t == null)
			return true;
		if (s == null || t == null)
			return false;
		if (s.length() != t.length())
			return false;
		Map<Character, Character> map = new HashMap<>();
		Map<Character, Character> reverseMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				if (map.get(s.charAt(i)) != t.charAt(i))
					return false;
			} else if (reverseMap.containsKey(t.charAt(i)))
				return false;
			else {
				map.put(s.charAt(i), t.charAt(i));
				reverseMap.put(t.charAt(i), s.charAt(i));
			}
		}

		return true;
	}

//	https://leetcode.com/problems/reverse-linked-list/
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode pre = null, cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

//	https://leetcode.com/problems/count-primes/
	public int countPrimes(int n) {
		if (n <= 2)
			return 0;

		/* Below code is not efficient enough because of too much data movement???
		int ret = 1;
		List<Integer> list = new ArrayList<>();
		for (int i = 3; i <= n; i+=2)
			list.add(i);
		int mid = (int) Math.sqrt(n);
		while (list.size() > 0 && list.get(0) < mid) {
			++ret;
			int div = list.remove(0);
			int i = 0;
			while (i < list.size()) {
				if (list.get(i)%div == 0)
					list.remove(i);
				else
					++i;
			}
		}

		return ret + list.size();
		*/
		boolean[] isPrime = new boolean[n];
		for (int i = 2; i < n; i++) {
			isPrime[i] = true;
		}
		// Loop's ending condition is i * i < n instead of i < sqrt(n)
		// to avoid repeatedly calling an expensive function sqrt().
		//see below, how to avoid multification....
		//So many tricky optimization...
		for (int i = 2; i * i < n; i++) {
			if (!isPrime[i]) continue;
			for (int j = i * i; j < n; j += i) {
				isPrime[j] = false;
			}
		}
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (isPrime[i]) count++;
		}
		return count;
	}

//	https://leetcode.com/problems/happy-number/
	public boolean isHappy(int n) {
		HashSet<Integer> set = new HashSet<>();
		while (n != 1 && !set.contains(n)) {
			set.add(n);
			int sum = 0;
			while (n != 0) {
				int d = n % 10;
				sum += d * d;
				n /= 10;
			}
			n = sum;
		}
		return n == 1;
	}

//	http://blog.welkinlan.com/2015/09/06/basic-calculator-i-ii-leetcode-java/
//	http://www.geeksforgeeks.org/expression-evaluation/
//	https://leetcode.com/problems/basic-calculator/
//			224. Basic Calculator
	int calcI(int a, int b, char op) {
		if (op == '+')
			return a + b;
		else
			return a - b;
	}
	public int calculateI(String s) {
		//assume the expression is valid...
		//assume the result will not overflow during the calculation...
		int ret = 0;
		int i = 0;
		Stack<Integer> data = new Stack<>();
		Stack<Character> op = new Stack<>();

		//how about "5     " as input.. You might have no operators..
		while (i < s.length()) {
			while (i < s.length() && Character.isSpaceChar(s.charAt(i)))
				++i;
			if (i < s.length()) {
				if (Character.isDigit(s.charAt(i))) {
					//this is one op...
					int sum = 0;
					while (i < s.length() && Character.isDigit(s.charAt(i))) {
						sum = 10 * sum + (s.charAt(i) - '0');
						++i;
					}
					data.push(sum);
				} else {
					//fetch op...
					char operator = s.charAt(i);
					switch (operator) {
						case '+':
						case '-':
							if (!op.isEmpty() && (op.peek() == '+' || op.peek() == '-')) {
								int b = data.pop();
								int a = data.pop();
								data.push(calcI(a, b, op.pop()));
							}
							op.push(operator);
							break;
						case '(':
							op.push(operator);
							break;
						case ')':
							while (op.peek() != '(') {
								int b = data.pop();
								int a = data.pop();
								data.push(calcI(a, b, op.pop()));
							}
							op.pop();//pop out '('
							//continues pop out
							break;
					}
					++i;
				}
			}
		}
		while (!op.isEmpty()) {
			int b = data.pop();
			int a = data.pop();
			data.push(calcI(a, b, op.pop()));
		}
		return data.peek();
	}

	int calcII(int a, int b, char op) {
		switch (op) {
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				return a / b;
		}
		return Integer.MAX_VALUE;
	}
//	https://leetcode.com/problems/basic-calculator-ii/
//	227. Basic Calculator II
	public int calculateII(String s) {
		int i = 0;
		Stack<Integer> data = new Stack<>();
		Stack<Character> op = new Stack<>();

		//how about "5     " as input.. You might have no operators..
		while (i < s.length()) {
			while (i < s.length() && Character.isSpaceChar(s.charAt(i)))
				++i;
			if (i < s.length()) {
				if (Character.isDigit(s.charAt(i))) {
					//this is one op...
					int sum = 0;
					while (i < s.length() && Character.isDigit(s.charAt(i))) {
						sum = 10 * sum + (s.charAt(i) - '0');
						++i;
					}
					data.push(sum);
				} else {
					//fetch op...
					char operator = s.charAt(i);
					switch (operator) {
						case '+':
						case '-':
							//This is always tricky... Why not if below???
							while (!op.isEmpty()) {
								int b = data.pop();
								int a = data.pop();
								data.push(calcII(a, b, op.pop()));
							}
							op.push(operator);
							break;
						case '*':
						case '/':
							if (!op.isEmpty() && (op.peek() == '*' || op.peek() == '/')) {
								int b = data.pop();
								int a = data.pop();
								data.push(calcII(a, b, op.pop()));
							}
							op.push(operator);
							break;
					}
					++i;
				}
			}
		}
		while (!op.isEmpty()) {
			int b = data.pop();
			int a = data.pop();
			data.push(calcII(a, b, op.pop()));
		}
		return data.peek();
	}

//	https://leetcode.com/problems/bitwise-and-of-numbers-range/
//	https://leetcode.com/submissions/detail/26097513/
//	201. Bitwise AND of Numbers Range
	public int rangeBitwiseAnd(int m, int n) {
		int ret = 0;
		for (int i = 31; i >= 0; i--) {
			int mask = (0x01 << i);
			if ((m & mask) == (n & mask))
				ret |= (m & mask);
			else
				break;
		}
		return ret;
	}

//	https://leetcode.com/problems/number-of-islands/
//	200. Number of Islands
	//You can also use UF...
	private void numIslandsHelper(char[][] grid, int i, int j) {
		grid[i][j] = 'C';
		//up
		if (i > 0 && grid[i-1][j] == '1')
			numIslandsHelper(grid, i -1, j);
		//down
		if (i + 1 < grid.length && grid[i+1][j] == '1')
			numIslandsHelper(grid, i +1, j);
		//left
		if (j > 0 && grid[i][j-1] == '1')
			numIslandsHelper(grid, i, j-1);
		//right
		if (j + 1 < grid[0].length && grid[i][j+1] == '1')
			numIslandsHelper(grid, i, j+1);
	}
	public int numIslands(char[][] grid) {
		int ret = 0;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					++ret;
					numIslandsHelper(grid, i, j);
				}
			}
		return ret;
	}

//	https://leetcode.com/problems/binary-tree-right-side-view/
//	199. Binary Tree Right Side View
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> ret = new ArrayList<>();
		//You can use queue here if you insert from right to left

		ArrayDeque<TreeNode> queue = new ArrayDeque<>();
		if (root != null)
			queue.add(root);
		while (!queue.isEmpty()) {
			ArrayDeque<TreeNode> next = new ArrayDeque<>();
			ret.add(queue.getLast().val);
			while (!queue.isEmpty()) {
				TreeNode cur = queue.poll();
				if (cur.left != null)
					next.add(cur.left);
				if (cur.right != null)
					next.add(cur.right);
			}

			queue = next;
		}
		return ret;
	}

//	https://leetcode.com/problems/dungeon-game/
//	174. Dungeon Game
	private void calculateMinimumHPBF(int[][] dungeon, int i, int j, int acc, int cur_min, int [] ret) {
		if (ret[0] > 0)
			return;

		acc += dungeon[i][j];
		if (acc < cur_min)
			cur_min = acc;

		if (i + 1 == dungeon.length && j + 1 == dungeon[0].length) {
			if (ret[0] < cur_min)
				ret[0] = cur_min;
			return;
		}
		//right
		if (j + 1 < dungeon[0].length) {
			calculateMinimumHPBF(dungeon, i, j + 1, acc+dungeon[i][j+1], cur_min, ret);
		}
		//down
		if (i + 1 < dungeon.length) {
			calculateMinimumHPBF(dungeon, i + 1, j, acc+dungeon[i + 1][j], cur_min, ret);
		}
	}
//	https://leetcode.com/submissions/detail/25884161/
//	http://www.cnblogs.com/grandyang/p/4233035.html
//	http://bookshadow.com/weblog/2015/01/07/leetcode-dungeon-game/
//	h[i][j] is the minimum health value before he enters (i,j). h[0][0] is the value of the answer.
	//Below code TLE..
	public int calculateMinimumHP(int[][] dungeon) {
		//whenever in which room, the K must have at least 1 life
		int [] ret = new int[1];
		ret[0] = Integer.MIN_VALUE;
		calculateMinimumHPBF(dungeon, 0,0,0,0,ret);
		if (ret[0] > 0)
			return 0;
		else
			return 1 - ret[0];
	}

//	https://leetcode.com/problems/rotate-array/
//	http://www.programcreek.com/2015/03/rotate-array-in-java/
//	189. Rotate Array
	int gcd(int x, int y) {
		//x >= y
		int remainder = x % y;
		if (remainder == 0)
			return y;
		else
			return gcd(y, remainder);
	}

	public void rotate(int[] nums, int k) {
		//think about all wired input... Please..
		if (nums == null || nums.length <= 1 || k % nums.length == 0)
			return;
		int length = nums.length;
		int d = gcd(length, k % length);
//		Use the gcd (greatest common divisor) between n and k. Because we need to find how many "circles" are there in the sequence, one circle is like counting each element nums[i] + k + k + k... and back to nums[i]. If the gcd is 1, which means only one circle will cover all the elements in the sequence, so only 1 iteration is needed.If the gcd is 2, there are 2 circles, so 2 iterations are needed.
		for (int i = 0; i < d; i++) {
			int j = i;
			int pre = nums[j];
			do {
				int next = (j + k) % length;
				int tmp = nums[next];
				nums[next] = pre;
				pre = tmp;
				j = next;
			} while (j != i);
		}

	}


//	186, Reverse Words in a String II
//Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
//	The input string does not contain leading or trailing spaces and the words are always separated by a single space.
//	For example,
//	Given s = "the sky is blue",
//	return "blue is sky the".
//	Could you do it in-place without allocating extra space?
	public void reverseWords(char[] s) {

		//with extra spaces...
		if (s == null || s.length <= 1)
			return;
		/*
		int length = s.length;
		char [] t = Arrays.copyOf(s, length);
		int s1 = length - 1;//the next available slot for write
		int t1 = 0;//starting place for search...
//		while (t1 < length) {
		while (s1 >= 0) {
			int t2 = t1;
			while (t2 < length && t[t2] != ' ')
				++t2;
			//copy
			int l = t2 - t1;// l >= 1
			int sx = s1 + 1- l;
			while (t1 < t2) {
				s[sx++] = t[t1++];
			}
			s1 -= l;
			if (s1 >= 0) {
				s[s1] = ' ';
				--s1;
				++t1;
			}
			//t1 equals to t2 now...
		}
		*/
		reverseWords(s, 0, s.length - 1);
		//Becareful if you only has one word.... You don't need to reverse back and force...
		//One word is fine as you only cares about one word.....
		int i = 0;
		while (i < s.length) {
			int j = i;
			while (j < s.length && s[j] != ' ')
				++j;
			reverseWords(s, i, j - 1);
			i = ++j;
		}
	}

	private void reverseWords(char[] s, int i, int j) {
		while (i < j) {
			char tmp = s[i];
			s[i] = s[j];
			s[j] = tmp;
			++i;
			--j;
		}
	}

	public int trailingZeroes(int n) {
		//assume n >= 0
		int ret = 0;
		while (n > 0) {
			n /= 5;
			ret += n;
		}
		return ret;
	}


//	167, Two Sum II - Input array is sorted
//Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
//	The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
//	You may assume that each input would have exactly one solution.
//			Input: numbers={2, 7, 11, 15}, target=9
//	Output: index1=1, index2=2
	public int[] twoSumII(int[] numbers, int target) {
		int l = 0, r = numbers.length - 1;
		while (l < r) {
			int sum = numbers[l] + numbers[r];
			if (sum < target)
				++l;
			else if (sum > target)
				--r;
			else {
				int [] tmp = new int [2];
				tmp[0] = l;
				tmp[1] = r;
				return  tmp;
			}
		}

		return null;
	}

//	https://leetcode.com/problems/maximum-gap/
	//Difficlut to understand what the hell is talking about there...
//	给定一个未排序的数组，找出其排序后的序列中两个相邻元素之间的最大差值。
//	最好在线性时间、线性空间复杂度内完成。
//	164. Maximum Gap

	//This one is tricky....
//	http://bookshadow.com/weblog/2014/12/14/leetcode-maximum-gap/
//	http://yucoding.blogspot.com/2014/12/leetcode-question-maximum-gap.html
	class MaximumGapBucket{
		int low;
		int high;
		public MaximumGapBucket(){
			low = -1;
			high = -1;
		}
	}

//	http://bookshadow.com/weblog/2014/12/14/leetcode-maximum-gap/
//	http://www.programcreek.com/2014/03/leetcode-maximum-gap-java/
	public int maximumGapBucket(int[] nums) {
		if (nums == null || nums.length < 2)
			return 0;

		//You can also use two arrays to do this....
		int max = nums[0];
		int min = nums[0];
		for(int i=1; i<nums.length; i++){
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[i]);
		}

		//ceil, The method ceil gives the smallest integer that is greater than or equal to the argument.
		//floor, The method floor gives the largest integer that is less than or equal to the argument.

		//		假设有N个元素A到B。
//		那么最大差值不会小于ceiling[(B - A) / (N - 1)]
//		令bucket（桶）的大小len = ceiling[(B - A) / (N - 1)]，则最多会有(B - A) / len + 1个桶
//		对于数组中的任意整数K，很容易通过算式loc = (K - A) / len找出其桶的位置，然后维护每一个桶的最大值和最小值
//		由于同一个桶内的元素之间的差值至多为len - 1，因此最终答案不会从同一个桶中选择。
//		对于每一个非空的桶p，找出下一个非空的桶q，则q.min - p.max可能就是备选答案。返回所有这些可能值中的最大值。
		//Or you can make your bucket small enough so that the answer must be in different buckets...
		//Below is a good approximate...
		// initialize an array of buckets
		MaximumGapBucket[] buckets = new MaximumGapBucket[nums.length+1]; //project to (0 - n)
		for(int i=0; i<buckets.length; i++){
			buckets[i] = new MaximumGapBucket();
		}

		double interval = (double) nums.length / (max - min);
		//distribute every number to a bucket array
		for(int i=0; i<nums.length; i++){
			int index = (int) ((nums[i] - min) * interval);

			if(buckets[index].low == -1){
				buckets[index].low = nums[i];
				buckets[index].high = nums[i];
			}else{
				buckets[index].low = Math.min(buckets[index].low, nums[i]);
				buckets[index].high = Math.max(buckets[index].high, nums[i]);
			}
		}

		//scan buckets to find maximum gap
		int result = 0;
		//why he is so sure, becase the min must be in the first bucket....
		int prev = buckets[0].high;
		for(int i=1; i<buckets.length; i++){
			//Becareful, some buckets might be empty....
			if(buckets[i].low != -1){
				result = Math.max(result, buckets[i].low-prev);
				prev = buckets[i].high;
			}

		}

		return result;
	}


	public int maximumGapRadix(int[] nums) {
			if (nums == null || nums.length < 2)
				return 0;
			int length = nums.length;
			int [] keys = new int [length];
			int [] couts = new int [2];
			int [] sorted = new int [length];
			//use radix sort here...
			//as we don't have negative numbers, come to 30 should be good enough
			for (int i = 0; i < 30; i++) {
	//		for (int i = 0; i < 31; i++) {
				int mask = 1 << i;
				couts[0] = 0;
				couts[1] = 0;
				for (int j = 0; j < length; j++) {
					keys[j] = (nums[j] & mask) == 0 ? 0 : 1;
					++couts[keys[j]];
				}
				couts[1] += couts[0];
				for (int j = length - 1; j >= 0; j--) {
					sorted[couts[keys[j]]-1] = nums[j];
					--couts[keys[j]];
				}
	//			System.out.println("mask:\t" + mask);
	//			for (int j = 0; j < length; j++) {
	//				System.out.print(sorted[j] + "\t");
	//			}
	//			System.out.println();
				System.arraycopy(sorted, 0, nums, 0, length);
			}
			int ret = 0;
			for (int i = 1; i < length; i++)
				ret = Math.max(ret, nums[i] - nums[i-1]);
			return ret;
	}
	int minBills(int target, int [] bills) {
		int [] tables = new int [target + 1];
		for (int i = 1; i <= target; i++) {
			tables[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < bills.length; i++) {
			if (bills[i] <= target)
				tables[bills[i]] = 1;
		}

		for (int i = 1; i <= target; i++) {
			if (tables[i] != Integer.MAX_VALUE) {
				for (int j = 0; j < bills.length; j++) {
					if (i + bills[j] <= target)
						tables[i + bills[j]] = Math.min(tables[i + bills[j]], 1 + tables[i]);
				}
			}
		}
		return tables[target] == Integer.MAX_VALUE ? -1 : tables[target];
	}

//	https://leetcode.com/problems/compare-version-numbers/
	//Ugly...
//	https://leetcode.com/submissions/detail/26392925/
//	Does 1..0 a correct input?
//	Are 1.0, 1 and 1.0.0 the same version number?
//	Are 1 and 01 the same version number?
	//I don't think Integer.parseInt is OK here, we can have overflow...., anything compared as integer directly should be consider wrong...
//	http://www.jyuan92.com/blog/leetcode-compare-version-numbers/
	public int compareVersion(String version1, String version2) {

		//compare with 3 and 3., fuckk.....
		String [] v1 = version1.split("\\.");
		String [] v2 = version2.split("\\.");
//		System.out.println(v1.length);
//		System.out.println(v2.length);

		int i;
		for (i = 0; i < v1.length && i < v2.length; i++) {
			//compare with v1[i], v2[i]
			/* why below not working, leading 0s....
			if (v1[i].length() < v2[i].length())
				return -1;
			else if (v1[i].length() > v2[i].length())
				return +1;
			else {
				for (int j = 0; j < v1[i].length(); j++)
					if (v1[i].charAt(j) != v2[i].charAt(j))
						return v1[i].charAt(j) < v2[i].charAt(j) ? -1 : +1;
			}
			*/
			//skip leading 0s
			int k1 = 0, k2 = 0;
			while (k1 < v1[i].length() && v1[i].charAt(k1) == '0')
				k1++;
			while (k2 < v2[i].length() && v2[i].charAt(k2) == '0')
				k2++;
			//let's compare
			//remaing length
			int r1 = v1[i].length() - k1, r2 = v2[i].length() - k2;
			if (r1 != r2)
				return r1 < r2 ? -1 : 1;
			while (k1 < v1[i].length() && k2 < v2[i].length()) {
				if (v1[i].charAt(k1) != v2[i].charAt(k2)) {
					return v1[i].charAt(k1) < v2[i].charAt(k2) ? -1 : +1;
				}
				++k1;
				++k2;
			}
		}
		//They also have 1.0 and 1.... Shit....
		/*
		if (v1.length == v2.length)
			return 0;
		else
			return v1.length < v2.length ? -1 : +1;
		*/
		if (v1.length == v2.length)
			return 0;
		else {
			if (v1.length < v2.length) {
				for (int j = i; j < v2.length; j++)
					if ((!v2[j].matches("^[0]+$")))
						return -1;
			} else {
//				v1.length > v2.length
				for (int j = i; j < v1.length; j++)
					if ((!v1[j].matches("^[0]+$")))
						return +1;

			}
			return 0;
		}
	}

//	https://leetcode.com/problems/fraction-to-recurring-decimal/
//	http://www.programcreek.com/2014/03/leetcode-fraction-to-recurring-decimal-java/
	public String fractionToDecimal(int numerator, int denominator) {
		if (denominator == 0)
			return new String("NaN");
		else if (numerator == 0)
			return Integer.toString(0);

		StringBuilder sb = new StringBuilder();
		long x, y;
		boolean isSigned = false;
		if (numerator < 0) {
			x = 0 - (long)numerator;
			isSigned = !isSigned;
		} else {
			x = numerator;
		}

		if (denominator < 0) {
			y = 0 - (long)denominator;
			isSigned = !isSigned;
		} else {
			y = denominator;
		}

		if (x >= y) {
			long n = x / y;
			sb.append(n);
			x %= y;
		} else {
			sb.append('0');
		}

		//remainder to index mapping
		HashMap<Long, Integer> map = new HashMap<>();
		if (x != 0) {
			sb.append('.');
			while (x != 0 && !map.containsKey(x)) {
				Long key = x;
				x *= 10;
				long n = x / y;
				sb.append(n);
				x %= y;
				map.put(key, sb.length() - 1);
			}

			if (x != 0) {
				//add parenthesis
				sb.insert((int)map.get(x), '(');
				sb.append(')');
			}
		}
		if (isSigned)
			sb.insert(0, '-');
		return sb.toString();
	}

//	163	Missing Ranges
//	Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
//	For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

	//I don't like the asns on lines.. Which are all kinds of bugs..
//	https://segmentfault.com/a/1190000003790309
public List<String> findMissingRanges(int[] nums, int lower, int upper) {
	//what if nums is null??? Please consider all inputs....
	List<String> ret = new ArrayList<>();
	int i = 0;
	while (i < nums.length && nums[i] < lower) {
		++i;
	}

	//we assume lower <= uppper, which means lower can't larger than upper
//if would be better also check whether lower == upper??
	if (i < nums.length && nums[i] <= upper) {
		//pick up the first one
		//fill up the first one
		if (nums[i] > lower) {
			if (nums[i] == lower + 1)
				ret.add(String.valueOf(lower));
			else
				ret.add(lower+"->"+(nums[i]-1));
		}
		++i;
		while (i < nums.length && nums[i] <= upper) {
			//fill in middle
			//we have nums[i-1] and nums[i]
			//we assume no duplicatess.
			if (nums[i-1] + 1 < nums[i]) {
				//we have gaps
				//from nums[i-1] + 1 to nums[i] - 1
				if (nums[i-1] + 1 == nums[i] - 1) {
					ret.add(String.valueOf(nums[i-1] + 1));
				} else {
					ret.add((nums[i-1] + 1)+"->"+(nums[i] - 1));
				}
			}
			++i;
		}
		//no matter what, nums[i-1] is Good
		if (nums[i-1] != upper) {
			if (nums[i-1] + 1 != upper) {
				ret.add((nums[i-1] + 1)+"->"+(upper));
			} else {
				ret.add(String.valueOf(upper));
			}
		}
	} else {
		if (lower == upper) {
			ret.add(String.valueOf(upper));
		} else {
			ret.add(lower+"->"+(upper));
		}
	}

	return ret;
}

//	162. Find Peak Element
//	https://leetcode.com/problems/find-peak-element/
	public int findPeakElement(int[] nums) {
		if (nums == null || nums.length <=2)
			return -1;
		//why it works???
		//I really don't understand the code without below explaination...
//		首先我们找到中间节点mid，如果大于两边返回当前index就可以了，如果左边的节点比mid大，那么我们可以继续在左半区间查找，这里面一定存在一个peak，为什么这么说呢？假设此时的区间范围为[0, mid - 1]， 因为num[mid - 1]一定大于num[mid]了，如果num[mid - 2] <= num[mid - 1]，那么num[mid - 1]就是一个peak。如果num[mid - 2] > num[mid - 1]，那么我们就继续在[0, mid - 2]区间查找，因为num[-1]为负无穷，所以最终我们绝对能在左半区间找到一个peak。同理右半区间一样。
//		https://leetcode.com/submissions/detail/26317803/
		return 0;
	}

//	161	One Edit Distance
	//	Given two strings S and T, determine if they are both one edit distance apart.
public boolean isOneEditDistance(String s, String t) {

//	https://segmentfault.com/a/1190000003906621
//	虽然我们可以用Edit Distance的解法，看distance是否为1，但Leetcode中会超时。这里我们可以利用只有一个不同的特点在O(N)时间内完成。如果两个字符串只有一个编辑距离，则只有两种情况：
//	两个字符串一样长的时候，说明有一个替换操作，我们只要看对应位置是不是只有一个字符不一样就行了
//	一个字符串比另一个长1，说明有个增加或删除操作，我们就找到第一个对应位置不一样的那个字符，如果较长字符串在那个字符之后的部分和较短字符串那个字符及之后的部分是一样的，则符合要求
//	如果两个字符串长度差距大于1，肯定不对

	//assume s != null && t != null
	int l1 = s.length(), l2 = t.length();
	if (Math.abs(l1-l2) > 1)
		return false;
	int i = 0;
	while (i < l1 && i < l2 && s.charAt(i) == t.charAt(i))
		++i;
	if (i == l1 || i == l2)
		return true;
	if (l1 == l2) {
		//we can skip current one
		++i;
		while (i < l1 && i < l2 && s.charAt(i) == t.charAt(i))
			++i;
		return i == l1;
	} else {
		//long one skip
		int j = i, k = i;
		if (l1 > l2) {
			++j;
		} else {
			++k;
		}
		//compare from j,k
		while (j < l1 && k < l2 && s.charAt(j) == t.charAt(k)) {
			++j;
			++k;
		}
		return (j == l1) && (k == l2);
	}
}

//	https://leetcode.com/problems/linked-list-cycle/
//	141	Linked List Cycle
	public boolean hasCycle(ListNode head) {
		ListNode fast = head, slow = head;
		while (fast != null && (fast = fast.next)!= null && fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}

		return fast != null;
	}

//	142. Linked List Cycle II
//	https://leetcode.com/problems/linked-list-cycle-ii/
	public ListNode detectCycle(ListNode head) {
		ListNode fast = head, slow = head;
		while (fast != null && (fast = fast.next)!= null && (fast = fast.next) != null && fast != (slow = slow.next)) {
			;
		}
		if (fast == null || head == fast)
			return fast;

		//below trick only works when the starting node is not the beginning one.. (There must be at least one step between head and cycle starting point)
		//Actually, the above one breaks the definition of head completely....
		while (head != fast) {
			head = head.next;
			fast = fast.next;
		}
		return head;
	}

	//	https://leetcode.com/problems/intersection-of-two-linked-lists/
	//Extremely, two linked list can be the exact same one...
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		//You don't have to be this tricky here, there is another way to handle this...
		/*
		if (headA == null || headB == null)
			return null;
		if (headA == headB)
			return headA;

		//find tail first...
		ListNode tailA = headA, tailB = headB;
		while (tailA.next != null)
			tailA = tailA.next;
		while (tailB.next != null)
			tailB = tailB.next;
		if (tailA != tailB)
			return null;

		tailA.next = headB;
		ListNode ret = detectCycle(headA);
		tailA.next = null;
		return ret;
		*/
		//hints: count the number of nodes....
//		http://yucoding.blogspot.com/2014/12/leetcode-question-intersection-of-two.html

		if (headA == null || headB == null)
			return null;
		if (headA == headB)
			return headA;
		int lengthA = 0, lengthB = 0;
		ListNode fA = headA, fB = headB;
		while (fA != null) {
			fA = fA.next;
			++lengthA;
		}
		while (fB != null) {
			fB = fB.next;
			++lengthB;
		}
		while (lengthA > lengthB) {
			--lengthA;
			headA = headA.next;
		}
		while (lengthB > lengthA) {
			--lengthB;
			headB = headB.next;
		}

		while (headA != null && headB != null && headA != headB) {
			headA = headA.next;
			headB = headB.next;
		}
		return headA;

//		解完后，打开Leetcode的solution, 找到一个很巧妙的解法。其实与解法1相比应该快不了多少，但是写出来超有B格的。。
//		Two pointer solution (O(n+m) running time, O(1) memory):
//		Maintain two pointers pA and pB initialized at the head of A and B, respectively. Then let them both traverse through the lists, one node at a time.
//		When pA reaches the end of a list, then redirect it to the head of B (yes, B, that's right.); similarly when pB reaches the end of a list, redirect it the head of A.
//		If at any point pA meets pB, then pA/pB is the intersection node.
//		To see why the above trick would work, consider the following two lists: A = {1,3,5,7,9,11} and B = {2,4,9,11}, which are intersected at node '9'. Since B.length (=4) < A.length (=6), pB would reach the end of the merged list first, because pB traverses exactly 2 nodes less than pA does. By redirecting pB to head A, and pA to head B, we now ask pB to travel exactly 2 more nodes than pA would. So in the second iteration, they are guaranteed to reach the intersection node at the same time.
//		If two lists have intersection, then their last nodes must be the same one. So when pA/pB reaches the end of a list, record the last element of A/B respectively. If the two last elements are not the same one, then the two lists have no intersections.
//		http://www.cnblogs.com/yuzhangcmu/p/4128794.html
//
//		There are many solutions to this problem:
//
//		Brute-force solution (O(mn) running time, O(1) memory):
//		For each node ai in list A, traverse the entire list B and check if any node in list B coincides with ai.
//
//		Hashset solution (O(n+m) running time, O(n) or O(m) memory):
//		Traverse list A and store the address / reference to each node in a hash set. Then check every node bi in list B: if bi appears in the hash set, then bi is the intersection node.
//
//				Two pointer solution (O(n+m) running time, O(1) memory):
//		Maintain two pointers pA and pB initialized at the head of A and B, respectively. Then let them both traverse through the lists, one node at a time.
//		When pA reaches the end of a list, then redirect it to the head of B (yes, B, that's right.); similarly when pB reaches the end of a list, redirect it the head of A.
//		If at any point pA meets pB, then pA/pB is the intersection node.
//		To see why the above trick would work, consider the following two lists: A = {1,3,5,7,9,11} and B = {2,4,9,11}, which are intersected at node '9'. Since B.length (=4) < A.length (=6), pB would reach the end of the merged list first, because pB traverses exactly 2 nodes less than pA does. By redirecting pB to head A, and pA to head B, we now ask pB to travel exactly 2 more nodes than pA would. So in the second iteration, they are guaranteed to reach the intersection node at the same time.
//				If two lists have intersection, then their last nodes must be the same one. So when pA/pB reaches the end of a list, record the last element of A/B respectively. If the two last elements are not the same one, then the two lists have no intersections.


	}


//	159	Longest Substring with At Most Two Distinct Characters
	//	Given a string, find the longest substring that contains only two unique characters. For example, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is "bcbbbbcccb".
//Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
//	For example, Given s = “eceba”,
//	T is "ece" which its length is 3.
public int lengthOfLongestSubstringTwoDistinct(String s) {
	if (s == null)
		return 0;
	int length = s.length();
	if (length <= 2)
		return length;

	HashMap<Character, Integer> map = new HashMap<>();
	int ret = 0;
	int start = 0;
	int i;
	for (i = 0; i < length; i++) {
		if (map.containsKey(s.charAt(i))) {
			map.put(s.charAt(i), 1 + map.get(s.charAt(i)));
		} else if (map.size() == 2) {
			while (map.size() > 1) {
				int val = map.get(s.charAt(start));
				--val;
				if (val == 0)
					map.remove(s.charAt(start));
				else
					map.put(s.charAt(start), val);
				++start;
			}
			map.put(s.charAt(i), 1);
		} else {
			map.put(s.charAt(i), 1);
		}
		ret = Math.max(ret, i - start + 1);
	}

	return ret;
}

//	https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
//	https://leetcode.com/submissions/detail/14603999/
//	153. Find Minimum in Rotated Sorted Array
public int findMin(int[] nums) {
	if (nums == null || nums.length <= 0)
		return 0;
	int length = nums.length;
	if (length <= 1)
		return nums[0];
	int l = 0, r = length-1;
	while (l < r) {
		//compare two ends
		if (nums[l] < nums[r])
			return nums[l];
		else {
			int mid = l + ((r - l) >> 1);
			//Why euqal is here???? mid might be the same as l....
			if (nums[l] <= nums[mid])
				l = mid + 1;
			else {
				//need make sure mid is not the ans.
				//l,r are always valid index
				if (nums[mid-1] > nums[mid])
					return nums[mid];
				r = mid - 1;
			}
		}
	}
	return nums[l];
}

//	https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
//	154. Find Minimum in Rotated Sorted Array II
	//I like below post...
//	http://bangbingsyb.blogspot.com/2014/11/leecode-find-minimum-in-rotated-sorted.html
	public int findMinII(int[] nums) {
		if (nums == null || nums.length <= 0)
			return 0;
		int length = nums.length;
		if (length <= 1)
			return nums[0];
		int l = 0, r = length-1;
		while (l < r && nums[l] >= nums[r]) {
			//compare two ends
			if (nums[l] == nums[r]) {
				int ret = nums[l];
				for (int k = l + 1; k <= r; k++)
					ret = Math.min(ret, nums[k]);
				return ret;
			} else {
				int mid = l + ((r - l) >> 1);
				//Why euqal is here???? mid might be the same as l....
				if (nums[l] <= nums[mid])
					l = mid + 1;
				else {
					//need make sure mid is not the ans.
					//l,r are always valid index
					if (nums[mid-1] > nums[mid])
						return nums[mid];
					r = mid - 1;
				}
			}
		}
		return nums[l];
	}
//	https://leetcode.com/problems/word-break/
//	139. Word Break
	private boolean wordBreakIHelper(String s, int start, Set<String> wordDict) {
		if (start >= s.length())
			return true;
		for (int i = start; i < s.length(); i++) {
			String tmp = s.substring(start, i + 1);
			if (wordDict.contains(tmp) && wordBreakIHelper(s, i + 1, wordDict))
				return true;
		}
		return false;
	}
	public boolean wordBreakI(String s, Set<String> wordDict) {
		if (s == null || s.length() <= 0)
			return false;

		//assume at least length of 1
		//Could one word been used multiple times???
		/* TLE with below method...
		return wordBreakIHelper(s,0,wordDict);
		*/

		int length = s.length();
//		boolean [][] table = new boolean [length][];
//		for (int i = 0; i < length; i++)
//			table[i] = new boolean [length];
		/*
		//not good enough because too many useless check, see below..
		for (int l = 1; l <= length; l++) {
			for (int i = 0; i + l <= length; i++) {
				String tmp = s.substring(i, i + l);
				//fill T[i][i+l-1]
				if (wordDict.contains(tmp))
					table[i][i+l-1] = true;
				else {
					for (int k = i; k < i + l - 1 && !table[i][i+l-1]; k++)
						if (table[i][k] && table[k+1][i+l-1])
							table[i][i+l-1] = true;
				}
			}
		}
		*/

		//The key point is recuding uncessary calculation..
		/*
		for (int i = 0; i < length; i++)
			for (int j = i; j < length; j++)
				if (wordDict.contains(s.substring(i, j + 1))) {
					table[i][j] =true;
				}
		for (int i = length - 2; i >= 0; i--){
			for (int j = i; j + 1< length && !table[i][length-1]; j++)
				if (table[i][j] && table[j+1][length-1])
					table[i][length-1] = true;
		}
		*/

		//you can improve this further by using one D table...
		//see https://leetcode.com/submissions/detail/10247405/
		//you don't need that must ...
		boolean [] T = new boolean [length + 1];
		T[0] = true;
		//T[i] denotes s.substr(0, i+1) is reachable...
		for (int i = 1; i <= length; i++) {
			//fill T[i]
			for (int j = 1; j <= i && !T[i]; j++) {
				if (T[j-1] && wordDict.contains(s.substring(j-1, i)))
					T[i] = true;
			}

		}

		return T[length];
//		return table[0][length-1];
	}

//	https://leetcode.com/problems/word-break-ii/
//	140. Word Break II
	void wordBreakIIHelper(List<String> ret, ArrayList<Integer> tmp, boolean [][] table, int index, String s) {
		if (index == table.length) {
			//copy data
			//this should be always true
			StringBuilder sb = new StringBuilder();
			sb.append(s.substring(0, tmp.get(0) + 1));
			for (int i = 1; i < tmp.size(); i++)
				sb.append(" " + s.substring(tmp.get(i-1) + 1, tmp.get(i) + 1));
			ret.add(sb.toString());
		} else {
			for (int i = index; i < table.length; i++) {
				if (table[index][i]) {
					tmp.add(i);//always add ending index
					wordBreakIIHelper(ret, tmp, table, i + 1, s);
					tmp.remove(tmp.size() - 1);
				}
			}
		}
	}

	//even so you still have redudant calculation..., how to avoid uncessary recursion(do it just for once..)..
	List<ArrayDeque<Integer>> wordBreakIIHelper(boolean [][] table, int index) {
		List<ArrayDeque<Integer>> ret = new ArrayList<>();
		for (int i = index; i < table.length; i++) {
			if (table[index][i]) {
				if (i + 1 != table.length) {
					List<ArrayDeque<Integer>> local = wordBreakIIHelper(table, i + 1);
					if (local.size() > 0) {
						Iterator<ArrayDeque<Integer>> iterator = local.iterator();
						while (iterator.hasNext()) {
							ArrayDeque<Integer> q = iterator.next();
							q.addFirst(i);
							ret.add(q);
						}
					}
				} else {
					ArrayDeque<Integer> local = new ArrayDeque<Integer>();
					local.add(i);
					ret.add(local);
				}
			}
		}
		return ret;
	}

	List<ArrayDeque<Integer>> wordBreakIIHelperWipossibleWithoutTable(String s, Set<String> wordDict, int index, boolean [] possible) {
		List<ArrayDeque<Integer>> ret = new ArrayList<>();
		for (int i = index; i < s.length(); i++) {
			//I guess because of the big tables....
			if (wordDict.contains(s.substring(index, i + 1)) && possible[i]) {
				if (i + 1 != s.length()) {
					List<ArrayDeque<Integer>> local = wordBreakIIHelperWipossibleWithoutTable(s, wordDict, i + 1, possible);
					if (local.size() > 0) {
						Iterator<ArrayDeque<Integer>> iterator = local.iterator();
						while (iterator.hasNext()) {
							ArrayDeque<Integer> q = iterator.next();
							q.addFirst(i);
							ret.add(q);
						}
					} else {
						possible[i+1] = false;
					}
				} else {
					ArrayDeque<Integer> local = new ArrayDeque<Integer>();
					local.add(i);
					ret.add(local);
				}
			}
		}
		return ret;
	}

//	http://www.danielbit.com/blog/puzzle/leetcode/leetcode-word-break-ii
//	http://www.cnblogs.com/yuzhangcmu/p/4037299.html
//	https://lsuper.gitbooks.io/leetcode-note/content/leetcode/questions/word_break_ii.html
	public List<String> wordBreakII(String s, Set<String> wordDict) {
//		int length = s.length();
		/*
		boolean [][] table = new boolean [length][];
		for (int i = 0; i < length; i++)
			table[i] = new boolean [length];
		for (int i = 0; i < length; i++)
			for (int j = i; j < length; j++)
				if (wordDict.contains(s.substring(i, j + 1))) {
					table[i][j] =true;
				}
		*/
		/* TLE, too much redundancy calculations...
		wordBreakIIHelper(ret, tmp, table, 0, s);
		*/
		/*
		//below method still got TLE...
		List<ArrayDeque<Integer>> local = wordBreakIIHelper(table, 0);
		Iterator<ArrayDeque<Integer>> iterator = local.iterator();
		while (iterator.hasNext()) {
			ArrayDeque<Integer> q = iterator.next();
			int previous = q.removeFirst();
			StringBuilder sb = new StringBuilder();
			sb.append(s.substring(0, previous + 1));
			while (q.size() > 0) {
				int cur = q.removeFirst();
				sb.append(" " + s.substring(previous + 1, cur + 1));
				previous = cur;
			}
			ret.add(sb.toString());
		}
		return ret;
		*/

		/*
		boolean [] possible = new boolean [length];
		for (int i = 0; i < length; i++)
			possible[i] = true;
		List<ArrayDeque<Integer>> local = wordBreakIIHelperWipossible(table, 0, possible);
		Iterator<ArrayDeque<Integer>> iterator = local.iterator();
		while (iterator.hasNext()) {
			ArrayDeque<Integer> q = iterator.next();
			int previous = q.removeFirst();
			StringBuilder sb = new StringBuilder();
			sb.append(s.substring(0, previous + 1));
			while (q.size() > 0) {
				int cur = q.removeFirst();
				sb.append(" " + s.substring(previous + 1, cur + 1));
				previous = cur;
			}
			ret.add(sb.toString());
		}
		return ret;
		*/

		/* Still TLE...
		boolean [] possible = new boolean [length];
		for (int i = 0; i < length; i++)
			possible[i] = true;
		List<ArrayDeque<Integer>> local = wordBreakIIHelperWipossibleWithoutTable(s, wordDict, 0, possible);
		Iterator<ArrayDeque<Integer>> iterator = local.iterator();
		while (iterator.hasNext()) {
			ArrayDeque<Integer> q = iterator.next();
			int previous = q.removeFirst();
			StringBuilder sb = new StringBuilder();
			sb.append(s.substring(0, previous + 1));
			while (q.size() > 0) {
				int cur = q.removeFirst();
				sb.append(" " + s.substring(previous + 1, cur + 1));
				previous = cur;
			}
			ret.add(sb.toString());
		}
		return ret;
		*/

		//what to do??
		//use wordBreakI first go check and stop iterate through if no match is possible?
		/*
		//Still TLE
		List<List<String>> tables = new ArrayList<>();
		for (int i = 0; i <= length; i++)
			tables.add(new ArrayList<>());
		tables.get(0).add("");
		for (int i = 1; i <= length; i++) {
			for (int j = 0; j < i; j++) {
				String tmp = s.substring(j, i);
				if (wordDict.contains(tmp) && tables.get(j).size() > 0) {
					for (String ss : tables.get(j)) {
						if (ss.length() == 0)
							tables.get(i).add(tmp);
						else
							tables.get(i).add(ss + " "+ tmp);
					}
				}
			}
		}
		return tables.get(length);
		*/

		//think about the Uber phone interview question....
//		A small optimization is at for wordLen in wordLenList:, where we only need to loop through all the possible word length.
		/*
		HashSet<Integer> lengthSet1 = new HashSet<>();
		Iterator<String> wordIterator = wordDict.iterator();
		while (wordIterator.hasNext()) {
			int l = wordIterator.next().length();
			lengthSet1.add(l);
		}
		TreeSet<Integer> lengthSet2 = new TreeSet<>(lengthSet1);
		List<List<String>> tables = new ArrayList<>();
		for (int i = 0; i < length; i++)
			tables.add(new ArrayList<>());
		for (int i = 0; i < length; i++) {
			//search the string directly..
			if (lengthSet1.contains(i+1)) {
				String tmp = s.substring(0, i + 1);
				if (wordDict.contains(tmp)) {
					tables.get(i).add(tmp);
				}
			}

			//do the extend...
			//assume i id done, how to extend the result
			Iterator<Integer> l2 = lengthSet2.iterator();
			while (l2.hasNext()) {
				int ll = l2.next();
				if (ll + i + 1> length)
					break;
				//real length is ll + i + 1
				//begin at i + 1, end at i + ll,
				String tmp = s.substring(i + 1, i + ll + 1);
				if (wordDict.contains(tmp)) {
					for (String ss : tables.get(i + ll)) {
						tables.get(i).add(ss + " "+ tmp);
					}
				}
			}
		}
		return tables.get(length-1);
		*/

		if (s == null || s.length() == 0)
			return new ArrayList<String>();
		int length = s.length();

		boolean [] T = new boolean [length + 1];
		T[0] = true;
		//T[i] denotes s.substr(0, i+1) is reachable...
		for (int i = 1; i <= length; i++) {
			//fill T[i]
			for (int j = 1; j <= i && !T[i]; j++) {
				if (T[j-1] && wordDict.contains(s.substring(j-1, i)))
					T[i] = true;
			}

		}

		if (!T[length])
			return new ArrayList<String>();

		HashSet<Integer> lengthSet1 = new HashSet<>();
		Iterator<String> wordIterator = wordDict.iterator();
		while (wordIterator.hasNext()) {
			int l = wordIterator.next().length();
			lengthSet1.add(l);
		}
		TreeSet<Integer> lengthSet2 = new TreeSet<>(lengthSet1);
		List<List<String>> tables = new ArrayList<>();
		for (int i = 0; i < length; i++)
			tables.add(new ArrayList<>());
		for (int i = 0; i < length; i++) {
			//search the string directly..
			if (lengthSet1.contains(i+1)) {
				String tmp = s.substring(0, i + 1);
				if (wordDict.contains(tmp)) {
					tables.get(i).add(tmp);
				}
			}

			if (T[i+1]) {
				//do the extend...
				//assume i id done, how to extend the result
				Iterator<Integer> l2 = lengthSet2.iterator();
				while (l2.hasNext()) {
					int ll = l2.next();
					if (ll + i + 1> length)
						break;
					//real length is ll + i + 1
					//begin at i + 1, end at i + ll,
					String tmp = s.substring(i + 1, i + ll + 1);
					if (wordDict.contains(tmp)) {
						for (String ss : tables.get(i)) {
							tables.get(i+ll).add(ss + " "+ tmp);
						}
					}
				}
			}

		}
		return tables.get(length-1);
	}

//	https://leetcode.com/problems/candy/
//	135. Candy
	//I like the first one...
//	https://siddontang.gitbooks.io/leetcode-solution/content/greedy/candy.html
//	http://fisherlei.blogspot.com/2013/11/leetcode-candy-solution.html
	public int candy(int[] ratings) {
		if (ratings == null)
			return 0;
		int length = ratings.length;
		if (length <= 1)
			return length;
		int ret = 0;
		int [] left = new int[length];
		int [] right = new int [length];
		left[0] = 1;

		//You can drop if equals....
		int acc = 1;
		for (int i = 1; i < length; i++) {
			if (ratings[i] > ratings[i-1])
				++acc;
			else
				acc = 1;
			left[i] = acc;
		}
		acc = 1;
		for (int i = length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i+1])
				++acc;
			else
				acc = 1;
			right[i] = acc;
		}
		right[length-1]=1;
		for (int i = 0; i < length; i++)
			ret += Math.max(left[i], right[i]);
		return ret;
	}

//	134. Gas Station
//	https://leetcode.com/problems/gas-station/
//	http://bangbingsyb.blogspot.com/2014/11/leetcode-gas-station.html
//	http://fisherlei.blogspot.com/2013/11/leetcode-gas-station-solution.html
//	http://www.programcreek.com/2014/03/leetcode-gas-station-java/
	public int canCompleteCircuit(int[] gas, int[] cost) {
		//assume gas, cost are valid.
		//at least of length 1???
		int i = 0;
		int length = gas.length;
		while (i < length) {
			int sum = 0;
			int j = i;
//			sum += (gas[j] - cost[j]);
//			while (sum >= 0) {
//				j = (j + 1)%length;
//				if (i == j)
//					return i;
//				sum += (gas[j] - cost[j]);
//			}

			do {
				sum += (gas[j] - cost[j]);
			} while(sum >= 0 && (j = (j + 1)%length) != i);

			if (sum >= 0)
				return i;

			if (j < i) {
				return -1;
			} else {
				i = ++j;
			}
		}
		return -1;
	}


	static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	}
//	133. Clone Graph
//	https://leetcode.com/problems/clone-graph/
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return null;
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		Stack<UndirectedGraphNode> st = new Stack<>();
		//node in st, whose neighbor might be not updated
		st.push(node);
		while (!st.isEmpty()) {
			UndirectedGraphNode cur = st.pop();
			UndirectedGraphNode mapped;
			if (!map.containsKey(cur)) {
				map.put(cur, new UndirectedGraphNode(cur.label));
			}
			mapped = map.get(cur);

			//populate neighbors
			if (cur.neighbors.size() > 0) {
				mapped.neighbors = new ArrayList<>();
				Iterator<UndirectedGraphNode> iter = cur.neighbors.iterator();
				while (iter.hasNext()) {
					UndirectedGraphNode next = iter.next();
					if (!map.containsKey(next)) {
						map.put(next, new UndirectedGraphNode(next.label));
						st.push(next);
					}
					mapped.neighbors.add(map.get(next));
				}
			}
		}
		return map.get(node);
	}

	static class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
	}
//	https://leetcode.com/problems/copy-list-with-random-pointer/
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return null;

		//create new ones first
		RandomListNode cur = head;
		while (cur != null) {
			RandomListNode new_node = new RandomListNode(cur.label);
			new_node.next = cur.next;
			cur.next = new_node;
			cur = new_node.next;
		}

		//populate random
		cur = head;
		while (cur != null) {
			if (cur.random != null)
				cur.next.random = cur.random.next;
			cur = cur.next.next;
		}

		//split the table
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode tail = dummy;
		cur = head;
		while (cur != null) {
			tail.next = cur.next;
			cur.next = cur.next.next;
			cur = cur.next;
			tail = tail.next;
		}
		return dummy.next;
	}

//	131. Palindrome Partitioning
//	https://leetcode.com/problems/palindrome-partitioning/
	private List<String> partitionIConvertI(List<Integer> l, String s) {
		List<String> ret = new ArrayList<>();
		ret.add(s.substring(0, l.get(0)));
		for (int i = 1; i < l.size(); i++)
			ret.add(s.substring(l.get(i-1), l.get(i)));

		return ret;
	}

	private List<List<String>> partitionIConvert(List<List<Integer>> l, String s) {
		List<List<String>> ret = new ArrayList<>();
		for (int i = 0; i < l.size(); i++) {
			ret.add(partitionIConvertI(l.get(i), s));
		}
		return ret;
	}

	public List<List<String>> partition(String s) {
//		List<List<String>> ret = new ArrayList<>();

		int length = s.length();
		//fill Palindrome table
		boolean [][] T = new boolean [length][];
		for (int i = 0; i < length; i++)
			T[i] = new boolean [length];
		for (int i = 0; i < length; i++)
			T[i][i] = true;

		for (int i = length - 2; i >= 0; --i) {
			for (int j = i + 1; j < length; ++j) {
				T[i][j] = (j == i + 1) ? s.charAt(j) == s.charAt(i) : s.charAt(j) == s.charAt(i) && T[i+1][j-1];
			}
		}

		//This is not necessary as we can have all signle ch who satisfy this...
//		if (!T[0][length-1])
//			return ret;

		/*
		//How to reduce deep copy cost??? Copy index instead of String....
		List<List<List<String>>> T2 = new ArrayList<>();
		for (int i = 0; i < length; ++i)
			T2.add(new ArrayList<>());

		//init
		List<String> tmp = new ArrayList<>();
		tmp.add(Character.toString(s.charAt(0)));
		T2.get(0).add(tmp);
		for (int i = 0; i < length; i++) {
			List<List<String>> cur = T2.get(i);
			//start from i + 1, end at j
			for (int j = i + 1; j < length && cur.size() > 0; j++) {
				if (T[i+1][j]) {
					//update T2.get(j)
					//do we need deep copy?
					for (int k = 0; k < cur.size(); ++k) {

					}
				}
			}
		}
		*/

		List<List<List<Integer>>> T2 = new ArrayList<>();
		for (int i = 0; i < length; ++i)
			T2.add(new ArrayList<>());

		for (int i = 0; i < length; i++) {
			//You should not forget yourself.....
			if (T[0][i]) {
				List<Integer> tmp = new ArrayList<>();
				tmp.add(i+1);
				T2.get(i).add(tmp);
			}

			List<List<Integer>> cur = T2.get(i);
			if (cur.size() > 0) {
				for (int j = i + 1; j < length; j++) {
					if (T[i+1][j]) {
						//deep copy a list
						List<List<Integer>> copy = new ArrayList<>();
						for (int k = 0; k < cur.size(); k++) {
							copy.add(new ArrayList<>(cur.get(k)));
						}

						//update T2.get(j)
						//do we need deep copy?
						for (int k = 0; k < copy.size(); ++k) {
							copy.get(k).add(j + 1);
							T2.get(j).add(copy.get(k));
						}
					}
				}
			}

		}

		//converting index back to String

		return partitionIConvert(T2.get(length-1), s);
	}


//	132. Palindrome Partitioning II
//	https://leetcode.com/problems/palindrome-partitioning-ii/
//	https://leetcode.com/discuss/9476/solution-does-not-need-table-palindrome-right-uses-only-space
	public int minCut(String s) {
		if (s == null)
			return 0;
		int length = s.length();
		if (length <= 1)
			return 0;

		//fill Palindrome table
		boolean [][] T = new boolean [length][];
		for (int i = 0; i < length; i++)
			T[i] = new boolean [length];
		for (int i = 0; i < length; i++)
			T[i][i] = true;

		for (int i = length - 2; i >= 0; --i) {
			for (int j = i + 1; j < length; ++j) {
				T[i][j] = (j == i + 1) ? s.charAt(j) == s.charAt(i) : s.charAt(j) == s.charAt(i) && T[i+1][j-1];
			}
		}

		int [] mins = new int [length];
		Arrays.fill(mins, length-1);
		for (int i = 0; i < length; i++) {
			if (T[0][i])
				mins[i] = 0;
			for (int j = i+1; j < length; j++)
				if (T[i+1][j])
					mins[j] = Math.min(mins[j], 1 + mins[i]);
		}

		return mins[length-1];
	}

//	https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
	static class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;
		TreeLinkNode(int x) { val = x; }
	}
	void connect(TreeLinkNode left, TreeLinkNode right) {
		left.next = right;
	}
public void connect(TreeLinkNode root) {
	/*
	//what if we want only constant spaces??
		if (root == null)
		return;

	List<TreeLinkNode> q = new ArrayList<>();
	q.add(root);
	while (!q.isEmpty()) {
		List<TreeLinkNode> next = new ArrayList<>();
		for (int i = 0; i < q.size(); i++) {
			if (i + 1 != q.size())
				q.get(i).next = q.get(i+1);
			if (q.get(i).left != null)
				next.add(q.get(i).left);
			if (q.get(i).right != null)
				next.add(q.get(i).right);
		}
		q = next;
	}
	*/
	TreeLinkNode head = root;
	while (head != null) {
		TreeLinkNode pre = null;
		TreeLinkNode cur = head;
		TreeLinkNode next_head = null;
		while (cur != null) {
			if (cur.left != null) {
				if (pre != null) {
					connect(pre, cur.left);
				}
				pre = cur.left;
				if (next_head == null)
					next_head = cur.left;
			}
			if (cur.right != null) {
				if (pre != null) {
					connect(pre, cur.right);
				}
				pre = cur.right;
				if (next_head == null)
					next_head = cur.right;
			}
			cur = cur.next;
		}
		head = next_head;
	}
}






//	115. Distinct Subsequences
//	https://leetcode.com/problems/distinct-subsequences/
public int numDistinct(String s, String t) {
	int l1 = s.length(), l2 = t.length();
	if (l1 < l2)
		return 0;
	if (l2 == 0)
		return 1;
	/*
	//Let's move to one D
	int [][] T = new int [l1+1][];
	//Think more about the definition of your table
	for (int i = 0; i < T.length; i++) {
		T[i] = new int [l2+1];
		T[i][0] = 1;
	}

	for (int i = 1; i < T.length; i++) {
		for (int j = 1; j < T[i].length; j++) {
			T[i][j] = T[i-1][j] + (s.charAt(i-1) == t.charAt(j-1) ? T[i-1][j-1] : 0);
		}
	}
	return T[l1][l2];
	*/

	int [] T = new int [l2];
	for (int i = 0; i < l1; i++) {
		int diag = 1;
		for (int j = 0; j < l2; j++) {
			int upper = T[j];
			T[j] = upper + (s.charAt(i) == t.charAt(j) ? diag : 0);
			diag = upper;
		}
	}

	return T[l2-1];
}

//	33. Search in Rotated Sorted Array
//	https://leetcode.com/problems/search-in-rotated-sorted-array/
	public int searchInRotatedSortedArray(int[] nums, int target) {
		if (nums == null || nums.length <= 0)
			return -1;
		int length = nums.length;
		int l = 0, r = length - 1;
		while (l <= r) {
			int mid = r + (r - l) >> 1;
			if (nums[mid] == target)
				return mid;
			if (nums[l] < nums[r]) {
				int ret = Arrays.binarySearch(nums, l, r + 1, target);
				if (ret < 0)
					return -1;
				else
					return ret;
			} else {
				if (nums[l] <= nums[mid]) {
					if (target > nums[mid] || target <= nums[r])
						l = mid + 1;
					else
					//you can do real bst here...
						r = mid - 1;
				} else {
					if (nums[mid] > target|| target > nums[r]) {
						r = mid - 1;
					} else {
						l = mid + 1;
					}
				}
			}
		}
		return -1;
	}

//	https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
//	https://leetcode.com/submissions/detail/62766296/
//	http://bangbingsyb.blogspot.com/2014/11/leetcode-search-in-rotated-sorted-array.html
//	81. Search in Rotated Sorted Array II
	public int searchInRotatedSortedArrayWithDuplicates(int[] nums, int target) {
		if (nums == null || nums.length <= 0)
			return -1;
		int length = nums.length;
		int l = 0, r = length - 1;
		while (l <= r) {
			int mid = r + (r - l) >> 1;
			if (nums[mid] == target)
				return mid;
			if (nums[l] < nums[r]) {
				int ret = Arrays.binarySearch(nums, l, r + 1, target);
				if (ret < 0)
					return -1;
				else
					return ret;
			} else if (nums[l] == nums[r]) {
				if (target == nums[l])
					return l;
				else {
					++l;
					--r;
				}
//			} if (nums[l] > nums[r]){
			} else {
					if (nums[l] <= nums[mid]) {
						if (target > nums[mid] || target <= nums[r])
							l = mid + 1;
						else
							//you can do real bst here...
							r = mid - 1;
					} else {
						if (nums[mid] > target|| target > nums[r]) {
							r = mid - 1;
						} else {
							l = mid + 1;
						}
					}
				}
			}
		return -1;
	}


//	152. Maximum Product Subarray
//	https://leetcode.com/problems/maximum-product-subarray/
//	http://bangbingsyb.blogspot.com/2014/11/leetcode-maximum-product-subarray.html
	public int maxProduct(int[] nums) {
		int length = nums.length;
		if (length == 1)
			return nums[0];

		int ret = nums[0];
		/*
		//let's do it without array
		int [] positive = new int [length];//positive, abs is max till i, if not 0
		int [] negative = new int [length];//negative, abs is max till i, if not 0
		if (nums[0] > 0) {
			positive[0] = nums[0];
		} else if (nums[0] < 0) {
			negative[0] = nums[0];
		}

		for (int i = 1; i < length; i++) {
			if (nums[i] > 0) {
				if (positive[i-1] != 0)
					positive[i] = nums[i] * positive[i-1];
				else
					positive[i] = nums[i];
				negative[i] = negative[i-1]*nums[i];
			} else if (nums[i] < 0) {
				positive[i] = nums[i] * negative[i-1];
				if (positive[i-1] != 0)
					negative[i] = positive[i-1] * nums[i];
				else
					negative[i] = nums[i];
			}
			ret = Math.max(ret, positive[i]);
		}
		*/
		int positive = 0, negative = 0;
		if (nums[0] > 0) {
			positive = nums[0];
		} else if (nums[0] < 0) {
			negative = nums[0];
		}

		for (int i = 1; i < length; i++) {
			int next_positive = 0, next_negative = 0;
			if (nums[i] > 0) {
				next_positive = positive != 0 ? nums[i] * positive : nums[i];
				next_negative = negative * nums[i];
			} else if (nums[i] < 0) {
				next_positive = nums[i] * negative;
				next_negative = positive != 0 ? positive * nums[i] : nums[i];
			}
			ret = Math.max(ret, next_positive);
			positive = next_positive;
			negative = next_negative;
		}

		return ret;

	}

//	https://leetcode.com/problems/reverse-words-in-a-string/
//	151. Reverse Words in a String
	public String reverseWords(String s) {
		char [] array = s.toCharArray();
		//This is a wired question, skip it..
		return s;
	}

//	https://leetcode.com/problems/interleaving-string/
//	97. Interleaving String
	public boolean isInterleave(String s1, String s2, String s3) {
		//assume no null here
		int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
		if (l1 + l2 != l3)
			return false;
		if (l1 == 0 || l2 == 0)
			return l1 == 0 ? s2.equals(s3) : s1.equals(s3);

		/*
		boolean [][] T = new boolean[l1 + 1][];
		for (int i = 0; i < T.length; i++)
			T[i] = new boolean[l2 + 1];
		T[0][0] = true;
		//init row
		for (int i = 1; i < T[0].length; i++)
			if (s2.charAt(i-1) == s3.charAt(i-1))
				T[0][i] = true;
			else
				break;
		for (int i = 1; i < T.length; i++)
			if (s1.charAt(i-1) == s3.charAt(i-1))
				T[i][0] = true;
			else
				break;

		for (int i = 1; i < T.length; i++)
			for (int j = 1; j < T[i].length; j++) {
				T[i][j] = (s1.charAt(i-1) == s3.charAt(i+j-1) && T[i-1][j]) || (s2.charAt(j-1) == s3.charAt(i+j-1) && T[i][j-1]);
			}
		return T[l1][l2];
		*/

		//let us do it in one D
		boolean [] T = new boolean[l2 + 1];
		T[0] = true;
		for (int i = 1; i < T.length; i++)
			if (s2.charAt(i-1) == s3.charAt(i-1))
				T[i] = true;
			else
				break;
		for (int i = 1; i <= l1; i++) {
			T[0] = T[0] && s1.charAt(i-1) == s3.charAt(i-1);
			boolean left = T[0];
			for (int j = 1; j <= l2; j++) {
				left =T[j] = (s1.charAt(i-1) == s3.charAt(i+j-1) && T[j]) || (s2.charAt(j-1) == s3.charAt(i+j-1) && left);
			}
		}
		return T[l2];
	}

//	https://leetcode.com/problems/insertion-sort-list/
//	147. Insertion Sort List
	public ListNode insertionSortList(ListNode head) {
		ListNode dummy = new ListNode(0);
		while (head != null) {
			ListNode cur = head;
			head = head.next;

			/*
			ListNode pre = dummy, tail = dummy.next;
			while (tail != null && tail.val <= cur.val) {
				pre = tail;
				tail = tail.next;
			}

			pre.next = cur;
			cur.next = tail;
			*/

			//try with one variable
			ListNode pre = dummy;
			while (pre.next != null && pre.next.val <= cur.val) {
				pre = pre.next;
			}
			cur.next = pre.next;
			pre.next = cur;
		}

		return dummy.next;
	}

//	https://leetcode.com/problems/binary-tree-preorder-traversal/
//	144. Binary Tree Preorder Traversal
//	https://leetcode.com/submissions/detail/8269161/
//	https://leetcode.com/submissions/detail/14543774/
//	http://fisherlei.blogspot.com/2013/11/leetcode-binary-tree-preorder-traversal.html
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> ret = new ArrayList<>();
		Stack<TreeNode> st = new Stack<>();
		if (root == null)
			return ret;

		st.push(root);
		while (!st.isEmpty()) {
			TreeNode cur = st.pop();
			while (cur != null) {
				ret.add(cur.val);
				if (cur.right != null)
					st.push(cur.right);
				cur = cur.left;
			}
		}

		return ret;
	}

//	https://leetcode.com/problems/binary-tree-postorder-traversal/
//	http://www.programcreek.com/2012/12/leetcode-solution-of-iterative-binary-tree-postorder-traversal-in-java/
//	145. Binary Tree Postorder Traversal
public List<Integer> postorderTraversal(TreeNode root) {
	/*
	List<Integer> ret = new ArrayList<>();
	Stack<TreeNode> st = new Stack<>();
	if (root == null)
		return ret;
	st.push(root);
	while (!st.isEmpty()) {
		TreeNode cur = st.pop();
		ret.add(cur.val);
		if (cur.left != null)
			st.push(cur.left);
		if (cur.right != null)
			st.push(cur.right);
	}
	Collections.reverse(ret);
	return ret;
	*/
//	https://leetcode.com/submissions/detail/62860809/
	List<Integer> ret = new ArrayList<>();
	Stack<TreeNode> st = new Stack<>();
	if (root == null)
		return ret;
	TreeNode pre = null;
	//naive way

	while (root != null || !st.isEmpty()) {
		if (root == null) {
			root = st.pop();
				if (pre == root.left) {
					if (root.right != null) {
						pre = root;
						st.push(root);
						root = root.right;
					} else {
						ret.add(root.val);
						pre = root;
						root = null;
					}
				} else if (pre == root.right) {
					ret.add(root.val);
					pre = root;
					root = null;
				}
		} else {
			while (root.left != null) {
				st.push(root);
				root = root.left;
			}

			//your root left is alway null here...Which means you can switch to root right
			if (root.right != null) {
				pre = root;
				st.push(root);
				root = root.right;
			} else {
				//visit root
				ret.add(root.val);
				pre = root;
				root = null;
			}
		}
	}

	return ret;
}

//	https://leetcode.com/problems/evaluate-reverse-polish-notation/
//	150. Evaluate Reverse Polish Notation
	private void evalRPN(Stack<Integer> data, String op) {
		int b = data.pop(), a = data.pop();
		switch (op) {
			case "+":
				data.push(a + b);
				break;
			case "-":
				data.push(a - b);
				break;
			case "*":
				data.push(a * b);
				break;
			case "/":
				data.push(a / b);
				break;
		}
	}
	public int evalRPN(String[] tokens) {
		Stack<Integer> data = new Stack<>();
		for (int i = 0; i < tokens.length; i++) {
			try {
				data.push(Integer.parseInt(tokens[i]));
			} catch (NumberFormatException e) {
				evalRPN(data, tokens[i]);
			}
		}

		return data.peek();
	}

//	https://leetcode.com/problems/max-points-on-a-line/
//	149. Max Points on a Line
	static class Point {
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { x = a; y = b; }
	}

	public int maxPoints(Point[] points) {
		if (points == null)
			return 0;
		int length = points.length;
		if (length <= 2)
			return length;
		int ret = 2;
		for (int i = 0; i < length; i++) {
			int duplicate = 1, horizional = 0, vertical = 0, cur_max = 0;
			HashMap<Double, Integer> map = new HashMap<>();
			for (int j = i + 1; j < length; j++) {
				if (points[i].x == points[j].x) {
					if (points[i].y == points[j].y)
						++duplicate;
					else
						++vertical;
				} else {
					if (points[i].y == points[j].y)
						++horizional;
					else {
						double slew = (double)(points[j].y - points[i].y) / (points[j].x - points[i].x);
						if (map.containsKey(slew))
							map.put(slew, map.get(slew) + 1);
						else
							map.put(slew, 1);
						cur_max = Math.max(cur_max, map.get(slew));
					}
				}
			}

			ret = Math.max(ret, duplicate + Math.max(horizional, Math.max(vertical, cur_max)));
		}
		return ret;
	}

//	https://leetcode.com/problems/word-ladder/
//	127. Word Ladder
	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		if (beginWord == endWord)
			return 0;
		//BFS
		Set<String> visited = new HashSet<>();
		ArrayDeque<String> queue = new ArrayDeque<>();
		queue.add(beginWord);
		visited.add(beginWord);
		//This is ugly. Both begin word and end word don't have to be in the dicts...
		int maxVisitedSize = wordList.contains(beginWord) ? wordList.size() : wordList.size() + 1;

		int level = 1;
		ArrayDeque<String> next = new ArrayDeque<>();

		while(!queue.isEmpty() && visited.size() <= maxVisitedSize) {
			StringBuilder cur = new StringBuilder(queue.removeFirst());
			//expand cur
			for (int i = 0; i < cur.length(); i++) {
				char tmp = cur.charAt(i);
				for (char c = 'a'; c <= 'z'; ++c) {
					if (tmp == c)
						continue;
					cur.setCharAt(i, c);
					String tmps = cur.toString();
					if (tmps.equals(endWord))
						return level + 1;
					else if (wordList.contains(tmps) && !visited.contains(tmps)) {
						visited.add(tmps);
						next.addLast(tmps);
					}
				}
				cur.setCharAt(i, tmp);
			}

			if (queue.isEmpty()) {
				++level;
				queue = next;
				next = new ArrayDeque<>();
			}
		}

		return 0;
	}

//	126. Word Ladder II
//	https://leetcode.com/problems/word-ladder-ii/
	static class FindLaddersNode {
		private int level;//level of current expanding node
		private final HashSet<String> list;//parent who can reach this node?? Why use a set instead of List?
		FindLaddersNode(int level) {
			this.level = level;
			list = new HashSet<>();
		}
	}

	private List<List<String>> constructFindLadders(String key, HashMap<String, FindLaddersNode> mapping) {
		List<List<String>> ret = new ArrayList<>();
		FindLaddersNode node = mapping.get(key);
		if (node.level == 0) {
			List<String> tmp = new ArrayList<>();
			tmp.add(key);
			ret.add(tmp);
		} else {
			Iterator<String> iterator = node.list.iterator();
			while (iterator.hasNext()) {
				String tmp = iterator.next();
				List<List<String>> sub = constructFindLadders(tmp, mapping);
				//You need copy list here
				for (int i = 0; i < sub.size(); i++) {
					List<String> copy = new ArrayList<>(sub.get(i));
					copy.add(key);
					ret.add(copy);
				}
			}
		}
		return ret;
	}
	public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
		List<List<String>> ret = new ArrayList<>();
		if (beginWord == endWord)
			return ret;

		//BFS
		HashMap<String, FindLaddersNode> mapping = new HashMap<>();
		ArrayDeque<String> queue = new ArrayDeque<>();
		queue.add(beginWord);
		mapping.put(beginWord, new FindLaddersNode(0));

		int level = 1;
		int max_level = Integer.MAX_VALUE;
		ArrayDeque<String> next = new ArrayDeque<>();

		while(!queue.isEmpty() && level <= max_level) {
			String first = queue.removeFirst();
			StringBuilder cur = new StringBuilder(first);
			//expand cur
			for (int i = 0; i < cur.length(); i++) {
				char tmp = cur.charAt(i);
				for (char c = 'a'; c <= 'z'; ++c) {
					if (tmp == c)
						continue;
					cur.setCharAt(i, c);
					String tmps = cur.toString();
					if (tmps.equals(endWord)) {
						if (max_level == Integer.MAX_VALUE) {
							max_level = level;
							mapping.put(tmps, new FindLaddersNode(level));
						}
						mapping.get(tmps).list.add(first);
					} else if (wordList.contains(tmps)) {
						if (!mapping.containsKey(tmps)) {
							mapping.put(tmps, new FindLaddersNode(level));
							mapping.get(tmps).level = level;
							mapping.get(tmps).list.add(first);
							next.addLast(tmps);
						} else if (mapping.get(tmps).level == level) {
							mapping.get(tmps).list.add(first);
						}
					}
				}
				cur.setCharAt(i, tmp);
			}

			if (queue.isEmpty()) {
				++level;
				queue = next;
				next = new ArrayDeque<>();
			}
		}

		if (!mapping.containsKey(endWord))
			return ret;
		else
			return constructFindLadders(endWord,mapping);
	}

//	156	Binary Tree Upside Down
//	Binary Tree Upside Down
//	Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
//	For example:
//	Given a binary tree {1,2,3,4,5},
//			1
//			/ \
//			2   3
//			/ \
//			4   5
//			return the root of the binary tree [4,5,2,#,#,3,1].
//			4
//			/ \
//			5   2
//			/ \
//			3   1
//	http://blog.csdn.net/whuwangyi/article/details/43186045
	TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null || root.left == null)
			return root;

		TreeNode last_root = null, last_right = null;
		while (root != null) {
			TreeNode left = root.left, right = root.right;
			root.left = last_right;
			root.right = last_root;
			last_root = root;

			root = left;
			last_right = right;
		}
		return last_root;
	}


//	157	Read N Characters Given Read4
//The API: int read4(char *buf) reads 4 characters at a time from a file.
//	The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
//	By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
//
//	Note:
//	The read function will only be called once for each test case.
	//Don't worry about the read4 API, it will proceed by itself...
	//Understands like this, data will be written into buf and actaul length will be returned by read4.
	//So actual file is some where else....
int read4(char[]buf) {
	return 0;
}
public int read(char[] buf, int n) {
	//we assume buf has enough space for write
	char [] buffer = new char[4];
	int i = 0;
	int curReads;
	while ((curReads = read4(buffer)) != 0 && n != 0) {
		if (n >= curReads) {
			System.arraycopy(buffer, 0, buf, i, curReads);
			i += curReads;
			n -= curReads;
		} else {
			System.arraycopy(buffer, 0, buf, i, n);
			i += n;
			n = 0;
		}

	}
	return i;
//	public int read(char[] buf, int n) {
//		char[] buffer = new char[4];
//		int readBytes = 0;
//		boolean eof = false;
//		while (!eof && readBytes < n) {
//			int sz = read4(buffer);
//			if (sz < 4) eof = true;
//			int bytes = Math.min(n - readBytes, sz);
//			System.arraycopy(buffer /*src*/, 0 /*srcPos*/, buf /*dest*/, readBytes /*destPos*/, bytes /*length*/);
//			readBytes += bytes;
//		}
//		return readBytes;
//	}
}

//	https://leetcode.com/problems/sum-root-to-leaf-numbers/
//	129. Sum Root to Leaf Numbers
	private int sumNumbers(TreeNode root, int acc) {
		acc = 10 * acc + root.val;
		if (root.left == null) {
			if (root.right == null)
				return acc;
			else
				return sumNumbers(root.right, acc);
		} else {
			if (root.right == null)
				return sumNumbers(root.left, acc);
			else
				return sumNumbers(root.left, acc) + sumNumbers(root.right, acc);
		}
	}
	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;
		return sumNumbers(root, 0);
	}

	static class BoardNode{
		int x;
		int y;
		BoardNode(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
//	130. Surrounded Regions
//	https://leetcode.com/problems/surrounded-regions/
	private void dfsSolve(char[][] board, int i, int j) {
		/*
//		System.out.println(i + "\t" + j + board[i][j]);
		board[i][j] = 'C';

//		System.out.println(i + "\t" + j + board[i][j]);

		//expand four directions
		//up
		if (i > 0 && board[i-1][j] == 'O')
			dfsSolve(board, i-1,j);
		//down
		if (i + 1 < board.length && board[i+1][j] == 'O')
			dfsSolve(board, i+1,j);

		//left
		if (j > 0 && board[i][j-1] == 'O')
			dfsSolve(board, i,j-1);

		//right
		if (j + 1 < board[0].length && board[i][j+1] == 'O')
			dfsSolve(board, i,j+1);
		*/

		//keep seeing StackOverflowError?? Why? because you are using DFS instead BFS
		/*
		ArrayDeque<BoardNode> q = new ArrayDeque<>();
		q.addLast(new BoardNode(i,j));
		while (!q.isEmpty()) {
			BoardNode cur = q.removeFirst();
			i = cur.x;
			j = cur.y;
			board[i][j] = 'C';
			if (i > 0 && board[i-1][j] == 'O')
				q.addLast(new BoardNode(i-1,j));
			//down
			if (i + 1 < board.length && board[i+1][j] == 'O')
				q.addLast(new BoardNode(i+1,j));

			//left
			if (j > 0 && board[i][j-1] == 'O')
				q.addLast(new BoardNode(i,j-1));

			//right
			if (j + 1 < board[0].length && board[i][j+1] == 'O')
				q.addLast(new BoardNode(i,j+1));
		}
		*/
		//why above code is not correct, because it might add the same node so many times....
		ArrayDeque<BoardNode> q = new ArrayDeque<>();
		board[i][j] = 'C';
		q.addLast(new BoardNode(i,j));
		while (!q.isEmpty()) {
			BoardNode cur = q.removeFirst();
			i = cur.x;
			j = cur.y;
			if (i > 0 && board[i-1][j] == 'O') {
				board[i-1][j] = 'C';
				q.addLast(new BoardNode(i-1,j));
			}

			//down
			if (i + 1 < board.length && board[i+1][j] == 'O') {
				board[i+1][j] = 'C';
				q.addLast(new BoardNode(i+1,j));
			}


			//left
			if (j > 0 && board[i][j-1] == 'O') {
				board[i][j-1] = 'C';
				q.addLast(new BoardNode(i,j-1));
			}


			//right
			if (j + 1 < board[0].length && board[i][j+1] == 'O') {
				board[i][j+1] = 'C';
				q.addLast(new BoardNode(i,j+1));
			}

		}
	}
	public void solve(char[][] board) {
		if (board == null || board.length<=2 || board[0].length<=2)
			return;
		//search 'O' from four boundary...
		for (int i = 0; i < board.length; ++i) {
			if (board[i][0] == 'O')
				dfsSolve(board,i,0);
			if (board[i][board[0].length-1] == 'O')
				dfsSolve(board,i,board[0].length-1);
		}
		for (int i = 0; i < board[0].length; ++i) {
			if (board[0][i] == 'O')
				dfsSolve(board,0,i);
			if (board[board.length-1][i] == 'O')
				dfsSolve(board,board.length-1,i);
		}

		for (int i = 0; i < board.length; ++i)
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == 'C')
					board[i][j] = 'O';
				else if (board[i][j] == 'O')
					board[i][j] = 'X';
	}

	static class  MaxPathSumNode {
		int maxleft;
		int maxright;
		int maxpath;
		MaxPathSumNode(int val) {
			maxleft = val;
			maxright = val;
			maxpath = val;
		}
	}
	public MaxPathSumNode maxPathSumHelper(TreeNode root) {
		MaxPathSumNode ret = new MaxPathSumNode(root.val);
		MaxPathSumNode left = new MaxPathSumNode(Integer.MIN_VALUE);
		if (root.left != null) {
			left = maxPathSumHelper(root.left);
			ret.maxleft = Math.max(0, Math.max(left.maxleft, left.maxright)) + root.val;

		}

		MaxPathSumNode right = new MaxPathSumNode(Integer.MIN_VALUE);
		if (root.right != null) {
			right = maxPathSumHelper(root.right);
			ret.maxright = Math.max(0, Math.max(right.maxleft, right.maxright)) + root.val;
		}
		ret.maxpath = Math.max(left.maxpath, Math.max(right.maxpath, root.val + Math.max(0, Math.max(left.maxleft, left.maxright)) + Math.max(0, Math.max(right.maxleft, right.maxright))));
		return ret;
	}

	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;

		return maxPathSumHelper(root).maxpath;
	}

//	https://leetcode.com/problems/reorder-list/
//	143. Reorder List
	public void reorderList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null)
			return;
		ListNode fast = head, slow = head;
		while (fast != null && (fast = fast.next) != null && (fast = fast.next) != null)
			slow = slow.next;

		ListNode h2 = slow.next;
		slow.next = null;
		h2 = reverseList(h2);
		//merge list
		while (h2 != null) {
			ListNode next_head = head.next;
			ListNode next_h2 = h2.next;

			head.next = h2;
			h2.next = next_head;
			head = next_head;
			h2 = next_h2;
		}
	}

//	https://leetcode.com/problems/pascals-triangle/
//	118. Pascal's Triangle
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> ret = new ArrayList<>();
		if (numRows <= 0)
			return ret;

		//add a dummy one
		ret.add(new ArrayList<>());
		while (numRows > 0) {
				List<Integer> tmp = new ArrayList<>(ret.get(ret.size()-1));
				for (int j = tmp.size() - 1; j > 0; j--) {
					tmp.set(j, tmp.get(j) + tmp.get(j-1));
				}
				tmp.add(1);
				ret.add(tmp);
			--numRows;
		}
		//remove the dummy one
		ret.remove(0);
		return ret;
	}

//	119. Pascal's Triangle II
//	https://leetcode.com/problems/pascals-triangle-ii/
	public List<Integer> getRow(int rowIndex) {
		//assume rowIndex >= 1
		List<Integer> ret = new ArrayList<>(rowIndex + 1);
		int last = 0;
		while (last <= rowIndex) {
			ret.add(last, 1);
			for (int j = last - 1; j > 0; j--) {
				ret.set(j, ret.get(j) + ret.get(j-1));
			}
			++last;
		}

		return ret;
	}

	private void minimumTotal(List<List<Integer>> triangle, int [] mins, int acc, int row, int col) {
		acc += triangle.get(row).get(col);
		if (row + 1 == triangle.size()) {
			mins[0] = Math.min(mins[0], acc);
		} else {
			minimumTotal(triangle, mins, acc, row+1, col);
			minimumTotal(triangle, mins, acc, row+1, col+1);
		}
	}

//	https://leetcode.com/problems/triangle/
//	120. Triangle
	public int minimumTotal(List<List<Integer>> triangle) {
		/*
		//TLE below method, 2^n complexity
		int [] ret = new int [1];
		ret[0] = Integer.MAX_VALUE;
		minimumTotal(triangle, ret, 0, 0, 0);
		return ret[0];
		*/

		int r = triangle.size() - 2;
		while (r >= 0) {
			for (int j = 0; j < triangle.get(r).size(); j++)
				triangle.get(r).set(j, Math.min(triangle.get(r+1).get(j), triangle.get(r+1).get(j+1)) + triangle.get(r).get(j));
			--r;
		}
		return triangle.get(0).get(0);
	}

//	https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
//	114. Flatten Binary Tree to Linked List
	public void flatten(TreeNode root) {
		while (root != null) {
			if (root.left != null) {
				TreeNode left;
				for (left = root.left; left.right != null; left = left.right);
				left.right = root.right;
				root.right=root.left;
				root.left = null;
			}
			root = root.right;
		}
	}

//	112. Path Sum
//	https://leetcode.com/problems/path-sum/
	private boolean hasPathSumHelper(TreeNode root, int sum) {
		sum -= root.val;
		if (root.left != null && root.right != null)
			return hasPathSumHelper(root.left, sum) || hasPathSumHelper(root.right, sum);
		else if (root.left != null)
			return hasPathSumHelper(root.left, sum);
		else if (root.right != null)
			return hasPathSumHelper(root.right, sum);
		else
			return sum == 0;
	}
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		return hasPathSumHelper(root, sum);
	}

//	113. Path Sum II
//	https://leetcode.com/problems/path-sum-ii/
	void pathSumHelper(TreeNode root, int sum, List<List<Integer>> l, List<Integer> path) {
		sum -= root.val;
		path.add(root.val);

		if (root.left == null && root.right == null) {
			if (sum == 0) {
				l.add(new ArrayList<>(path));
			}
		} else {
			if (root.left != null)
				pathSumHelper(root.left, sum, l, path);
			if (root.right != null)
				pathSumHelper(root.right, sum, l, path);
		}
		path.remove(path.size()-1);
	}
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> ret = new ArrayList<>();
		if (root == null)
			return ret;
		pathSumHelper(root, sum, ret, new ArrayList<>());
		return ret;
	}

//	87. Scramble String
//	https://leetcode.com/problems/scramble-string/
	//Recursion is also OK, have some sanity to avoid uncessary tree traversal.
//	http://blog.unieagle.net/2012/10/23/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Ascramble-string%EF%BC%8C%E4%B8%89%E7%BB%B4%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/
	public boolean isScramble(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 == null || s2 == null)
			return false;
		int l1 = s1.length(), l2 = s2.length();
		if (l1 != l2)
			return false;

		//assume s1 and s2 non-empty? l1 >= 1, l2 >= 1
		//l1 == l2 all below...
		//T[i][j][l] denotes s1 [i, .. i + l] with s2 [j,..j+l]
		boolean [][][] T = new boolean[l1][][];
		for (int i = 0; i < T.length; i++) {
			T[i] = new boolean[l1][];
			for (int j = 0; j < T[i].length; j++)
				T[i][j] = new boolean[l1];
		}

		//init
		for (int i = 0; i < l1; i++)
			for (int j = 0; j < l1; j++)
				if (s1.charAt(i) == s2.charAt(j))
					T[i][j][0] = true;

		for (int l = 1; l < l1; l++) {
			for (int i = 0; i + l < l1; i++) {
				for (int j = 0; j + l < l1; j++) {
					for (int k = 0; k < l && !T[i][j][l]; k++) {
						T[i][j][l] = (T[i][j][k] && T[i+k+1][j+k+1][l-k-1]) ||
								(T[i][j+l-k][k] && T[i+k+1][j][l-k-1]);
					}
				}
			}
		}
		return T[0][0][l1-1];
	}

//	https://leetcode.com/problems/edit-distance/
//	72. Edit Distance
	public int minDistance(String word1, String word2) {
		if (word1 == null || word2 == null)
			return 0;
		int l1 = word1.length(), l2 = word2.length();
		if (l1 == 0 || l2 == 0)
			return l1 == 0 ? l2 : l1;

		int [][] T = new int[l1+1][];
		for (int i = 0; i < T.length; i++)
			T[i] = new int [l2+1];

		//init row and col
		for (int i = 0; i < T[0].length; i++)
			T[0][i] = i;
		for (int i = 1; i < T.length; i++)
			T[i][0] = i;

		for (int i = 1; i < T.length; i++)
			for (int j = 1; j < T[i].length; j++)
				T[i][j] = Math.min((word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1) + T[i-1][j-1], 1 + Math.min(T[i][j-1], T[i-1][j]));
		return T[l1][l2];
	}

//	111. Minimum Depth of Binary Tree
//	https://leetcode.com/problems/minimum-depth-of-binary-tree/
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left != null && root.right != null)
			return 1 + Math.min(minDepth(root.left), minDepth(root.right));
		else if (root.left == null && root.right == null)
			return 1;
		else if (root.left != null)
			return 1 + minDepth(root.left);
		else
			return 1 + minDepth(root.right);
	}

//	https://leetcode.com/problems/balanced-binary-tree/
//	110. Balanced Binary Tree
	static class IsBalancedNode {
		int h;
		boolean isBalanced;
		IsBalancedNode(){
			isBalanced = true;
			h = 0;
		}
	}
	public IsBalancedNode isBalancedHelper(TreeNode root) {
		IsBalancedNode ret = new IsBalancedNode();
		if (root == null)
			return ret;

		IsBalancedNode left = isBalancedHelper(root.left);
		IsBalancedNode right = isBalancedHelper(root.right);
		if (!left.isBalanced || !right.isBalanced || Math.abs(left.h-right.h) > 1) {
			ret.isBalanced = false;
			return ret;
		}
		ret.isBalanced = true;
		ret.h = 1 + Math.max(left.h, right.h);

		return ret;
	}
	public boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;
		return isBalancedHelper(root).isBalanced;
	}

//	https://leetcode.com/problems/binary-tree-level-order-traversal/
//	102. Binary Tree Level Order Traversal
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ret = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();

		if (root != null)
			q.add(root);

		Queue<TreeNode> next = new LinkedList<>();
		List<Integer> tmp = new ArrayList<>();
		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			tmp.add(cur.val);
			if (cur.left != null)
				next.offer(cur.left);
			if (cur.right != null)
				next.offer(cur.right);

			if (q.isEmpty()) {
				ret.add(tmp);
				tmp = new ArrayList<>();
				q = next;
				next = new LinkedList<>();
			}

		}
		return ret;
	}

//	https://leetcode.com/problems/symmetric-tree/
//	101. Symmetric Tree

	boolean isSymmetricPair(TreeNode left, TreeNode right) {
		if (left == null && right == null)
			return true;
		if (left == null || right == null)
			return false;
		if (left.val != right.val)
			return false;
		return isSymmetricPair(left.left, right.right) && isSymmetricPair(left.right, right.left);
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return isSymmetricPair(root.left,root.right);
	}

	static class IsValidBSTNode {
		boolean isValidBST;
		TreeNode minNode;
		TreeNode maxNode;
		IsValidBSTNode() {
			isValidBST = true;
			minNode = null;
			maxNode = null;
		}
	}
	public IsValidBSTNode isValidBSTHelper(TreeNode root) {
		IsValidBSTNode node = new IsValidBSTNode();
		if (root == null) {
			return node;
		}
		IsValidBSTNode left = isValidBSTHelper(root.left);
		if (left.isValidBST && (left.maxNode == null || left.maxNode.val < root.val)) {
			IsValidBSTNode right = isValidBSTHelper(root.right);
			if (right.isValidBST && (right.minNode == null || right.minNode.val > root.val)) {
				node.minNode = (left.minNode == null) ? root : left.minNode;
				node.maxNode = (right.maxNode == null) ? root : right.maxNode;
			} else {
				node.isValidBST = false;
			}
		} else {
			node.isValidBST = false;
		}
		return node;
	}

	public boolean isValidBST(TreeNode root) {
//		if (root == null)
//			return true;
		//inorder traversal
//		TreeNode pre = null;
//		return false;
		return isValidBSTHelper(root).isValidBST;
	}

//	44. Wildcard Matching
//	https://leetcode.com/problems/wildcard-matching/
	public boolean isMatchRecursive(String s, int i, String p, int j) {
		if (i == s.length()) {
			//s come to an end
			while (j < p.length() && p.charAt(j) == '*')
				++j;
			return j == p.length();
		}
		if (j == p.length())
			return i == s.length();
		//both i and j are not ended.
		if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))
			return isMatchRecursive(s, i + 1, p, j + 1);
		else if (p.charAt(j) == '*') {
			//represent any char in s
			while (i <= s.length()) {
				if (isMatchRecursive(s, i, p, j+1))
					return true;
				++i;
			}
			return false;
		} else
			return false;
	}

//	https://simpleandstupid.com/2014/10/26/wildcard-matching-leetcode-%E8%A7%A3%E9%A2%98%E7%AC%94%E8%AE%B0/
	public boolean isMatchGreedy(String s, String p) {
		int i = 0;
		int j = 0;
		int star = -1;
		int mark = -1;
		while (i < s.length()) {
			if (j < p.length()
					&& (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				++i;
				++j;
			} else if (j < p.length() && p.charAt(j) == '*') {
				star = j;
				j++;
				mark = i;
				//这一步是关键，匹配s中当前字符与p中‘＊’后面的字符，如果匹配，则在第一个if中处理，如果不匹配，则继续比较s中的下一个字符。
			} else if (star != -1) {
				j = star + 1;
				i = ++mark;
			} else {
				return false;
			}
		}
		//最后在此处处理多余的‘＊’，因为多个‘＊’和一个‘＊’意义相同。
		while (j < p.length() && p.charAt(j) == '*') {
			++j;
		}
		return j == p.length();
	}

	//All kinds of crazy optimizations are applied below...
//	https://simpleandstupid.com/2014/10/26/wildcard-matching-leetcode-%E8%A7%A3%E9%A2%98%E7%AC%94%E8%AE%B0/

	//This problem can also be solved by Greedy.
//	https://simpleandstupid.com/2014/10/26/wildcard-matching-leetcode-%E8%A7%A3%E9%A2%98%E7%AC%94%E8%AE%B0/
	public boolean isMatch(String s, String p) {
		//recursion is TLE
//		return isMatchRecursive(s,0,p,0);

		if (s == null && p == null)
			return true;
		if (s  == null || p == null)
			return false;
		//compact String
		int i1 = 0, i2 = s.length()-1;
		int j1 = 0, j2 = p.length()-1;
		//skip both ends first....
		while (i1 <= i2 && j1 <= j2 && (s.charAt(i1) == p.charAt(j1) || p.charAt(j1) == '?')) {
			++i1;
			++j1;
		}

		while (i1 <= i2 && j1 <= j2 && (s.charAt(i2) == p.charAt(j2) || p.charAt(j2) == '?')) {
			--i2;
			--j2;
		}

		//if p ends, s have to end
		if (j1 > j2)
			return i1 > i2;
		if (i1 > i2) {
			//p can only contain '*'
			while (j1 <= j2 && p.charAt(j1) == '*')
				++j1;
			return j1 > j2;
		}

		//p have to both starts with '*'
		if (p.charAt(j1) != '*' || p.charAt(j2) != '*')
			return false;

		//compare s from i1 to i2, p from j1 to j2, both ends are included.
		//compaction for p again, skip consective '*'
		StringBuilder sb = new StringBuilder();
		sb.append(p.charAt(j1++));
		int unStart = 0;
		while (j1 <= j2) {
//			if (p.charAt(j1) != '*' || p.charAt(j1-1) != '*')
//				sb.append(p.charAt(j1));
//			++j1;
			if (p.charAt(j1) != '*') {
				sb.append(p.charAt(j1));
				++unStart;
			} else if (p.charAt(j1-1) != '*') {
				sb.append(p.charAt(j1));
			}
			++j1;
		}
		if (unStart > (i2 - i1 + 1))
			return false;
		//compare between s[i1...i2] and sb

//		System.out.println(s.substring(i1, i2+1));
//		System.out.println(sb.toString());
		//Still TLE below
//		return isMatchRecursive(s.substring(i1, i2+1), 0, sb.toString(), 0);

		//Let's try DP
		s = s.substring(i1, i2+1);
		p = sb.toString();
		boolean [][] T = new boolean [p.length()][];
		for (int i = 0; i < T.length; i++)
			T[i] = new boolean[s.length()];
		//the first row must be all true due to the first is '*'
		for (int i = 0; i < T[0].length; i++)
			T[0][i] = true;
		//because we have trucated consecutive '*', only the first row can have diag be true.
		for (int i = 1; i < T.length; i++) {
//			System.out.println("Row" + i);
			boolean diag = (i == 1) ? true : false;
			if (p.charAt(i) == '*') {
				//find first true from last row
				//we know 1 row won't have true in the first ele
				int j;
				for (j = 0; j < T[i-1].length && !T[i-1][j]; j++);
				while (j < T[i].length) {
					T[i][j] = true;
					++j;
				}
			} else {
				for (int j = 0; j < T[i].length; j++) {
					if (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j))
						T[i][j] = diag;
					diag = T[i-1][j];
				}
			}
		}
		return T[p.length()-1][s.length()-1];
	}

//	https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
//	108. Convert Sorted Array to Binary Search Tree
	TreeNode sortedArrayToBST(int[] nums, int start, int end) {
		if (start >= end)
			return null;
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = sortedArrayToBST(nums, start, mid);
		root.right = sortedArrayToBST(nums, mid + 1, end);
		return root;
	}
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;
		return sortedArrayToBST(nums, 0, nums.length);
	}

	//https://leetcode.com/problems/unique-binary-search-trees/
//	96. Unique Binary Search Trees
	//C(2n,n) / (n + 1)
	public int numTrees(int n) {
		/*
		//below has overflow issue
		if (n == 0)
			return 0;
		int [] T = new int [n + 1];
		Arrays.fill(T, 1);
		int lastRow = n << 1;
		for (int i = 2; i <= lastRow; i++) {
			for (int c = Math.min(i - 1, n); c > 0; c--)
				T[c] += T[c-1];
		}
		return T[n] / (n + 1);
		*/
		if (n == 0)
			return 0;
		long [] T = new long [n + 1];
		Arrays.fill(T, 1);
		int lastRow = n << 1;
		for (int i = 2; i <= lastRow; i++) {
			for (int c = Math.min(i - 1, n); c > 0; c--)
				T[c] += T[c-1];
		}
		return (int)(T[n] / (n + 1));
	}

//	https://leetcode.com/problems/unique-binary-search-trees-ii/
//	95. Unique Binary Search Trees II
	List<TreeNode> generateMergeTreesHelper(int val, List<TreeNode> left, List<TreeNode> right) {
		List<TreeNode> ret = new ArrayList<>();
		if (left == null && right == null) {
			TreeNode root = new TreeNode(val);
			ret.add(root);
		} else if (left == null) {
			Iterator<TreeNode> iter = right.iterator();
			while (iter.hasNext()) {
				TreeNode root = new TreeNode(val);
				root.right = iter.next();
				ret.add(root);
			}
		} else if (right == null) {
			Iterator<TreeNode> iter = left.iterator();
			while (iter.hasNext()) {
				TreeNode root = new TreeNode(val);
				root.left = iter.next();
				ret.add(root);
			}
		} else {
			for (int i = 0; i < left.size(); i++)
				for (int j = 0; j < right.size(); j++) {
					TreeNode root = new TreeNode(val);
					root.left = left.get(i);
					root.right = right.get(j);
					ret.add(root);
				}
		}
		return ret;
	}
	List<TreeNode>  generateTreesHelper(int l, int r) {
		//If you push null to the arraylist this will simply our merge code a lot...
		if (l > r)
			return null;
		List<TreeNode> ret = new ArrayList<>();
		for (int mid = l; mid <= r; ++mid) {
			List<TreeNode> left = generateTreesHelper(l, mid - 1);
			List<TreeNode> right = generateTreesHelper(mid+1, r);
			ret.addAll(generateMergeTreesHelper(mid, left, right));
		}
		return ret;
	}

	List<TreeNode> generateTreesHelperII(int l, int r) {
		List<TreeNode> ret = new ArrayList<>();
		if (l > r) {
			ret.add(null);
		} else {
			for (int mid = l; mid <= r; ++mid) {
				List<TreeNode> left = generateTreesHelperII(l, mid - 1);
				List<TreeNode> right = generateTreesHelperII(mid+1, r);
				for (int i = 0; i < left.size(); i++)
					for (int j = 0; j < right.size(); j++) {
						TreeNode root = new TreeNode(mid);
						root.left = left.get(i);
						root.right = right.get(j);
						ret.add(root);
					}
			}
		}
		return ret;
	}
	public List<TreeNode> generateTrees(int n) {
		if (n == 0)
			return new ArrayList<>();
		return generateTreesHelper(1,n);
	}

//	https://leetcode.com/problems/integer-break/
//	343. Integer Break
//	http://mathforum.org/library/drmath/view/70271.html
//	https://www.hrwhisper.me/leetcode-integer-break/
	public int integerBreak(int n) {
		if (n == 2)
			return 1;
		if (n == 3)
			return 2;
		//assume n >= 2
		int remainder = n % 3;
		int ret = 1;
		switch (remainder) {
			case 1:
				n -= 4;
				ret = 4;
				break;
			case 2:
				n -= 2;
				ret = 2;
				break;
		}
		while (n > 0) {
			n -= 3;
			ret *= 3;
		}

		return ret;
	}

//	https://leetcode.com/problems/binary-tree-inorder-traversal/
//	94. Binary Tree Inorder Traversal
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ret = new ArrayList<>();
		while (root != null) {
			if (root.left == null) {
				ret.add(root.val);
				root = root.right;
			} else {
				TreeNode left;
				for (left = root.left; left.right != null && left.right != root; left = left.right);
				if (left.right == null) {
					left.right = root;
					root = root.left;
				} else {
					left.right = null;
					ret.add(root.val);
					root = root.right;
				}
			}
		}

		return ret;
	}

//	https://leetcode.com/problems/n-queens/
//	51. N-Queens
	boolean solveNQueensCanPut(int index, int row, int [] rows) {
		for (int i = 0; i < row; i++) {
			if (index == rows[i] || (row - i) == Math.abs(index - rows[i])) {
				return false;
			}
		}
		return true;
	}
	void solveNQueensHelper(List<List<String>> ret, char [][] tmp, int row, int [] rows, int n) {
		if (row == n) {
			List<String> l = new ArrayList<>();
			for (int i = 0; i < tmp.length; i++)
				l.add(new String(tmp[i]));
			ret.add(l);
			return;
		}

		for (int i = 0; i < n; i++) {
			//check violations...
			if (solveNQueensCanPut(i, row, rows)) {
				rows[row] = i;
				tmp[row][i] = 'Q';
				solveNQueensHelper(ret, tmp, row + 1, rows, n);
				//put it back
				tmp[row][i] = '.';
			}
		}
	}
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> ret = new ArrayList<>();
		//assume n >=1
		char [][] tmp = new char [n][];
		for (int i = 0; i < n; i++) {
			tmp[i] = new char[n];
			Arrays.fill(tmp[i], '.');
		}

		solveNQueensHelper(ret, tmp, 0, new int [n], n);
		//build vilation map
		return ret;
	}

	boolean isValidPutQueen(int [] table, int row, int k) {
		for (int i = 0; i < row; i++) {
			if (k == table[i] || Math.abs(k - table[i]) == (row - i))
				return false;
		}
		return true;
	}
	void totalNQueensHelper(int [] table, int row, int [] ret) {
		if (row == table.length) {
			++ret[0];
		} else {
			for (int i = 0; i < table.length; i++) {
				if (isValidPutQueen(table, row, i)) {
					table[row] = i;
					totalNQueensHelper(table, row + 1, ret);
				}
			}
		}
	}
	public int totalNQueens(int n) {
		int [] ret = new int[1];
		totalNQueensHelper(new int [n], 0, ret);

		return ret[0];
	}

//	https://leetcode.com/problems/recover-binary-search-tree/
	public void recoverTree(TreeNode root) {
		TreeNode pre = null;
		TreeNode a = null, b = null;
		while (root != null) {
			if (root.left == null) {

				if (pre != null) {
					if (pre.val > root.val) {
						if (a == null) {
							a = pre;
							b = root;
						} else {
							b = root;
						}
					}
				}
				pre = root;
				root = root.right;
			} else {
				TreeNode left;
				for (left = root.left; left.right != null && left.right != root; left = left.right);
				if (left.right == null) {
					left.right = root;
					root = root.left;
				} else {
					left.right = null;
					if (pre != null) {
						if (pre.val > root.val) {
							if (a == null) {
								a = pre;
								b = root;
							} else {
								b = root;
							}
						}
					}
					pre = root;
					root = root.right;
				}
			}
		}

		//swap value between a and b
		int tmp = a.val;
		a.val = b.val;
		b.val = tmp;
	}

//	https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
//	105. Construct Binary Tree from Preorder and Inorder Traversal

	private TreeNode buildTreeHelper(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
		if (pStart >= pEnd || iStart >= iEnd)
			return null;
		TreeNode root = new TreeNode(preorder[pStart]);
		int iMid = iStart;
		while (preorder[pStart] != inorder[iMid])
			++iMid;
		root.left = buildTreeHelper(preorder, pStart + 1, pStart + 1 + (iMid - iStart), inorder, iStart, iMid);
		root.right = buildTreeHelper(preorder, pStart + 1 + (iMid - iStart), pEnd, inorder, iMid+1, iEnd);
		return root;
	}
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		//assume both array are valid
		return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
	}


//	https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
//	106. Construct Binary Tree from Inorder and Postorder Traversal
	TreeNode buildTree106Helper(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd) {
		if (iStart >= iEnd)
			return null;
		TreeNode root = new TreeNode(postorder[pEnd-1]);
		int iMid = iStart;
		while (inorder[iMid] != root.val)
			++iMid;
		root.left = buildTree106Helper(inorder, iStart, iMid, postorder, pStart, pStart + (iMid - iStart));
		root.right = buildTree106Helper(inorder, iMid+1, iEnd, postorder, pStart + (iMid - iStart), pEnd-1);
		return root;
	}

	public TreeNode buildTree106(int[] inorder, int[] postorder) {
		return buildTree106Helper(inorder, 0, inorder.length, postorder, 0, postorder.length);
	}



	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.size() <= 1)
			return intervals;

		//What's the difference between Collections.sort and Arrays.sort
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				if (a.start != b.start)
					return Integer.compare(a.start, b.start);
				else
					return Integer.compare(a.end, b.end);
			}
		});
		Interval cur = intervals.get(0);
		int i = 1;
		List<Interval> ret = new ArrayList<>();
		while (i < intervals.size()) {
			if (cur.end >= intervals.get(i).start) {
				cur.end = Math.max(intervals.get(i).end, cur.end);
			} else {
				ret.add(cur);
				cur = intervals.get(i);
			}
			++i;
		}
		ret.add(cur);
		return ret;
	}

	boolean isIntervalOverlap(Interval a, Interval b) {
		if (a.end < b.start || b.end < a.start)
			return false;
		return true;
	}
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if (intervals == null || newInterval == null)
			return intervals;

		//What's the difference between Collections.sort and Arrays.sort
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				if (a.start != b.start)
					return Integer.compare(a.start, b.start);
				else
					return Integer.compare(a.end, b.end);
			}
		});

		List<Interval> ret = new ArrayList<>();
		int i = 0;
		//by pass left part
		while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
			ret.add(intervals.get(i));
			++i;
		}

		//let's merge this into newInterval
		while (i < intervals.size() && isIntervalOverlap(intervals.get(i), newInterval)) {
			newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
			newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
			++i;
		}
		ret.add(newInterval);

		while (i < intervals.size()) {
			ret.add(intervals.get(i));
			++i;
		}
		return ret;
	}

//	https://leetcode.com/problems/sqrtx/
//	69. Sqrt(x)
//	https://zh.wikipedia.org/wiki/%E7%89%9B%E9%A1%BF%E6%B3%95
//	https://en.wikipedia.org/wiki/Newton%27s_method
	public int mySqrt(int x) {
		if (x < 0)
			return -1;
		if (x <= 1)
			return x;
		int l = 1, r = x;
		while (l + 1 != r) {
			int mid = l + (r - l) / 2;
			if (mid > x/mid)
				r = mid;
			else
				l = mid;
		}
		return l;
	}

	public int mySqrtNewTon(int x) {
		if (x < 0)
			return -1;
		if (x <= 1)
			return x;
		final double EPS = 0.0000000001;
		double last = 0.0;
		double res = x;
		while (Math.abs(last - res) > EPS) {
			last = res;
			res = (res + x / res) / 2;
		}
		return (int) res;
	}

//	92. Reverse Linked List II
//	https://leetcode.com/problems/reverse-linked-list-ii/
	public ListNode reverseBetween(ListNode head, int m, int n) {
		//1<=m <= n <= len(list) => list is not empty
		if (m == n)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		int k = 0;
		ListNode first, second;
		ListNode cur = dummy;
		--m;
		while (k < m) {
			++k;
			cur = cur.next;
		}
		first = cur;
		while (k < n) {
			++k;
			cur = cur.next;
		}
		second = cur;
		ListNode tail = second.next;
		second.next = null;

		ListNode subHead = first.next;
		ListNode subTail = second;
		reverseList(subHead);
		first.next = subTail;
		subHead.next = tail;

		return dummy.next;
	}

//	https://leetcode.com/problems/subsets/
//	78. Subsets
	private void subsetsHelperI(List<List<Integer>> ret,List<Integer> tmp, int[] nums, int index) {
		ret.add(new ArrayList<>(tmp));
		for (int i = index; i < nums.length; i++) {
			tmp.add(nums[i]);
			subsetsHelperI(ret, tmp, nums, i + 1);
			tmp.remove(tmp.size()-1);
		}
	}
//	http://bangbingsyb.blogspot.com/2014/11/leetcode-subsets-i-ii.html
	public List<List<Integer>> subsets(int[] nums) {
//		Given a set of distinct integers, nums, return all possible subsets.
		Arrays.sort(nums);
		List<List<Integer>> ret = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>();
		subsetsHelperI(ret, tmp, nums, 0);
		return ret;
	}


//	https://leetcode.com/problems/subsets-ii/
//	90. Subsets II
private void subsetsHelperII(List<List<Integer>> ret,List<Integer> tmp, int[] nums, int index) {
	ret.add(new ArrayList<>(tmp));
	int i = index;
	while (i < nums.length) {
		tmp.add(nums[i]);
		subsetsHelperII(ret, tmp, nums, i + 1);
		tmp.remove(tmp.size()-1);
		++i;
		while (i < nums.length && nums[i] == nums[i-1])
			++i;
	}
}
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> ret = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>();
		subsetsHelperII(ret, tmp, nums, 0);
		return ret;
	}

//	91. Decode Ways
//	https://leetcode.com/problems/decode-ways/
	public int numDecodings(String s) {
		if (s == null || s.length() <= 0||s.charAt(0) == '0')
			return 0;
		int i = 1;
		while (i < s.length()) {
			if (s.charAt(i) == '0' && !(s.charAt(i-1) == '2' || s.charAt(i-1) == '1'))
				return 0;
			++i;
		}

		int a0 = 1, a1 = 1, a2 = 1;
		i = 1;
		while (i < s.length()) {
			switch(s.charAt(i-1)) {
				case '1':
					if (s.charAt(i) == '0')
						a2 = a0;
					else
						a2 = a0 + a1;
					break;
				case '2':
					if (s.charAt(i) == '0')
						a2 = a0;
					else if (s.charAt(i) > '6')
						a2 = a1;
					else
						a2 = a0 + a1;
					break;
				default:
					a2 = a1;
			}
			a0 = a1;
			a1 = a2;

			++i;
		}
		return a2;
	}

//	88. Merge Sorted Array
//	https://leetcode.com/problems/merge-sorted-array/
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int l = m + n;
		while (l > 0 && n > 0) {
			if (m > 0) {
				if (nums1[m-1] > nums2[n-1])
					nums1[--l] = nums1[--m];
				else
					nums1[--l] = nums2[--n];
			} else {
				nums1[--l] = nums2[--n];
			}
		}
	}

//	86. Partition List
//	https://leetcode.com/problems/partition-list/
	public ListNode partition(ListNode head, int x) {
		ListNode less = new ListNode(0);
		ListNode larger = new ListNode(0);
		ListNode lessTail = less, largerTail = larger;
		while (head != null) {
			if (head.val < x) {
				lessTail.next = head;
				lessTail = head;
			} else {
				largerTail.next = head;
				largerTail = head;
			}
			head = head.next;
		}

		lessTail.next = larger.next;
		largerTail.next = null;
		return less.next;
	}

//	https://leetcode.com/problems/gray-code/
//	89. Gray Code
	public List<Integer> grayCode(int n) {
		List<Integer> ret = new ArrayList<>();
		ret.add(0);
		int i = 0;
		while (i < n) {
			int curSize = ret.size();
			int mask = 1 << i;
			for (int j = curSize - 1; j >= 0; --j) {
				ret.add(ret.get(j) | mask);
			}
			++i;
		}
		return ret;
	}

//	https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
//	http://articles.leetcode.com/convert-sorted-list-to-balanced-binary
//	109. Convert Sorted List to Binary Search Tree
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null)
			return null;
//		if (head.next == null)
//			return new TreeNode(head.val);

		ListNode slow = head, preSlow = null, fast = head;
		while (fast != null && (fast = fast.next) != null && (fast = fast.next) != null) {
			preSlow = slow;
			slow = slow.next;
		}
		TreeNode root = new TreeNode(slow.val);
		if (preSlow != null) {
			preSlow.next = null;
			root.left = sortedListToBST(head);
		}
		if (slow.next != null) {
			root.right = sortedListToBST(slow.next);
		}
		return root;
	}

//	83. Remove Duplicates from Sorted List
//	https://leetcode.com/problems/remove-duplicates-from-sorted-list/
public ListNode deleteDuplicates(ListNode head) {
	if (head == null)
		return null;
	ListNode tail = head, cur = head.next;
	while (cur != null) {
		if (tail.val != cur.val) {
			tail.next = cur;
			tail = cur;
		}
		cur = cur.next;
	}
	tail.next = null;
	return head;
}

//	https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
//	82. Remove Duplicates from Sorted List II
	public ListNode deleteDuplicatesII(ListNode head) {
		if (head == null)
			return null;

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		while (head != null) {
			if (head.next == null || head.next.val != head.val) {
				tail.next = head;
				tail = head;
			}
			ListNode pre = head;
			head = head.next;
			while (head != null && pre.val == head.val)
				head = head.next;
		}
		tail.next = null;
		return dummy.next;
	}

//	https://leetcode.com/problems/remove-duplicates-from-sorted-array/
//	http://bangbingsyb.blogspot.com/2014/11/leetcode-remove-duplicates-from-sorted.html
//	26. Remove Duplicates from Sorted Array/
	public int removeDuplicates(int[] nums) {
		int l = 0;
		int i = 0;
		while (i < nums.length) {
			int j = i + 1;
			while (j < nums.length && nums[j] == nums[j-1])
				++j;
			nums[l++] = nums[i];
			i = j;
		}
		return l;
	}

//	https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
//	80. Remove Duplicates from Sorted Array II
	public int removeDuplicatesII(int[] nums) {
		int l = 0;
		int i = 0;
		while (i < nums.length) {
			int j = i + 1;
			while (j < nums.length && nums[j] == nums[j-1])
				++j;
			nums[l++] = nums[i];
			if (j > i + 1 && i + 1 < nums.length) {
				nums[l++] = nums[i];
			}
			i = j;
		}
		return l;
	}

//	https://leetcode.com/problems/minimum-window-substring/
//	76. Minimum Window Substring
	public String minWindow(String s, String t) {
		if (t.length() == 0 || s.length() < t.length())
			return "";
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < t.length(); i++)
			if (map.containsKey(t.charAt(i)))
				map.put(t.charAt(i), map.get(t.charAt(i))+1);
			else
				map.put(t.charAt(i), 1);
		int minStart = 0;
		int start = 0;
		int total = 0;
		//becase assume exists
		int minLens = s.length();
		HashMap<Character, Integer> map2 = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				if (map2.containsKey(s.charAt(i))) {
					map2.put(s.charAt(i), map2.get(s.charAt(i))+1);
				} else {
					map2.put(s.charAt(i), 1);
				}
				if (map2.get(s.charAt(i)) <= map.get(s.charAt(i))) {
					++total;
				}
				if (total == t.length()) {
					while (true) {
						if (!map2.containsKey(s.charAt(start))) {
							++start;
						} else if (map2.get(s.charAt(start)) > map.get(s.charAt(start))) {
							map2.put(s.charAt(start),  map2.get(s.charAt(start))-1);
							++start;
						} else {
							break;
						}
					}
					//update length
					if (minLens > (i - start + 1)) {
						minStart = start;
						minLens = Math.min(minLens, (i - start + 1));
					}

				}
			}
		}

		if (total == t.length()) {
			while (true) {
				if (!map2.containsKey(s.charAt(start))) {
					++start;
				} else if (map2.get(s.charAt(start)) > map.get(s.charAt(start))) {
					map2.put(s.charAt(start),  map2.get(s.charAt(start))-1);
					++start;
				} else {
					break;
				}
			}
			//update length
			if (minLens > (s.length() - start )) {
				minStart = start;
				minLens = Math.min(minLens, (s.length() - start));
			}

			return s.substring(minStart, minStart + minLens);
		} else {
			return "";
		}
	}

//	https://leetcode.com/problems/combinations/
//	77. Combinations
	void combineHelper(List<List<Integer>> ret, List<Integer> tmp, int n, int remaining, int start) {
		if (remaining == 0) {
			ret.add(new ArrayList<>(tmp));
		}
		if (n - start + 1 >= remaining) {
			for (int i = start; i <= n; i++) {
				tmp.add(i);
				combineHelper(ret, tmp, n, remaining-1, i + 1);
				tmp.remove(tmp.size()-1);
			}
		}
	}
	public List<List<Integer>> combine(int n, int k) {
		//1 <= k <= n, which means they can be always valid and no empty...
		List<List<Integer>> ret = new ArrayList<>();
		combineHelper(ret, new ArrayList<>(), n, k, 1);

		return ret;
	}

//	https://leetcode.com/problems/restore-ip-addresses/
//	93. Restore IP Addresses
	boolean isValidIpSection(String s, int start, int end) {
		int l = end - start;
		if (l >= 1 && l <= 3) {

			//verify 003??
			int total = 0;
			while (start < end) {
				total = 10 * total + (s.charAt(start) - '0');
				++start;
			}
			System.out.println(s.substring(end - l, end));
			System.out.println(Integer.toString(total));
			System.out.println(s.substring(start, end).equals(Integer.toString(total)));
			if (total >= 0 && total <= 255 && s.substring(end - l, end).equals(Integer.toString(total)))
				return true;
		}
		return false;
	}

	void restoreIpAddressesHelper(List<String> ret, String s, int index, int [] partition, int section) {
		if (section == partition.length) {
			//last section
			//verify this part
			if (isValidIpSection(s, partition[partition.length-1], s.length())) {
				ret.add(s.substring(0, partition[0]) + "." + s.substring(partition[0], partition[1]) + "." + s.substring(partition[1], partition[2]) + "." +s.substring(partition[2], s.length()));
			}
		} else {
			for (int i = index; i < index + 3 && i < s.length() && (s.length() - i - 1) >= (3 - section); i++) {
				if (isValidIpSection(s, index, i + 1)) {
					partition[section] = i + 1;
					restoreIpAddressesHelper(ret, s, i + 1, partition, section + 1);
				} else {
					//chances for false, 002, >= 256
					break;
				}
			}
		}
	}
	public List<String> restoreIpAddresses(String s) {
		List<String> ret = new ArrayList<>();
		int [] partition = new int [3];
		restoreIpAddressesHelper(ret, s, 0, partition, 0);
		return ret;
	}

//	https://leetcode.com/problems/climbing-stairs/
//	70. Climbing Stairs
	public int climbStairs(int n) {
		if (n <= 1)
			return 1;
		int a1= 1, a2 = 2;
		int i = 3;
		while (i <= n) {
			int tmp = a1 + a2;
			a1 = a2;
			a2 = tmp;
			++i;
		}
		return a2;
		/*
		//how to save one more variable..
    int climbStairs(int n) {
        if (n<3){return n;}
        int f1=1;
        int f2=2;
        for (int i=3;i<=n;i++){
            f2=f1+f2;
            f1=f2-f1;
        }
        return f2;
    }
		 */
	}

//	https://leetcode.com/problems/add-binary/
//	67. Add Binary
	public String addBinary(String a, String b) {
		//assume both are non-null
		int l1 = a.length(), l2 = b.length();
		char [] tmp = new char [1 + Math.max(l1, l2)];
		int carry = 0;
		int l3 = tmp.length;
		while (l1 > 0 || l2 > 0 || carry > 0) {
			int sum;
			if (l1 > 0 && l2 > 0)
				sum = (a.charAt(--l1) - '0') + (b.charAt(--l2) - '0') + carry;
			else if (l1 > 0)
				sum = (a.charAt(--l1) - '0') + carry;
			else if (l2 > 0)
				sum = (b.charAt(--l2) - '0') + carry;
			else
				sum = carry;

			if (sum >= 2) {
				sum -= 2;
				carry = 1;
			} else {
				carry = 0;
			}
			tmp[--l3] = (char)('0' + sum);
		}
		if (tmp[0] == '1')
			return new String(tmp);
		else
			return new String(Arrays.copyOfRange(tmp, 1, tmp.length));
	}

//	https://leetcode.com/problems/minimum-path-sum/
//	64. Minimum Path Sum
	public int minPathSum(int[][] grid) {
		//init row
		for (int i = 1; i < grid[0].length; i++)
			grid[0][i] += grid[0][i-1];
		//init col
		for (int i = 1; i < grid.length; i++)
			grid[i][0] += grid[i-1][0];
		for (int i = 1; i < grid.length; i++)
			for (int j = 1; j < grid[i].length; j++)
				grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
		return grid[grid.length-1][grid[0].length-1];
	}

//	https://leetcode.com/problems/unique-paths/
//	62. Unique Paths
	public int uniquePaths(int m, int n) {
		//C(m + n, n)
		//No, actually, the ans is C(m-1+n-1, m-1)
		int x = Math.min(m,n);
		int y = Math.max(m,n);
		int [] tmp = new int [x];
		Arrays.fill(tmp, 1);
		int lastRow = x + y - 2;
		for (int row = 1; row <= lastRow; row++) {
			for (int col = Math.min(x-1, row - 1); col >= 1; --col)
				tmp[col] += tmp[col-1];
		}

		return tmp[x-1];
	}

//	https://leetcode.com/problems/unique-paths-ii/
//	http://bangbingsyb.blogspot.com/2014/11/leetcode-unique-paths-i-ii.html
//	63. Unique Paths II
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1] == 1)
			return 0;
		int i = 1;
		boolean isOpen = true;
		while (i < obstacleGrid.length) {
			if (isOpen) {
				obstacleGrid[i][0] = 1 - obstacleGrid[i][0];
				if (obstacleGrid[i][0] == 0)
					isOpen = false;
			} else {
				obstacleGrid[i][0] = 0;
			}
			++i;
		}
		i = 1;
		isOpen = true;
		while (i < obstacleGrid[0].length) {
			if (isOpen) {
				obstacleGrid[0][i] = 1 - obstacleGrid[0][i];
				if (obstacleGrid[0][i] == 0)
					isOpen = false;
			} else {
				obstacleGrid[0][i] = 0;
			}
			++i;
		}
		obstacleGrid[0][0] = 1;

		for (i = 1; i < obstacleGrid.length; ++i)
			for (int j = 1; j < obstacleGrid[i].length; ++j)
				obstacleGrid[i][j] = (obstacleGrid[i][j] == 1) ? 0 : (obstacleGrid[i-1][j] + obstacleGrid[i][j-1]);
		return obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1];
	}

//	https://leetcode.com/problems/plus-one/
//	66. Plus One
	public int[] plusOne(int[] digits) {
		int [] tmp = new int [1 + digits.length];
		int carry = 1;
		int l1 = digits.length, l2 = tmp.length;
		while (l1 > 0 || carry > 0) {
			int sum;
			if (l1 > 0) {
				sum = digits[--l1] + carry;
			} else {
				sum = carry;
			}
			if (sum >= 10) {
				sum -= 10;
				carry = 1;
			} else {
				carry = 0;
			}
			tmp[--l2] = sum;
		}
		if (tmp[0] == 0)
			return Arrays.copyOfRange(tmp, 1, tmp.length);
		else
			return tmp;
	}

//	73. Set Matrix Zeroes
//	https://leetcode.com/problems/set-matrix-zeroes/
	public void setZeroes(int[][] matrix) {
		int i = 0, j = 0;
		//find left most Zero first
		int lefti, leftj;
		boolean isFound = false;
		for (leftj = 0; leftj< matrix[0].length; ++leftj) {
			if (!isFound) {
				for (lefti = 0; lefti < matrix.length; ++lefti)
					if (matrix[lefti][leftj] == 0) {
						i = lefti;
						j = leftj;
						isFound = true;
						break;
					}
			} else {
				for (lefti = 0; lefti < matrix.length; ++lefti) {
					if (matrix[lefti][leftj] == 0) {
						//make Mark
						matrix[i][leftj] = 0;//for Col
						matrix[lefti][j] = 0;//for row
					}
				}
			}
		}
		if (isFound) {
			//Fill Zeros
			//Col first
			for (leftj = matrix[0].length - 1; leftj > j; --leftj) {
				if (matrix[i][leftj] == 0) {
					//fill the col
					for (int k = 0; k < matrix.length; ++k)
						matrix[k][leftj] = 0;
				}
			}
			//Fill rows
			for (lefti = 0; lefti < matrix.length; ++lefti)
				if (matrix[lefti][j] == 0)
					for (int k = 0; k < matrix[0].length; ++k)
						matrix[lefti][k] = 0;
			//finish the last col
			for (int k = 0; k < matrix.length; ++k)
				matrix[k][j] = 0;
		}
	}

//	https://leetcode.com/problems/permutation-sequence/
//	60. Permutation Sequence
	int power(int n){
		int ret = 1;
		while (n > 1)
			ret *= n--;
		return ret;
	}
	public String getPermutation(int n, int k) {
		//1 <= k <= n!
		char [] tmp = new char[n];
		boolean [] isUsed = new boolean[n];
		--k;
		int index = 0;

		while (index < tmp.length) {
			int p = power(--n);
			int j = k / p;
			for (int t = 0; t < isUsed.length; t++)
				if (!isUsed[t]) {
					if (j == 0) {
						tmp[index++] = (char)('1' + t);
						isUsed[t] = true;
						break;
					} else {
						--j;
					}
				}
			k %= p;
		}
		return new String(tmp);
	}

//	https://leetcode.com/problems/rotate-list/
//	61. Rotate List
	public ListNode rotateRight(ListNode head, int k) {

		if (head == null)
			return head;

		ListNode cur = head;
		int l = 0;
		while (cur != null) {
			cur = cur.next;
			++l;
		}
		k %= l;
		if (k == 0)
			return head;
		k = l - k;

		ListNode first = head;
		while (k > 1) {
			first = first.next;
			--k;
		}
		ListNode second = first.next;
		ListNode tail = second;
		while (tail.next != null)
			tail =tail.next;
		tail.next = head;
		first.next = null;

		return second;
	}

	public String simplifyPath(String path) {
		Stack<String> st = new Stack<>();
		int i = 0;
		while (i < path.length()) {
			while (i < path.length() && path.charAt(i) == '/')
				++i;
			int j = i;
			while (j < path.length() && path.charAt(j) != '/')
				++j;
			if (j > i) {
				boolean isGoodPath = true;
				if (path.charAt(i) == '.') {
					if (j - i == 1)
						isGoodPath = false;
					else if (j - i == 2 && path.charAt(i+1) == '.') {
						isGoodPath = false;
						if (!st.isEmpty())
							st.pop();
					}
				}

				if (isGoodPath) {
					st.push(new String(path.substring(i,j)));
				}
			}
			i = j;
		}
		StringBuilder sb = new StringBuilder();
		while (!st.isEmpty()) {
			sb.insert(0, "/" + st.pop());
		}
		if (sb.length() == 0)
			return new String("/");
		else
			return sb.toString();
	}

//	https://leetcode.com/problems/jump-game/
//	55. Jump Game
	public boolean canJump(int[] nums) {
		if (nums == null)
			return true;
		int maxReachable = 0;
		int l = nums.length;
		for (int i = 0; i <= maxReachable && i < l; ++i) {
			maxReachable = Math.max(i + nums[i], maxReachable);
		}
		return maxReachable >= (l - 1);
	}

//	https://leetcode.com/problems/jump-game-ii/
//	45. Jump Game II
	public int jump(int[] nums) {
		//You can assume that you can always reach the last index.
		int counter = 0;
		int i = 0;
		while (i < nums.length-1) {

			//check reachable from current i
			//i,i+1,.... i + nums[i], pick up who can jump furthest
			//You should not come a node says nums[i] == 0, otherwise you will stop there except for the last node...
			//current node could finish
			if (i + nums[i] >= nums.length - 1) {
				++counter;
				break;
			} else {
				int k = i + 1;
				for (int j = i + 2; j <= i + nums[i] && j < nums.length; j++) {
					if (j + nums[j] >= k + nums[k])
						k = j;
				}
				++counter;
				i = k;
			}

		}
		return counter;
	}

//	https://leetcode.com/problems/powx-n/
//	50. Pow(x, n)
	public double myPowHelper(double x, long n) {
		if (n == 0)
			return 1.0;
		return (n & 0x01) == 0 ? myPowHelper(x*x, n / 2) : x * myPowHelper(x*x, n / 2);
	}
	public double myPow(double x, int n) {
		return n >= 0 ? myPowHelper(x, n) : 1.0 / myPowHelper(x, 0 - (long)(n));
	}

//	https://leetcode.com/problems/anagrams/
//	49. Group Anagrams
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> ret = new ArrayList<>();
		HashMap<String, List<String>> map = new HashMap<>();
		for (int i = 0; i < strs.length; ++i) {
			char [] tmp = strs[i].toCharArray();
			Arrays.sort(tmp);
			String key = new String(tmp);
			if (!map.containsKey(key))
				map.put(key, new ArrayList<>());
			map.get(key).add(strs[i]);
		}
		for (List<String> l : map.values()) {
			ret.add(l);
		}
		return ret;
	}

//	https://leetcode.com/problems/rotate-image/
//	48. Rotate Image
	public void rotate(int[][] matrix) {
	}

//	https://leetcode.com/problems/spiral-matrix/
//	54. Spiral Matrix
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ret = new ArrayList<>();
		int m = matrix.length;
		int n = matrix[0].length;
		int r = 0, c = 0;

		return ret;
	}

//	https://leetcode.com/problems/first-missing-positive/
//	41. First Missing Positive
	public int firstMissingPositive(int[] nums) {
		int i;
		int l = nums.length;
		for (i = 0; i < l; i++) {
			while (nums[i] >= 1 && nums[i] <= l && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
				swap(nums, nums[i] - 1, i);
			}
		}
		i = 0;
		while (i < nums.length) {
			if (nums[i] != i + 1)
				return i + 1;
			++i;
		}
		return i + 1;
	}

//	46. Permutations
//	https://leetcode.com/problems/permutations/
	void permuteIhelper(List<List<Integer>> ret, Integer [] nums, int level) {
		if (level == nums.length) {
			ret.add(new ArrayList<>(Arrays.asList(nums)));
		} else {
			for (int i = level; i < nums.length; i++) {
				swap(nums, i, level);
				permuteIhelper(ret, nums, level+1);
				swap(nums, i, level);
			}
		}
	}
	public List<List<Integer>> permute(int[] nums) {
		/*
		List<List<Integer>> ret = new ArrayList<>();
		Integer [] tmp = new Integer[nums.length];
		for (int i = 0; i < nums.length; i++)
			tmp[i] = Integer.valueOf(nums[i]);

		permuteIhelper(ret, tmp, 0);
		return ret;
		*/
		List<List<Integer>> ret = new ArrayList<>();
		Integer [] tmp = new Integer[nums.length];
		for (int i = 0; i < nums.length; i++)
			tmp[i] = Integer.valueOf(nums[i]);
		ret.add(new ArrayList<>(Arrays.asList(tmp)));

		for (int i = 0; i + 1 < nums.length; ++i) {
			int size = ret.size();
			for (int k = 0; k < size; ++k) {
				for (int j = i + 1; j < nums.length; ++j) {
					List<Integer> tmpl = new ArrayList<>(ret.get(k));
					//swap
					int tmpi = tmpl.get(j);
					tmpl.set(j, tmpl.get(i));
					tmpl.set(i, tmpi);
					ret.add(tmpl);
				}

			}
		}

		return ret;
	}

//	47. Permutations II
//	https://leetcode.com/problems/permutations-ii/
void permuteUniquehelper(List<List<Integer>> ret, int [] counter, int [] val, int level, Integer [] tmp) {
	if (level == tmp.length) {
		ret.add(new ArrayList<>(Arrays.asList(tmp)));
	} else {
		for (int i = 0; i < counter.length; ++i) {
			if (counter[i] > 0) {
				--counter[i];
				tmp[level] = val[i];
				permuteUniquehelper(ret, counter, val, level + 1, tmp);
				++counter[i];
			}
		}
	}
}

//	http://bangbingsyb.blogspot.com/2014/11/leetcode-permutations-i-ii.html
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();
		Arrays.sort(nums);
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i : nums) {
			if (map.containsKey(i))
				map.put(i, 1 + map.get(i));
			else
				map.put(i, 1);
		}
		int [] counter = new int [map.size()];
		int [] val = new int [map.size()];
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			val[i] = entry.getKey();
			counter[i] = entry.getValue();
			++i;
		}

		Integer [] tmp = new Integer[nums.length];

		permuteUniquehelper(ret, counter, val, 0, tmp);
		return ret;
	}

//	https://leetcode.com/problems/multiply-strings/
//	43. Multiply Strings
	public String multiply(String num1, String num2) {
		int l1 = num1.length();
		int l2 = num2.length();
		int tmp [] = new int [l1 + l2];
		for (int i = 0; i < l1; ++i)
			for (int j = 0; j < l2; ++j)
				tmp[i+j+1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
		int carryOn = 0;
		for (int i = tmp.length - 1; i>= 0; --i) {
			tmp[i] += carryOn;
			carryOn = tmp[i] / 10;
			tmp[i] %= 10;
		}
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < tmp.length && tmp[i] == 0)
			++i;
		while (i < tmp.length)
			sb.append(Integer.toString(tmp[i++]));
		if (sb.length() == 0)
			return "0";
		else
			return sb.toString();
	}

//	https://leetcode.com/problems/count-and-say/
//	38. Count and Say
	public String countAndSay(int n) {
		StringBuilder sb = new StringBuilder("1");
		while (--n > 0) {
			StringBuilder next = new StringBuilder();
			int counter = 1;
			int i = 1;
			while (i < sb.length()) {
				if (sb.charAt(i) == sb.charAt(i-1))
					++counter;
				else {
					next.append(Integer.toString(counter) + sb.charAt(i-1));
					counter = 1;
				}
				++i;
			}
			next.append(Integer.toString(counter) + sb.charAt(i-1));
			sb = next;
		}

		return sb.toString();
	}

//	https://leetcode.com/problems/search-insert-position/
//	35. Search Insert Position
	public int searchInsert(int[] nums, int target) {
        /*
        int ret = Arrays.binarySearch(nums, target);
        return ret >= 0 ? ret : (-1-ret);
        */
		int l = -1, r = nums.length;
		while (l + 1 != r) {
			int mid = l + (r - l) / 2;
			if (nums[mid] < target)
				l = mid;
			else
				r = mid;
		}
		return r;
	}

//	https://leetcode.com/problems/search-for-a-range/
//	34. Search for a Range
	private int searchLeft(int[] nums, int target) {
		int l = -1, r = nums.length;
		while (l + 1 != r) {
			int mid = l + (r - l) / 2;
			if (nums[mid] < target)
				l = mid;
			else
				r = mid;
		}
		return r == nums.length || nums[r] != target ? -1 : r;
	}
	private int searchRight(int[] nums, int target) {
		int l = -1, r = nums.length;
		while (l + 1 != r) {
			int mid = l + (r - l) / 2;
			if (target < nums[mid])
				r = mid;
			else
				l = mid;
		}
		return l == -1 || nums[l] != target ? -1 : l;
	}
	public int[] searchRange(int[] nums, int target) {
		int [] ret = new int [2];
		ret[0] = searchLeft(nums, target);
		ret[1] = searchRight(nums, target);
		return ret;
	}

//	https://leetcode.com/problems/next-permutation/
//	31. Next Permutation
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length <= 1)
			return;
		int i = nums.length - 2;
		while (i >= 0 && nums[i] >= nums[i+1])
			--i;
		if (i >= 0) {
			//find a place to swap
			int k = nums.length-1;
			while (nums[k] <= nums[i])
				--k;
			swap(nums,k,i);
		}
		//swap
		//from i + 1 to nums.length -1
		int k = i + 1, j = nums.length-1;
		while (k  < j) {
			swap(nums,k++,j--);
		}
	}

//	30. Substring with Concatenation of All Words
//	https://leetcode.com/problems/substring-with-concatenation-of-all-words/
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> ret = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		for (String word : words) {
			if (map.containsKey(word))
				map.put(word, map.get(word)+1);
			else
				map.put(word, 1);
		}
		int l = words[0].length();
		for (int i = 0; i < l; ++i) {
			HashMap<String, Integer> map2 = new HashMap<>();
			int start = i;
			int total = 0;
			for (int j = i; j + l <= s.length(); j += l) {
				String tmp = s.substring(j, j + l);
				if (!map.containsKey(tmp)) {
					map2.clear();
					start = j + l;
					total = 0;
				} else {
					map2.put(tmp, map2.get(tmp) == null ? 1 : map2.get(tmp)+1);
					if (map2.get(tmp) > map.get(tmp)) {
						String tmp2;
						while (!(tmp2=s.substring(start, start + l)).equals(tmp)) {
							map2.put(tmp2, map2.get(tmp2)-1);
							--total;
							start += l;
						}
						map2.put(tmp, map2.get(tmp)-1);
						start += l;
					} else {
						++total;
					}
					if (total == words.length)
						ret.add(start);
				}
			}

		}

		return ret;
	}

//	https://leetcode.com/problems/divide-two-integers/
//	29. Divide Two Integers

	public int divide(int dividend, int divisor) {
		/*
		if (divisor == 0)
			return Integer.MAX_VALUE;
		boolean isSigned = false;
		long a,b;
		if (dividend >= 0) {
			a = dividend;
		} else {
			isSigned = !isSigned;
			a = 0 - (long)dividend;
		}
		if (divisor >= 0) {
			b = divisor;
		} else {
			isSigned = !isSigned;
			b = 0 - (long)divisor;
		}

		if (a < b)
			return 0;

		long ret = 0;
		int k = 0;
		while ((a>>1) >= (b<<k)) {
			++k;
		}

		while (k >= 0) {
			long b2 = b << k;
			if (a >= b2) {
				a -= b2;
				ret += (1 << k);
			}
			--k;
		}
		//The big problem here is that (1<<31) is a negative value....
		if (ret < 0) {
			if (isSigned)
				return (int)ret;
			else
				return Integer.MAX_VALUE;
		}

		if (isSigned) {
			ret = 0 - ret;
		}
		if (ret < Integer.MIN_VALUE || ret > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else
			return (int)ret;
			*/
		if(divisor==0) return Integer.MAX_VALUE;
		if(divisor==-1 && dividend == Integer.MIN_VALUE)
			return Integer.MAX_VALUE;

		//get positive values
		long pDividend = Math.abs((long)dividend);
		long pDivisor = Math.abs((long)divisor);

		int result = 0;
		while(pDividend>=pDivisor){
			//calculate number of left shifts
			int numShift = 0;
			while(pDividend>=(pDivisor<<numShift)){
				numShift++;
			}

			//dividend minus the largest shifted divisor
			result += 1<<(numShift-1);
			pDividend -= (pDivisor<<(numShift-1));
		}

		if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
			return result;
		}else{
			return -result;
		}
	}

//	https://leetcode.com/problems/reverse-nodes-in-k-group/
//	25. Reverse Nodes in k-Group
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode tail = dummy;
		while (head != null) {
			//go by k nodes
			int i = 1;
			ListNode cur = head;
			while (cur != null && i < k) {
				cur = cur.next;
				++i;
			}

			if (i >= k && cur != null) {
				ListNode next = cur.next;
				//after some processes...
				cur.next = null;
				//reverse begin head and cur
				reverseList(head);
				tail.next = cur;
				head.next = next;
				tail = head;
				head = next;
			} else {
				break;
			}
		}
		return dummy.next;
	}

//	https://leetcode.com/problems/swap-nodes-in-pairs/
//	24. Swap Nodes in Pairs
public ListNode swapPairs(ListNode head) {
	ListNode dummy = new ListNode(0);
	dummy.next = head;
	ListNode tail = dummy;
	while (head != null && head.next != null) {
		ListNode next = head.next.next;
		tail.next = head.next;
		head.next.next = head;
		head.next = next;
		tail = head;
		head = next;
	}
	return dummy.next;
}

//	https://leetcode.com/problems/merge-k-sorted-lists/
//	http://bangbingsyb.blogspot.com/2014/11/leetcode-merge-k-sorted-lists.html
//	23. Merge k Sorted Lists
	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> q = new PriorityQueue<>(new Comparator<ListNode> (){
			public int compare(ListNode a, ListNode b) {
				return Integer.compare(a.val, b.val);
			}
		}
		);

		for (int i = 0; i < lists.length; ++i)
			if (lists[i] != null)
				q.add(lists[i]);

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		while (!q.isEmpty()) {
			ListNode top = q.poll();
			if (top.next != null)
				q.add(top.next);
			tail.next = top;
			tail = top;
		}
		return dummy.next;
	}

//	https://leetcode.com/problems/valid-parentheses/
//	20. Valid Parentheses
	public boolean isValid(String s) {
		Stack<Character> st = new Stack<>();
		for (int i = 0; i < s.length(); ++i) {
			switch(s.charAt(i)) {
				case ')':
					if (st.isEmpty() || st.pop() != '(')
						return false;
					break;
				case '}':
					if (st.isEmpty() || st.pop() != '{')
						return false;
					break;
				case ']':
					if (st.isEmpty() || st.pop() != '[')
						return false;
					break;
				default:
					st.push(s.charAt(i));
			}
		}

		//what if s is empty???
		return st.isEmpty();
	}

//	https://leetcode.com/problems/generate-parentheses/
//	22. Generate Parentheses
	void generateParenthesisHelper(List<String> ret, char [] tmp, int level, int left, int right, int maxLeft) {
		if (level == tmp.length) {
			ret.add(new String(tmp));
		} else {
			if (left < maxLeft) {
				tmp[level] = '(';
				generateParenthesisHelper(ret, tmp, level + 1, left + 1, right, maxLeft);
			}
			if (left > right) {
				tmp[level] = ')';
				generateParenthesisHelper(ret, tmp, level + 1, left, right + 1, maxLeft);
			}
		}
	}
	public List<String> generateParenthesis(int n) {
		List<String> ret = new ArrayList<>();
		generateParenthesisHelper(ret, new char [2*n], 0, 0, 0, n);
		return ret;
	}


	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode slow = dummy, fast = dummy;
		while (n-- > 0)
			fast = fast.next;
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}
		slow.next = slow.next.next;
		return dummy.next;
	}

//	1. Two Sum
//	https://leetcode.com/problems/two-sum/
	public int[] twoSumI(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; ++i) {
			if (map.get(target - nums[i]) != null) {
				int [] ret = new int [2];
				ret[0] = map.get(target - nums[i]);
				ret[1] = i;
				return ret;
			} else {
				map.put(nums[i], i);
			}
		}

		return null;
	}

//	https://leetcode.com/problems/3sum/
//	15. 3Sum
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();
		Arrays.sort(nums);
		int i = 0;
		while (i < nums.length - 2) {
			int j = i + 1, k = nums.length - 1;
			while (j < k) {
				int total = nums[i] + nums[j] + nums[k];
				if (total == 0) {
					ret.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
					++j;
					while (j < k && nums[j] == nums[j-1])
						++j;
					--k;
					while (j < k && nums[k] == nums[k+1])
						--k;
				} else if (total < 0) {
					++j;
					while (j < k && nums[j] == nums[j-1])
						++j;
				} else {
					--k;
					while (j < k && nums[k] == nums[k+1])
						--k;
				}
			}
			++i;
			while (i < nums.length - 2 && nums[i] == nums[i-1])
				++i;
		}
		return ret;
	}

	public int threeSumClosest(int[] nums, int target) {
		int ret = nums[0] + nums[1] + nums[2];
		Arrays.sort(nums);
		int i = 0;
		while (i < nums.length - 2) {
			int j = i + 1, k = nums.length - 1;
			while (j < k) {
				int total = nums[i] + nums[j] + nums[k];
				if (total == target) {
					return target;
				} else if (total < target) {
					++j;
					while (j < k && nums[j] == nums[j-1])
						++j;
				} else {
					--k;
					while (j < k && nums[k] == nums[k+1])
						--k;
				}
				if (Math.abs(target - ret) > Math.abs(target - total))
					ret = total;
			}
			++i;
			while (i < nums.length - 2 && nums[i] == nums[i-1])
				++i;
		}
		return ret;
	}

//	https://leetcode.com/problems/container-with-most-water/
//	11. Container With Most Water
	//below is one the best explaination I can found, for l == r, you can also use the matrix to explain it.
//	https://leetcode.com/discuss//yet-another-way-to-see-what-happens-in-the-o-n-algorithm
//	https://leetcode.com/discuss/1074/anyone-who-has-a-o-n-algorithm
//	http://www.cnblogs.com/jcliBlogger/p/4646659.html
	public int maxArea(int[] height) {
		int ret = 0;
		int i = 0, j = height.length - 1;
		while (i < j) {
			ret = Math.max(ret, (j-i)*Math.min(height[i],height[j]));
			if (height[i] < height[j])
				++i;
			else
				--j;
		}
		return ret;
	}

//	https://leetcode.com/problems/letter-combinations-of-a-phone-number/
//	17. Letter Combinations of a Phone Number
	public List<String> letterCombinations(String digits) {
		final String [] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		List<String> ret = new ArrayList<>();
		int length = digits.length();
		if (length <= 0)
			return ret;

		//init
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < digits.length(); ++i)
			sb.append(mapping[digits.charAt(i)-'0'].charAt(0));
		ret.add(new String(sb));

		//plus one
		while (true) {
			int i = length - 1;
			while (i >= 0 && sb.charAt(i) == mapping[digits.charAt(i)-'0'].charAt(mapping[digits.charAt(i)-'0'].length()-1))
				--i;

			if (i < 0)
				break;
			//self plus one
			sb.setCharAt(i, mapping[digits.charAt(i)-'0'].charAt(mapping[digits.charAt(i)-'0'].indexOf(sb.charAt(i)) + 1));
			++i;
			while (i < digits.length()) {
				sb.setCharAt(i, mapping[digits.charAt(i)-'0'].charAt(0));
				++i;
			}
			ret.add(new String(sb));
		}
		return ret;
	}

//	https://leetcode.com/problems/longest-common-prefix/
//	14. Longest Common Prefix
	public String longestCommonPrefix(String[] strs) {
		int length = strs.length;
		if (length <= 0)
			return "";
		if (length ==1)
			return strs[0];
		Arrays.sort(strs);
		//stupid boy, you just need check commom prefix between the first and the last one here.
		int l = 0;
		for (l = 0; l < strs[0].length() && l < strs[strs.length-1].length() && (strs[0].charAt(l) == strs[strs.length-1].charAt(l)); ++l);
		return strs[0].substring(0,l);
	}

//	https://leetcode.com/problems/regular-expression-matching/
//	10. Regular Expression Matching
private boolean isMatch10Recursion(String s, int i, String p, int j) {
	if (j == p.length())
		return i == s.length();

	if (i == s.length()) {
		if (j + 1 < p.length()) {
			return p.charAt(j+1) == '*' && isMatch10Recursion(s,i,p,j+2);
		} else {
			return p.charAt(j) == '*';
		}
	} else {
		if (j + 1 < p.length() && p.charAt(j+1) == '*') {
			//you can have unlimited match
			do {
				if (isMatch10Recursion(s, i, p, j + 2))
					return true;
			} while (i < s.length() && (s.charAt(i++) == p.charAt(j) || p.charAt(j) == '.'));
			return false;
		} else {
			//current c must match....
			return (s.charAt(i) == p.charAt(j)  || p.charAt(j) == '.') && isMatch10Recursion(s, i + 1, p, j + 1);
		}
	}
}
	public boolean isMatch10(String s, String p) {

		/*
		//it works, but's have a DP version
		return isMatch10Recursion(s,0,p,0);
		*/

		//assume p will not start with '*'
		int m = p.length(), n = s.length();
		boolean [][] T = new boolean[1+m][];
		for (int i = 0; i < T.length; ++i)
			T[i] = new boolean[1+n];
		T[0][0] = true;
//		for (int i = 1; i < T.length; ++i)
//			T[i][0] = (p.charAt(i-1) == '*') ? (i >= 2 && T[i-2][0]) : false;

		for (int i = 1; i < T.length; ++i) {
			if (p.charAt(i-1) == '*') {
//				T[i][0] = (i >= 2 && T[i-2][0]);//i must >= 2 here. God sake
				T[i][0] = T[i-2][0];
				for (int j = 1; j < T[i].length; ++j) {
					//After so many years, I finally found this tricks...
					T[i][j] = T[i-2][j] || ((p.charAt(i-2) == s.charAt(j-1) || p.charAt(i-2) == '.') && (T[i][j-1]));
				}
			} else {
				T[i][0] = false;//by default
				for (int j = 1; j < T[i].length; ++j)
					T[i][j] = T[i-1][j-1] && (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '.');
			}

		}
			//T[i][0] = (p.charAt(i-1) == '*') ? (i >= 2 && T[i-2][0]) : false;

		//print table
//		for (int i = 0; i < T.length; ++i) {
//			for (int j = 0; j < T[i].length; ++j) {
//				System.out.print(T[i][j] + "\t");
//			}
//			System.out.println();
//		}

		return T[m][n];
	}

//	https://leetcode.com/problems/median-of-two-sorted-arrays/
//	4. Median of Two Sorted Arrays
	//k count from 1
	private double findMedianSortedArrays(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
		//end case first
		if (start1 >= end1)
			return nums2[start2 + k - 1];
		if (start2 >= end2)
			return nums1[start1 + k - 1];
		//so you should have at least one is not terminated.

//		if (k == 1)
//			return Math.min(nums1[start1], nums2[start2]);
		int mid1 = start1 + ((end1 - start1) >> 1);
		int mid2 = start2 + ((end2 - start2) >> 1);

		if (nums1[mid1] < nums2[mid2]) {
			if (mid1 - start1 + 1 + mid2 - start2>= k) {
				return findMedianSortedArrays(nums1, start1, end1, nums2, start2, mid2, k);
			} else {
				//how to prevent endless recursions here????
				return findMedianSortedArrays(nums1, mid1 + 1, end1, nums2, start2, end2, k - (mid1 - start1 + 1));
			}
		} else {
			if (mid2 - start2 + mid1 - start1 + 1>= k) {
				return findMedianSortedArrays(nums1, start1, mid1, nums2, start2, end2, k);
			} else {
				return findMedianSortedArrays(nums1, start1, end1, nums2, mid2+1, end2, k - (mid2 - start2 + 1));
			}

		}
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		//assuem no null is here
		int l1 = nums1.length, l2 = nums2.length;

		//what if l1 == 0 || l2 == 0

		//don't trick me on l1 + l2 overflow
		if (((l1 + l2) & 0x01) == 0) {
			//even
//			System.out.println(findMedianSortedArrays(nums1, 0, l1, nums2, 0, l2, (l1+l2)/2));
//			System.out.println(findMedianSortedArrays(nums1, 0, l1, nums2, 0, l2, (l1+l2)/2 + 1));
			return (findMedianSortedArrays(nums1, 0, l1, nums2, 0, l2, (l1+l2)/2 + 1) + findMedianSortedArrays(nums1, 0, l1, nums2, 0, l2, (l1+l2)/2)) / 2.0;
		} else {
			//odd
			return findMedianSortedArrays(nums1, 0, l1, nums2, 0, l2, (l1+l2)/2 + 1);
		}
	}

//	https://leetcode.com/problems/longest-palindromic-substring/
	public String longestPalindrome(String s) {
		int length = s.length();
		if (length <= 1)
			return s;
		int max_start = 0, max_length = 1;
		boolean T [] = new boolean[s.length()];
		T[s.length()-1] = true;
		for (int i = s.length()-2; i >= 0; --i) {
			T[i] = true;
			for (int j = s.length()-1; j > i; --j) {
				T[j] = (j > i + 1) ? s.charAt(i) == s.charAt(j) && T[j-1] : s.charAt(i) == s.charAt(j);
				if (T[j] && j - i + 1 > max_length) {
					max_length = j - i + 1;
					max_start = i;
				}
			}
		}

		return s.substring(max_start, max_start + max_length);
	}

//	https://leetcode.com/problems/add-two-numbers/
//	2. Add Two Numbers
	ListNode addTwoNumbersII(ListNode l1, ListNode l2, int carryon) {
		if (l1 == null && l2 == null) {
			if (carryon == 0)
				return null;
			else
			//assum carryon is 1 in this case.
				return new ListNode(1);
		}
		int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carryon;
		if (sum >= 10) {
			sum -= 10;
			carryon = 1;
		} else {
			carryon = 0;
		}
		ListNode ret = new ListNode(sum);
		ret.next = addTwoNumbersII(l1 != null ? l1.next : null, l2 != null ? l2.next : null, carryon);
		return ret;
	}
	public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
		return addTwoNumbersII(l1,l2,0);
	}

//	340	Longest Substring with At Most K Distinct Characters
//Given a string s, find the length of the longest substring T that contains at most k distinct characters.
//	For example, Given s = "eceba", k = 3
//T is "eceb" which its length is 4.
public int lengthOfLongestSubstringKDistinct(String s, int k) {
	//assume k >= 0
	if (k <= 0)
		return 0;
	if (s.length() <= k)
		return s.length();

	// k >= 1, l > k >= 1
	int ret = 1;
	HashMap<Character, Integer> map = new HashMap<>();
	int start = 0;
	for (int i = 0; i < s.length(); ++i) {
		if (map.containsKey(s.charAt(i))) {
			map.put(s.charAt(i), map.get(s.charAt(i))+1);
		} else if (map.size() < k) {
			map.put(s.charAt(i), 1);
		} else {
			map.put(s.charAt(i), 1);
			while (map.size() > k) {
				if (map.get(s.charAt(start)) > 1)
					map.put(s.charAt(start), map.get(s.charAt(start))-1);
				else
					map.remove(s.charAt(start));
				++start;
			}
		}
		ret = Math.max(ret, i - start + 1);
	}
	return ret;
}

//	从一个string里面找出至多有k个不同字符的最长子串，用hashmap加两个pointer解决，follow up是如果string很长很长，而且只给一个iterator,每次调用给一个字符，怎么办？
//	解决方法是hashmap里面不存字符的occurance,而是存字符最后出现的位置。返回值是substring的start index和end index
//	If you can only get one iterator, the above do make senses now....
	/*
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
	//assume k >= 0
	if (k <= 0)
		return 0;
	if (s.length() <= k)
		return s.length();

	// k >= 1, l > k >= 1
	int ret = 1;
	HashMap<Character, Integer> map = new HashMap<>();
	Iterator<Charactor> start = s.Iterator();
	//Looks like you can't get below lines in this casesss...
	Iterator<Charactor> cur = s.Iterator();
	int total = 0;
	while (cur.hasNext()) {
		char c = cur.next();
		if (map.containsKey(c)) {
			map.put(s.charAt(i), map.get(c)+1);
			++total;
		} else if (map.size() < k) {
			map.put(c, 1);
			++total;
		} else {
			map.put(c, 1);
			++total;
			while (map.size() > k) {
				c = start.next();
				if (map.get(c) > 1)
					map.put(c, map.get(c)-1);
				else
					map.remove(c);
				--total;
			}
		}
		ret = Math.max(ret, total);

	}

	return ret;
}
	*/

//	https://leetcode.com/problems/top-k-frequent-elements/
//	347. Top K Frequent Elements
//	http://www.programcreek.com/2014/05/leetcode-top-k-frequent-elements-java/
	static class TopKFrequentNode{
		int val;
		int counter;
		TopKFrequentNode(int val, int counter) {
			this.val = val;
			this.counter = counter;
		}
	}
	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> ret = new ArrayList<>();
		//sort??, nlogn
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; ++i)
			if (!map.containsKey(nums[i]))
				map.put(nums[i], 1);
			else
				map.put(nums[i], 1 + map.get(nums[i]));

		PriorityQueue<TopKFrequentNode> q = new PriorityQueue<>(new Comparator<TopKFrequentNode>() {
			@Override
			public int compare(TopKFrequentNode a, TopKFrequentNode b) {
				return Integer.compare(b.counter, a.counter);
			}
		});
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			q.add(new TopKFrequentNode(entry.getKey(), entry.getValue()));
		}
		while (ret.size() < k) {
			ret.add(q.poll().val);
		}
		return ret;
	}

//	popular number  找出数组里面出现次数大于 n/4 的数字， 之前面经见过 解法 binary search
	//I can do it in O(n)
	//What??? The input is sorted???

//	全程无表情。 给一个二叉树，一个source node 和 一个 target node， 问是否有一个路径从source node 到 target node （沿着parent 到 children ） ，LZ很天真的说用dfs做下，写完然后问如何在O1 time 和space 找到， LZ没想出， 然后提示是说先preprocess下，每个node 加一个label 的range，举个栗子 [1,10] 代表这个node下面的节点范围是1-10 这样通过判断 target node 的label是否在范围内就可以确定，感觉像segment tree， 最后没时间写代码了。 跪了。。。

//	假设除了用1和0表示数字的binary方式，还可以用2来表示，给一个数字，求有多少种表达方式。.
//	例子：4 = 100, 20 所以答案是2。 8 = 1000, 200, 120, 112 所以答案是4。

	int highest_order_bit( int x )
	{
		int y = x;
		do {
			x = y;
			y = x & (x-1); //remove low order bit
		}
		while( y != 0 );
		return x;
	}

//	如果n是奇数， 那么他的binary表示方式的最右位必然是1， 所以我们只要考虑除去最后一位剩下左边几位的表现形式，即dp[n] = dp[(n-1)/2]
//	如果n是偶数，那么他的binary表示方式的最右位必然是0或2，我们分情况讨论：
//	如果最右位是0，那么只要把n/2的每种表示方式后面加个0即可， 即dp[n] = dp[n/2]
//	如果最右位是2，类似的可以得到dp[n] = dp[(n-2)/2]

//	int countWays(int n){
//		vector<int> dp = {1, 1}; // 1 way when n = 0, 1 way when n = 1
//
//		for(int i = 2; i<=n; i++) {
//
//			int ways = 0;
//
//			if(i%2) {
//					// i is odd number, right most bit must be one
//					ways = dp[(i-1)/2];
//
//			}else {
//				// i is even number, right most bit can be 0 or 2
//
//				// when right most bit is 0
//				ways = dp[i/2];
//
//				// when right most bit is 2
//				ways += dp[(i-2)/2];
//			}
//			dp.push_back(ways);
//
//		}
//		return dp[n];
//	}
//
	//below is wrong, see representation of 6.
	int f3(int n) {
		if (n <= 1)
			return 1;
		int shift = 31;
		while ((n & (1 << shift)) == 0)
			--shift;

		int ret = 1;
		--shift;
		while (shift >= 0) {
			if ((n & (1 << shift)) == 0) {
				if (shift == 0)
					ret += 1;
				else
					ret += f3(n & ((1 << shift)-1));
			}
			--shift;
		}
		return ret;
	}

//	https://leetcode.com/problems/intersection-of-two-arrays/
//	349. Intersection of Two Arrays
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums1.length; ++i)
			set.add(nums1[i]);
		Set<Integer> set2 = new HashSet<Integer>();
		for (int i = 0; i < nums2.length; ++i)
			if (set.contains(nums2[i]))
				set2.add(nums2[i]);
		int [] ret = new int [set2.size()];
		int k = 0;
		for (Integer i : set2)
			ret[k++] = i;
		return ret;
	}

//	https://leetcode.com/problems/intersection-of-two-arrays-ii/
//	350. Intersection of Two Arrays II
	public int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		List<Integer> tmp = new ArrayList<>();
		int i = 0, j = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j])
				++i;
			else if (nums1[i] > nums2[j])
				++j;
			else {
				tmp.add(nums1[i]);
				++i;
				++j;
			}
		}
		int [] ret = new int [tmp.size()];
		for (i = 0; i < tmp.size(); ++i)
			ret[i] = tmp.get(i);
		return ret;
	}

	public static void main(String[] args) {
		Solution s = new Solution();

//		String [] words= {"a","b","c","ab","ac","aa"};
//		String [] words= {"a",""};
//		String [] words= {"ab","ba"};
//		System.out.println(s.palindromePairs(words));

//		int nums[] = {2,1,5,0,3};
//		int nums[] = {1,2,3};
//		int nums[] = {1,0,0,2,0,0,3};
//		int nums[] = {0,2,0,3};
//		int nums[] = {1,2,2};
//		System.out.println(s.increasingTriplet(nums));

//		String [][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
//		String [][] tickets = {{"JFK","SFO"}, {"JFK","ATL"}, {"SFO","ATL"}, {"ATL","JFK"}, {"ATL","SFO"}};
//		String [][] tickets = {{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
//		String [][] tickets = {{"A","B"}, {"B", "D"}};
//		String [][] tickets = {{"A","B"}};
//		List<String> ret = s.findItinerary(tickets);
//		System.out.println(ret.size());
//		for(String t : ret) {
//			System.out.println(t);
//		}

//		String preorder = "9,#,#,1";
//		String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
// 		String preorder = "9,#,92,#,#";
//		String preorder = "92,#,#";
//		String preorder = "3,4,#,#,#";
//		System.out.println(s.isValidSerialization(preorder));

//		int [] nums = {1,2,31,33};
//		int n = 2147483647;
//		System.out.println(s.minPatches(nums, n));

//		int [][] nums = {{9,9,4}, {6,6,8}, {2,1,1}};
//		int [][] nums = {{1}};
//		System.out.println(s.longestIncreasingPath(nums));

//		ListNode n1 = new ListNode(1);
//		ListNode n2 = new ListNode(2);
//		ListNode n3 = new ListNode(3);
//		n1.next = n2;
//		n2.next = n3;
//		ListNode ret = s.oddEvenList(n1);
//		while (ret != null) {
//			System.out.println(ret.val);
//			ret = ret.next;
//		}

//		int [] nums = {-2, 5, -1};
//		int lower = -2, upper = 2;
//		System.out.println(s.countRangeSum(nums, lower, upper));

//		for (int n =0; n < 40; n++)
//		System.out.println(s.isPowerOfThree(n));

//		int [] nums = {1, 5, 1, 1, 6, 4};
//		int [] nums = {1, 3, 2, 2, 3, 1};
//		int [] nums = {4,5,5,6};
//		s.wiggleSort(nums);
//		for (int i : nums) {
//			System.out.println(i);
//		}

//		int n =8;
//		System.out.println(n|1);

//		int nums [] = {1, 5, 11};
//		int amount = 39;
//		int [] nums = {27,352,421,198,317,110,461,31,264};
//		int amount = 5303;
//		int [] nums = {3,7,405,436};
//		int amount = 8839;
//		int [] nums = {3,7};
//		int amount = 11;
//		System.out.println(s.coinChange(nums, amount));

//		int [] nums = {5, 2, 6, 1};
//		System.out.println(s.countSmaller(nums));

//		int [] nums = {-2, 5, -1};
//		System.out.println(s.countRangeSum(nums, -2, 2));

//		int [] nums = {2147483647,-2147483648,-1,0};
//		System.out.println(s.countRangeSum(nums, -1, 0));

//		int [] nums = {0,0};
//		System.out.println(s.countRangeSum(nums, 0, 0));


//		String s1 = new String("bcacdcbc");
//		String s1 = new String("bcabc");
//		System.out.println(s.removeDuplicateLetters(s1));

		/*
		int [] nums1 = {3, 4, 6, 5};
		int [] nums2 = {9, 1, 2, 5, 8, 3};
		int k = 5;
		*/
//		int [] nums1 = {6,7};
//		int [] nums2 = {6,0,4};
//		int k = 5;
//		int [] nums1 = {2,5,6,4,4,0};
//		int [] nums2 = {7,3,8,0,6,5,7,6,2};
//		int k = 15;
//		for (int i : s.maxNumber(nums1, nums2, k))
//			System.out.println(i);

//		PriorityQueue<Integer> queue = new PriorityQueue<>();
//		queue.add(1);
//		queue.add(1);
//		queue.add(2);
//		queue.add(3);
//		queue.add(4);
//		while (queue.size() != 0) {
//			System.out.println(queue.remove());
//		}

//		System.out.println(s.nthUglyNumber(10));

//		int [] primes = {2,3,5,13,19,29,31,41,43,53,59,73,83,89,97,103,107,109,127,137,139,149,163,173,179,193,197,199,211,223,227,229,239,241,251,257,263,269,271,281,317,331,337,347,353,359,367,373,379,389,397,409,419,421,433,449,457,461,463,479,487,509,521,523,541,547,563,569,577,593,599,601,613,619,631,641,659,673,683,701,709,719,733,739,743,757,761,769,773,809,811,829,857,859,881,919,947,953,967,971};
//		int n = 4000;
//		System.out.println(s.nthSuperUglyNumber(n, primes));

//		int [] nums = {3, 1, 5, 8};
//		System.out.println(s.maxCoins(nums));

//		int n = 4;
//		int [][] edges = {{1, 0}, {1, 2}, {1, 3}};
//		int n = 6;
//		int [][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
//		System.out.println(s.findMinHeightTrees(n, edges));

//		int [] prices = {1, 2, 3, 0, 2};
//		System.out.println(s.maxProfitWithCooldown(prices));

//		String num = String.valueOf(112358);
//		System.out.println(num.charAt('0'));
//		String num = String.valueOf(199100199);
//		String num = String.valueOf("12122436");
//		String num = String.valueOf("221474836472147483649");
//		System.out.println(s.isAdditiveNumber(num));

//		String s1 = "sdfjs)))))(";
//		String s1 = "()())()";
//		System.out.println(s.removeInvalidParentheses(s1));

//		System.out.println(s.wordPattern(null, "     "));
//		System.out.println(s.wordPattern("abba", "dog    cat    cat   dog     "));

//		int [] nums = {10, 9, 2, 5, 3, 7, 101, 18};
//		int [] nums = {2,2,2,2,101,2,2,2};
//		int [] nums = {10,9,2,5,3,7,101,18};
//		int [] nums = {2,3,7};
//		System.out.println(s.lengthOfLIS(nums));

//		int intArr[] = {30,20,5,12,55};
//		Arrays.sort(intArr);
//		int searchVal = 2;
//		System.out.println(Arrays.binarySearch(intArr,searchVal));
//		for (int k = 1; k <= 16; k++)
//			System.out.println(s.numSquares(k));

//		System.out.println(s.firstBadVersion(1));
//		int[] citations = {0,0,0,3,4,4};
//		int[] citations = {1,2,2};
//		System.out.println(s.hIndexII(citations));

//		String num = "105";
//		int target = 5;
//		String num = "123";
//		int target = 6;
//		String num = "232";
//		int target = 8;
//		String num = "0000";
//		int target = 0;

//		String num = "3456237490";
//		int target = 9191;
//		for (String t : s.addOperators(num, target)) {
//			System.out.println(t);
//		}

//		int[][] grid = new int [3][5];
//		grid[0][0]=1;
//		grid[0][4]=1;
//		grid[2][2]=1;
//		grid[0][2]=2;
//		System.out.println(s.shortestDistance(grid));

//		int num = 1;
//		System.out.println(s.isPowerOfFour(num));

//		int n = 3, m = 3;
//		int [][] positions= {{0,0},{0,1},{2,2},{2,1}};
//		for (Integer i : s.numIslands2(n,m,positions)) {
//			System.out.println(i);
//		}
//		char [][] image = {{'0','0','1','0'},{'0','1','1','0'},{'0','1','0','0'}};
//		int x = 0, y = 2;
//		System.out.println(s.minArea(image,x,y));

//		String input = "aacecaaa";
//		String input = "abcd";
//		System.out.println(s.shortestPalindrome(input));

//		int [] nums = {1,3,-1,-3,5,3,6,7};
//		for (int i :  s.maxSlidingWindow(nums, 3))
//			System.out.println(i);

//		int [] nums = {1,2,3,4};
//		for (int i : s.productExceptSelf(nums))
//			System.out.println(i);
//		System.out.println("sdfsdf".substring(1,-1));

//		String pattern = "abab", str = "redblueredblue";
//		String pattern = "aaaa", str = "asdasdasdasd";
//		String pattern = "aabb", str = "xyzabcxzyabc";
//		String pattern = "ab", str = "aa";
//		System.out.println(s.wordPatternMatch(pattern, str));

//		int [][] rooms = new int [4][4];
//		for (int i = 0; i < rooms.length; i++)
//			Arrays.fill(rooms[i], Integer.MAX_VALUE);
//		rooms[0][1] = -1;
//		rooms[0][2] = 0;
//		rooms[1][3] = -1;
//		rooms[2][1] = -1;
//		rooms[2][3] = -1;
//		rooms[3][0] = 0;
//		rooms[3][1] = -1;
//		for (int i = 0; i < rooms.length; i++) {
//			for (int j = 0; j < rooms[0].length; j++)
//				System.out.print(rooms[i][j] + "\t");
//			System.out.println();
//		}
//
//		s.wallsAndGates(rooms);
//
//		System.out.println();
//		for (int i = 0; i < rooms.length; i++) {
//			for (int j = 0; j < rooms[0].length; j++)
//				System.out.print(rooms[i][j] + "\t");
//			System.out.println();
//		}

//		String [] words = {"wrt", "wrf", "er", "ett", "rftt"};
//		String [] words = {"ac", "ad"};
//		System.out.println(s.alienOrder(words));

//		System.out.println(s.canPermutePalindrome("aabc"));

//		int [][] costs = {{5,8,6}, {19,14,13}, {7,5,12}, {14,15,17}, {3,20,10}};
//		System.out.println(s.minCost(costs));

//		int n = 5;
//		int [][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
//		int [][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
//	Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
//	Hint:
//	Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
//		System.out.println(s.validTree(n, edges));

//		int [] nums = {2,2,3,2};
//		System.out.println(s.singleNumberII(nums));

//		int [] nums = {0,0,1,2};
//		for (int i : s.singleNumberIII(nums))
//			System.out.println(i);

//		int [] nums = {-2, 0, 1, 3};
//		int target = 2;
//		System.out.println(s.threeSumSmaller(nums,target));

//		int[] preorder = {2, 3, 1};
//		System.out.println(s.verifyPreorder(preorder));

//		int n = 1024;
//		for (List<Integer> l : s.getFactors(n)) {
//			for (int i = 0; i < l.size(); i++)
//				System.out.print(l.get(i) + "\t");
//			System.out.println();
//		}

//		[[0, 30],[5, 10],[15, 20]],
//		Interval [] intervals = new Interval[3];
//		intervals[0] = new Interval(0, 30);
//		intervals[1] = new Interval(5, 10);
//		intervals[2] = new Interval(15, 20);
//		intervals[0] = new Interval(1, 7);
//		intervals[1] = new Interval(2, 3);
//		intervals[2] = new Interval(4, 5);

//		System.out.println(s.canAttendMeetingsI(intervals));
//		System.out.println(s.minMeetingRooms(intervals));

//		5 (c1)
//	   /       \
//	  1(c2)    5(c3)
//	 /     \      \
//	5(c4)  5(c5)   5(c6)
//		TreeNode c1 = new TreeNode(5);
//		TreeNode c2 = new TreeNode(1);
//		TreeNode c3 = new TreeNode(5);
//		TreeNode c4 = new TreeNode(5);
//		TreeNode c5 = new TreeNode(5);
//		TreeNode c6 = new TreeNode(5);
//		c1.left = c2;
//		c1.right = c3;
//		c2.left = c4;
//		c2.right = c5;
//		c3.right = c6;
//		System.out.println(s.countUnivalSubtrees(c1));

//		String [] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
//		Arrays.sort(strings);
//		for (String ss : strings)
//			System.out.println(ss);
/*
a
abc
acef
az
ba
bcd
xyz
z
*/

//		String [] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
//		for (List<String> l : s.groupStrings(strings)) {
//			for (String ss : l)
//				System.out.print(ss + "\t");
//			System.out.println();
//		}

//		String test = "619";
//		System.out.println(s.isStrobogrammatic(test));

//		int n =3;
//		for (String ss : s.findStrobogrammatic(n)) {
//			System.out.println(ss);
//		}

//		String low = "50", high = "100";
//		System.out.print(s.strobogrammaticInRange(low, high));

//		String [] words = {"practice", "makes", "perfect", "coding", "makes"};
////		String word1 = "coding", word2 = "practice";
////		String word1 = "makes", word2 = "coding";
////		System.out.println(s.shortestDistance(words, word1, word2));
//		String word1 = "makes", word2 = "makes";
//		System.out.println(s.shortestWordDistanceIII(words, word1, word2));

//		int [] nums = {3,2,3};
//		for (int i : s.majorityElement(nums))
//			System.out.println(i);

//		int [] nums = {0,3,7,2,5,8,4,6,0,1};
//		int [] nums = {100, 4, 200, 1, 3, 2};
//		System.out.println(s.longestConsecutive(nums));

//		int [] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
//		System.out.println(s.trap(nums));

//		System.out.println(s.countDigitOne(10));

//		int[] nums = {-1,2147483647};
//		int k = 1;
//		int t = 2147483647;
//		int[] nums = {1,3,1};
//		int k = 1;
//		int t = 1;
//		System.out.println(s.containsNearbyAlmostDuplicate(nums, k, t));

//		int [][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
//		for (int [] a : s.getSkyline(buildings)) {
//			System.out.print(a[0] + "\t" + a[1]);
//			System.out.println();
//		}

//		char [][] matrix = {
//				{'1', '0', '1', '0', '0'},
//				{'1', '0', '1', '1', '1'},
//				{'1', '1', '1', '1', '1'},
//				{'1', '0', '1', '1', '0'}
//		};
//		char [][] matrix = {
//				{'1', '0', '1', '0'},
//				{'1', '0', '1', '1'},
//				{'1', '0', '1', '1'},
//				{'1', '1', '1', '1'}
//		};
//
//		System.out.print(s.maximalSquare(matrix));

//		int [] nums = {2,1,5,6,2,3};
//		System.out.println(s.largestRectangleArea(nums));

//		int [] candidates = {14,6,25,9,30,20,33,34,28,30,16,12,31,9,9,12,34,16,25,32,8,7,30,12,33,20,21,29,24,17,27,34,11,17,30,6,32,21,27,17,16,8,24,12,12,28,11,33,10,32,22,13,34,18,12};
//		int target = 27;
//		int [] candidates = {19,33,28,13,25,29,11,24,11,8,18,7,6,10,31,26,28,5,5,15,24,9,14,18,13,29,21,30,21,19,22,6,17,16,6,29,32,12,18,30,7,31,24,30,28,20,22,22,20,14,24,31};
//		int target = 25;
//		int [] candidates = {10,1,2,7,6,1,5};
//		int target = 8;
//		for (List<Integer> l : s.combinationSum2(candidates, target)) {
//			for (Integer i : l)
//				System.out.print(i + "\t");
//			System.out.println();
//		}

//		System.out.println(s.combinationSum3(3,9));

//		int n = 2;
//		System.out.println(Integer.toHexString(n));
//		System.out.println(Integer.toHexString(s.reverseBits(n)));

//		int nums [] = {3,2,1,5,6,4};
//		int k = 2;

//		for (int i = 1; i <= 4; i++) {
//			int nums [] = {3,4,2,1};
//		int k = i;
//		System.out.println(s.findKthLargest(nums, k));
//		}

//		int nums [] = {3,4,2,1};
//		int k = 4;
//		System.out.println(s.findKthLargest(nums, k));

//		int [] nums = {2,1};
//		s.sortColors(nums);
//		for (int i = 0; i < nums.length; i++) {
//			System.out.print(nums[i] + "\t");
//		}
//		System.out.println();

//		char [][] board = {{'A','B','C','E'},
//							{'S','F','C', 'S'},
//							{'A','D','E','E'}};
//		String word = "ABCCED";
//		System.out.println(s.wordSearchExist(board, word));

//		String [] words = {"oath","pea","eat","rain"};
//		char [][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};
//		System.out.println(s.findWords(board, words).size());

//		int numCourses = 2;
//		int[][] prerequisites = {{1,0}, {0,1}};
//		System.out.println(s.canFinish(numCourses, prerequisites));

//		int target = 120331635;
//		System.out.println(s.minSubArrayLen(target, nums));

//		System.out.print(s.countPrimes(499979));

//		System.out.println(s.calculateI("5    "));
//		System.out.println(s.calculateII("1*2-3/4+5*6-7*8+9/10"));
//		System.out.println(s.calculateII("1*2-3/4+5*6"));

//		System.out.println(s.rangeBitwiseAnd(5,7));

//		char[] input = "the sky is blue".toCharArray();
//		s.reverseWords(input);
//		System.out.println(input);

//		int [] nums = {1,2,3,4,5,6,7};
//		s.rotate(nums, 3);
//		for (int i : nums)
//			System.out.print(i + "\t");

//		int [] nums = {2,5};
//		for (int i = 0; i <= 10; i++)
//			System.out.println(s.minBills(i, nums));

//		int [] nums = {9,8,23,1,0};
//		System.out.println(s.maximumGapRadix(nums));

//		System.out.println(s.compareVersion("1", "1.1"));
//
//		System.out.println("1".matches("^[0]+$"));

//		System.out.println(s.fractionToDecimal(4,333));


//		int[] vals = {0, 1, 3, 50, 75};
//		int start= 0, end=99;
//		System.out.println(s.findMissingRanges(vals, start, end));

//		System.out.println(s.isOneEditDistance("aa", "abc"));
//		ListNode n1 =  new ListNode(1);
//		ListNode n2 =  new ListNode(2);
//		n1.next = n2;
//		n2.next = n1;
//		System.out.println(s.detectCycle(n1).val);

//		System.out.println(s.lengthOfLongestSubstringTwoDistinct("abcbbbbcccbdddadacb"));

//		for (List<String> ss : s.partition("aab")) {
//			for (String s2 : ss)
//				System.out.print(s2 + ",");
//			System.out.println();
//		}
//		System.out.println(s.minCut("abc"));

//		System.out.println(s.numDistinct("ccc", "c"));
//		int [] nums = {4, 5, 6, 7, 0, 1, 2};
//		int [] nums = {4, 5, 1, 2, 3};
//		System.out.println(s.findMin(nums));

//		int [] nums = {-2, -1};
//		System.out.println(s.maxProduct(nums));

//		String s1 = "aabcc", s2 = "dbbca";
//		System.out.println(s.isInterleave(s1, s2, "aadbbcbcac"));
//		String [] tokens = {"4", "13", "5", "/", "+"};
//		System.out.println(s.evalRPN(tokens));


//		Point[] points = new Point[3];
//		points[0] = new Point(0,0);
//		points[1] = new Point(-1,-1);
//		points[2] = new Point(2,2);
//		System.out.println(s.maxPoints(points));

//		String beginWord = "hit";
//		String endWord = "cog";
//		String [] wordsList = {"hot","dot","dog","lot","log"};
//		String beginWord = "aac";
//		String endWord = "bbc";
//		String [] wordsList = {"abc"};
//		Set<String> wordList = new HashSet<>(Arrays.asList(wordsList));
//		System.out.println(s.ladderLength(beginWord, endWord, wordList));

//		for (List<String> l : s.findLadders(beginWord, endWord, wordList)) {
//			for (String ss : l)
//				System.out.print(ss + "\t");
//			System.out.println();
//		}

//		String [] sinput = {"OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","OXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX","OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO"};
//		char [][] input = new char[sinput.length][];
//		for (int i = 0; i < sinput.length; i++)
//			input[i] = sinput[i].toCharArray();
//		s.solve(input);

//		for (Integer i : s.getRow(0))
//			System.out.print(i);

//		String  s1 = "abc", s2 = "bca";
//		System.out.println(s.isScramble(s1,s2));

//		String s1 = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba";
//		String s2 = "a*******b";
//		String s1 = "aa";
//		String s2 = "aa";
//		String s1 = "aab";
//		String s2 = "c*a*b";
//		String s1 = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
//		String s2 = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
//		System.out.println(s.isMatchGreedy(s1,s2));

//		System.out.println(s.numTrees(3));
//		System.out.println(s.integerBreak(10));
//		System.out.println(Math.log(0.01));

//		for (List<String> l: s.solveNQueens(4)) {
//			for (String ss : l)
//				System.out.print(ss + "\t");
//			System.out.println();
//		}

//		System.out.println(s.buildTree106(new int [] {-1},new int [] {-1}));
//		for (int i = 0; i <= 26; i++)
////			System.out.println(s.mySqrtNewTon(i) == (int)Math.sqrt(i));
//			System.out.println(s.mySqrtNewTon(i) + "\t" + (int)Math.sqrt(i));

//		int[] nums = {1,2,2};
//		System.out.println(s.subsetsWithDup(nums).size());

//		System.out.println(s.numDecodings("122"));

//		ListNode h1 = new ListNode(1);
//		ListNode h2 = new ListNode(2);
//		ListNode h3 = new ListNode(3);
//		ListNode h4 = new ListNode(4);
//		h1.next = h2;
//		h2.next = h3;
//		h3.next = h4;
//		System.out.println(s.sortedListToBST(h1));

//		for (List<Integer> l: s.combine(4, 2)) {
//			for (Integer i : l)
//				System.out.print(i + "\t");
//			System.out.println();
//		}

//		System.out.println(s.minWindow("cabwefgewcwaefgcf", "cae"));
//		System.out.println(s.minWindow("wcwaefgcf", "cae"));
//		System.out.println(s.minWindow("ABCwrwer", "ABC"));

//		for (String ss : s.restoreIpAddresses("0000"))
//			System.out.println(ss);

//		System.out.println(s.uniquePaths(2,2));
//		int[][] matrix = {{0,1}};
//		s.setZeroes(matrix);

//		int n = 3;
//		for (int k = 1; k <= 6; k++)
//			System.out.println(s.getPermutation(n,k));
//		System.out.println(s.getPermutation(n,2));

//		System.out.println(s.simplifyPath("/a/./b/../../c/"));

//		int [] nums = {2,0,0};
//		System.out.println(s.canJump(nums));

//		int [] nums = {2,3,1,1,4};
//		System.out.println(s.jump(nums));

//		int [] nums = {1,1};
//		System.out.println(s.firstMissingPositive(nums));

//		int [] nums = {1,1,2,2,3,3};
//		for (List<Integer> l : s.permuteUnique(nums)) {
//			for (Integer i : l)
//				System.out.print(i + "\t");
//			System.out.println();
//		}
//		int [] nums = {1,2,3};
//		for (List<Integer> l : s.permute(nums)) {
//			for (Integer i : l)
//				System.out.print(i + "\t");
//			System.out.println();
//		}

//		System.out.println(s.multiply("234","23"));
//		System.out.println(s.countAndSay(6));

//		int[] nums = {1,3,5,6};
//		System.out.println(s.searchInsert(nums, 5));
//		System.out.println(s.searchInsert(nums, 2));
//		System.out.println(s.searchInsert(nums, 7));
//		System.out.println(s.searchInsert(nums, 0));

//		String ss = "abc123abc123abc123";
//		String[] words = {"abc", "123"};
//		for (Integer i : s.findSubstring(ss, words)) {
//			System.out.print(i + "\t");
//		}
//		System.out.println();

//		System.out.println(s.divide(Integer.MIN_VALUE,1));
//		System.out.println(Math.abs((long)Integer.MIN_VALUE) >= (1 << 32));
//		System.out.println(Math.abs((long)Integer.MIN_VALUE) >= ((long)1 << 32));

//		int nums [] = {3,2,4};
//		int target = 6;
//		System.out.println(s.twoSumI(nums, target));

//		for (String ss : s.letterCombinations("23")) {
//			System.out.println(ss);
//		}
//		System.out.println(s.isMatch10("aa", "a"));
//		System.out.println(s.isMatch10("aa", "aa"));
//		System.out.println(s.isMatch10("aaa", "aa"));
//		System.out.println(s.isMatch10("aa", "a*"));
//		System.out.println(s.isMatch10("aa", ".*"));
//		System.out.println(s.isMatch10("ab", ".*"));
//		System.out.println(s.isMatch10("aa", "a"));
//		int [] nums1 = {1};
//		int [] nums2 = {1};
//		System.out.println(s.findMedianSortedArrays(nums1, nums2));

//		System.out.println(s.lengthOfLongestSubstringKDistinct("eceba", 3));
//		System.out.print(s.isStrobogrammatic("10"));
//		for (String ss : s.findStrobogrammatic(1)) {
//			System.out.println(ss);
//		}

//		int nums [] = {1,1,1,2,2,3};
//		int k = 2;
//		for (Integer i : s.topKFrequent(nums, k)) {
//			System.out.print(i + "\t");
//		}
		for (int i = 0; i <= 16; ++i)
			System.out.println(i + "\t" + s.f3(i));
	}

}
