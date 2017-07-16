package com.l1337.l587;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

static class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}


/*
https://en.wikipedia.org/wiki/Graham_scan
Again, determining whether three points constitute a "left turn" or a "right turn" does not require computing the actual angle between the two line segments, and can actually be achieved with simple arithmetic only. For three points {\displaystyle P_{1}=(x_{1},y_{1})} P_{1}=(x_{1},y_{1}), {\displaystyle P_{2}=(x_{2},y_{2})} P_{2}=(x_{2},y_{2}) and {\displaystyle P_{3}=(x_{3},y_{3})} P_{3}=(x_{3},y_{3}), compute the z-coordinate of the cross product of the two vectors {\displaystyle {\overrightarrow {P_{1}P_{2}}}} \overrightarrow {P_{1}P_{2}} and {\displaystyle {\overrightarrow {P_{1}P_{3}}}} \overrightarrow {P_{1}P_{3}}, which is given by the expression {\displaystyle (x_{2}-x_{1})(y_{3}-y_{1})-(y_{2}-y_{1})(x_{3}-x_{1})} (x_{2}-x_{1})(y_{3}-y_{1})-(y_{2}-y_{1})(x_{3}-x_{1}). If the result is 0, the points are collinear; if it is positive, the three points constitute a "left turn" or counter-clockwise orientation, otherwise a "right turn" or clockwise orientation (for counter-clockwise numbered points).

 */
//    https://leetcode.com/problems/erect-the-fence/discuss/103299/Java-Solution-Convex-Hull-Algorithm-Gift-wrapping-aka-Jarvis-march
    public List<Point> outerTrees(Point[] points) {
//        Arrays.sort(points, new Comparator<Point>() {
//            @Override
//            public int compare(Point o1, Point o2) {
//                if (o1.x == o2.x)
//                    return o1.y - o2.y;
//                else
//                    return o1.x - o2.x;
//            }
//        });


        //points.length >= 1
        Set<Point> unvisited = new HashSet<>();
        Set<Point> visited = new HashSet<>();
        Point first = points[0];
        unvisited.add(points[0]);
        for (int i = 1; i < points.length; ++i) {
            unvisited.add(points[i]);
            if (points[i].x < first.x)
                first = points[i];
            else if ((points[i].x == first.x) && (points[i].y < first.y))
                first = points[i];//second if may not necessary, let's see
        }

        unvisited.remove(first);
        visited.add(first);

        Point pre = new Point(first.x, 1 + first.y);
        while (!unvisited.isEmpty()) {
            Map<Double, List<Point>> map = new HashMap<>();

            Point AB = new Point(pre.x - first.x, pre.y - first.y);
            Iterator<Point> iter = unvisited.iterator();
            double curMaxCos = Double.MIN_VALUE;

            while (iter.hasNext()) {
                Point C = iter.next();
                Point AC = new Point(C.x - first.x, C.y - first.y);
                double curCos = innerCos(AB, AC);

                List<Point> cur = map.get(curCos);
                if (cur == null) {
                    cur = new ArrayList<>();
                    cur.add(C);
                    map.put(curCos, cur);
                } else {
                    cur.add(C);
                }

                curMaxCos = Math.max(curMaxCos, curCos);
            }

            //add all nodes that has that has the same curCos
            boolean stop = false;
            List<Point> cur = map.get(curMaxCos);
            double maxScale = -1;
            Point maxScaleNode = null;
            for (int k = 0; k < cur.size(); ++k) {
                Point C = cur.get(k);
                unvisited.remove(C);
                if (visited.contains(C))
                    stop = true;
                else
                    visited.add(C);
                Point AC = new Point(C.x - first.x, C.y - first.y);
                double curScale = Math.pow(AC.x, 2) + Math.pow(AC.y, 2);
                if (curScale > maxScale) {
                    maxScale = curScale;
                    maxScaleNode = C;
                }
            }

            if (stop)
                break;
            else {
                pre = first;
                first = maxScaleNode;
            }

        }

        return new ArrayList<>(visited);
    }

    private double innerCos(Point AB, Point AC) {
    return (AB.x * AC.x + AB.y * AC.y) / (Math.sqrt(Math.pow(AB.x, 2) + Math.pow(AB.y, 2)) * Math.sqrt(Math.pow(AC.x, 2) + Math.pow(AC.y, 2)));
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        List<Point> ret = s.outerTrees(new Point[]{new Point(1,1),
                new Point(2,2),new Point(2,0),new Point(2,4),new Point(3,3),new Point(4,2)});
//        List<Point> ret = s.outerTrees(new Point[]{new Point(1,1),
//                new Point(4,2),new Point(2,0)});

        for (int i = 0; i < ret.size(); ++i) {
            System.out.println(ret.get(i).x + "\t" + ret.get(i).y);
        }
    }
}
