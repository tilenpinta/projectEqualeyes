import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Problem {
    String name;
    int d;
    int maxFES, currentFES;
    double[] upperBounds;
    double[] lowerBounds;
    public double fitness;


    double min;
    double max;

    //ackley
    int CONSTANATA_A = 20;
    double CONSTANTA_B = 0.2;
    double PI = Math.PI;

    //schwefel
    double CONSTANA_SCH = 418.9829;

    //Rastrigin
    int CONSTANTA_RAS = 10;

    List<Double> bestResults = new ArrayList<>();

    public abstract double eval(List<Double> variables);

    public boolean isFeasible(Solution s) {
        for (int i = 0; i < s.getVariables().size(); i++) {
            if( s.getVariables().get(i) > this.max || s.getVariables().get(i) < this.min) {
                return false;
            }
        }
        return true;
    }

    private static final Random rnd = new Random();
    public List<Double> generateRandom(double min, double max, int d)
    {
        List<Double> variable = new ArrayList<>();
        for(int i = 0; i < d; i++){
            //variable.add(rnd.nextDouble());
            double randomValue = min + (max - min) * rnd.nextDouble();
            variable.add(randomValue);
        }
        return variable;
    }

    public void printRes(List<Double> variables, double fitness){
        if(this.bestResults.isEmpty() || this.bestResults.get(this.bestResults.size() - 1) > fitness) {
            this.bestResults.add(fitness);
            System.out.println(this.currentFES + ". " + variables.toString() + "=" + String.format("%.2f", fitness));
        }
    }

    public double[] getUpperBounds() {
        return upperBounds;
    }

    public int getD() {
        return d;
    }

    public double[] getLowerBounds() {
        return lowerBounds;
    }

    public String getName() {
        return name;
    }

    public int getMaxFES() {
        return maxFES;
    }

    public int getCurrentFES() {
        return currentFES;
    }

    public double getFitness() {
        return fitness;
    }

    public void setCurrentFES(int currentFES) {
        this.currentFES = currentFES;
    }
}
