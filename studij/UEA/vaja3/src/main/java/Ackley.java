import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ackley extends Problem{

    public Ackley(int d, int maxFES) {
        name = "Ackley";
        this.d = d;
        currentFES = 0;
        this.maxFES = maxFES;
        min = -32;
        max = 32;
    }

    @Override
    public double eval(List<Double> variables) {

        if(currentFES > maxFES) {
            System.out.println("maxFES over flow");
            return 0;
        }

        double fitness = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        double term1 = 0.0;
        double term2 = 0.0;

        for (int i = 0; i < variables.size(); i++) {
            sum1 += Math.pow(variables.get(i), 2);
            sum2 += Math.cos((PI*2) * variables.get(i));
        }

        term1 = -CONSTANATA_A * Math.exp(-CONSTANTA_B * Math.sqrt(sum1/d));
        term2 = -(Math.exp(sum2/d));

        fitness = term1 + term2 + CONSTANATA_A + Math.exp(1);
        currentFES++;
        return fitness;
    }
}
