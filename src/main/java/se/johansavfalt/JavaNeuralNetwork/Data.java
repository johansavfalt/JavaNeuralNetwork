package se.johansavfalt.JavaNeuralNetwork;

import java.util.Collections;
import java.util.Random;

/**
 * Small class for the data
 */
public class Data {


    private final Matrix testdata;
    private final Matrix test;


    public Data(Matrix testdata, Matrix test) {
        this.testdata = testdata;
        this.test = test;
    }

    public Matrix getTestdata() {
        return testdata;
    }

    public Matrix getTest() {
        return test;
    }

    void reshuffledata()
    {
        int index;
        double[] temp;
        Random random = new Random();

        for (int i = testdata.getRows() - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = testdata.getData()[index];
            testdata.getData()[index] = testdata.getData()[i];
            testdata.getData()[i] = temp;

            temp = test.getData()[index];
            test.getData()[index] = test.getData()[i];
            test.getData()[i] = temp;
        }
    }

    public static void main(String[] args) {
        // Set data, "data" trainingdata and "test" is testdata
        Matrix data = new Matrix(new double[][]{{0, 1}, {1, 0}, {0, 0}, {1, 1}});
        Matrix test = new Matrix(new double[][]{{1}, {1}, {0}, {0}});
        Data trainingData = new Data(data, test);
        trainingData.reshuffledata();
    }



}
