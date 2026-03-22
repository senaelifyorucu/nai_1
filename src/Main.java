import java.io.*;
import java.util.*;

public class Main{
    static class Sample {
        double[] features;
        String label;

        public Sample(double[] features, String label) {
            this.features = features;
            this.label = label;
        }
    }
    static class Neighbor {
        double distance;
        String label;

        public Neighbor(double distance,String label){
            this.distance = distance;
            this.label=label;
        }
    }
    public static List<Sample> readData(String filename) {
        List<Sample> data = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");

                int n = parts.length;
                double[] features = new double[n-1];
                for (int i =0;i<n-1; i++){
                    features[i]=Double.parseDouble(parts[i].replace(",", "."));

                }
                String label = parts[n-1];
                data.add(new Sample(features,label));

            }
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static double distance (Sample a, Sample b) {
        double sum = 0.0;

        for (int i = 0;i<a.features.length;i++) {
            double diff = a.features[i]-b.features[i];
            sum += diff*diff;
        }
        return Math.sqrt(sum);
    }
    public static String predict(List<Sample> trainingData, Sample testSample ,int k) {
        List<Neighbor> neighbors = new ArrayList<>();
        for (Sample train : trainingData) {
            double dist = distance (train, testSample);
            neighbors.add(new Neighbor(dist, train.label));

        }
        neighbors.sort(Comparator.comparingDouble(n-> n.distance));
        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i<k; i++) {
            String label = neighbors.get(i).label;
            countMap.put(label, countMap.getOrDefault(label, 0)+1);
        }
        String predictedLabel = null;
        int maxCount = 0;

        for (String label : countMap.keySet()) {
            if (countMap.get(label) > maxCount) {
                maxCount = countMap.get(label);
                predictedLabel = label;
            }
        }
        return predictedLabel;
    }

    public static void main(String[] args) {
        List<Sample> trainingData = readData("iris_training 1.txt");
        List<Sample> testData = readData("iris_test 1.txt");

        int k = 3;
        int correct = 0;

        for (Sample testSample : testData) {
            String predicted = predict(trainingData, testSample, k);
            if (predicted.equals(testSample.label)) {
                correct ++;

            }
        }
        System.out.println("Training Size:" + trainingData.size());
        System.out.println("Test size:" +testData.size());

        Sample s=trainingData.get(0);
        System.out.println("first simple label" +s.label);
        System.out.println("first feature"+s.features[0]);

        Sample s1= trainingData.get(0);
        Sample s2= trainingData.get(1);

        double d= distance(s1,s2);
        System.out.println("distance between first two samples;" +d);

        double accuracy = (correct*100.0) / testData.size() ;
        System.out.println("correct predictions are" +correct);
        System.out.println("acccuracy is %.2f%%\n" + accuracy + "%");

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter four features (sepal length, sepal width, petal length, petal width); ");
        double[] newFeatures = new double[4];
        for (int i = 0; i<4; i++) {
            newFeatures[i] = scanner.nextDouble();

        }
        Sample newSample = new Sample (newFeatures, "unknown");

        //int k = 3;
        String predicted = predict (trainingData, newSample, k);
        System.out.println("predicted class" + predicted);


    }
}

