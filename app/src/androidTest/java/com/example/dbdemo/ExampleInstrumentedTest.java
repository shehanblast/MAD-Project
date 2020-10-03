package com.example.dbdemo;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

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
        int result = eventPlanner.convertIntoHours(120);
        assertEquals(7200,result);
    }


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.dbdemo", appContext.getPackageName());
    }
}
