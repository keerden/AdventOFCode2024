package com.keerden.aoc2024;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class Day4Test {
    @Test
    public void testPart1() throws IOException {
        File file = new File("src/test/resources/testDay4.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day4.part1(fileContent);
        assertEquals(18, result);
    }

    @Test
    public void testPart2() throws IOException {
        File file = new File("src/test/resources/testDay4.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day4.part2(fileContent);
        assertEquals(9, result);
    }


}
