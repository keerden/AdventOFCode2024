package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Day2;

public class Day2Test {
    @Test
    void testPart1() throws IOException {
        File file = new File("src/tests/resources/testDay2.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day2.part1(fileContent);
        assertEquals(2, result);
    }

    @Test
    void testPart2() throws IOException {
        File file = new File("src/tests/resources/testDay2.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day2.part2(fileContent);
        assertEquals(4, result);
    }

    @Test
    void testPart2RemoveFirstTwo() throws IOException {
        File file = new File("src/tests/resources/testDay2a.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        int result = Day2.part2(fileContent);
        assertEquals(4, result);
    }

}
