import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CSCAN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, head, i, max;
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
        CSCAN(arr, head, n, max);
    }

    public static void CSCAN(int arr[], int head, int n, int max) {
        int seek_count = 0;
        int distance, cur_track;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> seek_sequence = new ArrayList<>();

        // Appending end values which have to be visited before reversing the direction
        left.add(0);
        right.add(max - 1);

        // Tracks on the left of the head will be serviced once the head comes back to the beginning (left end).
        for (int i = 0; i < n; i++) {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }

        // Sorting left and right lists
        Collections.sort(left);
        Collections.sort(right);

        // First service the requests on the right side of the head.
        for (int i = 0; i < right.size(); i++) {
            cur_track = right.get(i);

            // Appending current track to seek sequence
            seek_sequence.add(cur_track);

            // Calculate absolute distance
            distance = Math.abs(cur_track - head);

            // Increase the total count
            seek_count += distance;

            // Accessed track is now the new head
            head = cur_track;
        }

        // Once reached the right end, jump to the beginning.
        head = 0;

        // Adding seek count for head returning from (max - 1) to 0
        seek_count += (max - 1);

        // Now service the requests again which are left.
        for (int i = 0; i < left.size(); i++) {
            cur_track = left.get(i);

            // Appending current track to seek sequence
            seek_sequence.add(cur_track);

            // Calculate absolute distance
            distance = Math.abs(cur_track - head);

            // Increase the total count
            seek_count += distance;

            // Accessed track is now the new head
            head = cur_track;
        }

        System.out.println("Total number of seek operations = " + seek_count);
        System.out.println("Seek Sequence is:");
        for (int i = 0; i < seek_sequence.size(); i++) {
            if (i < seek_sequence.size() - 1) {
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

