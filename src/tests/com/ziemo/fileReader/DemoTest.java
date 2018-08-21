package com.ziemo.fileReader;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@Slf4j
class DemoTest {

    private Demo demo;

    @BeforeEach
    void setUp() {
        demo = new Demo("iras", 60, filePartReader);
    }

    @Mock
    private
    FilePartReader filePartReader = mock(FilePartReader.class);        //-->spy instead mock

    @Spy
    private
    List<Integer> list = spy(LinkedList.class);

    @Spy
    private
    LinkedList<String> list2 = spy(LinkedList.class);

    @Test
    void forSpy() {
        list.add(1);
        list.add(3);
        list.add(2);
        assertEquals(3, list.size());
    }

    @Test
    void spyString() {
        list2.add("Ziemo");
        list2.add("ania");
        list2.add("gabs");
        assertEquals("gabs",list2.get(2),"incorrect output");
    }

    @Test
    void testIfNameIsNull() {
        assertThrows(NullPointerException.class, () -> new Demo(null, 23, filePartReader));
    }

    @Test
    void testIfAgeIsBelow0() {
        assertThrows(IllegalArgumentException.class, () -> new Demo("ziemo", -12, filePartReader));
    }

    @ParameterizedTest
    @MethodSource("intStream")
    void testCreateInstanceWithStreamInputInt(int arg) {
        assertEquals(arg, new Demo("ziemo", arg, filePartReader).getAge());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ziemo", "ania", "nina"})
    void testCreateInstanceWithStringInputString(String name) {
        assertEquals(name, new Demo(name, 2,filePartReader).getName());
    }

    private static IntStream intStream() {
        return IntStream.range(1, 4);
    }

    ////////////////


    @Test
    void testInOne() {
        Assumptions.assumingThat(demo.getAge() >= 0 && demo.getName() != null, () -> {
            assertAll(
                    () -> assertEquals("iras", demo.getName()),
                    () -> assertEquals(60, demo.getAge())
            );
            assertAll(
                    () -> assertNotNull(demo.countList()),
                    () -> assertEquals(2, (int) demo.countList()),
                    () -> {
                        Mockito.when(demo.getPath()).thenReturn("path indeed");
                        assertEquals("path indeed", demo.getPath());
                    }

            );

        });
    }

    ////////////////


    @TestFactory
    Stream<DynamicTest> testFaq() {
        List<String> names = new ArrayList<>(Arrays.asList("ziemo", "ania", "nina"));
        List<Integer> ages = new ArrayList<>(Arrays.asList(32, 30, 25));

        return names.stream().map(n -> DynamicTest.dynamicTest("test for name " + n, () -> {
            int nameId = names.indexOf(n);
            int age = ages.get(nameId);
            assertAll(
                    () -> assertEquals(age, new Demo(n, age, filePartReader).getAge()),
                    () -> assertEquals(n, new Demo(n, age, filePartReader).getName())
            );
        }));
    }
}