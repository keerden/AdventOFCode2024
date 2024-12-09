package com.keerden.aoc2024;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public class Day8 {
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/main/resources/day8.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
         System.out.println(part2(fileContent));
    }

    public static int part1(List<String> lines) throws IOException {
        int sizeY = lines.size();
        int sizeX = lines.get(0).length();
        
        List<Triplet<Integer, Integer, Character>> antennas = getAntennas(lines);

        Set<Pair<Integer, Integer>> antinodes = new HashSet<>();

        for(int i = 0; i < antennas.size(); i++){
            Triplet<Integer, Integer, Character> antennaA = antennas.get(i);
        
            for(int j = i + 1; j < antennas.size(); j++){
                Triplet<Integer, Integer, Character> antennaB = antennas.get(j);
                if(antennaA.getValue2() != antennaB.getValue2()){
                    continue;
                }
                Pair<Integer, Integer> diff = subVector(antennaB.removeFrom2(), antennaA.removeFrom2());
                Pair<Integer, Integer> first = subVector(antennaA.removeFrom2(), diff);
                Pair<Integer, Integer> second = addVector(antennaB.removeFrom2(), diff);
                if(isWithinMap(first, sizeX, sizeY)){
                    antinodes.add(first);
                }
                if(isWithinMap(second, sizeX, sizeY)){
                    antinodes.add(second);
                }
            }
        }

        return antinodes.size();
    }

    public static int part2(List<String> lines) throws IOException {
        int sizeY = lines.size();
        int sizeX = lines.get(0).length();
        
        List<Triplet<Integer, Integer, Character>> antennas = getAntennas(lines);

        Set<Pair<Integer, Integer>> antinodes = new HashSet<>();

        for(int i = 0; i < antennas.size(); i++){
            Triplet<Integer, Integer, Character> antennaA = antennas.get(i);
        
            for(int j = i + 1; j < antennas.size(); j++){
                Triplet<Integer, Integer, Character> antennaB = antennas.get(j);
                if(antennaA.getValue2() != antennaB.getValue2()){
                    continue;
                }
                Pair<Integer, Integer> diff = subVector(antennaB.removeFrom2(), antennaA.removeFrom2());
                Pair<Integer, Integer> first = antennaA.removeFrom2();
                Pair<Integer, Integer> second = antennaB.removeFrom2();

                //antinodes before
                while(isWithinMap(first, sizeX, sizeY)){
                    antinodes.add(first);
                    first = subVector(first, diff);
                }

                while(isWithinMap(second, sizeX, sizeY)){
                    antinodes.add(second);
                    second = addVector(second, diff);
                }
            }
        }

        return antinodes.size();
    }

    private static List<Triplet<Integer, Integer, Character>> getAntennas(List<String> lines){
        List<Triplet<Integer, Integer, Character>> antennas = new ArrayList<>();

        for(int y = 0; y < lines.size(); y++){
            String line = lines.get(y);
            for(int x = 0; x < line.length(); x++){
                char c = line.charAt(x);
                if(c != '.'){
                    antennas.add(new Triplet<Integer,Integer,Character>(x, y, c));
                }
            }
        }
        return antennas;
    }


    private static Pair<Integer, Integer> addVector(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
        return new Pair<Integer, Integer>(a.getValue0() + b.getValue0(), a.getValue1() + b.getValue1());
    }

    private static Pair<Integer, Integer> subVector(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
        return new Pair<Integer, Integer>(a.getValue0() - b.getValue0(), a.getValue1() - b.getValue1());
    }

    private static Boolean isWithinMap(Pair<Integer, Integer> pos, int sizeX, int sizeY){
        return pos.getValue0() >= 0 && pos.getValue0() < sizeX && pos.getValue1() >= 0 && pos.getValue1() < sizeY;
    }

}
