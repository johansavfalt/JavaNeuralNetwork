package se.johansavfalt.utils;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;

import org.mockito.runners.MockitoJUnitRunner;

/**
 * MatrixTest
 * Unittest for the src/main/java/se/johansavfalt/utils/Matrix.java methods
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MatrixTest {

    /**
     * Test the contructor of the matrix
     *
     **/
    @Test
    public void testContructor() {

        Matrix testMatrix = new Matrix(5, 5);

        assertEquals(testMatrix.getColumns(), 5);
        assertEquals(testMatrix.getRows(), 5);
    
    }

     /**
     * Test setting datapoint of the matrix
     *
     **/
    @Test
    public void setData() {

        Matrix testMatrix = new Matrix(5, 5);
        testMatrix.setData(1, 4, 1.0);
        assertEquals(testMatrix.getData()[1][4], 1.0, 0.5);
    
    }

    

}
