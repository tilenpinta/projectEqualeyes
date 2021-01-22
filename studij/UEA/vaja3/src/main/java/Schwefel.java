import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Schwefel extends Problem {

    public Schwefel(int d, int maxFES) {
        name = "Schwefel";
        this.d = d;
        currentFES = 0;
        this.maxFES = maxFES;
        min = -500;
        max = 500;
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
            sum += variables.get(i) * Math.sin(Math.sqrt(Math.abs(variables.get(i))));
        }

        fitness = CONSTANA_SCH * d - sum;

        currentFES++;
        return fitness;
    }
}
