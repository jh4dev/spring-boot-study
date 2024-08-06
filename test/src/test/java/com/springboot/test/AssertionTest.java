package com.springboot.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionTest {

    @Test
    public void testAssertions() {
        assertTrue(2 < 1, "wrong condition");

    }
}
