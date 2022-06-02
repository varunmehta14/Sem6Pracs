

import java.util.ArrayList;
import java.util.Scanner;

public class PracPotentialDT {
    public int potential(int arr[], ArrayList<Integer> cost){
        return (2 * cost.size() - arr.length);
    }
    public static void main(String[] args){
        int counter = 0, length = 1, cost_at_step = 0;
        double total = 0;
        int arr[] = new int[length];
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> cost = new ArrayList<>();
        ArrayList<Integer> potential_arr = new ArrayList<>();
        ArrayList<Integer> final_cost = new ArrayList<>();
        potential_arr.add(0);
        PracPotentialDT obj = new PracPotentialDT();

        while(true){
            int input = sc.nextInt();
            int potent = obj.potential(arr, cost);
            System.out.println("Potential: " + potent);
            if(input == -1)
                break;
            else if(counter < arr.length){
                arr[counter++] = input;
                cost_at_step = 1;
                cost.add(cost_at_step);
                total += 1;
                potential_arr.add(potent);
            } else {
                System.out.println("Array Doubled at counter: " + (counter+1));
                int temp[] = arr.clone();
                arr = new int[length * 2];
                System.arraycopy(temp, 0, arr, 0, length);
                length = length * 2;
                cost_at_step = counter + 1;
                cost.add(cost_at_step);
                total += (cost_at_step);
                arr[counter++] = input;
                potential_arr.add(potent);
            }
            System.out.println(counter);
            final_cost.add((cost.get(counter-1) + (potential_arr.get(counter) - potential_arr.get(counter-1))));
            System.out.println("Cost Array: " + cost + "\tAmortized Cost: " + (cost.get(counter-1) + (potential_arr.get(counter) - potential_arr.get(counter-1))));
        }
        System.out.println(cost);
        System.out.println(potential_arr);
        System.out.println("Final Amortized Cost: " + (total / arr.length));

        System.out.println(final_cost);
    }
}
