package se.johansavfalt.utils;

import java.util.List;
import java.util.Random;

/**
 * Small class for the data
 */
public class Data {


    private final List<Matrix> testdata;
    private final List<Matrix> test;
    private final Random random;
    private Matrix nextRandomTrainData;
    private Matrix nextRandomTestData;
    private int index;

    public Data(List<Matrix> testdata, List<Matrix> test) {
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

    public void shuffle(){
        index = random.nextInt(testdata.size());
        nextRandomTrainData = (Matrix) testdata.get(index);
        nextRandomTestData = (Matrix) test.get(index);

    }

}
