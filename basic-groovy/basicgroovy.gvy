class Foo
{
   int FOO = 5;
   void FOO_FOO()
   {
      // From a Java-like call to a shorthand call, all this is valid Groovy:
      System.out.println("kk = " + FOO);
      println("kk = " + FOO);
      println "kk = " + FOO;
      // Semicolons are optional as well, BASH-style.
      // I'll use them anyway
      println "kk = " + FOO

      // Groovy has dynamic typing
      def skvrn = "prvých";
      println "skvrn = \"" + skvrn + "\"";

      // Groovy has multiline strings
      def povrch = """povrch
      raer
      tufriqana"""
      println "povrch = \"" + povrch + "\""

      // Primitive data types: Groovy counts lists and hash tables as primitives
      // Lists and maps, however, must be declared with def
      // Lists and arrays use Python-style brackets, not C-style braces
      def lista = [ "skvrn", "prvych", "povrch" ];
      def mapa = [ "skvrn":10, "prvych":20, "povrch":30 ];

      // Groovy can evaluate expressions within a double quote string
      println "lista = [ '${lista[0]}', '${lista[1]}', '${lista[2]}' ]";
      println "mapa = [ 'skvrn':${mapa["skvrn"]}, 'prvych':${mapa["prvych"]}, 'povrch':${mapa["povrch"]} ]";

      // Autoboxing means Groovy can in some cases convert automatically a primitive to its equivalent Java class.
      println "The Java class of the number 2 is ${2.getClass()}";

      // Groovy can do Perl-style regexps with operators
      String fruit = "apple";
      if (fruit ==~ /^a[a-z]*/)
      {
         println "It could be an apple";
      }
      else
      {
         println "It's definitely not an apple";
      }

      // Groovy has native support for for in loops:
      print "lista = [ "
      for (item in lista)
      {
         print "'" + item + "', ";
      }
      println "]";

      // Groovy functions have an implicit return
      // That means if there's no return statement, it will take an unassigned value and return it
      // This is meant for one-liner functions
      // Defining a function with def will make its return value dynamically typed
      // Here we have a function defined elsewhere as def reciprocal(int X) { (X != 0)? 1/X: "undefined"; }
      println "1/4 = ${reciprocal(4)}";

      // Closures are Groovy's lambda functions
      def reciprocal_clojure = { X -> (X != 0)? 1/X: "undefined" };
      println "1/4 = ${reciprocal_clojure(4)}";

      // Closures can have multiple parameters and multiple statements, just like Javascript's anonymous functions
      def divide = \
      {
         int X, int Y ->
            if (Y == 0)
               return "undefined";
            else
               return X/Y;
      }
      println "2/5 = ${divide(2,5)}";

      // As in Javascript, closures are often used to implement callback functions APIs and libraries
      def printsomething = { println "Print something" };
      imaginary_event_processor_that_receives_a_callback(printsomething);

      // As in old school Javascript, closures can see the parent scope
      String sooperseekrit = "Hhhhhhhhhhhhhhh";
      def closure_that_can_see_the_secret = { println "I can see variable sooperseekrit, its value is " + sooperseekrit };
      closure_that_can_see_the_secret();

      // Functions can be called out of a dynamically generated string
      def result1 = "reciprocal"(3);
      skvrn = "reciprocal";
      def result2 = "$skvrn"(3);
      println "First dynamic call gave $result1";
      println "Second dynamic call gave $result2";

      // Exception handling is just like in Java
      try
      {
         throw new Exception("JOHN IS KILL");
      }
      catch (exceptionthrown)
      {
         println "Exception caught! Message: ${exceptionthrown.getMessage()}";
      }
      finally
      {
         println "No files were opened during try or catch, nothing to close or cleanup";
      }
   }

   def reciprocal(int X) { (X != 0)? 1/X: "undefined"; }
   void imaginary_event_processor_that_receives_a_callback(param_function)
   {
      param_function();
   }
}

Foo foo = new Foo();
foo.FOO_FOO();
