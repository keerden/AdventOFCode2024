package com.keerden.aoc2024;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class Day2Test {
    @Test
    public void testPart1() throws IOException {
        File file = new File("src/test/resources/testDay2.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day2.part1(fileContent);
        assertEquals(2, result);
    }

    @Test
    public void testPart2() throws IOException {
        File file = new File("src/test/resources/testDay2.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day2.part2(fileContent);
        assertEquals(4, result);
    }

    @Test
    public void testPart2RemoveFirstTwo() throws IOException {
        File file = new File("src/test/resources/testDay2a.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day2.part2(fileContent);
        assertEquals(4, result);
    }

}
