import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rastrigin extends Problem {

    public Rastrigin(int d, int maxFES) {
        name = "Rastrigin";
        this.d = d;
        currentFES = 0;
        this.maxFES = maxFES;
        min = -5;
        max = 5;
    }

    @Override
    public double eval(List<Double> variables) {
        if(currentFES > maxFES) {
            System.out.println("maxFES over flow");
            return 0;
        }

        double fitness = 0.0;
        double sum = 0.0;

        for (int i = 0; i < variables.size(); i++) {
            sum += Math.pow(variables.get(i), 2) - CONSTANTA_RAS * Math.cos(2 * PI * variables.get(i));
        }

        fitness = CONSTANTA_RAS * d + sum;
        currentFES++;
        return fitness;
    }
}
