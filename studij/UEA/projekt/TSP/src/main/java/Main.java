import problems.TSP;

public class Main {

    public static void main(String[] args) {
        TSP tsp = new TSP("resources/eil101.tsp", 3000);
        GA ga = new GA(2, 0.4, 0.4);
    }
}
