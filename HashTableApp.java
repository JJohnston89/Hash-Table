/*This is the main driver of the project. The input is a text file. 
 *The output prints out tables for insertion, searching, and deletion from both linear and quadratic. 
 *Name: Josh Johnston
 *Date: 8/27/2015
*/

import java.io.*;
import java.util.Scanner;

public class HashTableApp{
   
   public static void main(String[] args) throws IOException{
      
           
      int count = getCount(args[0]); //get the count of strings       
                                         
      HashTable hashTableA = new HashTable(count);    //created a hash table 'A' for linear probing
      HashTable hashTableB = new HashTable(count);    //created a hash table 'B' for quadratic probing
      
      getDataA(args[0], hashTableA);       //inserting the strings from file1 into hashTableA using linear
      getDataB(args[0], hashTableB);      //inserting the strings from file1 into hashTableB using quadratic
      
      System.out.println("\t\t\tA");
      System.out.println("--------------------------------------------------------");
      hashTableA.displayTable();     		//printing the a table for insert using linear
      
      System.out.println("\t\t\tB");
      System.out.println("--------------------------------------------------------");
      hashTableB.displayTable();    		//printing the a table for insert using quadratic
      
      System.out.println("\t\t\tA Search");
      System.out.println("-----------------------------------------------"
      				+ "-------------------------------------------------");
       
      hashTableA.searchLinearTable(args[1]);   //printing table for searching using linear (file2)
      
      System.out.println("\t\t\tB Search");
      System.out.println("-----------------------------------------------"
        			+ "-------------------------------------------------");
      hashTableB.searchQuadraticTable(args[1]);  	//printing table for searching using quadratic (file2)
      
      System.out.println("\t\t\tA Delete");
      System.out.println("-----------------------------------------------"
        			+ "-------------------------------------------------");      
      hashTableA.deleteLinearTable(args[2]);  	//printing table for deletes using linear (file3)
      
      System.out.println("\t\t\tB Delete");
      System.out.println("-----------------------------------------------"
        			+ "-------------------------------------------------");      
      hashTableB.deleteQuadraticTable(args[2]);  	//printing table for deletes using quadratic (file3)
      
     

      
      }  // end main()

//--------------------------------------------------------------
   public static int getCount(String file) throws FileNotFoundException
   {
	   int count = 0;   //count for strings
	   File text = new File(file);
	   
	   if(text.canRead() == false){   //checking to see if the file can be read or not
			System.out.println("Can't read file");
			System.exit(0);			
		}
	   Scanner input = new Scanner(text);
	   
	   while(input.hasNext())
	   {
		   input.next();     //read input
		   count++;          //count string
	   }
	   input.close();        //closing file
	   return count;	    
   }
 //--------------------------------------------------------------  
   public static void getDataA(String file, HashTable object) throws FileNotFoundException
   {
	   DataItem aDataItem;    //create a DataItem for a string
	   File text = new File(file);
	   
	   if(text.canRead() == false){   //checking to see if the file can be read or not
			System.out.println("Can't read file");
			System.exit(0);			
		}
	   Scanner input = new Scanner(text);
	   
	   while(input.hasNext())
	   {
		   aDataItem = new DataItem(input.next());   //read input and insert string into DataItem
		   object.insertLinear(aDataItem);     //insert DataItem into hash table using linear probing		   	   
	   }   
	  
	   input.close();        //closing file
	   
   } //end of getDataA()
 //-------------------------------------------------------------- 
   public static void getDataB(String file, HashTable object) throws FileNotFoundException
   {
	   DataItem aDataItem; 		//create a DataItem for a string
	   File text = new File(file);
	   
	   if(text.canRead() == false){   	//checking to see if the file can be read or not
			System.out.println("Can't read file");
			System.exit(0);			
		}
	   Scanner input = new Scanner(text);
	   
	   while(input.hasNext())
	   {
		   aDataItem = new DataItem(input.next()); 		//read input and insert string into DataItem		   
		   object.insertQuadratic(aDataItem);			//insert DataItem into hash table using quadratic probing   
	   }	   
	  
	   input.close();        //closing file
	   
   } //end of getDataB()
 //-------------------------------------------------------------- 
   
   }  // end class HashTableApp
////////////////////////////////////////////////////////////////
