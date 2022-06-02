
import java.util.*;
 
class HiringProblem
{ 
    static int hireCandidate(int arr[],int inter,int sal,int fir,int len)
    {
        int totalCost=0;
        int maxSoFar=arr[0];
        int hired=0;
        for (int i = 0; i < len; ++i){
            
            
            if(i==0){   
                System.out.println("Day : "+ (i+1));
                System.out.println("Candidate : "+ (i+1)+ " Points : "+ arr[i]);
                System.out.println("Highest points of candidate so far :  "+ maxSoFar);
                totalCost=totalCost+inter+sal;  
                hired=hired+1;
                System.out.println("Cost so far : "+ totalCost);
                System.out.println("Hired having ponts : " + arr[i]) ;
                System.out.println("__________________________________________");
            }
            else{
                System.out.println("Day : "+ (i+1));
                System.out.println("Candidate : "+ (i+1)+ " Points : "+ arr[i]);
            if(maxSoFar>arr[i]){
                System.out.println("Highest points of candidate so far : "+ maxSoFar);
                totalCost=totalCost+inter+sal;
                System.out.println("Cost so far : "+ totalCost);
                System.out.println("Not Hired having points :"+ arr[i]);
                System.out.println("__________________________________________");
            }
            else{
                System.out.println("Highest points of candidate : "+ maxSoFar);
                int temp=maxSoFar;
                maxSoFar=arr[i];
                totalCost=totalCost+inter+sal+fir+sal;
                hired=hired+1; 
                System.out.println("Cost so far : "+ totalCost);
                System.out.println("Fired having points : "+ temp);
                System.out.println("Hired having points : "+ arr[i]);
                
                System.out.println("__________________________________________");
            }
            
        }
        }
        System.out.println("Number of candidates needed to be hired  to get the best candidate : "+ hired);
           
        return totalCost;
    }   
    static int hireRandomCandidate(int arr[],int inter,int sal,int fir,int len)
    {
        int totalCost=0;
        ArrayList<Integer> visited = new ArrayList<Integer>();
        Random rd = new Random(); // creating Random object

        int day=1;
        int maxSoFar=0;
        int hired=0;
        while(visited.size()!=arr.length){
            int rdIn=rd.nextInt(len);
            if(!visited.contains(rdIn)){
                
                System.out.println("Day  "+ (day));
                System.out.println("Candidate "+ (rdIn+1) + " Points :"+ arr[rdIn]);
                if(day==1){
                    maxSoFar=arr[rdIn];
                    System.out.println("Highest points of candidate  "+ maxSoFar);
                    totalCost=totalCost+inter+sal;
                    hired=hired+1;
                    System.out.println("Cost so far "+ totalCost);
                    System.out.println("Hired having points "+ maxSoFar);
                    System.out.println("__________________________________________");

                }
                else{
                
                if(maxSoFar>arr[rdIn]){
                    
                    System.out.println("Highest points of candidate  "+ maxSoFar);
                    totalCost=totalCost+inter+sal;
                    System.out.println("Cost so far "+ totalCost);
                    System.out.println("Not Hired having points "+arr[rdIn] );
                    System.out.println("__________________________________________");
                }
                else{
                
                    System.out.println("Highest points of candidate  "+ maxSoFar);
                    int temp=maxSoFar;
                    maxSoFar=arr[rdIn];
                    totalCost=totalCost+inter+sal+fir+sal;
                    hired=hired+1;
                    System.out.println("Cost so far "+ totalCost);
                    System.out.println("Hired having points "+ maxSoFar);
                    System.out.println("Fired having points "+ temp);
                    System.out.println("__________________________________________");
                }
            }
                visited.add(rdIn);
                day+=1;
            }
           


        }
        System.out.println("Number of candidates needed to be hired  to get the best candidate: : "+ hired);
        return totalCost;
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
       
        System.out.print("Enter number of candidates:");
        int len =sc.nextInt();
        System.out.print("Enter Interview Cost:");
        int inter =sc.nextInt();
        System.out.print("Enter salary per day:");
        int sal =sc.nextInt();
        System.out.print("Enter firing cost:");
        int fir =sc.nextInt();
        //int[] arr = new int[len];
        int[] arr={51,8,73,56,100};
    //   for (int i = 1; i <= arr.length; i++) {
    //         // arr[i] = rd.nextInt(len); 
    //         arr[i-1]=(i)*10;
    //   } 
        // int n = arr.length;
    printArray(arr);
    int hir=hireCandidate(arr, inter, sal, fir, len);
    System.out.println("Hiring in sequence:"+hir);
    System.out.println("***************************************");
    int hirRd=hireRandomCandidate(arr, inter, sal, fir, len);
    System.out.println("Hiring in random sequence:"+hirRd);
        
    }
}