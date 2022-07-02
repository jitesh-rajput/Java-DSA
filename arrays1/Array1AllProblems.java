/* 
Array 1 Problems :- 

1. Search Element in Array 
    Approach 1st :- Using simple for loop to traverse each element of Array and check with key
    Approach 2nd :- Sort Array and use binary Search  

2. Delete element From array 

3. Find largest/smallest element in array 
    Approach 1:- traverse array and find min/max
    Approach 2:- sort the given array and print arrays first/last element

4. Find Second largest Element in Array 
    Approach 1:- Traverse array with two max variables 
    Approach 2:- Sort array and print second last element 

5. Remove Duplicates from Sorted Array 
    Approach 1:- Check for next element if not same then add to temp element- O(n),space Complexity O(n)
    Approach 2:- check for next element if not same then add to same array using pointer O(n) witout space Complexity O(1)

6. Check if array is sorted or not 
    Approach 1:- Travers array and check element with next element if next element is greater than current
                    element then array is not sorted.
7. Reverse Array 
    Approach 1:- Traverse array in reverse order and insert element in new array , new array is reverse of given array .

8. Write a program to replace every element with the greatest element on its right side
    Array :- 7 5 8 9 6 8 5 7 4 6 
    Modified Array :- 9 9 9 8 8 7 7 6 6 0 
    Approach 1:- Use two loop to check greater element from right side and insert in array
    Approach 2:- Traverse array from right side, max=0 and update max in array 
*/
import java.util.Arrays;

public class Array1AllProblems{
    
    public static boolean searchElement(int arr[]){
        int key=25;             // element to be search in arr key=25
        for(int element :arr)   // Use for each loop 
        {    
            if(element==key) return true;  // if element is equal to key return true
        }
        return false;          // else return false 
    }

    public static int searchElementByBinarySearch(int arr[]){
        Arrays.sort(arr);           // Sort the array 
        int key=25;
        int start=0,mid;                   // set start to 0 
        int end=arr.length-1;           // end to arr.length
                        
        while(start<=end)              // mid is start+end / 2 (mean)
        {
            mid=(start+end)/2;
            if(arr[mid]==key)           // if key found return true
            {
                return mid;
            }
            if(arr[mid]>key)            // if key is less than mid then update end to mid-1
            {
                end=mid-1;
            }
            if(arr[mid]<key){           // if key is greater than mid then update end to mid+1
                start=mid+1;
            }
        }
        return -1;
    }

    public static void deleteElement(int arr[]){
        int index=searchElementByBinarySearch(arr);     // Check for element in array 
        System.out.println(index);
        if(index==-1)
        {
            System.out.println("Element Not present in Array ..");
        }
        else
        {
            int l=arr.length-1;
            for(int ele=index;ele<l;ele++){     // traverse for key index to last 
                arr[ele]=arr[ele+1];            // Swap element with next one 
            }
        }
        printArray(arr);
    }

    public static void getMaxElement(int arr[])
    {
        // Approach 1 - O(n)
        int max=arr[0];
        for(int element:arr)
        {
            if(element>max) max=element;
        }
        System.out.println("Maximum element in array :- "+max);

        // Approach 2 - O(1)
        Arrays.sort(arr);
        System.out.println("Maximum element in array :- "+arr[arr.length-1]);
    
    }

    public static void getSecondMaxElement(int arr[])
    {
        // Approach 1 - O(n)
        int max1=arr[0];
        int max2=arr[0];
        for(int element:arr)
        {
            if(element>max2 && element<max1 ) max2=element;
            if(element>max2 && element>max1) {
                max2=max1;
                max1=element;
            }
        }
        System.out.println("Second Maximum element in array :- "+max2);

        // Approach 2 - O(1)
        Arrays.sort(arr);
        System.out.println(" Second Maximum element in array :- "+arr[arr.length-2]);
    
    }

    public static void removeDuplicates(int arr[]){
        // Approach 1
        int[] temp=new int[arr.length-1];
        int j=0;
        for(int index=0;index<arr.length-1;index++){
            if(arr[index]!=arr[index+1]) 
            {
                temp[j]=arr[index];
                j++;
            }
        }
        temp[j++]=arr[arr.length-1]; 
        System.out.println("Approach 1: ");
        printArray(temp);

        // Approach 2 
        j=0;
        int c=1;
        for(int i=0;i<arr.length-1;++i){
            if(arr[i]!=arr[i+1]){  // check for duplicate element if not then assign to same array index if same then not added to array
                c++;               // for counting the unduplicate element in array for printing purpose later
                arr[j++]=arr[i];
            }
        }
        arr[j++]=arr[arr.length-1];        // assign last missing element of array to position 
        System.out.println("Approach 2: ");
        printArray(arr);
    }

    public static boolean isSorted(int arr[]){
        // Check if array is sorted 
	    for(int i=0;i<arr.length-1;++i){
	        if(arr[i+1]<arr[i]){
	            return false;
	        }
	    }
        return true;
    }

    public static void reverseArray(int arr[]){
        System.out.println("Given Array :- ");
        printArray(arr); 

        // Approach 1 TC:- O(n) with space complexity O(n)
        int length=arr.length;
        int[] newarr=new int[length];
        int j=0;
        for(int element=length-1;element>=0;element--){
            newarr[j]=arr[element];
            j++;
        }
        System.out.println("Approach 1 Reverse array :- ");
        printArray(newarr); 

        // Approach 2 using two pointer approach O(log n)
        int temp,left=0,right=length-1;
        while(left<=right){
            temp=arr[left];
            arr[left]=arr[right];
            arr[right]=temp;
            left++;
            right--;
        }
        System.out.println("Approach 2 Reverse array :- ");
        printArray(arr); 
    }

    public static void replaceRight(int arr[]){
        // Approach 1 - O(n^2) with space O(n)
        System.out.println("Approach 1 :- ");
        printArray(arr);
        int length=arr.length,max;
        int[] replaceArray=new int[length];
        for(int i=0;i<length;i++){
            max=0;
            for(int j=i+1;j<=length-1;j++){
                if(max<arr[j]){
                    max=arr[j];         // find greatest element from right
                }
            }
            replaceArray[i]=max;        // insert max in array 
        }
        printArray(replaceArray);

        // Approach 2:- O(n) with space O(n)
        System.out.println("Approach 2 :- ");
        max=0;                              
        for(int i=length-1;i>=0;i--){       // travese array reverse max =0
            replaceArray[i]=max;            // assign max to array 
            if(arr[i]>max) max=arr[i];      // if arr element is greater than max update max
        }
        printArray(replaceArray);
    }

    public static void printArray(int arr[]){
        for(int element:arr)
        {
            System.out.print(element+"\t");
        }
        System.out.println();
    }

    public static void main(String args[]){
        
        int[] arr={7, 5 ,8 ,9 , 6 ,8 ,5 ,7 ,4 ,6 };
        //  System.out.println("is element found ? :- "+searchElement(arr));
        //  System.out.println(" is element found ?:- "+searchElementByBinarySearch(arr));
        //  deleteElement(arr);
        //   getMaxElement(arr);
        //   getSecondMaxElement(arr);
        //  removeDuplicates(arr);
        // System.out.println("is array sorted ? :- "+isSorted(arr));
        // reverseArray(arr);
        replaceRight(arr);
    }
}