package se.johansavfalt.JavaNeuralNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class DL4J {

    public static void main(String[] args) {
        INDArray nd = Nd4j.create(new float[]{1,2,3,4},new int[]{2,2});
        INDArray nd2 = Nd4j.create(new float[]{5,6},new int[]{2,1}); //vector as column
        INDArray nd3 = Nd4j.create(new float[]{5,6},new int[]{2}); //vector as row
        System.out.println(nd);
        System.out.println(nd2);
        System.out.println(nd3);
    }
}
