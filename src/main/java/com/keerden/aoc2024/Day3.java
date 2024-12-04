package com.keerden.aoc2024;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/main/resources/day3.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    public static long part1(List<String> lines) throws IOException {       
        long result = 0;

        for(String line : lines){
            result += getMul(line);
        }


        return result;
    }


    private static long getMul(String line){
        long result = 0;
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(line);

        while(matcher.find()){
            result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }

        return result;
    }


    public static long part2(List<String> lines) throws IOException {       
        String file = String.join("", lines);
        long result = 0;
        String[] entries = file.split("do\\(\\)");
        for(String entry : entries){
            String[] parts = entry.split("don\'t\\(\\)", 2);
            result += getMul(parts[0]);
        }

        return result;
    }
}
