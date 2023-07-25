import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SCAN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, head, i, max;
        String direction;
        System.out.print("Enter the maximum size of disk: ");
        max = scanner.nextInt();
        System.out.print("Enter the size of queue request: ");
        n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("S.No\t\t\tDisk Position");
        for (i = 0; i < n; i++) {
            System.out.print(i + 1 + "\t\t\t\t");
            arr[i] = scanner.nextInt();
        }
        System.out.print("Enter the Initial position: ");
        head = scanner.nextInt();
        System.out.print("Enter the Direction (right/left): ");
        direction = scanner.next();

        SCAN(arr, head, direction, n, max);
    }

    public static void SCAN(int arr[], int head, String direction, int n, int max) {
        int seek_count = 0;
        int diff, cur_track;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> seek_sequence = new ArrayList<>();

        // Appending end values which have to be visited before reversing the direction
        if (direction.equals("left"))
            left.add(0);
        else if (direction.equals("right"))
            right.add(max - 1);

        for (int i = 0; i < n; i++) {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }

        // Sorting left and right lists
        Collections.sort(left);
        Collections.sort(right);

        // Run the while loop two times, scanning right and left of the head
        int run = 2;
        while (run-- > 0) {
            if (direction.equals("left")) {
                for (int i = left.size() - 1; i >= 0; i--) {
                    cur_track = left.get(i);

                    // Appending current track to seek sequence
                    seek_sequence.add(cur_track);

                    // Calculate absolute diff
                    diff = Math.abs(cur_track - head);

                    // Increase the total count
                    seek_count += diff;

                    // Accessed track is now the new head
                    head = cur_track;
                }
                direction = "right";
            } 
            else if (direction.equals("right")) {
                for (int i = 0; i < right.size(); i++) {
                    cur_track = right.get(i);

                    // Appending current track to seek sequence
                    seek_sequence.add(cur_track);

                    // Calculate absolute diff
                    diff = Math.abs(cur_track - head);

                    // Increase the total count
                    seek_count += diff;

                    // Accessed track is now the new head
                    head = cur_track;
                }
                direction = "left";
            }
        }

        System.out.println("Total number of seek operations = " + seek_count);
        System.out.println("Seek Sequence is:");
        for (int i = 0; i <= n; i++) {
            if (i < n) {
                System.out.print(seek_sequence.get(i) + "->");
            } else {
                System.out.print(seek_sequence.get(i));
            }
        }
        System.out.println();
        System.out.println("Average Seek Time is = " + (float) seek_count / (float) n);
        System.out.println("Throughput is = " + (float) n / (float) seek_count);
    }
}
