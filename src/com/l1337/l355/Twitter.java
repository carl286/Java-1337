package com.l1337.l355;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

/* eg:
Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
 */
class Tweet {
    static long system_id = 0;
    long time;
    int tweetId;
    Tweet(int tweetId) {
        this.tweetId = tweetId;
        time = ++system_id;
    }
}

//should all changes
public class Twitter {

    HashSet<Integer> set;
    //we can limit this tweets size in future.
    HashMap<Integer, List<Tweet>> tweetLists;
    HashMap<Integer, Set<Integer>> followLists;
    /** Initialize your data structure here. */
    public Twitter() {
        this.set = new HashSet<>();
        this.tweetLists = new HashMap<>();
        this.followLists = new HashMap<>();
    }

    /** Compose a new tweet. */
    private void createUser(int userId) {
        if (!set.contains(userId)) {
            set.add(userId);
            tweetLists.put(userId, new ArrayList<>());
            followLists.put(userId, new HashSet<>());
        }
    }
    public void postTweet(int userId, int tweetId) {
        createUser(userId);
        tweetLists.get(userId).add(new Tweet(tweetId));
    }

    private void addToPq(PriorityQueue<Tweet> pq, int maxSize, Tweet tweet) {
        if (pq.size() < maxSize)
            pq.add(tweet);
        else if (pq.peek().time < tweet.time) {
            pq.poll();
            pq.add(tweet);
        }
    }
    private List<Tweet> getSubList(int userId, int maxListSize) {
        List<Tweet> l = tweetLists.get(userId);
        if (l.size() <= maxListSize)
            return l;
        else
            return l.subList(l.size() - maxListSize, l.size());
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ret = new ArrayList<>();
        if (set.contains(userId)) {
            //merge lists
            PriorityQueue<Tweet> pq = new PriorityQueue<>(new Comparator<Tweet>() {
                @Override
                public int compare(Tweet o1, Tweet o2) {
                    if (o1.time > o2.time)
                        return +1;
                    else if (o1.time < o2.time)
                        return -1;
                    else
                        return 0;
                }
            });
            for (Tweet tweet : getSubList(userId, 10)) {
                addToPq(pq, 10, tweet);
            }
            for (Integer uId : followLists.get(userId)) {
                for (Tweet tweet : getSubList(uId, 10)) {
                    addToPq(pq, 10, tweet);
                }
            }
            while (!pq.isEmpty()) {
                Tweet tweet = pq.poll();
//                System.out.println(tweet.time + "\t" + tweet.tweetId);
                ret.add(tweet.tweetId);
            }
            Collections.reverse(ret);
        }
        return ret;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId)
            return;
        if (!set.contains(followerId))
            createUser(followerId);
        if (!set.contains(followeeId))
            createUser(followeeId);
        followLists.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (set.contains(followerId) && set.contains(followeeId)) {
            followLists.get(followerId).remove(followeeId);
        }
    }

    void printTweets(List<Integer> tweets) {
        for (Integer i : tweets)
            System.out.print(i + "\t");
        System.out.println();
    }
    public static void main(String [] args) {
        Twitter twitter = new Twitter();

        /*
// User 1 posts a new tweet (id = 5).
        twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
        for (Integer i : twitter.getNewsFeed(1))
            System.out.print(i + "\t");
        System.out.println();

// User 1 follows user 2.
        twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
        twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        for (Integer i : twitter.getNewsFeed(1))
            System.out.print(i + "\t");
        System.out.println();

// User 1 unfollows user 2.
        twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
        for (Integer i : twitter.getNewsFeed(1))
            System.out.print(i + "\t");
        System.out.println();
        */
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 101);
        /*
        twitter.postTweet(1, 13);
        twitter.postTweet(1, 10);
        twitter.postTweet(1, 2);
        twitter.postTweet(1, 94);
        twitter.postTweet(1, 505);
        twitter.postTweet(1, 333);
        */
        twitter.printTweets(twitter.getNewsFeed(1));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */