import Utility.RandomUtils;
import problems.TSP;

import java.util.ArrayList;
import java.util.List;

public class TSPTest {

    static final int executions = 50;
    static final double cr = 0.8;
    static final double pm = 0.1;
    static final int popSize = 100;
    static int maxEvaluations = 1000000;


    public static void main(String[] args) {
        String path = "dca1389.tsp";
        RandomUtils.setSeedFromTime();
        List<TSP.Tour> best = new ArrayList<>();
        GA ga = new GA(popSize, cr, pm);

        for (int i = 0; i < executions; i++) {
            System.out.println(i);
            best.add(ga.execute(new TSP(path, maxEvaluations)));
        }
        ////////////////////////////////
        double sum = 0;
        double avg;
        double std = 0;
        double min = 999999999;

        for (int i = 0; i < executions; i++) {
            sum += best.get(i).getDistance();
            if (best.get(i).getDistance() < min) {
                min = best.get(i).getDistance();
            }
        }
        avg = sum / executions;

        for (int i = 0; i < executions; i++) {
            std += Math.pow(best.get(i).getDistance() - avg, 2);
        }

        std = Math.sqrt(std / executions);


        System.out.println(path + "\nMIN: " + (int) min + "\nAVG: " + (int) avg + "\nSTD: " + (int) std + "\nSEED: " + RandomUtils.getSeed() + "\n");
    }
}
