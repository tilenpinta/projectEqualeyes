import Utility.RandomUtils;
import problems.TSP;

import java.util.*;

public class GA {

    int popSize; //populacija
    double cr; //crossover probability
    double pm; //mutation probability

    ArrayList<TSP.Tour> population;
    ArrayList<TSP.Tour> offspring;

    public GA(int popSize, double cr, double pm) {
        this.popSize = popSize;
        this.cr = cr;
        this.pm = pm;
        population = new ArrayList<>();
        offspring = new ArrayList<>();
    }

    public TSP.Tour execute(TSP problem) {

        population.clear();

        TSP.Tour best = null;

        for (int i = 0; i < popSize; i++) {
            TSP.Tour newTour = problem.generateTour();
            problem.evaluate(newTour);
            population.add(newTour);
            //best
            if(best == null || best.getDistance() > population.get(i).getDistance()) {
                best = population.get(i);
            }
         }

        while (problem.getNumberOfEvaluations() < problem.getMaxEvaluations()) {

            List<Integer> swaped = new ArrayList<>();
            offspring.add(best.clone());

            while (offspring.size() < popSize) {
                TSP.Tour parent1 = tournamentSelection();
                TSP.Tour parent2 = tournamentSelection();

                if (RandomUtils.nextDouble() < cr) {
                    TSP.Tour[] children = pmx(parent1, parent2);
                    offspring.add(children[0]);
                    swaped.add(offspring.size() - 1); //kateri se je spremenil
                    if (offspring.size() < popSize) {
                        offspring.add(children[1]);
                        swaped.add(offspring.size() - 1);
                    }
                } else {
                    offspring.add(parent1.clone());
                    if (offspring.size() < popSize)
                        offspring.add(parent2.clone());
                }
            }

            for (int i = 0; i < offspring.size(); i++) {
                if (RandomUtils.nextDouble() < pm) {
                    swaped.add(i); // mutacija na tem indexu, zapomnimo si spremenjenega
                    swapMutation(offspring.get(i));
                }
            }

            for (int i = 0; i < swaped.size(); i++) {
               problem.evaluate(offspring.get(swaped.get(i)));
            }
            population = new ArrayList<>(offspring);

            best = population.get(0);

            for (int i = 1; i < population.size(); i++) {
                if(best.getDistance() > population.get(i).getDistance()) {
                    best = population.get(i);
                }
            }

            offspring.clear();
        }
        return best;
    }

    private void swapMutation(TSP.Tour off) {
        int first = RandomUtils.nextInt(off.getDimension());
        int second = RandomUtils.nextInt(off.getDimension());

        while (first == second) {
            second = RandomUtils.nextInt(off.getDimension());
        }

        TSP.Tour cloned = off.clone();

        off.setCity(first, cloned.getPath()[second]);
        off.setCity(second, cloned.getPath()[first]);
    }

    private TSP.Tour[] pmx(TSP.Tour parent1, TSP.Tour parent2) {

        int n = parent1.getDimension();
        if (n != parent2.getDimension()) {
            System.out.println("size ni dober");
            return null;
        }

        int cuttingPoint1 = RandomUtils.nextInt(n);
        int cuttingPoint2 = RandomUtils.nextInt(n);

        if (cuttingPoint1 == cuttingPoint2) {
            cuttingPoint2 = n - 1;
        } else if (cuttingPoint1 > cuttingPoint2) {
            int swap = cuttingPoint1;
            cuttingPoint1 = cuttingPoint2;
            cuttingPoint2 = swap;
        }

        TSP.Tour[] tspArray = new TSP.Tour[2];

        tspArray[0] = oneOffspring(parent1, parent2, cuttingPoint1, cuttingPoint2);
        tspArray[1] = oneOffspring(parent2, parent1, cuttingPoint1, cuttingPoint2);

        return tspArray;
    }

    private TSP.Tour oneOffspring(TSP.Tour parent1, TSP.Tour parent2, int leftCut, int rightCut) {
        int length = parent1.getDimension();
        List<Object> selectedArr = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            Object obj;
            if (i >= leftCut && i < rightCut) { // iz drugega posameznika dodamo subArray
                obj = parent2.getPath()[i];
                selectedArr.add(obj);
            } else {
                selectedArr.add(null);
            }
        }

        BidirectionalMap map = new BidirectionalMap();

        // zacetna map
        for (int i = leftCut; i < rightCut; i++) {
            map.put(selectedArr.get(i), parent1.getPath()[i]);
        }

        for (int i = 0; i < length; i++) {
            boolean inside = i >= leftCut && i < rightCut;
            if (!inside) {
                Object substitution = parent1.getPath()[i];
                while (selectedArr.contains(substitution)) {
                    substitution = map.getAndRemove(substitution);
                }
                selectedArr.set(i, substitution);
            }
        }
        TSP.Tour tour = parent1.clone();

        for (int i = 0; i < tour.getDimension(); i++) {
            tour.setCity(i, (TSP.City) selectedArr.get(i));
        }

        return tour;
    }

    private static class BidirectionalMap {
        private Map<Object, Object> keys1;
        private Map<Object, Object> keys2;

        BidirectionalMap() {
            keys1 = new LinkedHashMap<>();
            keys2 = new LinkedHashMap<>();
        }

        public void put(Object key1, Object key2) {
            keys1.put(key1, key2);
            keys2.put(key2, key1);
        }

        public Object getAndRemove(Object key) {
            if (keys1.containsKey(key)) {
                Object value = keys1.get(key);
                keys1.remove(key);
                keys2.remove(value);
                return value;
            } else if (keys2.containsKey(key)) {

            } else {
                System.out.println("Error! Key " + key + " not found.");
            }
            return null;
        }
    }

    private TSP.Tour tournamentSelection() {
        int size = RandomUtils.nextInt(population.size());
        while(size < 2) {
            size = RandomUtils.nextInt(population.size());
        }
        TSP.Tour[] tours = new TSP.Tour[size];

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        while (arrayList.size() < size) {
            int a = RandomUtils.nextInt(population.size());

            if (!arrayList.contains(a)) {
                arrayList.add(a);
            }
        }

        for(int i = 0; i < tours.length; i++) {
            tours[i] = population.get(arrayList.get(i));
        }

        return getBestTour(tours);
    }

    private TSP.Tour getBestTour(TSP.Tour[] tours) {
        TSP.Tour best = tours[0];
        for (int i = 1; i < tours.length; i++) {
            if(best.getDistance() > tours[i].getDistance()) {
                best = tours[i];
            }
        }

        return best;
    }
}
