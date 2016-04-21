package com.l1337.l296;


//	Best Meeting Point, 296
//	A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1,
// where each 1 marks the home of someone in the group.
// The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
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

public class Solution {

    public int minTotalDistance(int[][] grid) {
        return 0;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}





///Analysis
//	二维的等于一维的相加, 一维的最小点必在median点(用反证法可以证明).
//	http://math.stackexchange.com/questions/113270/the-median-minimizes-the-sum-of-absolute-deviations
/*
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
		// 计算纵坐标到纵坐标中点的距离，这里不需要排序，因为之前统计时是按照i的顺序  <--- That's true because we added by row order
		for (Integer pos : ipos) {
			sum += Math.abs(pos - ipos.get(ipos.size() / 2));
		}
		// 计算横坐标到横坐标中点的距离，这里需要排序，因为统计不是按照j的顺序
		Collections.sort(jpos);
		for (Integer pos : jpos) {
			sum += Math.abs(pos - jpos.get(jpos.size() / 2));
		}
		return sum;
	}
 */