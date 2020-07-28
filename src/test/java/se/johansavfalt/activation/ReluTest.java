package se.johansavfalt.activation;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.mockito.runners.MockitoJUnitRunner;

import se.johansavfalt.utils.Matrix;

/**
 * ReluTest 
 * Unittest for the src/main/java/se/johansavfalt/activation/Activation_Relu.java methods
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ReluTest{

    @Test
    public void activation(){
        Matrix testMatrix_1 = new Matrix(2, 2);
        Matrix testMatrix_2 = new Matrix(2, 2);

        testMatrix_1.fillwith(5.0);
        testMatrix_2.fillwith(-5.0);


        Activation_Relu reluTest = new Activation_Relu();

        Matrix result1 = reluTest.activation_derivative(testMatrix_1);
        Matrix result2 = reluTest.activation_derivative(testMatrix_1);

        for (int i = 0; i < result1.getRows(); i++) {
            for (int j = 0; j < result1.getColumns(); j++) {
                assertEquals(result1.getData()[i][j], 1.0, 0.01 );
                
            }
            
        }
        for (int i = 0; i < result2.getRows(); i++) {
            for (int j = 0; j < result2.getColumns(); j++) {
                assertEquals(result2.getData()[i][j], 1.0, 0.01 );
                
            }
            
        }

    }

    @Test
    public void activation_derivative(){
        Matrix testMatrix_1 = new Matrix(2, 2);
        Matrix testMatrix_2 = new Matrix(2, 2);

        testMatrix_1.fillwith(5.0);
        testMatrix_2.fillwith(-5.0);


        Activation_Relu reluTest = new Activation_Relu();

        Matrix result1 = reluTest.activation_derivative(testMatrix_1);
        Matrix result2 = reluTest.activation_derivative(testMatrix_1);

        for (int i = 0; i < result1.getRows(); i++) {
            for (int j = 0; j < result1.getColumns(); j++) {
                assertEquals(result1.getData()[i][j], 1.0, 0.01 );
                
            }
            
        }
        for (int i = 0; i < result2.getRows(); i++) {
            for (int j = 0; j < result2.getColumns(); j++) {
                assertEquals(result2.getData()[i][j], 1.0, 0.01 );
                
            }
            
        }
 

    }
}
