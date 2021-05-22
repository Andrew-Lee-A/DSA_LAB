package Week6;

/**
   Class which demonstrates how bin sort can be used to
   sort distinct integer numbers between 0 and MAX_VALUE
*/

public class BinSort
{   
   public static void main(String[] args)
   {  int[] list = {17, 2, 23, 7, 7, 41, 29, 19, 43, 31, 5, 11, 47, 13,
         3, 37, 99, 89, 54, 1, 1, 99, 64}; // distinct integer values between 0 and MAX_VALUE
      final int MAX_VALUE = 100;
      int[] bin = new int[MAX_VALUE+1]; //initially all false
      // determine which bins should be set to true
      for (int i=0; i<list.length; i++)
        bin[list[i]]++;
      
      //using the bin, re allocate the array values using the bin values
      int pos = 0;
      for(int i = 0; i < bin.length; ++i)
        {
            int binValue = bin[i];
            
            for(int j = 0; j < binValue; ++j)
            {
                list[pos++] = i;
            }
        }
      // output the results
      for (int i=0; i<list.length; i++)
         System.out.print(((i>0)?", ":"") + list[i]);
      System.out.println();
   }
}
