package com.keerden.aoc2024;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/main/resources/day2.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    public static int part1(List<String> lines) throws IOException {       
        
        int count = 0;
        for(String line : lines){
            ArrayList<Integer> report = new ArrayList<>();
            Scanner scan = new Scanner(line);

            while(scan.hasNextInt()){
                int val = scan.nextInt();
                report.add(val);
            }
            scan.close();
            if(isSafe(report)){
                count++;
            }

        }

        return count;
    }

    public static int part2(List<String> lines) throws IOException {       
        
        int count = 0;
        for(String line : lines){
            ArrayList<Integer> report = new ArrayList<>();
            Scanner scan = new Scanner(line);

            while(scan.hasNextInt()){
                int val = scan.nextInt();
                report.add(val);
            }
            scan.close();
            if(isSafeAfterRemoving(report)){
                count++;
            }

        }

        return count;
    }

    private static boolean isSafe(ArrayList<Integer> report) throws IOException{
        return checkReport(report) == -1;
    }

    private static int checkReport(ArrayList<Integer> report) throws IOException{
        if(report.size() < 2){
            throw new IOException("Invalid Report");
        }

        Boolean isIncreasing = null;
        int prev = report.get(0);

        for(int i = 1; i < report.size(); i++){
            int current = report.get(i);
            if(current == prev){
                return i; //must be either increasing or decrasing by 1
            }

            boolean isLarger = current > prev;
            if(isIncreasing == null){
                isIncreasing = isLarger;
            }

            if(isIncreasing != isLarger){
                return i; //must be either always increasing or decrasing
            }

            if(Math.abs(current - prev) > 3){
                return i; //must be differ at most by 3 (and at least by one, which is already checked)
            }
            
            prev = current;
        }
        return -1;
    }



    private static boolean isSafeAfterRemoving(ArrayList<Integer> report) throws IOException{
        int failIndex = checkReport(report);
        if(failIndex == -1){
            return true;
        }

        

        ArrayList<Integer> alteredReport = new ArrayList<Integer>(report);
        alteredReport.remove(failIndex);
        if(checkReport(alteredReport) == -1){
            return true;
        }

        if(failIndex <= 2) {
            //could be the case when first or second number is bad
            alteredReport = new ArrayList<Integer>(report);
            alteredReport.remove(0);
            if(checkReport(alteredReport) == -1){
                return true;
            }

            alteredReport = new ArrayList<Integer>(report);
            alteredReport.remove(1);
            if(checkReport(alteredReport) == -1){
                return true;
            }
        }


        return false;
    }



}
