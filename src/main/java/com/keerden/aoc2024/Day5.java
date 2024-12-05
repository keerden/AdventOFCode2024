package com.keerden.aoc2024;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/main/resources/day5.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    public static int part1(List<String> lines) throws IOException {       
        int sum = 0;
        boolean parseRules = true;
        HashMap<Integer, HashSet<Integer>> rules = new HashMap<Integer, HashSet<Integer>>();

        for(String line: lines){
            if(line.isEmpty()){
                parseRules = false;
                continue;
            }

            if(parseRules){
                addRule(rules, line);
            }else {
                sum+= checkUpdate(rules, line);
            }
        }

        return sum;
    }

    public static int part2(List<String> lines) throws IOException {       
        int sum = 0;
        boolean parseRules = true;
        HashMap<Integer, HashSet<Integer>> rules = new HashMap<Integer, HashSet<Integer>>();

        for(String line: lines){
            if(line.isEmpty()){
                parseRules = false;
                continue;
            }

            if(parseRules){
                addRule(rules, line);
            }else {
                if(checkUpdate(rules, line) == 0){
                    sum+= sortUpdate(rules, line);
                }
            }
        }

        return sum;
    }


    private static void addRule(HashMap<Integer, HashSet<Integer>> rules, String rule) throws IOException{
        String[] pages = rule.split("\\|");
        if(pages.length != 2){
            throw new IOException("Invalid rule");
        }

        int left = Integer.parseInt(pages[0]);
        int right = Integer.parseInt(pages[1]);
        if(!rules.containsKey(left)){
            rules.put(left, new HashSet<Integer>());
        }
        rules.get(left).add(right);
    }

    private static Integer checkUpdate(HashMap<Integer, HashSet<Integer>> rules, String update){
        String[] tokens = update.split(",");
        HashSet<Integer> checked = new HashSet<>();

        for(String token : tokens){
            int page = Integer.parseInt(token);
            HashSet<Integer> leftSet = rules.get(page);
            if(leftSet != null && setsIntersect(leftSet, checked)){
                return 0;
            }
            checked.add(page);
        }

        return Integer.parseInt(tokens[tokens.length / 2]);
    }

    private static boolean setsIntersect(HashSet<Integer> left, HashSet<Integer> right){
        for(int num : left){
            if(right.contains(num)){
                return true;
            }
        }
        return false;
    }

    private static Integer sortUpdate(HashMap<Integer, HashSet<Integer>> leftRules, String update){
        String[] tokens = update.split(",");
        ArrayList<Integer> pages = new ArrayList<>();
        for(String token : tokens){
            pages.add(Integer.parseInt(token));
        };


        pages.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer page1, Integer page2) {
                HashSet<Integer> rule = leftRules.get(page1);
                if(rule != null && rule.contains(page2)){ //page1 must be before page2
                    return -1;
                }
                rule = leftRules.get(page2);
                if(rule != null && rule.contains(page1)){ //page2 must be before page1
                    return 1;
                }                

                //no rule found containing both pages, so they have no order
                return 0;

            }
        });

        return pages.get(pages.size() / 2);
    }
}
