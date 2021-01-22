package Util;

import weka.core.Instances;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataParser {

    public static Instances getInstances(String fileName) throws IOException {
        InputStream inputStream = DataParser.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            System.err.println("File " + fileName + " not found!");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            Instances data = new Instances(br);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
