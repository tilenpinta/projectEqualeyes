package problems;

import Utility.RandomUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class TSP {

    enum DistanceType {EUCLIDEAN, WEIGHTED}

    public static class City {
        public int index;
        public double x, y;

        City(int index, double x, double y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "Index: " + index + " X: " + x + " Y: " + y;
        }
    }

    public class Tour {

        double distance;
        int dimension;
        City[] path;

        public Tour(Tour tour) {
            distance = tour.distance;
            dimension = tour.dimension;
            path = tour.path.clone();
        }

        public Tour(double distance, int dimension, City[] path) {
            this.distance = distance;
            this.dimension = dimension;
            this.path = path;
        }

        public Tour(int dimension) {
            this.dimension = dimension;
            path = new City[dimension];
            distance = Double.MAX_VALUE;
        }

        public Tour clone() {
            return new Tour(this);
        }

        public double getDistance() {
            return distance;
        }

        public int getDimension() {
            return dimension;
        }

        public void setDimension(int dimension) {
            this.dimension = dimension;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public City[] getPath() {
            return path;
        }

        public void setPath(City[] path) {
            this.path = path.clone();
        }

        public void setCity(int index, City city) {
            path[index] = city;
            distance = Double.MAX_VALUE;
        }

        public String toString()  {
            String myString = "";
            for(int i = 0; i < path.length; i++){
                myString += "\n" + path[i].toString();
            }
            return myString;
        }
    }

    String name;
    City start;
    List<City> cities = new ArrayList<>();
    int numberOfCities;
    double[][] weights;
    DistanceType distanceType = DistanceType.EUCLIDEAN;
    int numberOfEvaluations, maxEvaluations;


    public TSP(String path, int maxEvaluations) {
        loadData(path);
        numberOfEvaluations = 0;
        this.maxEvaluations = maxEvaluations;
    }

    public void evaluate(Tour tour) {
        double distance = 0;
        distance += calculateDistance(start, tour.getPath()[0]);
        for (int index = 0; index < numberOfCities - 1; index++) {
            if (index + 1 < numberOfCities - 1)
                distance += calculateDistance(tour.getPath()[index], tour.getPath()[index + 1]);
            else
                distance += calculateDistance(tour.getPath()[index], start);
        }
        tour.setDistance(distance);
        numberOfEvaluations++;
    }

    private double calculateDistance(City from, City to) {
        switch (distanceType) {
            case EUCLIDEAN:
                return calculateEucliden(from, to);
            case WEIGHTED:
                return calculateWeighted(from, to);
            default:
                return Double.MAX_VALUE;
        }
    }

    private double calculateEucliden(City from, City to) {
        double x;
        double y;
        x = Math.pow(to.x - from.x, 2);
        y = Math.pow(to.y - from.y, 2);
        return Math.sqrt(x+y);
    }

    private double calculateWeighted(City from, City to) {
        return weights[from.index][to.index];
    }

    public Tour generateTour() {
        List<City> list = new ArrayList<>(cities);
        Collections.shuffle(list, RandomUtils.getRandom());
        return new Tour(Double.MAX_VALUE,numberOfCities - 1, list.toArray(new City[0]));
    }

    private void loadData(String path) {
        // starting city is always at index 0
        InputStream inputStream = TSP.class.getClassLoader().getResourceAsStream(path);
        if(inputStream == null) {
            System.err.println("File "+path+" not found!");
            return;
        }

        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line = br.readLine();
            while (line != null) {
                lines.add(line);
               line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        getCities(path, lines);

        if (path.equals("bays29.tsp")) {
            getMatrix(lines);
        }
    }

    public void getMatrix(List<String> lines) {
        int startingLine;

        startingLine = 8;

        weights = new double[numberOfCities][numberOfCities];

        for(int i = startingLine; i < startingLine+numberOfCities; i++) {
                String[] line = removeWhiteSpace(lines.get(i)).trim().split(" ");
            for(int j = 0; j < numberOfCities; j++) {
                weights[i- startingLine][j] = Double.parseDouble(line[j].trim());
            }
        }
    }

    public void getCities(String path, List<String> lines) {

        int startingLine;
        String[] words;
        // Determine which data set to load up and set the properties pertaining to it.
        if (path.equals("bays29.tsp")) {
            startingLine = 38;
            words = lines.get(3).split(" ");
        } else if(path.equals("dca1389.tsp")) {
            startingLine = 8;
            words = lines.get(5).split(" ");
        } else {
            startingLine = 6;
            words = lines.get(3).split(" ");
        }

        numberOfCities = Integer.parseInt(words[words.length-1]);

        // Read each line and turn it into a City.
        for (int i = startingLine; i < startingLine+numberOfCities; i++) {
            String[] line = removeWhiteSpace(lines.get(i)).trim().split(" ");
            double x = Double.parseDouble(line[1].trim());
            double y = Double.parseDouble(line[2].trim());
            City city = new City(Integer.parseInt(line[0]), x, y);
            this.cities.add(city);
        }
        start = new City(cities.get(0).index, cities.get(0).x, cities.get(0).y);
    }
    //no spaces
    private static String removeWhiteSpace (String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && s.charAt(i-1) == ' ') {
                if (i != s.length()) {
                    s = s.substring(0, i) + s.substring(i+1, s.length());
                    i--;
                } else {
                    s = s.substring(0, i);
                    i--;
                }
            }
        }
        return s;
    }

    public int getMaxEvaluations() {
        return maxEvaluations;
    }

    public int getNumberOfEvaluations() {
        return numberOfEvaluations;
    }
}
