package com.keerden.aoc2024;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/main/resources/day7.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
         System.out.println(part2(fileContent));
    }

    public static long part1(List<String> lines) throws IOException {   
        long sum = 0;
        for(String line : lines){
            String[] tokens = line.split(": ");
            if(tokens.length != 2){
                throw new IOException("invalid input");
            }
            long testvalue = Long.parseLong(tokens[0]);
            List<Integer> numbers = parseList(tokens[1]);
            if(checkCalibration(testvalue, numbers)){
                sum += testvalue;
            }
        }
        return sum;
    }

    private static List<Integer> parseList(String input){
        Scanner scanner = new Scanner(input);
        List<Integer> list = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        scanner.close();
        return list;
    }

    private static boolean checkCalibration(long testValue, List<Integer> numbers){
        if(numbers.size() < 2){
            return false;
        }
        return checkCalibration(testValue, numbers, 1, numbers.get(0));
    }

    private static boolean checkCalibration(long testValue, List<Integer> numbers, int index, long sum){
        if(index >= numbers.size()){
            return sum == testValue;
        }
        //check add
        long tmp = sum + numbers.get(index);
        if(tmp <= testValue && checkCalibration(testValue, numbers, index + 1, tmp)){
            return true;
        } else {
            tmp = sum * numbers.get(index);
            if(tmp <= testValue){
                return checkCalibration(testValue, numbers, index + 1, tmp);
            }
        }
        return false;
    }

    public static long part2(List<String> lines) throws IOException {       
        long sum = 0;
        for(String line : lines){
            String[] tokens = line.split(": ");
            if(tokens.length != 2){
                throw new IOException("invalid input");
            }
            long testvalue = Long.parseLong(tokens[0]);
            List<Integer> numbers = parseList(tokens[1]);
            if(checkCalibrationConcat(testvalue, numbers)){
                sum += testvalue;
            }
        }
        return sum;
    }

    private static boolean checkCalibrationConcat(long testValue, List<Integer> numbers){
        if(numbers.size() < 2){
            return false;
        }
        return checkCalibrationConcat(testValue, numbers, 1, numbers.get(0));
    }

    private static boolean checkCalibrationConcat(long testValue, List<Integer> numbers, int index, long sum){
        if(index >= numbers.size()){
            return sum == testValue;
        }
        
        long tmp = concat(sum, numbers.get(index));
        if(tmp <= testValue && checkCalibrationConcat(testValue, numbers, index + 1, tmp)){
            return true;
        }
        
        tmp = sum + numbers.get(index);
        if(tmp <= testValue && checkCalibrationConcat(testValue, numbers, index + 1, tmp)){
            return true;
        }

        tmp = sum * numbers.get(index);
        if(tmp <= testValue){
            return checkCalibrationConcat(testValue, numbers, index + 1, tmp);
        }
        
        return false;
    }


    private static long concat(long a, long b){
        String result = String.valueOf(a).concat(String.valueOf(b));
        return Long.parseLong(result);
    }
}
