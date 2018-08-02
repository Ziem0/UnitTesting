package com.ziemo.fileReader;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {

    @Test
    void testReadLinesBeforeSetup() {
        assertThrows(FileNotFoundException.class, () -> reader.readLine());
    }

    @Test
    void testSetupFromLineLT1() {
        assertThrows(IllegalArgumentException.class, () -> reader.setup("src/main/resources/test_data.txt", 0, 3));
    }

    @Test
    void testSetupToLineLTFromLine() {
        assertThrows(IllegalArgumentException.class, () -> reader.setup("src/main/resources/test_data.txt", 3, 2));
    }

    @Test
    void testReadLines1_2() throws FileNotFoundException {
        reader.setup("src/main/resources/test_data.txt", 1, 2);
        assertEquals("1a1\r\n" +
                "2b 2a", reader.readLine());
    }

    @Test
    void testReadLines2_4() throws FileNotFoundException {
        reader.setup("src/main/resources/test_data.txt", 2, 4);
        assertEquals("2b 2a\r\n" +
                "3c 3b 3a\r\n" +
                "4d 4cr 4bb4 4a", reader.readLine());
    }

    @Test
    void testReadLinesAll() throws FileNotFoundException {
        reader.setup("src/main/resources/test_data.txt", 1, 7);
        assertEquals("1a1\r\n" +
                "2b 2a\r\n" +
                "3c 3b 3a\r\n" +
                "4d 4cr 4bb4 4a\r\n" +
                "5e 5d 5c 5b 5ax\r\n" +
                "6f 6ea 6d 6ca 6bb 6a\r\n" +
                "7g 7f 7ea", reader.readLine());
    }

    @Test
    void testReadLinesPastEof() throws FileNotFoundException {
        reader.setup("src/main/resources/test_data.txt", 7, 7);
        assertEquals("7g 7f 7ea", reader.readLine());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    FilePartReader reader;

    @BeforeEach
    void setUp() {
        reader = new FilePartReader();
    }

    @Test
    void testIfPathIsOK() {
        reader.setup("src/main/resources/test_data.txt", 1, 7);
        Assumptions.assumingThat(reader.getFromLine() <= reader.getToLine(), () -> {
            assertAll(
                    () -> assertNotNull(reader.readLine()),
                    () -> assertEquals("1a1\r\n" +
                            "2b 2a\r\n" +
                            "3c 3b 3a\r\n" +
                            "4d 4cr 4bb4 4a\r\n" +
                            "5e 5d 5c 5b 5ax\r\n" +
                            "6f 6ea 6d 6ca 6bb 6a\r\n" +
                            "7g 7f 7ea", reader.readLine())
            );
            assertAll(
                    () -> assertEquals(1, reader.getFromLine()),
                    () -> assertEquals(7, reader.getToLine())
            );
        });
    }

    @ParameterizedTest
    @MethodSource("streamInt")
    void testIfSetupIsCreatedWithDifferentInput(int i) {
        assertThrows(IllegalArgumentException.class, () -> reader.setup("src/main/resources/test_data.txt", 7, i));
    }

    @ParameterizedTest
    @MethodSource("streamInt")
    void testGetters(int i) {
        reader.setup("src/main/resources/test_data.txt", i, i);
        assertAll(
                () -> assertEquals(i, reader.getFromLine()),
                () -> assertEquals(i, reader.getToLine())
        );
    }

    private static Stream<Integer> streamInt() {
        return IntStream.range(1, 6).limit(3).boxed();
    }

    @Test
    void testFileNotFoundException() {
        reader.setup("bad path", 1, 7);
        assertThrows(FileNotFoundException.class, () -> reader.readLine());
    }

    @TestFactory
    public Stream<DynamicTest> testDynamic() {
        List<Integer> lines = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<String> output = new ArrayList<>(Arrays.asList("1a1"
                , "1a1\r\n" +
                        "2b 2a"
                , "1a1\r\n" +
                        "2b 2a\r\n" +
                        "3c 3b 3a",
                "1a1\r\n" +
                        "2b 2a\r\n" +
                        "3c 3b 3a\r\n" +
                        "4d 4cr 4bb4 4a"));

        return lines.stream().map(l -> DynamicTest.dynamicTest("test 1 to " + l, () -> {
            int id = lines.indexOf(l);
            reader.setup("src/main/resources/test_data.txt", 1, l);
            assertEquals(output.get(id), reader.readLine());
        }));
    }
}