package com.springboot.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAllTest() {
        System.out.println(">>>> This is BeforeAll Method <<<<<");
    }

    @AfterAll
    static void afterAllTest() {
        System.out.println(">>>> This is AfterAll Method <<<<<");
    }

    @BeforeEach
    void beforeEachTest() {
        System.out.println(">>>> This is BeforeEach Method <<<<<");
    }

    @AfterEach
    void afterEachTest() {
        System.out.println(">>>> This is AfterEach Method <<<<<");
    }


    @Test
    void test1() {
        System.out.println(">>>>> test1() Start");
    }

    @Test
    void test2() {
        System.out.println(">>>>> test2() Start");
    }

    @Test
    @Disabled
    void test3() {
        System.out.println(">>>>> test2() Start");
    }
}
