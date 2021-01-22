import java.util.List;

public class Solution {
    private List<Double> variables;
    private Double fitness;

    public Solution(List<Double> variables, Double fitness) {
        this.variables = variables;
        this.fitness = fitness;
    }

    public List<Double> getVariables() {
        return variables;
    }

    public void setVariables(List<Double> variables) {
        this.variables = variables;
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }
}
