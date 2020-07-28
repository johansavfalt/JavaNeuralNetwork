package se.johansavfalt.utils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.mockito.runners.MockitoJUnitRunner;

/**
 * DataTest
 * Unittest for the src/main/java/se/johansavfalt/utils/Data.java methods
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DataTest{
    @Test
    public void testGetTestData(){
        Matrix matrix1 = new Matrix(1, 1);
        matrix1.setData(0, 0, 1.0);

        Matrix matrix2 = new Matrix(1, 1);
        matrix2.setData(0, 0, 2.0);

        List<Matrix> data1 = new ArrayList<>();
        data1.add(matrix1);
        data1.add(matrix2);

        List<Matrix> data2 = new ArrayList<>();
        data2.add(matrix1);
        data2.add(matrix2);
    
        Data data = new Data(data1, data2);
        data.shuffle();
        assertEquals(data.getTestdata(), data.getTest());



    }
}

