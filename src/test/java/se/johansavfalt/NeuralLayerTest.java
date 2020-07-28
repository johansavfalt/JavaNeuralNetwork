package se.johansavfalt;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.mockito.runners.MockitoJUnitRunner;

import se.johansavfalt.activation.Activation_Relu;
import se.johansavfalt.utils.Matrix;

/**
 * NeuralLayer
 * Unittest for the src/main/java/se/johansavfalt/NeuralLayer.java methods
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class NeuralLayerTest {


    // TODO should breakup these two methods so that we can more easily test them
    //  look at the C repo

    @Test
    public void layer_forward_propagation(){
        Matrix testMatrix = new Matrix(2, 2);
        testMatrix.fillwith(2.0);

        NeuralLayer neuralLayer = new NeuralLayer(2, 2, new Activation_Relu());
        //Matrix testing = neuralLayer.layer_forward_propagation(testMatrix);

    }

    @Test
    public void layer_backward_propagation(){
        Matrix testMatrix = new Matrix(2, 2);
        testMatrix.fillwith(2.0);

        NeuralLayer neuralLayer = new NeuralLayer(2, 2, new Activation_Relu());
        //Matrix testing = neuralLayer.layer_backward_propagation(testMatrix);

    }

    @Test
    public void updateParameters(){
        // TODO

    }

}
 
