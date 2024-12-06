package com.keerden.aoc2024;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public class Day6 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/day6.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    public static int part1(List<String> lines) throws IOException {
        int sizeY = lines.size();
        int sizeX = lines.get(0).length();
        Set<Pair<Integer, Integer>> obstacleMap = new HashSet<>();
        Pair<Integer, Integer> guardPos = populateMap(lines, obstacleMap);
        return getGuardedTiles(obstacleMap, guardPos, sizeX, sizeY).size();
    }

    public static int part2(List<String> lines) throws IOException {
        int sizeY = lines.size();
        int sizeX = lines.get(0).length();
        Set<Pair<Integer, Integer>> obstacleMap = new HashSet<>();
        Pair<Integer, Integer> guardPos = populateMap(lines, obstacleMap);
        Set<Pair<Integer, Integer>> guardedTiles = getGuardedTiles(obstacleMap, guardPos, sizeX, sizeY);
        
        int count = 0;

        for(Pair<Integer, Integer> tile : guardedTiles){
            if(tile.equals(guardPos)){ //skip guard pos
                continue;
            }
            Set<Pair<Integer, Integer>> newObstacleMap = new HashSet<>(obstacleMap);
            newObstacleMap.add(tile);
            if(checkLoop(newObstacleMap, guardPos, sizeX, sizeY)){
                count++;
            }
        }
  
        return count;
    }


    private static Pair<Integer, Integer> populateMap(List<String> lines, Set<Pair<Integer, Integer>> obstacleMap){
        Pair<Integer, Integer> guardPos = null;
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == '#') {
                    obstacleMap.add(new Pair<Integer, Integer>(x, y));
                } else if (c == '^') {
                    guardPos = new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return guardPos;
    }


    public static Set<Pair<Integer, Integer>> getGuardedTiles(Set<Pair<Integer, Integer>> obstacleMap, Pair<Integer, Integer> guardPos, int sizeX, int sizeY){
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        int direction = 0;
        Pair<Integer, Integer> dirVect = directionToVector(direction);
        Pair<Integer, Integer> currentPos = new Pair<Integer, Integer>(guardPos.getValue0(), guardPos.getValue1());
        while(isWithinMap(currentPos, sizeX, sizeY)){
            visited.add(currentPos);
            //calculate nextPos;
            Pair<Integer, Integer> nextPos = sum(currentPos, dirVect);
            if(obstacleMap.contains(nextPos)){ // turn
                direction++;
                direction %= 4;
                dirVect = directionToVector(direction);
            } else {
                currentPos = nextPos;
            }
        }
        return visited;
    }

    public static boolean checkLoop(Set<Pair<Integer, Integer>> obstacleMap, Pair<Integer, Integer> guardPos, int sizeX, int sizeY){
        Set<Triplet<Integer, Integer, Integer>> visited = new HashSet<>();
        int direction = 0;
        Pair<Integer, Integer> dirVect = directionToVector(direction);
        Pair<Integer, Integer> currentPos = new Pair<Integer, Integer>(guardPos.getValue0(), guardPos.getValue1());
        while(isWithinMap(currentPos, sizeX, sizeY)){
            Triplet<Integer, Integer, Integer> vect = currentPos.add(direction);
            if(visited.contains(vect)){
                //loop detected
                return true;
            }

            visited.add(vect);
            //calculate nextPos;
            Pair<Integer, Integer> nextPos = sum(currentPos, dirVect);
            if(obstacleMap.contains(nextPos)){ // turn
                direction++;
                direction %= 4;
                dirVect = directionToVector(direction);
            } else {
                currentPos = nextPos;
            }
        }
        return false;
    }


    private static Pair<Integer, Integer> directionToVector(int direction) {
        switch (direction) {
            case 0:
                return new Pair<Integer, Integer>(0, -1);
            case 1:
                return new Pair<Integer, Integer>(1, 0);
            case 2:
                return new Pair<Integer, Integer>(0, 1);

            case 3:
                return new Pair<Integer, Integer>(-1, 0);

            default:
                return null;
        }
    }

    private static boolean isWithinMap(Pair<Integer, Integer> pos, int sizeX, int sizeY){
        int x = pos.getValue0();
        int y = pos.getValue1();
        return x > 0 && x < sizeX && y > 0 && y < sizeY;
    }

    private static Pair<Integer, Integer> sum(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
        return new Pair<Integer, Integer>(a.getValue0() + b.getValue0(), a.getValue1() + b.getValue1());
    }

}
    
