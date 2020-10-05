package com.example.dbdemo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private EventPlanner eventPlanner;

    @Before
    public void setUp(){
        eventPlanner = new EventPlanner();
    }

    @Test
    public void hours(){
        int result = eventPlanner.convertIntoHours(120);
        assertEquals(2,result);
    }

    @Test
    public void seconds(){
        int result = eventPlanner.convertIntoSeconds(120);
        assertEquals(7200,result);
    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}