import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PenHolder extends Problem {

    public PenHolder(int d, int maxFES) {
        name = "PenHolder";
        this.d = d;
        currentFES = 0;
        this.maxFES = maxFES;
        min = -11;
        max = 11;
    }

    @Override
    public double eval(List<Double> variables) {

        if(currentFES > maxFES) {
            System.out.println("maxFES over flow");
            return 0;
        }

        double fitness = 0.0;

        fitness = - Math.exp(- 1 / Math.abs(Math.cos(variables.get(0)) * Math.cos(variables.get(1)) * Math.exp(Math.abs( 1 - Math.sqrt(Math.pow(variables
        .get(0), 2) + Math.pow(variables.get(1), 2)) / PI ))));
        currentFES++;
        return fitness;
    }
}
