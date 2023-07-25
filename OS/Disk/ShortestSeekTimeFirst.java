import java.util.Scanner;

public class ShortestSeekTimeFirst {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, head, i;
        System.out.print("Enter the size of queue request: ");
        n = scanner.nextInt();
        int[] proc = new int[n];
        System.out.println("S.No\t\t\tDisk Position");
        for (i = 0; i < n; i++) {
            System.out.print(i + 1 + "\t\t\t\t");
            proc[i] = scanner.nextInt();
        }
        System.out.print("Enter the Initial position: ");
        head = scanner.nextInt();
        shortestSeekTimeFirst(proc, head, n);
    }

    public static void shortestSeekTimeFirst(int request[], int head, int n) {
        if (n == 0) {
            return;
        }

        // Create array of objects of class node
         int[][] diff = new int[n][2];

        // Count total number of seek operation
        int seekcount = 0;

        // Stores sequence in which disk access is done
        int[] seeksequence = new int[n + 1];

        for (int i = 0; i < n; i++) {
            seeksequence[i] = head;
            calculatedifference(request, head, diff, n);
            int index = findMIN(diff, n);
            diff[index][1] = 1;

            // Increase the total count
            seekcount += diff[index][0];

            // Accessed track is now new head
            head = request[index];
        }
        seeksequence[n] = head;

        System.out.println("Total number of seek operations = " + seekcount);
        System.out.println("Seek sequence is:");
        // Print the sequence
        for (int i = 0; i <= n; i++) {
              if (i < n) {
                 System.out.print(seeksequence[i] + "->"); 
              } else {
                 System.out.print(seeksequence[i]); 
                  }
               } 
        
        System.out.println("\nAverage Seek Time is = " + (float) seekcount / (float) n);
        System.out.println("Throughput is = " + (float) n / (float) seekcount);
    }

    public static void calculatedifference(int request[], int head, int[][] diff, int n) {
        for (int i = 0; i < n; i++) {
            diff[i][0] = Math.abs(head - request[i]);
        }
    }

    public static int findMIN(int diff[][], int n) {
        int index = -1;
        int minimum = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (diff[i][1] == 0 && minimum > diff[i][0]) {
                minimum = diff[i][0];
                index = i;
            }
        }
        return index;
    }
}
