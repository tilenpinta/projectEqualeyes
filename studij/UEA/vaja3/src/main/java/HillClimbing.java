import java.util.ArrayList;
import java.util.List;

public class HillClimbing extends Algorithm {

    private Solution bestNeighbor(Problem prob, Solution sol) {

        Solution best = new Solution(sol.getVariables(), sol.getFitness());

        int size = sol.getVariables().size();
        boolean isFeasible = false;

        Solution temp = null;
        for(int i = 0; i < size; i++) {

            for (double y = -0.0001; y <= 0.0001; y = y + 0.0002) {
                List<Double> list = new ArrayList<>();
                list.addAll(sol.getVariables());

                list.set(i, sol.getVariables().get(i) + y);

                temp = new Solution(list, prob.eval(list));
                isFeasible = prob.isFeasible(temp);
                if (best.getFitness() > temp.getFitness() && isFeasible) {
                    best.setVariables(temp.getVariables());
                    best.setFitness(temp.getFitness());
                }
            }
        }
        return best;
    }

    @Override
    public Solution execute(Problem problem) {
        problem.setCurrentFES(0);

        List<Double> vari = new ArrayList<>();
        vari = problem.generateRandom(problem.min, problem.max, problem.d);

        Solution neighbor = new Solution(vari, problem.eval(vari));

        while(problem.getCurrentFES() < problem.getMaxFES()) {
            neighbor = bestNeighbor(problem, neighbor);
        }

        return neighbor;
    }
}
