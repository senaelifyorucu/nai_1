import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter k value: ");
        int k = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter training file: ");
        String trainFile = scanner.nextLine();

        System.out.print("Enter test file: ");
        String testFile = scanner.nextLine();


        List<DataPoint> train = DatasetLoader.loadDataset(trainFile);
        List<DataPoint> test = DatasetLoader.loadDataset(testFile);

        System.out.println("Train size: " + train.size());
        System.out.println("Test size: " + test.size());

        int correct = 0;




        for (DataPoint testPoint : test) {
            String predicted = KNN.classify(testPoint, train, k);

            System.out.println("Predicted: " + predicted + " | Actual: " + testPoint.label);

            if (predicted.equals(testPoint.label)) {
                correct++;
            }
        }

        double accuracy = (double) correct / test.size();

        System.out.println("\nCorrect predictions: " + correct);
        System.out.printf("Accuracy: %.2f%%\n", accuracy * 100);




        while (true) {

            System.out.println("\nEnter new sample values (or type 'exit'):");

            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = line.split("\\s+");

            double[] features = new double[parts.length];

            for (int i = 0; i < parts.length; i++) {
                features[i] = Double.parseDouble(parts[i]);
            }

            DataPoint newPoint = new DataPoint(features, "unknown");

            String prediction = KNN.classify(newPoint, train, k);

            System.out.println("Predicted class: " + prediction);
        }
    }
}



class MathUtils {

    public static double distance(double[] v1, double[] v2) {

        double sum = 0;

        for (int i = 0; i < v1.length; i++) {
            double diff = v1[i] - v2[i];
            sum += diff * diff;
        }

        return Math.sqrt(sum);
    }
}

//veriyi yukluyor

class DatasetLoader {

    public static List<DataPoint> loadDataset(String filename) {

        List<DataPoint> dataset = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");

                double[] features = new double[parts.length - 1];

                for (int i = 0; i < parts.length - 1; i++) {
                    features[i] = Double.parseDouble(parts[i].replace(",", "."));
                }

                String label = parts[parts.length - 1];

                dataset.add(new DataPoint(features, label));
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataset;
    }
}


class DataPoint {
    double[] features;
    String label;

    public DataPoint(double[] features, String label) {
        this.features = features;
        this.label = label;
    }
}



class KNN {

    public static String classify(DataPoint testPoint, List<DataPoint> train, int k) {

        List<Neighbor> neighbors = new ArrayList<>();

        for (DataPoint trainPoint : train) {
            double dist = MathUtils.distance(testPoint.features, trainPoint.features);
            neighbors.add(new Neighbor(dist, trainPoint.label));
        }

        neighbors.sort(Comparator.comparingDouble(n -> n.distance));

        Map<String, Integer> counts = new HashMap<>();

        int limit = Math.min(k, neighbors.size());

        for (int i = 0; i < limit; i++) {
            String label = neighbors.get(i).label;
            counts.put(label, counts.getOrDefault(label, 0) + 1);
        }

        String bestLabel = null;
        int maxCount = -1;

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                bestLabel = entry.getKey();
            }
        }

        return bestLabel;
    }
}



class Neighbor {
    double distance;
    String label;

    public Neighbor(double distance, String label) {
        this.distance = distance;
        this.label = label;
    }
}