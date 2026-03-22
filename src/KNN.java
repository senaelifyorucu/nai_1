import java.io.*;
import java.util.*;

public class KNN {
    public static List<Sample> readData(String filename){
        List<Sample> data = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if(line.isEmpty())
                    continue;

                String[] parts = line.split("\\s+");
                int n = parts.length;
                double[] features = new double[n-1];

                for(int i = 0; i <n-1; i++) {
                    features[i] = Double.parseDouble(parts[i].replace(",", "."));
                }
                String label = parts[n-1];
                data.add(new Sample(features, label));
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static double distance( Sample a, Sample b) {
        double sum = 0.0;
        for (int i = 0; i< a.features.length; i++) {
            double diff = a.features[i] - b.features[i];
            sum += diff*diff;
        }
        return Math.sqrt(sum);
    }
    public static void main(String[] args) {
        List<Sample> trainingData = readData("Iris_training 1.txt");
        List<Sample> testData = readData("iris_test 1.txt");
        System.out.println("training size:" + trainingData.size());
        System.out.println("test size:" + testData.size());

        Sample s = trainingData.get(0);
        System.out.println("first sample label:" + s.label);
        System.out.println("first feature:" + s.features[0]);
    }
}
