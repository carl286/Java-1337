package com.instacart;

import java.util.*;

public class Calculation {

    // 公式左边都是变量，右边都是数字。
    public int calcv1(String target, List<String> input)
    {
        //target needs to be ensured existed in the input, no duplicates
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < input.size(); ++i)
        {
            String [] splited = input.get(i).split("\\s+=\\s+");
            map.put(splited[0], Integer.valueOf(splited[1]));
        }
        return map.get(target);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    //    第一小问 input:["T2", ["T1 = 1", "T2 = T3", "T3 = T1"]] output: T2值   公式都是左边一个变量， 右边是变量或者数值
    public int calcv2(String target, List<String> input)
    {
        //target needs to be ensured existed in the input, no duplicates
        Map<String, Integer> map = new HashMap<>();
        Map<String, List<String>> variable = new HashMap<>();
        for(int i = 0; i < input.size(); ++i)
        {
            String [] splited = input.get(i).split("\\s+=\\s+");
            if (isNumeric(splited[1]))
            {
                map.put(splited[0], Integer.valueOf(splited[1]));
            }
            else
            {
                variable.putIfAbsent(splited[0], new ArrayList<>());
                variable.get(splited[0]).add(splited[1]);
                variable.putIfAbsent(splited[1], new ArrayList<>());
                variable.get(splited[1]).add(splited[0]);
            }
        }

        if (map.containsKey(target))
            return map.get(target);

        Set<String> visited = new HashSet<>();
        visited.add(target);
        Deque<String> dq = new ArrayDeque<>();
        dq.addLast(target);

        while (!dq.isEmpty())
        {
            String current = dq.pollFirst();
            for(int k = 0; k < variable.get(current).size(); ++k)
            {
                String next = variable.get(current).get(k);
                if (!visited.contains(next))
                {
                    if (map.containsKey(next))
                        return map.get(next);

                    visited.add(next);
                    dq.addLast(next);
                }
            }

        }
        return Integer.MAX_VALUE;
    }

    /*
    加问一，公式的左边都是变量，右边可能是数字，或者是变量的和或者差，比如 t1=t2+1, t2=1, t3=t1-t2。右边的运算只可能是和或者差。也不需要考虑需要把公式变形的情况。

    multiple calculations???
     第二小问 input: ["T2", ["T1 = 1", "T2 = 2 + T4", "T3 = T1 - 4", "T4 = T1 + T3]]  output:T2值
     公式左边为变量，右边为一个或多个变量/数值，包括加减操作

给一个target 比如T3,
还有 T1 = 2, T2 = 3, T3 = T4 + T5, T4 = T1 - T2, T5 = T4 + T1, 计算出 T3 = 0, 我的解法是build mappings from T3 -> T4 + T5, T4 -> T1 - T2, T5 = T4 + T1
      //assume the calculation is valid.
     */


    private boolean isInteger(String s)
    {
        return s.matches("^-?\\d+$");
    }

    class Node
    {
        private boolean isPlainValue;
        private String symbolicName;
        private int plainValue;

        public Node(String symbolicName)
        {
            this.symbolicName = symbolicName;
            this.isPlainValue = false;
        }

        public Node(int val)
        {
            this.isPlainValue = true;
            this.plainValue = val;
        }
    }

    class ArithmeticNode
    {
        private char op;// ' ', '+', '-'
        private Node [] operators;
        public ArithmeticNode(char op, Node [] operators)
        {
            this.op = op;
            this.operators = operators;
        }
    }

    private Node parseString(String s)
    {
        if (isInteger(s))
        {
            return new Node(Integer.valueOf(s));
        }
        else
        {
            return new Node(s);
        }
    }
    private int getOperatorValue(Node operator, Map<String, Integer> symbolToPlainValue)
    {
        if (operator.isPlainValue)
            return operator.plainValue;
        return symbolToPlainValue.get(operator.symbolicName);
    }

    private int getArithmeticNodeValue(ArithmeticNode node, Map<String, Integer> symbolToPlainValue)
    {
        int a,b;
        switch (node.op)
        {
            case '+':
                a = getOperatorValue(node.operators[0], symbolToPlainValue);
                b = getOperatorValue(node.operators[1], symbolToPlainValue);
                return a + b;
            case '-':
                a = getOperatorValue(node.operators[0], symbolToPlainValue);
                b = getOperatorValue(node.operators[1], symbolToPlainValue);
                return a - b;
            default:
                return getOperatorValue(node.operators[0], symbolToPlainValue);
        }
    }
    public int calcv3(String target, List<String> input)
    {
        Map<String, ArithmeticNode> symbolToArithmetic = new HashMap<>();
        Map<String, Integer> symbolToPlainValue = new HashMap<>();

        //T1 = T2 + T3;
        Map<String, Set<String>> dependenciesCounts = new HashMap<>();//T1->T2,T3
        Map<String, Set<String>> reverseDepencies = new HashMap<>();//T2->T1

        for(int i = 0; i < input.size(); ++i)
        {
            String [] splited = input.get(i).split("\\s*=\\s*");
            if (isNumeric(splited[1]))
            {
                symbolToPlainValue.put(splited[0], Integer.parseInt(splited[1]));
            }
            else
            {
                dependenciesCounts.putIfAbsent(splited[0], new HashSet<>());
                //needs update dependency as well
                ArithmeticNode arithmeticNode = null;
                if (splited[1].contains("+"))
                {
                    String [] splited2 = splited[1].split("\\+");
                    splited2[0] = splited2[0].trim();
                    splited2[1] = splited2[1].trim();
                    Node a = parseString(splited2[0]);
                    Node b = parseString(splited2[1]);
                    arithmeticNode = new ArithmeticNode('+', new Node [] {a, b});
                    if (!a.isPlainValue)
                    {
                        reverseDepencies.putIfAbsent(splited2[0], new HashSet<>());
                        reverseDepencies.get(splited2[0]).add(splited[0]);
                        dependenciesCounts.get(splited[0]).add(splited2[0]);
                    }
                    if (!b.isPlainValue)
                    {
                        reverseDepencies.putIfAbsent(splited2[1], new HashSet<>());
                        reverseDepencies.get(splited2[1]).add(splited[0]);
                        dependenciesCounts.get(splited[0]).add(splited2[1]);
                    }
                }
                else if (splited[1].contains("-"))
                {
                    String [] splited2 = splited[1].split("-");
                    splited2[0] = splited2[0].trim();
                    splited2[1] = splited2[1].trim();
                    Node a = parseString(splited2[0]);
                    Node b = parseString(splited2[1]);
                    arithmeticNode = new ArithmeticNode('-', new Node [] {a, b});
                    if (!a.isPlainValue)
                    {
                        reverseDepencies.putIfAbsent(splited2[0], new HashSet<>());
                        reverseDepencies.get(splited2[0]).add(splited[0]);
                        dependenciesCounts.get(splited[0]).add(splited2[0]);
                    }
                    if (!b.isPlainValue)
                    {
                        reverseDepencies.putIfAbsent(splited2[1], new HashSet<>());
                        reverseDepencies.get(splited2[1]).add(splited[0]);
                        dependenciesCounts.get(splited[0]).add(splited2[1]);
                    }
                }
                else
                {
                    //from a symbol to another symbol
                    Node b = new Node(splited[1]);
                    arithmeticNode = new ArithmeticNode(' ', new Node [] {b});
                    if (!b.isPlainValue)
                    {
                        reverseDepencies.putIfAbsent(splited[1], new HashSet<>());
                        reverseDepencies.get(splited[1]).add(splited[0]);

                        dependenciesCounts.get(splited[0]).add(splited[1]);
                    }
                }
                symbolToArithmetic.put(splited[0], arithmeticNode);
            }
        }

        Deque<String> dq = new ArrayDeque<>();
        Iterator<String> iterator = symbolToPlainValue.keySet().iterator();
        while (iterator.hasNext())
        {
            dq.addLast(iterator.next());
        }

        while (!dq.isEmpty())
        {
            String current = dq.pollFirst();
            Set<String> dependencies = reverseDepencies.getOrDefault(current, new HashSet<>());
            Iterator<String> iterator1 = dependencies.iterator();
            while (iterator1.hasNext())
            {
                String it = iterator1.next();
                dependenciesCounts.getOrDefault(it, new HashSet<>()).remove(current);
                if (dependenciesCounts.getOrDefault(it, new HashSet<>()).size() == 0 && !symbolToPlainValue.containsKey(it))
                {
                    ArithmeticNode arithmeticNode = symbolToArithmetic.get(it);
                    //calculate
                    //TODO
                    int newValue = getArithmeticNodeValue(arithmeticNode, symbolToPlainValue);
                    symbolToPlainValue.put(it, newValue);


                    dq.addLast(it);
                }
            }

        }

        return symbolToPlainValue.getOrDefault(target, Integer.MAX_VALUE);
    }

    public static void main(String [] args)  {

        Calculation cal = new Calculation();
        //v1
//        List<String> input1 = new ArrayList<>();
//        input1.add("T3 = 5");
//        input1.add("T2 = 7");
//        input1.add("T1 = 11");
//
//        String target = "T1";
//        System.out.println(cal.calcv1(target, input1));

        //v2
//        List<String> input2 = new ArrayList<>();
//        input2.add("T1 = 1");
//        input2.add("T2 = T3");
//        input2.add("T3 = T1");
//
//        String target = "T2";
//        System.out.println(cal.calcv2(target, input2));

        //v3
//        List<String> input3 = new ArrayList<>();
//        input3.add("t1=t2+1");
//        input3.add("t2=1");
//        input3.add("t3=t1-t2");
//
//        String target3 = "t3";

        List<String> input3 = new ArrayList<>();
        input3.add("T1 = 2");
        input3.add("T2 = 3");
        input3.add("T3 = T4 + T5");
        input3.add("T4 = T1 - T2");
        input3.add("T5 = T4 + T1");
        String target3 = "T3";

        System.out.println(cal.calcv3(target3, input3));


    }
}
