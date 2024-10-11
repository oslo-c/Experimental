import java.util.*;

// This program is designed to accept a very large integer and returns the largest prime integer less than the maximum given
public class LargestPrime {
    public static void main(String args[]) {
        int max = 10000000;
        System.out.println(primeSearch(max));
    }

    public static int primeSearch(int max) {
        List<Integer> raw = new LinkedList<>();
        List<Integer> processed = new LinkedList<>();

        // Generates raw list, omitting values less than 3
        for (int i=3; i<=max; i+=2) {
            raw.add(i);
        }

        while (!raw.isEmpty()) {
            int front = raw.remove(0);
            processed.add(front);

            Iterator<Integer> itr = raw.iterator();
            while (itr.hasNext()) {
                int current = itr.next();
                if (current % front == 0) {
                    itr.remove();
                }
            }
        }
        return processed.getLast();
    }
}