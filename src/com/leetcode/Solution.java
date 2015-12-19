package com.leetcode;

import java.util.Hashtable;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class Solution {

//	https://leetcode.com/problems/two-sum/
	public static int[] twoSum(int[] numbers, int target) {
		int [] ret = new int [2];
        
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
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) {
	 *         val = x;
	 *         next = null;
	 *     }
	 * }
	 */
	public class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) {
			 val = x;
			 next = null;
		 }
	}

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
    			while(s.charAt(start) != s.charAt(i)) {
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

    public String longestPalindrome(String s) {
    	int length = s.length();
    	if (length <= 1)
    		return s;
    	
    	int start = 0, max_length = 1;
    	
    	return s.substring(start, start + max_length);
    }
    
	public static void main(String[] args) {

//		two Sum
//		int [] numbers={2, 7, 11, 15};
//		int target = 9;
//		int [] ret = twoSum(numbers, target);
//		for (int i : ret )
//			System.out.println(i);
		
	}

}
