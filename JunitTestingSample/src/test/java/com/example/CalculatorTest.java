package com.example;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.lang.annotation.Target;

//Since CalculatorTest is initialized for every method in order to make it initialize per class
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD) -> IT is default
public class CalculatorTest {

    //In order to create the object of Calculator only once
    Calculator calculator;
    TestInfo testInfo;
    TestReporter testReporter;
    @BeforeEach
    void init(TestInfo testInfo,TestReporter testReporter){
        calculator=new Calculator();
        this.testInfo=testInfo;
        this.testReporter=testReporter;
        //testReporter is log
        //testInfo is having test data
        testReporter.publishEntry("Running "+testInfo.getTestMethod()+" "+testInfo.getDisplayName());
        System.out.println("Running "+testInfo.getTestMethod()+" "+testInfo.getDisplayName());
    }

    //beforeAll should be static since it initialized before is class in instantiated
    @BeforeAll
    static void beforeEverything(){
        System.out.println("Before All executed ");
    }

    //Two ways to perform multiple testcases
    //First way is nested
    @Nested
    @DisplayName("Nested Addition operation - 1")
    class AdditionTest{
        @Test
        @DisplayName("addingPositiveNumbers")
        void testingPositive(){
            //Calculator calculator=new Calculator();
            int expected=2;
            int actual=calculator.add(1,1);
            assertEquals(expected,actual,()->"adding operation performed on positive");
        }
        @Test
        @DisplayName("addingNegativeNumbers")
        void testingNegative(){
           // Calculator calculator=new Calculator();
            int expected=-2;
            int actual=calculator.add(-1,-1);
            assertEquals(expected,actual,()->"adding operation performed on negative");
        }
        @Test
        @DisplayName("addingZeroes")
        void testingZeroes(){
            int expected=0;
            int actual=calculator.add(0,0);
            assertEquals(expected,actual,()->"adding operation performed on negative");
        }
    }
    //another way for creating all testcases
    @Test
    @DisplayName("testing multiple multiplication cases - 2")
    void nestedMultiplicationTest(){
        //Calculator calculator=new Calculator();
        assertAll(
                ()-> assertEquals(4,calculator.multiply(2,2),"multiplication operation"),
                ()-> assertEquals(99,calculator.multiply(3,33),"multiplication operation")
        );
    }

    @Test
    @DisplayName("Addition testing - 3")
    void addTest(){
        //Calculator calculator=new Calculator();
        int expected=2;
        int actual=calculator.add(1,1);
        assertEquals(expected,actual,()->"addition operation");
    }

    @Test
    @Tag("multiplication tag")
    @DisplayName("multiplication testing - 4")
    void multiplicationTest(){
        //Calculator calculator=new Calculator();
        assertEquals(100,calculator.multiply(2,50),"performing multiplication");
    }

    @Test
    @Tag("Division tag")
    @DisplayName("division testing - 5")
    void divisionTest(){
        //Calculator calculator=new Calculator();
        assertEquals(50,calculator.division(100,2),"performing multiplication");
    }

    @Test
    @Tag("Division tag 2")
    @DisplayName("division testing throw- 6")
    void divisionException(){
        //Calculator calculator=new Calculator();
        assertThrows(ArithmeticException.class,()->calculator.division(10,0));
    }
}
