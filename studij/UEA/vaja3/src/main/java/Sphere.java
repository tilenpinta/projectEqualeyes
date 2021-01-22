import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sphere extends Problem {

    public Sphere(int d, int maxFES) {
        name = "Sphere";
        this.d = d;
        currentFES = 0;
        this.maxFES = maxFES;
        min = -5.12;
        max = 5.12;
    }

    @Override
    public double eval(List<Double> variables) {
        if(currentFES > maxFES) {
            System.out.println("maxFES over flow");
            return 0;
        }
        double fitness = 0.0;
        for (int i = 0; i < variables.size(); i++) {
            fitness += Math.pow(variables.get(i), 2);
        }

        currentFES++;
        return fitness;
    }
}
