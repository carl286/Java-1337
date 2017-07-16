package com.examples.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherPatternTest {

    public void matchTest(String input) {
        String patternStr = "\\$\\{(.*)\\}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            String missedSubstitution = matcher.group(1);
            System.out.println(missedSubstitution);
        } else {
            System.out.println("Empty");
        }
    }

    public static void main(String [] args) {
        MatcherPatternTest matchTest = new MatcherPatternTest();
        matchTest.matchTest("chimera_frontend.db_instance_dns_suffix = ChimeraInstanceDnsSuffix");
    }
}
