class DataItem{
                                  
   private String sData;           // data item (key)
   
   private long step;              //keep track of steps from insertion 
   
//--------------------------------------------------------------
   public DataItem(String sData)          // constructor
      { this.sData = sData; }
//-------------------------------------------------------------- 
   public String getKey()
      { return sData; }    //return the key
//--------------------------------------------------------------
   public long getStep()
   {
	   return step;     //return steps
   }
 //--------------------------------------------------------------
   public void setStep(long step)
   {
	   this.step = step;      //set steps
   }
 //--------------------------------------------------------------   
   }  // end class DataItem
////////////////////////////////////////////////////////////////
