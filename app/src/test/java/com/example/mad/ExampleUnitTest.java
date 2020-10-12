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
    private Marksview MV = new Marksview();

    @Before
    public void passstudent(){
        PS = new Pass_Student();
    }
    @Test
    public void passStudentCalculation() {
        double Result = PS.calculation(5.0,3);
        assertEquals(60.00,Result,0.01);

    }
    @Test
    public void passStudentCalculation1() {
        double Result = PS.calculation(10.0,3);
        assertEquals(30.00,Result,0.01);

    }@Test
    public void passStudentCalculation2() {
        double Result = PS.calculation(8.0,6);
        assertEquals(75.00,Result,0.01);

    }@Test
    public void passStudentCalculation3() {
        double Result = PS.calculation(13.0,6);
        assertEquals(46.15,Result,0.01);

    }



    //test calcPresentage

    @Test
    public void CalcPresentage(){
        int NoOFQues = 20 , Correct = 15;
        Double result = MV.CalcTotalPresentage(Correct,NoOFQues);
        assertEquals(75.00,result,0.001);
    }
    @Test
    public void CalcPresentage1(){
        int NoOFQues = 30 , Correct = 15;
        Double result = MV.CalcTotalPresentage(Correct,NoOFQues);
        assertEquals(50.00,result,0.001);
    } @Test
    public void CalcPresentage2(){
        int NoOFQues = 50 , Correct = 35;
        Double result = MV.CalcTotalPresentage(Correct,NoOFQues);
        assertEquals(70.00,result,0.001);
    } @Test
    public void CalcPresentage3(){
        int NoOFQues = 60 , Correct = 60;
        Double result = MV.CalcTotalPresentage(Correct,NoOFQues);
        assertEquals(100.00,result,0.001);
    }


}