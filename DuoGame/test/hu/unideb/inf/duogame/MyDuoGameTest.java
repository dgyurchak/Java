package hu.unideb.inf.duogame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Stream;

import static hu.unideb.inf.duogame.Status.*;
import static org.junit.jupiter.api.Assertions.*;

class MyDuoGameTest {

    @Test
    void freeCells() {
        MyDuoGame duoGame = new MyDuoGame();
        Status[] status = {FREE, FREE, ALICE, ALICE, FREE, FREE, FREE, BOB, FREE, BOB, ALICE};
        Status[] original = Arrays.copyOf(status, status.length);
        TreeMap<Integer, Integer> expected = new TreeMap<>();
        expected.put(1, 1);
        expected.put(4, 3);
        expected.put(8, 1);
        TreeMap<Integer, Integer> actual = duoGame.freeCells(status);
        assertArrayEquals(original, status);
        assertEquals(expected, actual);
    }

    @Test
    void finishedInNextMove() {
        MyDuoGame duoGame = new MyDuoGame();
        Status[] status = {FREE, FREE, ALICE, ALICE, FREE, FREE, FREE, BOB, FREE, BOB, ALICE};
        Status[] original = Arrays.copyOf(status, status.length);
        boolean result = duoGame.finishedInNextMove(status);
        assertArrayEquals(original, status);
        assertFalse(result);
    }

    @Test
    void applyMove() {
        MyDuoGame duoGame = new MyDuoGame();
        Status[] status = {FREE, FREE, ALICE, ALICE, FREE, FREE, FREE, BOB, FREE, BOB, ALICE};
        Status[] original = Arrays.copyOf(status, status.length);
        Status player = ALICE;
        int start = 4;
        int length = 2;
        Status[] expected = {FREE, FREE, ALICE, ALICE, ALICE, ALICE, FREE, BOB, FREE, BOB, ALICE};
        Status[] actual = duoGame.applyMove(status, player, start, length);
        assertArrayEquals(original, status);
        assertArrayEquals(Arrays.copyOfRange(expected, 1, expected.length), Arrays.copyOfRange(actual, 1, actual.length));
    }

    @ParameterizedTest
    @MethodSource
    void freeCellsMethod(Status[] status, String s) {
        MyDuoGame duoGame = new MyDuoGame();
        Status[] original = Arrays.copyOf(status, status.length);
        TreeMap<Integer, Integer> expected = new TreeMap<>();
        String[] pairs = s.split(",");
        for (String item: pairs) {
            String[] element = item.split(":");
            expected.put(Integer.parseInt(element[0]), Integer.parseInt(element[1]));
        }
        TreeMap<Integer, Integer> actual = new TreeMap<>();
    }

    static Stream<Arguments> freeCellsMethod() {
        return Stream.of(
                Arguments.arguments(new Status[] {FREE, FREE, ALICE, ALICE, ALICE, ALICE, FREE, BOB, FREE, BOB, ALICE}, "1:1,4:3,8:1")
        );
    }

    @ParameterizedTest
    @CsvFileSource(delimiter = ',', resources = "finished.csv", numLinesToSkip = 1)
    void finishedInNextMoveMethod(String s, String e) {
        MyDuoGame duoGame = new MyDuoGame();
        Status[] status = new Status[s.length()+1];
        status[0] = FREE;
        boolean expected = Boolean.parseBoolean(e);
        String[] line = s.split("");
        for (int i = 0; i < line.length; i++) {
            if (line[i].equals("F")) {
                status[i+1] = FREE;
            }
            else if (line[i].equals("A")) {
                status[i+1] = ALICE;
            }
            else if (line[i].equals("B")) {
                status[i+1] = BOB;
            }
        }
        boolean result = duoGame.finishedInNextMove(status);
        assertEquals(expected, result);

    }

    @CsvSource({"FFAAF,ALICE,1,1,FAAAF",
            "FFAAF, BOB, 4, 1, FFAAB",
            "FAFFF, BOB, 3, 2, FAFBB"})
    @ParameterizedTest
    void applyMoveMethod(String statusStr, Status player, int start, int length, String expectedStr) {
        MyDuoGame duoGame = new MyDuoGame();
        Status[] status = new Status[statusStr.length()];

        for (int i = 0; i < statusStr.length(); i++) {
            if (statusStr.toCharArray()[i] == 'A') {
                status[i] = ALICE;
            }
            else if (statusStr.toCharArray()[i] == 'B') {
                status[i] = BOB;
            }
            else if (statusStr.toCharArray()[i] == 'F') {
                status[i] = FREE;
            }
        }

        Status[] expected = new Status[expectedStr.length()];
        for (int i = 0; i < expectedStr.length(); i++) {
            if (expectedStr.toCharArray()[i] == 'A') {
                expected[i] = ALICE;
            }
            else if (expectedStr.toCharArray()[i] == 'B') {
                expected[i] = BOB;
            }
            else if (expectedStr.toCharArray()[i] == 'F') {
                expected[i] = FREE;
            }
        }

        Status[] result = duoGame.applyMove(status, player, start, length);
        assertArrayEquals(expected, result);

    }

}
