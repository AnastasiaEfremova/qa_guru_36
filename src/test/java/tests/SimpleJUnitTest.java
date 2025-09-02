package tests;

import org.junit.jupiter.api.*;

public class SimpleJUnitTest {

    /*
    Минимальный жизненный цикл JUnit теста.
     */

    @BeforeAll
     static void beforeAll() {
        System.out.println("Действия ПЕРЕД выполнением ВСЕХ тестов");
    }

    int result;

    // Аннотация, которая позволяет делать какое-то действие перед выполнением каждого отдельного теста
    @BeforeEach
    void beforeEach() {
        result = getResult();
    }

    @Test
    void firstTest() {
        Assertions.assertTrue(result > 2);
    }

    @Test
    void secondTest() {
        Assertions.assertTrue(result < 2);
    }

    int getResult() {
        return 3;
    }

    // Аннотация, которая позволяет делать какое-то действие после выполнения каждого отдельного теста
    @AfterEach
    void afterEach() {
        result = 0;
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Действия ПОСЛЕ выполнением ВСЕХ тестов");
    }
}