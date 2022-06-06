import java.sql.Timestamp;
import java.util.*;
 
class RandomQSort
{    
    static void random(int arr[],int low,int high)
    {
     
        Random rand= new Random();
        int pivot = rand.nextInt(high-low)+low;
         //swap pivot with array end
        int temp1=arr[pivot]; 
        arr[pivot]=arr[high];
        arr[high]=temp1;
    }
     
    static int partition(int arr[], int low, int high)
    {
        // pivot is chosen randomly
        random(arr,low,high);
        int pivot = arr[high];
     
        
        int i = (low-1); 
        for (int j = low; j < high; j++)
        {
            if (arr[j] < pivot)
            {
                //swap i and j
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //swap pivot with partition index
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
 
        return i+1;
    }
 
    static void sort(int arr[], int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);
 
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
 
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
 
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Random rd = new Random(); // creating Random object
        System.out.print("Enter number of elements:");
        int len =sc.nextInt();
        int[] arr = new int[len];
      for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(len); 
      } 
        int n = arr.length;
       
        
       
        Timestamp start=new Timestamp(System.currentTimeMillis());
        sort(arr, 0, n-1);
        Timestamp end=new Timestamp(System.currentTimeMillis());
        System.out.println("Task started at:"+ start);
        long startTime= start.getTime();
        System.out.println("Task ended at:"+ end);
        long endTime = end.getTime();
        long elapsedTime = endTime - startTime;
        float time=elapsedTime;
        System.out.println("Time Taken: "+time + "ms");
        
    }
}