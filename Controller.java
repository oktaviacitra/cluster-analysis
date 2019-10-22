public class Controller {
    public Controller() {

    }

    public void print_2d_data(int[][] data) {
        System.out.println("\n=======================");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.println("=======================");
    }

    public void print_1d_data(int[] data) {
        System.out.println("\n=======================");
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
        System.out.println("=======================\n");
    }
}