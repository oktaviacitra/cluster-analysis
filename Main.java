public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        int[][] data = service.readFile("/Users/oktaviacitra/Documents/MachineLearning/ruspini.csv");
        Controller controller = new Controller();
        // controller.print_1d_data(data[0]);
        int k = 4;
        int[] field0 = service.splitColumns(data, 0);
        int[] field1 = service.splitColumns(data, 1);
        int min_field0 = service.min(field0);
        // System.out.println(min_field0);
        int max_field0 = service.max(field0);
        // System.out.println(max_field0);
        int min_field1 = service.min(field1);
        // System.out.println(min_field1);
        int max_field1 = service.max(field1);
        // System.out.println(max_field1);
        int[] min_field0_array = service.minArray(min_field0, max_field0, k);
        // controller.print_1d_data(min_field0_array);
        int[] max_field0_array = service.maxArray(min_field0, max_field0, k);
        // controller.print_1d_data(max_field0_array);
        int[] min_field1_array = service.minArray(min_field1, max_field1, k);
        // controller.print_1d_data(min_field1_array);
        int[] max_field1_array = service.maxArray(min_field1, max_field1, k);
        // controller.print_1d_data(max_field0_array);
        int[] randomNumberArray0 = new int[k];
        int[] randomNumberArray1 = new int[k];
        int[][] centroids = new int[k][2];
        for (int i = 0; i < k; i++) {
            randomNumberArray0[i] = service.randomNumberInRange(min_field0_array[i], max_field0_array[i]);
            randomNumberArray1[i] = service.randomNumberInRange(min_field1_array[i], max_field1_array[i]);
        }
        // System.out.println("Field-0\nMin\tMax\tRan");
        // for (int i = 0; i < k; i++) {
        // System.out.println(min_field0_array[i] + "\t" + max_field0_array[i] + "\t" +
        // randomNumberArray0[i]);
        // }
        // System.out.println("Field-1\nMin\tMax\tRan");
        // for (int i = 0; i < k; i++) {
        // System.out.println(min_field1_array[i] + "\t" + max_field1_array[i] + "\t" +
        // randomNumberArray1[i]);
        // }
        for (int i = 0; i < k; i++) {
            centroids[i][0] = randomNumberArray0[i];
            centroids[i][1] = randomNumberArray1[i];
        }
        // controller.print_2d_data(centroids);
        KMeans kmeans = new KMeans();
        int[][] newCentroids = new int[k][2];
        while (true) {
            int[][] centroidsData = kmeans.getDataCentroids(data, centroids);
            controller.print_2d_data(centroidsData);
            for (int i = 0; i < k; i++) {
                int[][] sameCentroid = kmeans.getSameCentroid(centroidsData, i);
                int[] newCentroid = kmeans.getNewCentroid(sameCentroid);
                for (int j = 0; j < 2; j++) {
                    newCentroids[i][j] = newCentroid[j];
                }
            }
            System.out.print("\n\nOld Centroids\t: ");
            controller.print_2d_data(centroids);
            System.out.print("\n\nNew Centroids\t: ");
            controller.print_2d_data(newCentroids);
            if (kmeans.compareCentroids(newCentroids, centroids)) {
                ClusterAnalysis clusterAnalysis = new ClusterAnalysis();
                double[] clusterVariance = new double[k];
                int[] nI = new int[k];
                for (int i = 0; i < k; i++) {
                    int[][] sameCentroid = kmeans.getSameCentroid(centroidsData, i);
                    nI[i] = sameCentroid.length;
                    clusterVariance[i] = clusterAnalysis.clusterVariance(sameCentroid, centroids[i]);
                    // System.out.println(clusterVariance[i]);
                }
                double varianceWithinCluster = clusterAnalysis.varianceWithinCluster(clusterVariance,
                        centroidsData.length, nI);
                System.out.println("\nVariance Within Cluster\t\t: " + varianceWithinCluster);
                double[] meanCentroid = service.mean(centroids);
                double varianceBetweenClusters = clusterAnalysis.varianceBetweenClusters(nI, centroids, meanCentroid);
                System.out.println("Variance Between Clusters\t: " + varianceBetweenClusters);
                double varianceAll = clusterAnalysis.varianceAll(varianceWithinCluster, varianceBetweenClusters);
                System.out.println("Variance of all clusters\t: " + varianceAll);
                break;
            } else
                centroids = newCentroids;
        }
    }
}