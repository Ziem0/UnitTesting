package com.ziemo.fileReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.Set;

import static org.junit.Assert.*;

class ServiceHolderTest {

    private ServiceHolder tested;

    @BeforeEach
    void setUp() {
        tested = new ServiceHolder();
        final Object service = new Object();
        final Object service2 = new Object();

        tested.addService(service);
        tested.addService(service2);
    }

    @Test
    void testAddService() {


        Set<String> services = Whitebox.getInternalState(tested, "services");  // or Set.class for 'services'
        assertEquals("size should be 2",2,services.size());
    }
}