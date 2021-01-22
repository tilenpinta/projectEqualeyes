import Util.DataParser;
import Util.RandomUtils;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.*;
import weka.classifiers.trees.lmt.LogisticBase;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    static final String fileName = "paketnik.arff";
    static final String fileNameTest = "test.arff";

    static Instances instances;
    static AbstractClassifier[] abstractClassifiers;
    static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static Dimension PROGRAM_DIMENSIONS = new Dimension(1000, 600);

    public static void main(String[] args) throws Exception {
        RandomUtils.setSeedFromTime();
        instances = DataParser.getInstances(fileName);
        assert instances != null;
        instances.randomize(RandomUtils.getRandom());
        test();
    }

    private static void test() throws Exception {
        assert instances != null;
        instances.setClassIndex(instances.numAttributes() - 1);
//        instances.setClassIndex();

        //splitAndClassify(instances, 0.66f);

        showTree(instances);
        //classifyByFoldsOthers(instances, 2);
        testSetWithOriginalSet(instances);
//        splitAndClassify(instances, 0.9f);
    }


    private static void showTree(Instances instances) throws Exception {
        J48 j48 = new J48();

        j48.buildClassifier(instances);
//        Evaluation eval = new Evaluation(instances);
//        eval.crossValidateModel(j48, instances, numFolds, new Random(RandomUtils.getSeed()));


        JFrame jf = new JFrame("Weka Classifier Tree Visualizer: J48");
        jf.setSize(PROGRAM_DIMENSIONS);
        jf.setLocation((int) (SCREEN_SIZE.getWidth() - PROGRAM_DIMENSIONS.getWidth()) / 2,
                (int) (SCREEN_SIZE.getHeight() - PROGRAM_DIMENSIONS.getHeight()) / 2);

        TreeVisualizer tv = new TreeVisualizer(null, j48.graph(), new PlaceNode2());
        jf.getContentPane().add(tv, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jf.dispose();
            }
        });
        jf.setVisible(true);
        tv.fitToScreen();

   //     System.out.println("Algorithm name: " + j48.getClass());
 //       print(eval);
    }

    private static void classifyByFoldsOthers(Instances instances, int numFolds) throws Exception {
        initializeClassifiers();
        for (int i = 0; i < abstractClassifiers.length; i++) {
            System.out.println("#####################################");
            System.out.println("Algorithm name: " + abstractClassifiers[i].getClass());
            abstractClassifiers[i].buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);
            eval.crossValidateModel(abstractClassifiers[i], instances, numFolds, new Random(RandomUtils.getSeed()));
            print(eval);

        }

    }

    private static void splitAndClassify(Instances trainingSet, float percentage) throws Exception {
        int trainSize = Math.round(trainingSet.numInstances() * percentage);
        int testSize = trainingSet.numInstances() - trainSize;
        initializeClassifiers();
        Instances train = new Instances(trainingSet, 0, trainSize);
        Instances test = new Instances(trainingSet, trainSize, testSize);
        eval(train, test);
    }

    private static void testSetWithOriginalSet(Instances trainingSet) throws Exception {
        Instances test = DataParser.getInstances(fileNameTest);
        initializeClassifiers();
        test.setClassIndex(test.numAttributes() - 1);
        eval(trainingSet, test);

    }

    private static void print(Evaluation eval) {
        System.out.println(eval.toSummaryString("\nResults\n=======\n", true));
        System.out.println("F measure: " + String.format("%.2f", eval.fMeasure(1)) + " Precision: " +
                String.format("%.2f", eval.precision(1)) + " Recall: " + String.format("%.2f", eval.recall(1)));
        double[][] matrix = eval.confusionMatrix();

        System.out.println();
        System.out.println("Confusion matrix: ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }

//        System.out.println("True positive   : " + eval.numTruePositives(0));
//        System.out.println("False positive  : " + eval.numFalsePositives(0));
//        System.out.println("True negative   : " + eval.numTrueNegatives(0));
//        System.out.println("False negative  : " + eval.numFalseNegatives(0));
//        System.out.println("");
//        System.out.println("True positive  : " + eval.numTruePositives(1));
//        System.out.println("False positive : " + eval.numFalsePositives(1));
//        System.out.println("True negative  : " + eval.numTrueNegatives(1));
//        System.out.println("False negative : " + eval.numFalseNegatives(1));
    }

    private static void eval(Instances trainingSet, Instances testSet) throws Exception {

        for (int i = 0; i < abstractClassifiers.length; i++) {
            System.out.println("###############################################");
            System.out.println(abstractClassifiers[i].getClass());
            abstractClassifiers[i].buildClassifier(trainingSet);
            Evaluation eval = new Evaluation(trainingSet);
            eval.evaluateModel(abstractClassifiers[i], testSet);
            print(eval);
        }

    }

    private static void initializeClassifiers() {
        abstractClassifiers = new AbstractClassifier[11];
        abstractClassifiers[0] = new BayesNet();
        abstractClassifiers[1] = new JRip();
        abstractClassifiers[2] = new OneR();
        abstractClassifiers[3] = new RandomTree();
        abstractClassifiers[4] = new RandomForest();
        abstractClassifiers[5] = new LogisticBase();
        abstractClassifiers[6] = new IBk();
        abstractClassifiers[7] = new HoeffdingTree();
        abstractClassifiers[8] = new LMT();
        abstractClassifiers[9] = new KStar();
        abstractClassifiers[10] = new J48();


    }

}
