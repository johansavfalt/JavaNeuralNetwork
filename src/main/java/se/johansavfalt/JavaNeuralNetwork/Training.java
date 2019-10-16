package se.johansavfalt.JavaNeuralNetwork;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles the training of the network (feedforward and backward) , computes the loss and updates the layers
 */
public class Training {


    private final Data trainingData;
    private final int printResult;
    private final XYSeries XYSeries;
    private ArrayList<NeuralLayer> NeuralNetwork;
    private double learningRate;
    private int epochs;
    private Matrix deltaLoss;

    /**
     * Contructor, including the network structure and hyperparameters
     *  @param NeuralNetwork
     * @param learningRate
     * @param epochs
     * @param trainingData
     * @param printResult
     */
    public Training(ArrayList<NeuralLayer> NeuralNetwork, double learningRate, int epochs, Data trainingData, int printResult){
        this.NeuralNetwork = NeuralNetwork;
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.trainingData = trainingData;
        this.printResult = printResult;
        this.XYSeries = new XYSeries("Data");

    }

    /**
     * Training
     */
    public void train(){
        for (int i = 0; i < epochs - 1; i++) {
            this.trainingData.shuffle();
            Matrix forwardPass = this.forwardPopagation(this.trainingData.getTestdata());
            deltaLoss = compute_cross_entropy_loss(forwardPass, this.trainingData.getTest(), true);
            this.backwardPropagation(deltaLoss);
            this.updateParameters();

            if(i % printResult == 0){
                Matrix loss = compute_cross_entropy_loss(this.forwardPopagation(this.trainingData.getTestdata()),this.trainingData.getTest()
                        , false);
                XYSeries.add(i,loss.getData()[0][0]);

            }

        }

        final XYSeriesCollection data = new XYSeriesCollection(XYSeries);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Trainingloss",
                "X",
                "Y",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        ApplicationFrame applicationFrame = new ApplicationFrame("title");
        applicationFrame.setContentPane(chartPanel);
        applicationFrame.pack();
        RefineryUtilities.centerFrameOnScreen(applicationFrame);
        applicationFrame.setVisible(true);



    }

    /**
     * Updates
     */
    private void updateParameters() {
        for (NeuralLayer layer : NeuralNetwork) {
            layer.updateParameters(this.learningRate);
        }
    }

    /**
     * Backward propagation
     * @param deltaLoss
     * @return
     */

    private Matrix backwardPropagation(Matrix deltaLoss) {

        for (int i = NeuralNetwork.size()-1; i >=0 ; i--) {
            Matrix layerInput = deltaLoss;
            deltaLoss = NeuralNetwork.get(i).layer_backward_propagation(layerInput);

        }
        return deltaLoss;
    }

    /**
     * Forward propagation
     * @param data
     * @return
     */
    private Matrix forwardPopagation(Matrix data) {
        for (NeuralLayer layer : NeuralNetwork) {
            Matrix layerInput = data;
            data = layer.layer_forward_propagation(layerInput);
        }
        return data;
    }

    /**
     * Computes Cross-Entropy Loss (Actual or Derivative)
     * @param data
     * @param test
     * @param derivative
     * @return
     */
    private static Matrix compute_cross_entropy_loss(Matrix data, Matrix test, boolean derivative) {
        if (derivative){
            return data.minus(test);
        } else {
            return cross_entropy_loss(data, test);
        }

    }

    /**
     * Cross Entropy Value
     * @param predictDistribution
     * @param trueDistribution
     * @return
     */
    private static Matrix cross_entropy_loss(Matrix predictDistribution, Matrix trueDistribution) {
        double error = 0.0;
        int M = predictDistribution.getRows();
        List<Double> batchResult = new ArrayList<>();


        for (int i = 0; i < M; i++) {
            double singleTrue = trueDistribution.getData()[i][0];
            double singlePred = predictDistribution.getData()[i][0];

            if (singleTrue == 1){
                error = Math.log(singlePred)* -1.0;
            } else {
                error =  Math.log(1.0 - singlePred) * -1.0;
            }
            batchResult.add(error);


        }
        Matrix result = new Matrix(1,1);
        double doublevalue = (1.0/M * batchResult.stream().mapToDouble(f -> f.doubleValue()).sum());
        result.setData(0,0, doublevalue);
        return result;
    }


}
