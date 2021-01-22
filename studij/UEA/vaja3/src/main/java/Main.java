import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        //DC
        ///////////////////////////////////
        int d = 2; //kelko size
        int maxFES = 20000; //kelko krat
        int maxTime = 50; //ponovitev
        Problem problem;
        Algorithm algo = new DE();
        Solution solution;

        double sum = 0;
        double avg;
        double std = 0;
        double min = 99999999;

        double[] allFitness = new double[maxTime];

        List<Problem> problems = new ArrayList<Problem>();

        problems.add(new Sphere(d, maxFES));
        problems.add(new Ackley(d, maxFES));
        problems.add(new Schwefel(d, maxFES));
        problems.add(new Rastrigin(d, maxFES));
        problems.add(new Rosenbrock(d, maxFES));
        problems.add(new ZeroSum(d, maxFES));
        problems.add(new PenHolder(d, maxFES));
        problems.add(new Easom(d, maxFES));

        //all
        for(int j = 0; j < problems.size(); j++) {
            System.out.println(problems.get(j).getName());
            for (int i = 0; i < maxTime; i++) {
                problem = problems.get(j);
                solution = algo.execute(problem);

                //System.out.println(solution.getVariables());

                allFitness[i] = solution.getFitness();
                sum += solution.getFitness();
                if (solution.getFitness() < min) {
                    min = solution.getFitness();
                }
            }
            avg = sum / maxTime;

            for (int i = 0; i < maxTime; i++) {
                std += Math.pow(allFitness[i] - avg, 2);
            }

            std = Math.sqrt(std / maxTime);

            System.out.println("min: " + min + " avg: " + avg + " std: " + std);

            sum = 0;
            std = 0;
            min = 99999999;
        }
    }


        /* HILL CLIMBING
        ///////////////////////////////////
        int d = 2; //kelko size
        int maxFES = 10000; //kelko krat
        int maxTime = 100;
        Problem problem;
        Algorithm algo = new HillClimbing();
        Solution solution;

        double sum = 0;
        double avg;
        double std = 0;
        double min = 99999999;

        double[] allFitness = new double[maxTime];

        List<Problem> problems = new ArrayList<Problem>();
        problems.add(new Sphere(d, maxFES));
        problems.add(new Ackley(d, maxFES));
        problems.add(new Schwefel(d, maxFES));
        problems.add(new Rastrigin(d, maxFES));
        problems.add(new Rosenbrock(d, maxFES));
        problems.add(new ZeroSum(d, maxFES));
        problems.add(new PenHolder(d, maxFES));
        problems.add(new Easom(d, maxFES));

        //all
        for(int j = 0; j < problems.size(); j++) {
            System.out.println(problems.get(j).getName());
            for(int i = 0; i < maxTime; i++) {
                problem = problems.get(j);
                solution = algo.execute(problem);

                allFitness[i] = solution.getFitness();
                sum += solution.getFitness();
                if(solution.getFitness() < min){
                    min = solution.getFitness();
                }
            }
            avg = sum / maxTime;

            for(int i = 0; i < maxTime; i++) {
                std += Math.pow(allFitness[i] - avg, 2);
            }

            std = Math.sqrt(std/maxTime);

            System.out.println("min: " + min + " avg: " + avg + " std: " + std);

            sum = 0;
            std = 0;
            min = 99999999;
        }
     */
}
