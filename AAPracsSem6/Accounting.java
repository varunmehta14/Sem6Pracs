import java.util.*;
class DynamicArray{
    int size=1;
    int cost=1;
    int totalCost=0;
    int bank=0;
    int[] a=new int[size];
    ArrayList<Integer> c = new ArrayList<Integer>();
    ArrayList<Integer> bank_arr = new ArrayList<Integer>();
    int counter=0;
    public void add(int element,int amort){
        if(counter==size){
        System.out.println("------Size double-------");
        size=size*2;
        cost=counter+1;

        int [] temp=new int[size];
        System.arraycopy(a,0,temp,0,a.length);
        a=temp;
    }
    else{cost=1;}
   
    a[counter]=element;
    c.add(cost);
    counter++;
    bank+=amort-cost;
    bank_arr.add(bank);
    
    totalCost=totalCost+cost;
    System.out.println("Size: "+size+ " Counter: "+counter);
    System.out.println("Array: "+Arrays.toString(a));
    float sum=totalCost;
    float amot=(sum/c.size());
    // System.out.println("Total cost till " + counter +" " +totalCost + " "+ (totalCost/counter)); 
    System.out.println("Cost At that step: " + cost + "\tBank: " + (bank));
    System.out.println("Cost Array: "+ c);
    System.out.println("Bank Array: "+ bank_arr);
    System.out.println("Amortised cost: " + amot);
    
    System.out.println("_________________________________________________________");
    }

   
}
public class Accounting {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      DynamicArray dynamicarray=new DynamicArray();
      System.out.print("Enter  n:");
      int n=sc.nextInt();
      System.out.print("Enter  cost:");
      int amot=sc.nextInt();
      while(n!=-1){
          dynamicarray.add(n,amot);
          System.out.print("Enter  n:");
          n=sc.nextInt();
          System.out.print("Enter  cost:");
          amot=sc.nextInt();
      }
       
       
    }
}