package com.l1337.l832;


public class Solution {

    private void swap(int [] data, int i, int j)
    {
        if (i != j)
        {
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }
    }
    public int[][] flipAndInvertImage(int[][] image) {
        for(int i = 0; i < image.length; ++i)
        {
            int l = 0, r = image[i].length - 1;
            while (l <= r)
            {
                if (l == r)
                {
                    image[i][l] = 1 - image[i][l];
                }
                else
                {
                    image[i][l] = 1 - image[i][l];
                    image[i][r] = 1 - image[i][r];
                    swap(image[i], l, r);
                }
                ++l;
                --r;
            }

        }

        return image;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
