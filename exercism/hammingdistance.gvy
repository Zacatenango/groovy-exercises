class Hamming 
{
   def distance(strand1, strand2) 
   {
      // Corner case: are both strands empty? This is not an anomalous condition, so we output distance 0 and ax the function
      if (strand1 == "" && strand2 == "")
      {
         return 0;
      }
      
      // Sanity check: is either strand empty? Throw an arithmetic exception
      if (strand1 == null || strand1 == "" || strand2 == null || strand2 == "")
      {
         throw new ArithmeticException("ERROR: Strands cannot be empty");
      }

      // Sanity check: are both strands of different length? Throw an arithmetic exception
      if (strand1.size() != strand2.size())
      {
         throw new ArithmeticException("ERROR: Strands must be of same length");
      }

      // Are both strands equal? Automatically that means the distance is 0, so we output 0 and ax the function
      if (strand1.equals(strand2))
      {
         return 0;
      }

      // Are both strands different and a single letter? That means the distance is 1
      if (strand1.size == 1 && strand2.size == 1 && !strand1.equals(strand2))
      {
         return 1;
      }

      // After having passed all those previous checks, we can now assume our strings are different, 
      // equal length, at least 2 letters, and none is null or empty
      // We proceed to calculate distance
   }

   public static void main(String[] args)
   {
      //
   }
}