public class ClusterAnalysis {
    public ClusterAnalysis() {

    }

    public double clusterVariance(int[][] cluster, int[] centroids) {
        double part_first = (1.0 / ((double) cluster.length - 1.0));
        double[] sum = { 0.0, 0.0 };
        for (int i = 0; i < cluster.length; i++) {
            for (int j = 0; j < centroids.length; j++) {
                sum[j] += (cluster[i][j] - centroids[j]);
            }
        }
        double jumlah = (sum[0] * sum[0]) + (sum[1] * sum[1]);
        // System.out.println(jumlah);
        double result = part_first * jumlah;
        // System.out.println(part_first);
        return result;
    }

    public double varianceWithinCluster(double[] clusterVariance, int n, int[] nI) {
        int k = clusterVariance.length;
        double part_first = 1.0 / ((double) n - k);
        double sum = 0.0;
        for (int i = 0; i < k; i++) {
            sum += ((double) nI[i] - 1) * clusterVariance[i];
        }
        double result = part_first * sum;
        return result;
    }

    public double varianceBetweenClusters(int[] nI, int[][] centroids, double[] centroid) {
        int k = nI.length;
        double part_first = 1.0 / (((double) k) - 1.0);
        double temp = 0.0;
        for (int i = 0; i < centroids.length; i++) {
            double[] sum = { 0.0, 0.0 };
            for (int j = 0; j < centroids[0].length; j++) {
                sum[j] += (((double) centroids[i][j]) - centroid[j]);
            }
            double result = 0.0;
            for (int l = 0; l < centroids[0].length; l++) {
                result += sum[l] * sum[l];
            }
            temp += ((double) nI[i]) * result;
        }
        double last = part_first * temp;
        return last;
    }

    public double varianceAll(double vw, double vb) {
        return (vw / vb);
    }
}