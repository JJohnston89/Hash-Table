import java.io.*;
import java.util.Scanner;

class HashTable{
   
   private DataItem[] hashArray;    // array holds hash table
   private int arraySize;
   private DataItem nonItem;        // for deleted items
   private long step;
   private long itemCount;
   private long sumProbInsert;   
   
   private long succcessCountLinearSearch;     //variables for the success and failed counts for linear probing searching
   private long failedCountLinearSearch;
   private long succcessSumLinearSearch;
   private long failedSumLinearSearch;
   
   private long succcessSumQuadSearch;        //variables for the success and failed counts for quadratic probing searching
   private long succcessCountQuadSearch;
   private long failedSumQuadSearch;
   private long failedCountQuadSearch;
   
   private long successSumLinearDel;      //variables for the success and failed counts for linear probing deleting
   private long successCountLinearDel;
   private long failedSumLinearDel;
   private long failedCountLinearDel;
   
   private long successSumQuadDel;        //variables for the success and failed counts for quadratic probing deleting
   private long successCountQuadDel;
   private long failedSumQuadDel;
   private long failedCountQuadDel;
   
// -------------------------------------------------------------
   public HashTable(int count)       // constructor
      {
      arraySize = getPrime(count);       //getting a prime number and setting array size to it
      hashArray = new DataItem[arraySize];
      nonItem = new DataItem("DEL");   // deleted item key is "DEL"
      }
// -------------------------------------------------------------  
   public int hashFunc(String key)
      {
	   	int hashValue = key.charAt(0);     //starting with the first char
	   	for(int i = 1; i < key.length(); i++)    //left to right
	   	{
	   		int letter = key.charAt(i);     //get char code
	   		hashValue = (hashValue * 26 + letter) % arraySize; //reducing
	   	}
	   	return hashValue;      
      } //end of hashFunc()
// -------------------------------------------------------------
   public void insertLinear(DataItem item) // insert a DataItem
   // (assumes table not full)
      {
	  step = 0;    //resetting step to 0
      String key = item.getKey();      // extract key
      int hashVal = hashFunc(key);  // hash the key
      
                                    // until empty cell or DEL
      while(hashArray[hashVal] != null &&
                      hashArray[hashVal].getKey() != "DEL")
         {
    	   step++;  //count the prob length
         ++hashVal;               // go to next cell         
         hashVal %= arraySize;     // wraparound if necessary
         }
      itemCount++;    //count items
      step++;         //count prob length
      hashArray[hashVal] = item;    // insert item
      item.setStep(step);        //inserting the prob length for inserting to the dataItem object
      sumProbInsert += step;     //get the sum of prob lengths 
      
      }  // end insertLinear()
// -------------------------------------------------------------
   public void insertQuadratic(DataItem item) // insert a DataItem
   // (assumes table not full)
      {
	  step = 0;      //resetting step to 0
      String key = item.getKey();      // extract key
      int hashVal = hashFunc(key);  // hash the key
      int i = 1;
      
                                    // until empty cell or DEL
      while(hashArray[hashVal] != null &&
                      hashArray[hashVal].getKey() != "DEL")
         {
    	 
    	   step++;     					//count prob length                     
    	   hashVal = hashVal + i*i;    	// go to next cell
    	   i++;    	             
    	   hashVal %= arraySize;	  		// wraparound if necessary         
         }
      
      step++;						 //count prob length
      hashArray[hashVal] = item;     // insert item
      item.setStep(step);            //inserting the prob length for inserting to the dataItem object
      sumProbInsert += step;
      itemCount++;   				//count items
      
      }  // end insertQuadratic()
// -------------------------------------------------------------
   public DataItem deleteLinear(String key)  // delete a DataItem
      {
	   step = 0;                     //resetting step to 0
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // found the key?
    	   step++;   //count the step
         if(hashArray[hashVal].getKey().equals(key))
            {
            DataItem temp = hashArray[hashVal]; // save item
            hashArray[hashVal] = nonItem;       // delete item
            successSumLinearDel += step;        //add the success steps
            successCountLinearDel++;            //count the success steps
            return temp;                        // return item
            }
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;    // wraparound if necessary   
        	      
         }
      step++;   //count the step
      failedSumLinearDel += step;  //add the failed steps
      failedCountLinearDel++;      //count the failed steps
      return null;                  // can't find item
      }  // end deleteLinear()
// -------------------------------------------------------------
   public DataItem deleteQuad(String key)  // delete a DataItem
   {
   step = 0;
   int i = 1;
   int hashVal = hashFunc(key);  // hash the key

   while(hashArray[hashVal] != null)  // until empty cell,
      {                               // found the key?
	  step++;
      if(hashArray[hashVal].getKey().equals(key))
         {
         DataItem temp = hashArray[hashVal]; // save item
         hashArray[hashVal] = nonItem;       // delete item
         successSumQuadDel += step;
      	 successCountQuadDel++;
         return temp;                        // return item
         }
      hashVal = hashVal + i*i;                 // go to next cell
      i++;      
      hashVal %= arraySize;     // wraparound if necessary
      }
   step++;
   failedSumQuadDel += step;
   failedCountQuadDel++;
   return null;                  // can't find item
   }  // end deleteQuad()
//-------------------------------------------------------------
   
   public DataItem findLinear(String key)    // find item with key
      {
	  
      int hashVal = hashFunc(key);  // hash the key
      step = 0;
      
      
      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // found the key?
    	   step++;
         if(hashArray[hashVal].getKey().equals(key))
         {
        	succcessSumLinearSearch += step;
        	succcessCountLinearSearch++;
            return hashArray[hashVal];   // yes, return item
         }
         ++hashVal;                // go to next cell                 
         hashVal %= arraySize;       // wraparound if necessary        
         }
      step++;
      failedSumLinearSearch += step;
      failedCountLinearSearch++;
      
      return null;                  // can't find item
      }//end of findLinear()
// -------------------------------------------------------------
   public DataItem findQuadratic(String key)    // find item with key
   {
	   
   int i = 1;
   int hashVal = hashFunc(key);  // hash the key
   step = 0;   
   
   while(hashArray[hashVal] != null)  // until empty cell,
      {                               // found the key?
 	   step++;
      if(hashArray[hashVal].getKey().equals(key))
      {
    	 succcessSumQuadSearch += step;
       succcessCountQuadSearch++;
       return hashArray[hashVal];   // yes, return item
      }
      hashVal = hashVal + i*i;                 // go to next cell
      i++;          
      hashVal %= arraySize;      // wraparound if necessary
      
      }
   step++;
   failedSumQuadSearch += step;
   failedCountQuadSearch++;
   
   return null;                  // can't find item
   } //end of findQuadratic()
// -------------------------------------------------------------
   private static int getPrime(int count)    //returns first prime > min
   {
	   int min = 2 * count;               
	   for(int i = min + 1; true; i++)    //for all i > min
	   {
		   if(isPrime(i))                //is i prime?
			   return i;                 //yes, return it
	   }
   }//end of getPrime()
// -------------------------------------------------------------
   private static boolean isPrime(int n)
   {
	   for(int i = 2; (i * i <= n); i++) //for all i
	   {                                 
		   if(n % i == 0)                //divides evenly by i
			   return false;             //yes, so not prime      
	   }
	   return true;                      //no, so prime
   }// end of isPrime()
// -------------------------------------------------------------
   public long getStep()
   {
	   return step;
   } //end of getStep()
// -------------------------------------------------------------
   public void displayTable(){
		 //display the insertion table	     
		 System.out.printf("%-15s%-15s%-15s\n",
			                "Index", "String", "probe length for insertion");
		 
		  for(int i = 0; i < arraySize; i++){
		       if(hashArray[i] != null && hashArray[i].getKey() != "DEL")
		       {   	    	   
		    	   System.out.printf("%-15d%-15s%-15d\n",
		    			   			  i, hashArray[i].getKey(), hashArray[i].getStep());
		       }
		       
	             }
		  System.out.printf("average probe length:     \t   %.2f\n", getAvgProbLengthInsert());
	 }//end of displayCodeTable()
// -------------------------------------------------------------
   public double getAvgProbLengthInsert() 
   {
	   
	   return (double)sumProbInsert / itemCount; 
   } // end of getAvgProbLengthInsert
// -------------------------------------------------------------
   public void searchLinearTable(String file) throws FileNotFoundException
   {
	   
	   String key; 
	   File text = new File(file);
	   
	   if(text.canRead() == false){          //checking to see if the file can be read or not
			System.out.println("Can't read file");
			System.exit(0);			
		}
	   Scanner input = new Scanner(text);
	   
	   System.out.printf("%-15s%-15s%-15s%-30s%-30s\n",   //printing search table
			   			 "String", "Succcess", "Failure", "Probe length for success", "Probe length for failure");
	   while(input.hasNext())
	   {
		   key = input.next();
		   
		   
		   if(findLinear(key) == null) //searching for the key
		   {
			   System.out.printf("%-15s%-15s%-15s%-30s%-30d\n",
			           			 key, "***", "yes", "***", getStep()); 
		   }
		   else
			   {
			   System.out.printf("%-15s%-15s%-15s%-30d%-30s\n",			   
			                     key, "yes", "***", getStep(), "***");
			   }
		   
		   	   
	   }
	   double successAvg = (double) succcessSumLinearSearch / succcessCountLinearSearch;  //get the average of success linear prob length
	   double failedAvg = (double) failedSumLinearSearch / failedCountLinearSearch;       //get the average of failed linear prob length
	   System.out.printf("average probe length:\t\t\t     %.2f  \t\t\t  %.2f \n", successAvg, failedAvg);
	   
	  
	   input.close();        //closing file
   }//end of searchLinearTable()
// -------------------------------------------------------------   
   public void searchQuadraticTable(String file) throws FileNotFoundException
   {
	   
	   String key; 
	   File text = new File(file);
	   
	   if(text.canRead() == false){   //checking to see if the file can be read or not
			System.out.println("Can't read file");
			System.exit(0);			
		}
	   Scanner input = new Scanner(text);
	   
	   System.out.printf("%-15s%-15s%-15s%-30s%-30s\n", //printing search table
			   			"String", "Succcess", "Failure", "Probe length for success", "Probe length for failure");
	   while(input.hasNext())
	   {
		   key = input.next();
		   
		   
		   if(findQuadratic(key) == null)     //searching for the key
		   {
			   System.out.printf("%-15s%-15s%-15s%-30s%-30d\n",
			           			key, "***", "yes", "***", getStep()); 
		   }
		   else
			   {
			   System.out.printf("%-15s%-15s%-15s%-30d%-30s\n",			   
			           			key, "yes", "***", getStep(), "***");
			   }
		   
		   	   
	   }
	   
	   double successAvg = (double) succcessSumQuadSearch / succcessCountQuadSearch;    //get the average of success quadratic prob length
	   double failedAvg = (double) failedSumQuadSearch / failedCountQuadSearch;         //get the average of failed quadratic prob length
	   System.out.printf("average probe length:\t\t\t     %.2f  \t\t\t  %.2f \n", successAvg, failedAvg);
	   
	   input.close();        //closing file
   }// end of searchQuadraticTable
// -------------------------------------------------------------     
   public void deleteLinearTable(String file) throws FileNotFoundException
   {
	   
	   String key; 
	   File text = new File(file);
	   
	   if(text.canRead() == false){                  //checking to see if the file can be read or not
			System.out.println("Can't read file");
			System.exit(0);			
		}
	   Scanner input = new Scanner(text);
	   
	   System.out.printf("%-15s%-15s%-15s%-30s%-30s\n",    //printing deletion table
			   			 "String", "Succcess", "Failure", "Probe length for success", "Probe length for failure");
	   while(input.hasNext())
	   {
		   key = input.next();
		   
		   
		   if(deleteLinear(key) == null)         //searching then delete key if found
		   {
			   System.out.printf("%-15s%-15s%-15s%-30s%-30d\n",
			           			key, "***", "yes", "***", getStep()); 
		   }
		   else
			   {
			   System.out.printf("%-15s%-15s%-15s%-30d%-30s\n",			   
			           			key, "yes", "***", getStep(), "***");
			   }
		   
		   	   
	   }
	   double successAvg = (double) successSumLinearDel / successCountLinearDel;  //get the average of success linear prob length
	   double failedAvg = (double) failedSumLinearDel / failedCountLinearDel;     //get the average of failed linear prob length
	   System.out.printf("average probe length:\t\t\t     %.2f  \t\t\t  %.2f \n", successAvg, failedAvg);	   
	  
	   input.close();        //closing file
   }//end of deleteLinearTable()
// -------------------------------------------------------------   
   public void deleteQuadraticTable(String file) throws FileNotFoundException
   {
	   
	   String key; 
	   File text = new File(file);
	   
	   if(text.canRead() == false){   				//checking to see if the file can be read or not
			System.out.println("Can't read file");
			System.exit(0);			
		}
	   Scanner input = new Scanner(text);
	   
	   System.out.printf("%-15s%-15s%-15s%-30s%-30s\n",    //printing deletion table
			   			 "String", "Succcess", "Failure", "Probe length for success", "Probe length for failure");
	   while(input.hasNext())
	   {
		   key = input.next();
		   
		   
		   if(deleteQuad(key) == null)        //searching then delete key if found
		   {
			   System.out.printf("%-15s%-15s%-15s%-30s%-30d\n",
			           			 key, "***", "yes", "***", getStep()); 
		   }
		   else
			   {
			   System.out.printf("%-15s%-15s%-15s%-30d%-30s\n",			   
			           			 key, "yes", "***", getStep(), "***");
			   }
		   
		   	   
	   }
	   double successAvg = (double) successSumQuadDel / successCountQuadDel;     //get the average of success quadratic prob length
	   double failedAvg = (double) failedSumQuadDel / failedCountQuadDel;        //get the average of failed quadratic prob length
	   System.out.printf("average probe length:\t\t\t     %.2f  \t\t\t  %.2f \n", successAvg, failedAvg);
	   
	   input.close();        //closing file
   }//end of deleteQuadraticTable()   
// -------------------------------------------------------------
   }  // end class HashTable
////////////////////////////////////////////////////////////////
