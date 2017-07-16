package com.googlePrep;


import java.lang.reflect.Array;
import java.util.*;

public class Solution {

//    给一个 string,返回含有 word 的 list。word 的定义是空格(大于等于一个)之间的或者引号 之间的,如果引号里面有空格要做为一个 word 返回。比如 string 是 I have a "faux coat" 要返回[I, have, a, faux coat]

    public List<String> findWords(String s) {
        List<String> ret = new ArrayList<>();
        int length = s.length();
        if (s == null || length == 0)
            return ret;
        int i = 0;
        while (i < length) {
            while (i < length && Character.isWhitespace(s.charAt(i)))
                ++i;
            if (i < length) {
                char c = s.charAt(i);
                if (c == '"') {
                    //How about ""??? return empty String??
                    int start = ++i;
                    while (i < length && s.charAt(i) != '"')
                        ++i;
                    ret.add(s.substring(start, i));
                } else {
                    int start = i;
                    //How about abc""cdsd
                    while (i < length && !Character.isWhitespace(s.charAt(i)) && s.charAt(i) != '"')
                        ++i;
                    ret.add(s.substring(start, i));
                }
            }

            ++i;
        }
        return ret;
    }

//    题目是给一个 n*m 的二维数组返回一个一维数组,这个数组包含之前数组的对角线的和。 下面是例子:
//            [1, 2, 3
//            4, 5, 6
//            7, 8, 9] 要返回 [7, 12, 15, 8, 3]
    public int [] ConvertTwoDimention(int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0)
            return null;
        int n = array.length;
        int m = array[0].length;
        int [] ret = new int [m + n - 1];

        //Think how to calculate in a random rectagular...
        for (int i = 0; i < n; i++) {
            //from A[n-1-i][0] to ?
            for (int r = n - 1 - i, col = 0; r < n && col < m; r++, col++)
                ret[i] += array[r][col];
        }

        for (int col = 1; col < m; col++) {
            for (int k = 0, j = col; k < n && j < m; k++, j++)
                ret[n+col-1] += array[k][j];
        }
        return ret;
    }

    //    给 string, 只包含{0,1,?}, ?可以代表 0 或者 1, 输出所有的组合. 例如"10?", 输出"100", "101"
    public void parse01StringsHelper(char [] tmp, int start, int length, String input, List<String> ret) {
        if (start == length) {
            ret.add(new String(tmp));
            return;
        }

        char c = input.charAt(start);
        if (c == '0' || c == '1') {
            tmp[start] = c;
            parse01StringsHelper(tmp, start+1, length, input, ret);
        } else {
            tmp[start] = '0';
            parse01StringsHelper(tmp, start+1, length, input, ret);
            tmp[start] = '1';
            parse01StringsHelper(tmp, start+1, length, input, ret);
        }
    }
    public List<String> parse01Strings(String input) {
        List<String> ret = new ArrayList<>();
        if (input == null || input.length() <= 0)
            return ret;
        int length = input.length();
        char [] tmp = new char[length];
        parse01StringsHelper(tmp, 0, length, input,ret);
        return ret;
    }

//    https://en.wikipedia.org/wiki/Longest_common_substring_problem
//    http://www.geeksforgeeks.org/longest-common-substring/
//Keep only the last and current row of the DP table to save memory (O(\min(m, n)) instead of O(n m))
//    Store only non-zero values in the rows. This can be done using hash tables instead of arrays. This is useful for large alphabets.
    public int LCSubStr(String s1, String s2) {
        //assume s1, s2 of at least length of 1
        int ret = 0;
        int l1 = s1.length(), l2 = s2.length();
        int [][] table = new int [l1][l2];

        //init row
        for (int j = 0; j < l2; j++) {
            table[0][j] = s1.charAt(0) == s2.charAt(j) ? 1 : 0;
            ret = Math.max(ret, table[0][j]);
        }
        //init col
        for (int i = 1; i < l1; i++) {
            table[i][0] = s1.charAt(i) == s2.charAt(0) ? 1 : 0;
            ret = Math.max(ret, table[i][0]);
        }
        for (int i = 1; i < l1; i++)
            for (int j = 1; j < l2; j++)
                if (s1.charAt(i) == s2.charAt(j)) {
                    table[i][j] = 1 + table[i-1][j-1];
                    ret = Math.max(ret, table[i][j]);
                }

        return ret;
    }

//    Given a sorted array of floats, find the index of the number closest to x:
//    Example: {1.2, 2.5, 9.3} x = 5, return 1
    int closestToX(double [] array, int x) {
        if (array == null || array.length <= 1)
            return 0;

        //first first t such that a[t] <= target, a[t+1] > target, compare both diff in return if needed
        int l = -1, r = array.length;
        while (l + 1 != r) {
            int mid = l + (r - l) / 2;
            if (array[mid] > x)
                r = mid;
            else
                l = mid;
        }

        if (l == -1)
            return 0;
        else if (l == array.length - 1 || Math.abs(array[l]-x) < Math.abs(array[l+1]-x))
            return l;
        else
            return l+1;
    }

//    一段代码,用 string 表示的,写一个函数,返回所有的注释里面的内容,返回//和/* */里 的内容。
    //////
//    sdf//sdfsdf   <- no newline end with it..
    //Becareful about below tricks
//    /**/**/
//    /*/
    List<String> getComments(String input) {
        List<String> ret = new ArrayList<>();
        char pre = ' ';
        int mode = 0;//0 still search, 1 in //, 2 in /*
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            switch (mode) {
                case 0:
                    if (pre == '/') {
                        if(input.charAt(i) == '/') {
                            mode = 1;
                            sb.setLength(0);
                            pre = ' '; //for the case I mentioned above
                        } else if (input.charAt(i) == '*') {
                            mode = 2;
                            sb.setLength(0);
                            pre = ' '; //for the case I mentioned above
                        } else {
                            pre = input.charAt(i);
                        }
                    } else {
                        pre = input.charAt(i);
                    }
                    break;
                case 1:
                    if (input.charAt(i) == '\n') {
                        ret.add(sb.toString());
                        mode = 0;
                    } else {
                        sb.append(input.charAt(i));
                    }
                    break;
                default:
                    if (pre == '*' && input.charAt(i) == '/') {
                        sb.deleteCharAt(sb.length()-1);
                        pre = ' ';
                        ret.add(sb.toString());
                        mode = 0;
                    } else {
                        sb.append(input.charAt(i));
                        pre = input.charAt(i);
                    }
                    break;
            }
        }

        return ret;
    }


//    input n 个 tuples 每个 tuples 里有若干个 string, 让你生成全排列,从每个里取一个。
    //What if any of them is empty??? Suppse not right now...
    void generatePermutationHelper(List<List<String>> tuples, int index, List<String> tmp, List<List<String>> ret) {
        if (index >= tuples.size()) {
            ret.add(new ArrayList<>(tmp));
        } else {
            for (int i = 0; i < tuples.get(index).size(); i++) {
                tmp.set(index, tuples.get(index).get(i));
                generatePermutationHelper(tuples, index + 1, tmp, ret);
            }
        }
    }

    List<List<String>> generatePermutation(List<List<String>> tuples) {
        List<List<String>> ret = new ArrayList<>();
        List<String> tmp = new ArrayList<String>(Collections.nCopies(tuples.size(), ""));
        generatePermutationHelper(tuples, 0, tmp, ret);

        return ret;
    }

//    43, 一群人排队,每个人有(height, Tvalue), height 表示身高,Tvalue 表示 前面有几个比当前人身 高高的人。。然后顺序打乱,重新排队,还得复原以前的队列
    static class  HRankNode {
        int height;
        int tvalue;
        HRankNode(int h, int t) {
            height = h;
            tvalue = t;
        }
        HRankNode(HRankNode n) {
            height = n.height;
            tvalue = n.tvalue;
        }
    }

    static class HRankNodeComparator implements Comparator<HRankNode> {
        public int compare(HRankNode n1, HRankNode n2) {
            return n1.height - n2.height;
        }
    }
    HRankNode [] restoreHeightArray(HRankNode [] array) {
        //sort by height;
        //how to merge them back with low cost...
        Arrays.sort(array, new HRankNodeComparator());

//        System.out.println("*********************");
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i].height + "\t" + array[i].tvalue);
//        }

        HRankNode [] ret = new HRankNode [array.length];
        for (int i = 0; i < array.length; i++) {
            int c = 0;
            for (int k = 0; k < ret.length; k++) {
                if (ret[k] == null) {
                    if (c == array[i].tvalue) {
                        ret[k] = array[i];
                        break;
                    }
                    else
                        c++;
                }
            }
        }
        return ret;
    }

//  71  求最长等差数列.给定一个未排序数组,找出其中最长的等差数列
    //先问清楚这个sequence要不要保持原数组的order...
    //Too much tricks...
//    http://jeffe.cs.illinois.edu/pubs/pdf/arith.pdf
//For simplicity, we have assumed that the given set is sorted. We can always add a pre-processing step to first sort the set and then apply the below algorithms.
    //A very good explanation in below post....
//    http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/

//    http://codercareer.blogspot.com/2014/03/no-53-longest-arithmetic-sequence.html
    static class LongestArithmeticSequenceNode {
        int first;
        int second;
        LongestArithmeticSequenceNode(int f, int s) {
            first = f;
            second = s;
        }
     }
    int longestArithmeticSequence(int [] nums) {
        if (nums == null)
            return 0;
        int length = nums.length;
        if (length <= 2)
            return length;
        int ret = 2;
        HashMap<Integer, List<LongestArithmeticSequenceNode>> map = new HashMap<>();
        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++) {
                int diff = nums[i] - nums[j];
                if (!map.containsKey(diff)) {
                    map.put(diff, new ArrayList<>());
                }
                List<LongestArithmeticSequenceNode> list = map.get(diff);
                list.add(new LongestArithmeticSequenceNode(i,j));
            }

        for (List<LongestArithmeticSequenceNode> list : map.values()) {
            int [] tmp = new int [length];
            Arrays.fill(tmp, 1);
            for (int i = 0; i < list.size(); i++) {
                tmp[list.get(i).second] = tmp[list.get(i).first] + 1;
                ret = Math.max(ret, tmp[list.get(i).second]);
            }

        }
        return ret;
    }

    //we don't need to the order as in the original nums
    int lenghtOfLongestAP(int [] nums) {
        if (nums == null)
            return 0;
        int length = nums.length;
        if (length <= 2)
            return length;

        Arrays.sort(nums);
        //why 2???
        int ret = 2;
        int [][] table = new int [length][];
        for (int i = 0; i < length; i++)
            table[i] = new int [length];

        //init last col
        for (int i = 0; i < length; i++)
            table[i][length - 1] = 2;

        for (int j = length - 2; j > 0; j--) {
            int i = j - 1, k = j + 1;
            while (i >= 0 && k < length) {
                if (nums[i] + nums[k] < 2 * nums[j])
                    ++k;
                else if (nums[i] + nums[k] > 2 * nums[j]) {
                    table[i][j] = 2;
                    --i;
                } else {
                    table[i][j] = table[j][k] + 1;
                    ret = Math.max(ret, table[i][j]);
                    --i;
                    ++k;
                }
            }
            while (i >= 0) {
                table[i][j] = 2;
                --i;
            }
        }
        return ret;
    }

//    find reverse pair in two arrays
//    There are two arrays A1 and A2. Find out the pairs of numbers in A2 which were in reverse order in A1. For ex.
//    A1 = 2 6 5 8 1 3 A2 = 1 2 3 4 5 6 Answer: 1 & 2 5 & 6

    //THis is a different questions.... Too simple compared with this question...
//    http://www.geeksforgeeks.org/counting-inversions/

//  thought 1.  对第二个数组作value, index的hash，复杂度受限于输出的规模O(n^2)，比如两个数组,数字都一样，一个升序一个降序， 所有的pair都打出来也要O(n^2)
//  thought 2.  可以先得到第一个数组里元素的rank list，做成hashset，然后遍历一遍第二个数组，按顺序把出现的第一个数组的元素的rank记录下来：
//    int *rank_b = new int[len];
//    int index = 0;
//    for(int i = 0; i < len; i++){
//        if(rank_a[b[i]]){
//            rank_b[index++] = rank_a[b[i]];
//        }
//    }
//    接下来对这个数组做归并排序（类似找逆序数的算法），即可得到所有reversed pairs.
//    如果只是得到所有reversed pairs的数量的话，O(n*logn)就可以了，如果需要得到所有的pairs，算上拷贝的时间，是O(n^2).
    //就是先把array1里面的数的index记下来(如果不unique了???)
    //建立一个新的数组,把数组2在里面的index找到,然后count reverse numbers.. (O(nlogn)..Only if unique)
    int findReversePairs(int [] array1, int [] array2) {
        return 0;
    }

//    Fill tank
//    you are given following:
//            1. An empty tank
//    ￼2. Unlimited source of water. 3. Some container of certain measurements and a container of 1 litre is always given.
//    Your job is to fill the tank from source of water using the containers in minimum number of steps. You cant fill the container with a small amount of water than its size (filling partially is not allowed). Find the number of steps and print the solution.
//            e.g.
//    Tank Size: 80 litre
//    Containers: 1,3,5,6,25 litre
//    Logger:
//            4
//            5,25,25,25
//    Tank Size: 71 litre
//    Containers: 1,3,5,6,25 litre
//    Logger:
//            6
//            3,6,6,6,25,25
    //DP no brainer, no negative value is allowed
//    O(lengthOf(tanks) * target)
    //becareful that tanks might containt value > target...or duplcates..
    int fillTank(int [] tanks, int target) {
        if (target <= 0)
            return 0;

        int [] targets = new int [target + 1];
        //duplicates are fine...,
        for (int i = 0; i < tanks.length; i++)
            if (tanks[i] <= target)
                targets[tanks[i]] = 1;

        for (int i = 1; i <= target; i++) {
            if (targets[i] != 1) {
                for (int k = 0; k < tanks.length; k++) {
                    if (tanks[k] <= target && i - tanks[k] > 0) {
                        if (targets[i] == 0)
                            targets[i] = 1 + targets[i - tanks[k]];
                        else
                            targets[i] = Math.min(targets[i], 1 + targets[i - tanks[k]]);
                    }
                }
            }
        }
        return targets[target];
    }

//    You need to organize a football tournament.
//    There are n teams given. You need to prepare a schedule for the matches so that each team plays with every other team and on the same day no team plays twice. You want to finish the tournament as early as possible.Give schedule for tournament.
//    https://en.wikipedia.org/wiki/Round-robin_tournament
//If n is the number of competitors, a pure round robin tournament requires \begin{matrix} \frac{n}{2} \end{matrix}(n - 1) games. If n is even, then in each of (n - 1) rounds, \begin{matrix} \frac{n}{2} \end{matrix} games can be run concurrently, provided there exist sufficient resources (e.g. courts for a tennis tournament). If n is odd, there will be n rounds, each with \begin{matrix} \frac{n - 1}{2} \end{matrix} games, and one competitor having no game in that round.
//Instead of rotating one position, any number relatively prime to (n-1) will generate a complete schedule.

//    Write a program to return the longest repeating substring in a string.
//            eg. for "ababab", "abab" is the longest repeating substring.
    String longestRepeatingSubString(String input) {
        if (input == null || input.length() <= 1)
            return "";

        String [] array = new String[input.length()];
        for (int i = 0; i < input.length(); i++)
            array[i] = input.substring(i);
        Arrays.sort(array);
        int start = 0, length = 0;
        String last = array[0];
        for (int i = 1; i < array.length; i++) {
            int k = 0;
            for (k = 0; k < last.length() && k < array[i].length(); k++) {
                if (last.charAt(k) != array[i].charAt(k))
                    break;
            }
            if (length < k) {
                length = k;
                start = i;
            }
            last = array[i];
        }
        return array[start].substring(0, length);
    }

//    1. 系统设计：给一个url和一个给定的api可以返回所有从这个url可以直接链接到的url。要求统计所有能访问到url数。结果先让我coding，我以为搞错了，后来coding完了，followup就是怎么解决scalable的问题，给定的那个API有什么问题以及怎么改进（最后引申到设计web crawler），怎么解决url无效等等问题。
    /*
    int getAccess(url) {
        if (url == null)
            return 0;
        HashMap<URL> map = new HashMap<>();
        ArrayDeque<> q = new ArrayDeque<>();
        map.add(url);
        q.addLast(url);
        while (!q.isEmpty()) {
            Url url = q.removeFirst();
            List<URL> his = getHist(url);
            Iterator<URL> iter = his.iterator();
            while (iter.hasNext()) {
                URL tmp = iter.next();
                if (!map.contains(tmp)) {
                    map.add(tmp);
                    q.addLast(tmp);
                }

            }
        }
        return map.size();
    }

     */

    private void intSwap(int []nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void sortColors(int[] nums) {
        int r = 0, b = nums.length;
        int i = 0;
        while (i < b) {
            if (nums[i] == 0) {
                intSwap(nums, i++, r++);
            } else if (nums[i] == 2) {
                //swap end
                intSwap(nums, i, --b);
            } else {
                ++i;
            }
        }
    }

//    2维坐标找与给定点相邻的点，fellow up k 维坐标找与给定点相邻的点。. Waral 鍗氬鏈夋洿澶氭枃绔�,
//    比如二维下给定（0，0）这个点，那么相邻的点有（-1，-1），（-1， 0）， （-1， 1），（0， -1），（1， -1），（1，0），（1， 1），（0，1）这8个点
//    fellow up一开始没什么思路，小哥给了个提示，让我算3维和4维的个数，我报给他答案后又沉默了差不多有3，4分钟（想想真是笨，都提示得那么明显了），然后想到了用divide and conquer，然后简单解释了一下

    private void neiborhoodsDFS(List<List<Integer>> ret, Integer nums[], int level, int [] directions, boolean isChanged) {
        if (level == nums.length) {
            if (isChanged) {
                ret.add(new ArrayList<>(Arrays.asList(nums)));
            }
        } else {
            for (int i = 0; i < directions.length; i++) {
                nums[level] += directions[i];
                neiborhoodsDFS(ret, nums, level+1, directions, isChanged || (directions[i] != 0));
                nums[level] -= directions[i];
            }
        }
    }
    List<List<Integer>> neiborhoods(Integer nums[]) {
        List<List<Integer>> ret = new ArrayList<>();
        //k dimention, k >= 2

        int [] directions = {-1, 0, 1};

        neiborhoodsDFS(ret, nums, 0, directions, false);
        return ret;
    }

    /*
    public class Logger {
    public List<int[]> solve(int[] p) {
        int k = p.length;
        int[] directions = {-1, 1};
        LinkedList<int[]> ans = new LinkedList<>();
        ans.offer(Arrays.copyOf(p, k));
        for (int i = 0; i < k; ++i) {
            int n = ans.size();
            for (int j = 0; j < n; ++j) {
                for (int d: directions) {
                    int[] copy = Arrays.copyOf(ans.get(j), k);
                    copy[i] += d;
                    ans.offer(copy);
                }
            }
        }
        ans.pollFirst();
        return ans;
    }
}
     */

//    “3a2[mtv]ac”,  decompress to: aaamtvmtvac，括号可以嵌套。
//    这个我觉得不是很难，大概花了15分钟理清了思路并写好了代码，大概就是找匹配括号递归解，面试官也找不到bug表示认同。
//
//    但吊诡的地方来了，面试官说把这种字符串compress回去...这显然有多种情况，于是我问是不是要求压缩后最短，面试官说肯定越短越好。
//    比如对于aaaa, 肯定4a比2[aa]好。
//    我思考了一会，只想到了枚举所有substring及其连续出现次数，然后选择match出现次数最多的substring作为压缩。
//    面试官觉得复杂度高了，问还能不能优化，感觉他自己语气也不是很肯定，提示了一下我有没有类似two pointer的解法。
//    我个人觉得这道题真心不简单，没什么想法，一直卡到了这轮结束.
    String deCompressString(String input) {
        //no digits is allowed??
        //no [] are allowed in source?
        //can we have empty []
        // [] without numbers beforehead??
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int counter = 0;
        while (i < input.length() && input.charAt(i) != '[') {
            if (Character.isAlphabetic(input.charAt(i))) {
                if (counter != 0) {
                    char tmp [] = new char[counter];
                    Arrays.fill(tmp, input.charAt(i));
                    sb.append(tmp);
                } else {
                    sb.append(input.charAt(i));
                }
                ++i;
                counter = 0;
            } else {
                int j = i;
                while (j < input.length() && Character.isDigit(input.charAt(j)))
                    ++j;
                counter = Integer.parseInt(input.substring(i,j));
                i = j;
            }

        }
        if (i != input.length()) {
            int first = input.indexOf('[');
            int last = input.lastIndexOf(']');
            String middle = deCompressString(input.substring(first+1, last));
            while (counter > 0) {
                sb.append(middle);
                --counter;
            }
            String lastS = deCompressString(input.substring(last + 1));
            sb.append(lastS);
        }

        return sb.toString();
    }

//    有一个double类型的数组， 找满足 [a, a + 1) 的最长序列含有的元素的个数， eg. [ 1.0 ,1.3 ,1.5 ,2.3, 3.5],  最长的是[1.0 1.3 1.5], 应该返回3。这种小学5年级的数学（数组操作）题对我来说是很困难的， 我直接说暴力解法呗， 然后面试官提醒了下用greedy的方法。 然而代码还是写的很艰难。。。大家可以自己写写O(n)的方法练习一下。
    boolean isSatisfied(double a, double b) {
        return a <= b && b < a + 1;
    }
    int longestDoubleSeqs(double [] input) {
        if (input == null)
            return 0;
         if (input.length <= 1)
            return input.length;
        int ret = 1;
        int start = 0;
        int i = 1;
        while (i < input.length) {
            while(!isSatisfied(input[start], input[i]))
                ++start;
            ++i;
            ret = Math.max(ret, i - start);
        }

        return ret;
    }

//    完全二叉树 parent是孩子中的最小值，请找出整棵树第二小的值
//    example:
//            *     2
//            *    / \
//            *   2   3
//            *  / \  | \
//            * 4   2 5  3. From 1point 3acres bbs
//
//    * In this given tree the answer is 3.
//    解法 一开始装傻提出O(n),两分钟后给出了O(logn)的解法
//    解法是follow up的 多叉树 这题面试官给了我3.0分. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
//    Public class Logger {
//        Public int findSecond(TreeNode root) {
//
//            If (root.children.size() == 0) return Integer.MAX_VALUE;. 1point3acres.com/bbs
//            Int currSeond = 0;
//            TreeNode minNode = null;
//            TreeNode second = null;
//            For (int i =0; i < root.children.size(); i++ ) {-google 1point3acres
//                If (root.children.val = root.val) {
//                    minNode = root.children;
//                } else {
//                    if (second == null) {second = root.children;}
//                    else {
//                        Second = second.val < root.children ? second : root.children;}. more info on 1point3acres.com
//                }
//            }
//            return Math.min(findSecond(minNode), second.val);
//        }
//    }

    /*
    //My solution
    int findSecond(TreeNode root) {
        //assume root non-null here
        if (root.children.size() <= 1)
            //You are the only one. Who unfornately holds the min vals, so return MAX here
            return Integer.MAX_VALUE;

        TreeNode minNode = null;
        TreeNode second = null;
        for (int i = 1; i < root.children.size(); ++i) {
            //how to handle multiple cases
            if (root.val == root.children.get(i).val) {
                if (minNode == null)
                    minNode = root.children.get(i);
                else
                    return root.val;
            } else {
                if (second == null || second.val > root.children.get(i).val)
                    second = root.children.get(i);
            }
        }
        //come here, minNode and second are different
        return Math.min(findSecond(minNode), second);
    }
     */

//    big integer 的加法 这里我一开始没有问清楚 没有考虑负数的问题 导致跳坑一下子跳不出来了 但还是把正数加法做了出来 出现负数的也弄了差不多 就是整合出了一点问题

//    给一组有向图的边的array，找A->B同时B->A，这样两个点之间直接有两条边相连，符合这样条件的边的条数。比如A->B,B->A,A->C那么边的条数就是2
//    LZ说用hashset解决，把to Node value + "#" + from Node value, 存进set里面，下次如果from node value + "#" + to node value已经在set中存在了，说明找到了一对，则边数+2
//    follow up是如果图很大，边很多，可以用多台machine，该怎么办。LZ说把边array分成几段，每段交给一个machine处理，得到的hashset和num数再merge。姐姐又问怎么才能更好的切分这个数组，如果直接分的话，每个machine返回的hashset会很大，并起来的时候还是很大。姐姐提示了好多LZ还是没想出来，最后姐姐说可以对每组边算个hashcode,然后像bucket sort那样分给一个相应的machine处理
//You can just splits the array in parts so that they don't need to communicate again except the final results..


    //print all prime factor of a number n, eg. 96 -> 2 2 2 2 2 3
    void printPrimeFactor(int n) {
        if (n <= 1)
            return;
        /*
        int tmp [] = new int [n+1];
        int mid = (int) Math.sqrt(n);

        tmp[0] = 1;
        tmp[1] = 1;
        int i = 2;
        while (i <= mid) {
            if (tmp[i] == 0) {
                //start from whom
                int k = i;
                while (true) {
                    int prod = k++ * i;
                    if (prod <= tmp.length)
                        tmp[prod] = 1;
                    else
                        break;
                }
                int t = n;
                while (t % i == 0) {
                    t /= i;
                    System.out.print(i + "\t");
                }
            }
            ++i;
        }
        System.out.println();
        */
        List<Integer> [] tmp = new List[n+1];
        for (int i = 2; i < tmp.length; ++i)
            tmp[i] = new ArrayList<>();

        int mid = (int) Math.sqrt(n);
        int i = 2;
        while (i <= mid) {
            if (tmp[i].size() == 0) {
                int k = 2;
                while (true) {
                    int prod = k++ * i;
                    if (prod <= n)
                        tmp[prod].add(i);
                    else
                        break;
                }
            }
            ++i;
        }
        if (tmp[n].size() == 0)
            System.out.println(n);
        else {
            int k = n;
            for (i = 0; i < tmp[n].size() && k > 1; ++i) {
                while (k > 1 && k % tmp[n].get(i) == 0) {
                    System.out.print(tmp[n].get(i) + "\t");
                    k /= tmp[n].get(i);
                }
            }
            System.out.println();
        }
    }

//    给一个string，只含有a和b,a可以变成b,b可以变成a,也可以不操作，返回操作次数最少就可以得到的sort的string
    int convertSortedString(String s) {
        int length = s.length();
        if (length <= 1)
            return length;
        //I assume we want aaa...bbb, ret can be all a or all b
//        let A[n] ends with a, B[n] ends with b
        //We can improve it without array....
        int [] a = new int [1+length];
        int [] b = new int [1+length];
        for (int i = 1; i <= length; ++i) {
            if (s.charAt(i-1) == 'a') {
                a[i] = a[i-1];
                b[i] = 1 + Math.min(a[i-1], b[i-1]);
            } else {
                a[i] = 1 + a[i-1];
                b[i] = Math.min(a[i-1], b[i-1]);
            }
        }

        return Math.min(a[length], b[length]);
    }


    //There will be at least two digit that is the same in the number
    //wired, int could not suppor the range, let's use String for now
    String LargestNumberAferRemovingOneDigit(String x) {

        //find consective group first...


        //if replace with a character but the later c is lager, wait till last chacne..
        // see example, 2227543339
        // 1. 227543339
        // 2. 222754339  <- clearly stays last win


        //For got sake, you need return the largest one instead of the smallest one......
        /*
        //return the smallest one
        int i = 0;
        int last = -1;
        //SB, you should remove from the first one. not for the first one if you want minimum...
        while (i < x.length()) {
            int j = i;
            while (j < x.length() && x.charAt(i) == x.charAt(j))
                ++j;
            if (j - i > 1) {
                //we have in domain, [i,j)
                if (j < x.length()) {
                    if (x.charAt(j) < x.charAt(i)) {
                        //here it is
                        //remove i
                        return x.substring(0,i) + x.substring(i+1);
                    } else {
                        //this is lager
                        last = i;
                    }
                } else {
                    //this is the last one. Remove it.....
                    return x.substring(0, x.length()-1);
                }
            }
            i = j + 1;
        }
        return x.substring(0,last) + x.substring(last+1);
        */

        //let's return largest one...

        int i = 0;
        int last = -1;
        //Bugar??? Remove from the last one, not the first one.
        //what if all same/??
        while (i < x.length()) {
            int j = i;
            while (j < x.length() && x.charAt(i) == x.charAt(j))
                ++j;
            if (j - i > 1) {
                //we have in domain, [i,j)
                if (j < x.length()) {
                    if (x.charAt(j) > x.charAt(i)) {
                        //here it is
                        //remove i
                        return x.substring(0,i) + x.substring(i+1);
                    } else {
                        //this is lager
                        last = i;
                    }
                } else {
                    //remove from the first sequence.
                    break;
                }
            }

            //becareful that x[j] != x[i] here now.
            i = j;
        }
        if (last != -1)
            return x.substring(0,last) + x.substring(last+1);
        else
            return x.substring(1);
    }

    public boolean isSumOfTwoSquares(int n){
        if (n < 0)
            return false;
        if (n <= 2)
            return true;
        int last = (int)Math.sqrt(n);
        //As perfect number is not that much, you can prepare it first..
        for (int i = 0; i <= last; ++i) {
            int remaing = n - i*i;
            int j = (int)Math.sqrt(remaing);
            if (j*j == remaing)
                return true;
        }
        return false;
    }


    //input是一个 integer数组, 求smallest  subset sum bigger than target，target是全部数字求和的1%
    //They are asking 最少的elements达到和的水平...
    //what if all 0 ?????
    //pre: target must be satisfied by all ele together????, not necessary


    /*  //Basic idea....
    public List<Integer> minSubSet(int[] nums){
                List<Integer> list = new ArrayList<>();
                int sum = 0;
                for(int num : nums){
                        list.add(num);
                        sum += num;
                }
                int target = sum / 100;
                return helper(list, target);
        }

        public List<Integer> helper(List<Integer> list, int target){
                int size = list.size();
                Random random = new Random();
                int n = list.get(random.nextInt(size));
                List<Integer> biglist = new ArrayList<>();
                List<Integer> smalllist = new ArrayList<>();
                int sum = 0;
                for(int num : list){
                        if(num > n){
                                biglist.add(num);
                                sum += num;
                        }else{
                                smalllist.add(num);
                        }
                }
                if(sum > target){
                        if(biglist.size() == 1) return biglist;
                        return helper(biglist, target);
                }else{
                        List<Integer> tmp = helper(smalllist, target - sum);
                        for(int num : tmp){
                                biglist.add(num);
                        }
                        return biglist;
                }
        }
     */

    //Your input is guaranteed that the total sum can be satisfied by the target, Sum(i) >= target...
    int minSubSet(int [] nums, int start, int end, double target) {
        //This is not possible as long as you don't give me bad input as beginning.
//        if (end == start)
//            return -1;

        //at least one element below...
        double total = 0;
        for (int i = start; i < end; ++i)
            total += nums[i];
        //not possible.
        if (total < target)
            return -1;

        //one element
        if (start + 1 == end)
            return 1;

        int mid = start + (end - start)/2;
        //splits by half???
        //petition
        int key = nums[mid];
        int l = start-1, r = end;
        int i = start;
        while (i < r) {
            if (nums[i] < key) {
                nums[++l] = nums[i++];
            } else if (nums[i] > key) {
                intSwap(nums, i, --r);
            } else {
                ++i;
            }
        }
        //You can do it like above to save us some trouble....
        int right = minSubSet(nums, mid, end, target);
        if (right != -1)
            return right;

        //Because this is always possible...
        //figure out right sum
        for (i = mid; i < end; ++i)
            target -= nums[i];
        return minSubSet(nums, start, mid, target);
    }
    public int minSubSet(int[] nums){
        //????
        //assume this array are not all 0s.
        if (nums.length <= 1)
            return nums.length;

        //we need becareful about overflow... etcs.
        double total = 0;
        for (int i = 0; i < nums.length; ++i)
            total += nums[i];
        total /= 100;

//        return minSubSet(nums, total);
        return 0;
    }


//    是两倍的chain比如1 2 4，找最长的chain。Let's do it in O(n)
    public int maxDoubleChain(int[] nums){
        //Skip 0.....
        HashMap<Integer, Integer> set = new HashMap<>();
        for (int i : nums) {
            if (i != 0)
                set.put(i, 1);
        }

        int ret = 0;
        for (int i : nums) {
            //or you can add visit map incase to many duplicates
            if (i != 0 && set.get(i) == 1) {
                //scale down first....
                //This is an even number, always...
                while (i != 0 && ((i & 0x01) == 0) && set.containsKey(i>>1)) {
                    i >>= 1;
                }
                //scale back into map
                i <<= 1;
                while (set.containsKey(i)) {
                    set.put(i, 1 + set.get(i>>1));
                    ret = Math.max(ret, set.get(i));
                    i <<= 1;
                }
            }
        }


        return ret;
    }


    boolean canSplitByk(int [] nums, int k) {
        //sanity check with nums.length and k
        if (k <= 0 || nums.length == 0)
            return false;
        if (k == 1)
            return true;
        if (nums.length % k != 0)
            return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : nums) {
            if (map.containsKey(i))
                map.put(i, 1 + map.get(i));
            else
                map.put(i, 1);
        }
        while (!map.isEmpty()) {
            //poll out k
            int minKey = map.firstKey();
            for (int i = 0; i < k; ++i) {
                if (map.containsKey(minKey + i)) {
                    if (map.get(minKey + i) == 1)
                        map.remove(minKey + i);
                    else
                        map.put(minKey + i, map.get(minKey + i)-1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String [] args) {
        Solution s = new Solution();

//        String ss = "I have a \"faux coat\"";
//        String ss = "anc\"\"sdf";
//        System.out.println(s.findWords(ss));

//        int [][] array = {{1,2,3,4}, {4,5,6,7}, {7,8,9,2}};
//        for (int a : s.ConvertTwoDimention(array))
//            System.out.println(a);

//        String input = "???";
//        for (String i : s.parse01Strings(input)) {
//            System.out.println(i);
//        }

//        String s1 = "ABAB", s2 = "BABA";
//        System.out.println(s.LCSubStr(s1,s2));

//        double [] array = {1.2, 2.5, 9.3};
//        for (int x = 0; x <= 10; x++)
//            System.out.println(x + "\t" + s.closestToX(array,x));

//        String input = "sdf//sdfsdf\n/*aa*/*cc*/";
//        for (String ss : s.getComments(input)) {
//            System.out.println(ss);
//        }

//        List<String> a1 = Arrays.asList("xyz1", "abc1");
//        List<String> a2 = Arrays.asList("xyz2", "abc2", "isdf2");
//        List<String> a3 = Arrays.asList("xyz3", "abc3", "hijsdf3");
//        List<List<String>> tuples = Arrays.asList(a1, a2, a3);
//        for (List<String> l: s.generatePermutation(tuples)) {
//            for (String ss : l) {
//                System.out.print(ss + "\t");
//            }
//            System.out.println();
//        }

        /*
        int N = 14, maxHeight = 100;
        Random random = new Random();
        HRankNode [] ranks = new HRankNode[N];
        for (int i = 0; i < N; i++) {
            ranks[i] = new HRankNode(random.nextInt(maxHeight) + 1, 0);
        }
        for (int i = 1; i < N; i++) {
            int c = 0;
            for (int j = 0; j < i; j++)
                if (ranks[j].height > ranks[i].height)
                    c++;
            ranks[i].tvalue = c;
        }
        for (int i = 0; i < N; i++) {
            System.out.println(ranks[i].height + "\t" + ranks[i].tvalue);
        }

        System.out.println("*********************");

        HRankNode [] ranks2 = new HRankNode[N];
        for (int i = 0; i < ranks.length; i++)
            ranks2[i] = new HRankNode(ranks[i]);

        List<HRankNode> list = Arrays.asList(ranks2);
        Collections.shuffle(list);

        ranks2 = (HRankNode []) list.toArray();
//        for (int i = 0; i < N; i++) {
//            System.out.println(ranks2[i].height + "\t" + ranks2[i].tvalue);
//        }
        for (HRankNode n2 : s.restoreHeightArray(ranks2))
            System.out.println(n2.height + "\t" + n2.tvalue);
        */

//        int N = 10, maxVal = 20;
//        Random random = new Random();
//        int [] array = new int[N];
//        for (int i = 0; i < N; i++) {
//            array[i] = random.nextInt(maxVal) + 1;
//        }
//        for (int i = 0; i < N; i++) {
//            System.out.print(array[i] + "\t");
//        }
//        System.out.println();

//        int [] nums = {1, 6, 3, 5, 9, 7};
//        System.out.println(s.lenghtOfLongestAP(nums));
//        System.out.println(s.longestArithmeticSequence(nums));

//        int [] tanks = {1,3,5,6,25};
//        int target = 80;
//        int [] tanks = {1,3,5,6,25};
//        int target = 71;
//        System.out.println(s.fillTank(tanks, target));

//        System.out.println(s.longestRepeatingSubString("abcbd"));

//        Integer nums[] = {0,0,0};
//        for (List<Integer> l : s.neiborhoods(nums)) {
//            for (Integer i : l)
//                System.out.print(i + "\t");
//            System.out.println();
//        }

//        System.out.println(s.deCompressString("3[a2[mtv]a]c"));
//        System.out.println(s.deCompressString("2[mtv]"));
//        double input [] = { 1.0 ,2.3, 3.5};
//        System.out.println(s.longestDoubleSeqs(input));
//        s.printPrimeFactor(96);
//        System.out.println(s.convertSortedString("aaab"));
//        System.out.println(s.convertSortedString("baab"));
//        System.out.println(s.convertSortedString("abab"));
//        System.out.println(s.convertSortedString("bbba"));
//        System.out.println(s.convertSortedString("baba"));


//        String x = "23332226";
//        System.out.println(s.LargestNumberAferRemovingOneDigit(x));

//        int n = 15;
//        System.out.println(s.isSumOfTwoSquares(n));

//        int[] nums = {1,2,3,4,5,6,7};
//        System.out.println(s.maxDoubleChain(nums));

        int [] nums = {1,2,3,4,5,2,3,4,5,6,5,6,7,8,10 };
        int k = 5;
        System.out.println(s.canSplitByk(nums, k));
    }
}
