package com.vivek.junit;

import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 *Junit 5 consists of 3 blocks
 *      Junit Platform is te building blocks
 *      Junit Jupiter is the new programming model + extension model + extensions - assertions, annotations
 *      Junit Vintage is to support old Junit Version
 *
 * In this Test Class, I'm trying out different test asserts and seeing how things are different in terms of Junit 5
 *Some of the things that I learnt
 * New Feature in Junit 5 is
 *          The method need not be started with public
 *          U can add @DisplayName to make it look better for every test
 *          In Junit 4 , we can test excpetion testing by adding a @Test(expected = )
 *          in Junit 5 we assert that using lambda expression
 *          The @beforeAll method should be static else the class should be annotated to @TestInstance
 *                  In order to allow individual test methods to be executed in isolation and to avoid unexpected side effects due to mutable test instance state,
 *                  JUnit creates a new instance of each test class before executing each test method (see Test Classes and Methods).
 *                  This "per-method" test instance lifecycle is the default behavior in JUnit Jupiter and is analogous to all previous versions of JUnit.
 *                  Please note that the test class will still be instantiated if a given test method is disabled via a condition (e.g., @Disabled, @DisabledOnOs, etc.)
 *                  even when the "per-method" test instance lifecycle mode is active.
 *                  If you would prefer that JUnit Jupiter execute all test methods on the same test instance,
 *                  annotate your test class with @TestInstance(Lifecycle.PER_CLASS).
 *                  When using this mode, a new test instance will be created once per test class. Thus, if your test methods rely on state stored in instance variables, you may need to reset that state in @BeforeEach or @AfterEach methods.
 *
 *          The parmeterisedTest is used to pass different input to same assert/ testcase
 *                  @ValueSource is for String parameter
 *                  @CsvSourcce is for input data and expected value
 *                   name inside Parametrised test to give a better readability
 *
 *          @RepeatedTest is used to run the same test repeatedly for a given time. Is introduced in Junit 5
 *          Performance Testing. This is asserted using lambda function
 *          @assertTimeOut is used for this
 *
 *          @Disabled is used to not to run the test case due to some scenario. @Ignored is for Junit 4
 *
 *          Grouping specific type of Tests @Nested is part of Junit 5
 *
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class StringTest {

    /**
     * This annotation is used to run the method only once when the test class is fired
     * for example establishing connection with external sources
     * The @beforeAll method should be static else the class should be annotated to @TestInstance
     */
    @BeforeAll
    void beforeAll(){
        System.out.println("this is initialised at the beginning of test class");
    }

    /**
     * This annotation is used to run the method only once when the test class is fired and commpleted
     * for example closing connection with external sources
     * The @afteAll method should be static else the class should be annotated to @TestInstance
     */
    @AfterAll
    void afterAll(){
        System.out.println("Close all the external connection");
    }

    /**
     *  This method is called before every test is fired. this can be used to initialise the object
     *  TestInfo will provide which test method was running and failed
     * @ before in Junit  is now @before Each
     */
    @BeforeEach
    void beforeEach(TestInfo info){
        System.out.println(" Initialize the same test data"+info.getDisplayName());
    }

    /**
     * This method is used after each test is completed
     * @ after in Junit  is now @after Each
     */
    @AfterEach
    void afterEach(){
        System.out.println("this method is called after very test method");
    }
    @Test
    @Disabled("Testing Disabled")
    void testfail(){
        assertTrue("Not yet implemented".length()>0);
    }

    /**
     * assertEquals and Not Null
     */
    @Test
    @DisplayName("Test for UPPERCASE")
     void toUppercase(){
        String str = new String("abc");
        assertNotNull(str);
        assertEquals("ABC",str.toUpperCase());
    }

    /**
     * In this we are learning how to assert boolean
     */
    @Test
      void testAssertBoolean(){
        String str = new String("asdasd");
        assertTrue(str.contains("asd"));

    }

    /**
     * In this we are learning how to assert boolean
     */
    @RepeatedTest(10)
      void testAssertBooleanRepeatedly(){
        String str = new String("asdasd");
        assertTrue(str.contains("asd"));

    }

    /**
     * In this method we are asserting how to asser Array
     */
    @Test
     void testAssertArray(){
        String str = "ABC DEF GHI";
        String strArr[] = str.split(" ");
        assertArrayEquals(new String[]{"ABC","DEF","GHI"}, strArr);
    }

    /**
     * Testing Exception
     * In Junit 4 , we can test excpetion testing by adding a @Test(expected = )
     * in Junit 5 we assert that using lambda expression
     */
    @Test
    void testingException(){
         String str = null;
       //  int actualLength = str.length();
         assertThrows(NullPointerException.class,()->str.length());
    }

    @Test
    void lengthGreaterThanZero(){
        assertTrue("Abc".length()>0);
        assertTrue("asd".length()>0);
        assertTrue("dfg".length()>0);
        assertTrue("dAbc".length()>0);
    }

    /**
     * Parameterised Tests, we can pass different value to see if things are tested for dfiferent scenario
     */
    @ParameterizedTest
    @ValueSource(strings ={"asd","fgd","tyu","sdffghdasdfasdf","s"})
    void lengthGreaterThanZeroWithParameterValues(String str){
        assertTrue(str.length()>0);
    }


    /**
     * CSV source to a have different values and expeccted result
     * U can give  a better definition to these parameter sets using name attribute
     */
    @ParameterizedTest(name="{0} length is {1}")
    @CsvSource(value = {"abc,3","asdfg,5","'',0","abcdefg,7"})
    void upperCaseWithParameters(String val, int expectedLength){
        assertEquals(expectedLength,val.length());

    }

    /**
     * Performance Testing
     */
    @Test
    void performanceTest(){
        assertTimeout(Duration.ofSeconds(3),
            ()->{
            for(int i=0; i <= 700000 ; i++){
                int j=i;
                System.out.println(j);
            }
            }
            );
    }

}
