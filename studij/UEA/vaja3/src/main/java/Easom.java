import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Easom extends Problem {

    public Easom(int d, int maxFES) {
        name = "Easom";
        this.d = d;
        currentFES = 0;
        this.maxFES = maxFES;
        min = -100;
        max = 100;
    }

    @Override
    public double eval(List<Double> variables) {

        if(currentFES > maxFES) {
            System.out.println("maxFES over flow");
            return 0;
        }

        double fitness = 0.0;
        double temp1 = 0.0;
        double temp2 = 0.0;

        temp1 = - Math.cos(variables.get(0)) * Math.cos(variables.get(1));
        temp2 = Math.exp( - Math.pow((variables.get(0) - PI), 2) - Math.pow(variables.get(1) - PI, 2));

        fitness = temp1 * temp2;
        currentFES++;
        return fitness;
    }
}
