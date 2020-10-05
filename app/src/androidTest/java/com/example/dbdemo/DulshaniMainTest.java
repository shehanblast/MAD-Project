package com.example.dbdemo;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DulshaniMainTest {

    @Rule
    public ActivityTestRule<DulshaniMain> dulshaniMainActivityTestRule = new ActivityTestRule<DulshaniMain>(DulshaniMain.class);

    private DulshaniMain dulshaniMain=null;

    @Before
    public void setUp() throws Exception {
        dulshaniMain =dulshaniMainActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view=dulshaniMain.findViewById(R.id.guestcount);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {

        dulshaniMain =null;
    }
}