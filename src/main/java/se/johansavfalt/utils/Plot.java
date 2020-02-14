package se.johansavfalt.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class Plot extends ApplicationFrame {

    public Plot(final String title) {
        super(title);
        final XYSeries series = new XYSeries("Random Data");
        series.add(1, 0.2);
        series.add(2, 0.4);
        series.add(3, 0.5);
        series.add(4, 0.6);
        series.add(5, 0.7);
        series.add(6, 0.8);
        series.add(7, 0.8);
        series.add(8, 0.2);
        series.add(9, 0.2);
        series.add(10, 0.3);

//        series.add(5.0, 694.1);
//        series.add(4.0, 100.0);
//        series.add(12.5, 734.4);
//        series.add(17.3, 453.2);
//        series.add(21.2, 500.2);
//        series.add(21.9, null);
//        series.add(25.6, 734.4);
//        series.add(30.0, 453.2);
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Series Demo",
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
        setContentPane(chartPanel);

    }

// ****************************************************************************
// * JFREECHART DEVELOPER GUIDE                                               *
// * The JFreeChart Developer Guide, written by David Gilbert, is available   *
// * to purchase from Object Refinery Limited:                                *
// *                                                                          *
// * http://www.object-refinery.com/jfreechart/guide.html                     *
// *                                                                          *
// * Sales are used to provide funding for the JFreeChart project - please    *
// * support us so that we can continue developing free software.             *
// ****************************************************************************

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(final String[] args) {

        final Plot demo = new Plot("");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
