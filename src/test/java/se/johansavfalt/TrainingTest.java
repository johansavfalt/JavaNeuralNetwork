package se.johansavfalt;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


import org.mockito.runners.MockitoJUnitRunner;

import se.johansavfalt.utils.Matrix;

/**
 * NeuralLayer
 * Unittest for the src/main/java/se/johansavfalt/NeuralLayer.java methods
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TrainingTest {

    @Test
    public void cross_entropy_loss(){

        Matrix predictDistribution = new Matrix(4, 1);
        Matrix trueDistribution = new Matrix(1, 4);

        predictDistribution.setData(0, 0, 0.4333);
        predictDistribution.setData(1, 0, 0.3231);
        predictDistribution.setData(2, 0, 0.3321);
        predictDistribution.setData(3, 0, 0.1131);

        trueDistribution.setData(0, 0, 0.0);
        trueDistribution.setData(0, 1, 1.0);
        trueDistribution.setData(0, 2, 0.0);
        trueDistribution.setData(0, 3, 1.0);

        assertEquals(Training.cross_entropy_loss(predictDistribution, trueDistribution).getData()[0][0], 1.0702, 0.5);


    }

    public void cross_entropy_loss_derivative(){
        Matrix predictDistribution = new Matrix(4, 1);
        Matrix trueDistribution = new Matrix(1, 4);

        predictDistribution.setData(0, 0, 14.332);
        predictDistribution.setData(1, 0, 13.323);
        predictDistribution.setData(2, 0, 88.332);
        predictDistribution.setData(3, 0, 12.113);

        trueDistribution.setData(0, 0, 0.0);
        trueDistribution.setData(0, 1, 1.0);
        trueDistribution.setData(0, 2, 0.0);
        trueDistribution.setData(0, 3, 1.0);

        Matrix result = 
            Training.compute_cross_entropy_loss(predictDistribution, trueDistribution, true);

        assertEquals(result.getData()[0][0], 14.332, 0.001);
        assertEquals(result.getData()[1][0], 12.323, 0.001);
        assertEquals(result.getData()[2][0], 88.332, 0.001);
        assertEquals(result.getData()[3][0], 11.113, 0.001);


    }
}


