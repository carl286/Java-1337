package com.l1337.l353;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */


//http://www.cnblogs.com/grandyang/p/5558033.html
class Snake {

    private int width;
    private int height;
    HashSet<Integer> set;
    LinkedList<Integer> snake;
    HashMap<Integer, Integer> food;

    private int getKey(int h, int w) {
        return h*width+w;
    }
    private int getW(int key) {
        return key%width;
    }
    private int getH(int key) {
        return key/width;
    }

    public Snake(int width, int height, List<int[]> food) {
        this.width = width;
        this.height = height;
        set = new HashSet<>();
        snake = new LinkedList<>();

        int initW = 0, initH = 0;
        int key = getKey(initW, initH);
        set.add(key);
        snake.add(key);

        //food might be placed on the same place several times...
        this.food = new HashMap<>();
        for (int i = 0; i < food.size(); ++i) {
            key = getKey(food.get(i)[0], food.get(i)[1]);
            if (this.food.containsKey(key)) {
                this.food.put(key, this.food.get(key)+1);
            } else {
                this.food.put(key, 1);
            }
        }
    }

    /**
     * @param direction U = Up, L = Left, R = Right, D = Down
     * @return The game's score after the move. Return -1 if game over.
     */
    public int move(char direction) {
        //The score will the snake.size()-1

        //Take the head
        int head = snake.get(0);
        //calculate next point
        int w = getW(head);
        int h = getH(head);

        //get collision
        //cross boarder
        if (w < 0 || h < 0 || w >= width || h >= height)
            return -1;

        //touch the food????
        int key = getKey(h,w);
        if (food.containsKey(key)) {
            if (food.get(key) == 1)
                food.remove(key);
            else
                food.put(key, food.get(key)-1);
            //update snake
            snake.add(key);
            set.add(key);
            return snake.size()-1;
        }


        //check collision
        //remove tail
        int tail = snake.remove(snake.size()-1);
        set.remove(tail);

        if (set.contains(key))
            return -1;

        set.add(key);
        snake.add(key);
        return snake.size()-1;
    }
}
