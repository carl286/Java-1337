package com.l1337.l1236;


public class Solution {
    /*
    Web Crawler

    Given a url startUrl and an interface HtmlParser, implement a web crawler to crawl all links that are under the same hostname as startUrl.

Returns all urls obtained by your web crawler in any order.

Your crawler should:

Start from the page: startUrl
Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
Do not crawl the same link twice.
Only the links that are under the same hostname as startUrl should be explored by the crawler

As shown in the example url above, the hostname is example.org. For simplicity sake, you may assume all urls use http protocol without any port specified.

The function interface is defined like this:

interface HtmlParser {
public:
  // Returns a list of urls contained in url .
  public List<String> getUrls(String url);
}

Below there are two examples explaining the functionality of the problem, for custom testing purposes you'll have 3 variables urls, edges and startUrl. Notice that you will only have access to startUrl, while urls and edges are secret to you on the rest of the testcases.

     */

    /*
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {

    }
    */

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
