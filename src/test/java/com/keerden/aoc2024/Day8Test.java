package com.keerden.aoc2024;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.junit.Test;

public class Day8Test {
    @Test
    public void testPart1() throws IOException {
        File file = new File("src/test/resources/testDay8.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day8.part1(fileContent);
        assertEquals(14, result);
    }

    @Test
    public void testPart2() throws IOException {
        File file = new File("src/test/resources/testDay8.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day8.part2(fileContent);
        assertEquals(34, result);
    }
}
