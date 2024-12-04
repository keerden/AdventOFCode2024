package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Day4;

public class Day4Test {
    @Test
    void testPart1() throws IOException {
        File file = new File("src/tests/resources/testDay4.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day4.part1(fileContent);
        assertEquals(18, result);
    }

    @Test
    void testPart2() throws IOException {
        File file = new File("src/tests/resources/testDay4.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day4.part2(fileContent);
        assertEquals(9, result);
    }


}
