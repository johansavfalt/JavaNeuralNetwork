package se.johansavfalt.JavaNeuralNetwork;

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


    public void reshuffledata() {
        shuffle(this.testdata);
        shuffle(this.test);
    }



    private void shuffle(Matrix data) {
        double[] lastElement = data.getData()[data.getData().length-1];

        for (int i = data.getData().length-1; i > 0 ; --i) {
            data.getData()[i] = data.getData()[i -1];
        }

        data.getData()[0] = lastElement;
    }


}
