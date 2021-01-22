import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZeroSum extends Problem{

    public ZeroSum(int d, int maxFES) {
        name = "ZeroSum";
        this.d = d;
        currentFES = 0;
        this.maxFES = maxFES;
        min = -10;
        max = 10;
    }

    @Override
    public double eval(List<Double> variables) {
        if(currentFES > maxFES) {
            System.out.println("maxFES over flow");
            return 0;
        }

        double fitness = 0.0;
        double x = 0;

        for (int i = 0; i < variables.size() - 1; i++) {
            x += variables.get(i);
            //fitness += 1 + Math.pow((10000 * Math.abs(variables.get(i))), 0.5);
            //System.out.println(x);
        }

        if(x <= 0.0000001) {
            //System.out.println("asdasd");
            currentFES++;
            return fitness;
        } else {
            fitness = 1 + Math.pow((10000 * Math.abs(x)), 0.5);
        }
        currentFES++;
        return fitness;
    }
}
