package com.googlePrep;

import java.util.Arrays;

public class CocaMachine {
    /*
    这 轮是最难的,可以说是唯  道hard级别的题吧,所以我特意设置低 点的积分,希望  多的 能看到。 可乐饮 机,有 系 按钮,每个按钮按下去会得到 定体积范围的可乐。先给定 个  标体积范围,问 限制按按钮次数,能否确定 定能得到 标范围内的可乐? 举 :有三个按钮,按 下去得到的范围是[100, 120], [200, 240], [400, 410], 假设 标是[100, 110], 那答案是 能。因为按下 , 可能得到120体积的可乐, 在 标范围 。 假设 标是[90, 120],那答案是可以。因为按下 , 定可 以得到此范围内的可乐。 假设 标是[300, 360], 那答案是可以,因为按下 再按 , 定可以得到此范 围内 假设 标是[310, 360], 那答案是 能,因为按下 再按 ,有可能得到300,永远没可能确定得到这 个范围内的可乐。 假设 标是[1, 9999999999],那答案是可以。随 按 个都确定满 此范围。 lz两眼  懵,真的做的快出汗 , 直试图跟 试官讲  的思 ,然后发现每个思 都有问题,最后在 试官 提示下想到 dp,然后 在提示下想到 recursion,最后终 条件实在是快想 出来 疯狂举 要hint, 最后终于被 试官带着做出来 。 这道题真的很有趣,但是当时脑 很僵 是最后 轮,感觉很崩。.
     */

    private boolean dfs(int [][] machines, int [] asked) {
        for (int i = 0; i < machines.length && machines[i][1] <= asked[1]; ++i) {
            if (machines[i][0] >= asked[0])
                return true;
            if (machines[i][1] - machines[i][0] <= asked[1] - asked[0]) {
                asked[1] -= machines[i][1];
                asked[0] -=  machines[i][0];

                if (dfs(machines, asked))
                    return true;
                asked[1] += machines[i][1];
                asked[0] +=  machines[i][0];
            }

        }

        return false;
    }

    public boolean isPossible(int [][] machines, int [] asked) {
        //sort by end
        Arrays.sort(machines, (a, b) -> (a[1] - b[1]));

        return dfs(machines, asked);
    }

    public static void main(String [] args) {
        CocaMachine coca = new CocaMachine();
        System.out.println(coca.isPossible(new int [][]{{100,120}, {200,240}, {400,410}}, new int []{310,360}));

    }
}
