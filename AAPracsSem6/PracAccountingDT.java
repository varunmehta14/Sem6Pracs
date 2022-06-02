
import java.util.ArrayList;
import java.util.Scanner;

public class PracAccountingDT {
    public static void main(String[] args){
        int counter = 0, length = 1, cost_at_step = 0, bank = 0;
        double total = 0;
        int arr[] = new int[length];
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> cost = new ArrayList<>();
        ArrayList<Integer> bank_arr = new ArrayList<>();

        while(true){
            int input = sc.nextInt();
            int account = sc.nextInt();
            if(input == -1)
                break;
            else if(counter < arr.length){
                arr[counter++] = input;
                cost_at_step = 1;
                cost.add(cost_at_step);
                total += 1;
                bank += (account - cost_at_step);
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
                bank += (account - cost_at_step);
            }
            bank_arr.add(bank);
            System.out.println("Cost Array: " + cost + "\tBank: " + (bank));
        }
        System.out.println(cost);
        System.out.println(bank_arr);
        System.out.println("Final Amortized Cost: " + (total / arr.length));
    }
}
