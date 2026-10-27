import java.util.Scanner;

public class Latihan05 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of row: ");
        int n = input.nextInt();

        int total = 2 * n - 1;
        int i = 0;

        while (i < total) {
            int j = 0;
            while (j < total) {
                int distI = Math.abs((n - 1) - i);
                int distJ = Math.abs((n - 1) - j);

                if (distI + distJ >= n - 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
                j++;
            }
            System.out.println();
            i++;
        }
    }
}