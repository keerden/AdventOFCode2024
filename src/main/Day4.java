package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import main.util.VectorObj;

public class Day4 {
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/main/resources/day4.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    public static int part1(List<String> map) throws IOException {       
        List<VectorObj> words = getPossibleWords(map);
        int count = 0;
        for(VectorObj word : words){
            if(isValidWord(word, map, "XMAS")){
                count++;
            }
        }
        
        return count;
    }

    private static List<VectorObj> getPossibleWords(List<String> map){
        final int sizeY = map.size();
        final int sizeX = map.get(0).length();

        ArrayList<VectorObj> result = new ArrayList<>();
        for(int y = 0; y < map.size(); y++){
            String line = map.get(y);
            for(int x = 0; x < line.length(); x++){
                if(line.charAt(x) == 'X'){
                    result.addAll(makeWords(x, y, sizeX, sizeY));
                }
            }
        }
        return result;
    }
    
    private static List<VectorObj> makeWords(int posX, int posY, int sizeX, int sizeY){
        final int minX = Math.max(0, posX - 1);
        final int minY = Math.max(0, posY - 1);
        final int maxX = Math.min(posX + 2, sizeX);
        final int maxY = Math.min(posY + 2, sizeY);

        ArrayList<VectorObj> result = new ArrayList<>();
        for(int y = minY; y < maxY; y++){
            for(int x = minX; x < maxX; x++){
                if(x == posX && y == posY){
                    continue;
                }
                VectorObj word = new VectorObj(posX, posY, x - posX, y - posY);
                result.add(word);
            }
        }
        return result;
    }

    private static boolean isValidWord(VectorObj word, List<String> map, String value){
        final int sizeY = map.size();
        final int sizeX = map.get(0).length();
        
        for(int i = 0; i < value.length(); i++){
            int posX = word.posX + (i * word.directionX);
            int posY = word.posY + (i * word.directionY);
            if(posX < 0 || posX >= sizeX || posY < 0 || posY >= sizeY){
                return false;
            }

            char c = map.get(posY).charAt(posX);
            if(c != value.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public static int part2(List<String> map) throws IOException {       
        int count = 0;

        for(int y = 0; y < map.size(); y++){
            String line = map.get(y);
            for(int x = 0; x < line.length(); x++){
                if(line.charAt(x) == 'A'){
                    if(checkMas(map, x, y)){
                        count++;
                    }
                }
            }
        }

        return count;
    }


    private static boolean checkMas(List<String> map, int posX, int posY){
        int count = 0;

        //top left
        if(isValidWord(new VectorObj(posX - 1, posY -1, 1, 1), map, "MAS")){
            count++;
        }

        //top right
        if(isValidWord(new VectorObj(posX + 1, posY -1, -1, 1), map, "MAS")){
            count++;
        }

        //bottom left
        if(count < 2 && isValidWord(new VectorObj(posX - 1, posY + 1, 1, -1), map, "MAS")){
            count++;
        }

        //bottom right
        if(count < 2 && isValidWord(new VectorObj(posX + 1, posY + 1, -1, -1), map, "MAS")){
            count++;
        }



        return count == 2;
    }
}
