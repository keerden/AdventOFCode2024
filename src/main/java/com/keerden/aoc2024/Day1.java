package com.keerden.aoc2024;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/main/resources/day1.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
         System.out.println(part2(fileContent));
    }

    public static int part1(List<String> lines) throws IOException {       
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();


        for(String line : lines){
            Scanner scan = new Scanner(line);
            int leftVal = scan.nextInt();
            left.add(leftVal);
            int rightVal = scan.nextInt();
            right.add(rightVal);
            scan.close();
        }

        Collections.sort(left);
        Collections.sort(right);

        if(left.size() != right.size()){
            throw new IOException("Input error, lists have different size");
        }

        int sum = 0;
        for(int i = 0; i < left.size(); i++){
            int distance = Math.abs(left.get(i) - right.get(i));
            sum += distance;
        }

        return sum;
    }

    public static int part2(List<String> lines) throws IOException {       
        ArrayList<Integer> left = new ArrayList<>();
        Map<Integer, Integer> right = new HashMap<>();


        for(String line : lines){
            Scanner scan = new Scanner(line);
            int leftVal = scan.nextInt();
            left.add(leftVal);
            int rightVal = scan.nextInt();

            Integer count = right.get(rightVal);
            count = (count == null) ? 1 : count + 1;
            right.put(rightVal, count);
            scan.close();
        }


        int sum = 0;
        for(int i = 0; i < left.size(); i++){
            int leftVal = left.get(i);
            Integer count = right.get(leftVal); 
            if(count != null){
                sum += leftVal * count;
            }
        }

        return sum;
    }

}
