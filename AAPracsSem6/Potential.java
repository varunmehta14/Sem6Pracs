import java.util.*;
class DynamicArray{
    int size=1;
    int cost=1;
    int totalCost=0;
    int[] a=new int[size];
    ArrayList<Integer> c = new ArrayList<Integer>();
    ArrayList<Integer> potential_arr = new ArrayList<Integer>();
    ArrayList<Integer> amortized_cost = new ArrayList<Integer>();
    int counter=0;
    
    public int potential(int a,int b){
        return (2 * a - b);
    }
    public void add(int element){
        
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
    int potent = potential(counter, a.length);
    System.out.println("Potential: " + potent);
    potential_arr.add(potent);
    amortized_cost.add(cost + (potential_arr.get(counter) - potential_arr.get(counter-1)));
    System.out.println("Cost: " + cost + "\tAmortized Cost: " + (cost + (potential_arr.get(counter) - potential_arr.get(counter-1))));
    totalCost=totalCost+cost;
    System.out.println("Size: "+size+ " Counter: "+counter +" Cost: "+ cost);
    System.out.println("Array: "+Arrays.toString(a));
    System.out.println("Amortised Cost: "+ amortized_cost);
    float sum=totalCost;
    float amot=(sum/c.size());
    // System.out.println("Total cost till " + counter +" " +totalCost + " "+ (totalCost/counter)); 
    System.out.println("Cost: "+ c);
    System.out.println("Amortised cost: " + amot);
    System.out.println("_________________________________________________________");
    }
   
}
public class Potential {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      DynamicArray dynamicarray=new DynamicArray();
      dynamicarray.potential_arr.add(0);
      System.out.print("Enter  n:");
      int n=sc.nextInt();
      
      while(n!=-1){
          dynamicarray.add(n);
          System.out.print("Enter  n:");
          n=sc.nextInt();
      }
       
       
    }
}