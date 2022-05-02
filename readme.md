# Junit

Junit 5 consists of 3 blocks
      Junit Platform is te building blocks
      Junit Jupiter is the new programming model + extension model + extensions - assertions, annotations
      Junit Vintage is to support old Junit Version

 In this Test Class, I'm trying out different test asserts and seeing how things are different in terms of Junit 5 .
 
Some of the things that I learnt

## New Feature in Junit 5 is

    The method need not be started with public
    U can add @DisplayName to make it look better for every test
      
    In Junit 4 , we can test excpetion testing by adding a @Test(expected = )
    in Junit 5 we assert that using lambda expression
    
    The @beforeAll method should be static else the class should be annotated to @TestInstance
      In order to allow individual test methods to be executed in isolation and to avoid unexpected side effects due to mutable test instance state,
      JUnit creates a new instance of each test class before executing each test method (see Test Classes and Methods).
      
      This "per-method" test instance lifecycle is the default behavior in JUnit Jupiter and is analogous to all previous versions of JUnit.
      Please note that the test class will still be instantiated if a given test method is disabled via a condition (e.g., @Disabled, @DisabledOnOs, etc.)
      even when the "per-method" test instance lifecycle mode is active.
      
      If you would prefer that JUnit Jupiter execute all test methods on the same test instance,
      annotate your test class with @TestInstance(Lifecycle.PER_CLASS).
      When using this mode, a new test instance will be created once per test class. Thus, if your test methods rely on state stored in instance variables, you may need to reset that state in @BeforeEach or @AfterEach methods.

      The parmeterisedTest is used to pass different input to same assert/ testcase
              @ValueSource is for String parameter
              @CsvSourcce is for input data and expected value
               name inside Parametrised test to give a better readability

      @RepeatedTest is used to run the same test repeatedly for a given time. Is introduced in Junit 5
      
      Performance Testing. This is asserted using lambda function
      @assertTimeOut is used for this

      @Disabled is used to not to run the test case due to some scenario. @Ignored is for Junit 4

      Grouping specific type of Tests @Nested is part of Junit 5

