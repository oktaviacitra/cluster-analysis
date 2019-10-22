public class KMeans {
    public KMeans() {

    }

    public int[][] getDataCentroids(int[][] data, int[][] centroids) {
        int[][] result = new int[data.length][data[0].length + 1];
        double[] temp = new double[centroids.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < centroids.length; j++) {
                temp[j] = getDistance(data[i], centroids[j]);
            }
            for (int k = 0; k < (data[0].length + 1); k++) {
                if (k == data[0].length)
                    result[i][k] = nearest(temp);
                else
                    result[i][k] = data[i][k];
            }
        }
        return result;
    }

    public double getDistance(int[] data, int[] centroids) {
        return Math.sqrt((Math.pow(data[1] - centroids[1], 2)) + (Math.pow(data[0] - centroids[0], 2)));
    }

    public int nearest(double[] distance) {
        double min = distance[0];
        int count = 0;
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] < min)
                min = distance[i];
        }
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == min) {
                count = i;
                break;
            }
        }
        return count;
    }

    public int[][] getSameCentroid(int[][] data, int value) {
        int count = 0;
        // System.out.println(data.length);
        for (int i = 0; i < data.length; i++) {
            if (data[i][3] == value)
                count++;
        }
        // System.out.println(count);
        int[][] result = new int[count][data[0].length];
        int add = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i][3] == value) {
                for (int j = 0; j < data[0].length; j++) {
                    result[add][j] = data[i][j];
                }
                add++;
            }
        }
        return result;
    }

    public int[] getNewCentroid(int[][] data) {
        int[] result = new int[2];
        Service service = new Service();
        for (int i = 0; i < 2; i++) {
            int[] column = service.splitColumns(data, i);
            double sum = 0.0;
            for (int j = 0; j < column.length; j++) {
                sum += column[j];
            }
            result[i] = (int) (sum / column.length);
            // int min = service.min(column);
            // int max = service.max(column);
            // result[i] = (max - min) / 2;
        }
        return result;
    }

    public boolean compareCentroids(int[][] newCentroids, int[][] oldCentroids) {
        boolean result = false;
        for (int i = 0; i < newCentroids.length; i++) {
            for (int j = 0; j < newCentroids[0].length; j++) {
                if (newCentroids[i][j] == oldCentroids[i][j])
                    result = true;
                else
                    result = false;
            }
        }
        return result;
    }
}