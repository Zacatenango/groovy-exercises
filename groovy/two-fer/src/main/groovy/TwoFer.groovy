class TwoFer 
{
   static String twoFer(String name) 
   {
      return (name && name != "")? "One for $name, one for me": "One for you, one for me"
   }

   public static void main(String[] args)
   {
      def twofer = new TwoFer();
      println "${twoFer()}";
      println "${twoFer("")}";
      println "${twoFer("skvrn")}";
   }
}
