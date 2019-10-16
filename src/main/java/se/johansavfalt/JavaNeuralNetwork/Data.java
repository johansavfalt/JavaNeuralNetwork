package se.johansavfalt.JavaNeuralNetwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Small class for the data
 */
public class Data {


    private final List testdata;
    private final List test;
    private final Random random;
    private Matrix nextRandomTrainData;
    private Matrix nextRandomTestData;
    private int index;

    public Data(List testdata, List test) {
        this.testdata = testdata;
        this.test = test;
        this.random = new Random();
    }

    public Matrix getTestdata() {
        return nextRandomTrainData;
    }

    public Matrix getTest() {
        return nextRandomTestData;
    }

    void shuffle(){
        index = random.nextInt(4);
        nextRandomTrainData = (Matrix) testdata.get(index);
        nextRandomTestData = (Matrix) test.get(index);

    }
//
//    void reshuffledata()
//    {
//        int index;
//        double[] temp;
//        Random random = new Random();
//
//        for (int i = testdata.getRows() - 1; i > 0; i--)
//        {
//            index = random.nextInt(i + 1);
//            temp = testdata.getData()[index];
//            testdata.getData()[index] = testdata.getData()[i];
//            testdata.getData()[i] = temp;
//
//            temp = test.getData()[index];
//            test.getData()[index] = test.getData()[i];
//            test.getData()[i] = temp;
//        }
//    }

    public static void main(String[] args) {
        // Set data, "data" trainingdata and "test" is testdata
        List<Matrix> listData = new ArrayList<>();
        List<Matrix> listDataTest = new ArrayList<>();
        listData.add(new Matrix(new double[][]{{0, 1}}));
        listData.add(new Matrix(new double[][]{{1, 0}}));
        listData.add(new Matrix(new double[][]{{0, 0}}));
        listData.add(new Matrix(new double[][]{{1, 1}}));

        listDataTest.add(new Matrix(new double[][]{{1}}));
        listDataTest.add(new Matrix(new double[][]{{1}}));
        listDataTest.add(new Matrix(new double[][]{{0}}));
        listDataTest.add(new Matrix(new double[][]{{0}}));

//        Matrix data = new Matrix(new double[][]{{0, 1}, {1, 0}, {0, 0}, {1, 1}});
//        Matrix test = new Matrix(new double[][]{{1}, {1}, {0}, {0}});
        Data trainingData = new Data(listData, listDataTest);
        trainingData.shuffle();
        trainingData.getTestdata();
        trainingData.getTest();
        trainingData.shuffle();
        trainingData.getTestdata();
        trainingData.getTest();

    }



}
