import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rosenbrock extends Problem{

    public Rosenbrock(int d, int maxFES) {
        name = "Rosenbrock";
        this.d = d;
        currentFES = 0;
        this.maxFES = maxFES;
        min = -5;
        max = 10;
    }

    @Override
    public double eval(List<Double> variables) {
        if(currentFES > maxFES) {
            System.out.println("maxFES over flow");
            return 0;
        }

        double fitness = 0.0;
        double sum = 0.0;
        double sumNext = 0.0;

        for (int i = 0; i < variables.size() - 1; i++) {
            sumNext = 100 * Math.pow((variables.get(i+1) - Math.pow(variables.get(i), 2)), 2) + Math.pow(variables.get(i)-1, 2);
            sum += sumNext;
        }

        fitness = sum;

        currentFES++;
        return fitness;
    }
}
