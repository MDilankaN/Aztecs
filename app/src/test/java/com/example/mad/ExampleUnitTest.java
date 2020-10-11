package com.example.mad;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Pass_Student PS;

    @Before
    public void passstudent(){
        PS = new Pass_Student();
    }
    @Test
    public void passStudentCalculation() {
        double Result = PS.calculation(5.0,3);
        assertEquals(60.00,60.00,0.01);

    }
}