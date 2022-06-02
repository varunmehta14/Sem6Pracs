import java.util.*;
class DynamicArray{
    int size=1;
    int cost=1;
    int totalCost=0;
    int[] a=new int[size];
    ArrayList<Integer> c = new ArrayList<Integer>();
    int counter=0;
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
   
   
    totalCost=totalCost+cost;
    System.out.println("Size: "+size+ " Counter: "+counter +" Cost: "+ cost);
    System.out.println("Array: "+Arrays.toString(a));
    float sum=totalCost;
    float amot=(sum/c.size());
    // System.out.println("Total cost till " + counter +" " +totalCost + " "+ (totalCost/counter)); 
    System.out.println("Cost: "+ c);
    System.out.println("Amortised cost: " + amot);
    System.out.println("_________________________________________________________");
    }
   
}
public class Aggregate {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      DynamicArray dynamicarray=new DynamicArray();
      System.out.print("Enter  n:");
      int n=sc.nextInt();
       
      while(n!=-1){
          dynamicarray.add(n);
          System.out.print("Enter  n:");
          n=sc.nextInt();
      }
       
       
    }
}