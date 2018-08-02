package com.ziemo.fileReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FileWordAnalyzerTest {

    FileWordAnalyzer a;

    @Mock
    FilePartReader r;

    @BeforeEach
    void setUp() {
        r = mock(FilePartReader.class);
        a = new FileWordAnalyzer(r);
    }

    @Test
    void testWhen() {
        Mockito.when(r.getToLine()).thenReturn(2);
        assertEquals(2, r.getToLine());
    }

    @Test
    void testMock() throws FileNotFoundException {
        assertEquals("test mock", a.forMock(r));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testParaMock(int arg) {
        assertAll(
                ()-> {
                    r.setup("path", arg, arg);
                    Mockito.when(r.readLine()).thenReturn("path " + arg + arg);
                    assertEquals("path "+arg+arg,r.readLine());
                }
        );
    }

    @Test
    @Disabled
    void test1() throws FileNotFoundException {
        assertEquals("[1a1\r\n" +
                "2b, 2a\r\n" +
                "3c, 3a\r\n" +
                "4d, 3b, 4a\r\n" +
                "5e, 4bb4, 4cr, 5ax\r\n" +
                "6f, 5b, 5c, 5d, 6a\r\n" +
                "7g, 6bb, 6ca, 6d, 6ea, 7ea, 7f]", a.wordsByABC());
    }
}