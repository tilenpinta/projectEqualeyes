import java.util.*;

public class DE extends Algorithm {

    double F = 0.6;
    double CR = 0.5;
    int velikostPopulacije = 20;

    @Override
    public Solution execute(Problem problem) {
        problem.setCurrentFES(0);

        boolean isFeasible = false;

        if(velikostPopulacije < 4) {
            System.out.println("premala populacija!");
            return null;
        }

        int G = 0;

        int randomIndex;
        Random rand = new Random();
        List<List> vari = new ArrayList<>();
        List<List> variClone = new ArrayList<>();
        List<Double> a = new ArrayList<>();
        List<Double> b = new ArrayList<>();
        List<Double> c = new ArrayList<>();
        List<List> u = new ArrayList<>();
        double doubleU;

        List<Solution> solU = new ArrayList<>(velikostPopulacije);
        List<Solution> solList = new ArrayList<>(velikostPopulacije);
        List<Solution> solBest = new ArrayList<>(velikostPopulacije);

        for(int i = 0; i < velikostPopulacije; i++) {
            vari.add(problem.generateRandom(problem.min, problem.max, problem.d));
        }

        while (problem.getCurrentFES() < problem.getMaxFES()) {
            u.clear();
            //System.out.println("uuu: ");
            for(int i = 0; i < velikostPopulacije; i++) {
                //System.out.println("best: " + vari.get(i).toString());
                variClone.addAll(vari);
                variClone.remove(i);
                randomIndex = rand.nextInt(variClone.size());
                a = variClone.get(randomIndex);
                variClone.remove(randomIndex);
                randomIndex = rand.nextInt(variClone.size());
                b = variClone.get(randomIndex);
                variClone.remove(randomIndex);
                randomIndex = rand.nextInt(variClone.size());
                c = variClone.get(randomIndex);
                variClone.clear();

                List<Double> douU = new ArrayList<>();
                for(int j = 0; j < problem.getD(); j++) {

                    if(Math.random() <= CR) {
                        doubleU = a.get(j) + (F * (b.get(j) - c.get(j)));
                    }
                    else {
                        doubleU = (double)vari.get(i).get(j);
                    }
                    douU.add(doubleU);
                }
                u.add(douU);
            }
            solU.clear();
            solList.clear();
            for(int i = 0; i < velikostPopulacije; i++) {
                solU.add(new Solution(u.get(i), problem.eval(u.get(i))));
                solList.add(new Solution(vari.get(i), problem.eval(vari.get(i))));

                isFeasible = problem.isFeasible(solU.get(i));
                if(isFeasible) {
                    if(solU.get(i).getFitness() < solList.get(i).getFitness()) {
                        vari.set(i, u.get(i));
                        solBest.add(solU.get(i));
                    }
                    else {
                        solBest.add(solList.get(i));
                    }
                }
            }
        }

        double minValue = solBest.get(0).getFitness();
        int bestIndex = 0;
        for(int i=1;i<solBest.size();i++){
            if(solBest.get(i).getFitness() < minValue){
                minValue = solBest.get(i).getFitness();
                bestIndex = i;
            }
        }

        return solBest.get(bestIndex); //solList.getFitness() < solU.getFitness() ? solList : solU;
    }
}
