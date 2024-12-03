package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Day3;

public class Day3Test {
    @Test
    void testPart1() throws IOException {
        File file = new File("src/tests/resources/testDay3.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        long result = Day3.part1(fileContent);
        assertEquals(161, result);
    }

    @Test
    void testPart2() throws IOException {
        File file = new File("src/tests/resources/testDay3a.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        long result = Day3.part2(fileContent);
        assertEquals(48, result);
    }

}