package se.johansavfalt.utils;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void random(){
        Matrix testMatrix1 = Matrix.random(5, 5);
        Matrix testMatrix2 = Matrix.random(5, 5);

        assertNotEquals(testMatrix1.getData()[1][1], testMatrix2.getData()[1][1], 0.01);

    }

    @Test
    public void transpose(){
        Matrix testMatrix = new Matrix(5, 5);
        testMatrix.setData(1, 2, 5.0);
        testMatrix.setData(0, 2, 7.0);

        Matrix result = testMatrix.transpose();

        assertEquals(result.getData()[2][1], 5.0, 0.01 );
        assertEquals(result.getData()[2][0], 7.0, 0.01 );

    }



    @Test
    public void fillwith_int(){
        Matrix testMatrix = new Matrix(5, 5);
        testMatrix.fillwith(4);
        for (int i = 0; i < testMatrix.getRows() ; i++) {
            for ( int j = 0; j < testMatrix.getColumns() ; j++) {
                assertEquals(testMatrix.getData()[i][j], 4, 0.01 );
                
            }
            
        }

    }

    
    @Test
    public void fillwith_double(){
        Matrix testMatrix = new Matrix(5, 5);
        testMatrix.fillwith(4.0);
        for (int i = 0; i < testMatrix.getRows() ; i++) {
            for ( int j = 0; j < testMatrix.getColumns() ; j++) {
                assertEquals(testMatrix.getData()[i][j], 4.0, 0.01 );
                
            }
            
        }

    }

    @Test
    public void plus(){
        Matrix testMatrix1 = new Matrix(5, 5);
        Matrix testMatrix2 = new Matrix(5, 5);

        testMatrix1.fillwith(4.0);
        testMatrix2.fillwith(1.0);

        Matrix result = testMatrix1.plus(testMatrix2);

        for (int i = 0; i < result.getRows() ; i++) {
            for ( int j = 0; j < result.getColumns() ; j++) {
                assertEquals(result.getData()[i][j], 5, 0.01 );
                
            }
            
        }

    }


    @Test
    public void plus_withadjustedmatrix(){
        Matrix testMatrix1 = new Matrix(4, 4);
        Matrix testMatrix2 = new Matrix(1, 4);

        testMatrix1.fillwith(4.0);
        testMatrix2.fillwith(1.0);

        Matrix result = testMatrix1.plus(testMatrix2);

        for (int i = 0; i < result.getRows() ; i++) {
            for ( int j = 0; j < result.getColumns() ; j++) {
                assertEquals(result.getData()[i][j], 5, 0.01 );
                
            }
            
        }

    }


    @Test
    public void minus(){
        Matrix testMatrix1 = new Matrix(5, 5);
        Matrix testMatrix2 = new Matrix(5, 5);

        testMatrix1.fillwith(4.0);
        testMatrix2.fillwith(1.0);

        Matrix result = testMatrix1.minus(testMatrix2);

        for (int i = 0; i < result.getRows() ; i++) {
            for ( int j = 0; j < result.getColumns() ; j++) {
                assertEquals(result.getData()[i][j], 3, 0.01 );
                
            }
            
        }

    }

    @Test
    public void minusConstant(){
        Matrix testMatrix1 = new Matrix(5, 5);

        testMatrix1.fillwith(4.0);

        Matrix result = testMatrix1.minusConstant(1.0);

        for (int i = 0; i < result.getRows() ; i++) {
            for ( int j = 0; j < result.getColumns() ; j++) {
                assertEquals(result.getData()[i][j], 3, 0.01 );
                
            }
            
        }

    }

    @Test
    public void times(){
        Matrix testMatrix1 = new Matrix(2, 2);
        Matrix testMatrix2 = new Matrix(2, 2);

        testMatrix1.fillwith(5.0);
        testMatrix2.fillwith(5.0);

        Matrix result = testMatrix1.times(testMatrix2);
        //result.show();

        for (int i = 0; i < result.getRows() ; i++) {
            for ( int j = 0; j < result.getColumns() ; j++) {
                assertEquals(result.getData()[i][j], 50.0, 0.01 );
                
            }
            
        }

    }

    @Test
    public void sum(){
        Matrix testMatrix = new Matrix(2, 2);
        testMatrix.fillwith(5.0);

        Matrix result = testMatrix.sum();
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                assertEquals(result.getData()[i][j], 20,0.01);
                
            }
            
        }

    }


    @Test
    public void max(){
        Matrix testMatrix = new Matrix(2, 2);
        testMatrix.fillwith(1.0);
        testMatrix.setData(1, 1, 4.0);

        Matrix result = testMatrix.max();
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                assertEquals(result.getData()[i][j], 4,0.01);
            }
        }

    }

    @Test
    public void product(){
        Matrix testMatrix1 = new Matrix(2, 2);
        Matrix testMatrix2 = new Matrix(2, 2);

        testMatrix1.fillwith(5.0);
        testMatrix2.fillwith(5.0);

        Matrix result = testMatrix1.product(testMatrix2);

        for (int i = 0; i < result.getRows() ; i++) {
            for ( int j = 0; j < result.getColumns() ; j++) {
                assertEquals(result.getData()[i][j], 10.0, 0.01 );
                
            }
            
        }

    }


    @Test
    public void hadamanproduct(){
        Matrix testMatrix1 = new Matrix(2, 2);
        Matrix testMatrix2 = new Matrix(2, 2);

        testMatrix1.fillwith(5.0);
        testMatrix2.fillwith(5.0);

        Matrix result = testMatrix1.hadamanproduct(testMatrix2);

        for (int i = 0; i < result.getRows() ; i++) {
            for ( int j = 0; j < result.getColumns() ; j++) {
                assertEquals(result.getData()[i][j], 25.0, 0.01 );
                
            }
            
        }

    }


    @Test
    public void timesConstant(){
        Matrix testMatrix1 = new Matrix(2, 2);

        testMatrix1.fillwith(5.0);

        Matrix result = testMatrix1.timesConstant(2.0);

        for (int i = 0; i < result.getRows() ; i++) {
            for ( int j = 0; j < result.getColumns() ; j++) {
                assertEquals(result.getData()[i][j], 10.0, 0.01 );
                
            }
            
        }

    }


}
