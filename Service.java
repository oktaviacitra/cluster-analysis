import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Service {
    public Service() {

    }

    public int[][] readFile(String name) {
        int[][] intArray = new int[75][3];
        String[] stringArray = new String[3];
        String strLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            int count = 0;
            while (strLine != null) {
                strLine = br.readLine();
                if (strLine == null)
                    break;
                stringArray = strLine.split(",");
                for (int i = 0; i < stringArray.length; i++)
                    intArray[count][i] = Integer.valueOf(stringArray[i]);
                count++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return intArray;
    }

    public int max(int[] data) {
        int max = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    public int min(int[] data) {
        int min = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }

    public int randomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public int[] minArray(int min, int max, int k) {
        int[] minArray = new int[k];
        int divider = (max - min) / k;
        minArray[0] = min;
        for (int i = 1; i <= k - 1; i++) {
            minArray[i] = minArray[i - 1] + divider;
        }
        return minArray;
    }

    public int[] maxArray(int min, int max, int k) {
        int[] maxArray = new int[k];
        int divider = (max - min) / k;
        maxArray[k - 1] = max;
        for (int i = k - 2; i >= 0; i--) {
            maxArray[i] = maxArray[i + 1] - divider;
        }
        return maxArray;
    }

    public int[] splitColumns(int[][] data, int j) {
        int[] result = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i][j];
        }
        return result;
    }

    public double[] mean(int[][] data) {
        double[] sum = { 0.0, 0.0 };
        double[] result = new double[2];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                sum[j] += data[i][j];
            }
        }
        for (int i = 0; i < sum.length; i++) {
            result[i] = sum[i] / data.length;
        }
        return result;
    }

    // public int[] splitRows(int[][] data) {
    // int[] result = new int[data.length];
    // for (int i = 0; i < data.length; i++) {
    // result[i] = data[i];
    // }
    // return result;
    // }
}